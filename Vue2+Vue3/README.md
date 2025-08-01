# Vue2

## 1. Vue核心

### 1.1 Vue简介

#### 1.1.1 介绍与描述

    1. 动态构建用户界面的渐进式JavaScript框架

    2. 作者:尤雨溪

#### 1.1.2 Vue的特点

    1. 遵循MVVM模式

    2. 编码简洁，体积小，运行效率高，适合移动/PC端开发

    3. 它本身只关注UI，也可以引入其它第三方库开发项目

#### 1.1.3 与其它JS框架的关联

    1. 借鉴Angular的模版和数据绑定技术

    2. 借鉴React的组件化和虚拟DOM技术

#### 1.1.4 Vue周边库

    1. vue-cli：vue脚手架

    2. vue-resource

    3. axios

    4. vue-router：路由

    5. vuex：状态管理

    6. element-ui：基于vue的UI组件库（PC端）

        .........

### 1.2 初始Vue

### 1.3 模板语法

#### 1.3.1 模板的理解

    html中包含了一些JS语法代码，语法分为两种，分为：

    1. 插值语法（双大括号表达式）

    2. 指令（以v-开头）

#### 1.3.2 指令语法

    1. 功能：解析标签属性、解析标签体内容、绑定事件

    2. 举例：v-bind:href = 'xxxx'，xxxx会作为js表达式被解析

    3. 说明：Vue中有很多指令，此处只是用v-bind举个例子

### 1.4 数据绑定

#### 1.4.1 单向数据绑定

    1. 语法：v-bind:href = "xxx"或简写为:href

    2. 特点：数据只能从data流向页面

#### 1.4.2 双向数据绑定

    1. 语法：v-model:value="xxx"或简写为v-model = "xxx"

    2. 特点：数据不仅能从data流向页面，还能从页面流向data

### 1.5 MVVM模型

    1. M：模型（Model）：对应data中的数据

    2. V：视图（View）：模板

    3. VM：视图模型（ViewModel）：Vue示例对象

### 1.6 事件处理

#### 1.6.1 绑定监听

    1. v-on:xxx="fun"

    2. @xxx="fun"

    3. @xxx="fun(参数)"

    4. 默认事件形参：event

    5. 隐含属性对象：$event

#### 1.6.2 事件修饰符

    1. .prevent：阻止事件的默认行为event.preventDefault()

    2. .stop：停止事件冒泡event.stopPropagation()

#### 1.6.3 按键修饰符

    1. keycode：操作的是某个keycode值的键

    2. .keyName：操作的某个按键名的键（少部分）

### 1.7 计算属性与监视

#### 1.7.1 计算属性-computed

    1. 要显示的数据不存在，要通过计算得来。

    2. 在computed对象中定义计算属性。

    3. 在页面中使用{{方法名}}来显示计算的结果。

#### 1.7.2 监视属性-watch

    1. 通过vm对象的$watch()或watch配置来监视指定的属性

    2. 当属性变化时，回调函数自动调用，在函数内部进行计算

#### 1.7.3 监视属性-watch

    1. 通过vm对象的$watch()或watch配置来监视指定的属性

    2. 当属性变化时,回调函数自动调用,在函数内部进行计算

### 1.8 class与style绑定

#### 1.8.1 理解

    1. 在应用界面中，某个（些）元素的样式是变化的

    2. class/style绑定就是专门用来实现动态样式效果的技术

#### 1.8.2 class绑定

    1. :class='xxx'

    2. 表达式是字符串：'classA'

    3. 表达式是对象：{classA:isA, classB:isB}

    4. 表达式是数组：['classA','classB']

#### 1.8.3 style绑定

    1.  :style="{color: activeColor, fontSize: fontSize + 'px'}"

    2.  其中activeColor/fontSize是data属性

### 1.9 条件渲染

#### 1.9.1 条件渲染指令

    1. v-if与v-else

    2. v-show

#### 1.9.2 比较v-if与v-show

    1. 如果需要频繁切换v-show较好

    2. 当条件不成立时，v-if的所有子节点不会解析（项目中使用）

### 1.10 列表渲染

#### 1.10.1 列表显示指令

    遍历数组：v-for / index

    遍历对象：v-for / key

### 1.11 收集表单数据

    

### 1.12 过滤器

#### 1.12.1 理解过滤器

    1. 功能：对要显示的数据进行特定格式化后再显示

    2. 注意：并没有改变原本的数据，是产生新的对应的数据

### 1.13 内置指令与自定义指令

#### 1.13.1 常用内置指令 

    1. v-text：更新元素的textContent

    2. v-html：更新元素的innerHTML

    3. v-if：如果为true，当前标签才会输出到页面

    4. v-else：如果为false，当前标签才会输出到页面

    5. v-show：通过控制display样式来控制显示/隐藏

    6. v-for：遍历数组/对象

    7. v-on：绑定事件监听，一般简写为@

    8. v-bind：绑定解析表达式，可以省略v-bind

    9. v-model：双向数据绑定

    10. v-cloak：防止闪现，与css配合：[v-cloak]{display: none}

#### 1.13.2 自定义指令

```JavaScript
Vue.directive('my-directive',function(el,binding){
    el.innerHTML = binding.value.toupperCase()
})
```


```JavaScript
directives:{
    'my-directive':{
    bind(el,binding){
        el.innerHTML = binding.value.toupperCase()
    }
    }
}
```
 

```JavaScript
v-my-directive='xxx'
```

### 1.14 Vue实例生命周期 

#### 1.14.1 生命周期流程图

![image.png](https://flowus.cn/preview/ff8ad0d5-b388-434b-8ca3-eb5e0e38c971)

#### 1.14.2 vue生命周期分析

    1. 初始化显示

        - beforeCreate()

        - created()

        - beforeMount()

        - mounted()

    1. 更新状态：this.xxx = value

        - beforeUpdate()

        - updated()

    1. 销毁vue实例：vm.$destory()

        - beforeDestory()

        - destoryed()

#### 1.14.3 常用的声明周期方法

    1. mounted()：发送ajax请求，启动定时器等异步任务

    2. beforeDestory()：做收尾工作，如：清除定时器

## 2. Vue组件化编程

### 2.1 模块与组件、模块化与组件化

#### 2.1.1 模块

    1. 理解：向外提供特定功能的js程序，一般就是一个js文件

    2. 为什么：js文件很多很复杂

    3. 作用：复用js，简化js的编写，提高js运行效率

#### 2.1.2 组件

    1. 理解：用来实现局部（特定）功能效果的代码合集（html/css/js/image）

    2. 为什么：一个界面的功能很复杂

    3. 作用：复用编码，简化项目编码，提高运行效率

#### 2.1.3 模块化

    当应用中的js都可以模块来编写，那这个应用就是一个模块化的应用。

#### 2.1.4 组件化 

    当应用中的功能都是多组件的方式来编写的，那这个应用就是一个组件化的应用。

### 2.2 非单文件组件

    1. 模板编写没有提示

    2. 没有构建过程，无法将ES6转换成ES5

    3. 不支持组件的CSS

    4. 真正开发中几乎不用

### 2.3 单文件组件

#### 2.3.1 .vue文件的3个组成部分

1. 模板页面

```HTML
    <template>
    页面模板
    </template>
```

2. JS模块对象

```JavaScript
<script>
    export default {
        data(){return {}},
        methods:{},
        computed:{},
        compenents:{}
    }
</script>
```

3. 样式

```CSS
<style>
    样式定义
</style>
```

#### 2.3.2 基本使用

    1. 引入组件

    2. 映射成标签

    3. 使用组件标签

## 3. 使用Vue脚手架

### 3.1 初始化脚手架

#### 3.1.1 说明

    1. Vue脚手架是Vue官方提供的标准化开发工具(开发平台)

    2. 最新版本是5.x

    3. 目前好像已经进入维护模式，官方推荐使用 Vite

#### 3.1.2 具体步骤

第一步：安装@vue/cli

```Shell
npm install -g @vue/cli
```

第二步：切换到你要创建项目的目录，然后使用命令创建项目

```Shell
vue create xxxx
```

第三步：

```Shell
npm run serve
```

    

#### 3.1.3 模板项目的结构

    ├── node_modules

    ├── public

    │ ├── favicon.ico: 页签图标

    │ └── index.html: 主页面

    ├── src

    │ ├── assets: 存放静态资源

    │ │ └── logo.png

    │ │── component: 存放组件

    │ │ └── HelloWorld.vue

    │ │── App.vue: 汇总所有组件

    │ │── main.js: 入口文件

    ├── .gitignore: git 版本管制忽略的配置

    ├── babel.config.js: babel 的配置文件

    ├── package.json: 应用包配置文件

    ├── README.md: 应用描述文件

    ├── package-lock.json：包版本控制文件

#### 3.1.4 关于不同版本的Vue 

    1. vue.js与vue.runtime.xxx.js的区别

    2. vue.js是完整版的Vue，包含：核心功能+模板解析器。

    3. vue.runtime.xxx.js是运行版的Vue，只包含：核心功能；没有模板解析器。

    4. 因为vue.runtime.xxx.js没有模板解析器，所以不能使用template这个配置项，需要使用render函数接收到的createElement函数去指定具体内容。

#### 3.1.5 vue.config.js配置文件

    1. 使用vue inspect > output.js可以查看到Vue脚手架的默认配置。

    2. 使用vue.config.js可以对脚手架进行个性化定制，详情见：[https://cli.vuejs.org/zh](https://cli.vuejs.org/zh)

### 3.2 ref与props

#### ref属性

    1. 被用来给元素或子组件注册引用信息（id的替代者）

    2. 应用在html标签上获取的事真实DOM元素，应用在组件标签上是组件实例对象（vc）

    3. 使用方式：

         1. 打标识：`<h1 ref="xxx">......</h1> 或 <School ref="xxx"></School>`

         2. 获取： `this.$refs.xxx`

#### props配置项

1. 功能：让组件接收外部传过来的数据

2. 传递数据： `<Demo name="" />` 

3. 接收数据：

   1. 第一种方式（只接收）： `props:['name']` 

   2. 第二种方式（限制类型）： `props:{name:String}` 

   3. 第三种方式（限制类型、限制必要性、指定默认值）：

```JavaScript
props:{
    name:{
        type:String,    // 类型
        required:true,  // 必要性
        default:'张三'  // 默认值 
    }
}
```

    备注：props是只读的，Vue底层会监测你对props的修改，如果进行了修改，就会发出警告，若业务需求确实需要修改，那么请复制props的内容到data中一份，然后去修改data中的数据。

### 3.3 混入

    1. 功能：可以把多个组件共用的配置提取成一个混入对象

    2. 使用方式：

```JavaScript
{
    data(){......},
    methods:{......}
}
```

```JavaScript
Vue.mixin(xxx)    // 全局混入

mixins:['xxx']    // 局部混入
```

### 3.4 插件

1. 功能：用于增强Vue

2. 本质：包含install方法的一个对象，install的第一个参数是Vue，第二个以后得参数是插件使用者传递的数据。

3. 定义插件：

```JavaScript
对象.install = function(Vue, options){
    // 添加全局过滤器
    Vue.filter(....);
    // 添加全局指令
    Vue.directive(....);
    // 配置全局混入(合)
    Vue.mixin(....);
    
    // 添加实例方法
    Vue.prototype.$myMethod = function(){...}
    Vue.prototype.$myProperty = xxxx
}
```

1. 使用插件 `Vue.use()` 

### 3.5 scoped样式

    1. 作用：让样式在局部生效，防止冲突。

    2. 写法： `<style scoped>` 

### 3.6 webStorage

    1. 存储内容大小一般支持5M左右(不同浏览器可能不一样)

    2. 浏览器端通过Window.sessionStorage和Window.localStorage属性来实现本地存储机制

    3. 相关API：

        1. xxxxxxStorage.setItem('key', 'value'); 该方法接受一个键和值作为参数，会把键值对添加到存储中，如果键名存在，则更新其对应的值。

        2. xxxxxxStorage.getItem('key'); 该方法接受一个键名作为参数，返回键名对应的值。

        3. xxxxxxStorage.removeItem('key'); 该方法接受一个键名作为参数，并把该键名从存储中删除。

        4. xxxxxxStorage.clear(); 该方法会清空存储中的所有数据

    4. 备注

        1. SessionStorage存储的内容会随着浏览器窗口关闭而消失。

        2. LocalStorage存储的内容，需要手动清除才会消失。

        3. xxxxxxStorage.getItem('key'); 如果xxx对应的value获取不到，那么getItem的返回值是null。

        4. JSON.parse(null) 的结果依然是null。

### 3.7 Vue中的自定义事件

 1. 一种组件间通信的方式，适用于：子组件 ===> 父组件

 2. 使用场景：A是父组件，B是子组件，B想给A传数据，那么就要在A中给B绑定自定义事件（事件的回调在A中）。

 3. 绑定自定义事件：

     1. 第一种方式，在父组件中： `<Demo @example="test" />` 或 `<Demo v-on:example="test" />` 

     2. 第二种方式，在父组件中：

        ```JavaScript
        <Demo ref="demo" />
            ......
        mounted(){
            this.$refs.xxx.$on('example', this.test);
        }
        ```

    3. 若想让自定义事件只能触发一次，可以使用once修饰符，或$once方法。

 4. 触发自定义事件： `this.$emit('example', data)`

 5. 解绑自定义事件： `this.$off('example')` 

 6. 组件上也可以绑定原生DOM事件，需要使用native修饰符。

 7. 注意：通过this.$refs.xxx.$on('example', callback)绑定自定义事件时，回调要么配置在methods中，要么用箭头函数，否则this指向会出问题！

### 3.8 全局事件总线

 1. 一种组件间通信的方式，适用于任意组件间通信。

 2. 安装全局事件总线：  

    ```JavaScript
    new Vue({
        ......
        beforeCreate(){
        Vue.prototype.$bus = this;   //安装全局事件总线,$bus就是当前应用的vm
        }
        ......
    })
    ```

 3. 使用事件总线：

    1. 接收数据：A组件想接收数据，则在A组件中给$bus绑定自定义事件，事件的回调留在A组件自身。

        ```JavaScript
        methods(){
            demo(data){......}
        }
            ......
        mounted(){
            this.$bus.$on('', this.demo);
        }
        ```

    2. 提供数据：this.$bus.$emit('xxxx', data) 

    3. 最好在beforeDestroy钩子中，用$off去解绑当前组件所用到的事件。

### 3.9 消息订阅与发布

 1. 一种组件间通信的方式，适用于任意组件间通信。

 2. 使用步骤：

    1. 安装pubsub： `npm i pubsub-js` 

    2. 引入： `import pubsub from 'pubsub-js'` 

    3. 接收数据：A组件想接收数据，则在A组件中订阅消息，订阅的回调留在A组件自身。

    ```JavaScript
    methods(){
        demo(data){......}
    }
        ......
    mounted(){
        this.pid = pubsub.subscribe('xxx', this.demo);  //订阅消息
    }
     ```

    4. 提供数据： `pubsub.publish('xxx', 数据)` 

    5. 最好在beforeDestroy钩子中，用 `PubSub.unsubscribe(pid)` 去取消订阅。

### 3.10 nextTick

    1. 语法： `this.$nextTick(callback function)` 

    2. 作用：在下一次DOM更新结束后执行其指定的回调。

    3. 什么时候用：当改变数据后，要基于更新后的新DOM进行某些操作时，要在nextTick所指定的回调函数中执行。

### 3.11 过度与动画

 1. 作用：在插入、更新或移除DOM元素时，在合适的时候给元素添加样式类名。

 2. 写法：

     1. 准备好样式：

         - 元素进入的样式：

             1. v-enter：进入的起点

             2. v-enter-active：进入过程中

             3. v-enter-to：进入的终点

         - 元素离开的样式：

             1. v-leave：离开的起点

             2. v-leave-active：离开过程中

             3. v-leave-to：离开的终点

     2. 使用<transition>包裹要过度的元素，并配置name属性：

        ```JavaScript
        <transition name="demo">
            <h1 v-show="isShow"> hello! </h1>
        </transition>
        ```

       3. 备注：若有多个元素需要过度，则需要使用：<transition-group>，且每个元素都要指定key值。

## 4. Vue中的ajax

### 4.1 Vue脚手架配置代理

#### 方法一

在vue.config.js中添加如下配置:

```JavaScript
devServer:{
    proxy:"http://localhost:5000"
}
```

说明:

    1. 优点：配置简单，请求资源时直接发给前端即可。

    2. 缺点：不能配置多个代理，不能灵活的控制请求是否走代理。

    3. 工作方式：若按照上述配置代理，当请求了前端不存在的资源时，那么该请求会转发给服务器（优先匹配前端资源）

#### 方法二

编写vue.config.js配置具体代理规则:

```JavaScript
module.export = {
    devServer:{
    proxy:{
        '/api1': {// 匹配所有以'/api1'开头的请求路径
        target: 'http://localhost:5000',// 代理目标的基础路径
        changeOrigin: true,
        pathRewrite: {'^/api1': ''}
        },
        '/api2': {
        target: 'http://localhost:5001',
        changeOrigin: true,
        pathRewrite: {'^/api2': ''}
        }
    }
    }
}
/*
    changeOrigin设置为true时,服务器收到的请求头中的host为:localhost:5000
    changeOrigin设置为false时,服务器收到的请求头中的host为:localhost:8080
    changeOrigin默认为true
*/
```

    说明:

        1. 优点：可以配置多个代理，且可以灵活的控制请求是否走代理。

        2. 缺点：配置略微繁琐，请求资源时必须加前缀。

### 4.2 插槽

 1. 作用：让父组件可以向子组件指定位置插入html结构，也是一种组件间通信的方式，适用于 父组件===> 子组件

 2. 分类：默认插槽、具名插槽、作用域插槽

 3. 使用方式：

    1. 默认插槽  

     ```HTML
     父组件中:
         <Category>
         <div> html结构1 </div>
         </Category>
     子组件中:
         <template>
         <div>
             <!-- 定义插槽 -->
             <slot> 插槽默认内容... </slot>
         </div>
         </template>
     ```

    2. 具名插槽

     ```HTML
     父组件中:
         <Category>
         <template slot="center" >
             <div> html结构1 </div>
         </template>
         <template v-slot:footer >
             <div> html结构2 </div>
         </template>
         </Category>
     子组件中:
         <template>
         <div>
             <!-- 定义插槽 -->
             <slot name="center"> 插槽默认内容... </slot>
             <slot name="footer"> 插槽默认内容... </slot>
         </div>
         </template>
     ```

    3. 作用域插槽

     ```HTML
     父组件中:
         <Category>
         <template scope="scopeData" >
             <!-- 生成的是ul列表 -->
             <ul>
             <li v-for="g in scopeData.games" :key="g" >{{g}}</li>
             </ul>
         </template>
         </Category>
         <Category>
         <template slot-scope="scopeData" >
             <!-- 生成的是ul列表 -->
             <h4 v-for="g in scopeData.games" :key="g" >{{g}}</h4>
         </template>
         </Category>
     子组件中:
         <template>
         <div>
             <slot :games="games"></slot>
         </div>
         </template>
         <script>
         export default {
             name: 'Category',
             props: ['title'],
             data(){
             return{
                 games:['红色警戒', '穿越火线', '劲舞团', '超级玛丽']
             }
             }
         }
         </script>
     ```

## 5. vuex

### 5.1 概念

    在Vue中实现集中式状态（数据）管理的一个Vue插件，对vue应用中多个组件的共享状态进行集中式的管理（读/写），也是一种组件间通信的方式，且适用于任意组件间通信。

### 5.2 何时使用

    多个组件需要共享数据时

### 5.3 搭建vuex环境

1.创建文件：src/store/index.js

```JavaScript
import Vue from "vue";
// 引入Vuex
import Vuex from "vuex";
// 应用Vuex插件
Vue.use(Vuex);

// 准备actions--用于相应组件中的动作
const actions = {};

// 准备mutations--用于操作数据(state)
const mutations = {};

// 准备state--用于存储数据
const state = {};

export default new Vuex.Store({
    actions,
    mutations,
    state,
});

```

2.在main.js中创建vm时传入store配置项

```JavaScript
......
// 引入store
import store from "./store";
......

// 创建Vue实例对象vm
new Vue({
    el: "#app",
    render: (h) => h(App),
    store
});
```

### 5.4 基本使用

1.初始化数据、配置action、配置mutations，操作文件stores.js

```JavaScript
import Vue from "vue";
// 引入Vuex
import Vuex from "vuex";
// 应用Vuex插件
Vue.use(Vuex);

// 准备actions--用于相应组件中的动作
const actions = {
    jia(context, value) {
        console.log("action中的jia被调用");
        context.commit("JIA", value);
    },
};

// 准备mutations--用于操作数据(state)
const mutations = {
    JIA(state, value) {
        console.log("mutations中的JIA被调用");
        state.sum += value;
    },
};

// 准备state--用于存储数据
const state = {
    sum: 0,
};

export default new Vuex.Store({
    actions,
    mutations,
    state,
});

```

2.组件中读取vuex中的数据：$store.state.sum

3.组件中修改vuex中的数据：$store.dispatch('action中的方法名',数据)或$store.commit('mutations中的方法名', 数据)

    备注:若没有网络请求或其他业务逻辑,组件中也可以越过actions,即不写dispatch,直接写commit

### 5.5 getters的使用

1.概念：当state中的数据需要经过加工后再使用时，可以使用getters加工。

2.在store.js中追加getters配置

```JavaScript
......
const getters = {
    bigSum(state){
    return state.sum * 10;
    }
}

export default new Vuex.Store({
    ......
    getters
})
```

3.组件中读取数据：$store.getters.bigSum

### 5.6 四个map方法的使用

1.mapState方法：用于帮助我们映射state中的数据为计算属性

```JavaScript
computed:{
    //借助mapState生成计算属性,从state中读取数据.(对象写法)
    ...mapState({sum:'sum', school:'school', subject:'subject'}),
    
    // 借助mapState生成计算属性,从state中读取数据.(数组写法)
    ...mapState(["sum", "school", "subject"]),
}
```

2.mapGetters方法：用于帮助我们映射getters中的数据为计算属性

```JavaScript
computed:{
    // 借助mapGetters生成计算属性,bigSum(对象写法)
    ...mapGetters({ bigSum: "bigSum" }),
    // 借助mapGetters生成计算属性,bigSum(数组写法)
    ...mapGetters(["bigSum"]),
}
```

3.mapActions方法：用于帮助我们生成与actions对话的方法，即：包含$store.dispatch(xxx)的函数

```JavaScript
methods:{
    // 借助mapActions生成对应的方法,方法中会调用commit去联系mutations(对象写法)
    ...mapActions({ incrementOdd: "jiaOdd", incrementWait: "jiaWait" }),

    // 借助mapActions生成对应的方法,方法中会调用commit去联系mutations(数组写法)
    ...mapActions(["jiaOdd","jiaWait"]),
}
```

4.mapMutations方法：用于帮助我们生成与mutations对话的方法：包含$store.commit(xxx)的函数

```JavaScript
methods: {
    // 借助mapMutations生成对应的方法,方法中会调用commit去联系mutations(对象写法)
    ...mapMutations({ increment: "JIA", decrement: "JIAN" }),

    // 借助mapMutations生成对应的方法,方法中会调用commit去联系mutations(数组写法)
    ...mapMutations(["JIA", "JIAN"]),
}
```

备注：mapActions与mapMutations使用时，若需要传递参数需要：在模板中绑定事件时传递好参数，否则参数是事件对象。

### 5.7 模块化+命名空间

1.目的：让代码更好维护，让多种数据分类更加明确。

2.修改store.js

```JavaScript
    const countOptions = {
        namespaced: true,
        actions: {...},
        mutations: {...},
        state: { x: 0},
        getters: {
            bigSum(state) {
                return state.sum * 10;
            },
        },
};


const personOptions = {
        namespaced: true,
        actions: {...},
        mutations: {...},
        state: {...},
        getters: {...},
};

const store = new Vuex.Store({
    modules: {
        countOptions,
        personOptions,
    },
});
```

3.开启命名空间后，组件中读取state数据：

```JavaScript
// 方式一:自己直接去读
this.$store.state.personOptions.list
// 方式二:借助mapState读取
...mapState("countOptions", ["sum", "school", "subject"]),
```

4.开启命名空间后，组件中读取getters数据：

```JavaScript
// 方式一:自己直接去读
this.$store.getters['personOptions/firstPersonName']
// 方式二:借助mapGetters读取
...mapGetters("countOptions", ["bigSum"]),
```

5.开启命名空间后，组件中调用dispatch

```JavaScript
// 方式一:自己直接dispatch
this.$store.dispatch('personOptions/addPersonWang', person)
// 方式二:借助mapActions
...mapActions("countOptions", {
                incrementOdd: "jiaOdd",
                incrementWait: "jiaWait",
            }),
```

6.开启命名空间后，组件中调用commit

```JavaScript
// 方式一:自己直接commit
this.$store.commit('personOptions/ADD_PERSON', person)
// 方式二:借助mapMutations
...mapMutations('countOptions',{increment:"JIA", decrement:"JIAN" }),
```

## 6. vue-router

1. 理解：一个路由（route）就是一组映射关系（key-value），多个路由需要路由器（router）进行管理。

2. 前端路由：key是路径，value是组件

### 6.1 基本使用

1.安装vue-router，命令： `npm i vue-router` 

2.应用插件： `Vue.use(VueRouter)` 

3.编写router配置项:

```JavaScript
// 引入VueRouter
import VueRouter from 'vue-router'
// 引入路由组件
import About from '../components/About'
import Home from '../components/Home'

//创建router实例对象,去管理一组一组的路由规则
const router = new VueRouter({
    routes:[
        {
            path:'/about',
            component:About
        },
        {
            path:'/home',
            component:Home
        }
    ]
})

// 暴露router
export default router
```

4.实现切换(active-class可配置高亮样式)

```HAML
<router-link active-class="active" to="/about">About</router-link>
```

5.指定展示位置

```HAML
<router-view></router-view>
```

### 6.2 几个注意点

    1. 路由组件通常存放在pages文件夹，一般组件通常存放在components文件夹。

    2. 通过切换，“隐藏”了的路由组件，默认是被销毁掉的，需要的时候再去挂载。

    3. 每个组件都有自己的$route属性，里面存储着自己的路由信息。

    4. 整个应用只有一个router，可以通过组件的$router属性获取到。

### 6.3 多级路由

1.配置路由规则，使用children配置项：

```JavaScript
routes:[
    {
    path:'/about',
    component:About,
    },
    {
    path:'/home',
    component:Home,
    children:[    //通过children配置子级路由
        {
        path:'news',
        component:News,
        },
        {
        path:'message',
        component:Message,
        },
    ]
    }
]
```

2跳转（要写完整路径）：

```HAML
<router-link to="/home/news" >News</router-link>
```

### 6.4 路由的query参数

1.传递参数

```HAML
<!-- 跳转并携带query参数,to的字符串写法 -->
<router-link :to="/home/message/detail?id=123&title=hello">跳转</router-link>

<!-- 跳转并携带query参数,to的对象写法 -->
<router-link :to="{
        path:'/home/message/detail',
        query:{
            id:123,
            title:'hello'
        }
    }"
>跳转</router-link>
```

2.接收参数

```JavaScript
$route.query.id
$route.query.title
```

### 6.5 命名路由

 1. 作用：可以简化路由的跳转。

 2. 如何使用

     a.给路由命名：

     ```JavaScript
     {
         path:'demo',
         component:Demo,
         children:[
             {
                 path:'test',
                 component:Test,
                 children:[
                     {
                         name:'hello',  //给路由命名
                         path:'welcome',
                         component:Hello,
                     }
                 ]
             }
         ]
     }
     ```

     b.简化跳转：

     ```HAML
     <!-- 简化前 -->
     <router-link to="/demo/test/welcome" >跳转</router-link>
     
     <!-- 简化后-->
     <router-link :to="{name:'hello'}" >跳转</router-link>
     
     <!-- 简化写法配合传递参数-->
     <router-link 
             :to="{
                 name:'hello',
                 query:{
                 id:123,
                 title:'hello'
                 }
     }" >跳转</router-link>
     ```

### 6.6 路由的params参数

1.配置路由，声明接收params参数

```JavaScript
    {
    path:'/home',
    component:Home,
    children:[    //通过children配置子级路由
        {
        path:'news',
        component:News,
        },
        {
        path:'message',
        component:Message,
        children:[
            {
            name:'xiangqing',
            path:'detail/:id/:title',   //使用占位符声明接收params参数
            component:Detail
            }
        ]
        },
    ]
    }
```

2.传递参数

```HAML
<!-- 跳转并携带params参数,to的字符串写法 -->
<router-link to="/home/message/detail/666/hello" >跳转</router-link>

<!-- 跳转并携带params参数,to的对象写法 -->
<router-link 
    :to="{
        name:'xiangqing',
        params:{
            id:123,
            title:'hello'
        }
    }" 
>跳转</router-link>
```

特别注意：路由携带params参数时，若使用to的对象写法，则不能使用path配置项，必须使用name配置！

3.接收参数

```HAML
$route.params.id
$route.params.title
```

### 6.7 路由的props配置

作用：让路由组件更方便的收到参数

```Java
{
    name:'xiangqing',
    path:'detail/:id',
    component:Detail,
    
    //第一种写法:props值为对象,该对象中所有的key-value的组合最终都会通过props传给Detail组件
    //props:{a:123}
    
    //第二种写法:prop值为布尔值,布尔值为true,则把路由收到的所有params参数通过props传给Detail组件
    //props:true
    
    // 第三张写法:props值为函数,该函数返回的对象中每一组key-value都会通过props传给Detail组件
    props(route){
        return{
            id:route.query.id,
            title:route.query.title
        }
    }
}
```

### 6.8 <router-link>的replace属性

    1. 作用：控制路由跳转时操作浏览器历史记录的模式

    2. 浏览器的历史记录有两种写入方式：分别为push和replace，push是追加历史记录，replace是替换当前记录。路由跳转时默认为push。

    3. 如何开启replace模式：<router-link replace ......>News</router-link>

### 6.9 编程式路由导航

1.作用:不借助<router-link>实现路由跳转,让路由跳转更加灵活

2.具体编码

```JavaScript
this.$router.push({
    name:'xiangqing',
    params:{
    id:xxx,
    title:xxx
    }
})

this.$router.replace({
    name:'xiangqing',
    params:{
    id:xxx,
    title:xxx
    }
})
    
this.$router.forward()    //前进
this.$router.back()       //后退
this.$router.go()         //可前进和后退
```

### 6.10 缓存路由组件

1.作用:让不展示的路由组件保持挂载,不被销毁

2.具体编码:

```HTML
<keep-alive include="News">
    <router-view></router-view>
</keep-alive>
```

### 6.11 两个新的生命周期钩子

    1. 作用:路由组件所独有的两个钩子,用于捕获路由组件的激活状态

    2. 具体名字:

        3. activated路由组件被激活时触发

        4. deactivated路由组件失活时触发

### 6.12 路由守卫

1.作用：对路由进行权限控制

2.分类：全局守卫、独享守卫、组件内守卫

3.全局守卫：

```JavaScript
//全局前置守卫：初始化时执行、每次路由切换前执行
router.beforeEach((to,from,next)=>{
    console.log('beforeEach',to,from)
    if(to.meta.isAuth){ //判断当前路由是否需要进行权限控制
        if(localStorage.getItem('school') === 'atguigu'){ //权限控制的具体规则
            next() //放行
        }else{
            alert('暂无权限查看')
            // next({name:'guanyu'})
        }
    }else{
        next() //放行
    }
})

//全局后置守卫：初始化时执行、每次路由切换后执行
router.afterEach((to,from)=>{
    console.log('afterEach',to,from)
    if(to.meta.title){ 
        document.title = to.meta.title //修改网页的title
    }else{
        document.title = 'vue_test'
    }
})
```

4.独享守卫：

```JavaScript
beforeEnter(to,from,next){
    console.log('beforeEnter',to,from)
    if(to.meta.isAuth){ //判断当前路由是否需要进行权限控制
        if(localStorage.getItem('school') === 'atguigu'){
            next()
        }else{
            alert('暂无权限查看')
            // next({name:'guanyu'})
        }
    }else{
        next()
    }
}
```

5.组件内守卫：

```JavaScript
//进入守卫：通过路由规则，进入该组件时被调用
beforeRouteEnter (to, from, next) {
},
//离开守卫：通过路由规则，离开该组件时被调用
beforeRouteLeave (to, from, next) {
}
```

### 6.13 路由器的两种工作模式

    1. 对于一个url来说，什么是hash值？—— #及其后面的内容就是hash值。

    2. hash值不会包含在 HTTP 请求中，即：hash值不会带给服务器。

    3. hash模式：

        4. 地址中永远带着#号，不美观 。

        5. 若以后将地址通过第三方手机app分享，若app校验严格，则地址会被标记为不合法。

        6. 兼容性较好。

    7. history模式：

        8. 地址干净，美观 。

        9. 兼容性和hash模式相比略差。

        10. 应用部署上线时需要后端人员支持，解决刷新页面服务端404的问题。

## 7. Vue UI组件

### 7.1 移动端常用UI组件库

    1. Vant [https://vant-ui.github.io/vant](https://vant-ui.github.io/vant/)/

    2. Cube UI [https://didi.github.io/cube-ui](https://didi.github.io/cube-ui)

    3. Mint UI [http://mint-ui.github.io](http://mint-ui.github.io)

### 7.2 PC端常用UI组件库

    1. Element UI [https://element.eleme.cn](https://element.eleme.cn)

    2. IView UI  [https://www.iviewui.com](https://www.iviewui.com)

# Vue3

## 1. Vue3简介

### 1.1 Vue3带来了什么

#### 1.1.1 性能的提升

    - 打包大小减少41%

    - 初次渲染快55%,更新渲染快133%

    - 内存减少54%

#### 1.1.2 源码的升级

    - 使用Proxy代替defineProperty实现响应式

    - 重写虚拟DOM的实现和Tree-Shaking

#### 1.1.3 拥抱TypeScript

    - Vue3可以更好的支持TypeScript

#### 1.1.4 新的特性

    1. Composition API(组合API)

        1. setup配置

        2. ref与reactive

        3. watch与watchEffect

        4. provid与inject

    2. 新的内置组件

        1. Fragment

        2. Teleport

        3. Suspend

    3.  其他改变

        1.  新的生命周期钩子

        2.  data选项应始终被声明为一个函数

        3.  移除keyCode支持作为v-on的修饰符

## 2. 创建Vue3.0工程

### 2.1 使用vue-cli创建

官方文档：[https://cli.vuejs.org/zh/guide/creating-a-project.html#vue-create](https://cli.vuejs.org/zh/guide/creating-a-project.html#vue-create)

```Shell
## 查看@vue/cli版本,确保@vue/cli版本在4.5.0以上
vue --version
## 安装或者升级你的@vue/cli
npm install -g @vue/cli
## 创建
vue create vue_test
## 启动
cd vue_test
npm run serve
```

### 2.2 使用vite创建

官方文档：[https://v3.cn.vuejs.org/guide/installation.html#vite](https://v3.cn.vuejs.org/guide/installation.html#vite)

vite官网：[https://vitejs.cn](https://vitejs.cn)

```Shell
## 创建 需要输入后续指令选择安装插件
npm create vue@latest
## 启动
cd <your-project-name>
npm run dev
```

## 3. 常用Composition API

官方文档: [https://v3.cn.vuejs.org/guide/composition-api-introduction.html](https://v3.cn.vuejs.org/guide/composition-api-introduction.html)

### 3.1 setup

1. 理解：Vue3.0中一个新的配置项，值为一个函数。

2. setup是所有Composition API（组合API）的“舞台”。

3. 组件中所用到的：数据、方法等待，均要配置在setup中。

4. setup函数的两种返回值：

    1. 若返回一个对象，则对象中的属性、方法，在模板中均可以直接使用。（重点关注！）

    2. 若返回一个渲染函数：则可以自定义渲染内容。（了解）

5. 注意点：

    1. 尽量不要与Vue2.x配置混用

        1. Vue2.x配置（data、methods、computed...）中可以访问到setup中的属性、方法。

        2.  但在setup中不能访问到Vue2.x配置（data、methods、computed...）。

        3.  如果有重名，setup优先。

    2.  setup不能是一个async函数，因为返回值不再是return的对象，而是promise，模板看不到return对象中的属性。（后期也可以返回一个Promise实例，但需要Suspense和异步组件的配合）

### 3.2 ref函数

- 作用：定义一个响应式的数据

- 语法： `const xxx = ref(initValue)` 

    - 创建一个包含响应式数据的引用对象（reference对象，简称ref对象）。

    - JS中操作数据：xxx.value

    - 模板中读取数据：不需要.value，直接： `<div>{{xxx}}</div>` 

- 备注：

    - 接收的数据可以是：基本类型、也可以是对象类型。

    - 基本类型的数据：响应式依然是靠Object.defineProperty()的get与set完成的。

    - 对象类型的数据：内部使用了Vue3.0中的一个新函数---reactive函数。

### 3.3 reactive函数

  - 作用：定义一个对象类型的响应式数据（基本类型不要用它，要用ref函数）。

  - 语法： `const 代理对象= reactive(源对象)`接收一个对象（或数组），返回一个代理对象（Proxy的实例对象，简称proxy对象）。

  - reactive定义的响应式数据是“深层次的”。

  - 内部基于ES6的Proxy实现，通过代理对象操作源对象内部数据进行操作。

### 3.4 Vue3.0中的响应式原理

#### Vue2.x的响应式

  - 实现原理：

      - 对象类型：通过`Object.defineProperty()`对属性的读取、修改进行拦截（数据劫持）。

      - 数组类型：通过重写更新数组的一系列方法来实现拦截。（对数组的变更方法进行了包裹）。

      ```JavaScript
      Object.defineProperty(data, 'count', {
          get(){},
          set(){}
      })
      ```

  - 存在问题：

      - 新增属性、删除属性，界面不会更新。

      - 直接通过下标修改数组，界面不会自动更新。

#### Vue3.0的响应式

  - 实现原理：

      - 通过Proxy（代理）：拦截对象中任意属性的变化，包括：属性的读写、属性的添加、属性的删除等。

      - 通过Reflect（反射）：对源对象的属性进行操作。

      - MDN文档中描述的Proxy与Reflect：

          - Proxy：[https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Proxy](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Proxy)

          - Reflect：[https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Reflect](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Reflect)

          ```JavaScript
          new Proxy(data, {
              // 拦截读取属性值
              get(target, prop){
              return Reflect.get(target, prop);
              },
              set(target, prop, value){
              return Reflect.set(target, prop, value);
              },
              deleteProperty(target, prop){
              return Reflect.deleteProperty(target, prop);
              }
          });
          ```

### 3.5 reactive对比ref

  - 从定义数据角度对比：

      - ref用来定义：基本类型数据。

      - reactive用来定义：对象（或数组）类型数据。

      - 备注：ref也可以用来定义对象（或数组）类型数据，它内部会自动通过reactive转为代理对象。

  - 从原理角度对比：

      - ref通过Object.defineProperty()的get与set来实现响应式（数据劫持）。

      - reactive通过使用Proxy来实现响应式（数据劫持），并通过Reflect操作源对象内部的数据。

  - 从使用角度对比：

      - ref定义的数据：操作数据需要.value，读取数据时模板中直接读取不需要.value。

      - reactive定义的数据：操作数据与读取数据：均不需要.value。

### 3.6 setup的两个注意点

  - setup执行的时机

      - 在beforeCreate之前执行一次，this是undefined。

  - setup的参数

      - props：值为对象，包含：组件外部传递过来，且组件内部声明接收了的属性。

      - context：上下文对象

          - attrs：值为对象，包含：组件外部传递过来，但没有在props配置中声明的属性，相当于this.$attrs。

          - slots：收到的插槽内容，相当于this.$slots。

          - emit：分发自定义事件的函数，相当于this.$emit。

### 3.7 计算属性与监视

#### 3.7.1 computed函数

  - 与Vue2.x中computed配置功能一致

  - 写法

  ```JavaScript
  import {computed} from 'vue'
  
  setup(){
      ...
      //计算属性——简写
      let fullName = computed(()=>{
          return person.firstName + '-' + person.lastName
      })
      //计算属性——完整
      let fullName = computed({
          get(){
              return person.firstName + '-' + person.lastName
          },
          set(value){
              const nameArr = value.split('-')
              person.firstName = nameArr[0]
              person.lastName = nameArr[1]
          }
      })
  }
  ```

#### 3.7.2 watch函数

- 与Vue2.x中watch配置功能一致

- 两个问题:

    - 监视reactive定义的响应式数据时：oldValue无法正确获取

    - 监视reactive定义的响应式数据中某个属性时：deep配置有效。

```JavaScript
// 情况一:监视ref所定义的一个响应式数据
watch(
    sum,
    (newVal, oldVal) => {
        console.log("sum改变了", newVal, oldVal);
    },
    { immediate: true }
);

// 情况二:监视ref所定义的多个响应式数据
watch(
    [sum, msg],
    (newVal, oldVal) => {
        console.log("sum改变了", newVal, oldVal);
    },
    { immediate: true }
);

// 情况三:监视reactive所定义的一个响应式数据的全部属性
//      1.注意:此处无法正确获取oldValue(依然未修复)
//      2.注意:深度监视配置好像修复了(deep配置false可以生效)
watch(
    person,
    (newVal, oldVal) => {
        console.log("person改变了", newVal, oldVal);
    },
    { deep: false }
);

// 情况四:监视reactive所定义的一个响应式数据的某个属性
watch(
    () => person.name,
    (newVal, oldVal) => {
        console.log("person.name改变了", newVal, oldVal);
    }
);

// 情况五:监视reactive所定义的一个响应式数据的某些属性
watch([() => person.name, () => person.age], (newVal, oldVal) => {
    console.log("person.name或person.age改变了", newVal, oldVal);
});

// 特殊情况,
watch(
    () => person.job,
    (newVal, oldVal) => {
        console.log("person.job改变了", newVal, oldVal);
    },
    { deep: false } //deep配置问题貌似已经修复
);
```

#### 3.7.3 watchEffect函数

- watch的套路是：既要指明监视的属性，也要指明监视的回调。

- watchEffect的套路是：不用指明监视哪个属性，监视的回调中用到哪个属性，那就监视哪个属性。

- watchEffect有点像computed：

    - 但computed注重的计算出来的值（回调函数的返回值），所以必须要写返回值。

    - watchEffect更注重的是过程（回调函数的函数体），所以不用写返回值。

```JavaScript
//watchEffect所指定的回调中用到的数据只要发生变化，则直接重新执行回调。
watchEffect(()=>{
    const x1 = sum.value
    const x2 = person.age
    console.log('watchEffect配置的回调执行了')
})
```

### 3.8 生命周期

![image.png](https://flowus.cn/preview/b0348351-625d-43ba-850b-ff28414bbd4b)

  - Vue3.0中可以继续使用Vue2.x中的生命周期钩子，但有两个被更名：

      - beforeDestroy改名为beforeUnmount

      - destroyed改名unmounted

  - Vue3.0也提供了Composition API形式的生命周期钩子，与Vue2.x中钩子对应关系如下：

      - beforeCreate ===> setup()

      - created===> setup()

      - beforeMount===> onBeforeMount

      - mounted===> onMounted

      - beforeUpdate===> onBeforeUpdate

      - updated===> onUpdated

      - beforeUnmount===> onBeforeUnmount

      - unmounted===> onUnmounted

### 3.9 自定义hook函数

  - 什么是hook?----本质是一个函数，把setup函数中使用的Composition API进行了封装。

  - 类似于vue2.x中的mixin。

  - 自定义hook的优势，复用代码，让setup中的逻辑更清楚易懂。

### 3.10 toRef

  - 作用：创建一个ref对象，其value值指向另一个对象中的某个属性。

  - 语法： `const name = toRef(person, 'name');` 

  - 应用：要将响应式对象中的某个属性单独提供给外部使用时。

  - 扩展：toRefs与toRef功能一致，但可以批量创建多个ref对象，语法toRefs(person)

## 4. 其它Composition API

### 4.1 shallowReactive与shallowRef

  - shallowReactive：只处理对象最外层属性的响应式（浅响应式）。

  - shallowRef：只处理基本数据类型的响应式，不进行对象的响应式处理。

  - 什么时候使用：

      - 如果有一个对象数据，结构比较深，但变化时只是外层属性变化===>shallowReactive。

      - 如果有一个对象数据，后续功能不会修改该对象中的属性，而是产生新的对象来替换===>shallowRef

### 4.2 readonly与shallowReadonly

  - readonly：让一个响应式数据变为只读的（深只读）。

  - shallowReadonly：让一个响应式数据变为只读的（浅只读）。

  - 应用场景：不希望数据被修改时。

### 4.3 toRaw与markRaw

  - toRaw：

      - 作用：将一个由reactive生成的响应式对象转为普通对象。

      - 使用场景：用于读取响应式对象对应的普通对象，对这个普通对象的所有操作，不会引起页面更新。

  - markRaw

      - 作用：标记一个对象，使其永远不会再成为响应式对象。

      - 应用场景：

          - 有些值不应被设置为响应式的，例如复杂的第三方类库等。

          - 当渲染具有不可变数据源的大列表时，跳过响应式转换可以提高性能。

### 4.4 customRef

  - 作用：创建一个自定义的ref，并对其依赖项跟踪和更新触发进行显示控制。

  - 实现防抖效果：

  ```JavaScript
  <template>
      <input type="text" v-model="keyword">
      <h3>{{keyword}}</h3>
  </template>
  
  <script>
      import {ref,customRef} from 'vue'
      export default {
          name:'Demo',
          setup(){
              // let keyword = ref('hello') //使用Vue准备好的内置ref
              //自定义一个myRef
              function myRef(value,delay){
                  let timer
                  //通过customRef去实现自定义
                  return customRef((track,trigger)=>{
                      return{
                          get(){
                              track() //告诉Vue这个value值是需要被“追踪”的
                              return value
                          },
                          set(newValue){
                              clearTimeout(timer)
                              timer = setTimeout(()=>{
                                  value = newValue
                                  trigger() //告诉Vue去更新界面
                              },delay)
                          }
                      }
                  })
              }
              let keyword = myRef('hello',500) //使用程序员自定义的ref
              return {
                  keyword
              }
          }
      }
  </script>
  ```

### 4.5 provide与inject

  - 作用：实现祖与后代组件间通信

  - 套路：父组件有一个provide选项来提供数据，后代组件有一个inject选项来开始使用这些数据

  - 具体写法：

      - 祖组件中：

      ```JavaScript
      setup(){
          ......
          let car = reactive({name:'奔驰',price:'40万'})
          provide('car',car)
          ......
      }
      ```

      - 后代组件中：

      ```JavaScript
      setup(props,context){
          ......
          const car = inject('car')
          return {car}
          ......
      }
      ```

### 4.6 响应式数据的判断

  - isRef: 检查一个值是否为一个 ref 对象

  - isReactive: 检查一个对象是否是由 reactive 创建的响应式代理

  - isReadonly: 检查一个对象是否是由 readonly 创建的只读代理

  - isProxy: 检查一个对象是否是由 reactive 或者 readonly 方法创建的代理

## 5. Composition API的优势

- Options API存在的问题:新增或者修改一个需求，就需要分别在data，methods，computed里修改。

- Composition API的优势：可以更加优雅的组织代码，函数。让相关功能的代码更加有序地组织在一起。

## 6. 新的组件

### 6.1 Fragment

  - 在Vue2中：组件必须有一个根标签

  - 在Vue3中：组件可以没有跟标签，内部会将多个标签包含在一个Fragment虚拟元素中

  - 好处：减少标签层级，减小内存占用

### 6.2 Teleport

Teleport是一种能够将我们的组件html结构移动到指定位置的技术。

```HTML
<teleport to="移动位置">
    <div v-if="isShow" class="mask">
        <div class="dialog">
            <h3>我是一个弹窗</h3>
            <button @click="isShow = false">关闭弹窗</button>
        </div>
    </div>
</teleport>
```

### 6.3 Suspense

  - 等待异步组件时渲染一些额外内容，让应用有更好的用户体验

  - 使用步骤

      - 异步引入组件

      ```JavaScript
      import {defineAsyncComponent} from 'vue'
      const Child = defineAsyncComponent(()=>import('./components/Child.vue'))
      ```

      - 使用Suspense包裹组件，并配置好default与fallback

      ```HTML
      <template>
          <div class="app">
              <h3>我是App组件</h3>
              <Suspense>
                  <template v-slot:default>
                      <Child/>
                  </template>
                  <template v-slot:fallback>
                      <h3>加载中.....</h3>
                  </template>
              </Suspense>
          </div>
      </template>
      ```

## 7. 其他

### 7.1 全局API的转移

  - Vue 2.x有许多全局API配置。

      - 例如：注册全局组件、注册全局指令等

      ```JavaScript
      //注册全局组件
      Vue.component('MyButton', {
          data: () => ({
          count: 0
          }),
          template: '<button @click="count++">Clicked {{ count }} times.</button>'
      })
      
      //注册全局指令
      Vue.directive('focus', {
          inserted: el => el.focus()
      }
      ```

  - Vue3.0中对这些API做出了调整。

      - 将全局的API,即Vue.xxx调整到应用实例(app)上

      |2.x全局API(Vue)|3.x实例API(app)|
    |-|-|
    |Vue.config.xxxx|app.config.xxxx|
    |Vue.config.productionTip|移除|
    |Vue.component|app.component|
    |Vue.directive|app.directive|
    |Vue.mixin|app.mixin|
    |Vue.use|app.use|
    |Vue.prototype|app.config.globalProperties|

### 7.2 其它改变

- data选项应始终被声明为一个函数。

- 过度类名的更改:

    - Vue2.x写法

    ```Stylus
    .v-enter,
    .v-leave-to {
        opacity: 0;
    }
    .v-leave,
    .v-enter-to {
        opacity: 1;
    }
    ```

    - Vue3.x写法

    ```Stylus
    .v-enter-from,
    .v-leave-to {
        opacity: 0;
    }
    
    .v-leave-from,
    .v-enter-to {
        opacity: 1;
    }
    ```

- 移除keyCode作为v-on的修饰符，同时也不再支持config.keyCodes

- 移除v-on.native修饰符

    - 父组件中绑定事件

    ```HTML
    <my-component
        v-on:close="handleComponentEvent"
        v-on:click="handleNativeClickEvent"
    />
    ```

    - 子组件中声明自定义事件

    ```HTML
    <script>
        export default {
        emits: ['close']
        }
    </script>
    ```

- 移除过滤器（filter）

    过滤器虽然这看起来很方便，但它需要一个自定义语法，打破大括号内表达式是 “只是 JavaScript” 的假设，这不仅有学习成本，而且有实现成本！建议用方法调用或计算属性去替换过滤器。

