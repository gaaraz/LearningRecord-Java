// 引入的不再是Vue构造函数了,引入的是一个名为createApp的工厂函数
import { createApp } from "vue";
import App from "./App.vue";

// 创建应用实例对象--app(类似于之前Vue2中的vm,但app比vm更轻量)
const app = createApp(App);
// 挂载
app.mount("#app");

// Vue2引入Vue
// import Vue from 'vue'
//
// Vue2创建vm
// new Vue({
// 	el:'#app',
// 	render: h => h(App),
// })
