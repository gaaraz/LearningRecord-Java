// 改文件时整个项目的入口文件
// 引入Vue
import Vue from 'vue'
// 引入App组件，它是所有组件的父组件
import App from './App.vue'
// 关闭vue的生产提示
Vue.config.productionTip = false;

/*
  关于不同版本的Vue:
    1. Vue.js与vue.runtime.xxx.js的区别:
      a. vue.js是完整版的Vue,包含:核心功能+模板解析器
      b. vue.runtime.xxx.js是运行版的Vue,只包含:核心功能;没有模板解析器
    2. 因为vue.runtime.xxx.js没有模板解析器,所以不能使用template配置项,
        需要使用render函数接收到的createElement函数去指定内容。
 */

// 创建Vue实例对象vm
new Vue({
  el:'#app',
  // render函数完成了这个功能:将App组件放入容器中
  render: h => h(App),
  // render:q => q('h1', '你好!!!'),

  // render(createElement){
  //   return createElement('h1', '你好!');
  // }

  // 由于引入的是vue.runtime.xxx.js,下面写法无法进行模板解析
  // template:`<h1>你好!!</h1>`,
  // components:{App}
});
