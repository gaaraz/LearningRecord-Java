<template>
    <div class="category">
        <h3>{{ title }}分类</h3>
        <!-- 定义一个插槽(等着组件的使用者进行填充) -->
        <slot :games="games" msg="hello">默认内容</slot>
    </div>
</template>

<script>
    export default {
        name: "Category",
        props: ["title"],
        data() {
            return {
                games: ["黑神话", "只狼", "黑魂", "仁王"],
            };
        },
    };
</script>

<style scoped>
    .category {
        background-color: skyblue;
        widows: 200px;
        height: 300px;
    }
    h3 {
        text-align: center;
        background-color: orange;
    }
    video {
        widows: 100%;
    }
    img {
        width: 100%;
    }
</style>
