<template>
    <div class="school">
        <h2>学校名称：{{ name }}</h2>
        <h2>学校地址：{{ address }}</h2>
    </div>
</template>

<script>
    import pubsub from "pubsub-js";
    export default {
        name: "School",
        data() {
            return {
                name: "上海大学",
                address: "上海",
            };
        },
        mounted() {
            // this.$bus.$on("hello", (data) => {
            //     console.log("School收到的数据", data);
            // });
            this.pid = pubsub.subscribe("hello", (msg, data) => {
                console.log("接收到hello主题的消息", msg, data);
            });
        },
        beforeDestroy() {
            // this.$bus.$off("hello");
            pubsub.unsubscribe(this.pid);
        },
    };
</script>

<style scoped>
    .school {
        background-color: skyblue;
        padding: 5px;
    }
</style>
