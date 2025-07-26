<template>
    <h1>个人信息</h1>
    <h2>姓名:{{ person.name }}</h2>
    <h2>年龄:{{ person.age }}</h2>
    <h2>msg:{{ msg }},school:{{ school }}</h2>
    <button @click="test">测试触发Demo组件的Hello事件</button>
    <slot name="qwe">default</slot>
    <slot name="asd">default</slot>
</template>

<script>
    import { reactive } from "vue";

    export default {
        name: "Demo",
        props: ["msg", "school"],
        emits: ["hello"],
        setup(props, context) {
            console.log("---setup---props", props);
            console.log("---setup---context", context);
            console.log("---setup---attrs", context.attrs);
            console.log("---setup---emit", context.emit);
            console.log("---setup---slots", context.slots);
            let person = reactive({
                name: "张三",
                age: 18,
            });

            function test() {
                context.emit("hello", 666);
            }

            return {
                person,
                test,
            };
        },
    };
</script>
