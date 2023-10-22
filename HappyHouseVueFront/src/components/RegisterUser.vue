<template>
    <div class="container-fluid bg-transparent">
        <h1 class="mb-3 mt-5">회원 가입</h1>
        <p class="mb-4">
            <b>
                계정을 등록하세요!!<br />
                안내에 따라 ID, Password, 이름, e-mail, 전화번호를 입력하세요!
            </b>
        </p>

        <div class="row">
            <div class="col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                <img class="position-relative rounded w-100 h-100"
                    src="@/img/property-2.jpg"
                    frameborder="0" style="min-height: 400px; border: 0" allowfullscreen="" aria-hidden="false"
                    tabindex="0"/>
            </div>

            <div class="col-md-6 wow fadeInUp mt-5" data-wow-delay="0.5s">
                <div class="wow fadeInUp" data-wow-delay="0.5s">

                    <div class="row g-3">
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="name" id="userNameBox" class="form-control" :class="{
                                    'is-valid': isNameFocusAndValid,
                                    'is-invalid': isNameFocusAndInvalid,
                                }" v-model="name" @input="validateName" @focus="isNameFocus = true" />
                                <label for="userNameBox" class="form-label">NAME</label>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">올바른 이름을 입력해 주세요.</div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="text" id="userIdBox" class="form-control" :class="{
                                    'is-valid': isIdFocusAndValid,
                                    'is-invalid': isIdFocusAndInvalid,
                                }" v-model="id" @input="validateId" @focus="isIdFocus = true" />
                                <label for="userIdBox" class="d-inline col-form-label">ID</label>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">ID는 6자 이상이어야 합니다.</div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="password" id="userPasswordBox" class="form-control" :class="{
                                    'is-valid': isPwFocusAndValid,
                                    'is-invalid': isPwFocusAndInvalid,
                                }" v-model="password" @input="validatePw" @focus="isPwFocus = true" />
                                <label for="userPasswordBox" class="form-label">PASSWORD</label>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">
                                    1개 이상의 특수문자, 대소문자 및 숫자를 포함하고 8자리
                                    이상이여야 합니다.
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="email" id="userAddressBox" class="form-control" :class="{
                                    'is-valid': isAddrFocusAndValid,
                                    'is-invalid': isAddrFocusAndInvalid,
                                }" v-model="address" @input="validateAddr" @focus="isAddrFocus = true" />
                                <label for="userAddressBox" class="form-label">E-MAIL</label>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">
                                    올바른 Email 을 입력해 주세요.
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating">
                                <input type="phone" id="userPhoneBox" class="form-control" :class="{
                                    'is-valid': isPhoneFocusAndValid,
                                    'is-invalid': isPhoneFocusAndInvalid,
                                }" v-model="phone" @input="validatePhone" @focus="isPhoneFocus = true" />
                                <label for="userPhoneBox" class="form-label">PHONE</label>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">
                                    올바른 Phone Number 를 입력해 주세요.
                                </div>
                            </div>
                        </div>

                        <div class="col-12">
                            <button @click="registUser" style="background-color: rgb(0, 185, 142)" type="button"
                                class="btn btn-outline m-3 text-white col-2">
                                등록 ✔
                            </button>
                            <button @click="cancelRegist" type="button" class="btn btn-outline btn-danger m-3 col-2">
                                취소 ✔
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";

import http from "@/common/axios.js";

Vue.use(VueAlertify);

export default {
    data() {
        return {
            // data
            id: "",
            password: "",
            name: "",
            address: "",
            phone: "",

            // focus
            isIdFocus: false,
            isPwFocus: false,
            isNameFocus: false,
            isAddrFocus: false,
            isPhoneFocus: false,

            // validation
            isIdValid: false,
            isPwValid: false,
            isNameValid: false,
            isAddrValid: false,
            isPhoneValid: false,
        };
    },
    methods: {
        async registUser() {
            try {
                let response = await http.post("/users", {
                    id: this.id,
                    password: this.password,
                    name: this.name,
                    address: this.address,
                    phone: this.phone,
                });
                let { data } = response;

                console.log(data);
                this.$alertify.success("등록이 완료되었습니다.");
                this.$router.push("/MainPage");
            } catch (error) {
                console.error(error);
                this.$alertify.error("사용자 등록 중 문제가 발생하였습니다.");
            }
        },
        cancelRegist() {
            this.$router.push("/MainPage");
        },
        validateId() {
            this.isIdValid = this.id.length > 5 ? true : false;
            console.log(this.isIdValid);
        },
        validatePw() {
            let patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
            let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
            let patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

            this.isPwValid =
                patternEngAtListOne.test(this.password) &&
                    patternSpeAtListOne.test(this.password) &&
                    patternNumAtListOne.test(this.password) &&
                    this.password.length >= 8
                    ? true
                    : false;
        },
        validateName() {
            this.isNameValid = this.name.length > 0 ? true : false;
            console.log(this.isNameValid);
        },
        validateAddr() {
            let regexp =
                /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            this.isAddrValid = regexp.test(this.address) ? true : false;
            console.log(this.isAddrValid);
        },
        validatePhone() {
            let regexp = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
            this.isPhoneValid = regexp.test(this.phone) ? true : false;
            console.log(this.isPhoneValid);
        },
    },
    computed: {
        isIdFocusAndValid() {
            return this.isIdFocus && this.isIdValid;
        },
        isIdFocusAndInvalid() {
            return this.isIdFocus && !this.isIdValid;
        },
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
};
</script>

<style>
</style>