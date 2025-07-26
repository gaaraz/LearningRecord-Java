<template>
    <h1>个人信息</h1>
    <h2>姓名:{{ name }}</h2>
    <h2>年龄:{{ age }}</h2>
    <h3>工作种类:{{ job.type }}</h3>
    <h3>工作薪水:{{ job.salary }}</h3>
    <button @click="changeInfo">修改个人信息</button>
</template>

<script>
    import { ref } from "vue";
    export default {
        name: "App",
        setup() {
            let name = ref("张三");
            let age = ref(18);
            let job = ref({
                type: "前端工程师",
                salary: "30K",
            });

            function changeInfo() {
                console.log(job.value);
                name.value = "李四";
                age.value = 28;
                job.value.type = "UI设计师";
                job.value.salary = "20k";
                console.log(name, age);
            }

            return {
                name,
                age,
                job,
                changeInfo,
            };
        },
    };
</script>

<style></style>
