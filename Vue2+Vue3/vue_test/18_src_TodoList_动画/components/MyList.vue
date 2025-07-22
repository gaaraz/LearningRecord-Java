<template>
    <ul class="todo-main">
        <transition-group name="item" appear>
            <MyItem v-for="item in todos" :key="item.id" :item="item" />
        </transition-group>
    </ul>
</template>

<script>
    import MyItem from "./MyItem.vue";
    export default {
        name: "MyList",
        components: {
            MyItem,
        },
        props: ["todos"],
    };
</script>

<style>
    /*main*/
    .todo-main {
        margin-left: 0px;
        border: 1px solid #ddd;
        border-radius: 2px;
        padding: 0px;
    }

    .todo-empty {
        height: 40px;
        line-height: 40px;
        border: 1px solid #ddd;
        border-radius: 2px;
        padding-left: 5px;
        margin-top: 10px;
    }

    .item-enter-active {
        animation: example 0.5s linear;
    }

    .item-leave-active {
        animation: example 0.5s linear reverse;
    }

    @keyframes example {
        from {
            transform: translateX(100%);
        }
        to {
            transform: translateX(0px);
        }
    }
</style>
