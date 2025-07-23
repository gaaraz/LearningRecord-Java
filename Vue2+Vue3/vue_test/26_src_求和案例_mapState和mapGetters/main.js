// 引入Vue
import Vue from "vue";
// 引入App组件，它是所有组件的父组件
import App from "./App.vue";
// 引入store
import store from "./store";
// 关闭vue的生产提示
Vue.config.productionTip = false;

// 创建Vue实例对象vm
new Vue({
    el: "#app",
    render: (h) => h(App),
    store,
    beforeCreate() {
        Vue.prototype.$bus = this;
    },
});
