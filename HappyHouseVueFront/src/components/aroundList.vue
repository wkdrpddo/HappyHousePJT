<template>
    <div>
        <div class="testimonial-item bg-light rounded p-3">
            <div class="bg-white border rounded p-4">
                <p class="d-flex"><strong>{{ aroundInfo.place_name }}</strong></p>
                <div class="d-flex align-items-center">
                    <!-- <img class="img-fluid flex-shrink-0 rounded" src="img/testimonial-1.jpg"
                        style="width: 45px; height: 45px;"> -->
                    <div class="ps-3">
                        <h6 class="fw-bold mb-1">주소 : {{ aroundInfo.address_name }}</h6>
                        <p><small>지번주소 : {{ aroundInfo.road_address_name }} </small></p>
                        <p><small>연락처 : {{ aroundInfo.phone }} </small></p>
                        <!-- <small>{{ aroundInfo.category_name }} </small> -->
                    </div>
                </div>
            </div>
        </div>
        <br>
    </div>
</template>

<script>
import http from "@/common/axios.js";

export default {
    props: ["aroundInfo"],
    components: {},
    data() {
        return {

        };
    },
    methods: {
    },
};
</script>

<style>
</style>
