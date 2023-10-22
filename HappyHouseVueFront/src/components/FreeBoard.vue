<template>
    <div class="container-xxl py-5">
        <div class="container">
            <div class="row g-0 gx-5 align-items-end">
                <div class="col-lg-6">
                    <div class="text-start mx-auto mb-5 wow slideInLeft"
                        data-wow-delay="0.1s">
                        <h1 class="mb-3">자유게시판</h1>
                    </div>
                </div>
                <div class="col-lg-6 text-start text-lg-end wow slideInRight"
                    data-wow-delay="0.1s">
                    <ul class="nav nav-pills d-inline-flex justify-content-end mb-5">
                        <li class="nav-item me-2">
                            <a @click="showInsert" style="background-color: rgb(0, 185, 142)" class="btn active text-white"
                                data-bs-toggle="pill"
                                href="#tab-1">글쓰기 ✔</a>
                        </li>
                        <insert-modal v-on:call-parent-insert="afterInsert"></insert-modal>
                    </ul>
                </div>
            </div>
            
            <div class="tab-content">
                <div id="tab-1" class="tab-pane fade show p-0 active">
                    <div class="row g-4">
                        
                        <div class="col-lg-4 wow fadeInUp overflow-auto" style="height:400px" data-wow-delay="0.1s">
                            <div style="cursor:pointer" v-for="(board, index) in getList" :key="index" @click="boardDetail(board.boardId)">
                                <board-card :board="board"></board-card>
                            </div>
                        </div>

                        <div class="col-lg-8 wow fadeInUp" data-wow-delay="0.1s">
                            <div v-show="!$store.state.board.isDetail" class="bg-white rounded p-4">
                                <h3>자유게시판 규칙</h3><br>
                                <h5 class="mb-3">1. 각자 거주 혹은 알고 계신 지역의 장/단점을 자유롭게 작성해주세요.</h5>
                                <h5 class="mb-3">2. 그 외 자유로운 게시글을 작성하셔도 괜찮습니다.</h5>
                                <h5 class="mb-3">3. 목적에 맞지 않거나 음란/ 욕설/ 비방 등 불쾌감/혐오감을 조성하는 글은 삭제됩니다.</h5>
                            </div>

                            <div v-show="$store.state.board.isDetail" class="bg-white border rounded p-4">
                                <div class="contentArea text-start overflow-auto" v-html="$store.state.board.content"></div>
                                <div class="d-flex align-items-center" style="position:relative">
                                    <img src="@/img/testimonial-2.jpg" style="width:45px; height:45px" class="img-fluid flex-shrink-0 rounded">
                                    <div class="ps-3">
                                        <h6>{{$store.state.board.userId}}</h6>
                                        <small> {{ $store.state.board.regDate }} {{ $store.state.board.regTime }}</small>
                                    </div>
                                    <div v-show="$store.state.board.sameUser" class="position-absolute end-0 top-10">
                                        <button @click="showModify" type="button" style="background-color: rgb(0, 185, 142)" class="text-white btn me-3 btn-outline">
                                            수정 ✔
                                        </button>
                                        <modify-modal v-on:call-parent-modify="afterModify"></modify-modal>
                                        <button @click="boardDelete($store.state.board.boardId)" type="button" class="btn btn-outline btn-danger">
                                            삭제 ✔
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>

</template>

<script>
import BoardCard from "./BoardCard.vue";
import InsertModal from "./modals/InsertModal.vue";
import ModifyModal from "./modals/ModifyModal.vue";

import {Modal} from "bootstrap";

export default {
    name: 'FreeBoard',
    components: { BoardCard, InsertModal, ModifyModal },
    data() {
        return {
            insertModal: null,
            modifyModal: null,
        }
    },
    computed: {
        getList() {
            return this.$store.getters.getBoardList;
        }
    },
    methods: {
        boardList() {
            this.$store.dispatch("boardList")
                .then(response=> {
                    this.$alertify.success('글 목록을 불러왔습니다.');
                }, error => {
                    this.$alertify.error('글 목록을 불러오는데에 실패했습니다.');
                });
        },
        boardDetail(boardId) {
            this.$store.dispatch("boardDetail", boardId)
                .then(response=> {
                    this.$alertify.success('글 상세정보를 불러왔습니다.');
                }, error => {
                    this.$alertify.error('글 상세정보를 불러오는데에 실패했습니다.');
                });
        },
        afterInsert() {
            this.insertModal.hide();
            this.boardList();
        },
        showInsert() {
            this.insertModal.show();
        },
        boardDelete() {
            this.$store.dispatch("boardDelete", this.$store.dispatch)
                .then(response=> {
                    this.$alertify.success('글 삭제에 성공했습니다.');
                }, error => {
                    this.$alertify.error('글 삭제에 실패했습니다.');
                });
        },
        showModify() {
            this.modifyModal.show();
        },
        afterModify() {
            this.modifyModal.hide();
            this.boardList();
        }
    },
    created() {
        this.$store.commit("SET_BOARD_DETAIL_DEFAULT");
        this.$store.commit("SET_BOARD_CODE", "001");
        this.boardList();
    },
    mounted() {
        this.insertModal = new Modal(document.getElementById("insertModal"));
        this.modifyModal = new Modal(document.getElementById("modifyModal"));

    }
}
</script>

<style scoped>

.contentArea {
    height: 400px;
}

</style>