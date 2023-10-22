<template>
    <div class="container-fluid bg-transparent">
        <legend class="mb-3 mt-5">{{ title }}</legend>
        <div class="row">
            <div class="col-md-4 wow fadeInUp" data-wow-delay="0.1s">
                <img class="position-relative rounded w-100 h-100"
                    src="@/img/property-3.jpg"
                    frameborder="0" style="min-height: 400px; border: 0" allowfullscreen="" aria-hidden="false"
                    tabindex="0"/>
            </div>

            <div class="col-md-8 wow fadeInUp mt-3" data-wow-delay="0.5s">
                <fieldset class="m-4" disabled>
    
                    <div class="row g-3">
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="text" id="userIdBox" class="form-control" v-model="$store.state.user.id" disabled>
                                <label for="userIdBox" class="d-inline col-form-label">ID</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="name" id="userNameBox" class="form-control" :class="{'is-valid':isNameFocusAndValid, 'is-invalid':isNameFocusAndInvalid}" v-model="$store.state.user.name" @input="validateName" @focus="isNameFocus = true">
                                <label for="userNameBox" class="form-label">NAME</label>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">올바른 이름을 입력해 주세요.</div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="password" id="userPasswordBox" class="form-control" :class="{'is-valid':isPwFocusAndValid, 'is-invalid':isPwFocusAndInvalid}" v-model="$store.state.user.password" @input="validatePw" @focus="isPwFocus = true">
                                <label for="userPasswordBox" class="form-label">PASSWORD</label>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">1개 이상의 특수문자, 대소문자 및 숫자를 포함하고 8자리 이상이여야 합니다.</div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="email" id="userAddressBox" class="form-control" :class="{'is-valid':isAddrFocusAndValid, 'is-invalid':isAddrFocusAndInvalid}" v-model="$store.state.user.address" @input="validateAddr" @focus="isAddrFocus = true">
                                <label for="userAddressBox" class="form-label">E-MAIL</label>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">올바른 Email 을 입력해 주세요.</div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="phone" id="userPhoneBox" class="form-control" :class="{'is-valid':isPhoneFocusAndValid, 'is-invalid':isPhoneFocusAndInvalid}" v-model="$store.state.user.phone" @input="validatePhone" @focus="isPhoneFocus = true">
                                <label for="userPhoneBox" class="form-label">PHONE</label>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">올바른 Phone Number 를 입력해 주세요.</div>
                            </div>
                        </div>
                    </div>
                </fieldset>
                
                    <div class="col-12">
                        <button @click="endCheck" v-show="!isUpdate" type="button" class="btn btn-outline btn-primary m-1 col-2">
                            돌아가기 ✔
                        </button>
                        <button v-show="!isUpdate&&!this.$store.state.login.isKakao" @click="updateInfo" style="background-color: rgb(0, 185, 142)" type="button"
                            class="btn btn-outline m-1 text-white col-2">
                            수정 ✔
                        </button>
                        <button @click="deleteUser" v-show="!isUpdate" type="button" class="btn btn-outline btn-danger m-1 col-2">
                            탈퇴 ✔
                        </button>
                        <button v-show="isUpdate" @click="acceptUpdate" style="background-color: rgb(0, 185, 142)" type="button"
                            class="btn btn-outline m-1 text-white col-2">
                            수정하기 ✔
                        </button>
                    </div>
            </div>

        </div>
    </div>
</template>

<script>
export default {
    props: ['userId'],
    data() {
        return {
            title: '회원 정보 확인',

            isUpdate: false,

            // focus
            isPwFocus: false,
            isNameFocus: false,
            isAddrFocus: false,
            isPhoneFocus: false,

            // validation
            isPwValid: true,
            isNameValid: true,
            isAddrValid: true,
            isPhoneValid: true,
        }
    },
    methods: {
        endCheck() {
            this.$router.push('/');
        },
        updateInfo() {
            this.title = '회원 정보 수정'
            document.querySelector("fieldset").removeAttribute('disabled');
            this.isUpdate = true;
        },
        acceptUpdate() {
            this.$store.dispatch('updateUser')
                .then(response=> {
                    this.$alertify.success('회원 정보 수정에 성공했습니다.');
                }, error => {
                    this.$alertify.error('회원 정보 수정에 실패했습니다.');
                });
        },
        acceptDelete() {
            this.$store.dispatch('userDelete')
                .then(response=> {
                    this.$alertify.success('회원 탈퇴에 성공했습니다.');
                }, error => {
                    this.$alertify.error('회원 탈퇴에 실패했습니다.');
                });
        },
        deleteUser() {
            var $this = this;
            this.$alertify.confirmWithTitle(
                '탈퇴 확인', '정말로 탈퇴 하시겠습니까?',
                function() {
                    $this.acceptDelete();
                },
                function() {
                    console.log('cancel');
                }
            )
        },
        validatePw() {
            let patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
            let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
            let patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

            let password = this.$store.getters.getUserPassword;
            this.isPwValid = patternEngAtListOne.test(password) && patternSpeAtListOne.test(password) && patternNumAtListOne.test(password) && password.length >= 8 ? true : false;
        },
        validateName() {
            console.log(this.$store.state.user.name);
            this.isNameValid = this.$store.getters.getUserName.length > 0 ? true : false;
            console.log(this.isNameValid);
        },
        validateAddr() {
            let regexp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            this.isAddrValid = regexp.test(this.$store.getters.getUserAddress) ? true : false;
            console.log(this.isAddrValid);
        },
        validatePhone() {
            let regexp = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
            this.isPhoneValid = regexp.test(this.$store.getters.getUserPhone) ? true : false;
            console.log(this.isPhoneValid);
        },
    },
    computed: {
        isPwFocusAndValid() {
            return this.isPwFocus && this.isPwValid;
        },
        isPwFocusAndInvalid() {
            return this.isPwFocus && !this.isPwValid;
        },
        isNameFocusAndValid() {
            return this.isNameFocus && this.isNameValid;
        },
        isNameFocusAndInvalid() {
            return this.isNameFocus && !this.isNameValid;
        },
        isAddrFocusAndValid() {
            return this.isAddrFocus && this.isAddrValid;
        },
        isAddrFocusAndInvalid() {
            return this.isAddrFocus && !this.isAddrValid;
        },
        isPhoneFocusAndValid() {
            return this.isPhoneFocus && this.isPhoneValid;
        },
        isPhoneFocusAndInvalid() {
            return this.isPhoneFocus && !this.isPhoneValid;
        },
    },

}
</script>

<style>
</style>