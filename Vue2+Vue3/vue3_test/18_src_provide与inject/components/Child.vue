<template>
    <div class="child">
        <h3>Child组价(子),</h3>
        <GrandSon />
    </div>
</template>

<script>
    // import { inject } from "vue";
    import GrandSon from "./GrandSon.vue";
    export default {
        name: "Child",
        components: { GrandSon },
        // setup() {
        //     let car = inject("car");
        //     return { car };
        // },
    };
</script>

<style>
    .child {
        background-color: skyblue;
        padding: 10px;
    }
</style>
