<template>
  <div id="app">
    <img alt="logo" src="./assets/logo.png">
    <h1 v-text="msg" ref="title"></h1>
    <button ref="btn" @click="showDOM">点击输出上方的DOM元素</button>
    <School ref="sch"/>
  </div>
</template>

<script>
import School from './components/School.vue'

export default {
  name: 'App',
  components: {
    School
  },
  data() {
    return {
      msg:'welcome!'
    }
  },
  methods: {
    showDOM(){
      console.log(this.$refs.title);  // 真实DOM元素
      console.log(this.$refs.btn);    // 真实DOM元素
      console.log(this.$refs.sch);    // School组件的实例对象
    }
  },
}
</script>
