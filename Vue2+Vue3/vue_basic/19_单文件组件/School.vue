<template>
    <div>
        <h2>学校名称：{{schoolName}}</h2>
        <h2>学校地址：{{address}}</h2>
        <button @click="showName">点击提示学校名</button>
    </div>
</template>

<script>
export default {
    data() {
        return {
            schoolName: '上海大学',
            address: '上海'
        }
    },
    methods: {
        showName() {
            alert(this.schoolName)
        }
    }
}
</script>

<style>
    .demo {
        border-block-color: red;
    }
</style>