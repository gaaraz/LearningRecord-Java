<template>
    <div>
        <button @click="isShow = !isShow">显示/隐藏</button>
        <transition-group
            appear
            name="animate__animated animate__bounce"
            enter-active-class="animate__swing"
            leave-active-class="animate__backOutUp"
        >
            <h1 v-show="!isShow" key="1">Hello!!!</h1>
            <h1 v-show="isShow" key="2">Welcome!!!</h1>
        </transition-group>
    </div>
</template>

<script>
    import "animate.css";
    export default {
        name: "Test3",
        data() {
            return {
                isShow: true,
            };
        },
    };
</script>

<style scoped>
    h1 {
        background-color: orange;
    }
    /* 进入的起点,离开的终点 */
    .hello-enter,
    .hello-leave-to {
        transform: translateX(-100%);
    }
    .hello-enter-active,
    .hello-leave-active {
        transition: 0.5s linear;
    }
    /* 进入的终点,离开的起点 */
    .hello-enter-to,
    .hello-leave {
        transform: translateX(0);
    }
</style>
