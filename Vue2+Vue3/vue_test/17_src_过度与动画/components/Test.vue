<template>
    <div>
        <button @click="isShow = !isShow">显示/隐藏</button>
        <transition name="hello" appear>
            <h1 v-show="isShow">Hello!!!</h1>
        </transition>
    </div>
</template>

<script>
    export default {
        name: "Test",
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

    .hello-enter-active {
        animation: example 0.5s linear;
    }

    .hello-leave-active {
        animation: example 0.5s linear reverse;
    }

    @keyframes example {
        from {
            transform: translateX(-100%);
        }
        to {
            transform: translateX(0px);
        }
    }
</style>
