<template>
  <div id="app">
    <img alt="logo" src="./assets/logo.png">
    <SchoolComponent/>
    <StudentComponent/>
  </div>
</template>

<script>
import SchoolComponent from './components/School.vue'
import StudentComponent from './components/Student.vue'

export default {
  name: 'App',
  components: {
    SchoolComponent,
    StudentComponent
  }
}
</script>
