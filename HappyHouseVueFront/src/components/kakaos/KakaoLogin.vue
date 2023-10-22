<template>
    <div class="container-fluid bg-transparent">
        <h1 class="mb-3 mt-5">Kakao 회원 등록</h1>
        <div><small>정보를 확인하고 '확인' 버튼을 눌러주세요</small></div>

        <div class="row">
            <div class="col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                <img class="position-relative rounded w-100 h-100" src="@/img/property-4.jpg" frameborder="0"
                    style="min-height: 400px; border: 0" allowfullscreen="" aria-hidden="false" tabindex="0" />
            </div>

            <div class="col-md-6 wow fadeInUp mt-5" data-wow-delay="0.5s">
                <div class="wow fadeInUp" data-wow-delay="0.5s">

                    <div class="row g-3">
                        <div class="col-12">
                            <div class="form-floating">
                                <input v-model="user.name" type="name" id="userNameBox" class="form-control"  disabled required />
                                <label for="userNameBox" class="form-label">NAME</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input v-model="user.id" type="text" id="userIdBox" class="form-control" disabled required/>
                                <label for="userIdBox" class="d-inline col-form-label">ID</label>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input v-model="user.address" type="email" id="userAddressBox" class="form-control"
                                    disabled required />
                                <label for="userAddressBox" class="form-label">E-MAIL</label>
                            </div>
                        </div>

                        <div class="col-12">
                            <button @click="registUser" style="background-color: rgb(0, 185, 142)" type="button"
                                class="btn btn-outline m-3 text-white col-2">
                                확인 ✔
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import http from '@/common/axios.js'
export default {
    data() {
        return {
            codes: "",
            user: {
                name: "",
                id: "",
                address: "",
            }
        }
    },
    created() {
        this.create();
    },
    methods: {
        create() {
            this.codes = this.$route.query.code;
            console.log(this.codes);
            
            let data = this.getToken();
            console.log(data);
        },
        getToken() {
            http.get("/loginkakao?authorize_code=" + this.codes)
                .then((res) => {
                    console.log(res);
                    this.user.address = res.data.email;
                    this.user.id = res.data.id;
                    this.user.name = res.data.name;
                    if(this.user.id == undefined) {
                        alert("잘못된 접근");
                        this.$router.push('/MainPage');
                    } else {
                        this.login();
                    }
                });
        },
        async login() {
            console.log("login start");

            let id = this.user.id;
            let address = this.user.address;

            console.log(this.user);

            http.post('/kakao', {id, address})
                .then((res) => {
                    if (res.data != null) {
                        document.cookie = `accessToken=${res.data}`;
                        http.defaults.headers.common["x-access-token"] = res.data;

                        this.$store.commit("SET_KAKAO_LOGIN", res.data.dto);

                        this.$router.push("/MainPage");
                    }
                })
        },
        async registUser() {
            http.post("/kakaoUsers", this.user)
                .then((res) => {
                    console.log("res.status");
                    this.login();
                })
        }
    },

}
</script>

<style>
</style>