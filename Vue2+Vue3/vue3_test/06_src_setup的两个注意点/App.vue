<template>
    <Demo @hello="showHelloMsg" msg="hello" school="上海大学">
        <template v-slot:qwe>
            <h3>QWE</h3>
        </template>
        <template v-slot:asd>
            <h3>ASD</h3>
        </template>
    </Demo>
</template>

<script>
    import Demo from "./components/Demo.vue";
    export default {
        name: "App",
        components: { Demo },
        setup() {
            function showHelloMsg(value) {
                alert(`触发hello事件,收到参数:${value}!`);
            }

            return {
                showHelloMsg,
            };
        },
    };
</script>

<style></style>
