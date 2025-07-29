<template>
    <h4>当前sum值为:{{ sum }}</h4>
    <button @click="sum++">点击sum++</button>
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
        readonly,
        shallowReadonly,
    } from "vue";

    export default {
        name: "Demo",
        setup(props, context) {
            let sum = ref(0);
            let person = reactive({
                name: "张三",
                age: 18,
                job: {
                    j1: {
                        salary: 20,
                    },
                },
            });

            // person = readonly(person);
            person = shallowReadonly(person);

            // sum = readonly(sum);
            sum = shallowReadonly(sum);

            return {
                // person,
                ...toRefs(person),
                sum,
            };
        },
    };
</script>
