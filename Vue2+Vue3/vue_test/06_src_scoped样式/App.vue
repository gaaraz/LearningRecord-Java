<template>
  <div id="app">
    <h1 class="title">Hello</h1>
    <Student/>
    <School/>
  </div>
</template>

<script>
import Student from './components/Student.vue'
import School from './components/School.vue'

export default {
  name: 'App',
  components: {
    Student,
    School
  }
}
</script>

<style scoped>
  .title{
    color: red;
  }
</style>