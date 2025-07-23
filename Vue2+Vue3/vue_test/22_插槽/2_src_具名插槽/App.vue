<template>
    <div class="container">
        <Category title="美食">
            <img
                slot="center"
                src="https://s3.ax1x.com/2021/01/16/srJlq0.jpg"
                alt=""
            />
            <a slot="footer" href="http://www.baidu.com">更多美食</a>
        </Category>

        <Category title="游戏">
            <ul slot="center">
                <li v-for="(g, index) in games" :key="index">{{ g }}</li>
            </ul>
            <div class="foot" slot="footer">
                <a href="http://www.baidu.com">单机游戏</a>
                <a href="http://www.baidu.com">网络游戏</a>
            </div>
        </Category>

        <Category title="电影">
            <img
                slot="center"
                src="https://s3.ax1x.com/2021/01/16/srJlq0.jpg"
                alt=""
            />
            <template v-slot:footer>
                <div class="foot">
                    <a href="http://www.baidu.com">经典</a>
                    <a href="http://www.baidu.com">热门</a>
                    <a href="http://www.baidu.com">推荐</a>
                </div>
                <h4>welcome</h4>
            </template>
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
    .container,
    .foot {
        display: flex;
        justify-content: space-around;
    }
</style>
