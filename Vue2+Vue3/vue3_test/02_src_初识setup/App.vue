<template>
    <h1>个人信息</h1>
    <h2>姓名:{{ name }}</h2>
    <h2>年龄:{{ age }}</h2>
    <h2>性别:{{ sex }}</h2>
    <h2>a的值:{{ a }}</h2>
    <button @click="sayHello">Vue3配置的---sayHello</button>
    <br />
    <br />
    <button @click="sayWelcome">Vue2配置的---sayWelcome</button>
    <br />
    <br />
    <button @click="test1">测试在Vue2的配置中去读取Vue3中数据、方法</button>
    <br />
    <br />
    <button @click="test2">
        测试在Vue3的setup配置中去读取Vue2中数据、方法
    </button>
</template>

<script>
    export default {
        name: "App",
        data() {
            return {
                sex: "男",
                a: 100,
            };
        },
        methods: {
            sayWelcome() {
                alert("Welcome");
            },
            test1() {
                console.log(this.sex);
                console.log(this.name);
                console.log(this.age);
                console.log(this.sayHello);
            },
        },
        setup() {
            let name = "张三";
            let age = 18;
            let a = 200;

            function sayHello() {
                alert(`我叫${name},我${age}岁`);
            }

            function test2() {
                console.log(name);
                console.log(age);
                console.log(sayHello);
                console.log(this.sex);
                console.log(this.sayWelcome);
            }

            return {
                name,
                age,
                sayHello,
                test2,
            };
        },
    };
</script>

<style></style>
