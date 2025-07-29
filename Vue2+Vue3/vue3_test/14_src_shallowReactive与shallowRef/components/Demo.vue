<template>
    <h4>当前x.y值为:{{ x.y }}</h4>
    <button @click="x = { y: 666 }">点击替换x</button>
    <button @click="x.y++">点击x.y++</button>
    <hr />
    <h2>姓名:{{ name }}</h2>
    <h2>年龄:{{ age }}</h2>
    <h2>薪资:{{ job.j1.salary }}</h2>
    <button @click="name += '#'">修改姓名</button>
    <button @click="age++">增长年龄</button>
    <button @click="job.j1.salary++">涨薪</button>
</template>

<script>
    import {
        ref,
        reactive,
        toRef,
        toRefs,
        shallowRef,
        shallowReactive,
    } from "vue";

    export default {
        name: "Demo",
        setup(props, context) {
            // let person = shallowReactive({
            let person = reactive({
                name: "张三",
                age: 18,
                job: {
                    j1: {
                        salary: 20,
                    },
                },
            });

            let x = shallowRef({ y: 0 });
            console.log("******", x);

            return {
                person,
                ...toRefs(person),
                x,
            };
        },
    };
</script>
