<template>
    <div class="demo">
        <h2 class="title">学生姓名：{{name}}</h2>
        <h2 class="example">学生性别：{{sex}}</h2>
    </div>
</template>

<script>
    export default {
        name: 'Student',
        data() {
            return {
                name:'张三',
                sex:'男'
            }
        }
    }
</script>
<style scoped>
    .demo{
        background-color: pink;
    }
    .example{
        font-size: 40px;
    }
</style>