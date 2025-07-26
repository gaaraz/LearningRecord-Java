<template>
    <h2>当前求和为:{{ sum }}</h2>
    <button @click="sum++">点击+1</button>
    <hr />
    <h2>当前的信息为:{{ msg }}</h2>
    <button @click="msg += '!'">修改信息</button>
    <hr />
    <h2>姓名:{{ person.name }}</h2>
    <h2>年龄:{{ person.age }}</h2>
    <h2>薪资:{{ person.job.j1.salary }}</h2>
    <button @click="person.name += '#'">修改姓名</button>
    <button @click="person.age++">增长年龄</button>
    <button @click="person.job.j1.salary++">涨薪</button>
</template>

<script>
    import { ref, reactive, watch } from "vue";

    export default {
        name: "Demo",
        setup(props, context) {
            let sum = ref(0);
            let msg = ref("Hello");
            let person = reactive({
                name: "张三",
                age: 18,
                job: {
                    j1: {
                        salary: 20,
                    },
                },
            });
            // 情况一:监视ref所定义的一个响应式数据
            // watch(
            //     sum,
            //     (newVal, oldVal) => {
            //         console.log("sum改变了", newVal, oldVal);
            //     },
            //     { immediate: true }
            // );

            // 情况二:监视ref所定义的多个响应式数据
            // watch(
            //     [sum, msg],
            //     (newVal, oldVal) => {
            //         console.log("sum改变了", newVal, oldVal);
            //     },
            //     { immediate: true }
            // );

            // 情况三:监视reactive所定义的一个响应式数据的全部属性
            //      1.注意:此处无法正确获取oldValue(依然未修复)
            //      2.注意:深度监视配置好像修复了(deep配置false可以生效)
            // watch(
            //     person,
            //     (newVal, oldVal) => {
            //         console.log("person改变了", newVal, oldVal);
            //     },
            //     { deep: false }
            // );

            // 情况四:监视reactive所定义的一个响应式数据的某个属性
            // watch(
            //     () => person.name,
            //     (newVal, oldVal) => {
            //         console.log("person.name改变了", newVal, oldVal);
            //     }
            // );

            // 情况五:监视reactive所定义的一个响应式数据的某些属性
            // watch([() => person.name, () => person.age], (newVal, oldVal) => {
            //     console.log("person.name或person.age改变了", newVal, oldVal);
            // });

            // 特殊情况,
            // watch(
            //     () => person.job,
            //     (newVal, oldVal) => {
            //         console.log("person.job改变了", newVal, oldVal);
            //     },
            //     { deep: false } //deep配置问题貌似已经修复
            // );

            return {
                sum,
                msg,
                person,
            };
        },
    };
</script>
