<template>
    <div class="modal" tabindex="-1" id="insertModal">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">글쓰기</h3>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <div class="mb-3">
                        <input v-model="title" id="boardTitle" type="text" class="form-control" placeholder="제목">
                    </div>
                    <div class="mb-3">
                        <div id=divEditorInsert></div>
                    </div>

                    <div v-show="$store.state.board.boardCode == '002'" >
                        <div class="form-check mb-3">
                            <input v-model="attachFile" class="form-check-input" type="checkbox" value=""
                                id="chkFileUploadInsert">
                            <label class="form-check-label" for="chkFileUploadInsert">파일 추가</label>
                        </div>
                        <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
                            <input @change="changeFile" type="file" id="inputFileUploadInsert" multiple>
                            <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
                                <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
                                <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button @click="boardInsert" class="btn btn-sm btn-primary btn-outline" data-dismiss="modal"
                        type="button">등록</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Vue from 'vue';
import CKEditor from '@ckeditor/ckeditor5-vue2';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import VueAlertify from 'vue-alertify';

Vue.use(CKEditor).use(VueAlertify);

import http from "@/common/axios.js";

export default {
    name: "InsertModal",
    data() {
        return {
            title: "",
            CKEditor: "",
            attachFile: false,
            fileList: [],
        }
    },
    methods: {
        initUI() {
            this.title = "",
            this.CKEditor.setData("");
            this.attachFile = false;
            this.fileList = [];
            document.querySelector("#inputFileUploadInsert").value = '';
        },
        changeFile(fileEvent) {
            this.fileList = [];

            const fileArray = Array.from(fileEvent.target.files);
            fileArray.forEach(file => {
                this.fileList.push(URL.createObjectURL(file));
            });
        },
        async boardInsert() {
            let formData = new FormData();
            formData.append("title", this.title);
            formData.append("content", this.CKEditor.getData());

            // file upload : 공지 게시판에서만 사용
            let attachFiles = document.querySelector("#inputFileUploadInsert").files;

            if (attachFiles.length > 0) {
                const fileArray = Array.from(attachFiles);
                fileArray.forEach(file => formData.append("file", file))
            }

            let options = {
                headers: { 'Content-Type': 'multipart/form-data' }
            }

            try {
                let response
                if(this.$store.state.board.boardCode == "001") {
                    response = await http.post('/freeboards', formData, options);
                } else {
                    response = await http.post('/noticeboards', formData, options);
                }
                let { data } = response;

                console.log("InsertModalVue: data : ");
                console.log(data);

                if (data.result == 'login') {
                    this.doLogout();
                } else {
                    this.$alertify.success('글이 등록되었습니다.');
                    this.closeModal();
                }

            } catch (error) {
                console.log("BoardInsertModal error : ");
                console.log(error);
                this.$alertify.error('글 등록에 실패하였습니다.');
            }
        },
        closeModal() {
            this.$emit('call-parent-insert');
        },
    },
    async mounted() {
        try {
            this.CKEditor = await ClassicEditor.create(document.querySelector("#divEditorInsert"));
        } catch (error) {
            console.error(error);
        }

        let $this = this;
        this.$el.addEventListener('show.bs.modal', function () {
            $this.initUI();
        })
    }

}
</script>


<style scoped>
.modal>>>.ck-editor__editable {
    min-height: 300px !important;
}

.modal>>>.thumbnail-wrapper {
    margin-top: 5px;
}

.modal>>>.thumbnail-wrapper img {
    width: 100px !important;
    margin-right: 5px;
    max-width: 100%;
}
</style>