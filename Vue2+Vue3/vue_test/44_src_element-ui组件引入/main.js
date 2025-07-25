// 引入Vue
import Vue from "vue";
// 引入App组件，它是所有组件的父组件
import App from "./App.vue";
//引入ElementUI组件
// import ElementUI from "element-ui";
//引入ElementUI全部样式
// import "element-ui/lib/theme-chalk/index.css";

import { Button, Row, DatePicker } from "element-ui";

// 关闭vue的生产提示
Vue.config.productionTip = false;
// 应用插件
// Vue.use(ElementUI);
Vue.component(Button.name, Button);
Vue.component(Row.name, Row);
Vue.component(DatePicker.name, DatePicker);

// 创建Vue实例对象vm
new Vue({
    el: "#app",
    render: (h) => h(App),
});
