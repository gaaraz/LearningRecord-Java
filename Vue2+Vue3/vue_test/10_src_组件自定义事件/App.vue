<template>
    <div id="app" class="demo">
        <h1>{{ msg }},学生姓名是:{{ studentName }}</h1>
        <!-- 通过父组件给子组件传递函数类型的props实现:子给父传递数据 -->
        <School :getSchoolName="getSchoolName" />
        <!-- 通过父组件给子组件绑定一个自定义事件实现:子给父传递数据(第一种写法,使用@或v-on) -->
        <Student @example="getStudentName" @demo="m1" />
        <!-- 通过父组件给子组件绑定一个自定义事件实现:子给父传递数据(第二种写法,使用ref) -->
        <!-- <Student ref="student"  /> -->
    </div>
</template>

<script>
    import Student from "./components/Student.vue";
    import School from "./components/School.vue";

    export default {
        name: "App",
        components: {
            Student,
            School,
        },
        data() {
            return {
                msg: "hello!",
                studentName: "",
            };
        },
        methods: {
            getSchoolName(name) {
                console.log("App收到了学校名:", name);
            },
            getStudentName(name, ...params) {
                console.log("App收到了学生名:", name, params);
                this.studentName = name;
            },
            m1() {
                console.log("m1事件被触发了!");
            },
            show() {
                alert(123);
            },
        },
        mounted() {
            this.$refs.student.$on("example", this.getStudentName);
        },
    };
</script>

<style scoped>
    .demo {
        background-color: gray;
        padding: 5px;
    }
</style>
