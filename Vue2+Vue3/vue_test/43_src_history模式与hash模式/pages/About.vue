<template>
    <h2>About的内容</h2>
</template>

<script>
    export default {
        name: "About",
        // 通过路由规则,进入组件时被调用
        beforeRouteEnter(to, from, next) {
            console.log("About--beforeRouteEnter", to, from);
            if (to.meta.isAuth) {
                if (localStorage.getItem("token") === "123456") {
                    next();
                } else {
                    alert("需要权鉴");
                }
            } else {
                next();
            }
        },
        // 通过路由规则,离开组件时被调用
        beforeRouteLeave(to, from, next) {
            console.log("About--beforeRouteLeave", to, from);
            next();
        },
    };
</script>
