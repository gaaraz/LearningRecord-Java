<template>
    <div>
        <h1>{{msg}}</h1>
        <h2>学生姓名：{{name}}</h2>
        <h2>学生性别：{{sex}}</h2>
        <h2>学生年龄：{{myAge+1}}</h2>
        <button @click="updateAge">修改年龄</button>
    </div>
</template>

<script>
    export default {
        name: 'Student',
        data() {
            return {
                msg:'Hello World',
                myAge:this.age
            }
        },
        methods: {
            updateAge(){
                this.myAge++;
            }
        },
        // 简单声明接受
        // props:['name','age','sex']

        // 接收的同时对数据进行类型限制
        // props:{
        //     name:String,
        //     sex:String,
        //     age:Number
        // }

        // 接收的同时对数据进行类型限制+默认值的指定+必要性的限制
        props:{
            name:{
                type:String,
                required:true
            },
            age:{
                type:Number,
                default:99
            },
            sex:{
                type:String,
                required:true
            }
        }
    }
</script>