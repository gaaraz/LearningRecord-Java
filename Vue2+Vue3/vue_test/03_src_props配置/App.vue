<template>
  <div id="app">
    <Student name="李四" sex="女"  />
  </div>
</template>

<script>
import Student from './components/Student.vue'

export default {
  name: 'App',
  components: {
    Student
  }
}
</script>
