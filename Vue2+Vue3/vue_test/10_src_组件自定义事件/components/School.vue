<template>
    <div class="school">
        <h2>学校名称：{{ name }}</h2>
        <h2>学校地址：{{ address }}</h2>
        <button @click="sendSchoolName">把学校名给App</button>
    </div>
</template>

<script>
    export default {
        name: "School",
        props: ["getSchoolName"],
        data() {
            return {
                name: "上海大学",
                address: "上海",
            };
        },
        methods: {
            sendSchoolName() {
                this.getSchoolName(this.name);
            },
        },
    };
</script>

<style scoped>
    .school {
        background-color: skyblue;
        padding: 5px;
    }
</style>
