import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import http from "@/common/axios.js";
import util from "@/common/util.js";
import router from "@/routers/routers.js";

export default new Vuex.Store({
    state: {
        login: {
            // NavBar
            isLogin: false,
            isKakao: false,

            // Login
            id: "ssafy",
            password: "1234",
        },

        user: {
            // userInfo
            id: "",
            password: "",
            name: "",
            address: "",
            phone: "",
            userCode: "",
        },

        address: {
            sidoList: [],
            sido: "0", // 최초 선택 항목 지정
            gugunList: [],
            gugun: "0",
            dongList: [],
            dong: "0",
        },

        apartment: {
            aptName: "apart",
        },

        kakao: {
            kakaoMap: ''
        },

        board: {
            isDetail: false,

            list: [],
            limit: 5,
            offset: 0,
            searchWord: "",

            listRowCount: 5,
            pageLinkCount: 5,
            currentPageIndex: 1,

            totalListItemCount: 0,

            boardId: 0,
            title: "",
            content: "",
            userId: "",
            regDate: "",
            regTime: "",
            sameUser: false,
            fileList: [],
            boardCode: "",

            readCount: 0,
        },
    },
    // state 변경을 하는 것
    mutations: {
        SET_LOGIN(state, payload) {
            state.login.isLogin = true;
            // console.log(state.login.isLogin);

            state.user.id = payload.id;
            state.user.password = payload.password;
            state.user.name = payload.name;
            state.user.address = payload.address;
            state.user.phone = payload.phone;
            state.user.userCode = payload.userCode;
        },
        SET_LOGOUT(state, payload) {
            state.login.isLogin = false;
            state.login.isKakao = false;

            state.login.id = "ssafy";
            state.login.password = "1234";

            state.user.id = "";
            state.user.password = "";
            state.user.name = "";
            state.user.address = "";
            state.user.phone = "";
            state.user.userCode = "";
        },
        SET_KAKAO_LOGIN(state, payload) {
            state.login.isLogin = true;
            state.login.isKakao = true;

            state.login.id = payload.id;
            state.login.password = payload.password;

            state.user.id = payload.id;
            state.user.password = payload.password;
            state.user.name = payload.name;
            state.user.address = payload.address;
            state.user.phone = payload.phone;
            state.user.userCode = payload.userCode;
        },

        SET_ADDRESS_SIDOlIST(state, payload) {
            state.address.sidoList = payload;
        },
        SET_ADDRESS_GUGUNlIST(state, payload) {
            state.address.gugunList = payload;
        },
        SET_ADDRESS_DONGlIST(state, payload) {
            state.address.dongList = payload;
        },

        SET_ADDRESS_SIDO(state, payload) {
            state.address.sido = payload;

            state.address.gugun = "0";
            state.address.dong = "0";
            state.address.gugunList = [];
            state.address.dongList = [];
        },
        SET_ADDRESS_GUGUN(state, payload) {
            state.address.gugun = payload;

            state.address.dong = "0";
            state.address.dongList = [];
        },
        SET_ADDRESS_DONG(state, payload) {
            state.address.dong = payload;
        },

        SET_APARTMENT_NAME(state, payload) {
            state.apartment.aptName = payload;
        },

        SET_KAKAO_MAP(state, payload) {
            state.kakao.kakaoMap = payload;
        },


        SET_BOARD_CODE(state, code) {
            state.board.boardCode = code;
        },
        SET_BOARD_TITLE(state, title) {
            state.board.title = title;
        },
        SET_BOARD_LIST(state, list) {
            state.board.list = list;
        },
        SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count) {
            state.board.totalListItemCount = count;
        },
        SET_BOARD_DETAIL(state, payload) {
            state.board.isDetail = true;

            state.board.boardId = payload.boardId;
            state.board.title = payload.title;
            state.board.content = payload.content;
            state.board.userId = payload.userId;
            state.board.regDate = util.makeDateStr(payload.regDt.date.year, payload.regDt.date.month, payload.regDt.date.day, ".");
            state.board.regTime = util.makeTimeStr(payload.regDt.time.hour, payload.regDt.time.minute, payload.regDt.time.second, ":");
            state.board.sameUser = payload.sameUser;
            state.board.fileList = payload.fileList;

            state.board.readCount = payload.readCount;
        },
        SET_BOARD_DETAIL_DEFAULT(state) {
            state.board.isDetail = false;

            state.board.boardId = "",
            state.board.title = "",
            state.board.content = "",
            state.board.userId = "",
            state.board.regDate = "";
            state.board.regTime = "";
            state.board.sameUser = false;
            state.board.fileList = [];
        }
    },
    // 비동기 메서드
    actions: {
        async login(context) {
            try {
                let id = this.state.login.id;
                let password = this.state.login.password;

                let { data } = await http.post("/login", { id, password });
                console.log("login data : " + data.result);

                sessionStorage.setItem("userDto", data.dto);

                context.commit("SET_LOGIN", data.dto);
                router.push('/MainPage');
            } catch (error) {
                console.log(error);
            }
        },
        async updateUser() {
            try {
                let response = await http.put(
                    "/users/" + this.state.user.id,
                    this.state.user
                );
                let { data } = response;

                console.log(data);

                // if (data.result == "login") {
                    
                // }

                router.push("/MainPage");
            } catch (error) {
                console.error(error);
            }
        },
        async userDelete(context) {
            try {
                let response = await http.delete("/users/" + this.state.login.id);
                let { data } = response;

                console.log(data);

                context.commit("SET_LOGOUT", "");
                router.push("/MainPage");
            } catch (error) {
                console.log(error);
            }
        },

        async getSidoList(context) {
            try {
                let { data } = await http.get("/search/sl");
                context.commit("SET_ADDRESS_SIDOlIST", data);
            } catch (error) {
                console.log(error);
            }
        },
        async getGugunList(context) {
            try {
                let { data } = await http.get("/search/sl2/" + this.state.address.sido);
                console.log(data);
                context.commit("SET_ADDRESS_GUGUNlIST", data);
            } catch (error) {
                console.log(error);
            }
        },
        async getDongList(context) {
            try {
                let { data } = await http.get(
                    "/search/sl3/" + this.state.address.gugun
                );
                console.log(data);
                context.commit("SET_ADDRESS_DONGlIST", data);
            } catch (error) {
                console.log(error);
            }
        },


        async boardList(context) {
            let params = {
                limit: this.state.board.limit,
                offset: this.state.board.offset,
                searchWord: this.state.board.searchWord,
            };

            try {
                let response;
                if (this.state.board.boardCode == "001") {
                    response = await http.get("/freeboards", { params });
                    console.log("freeBoard : ");
                } else if (this.state.board.boardCode == "002") {
                    response = await http.get("/noticeboards", { params });
                    console.log("noticeBoard : ");
                }
                let { data } = response;

                console.log(data);

                if (data.result == "login") {
                    router.push("/login");
                } else {
                    context.commit("SET_BOARD_LIST", data.list);
                    context.commit("SET_BOARD_TOTAL_LIST_ITEM_COUNT", data.count);
                    context.commit("SET_BOARD_DETAIL_DEFAULT");
                }
            } catch (error) {
                console.error(error);
            }
        },
        async boardDetail(context, boardId) {
            try {
                let response;
                if (this.state.board.boardCode == '001') {
                    response = await http.get("/freeboards/" + boardId);
                } else {
                    response = await http.get("/noticeboards/" + boardId);
                }
                let { data } = response;
                console.log("board detail : ");
                console.log(data);

                if (data.result == "login") {
                    router.push("/MainPage");
                } else {
                    let { dto } = data;

                    console.log(dto);

                    context.commit("SET_BOARD_DETAIL", dto);
                }

                console.log(this.state.board.fileList);

            } catch (error) {
                console.log("freeBoardDetail Error : ");
                console.warn(error);
            }
        },
        async boardDelete(context, dispatch) {
            try {
                let response;
                if (this.state.board.boardCode == '001') {
                    response = await http.delete("/freeboards/" + this.state.board.boardId);
                } else {
                    response = await http.delete("/noticeboards/" + this.state.board.boardId);
                }
                let { data } = response;
                console.log("board delete : ");
                console.log(data);

                dispatch("boardList");
            } catch (error) {
                console.log("boardDeleteError : ");
                console.warn(error);
            }
        }
    },
    getters: {
        isLogin(state) {
            return state.login.isLogin;
        },
        getUserId(state) {
            return state.user.id;
        },
        getUserPassword(state) {
            return state.user.password;
        },
        getUserName(state) {
            return state.user.name;
        },
        getUserAddress(state) {
            return state.user.address;
        },
        getUserPhone(state) {
            return state.user.phone;
        },

        getSidoList(state) {
            return state.address.sidoList;
        },
        getGugunList(state) {
            return state.address.gugunList;
        },
        getDongList(state) {
            return state.address.dongList;
        },

        getSido(state) {
            return state.address.sido;
        },
        getGugun(state) {
            return state.address.gugun;
        },
        getDong(state) {
            return state.address.dong;
        },

        getApartName(state) {
            return state.apartment.aptName;
        },

        getKakaoMap(state) {
            return state.kakao.kakaoMap;
        },


        getBoardList(state) {
            return state.board.list;
        },
    },
});
