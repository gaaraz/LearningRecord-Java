<template>
    <div class="school">
        <h2>学校名称：{{ name }}</h2>
        <h2>学校地址：{{ address }}</h2>
    </div>
</template>

<script>
    export default {
        name: "School",
        data() {
            return {
                name: "上海大学",
                address: "上海",
            };
        },
        mounted() {
            this.$bus.$on("hello", (data) => {
                console.log("School收到的数据", data);
            });
        },
        beforeDestroy() {
            this.$bus.$off("hello");
        },
    };
</script>

<style scoped>
    .school {
        background-color: skyblue;
        padding: 5px;
    }
</style>
