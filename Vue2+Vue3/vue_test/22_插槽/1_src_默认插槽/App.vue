<template>
    <div class="container">
        <Category title="美食">
            <img src="https://s3.ax1x.com/2021/01/16/srJlq0.jpg" alt="" />
        </Category>
        <Category title="游戏">
            <ul>
                <li v-for="(g, index) in games" :key="index">{{ g }}</li>
            </ul>
        </Category>
        <Category title="电影">
            <img src="https://s3.ax1x.com/2021/01/16/srJlq0.jpg" alt="" />
        </Category>
    </div>
</template>

<script>
    import Category from "./components/Category.vue";

    export default {
        name: "App",
        components: { Category },
        data() {
            return {
                foods: ["火锅", "烧烤", "炸串", "海鲜"],
                games: ["黑神话", "只狼", "黑魂", "仁王"],
                films: [
                    "《教父》",
                    "《阿凡达》",
                    "《泰塔尼克号》",
                    "《让子弹飞》",
                ],
            };
        },
    };
</script>

<style scoped>
    .container {
        display: flex;
        justify-content: space-around;
    }
</style>
