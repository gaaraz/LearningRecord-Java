<template>
    <div class="demo">
        <h2>学校名称：{{name}}</h2>
        <h2>学校地址：{{address}}</h2>
        <button @click="showName">点击提示学校名</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            name: '上海大学',
            address: '上海'
        }
    },
    methods: {
        showName() {
            alert(this.name)
        }
    }
}
</script>

<style>
    .demo {
        border-block-color: orange;
    }
</style>