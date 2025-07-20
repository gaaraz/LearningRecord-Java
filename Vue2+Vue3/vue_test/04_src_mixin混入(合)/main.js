// 引入Vue
import Vue from 'vue'
// 引入App组件，它是所有组件的父组件
import App from './App.vue'
import {hunhe,hunhe2} from './mixin'
// 关闭vue的生产提示
Vue.config.productionTip = false;
Vue.mixin(hunhe)
Vue.mixin(hunhe2)


// 创建Vue实例对象vm
new Vue({
  el:'#app',
  render: h => h(App),
});
