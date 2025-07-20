<template>
    <div class="demo">
        <h2>学校名称：{{name | mySlice}}</h2>
        <h2>学校地址：{{address}}</h2>
        <button @click="test">点击测试一个hello方法</button>
    </div>
</template>

<script>
export default {
    name: 'School',
    data() {
        return {
            name: '上海大学1234',
            address: '上海'
        }
    },
    methods: {
        test(){
            this.hello();
        }
    },
}
</script>