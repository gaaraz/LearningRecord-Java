<template>
    <h1>个人信息</h1>
    <h2>姓名:{{ person.name }}</h2>
    <h2>年龄:{{ person.age }}</h2>
    <h3>工作种类:{{ person.job.type }}</h3>
    <h3>工作薪水:{{ person.job.salary }}</h3>
    <h3>爱好:{{ person.hobby }}</h3>
    <h3>测试的数据C:{{ person.job.a.b.c }}</h3>
    <button @click="changeInfo">修改个人信息</button>
</template>

<script>
    import { reactive } from "vue";
    export default {
        name: "App",
        setup() {
            let person = reactive({
                name: "张三",
                age: 18,
                job: {
                    type: "前端工程师",
                    salary: "20K",
                    a: {
                        b: {
                            c: 123,
                        },
                    },
                },
                hobby: ["抽烟", "喝酒", "烫头"],
            });

            function changeInfo() {
                person.name = "李四";
                person.age = 28;
                person.job.type = "UI设计师";
                person.job.salary = "20k";
                person.job.a.b.c = 999;
                person.hobby[0] = "学习";
            }

            return {
                person,
                changeInfo,
            };
        },
    };
</script>

<style></style>
