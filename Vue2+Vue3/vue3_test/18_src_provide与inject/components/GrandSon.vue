<template>
    <div class="grandSon">
        <h3>GrandSon组件(孙),{{ car.name }}--{{ car.price }}</h3>
    </div>
</template>

<script>
    import { inject } from "vue";

    export default {
        name: "GrandSon",
        setup() {
            let car = inject("car");
            return { car };
        },
    };
</script>

<style>
    .grandSon {
        background-color: orange;
        padding: 10px;
    }
</style>
