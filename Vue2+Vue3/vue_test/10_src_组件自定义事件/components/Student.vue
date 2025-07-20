<template>
    <div class="demo">
        <h2 class="title">学生姓名：{{ name }}</h2>
        <h2 class="example">学生性别：{{ sex }}</h2>
        <h2>当前number为:{{ number }}</h2>
        <button @click="add">点击number++</button>
        <button @click="sendStudentName">把学生名给App</button>
        <button @click="unbind">解绑example事件</button>
        <button @click="death">销毁当前Student组件的实例(vc)</button>
    </div>
</template>

<script>
    export default {
        name: "Student",
        data() {
            return {
                name: "张三",
                sex: "男",
                number: 0,
            };
        },
        methods: {
            add() {
                console.log("add被调用");
                this.number++;
            },
            sendStudentName() {
                // 触发Student组件实例身上的example事件
                this.$emit("example", this.name, 1, 2, 3);
                this.$emit("demo");
                // this.$emit("click");
            },
            unbind() {
                this.$off("example"); // 解绑一个自定义事件
                // this.$off(["example", "demo"]);  // 解绑多个自定义事件
                // this.$off();                     // 解绑所有的自定义事件
            },
            death() {
                // 销毁当前Student组件的实例,销毁后所有Student实例的自定义事件全都不奏效
                this.$destroy();
            },
        },
    };
</script>
<style scoped>
    .demo {
        background-color: pink;
    }
    .example {
        font-size: 40px;
    }
</style>
