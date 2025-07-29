<template>
    <div class="grandSon">
        <h3>GrandSon组件</h3>
        <Dialog />
    </div>
</template>

<script>
    import Dialog from "./Dialog.vue";
    export default {
        name: "GrandSon",
        components: { Dialog },
    };
</script>

<style>
    .grandSon {
        background-color: orange;
        padding: 10px;
    }
</style>
