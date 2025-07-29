<template>
    <div class="child">
        <h3>Child组件</h3>
        <GrandSon />
    </div>
</template>

<script>
    import GrandSon from "./GrandSon.vue";
    export default {
        name: "Child",
        components: { GrandSon },
    };
</script>

<style>
    .child {
        background-color: skyblue;
        padding: 10px;
    }
</style>
