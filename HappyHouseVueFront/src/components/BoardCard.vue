<template>
    <div class="property-item rounded overflow-hidden">
        <div class="p-4 pb-0">
            <div class="d-block h5 mb-2 text-start text-truncate">TITLE : {{board.title}}</div>
            <p class="text-start">
                <i style="color: rgb(0, 185, 142)" class="fa fa-edit me-2"></i>{{ board.userId }}
                &nbsp;<small>{{ board.regDt.date | makeDateStr(".") }}</small>
                <span class="float-end badge bg-light text-dark">{{board.readCount}}</span>
            </p>
        </div>
    </div>
</template>

<script>
export default {
    name: "BoardCard",
    props: ['board'],
    filters: {
        makeDateStr: function (date, separator) {
            return date.year + separator + (date.month < 10 ? "0" + date.month : date.month) + separator + (date.day < 10 ? "0" + date.day : date.day);
        },
    }
}
</script>

<style>
</style>