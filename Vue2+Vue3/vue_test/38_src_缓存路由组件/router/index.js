import VueRouter from "vue-router";
import About from "../pages/About";
import Home from "../pages/Home";
import News from "../pages/News";
import Message from "../pages/Message";
import Detail from "../pages/Detail";

export default new VueRouter({
    routes: [
        {
            name: "about",
            path: "/about",
            component: About,
        },
        {
            path: "/home",
            component: Home,
            children: [
                {
                    path: "news",
                    component: News,
                },
                {
                    path: "message",
                    component: Message,
                    children: [
                        {
                            name: "detail",
                            // path: "detail/:id/:title",   //params必要写法
                            path: "detail",
                            component: Detail,

                            // props的第一种写法,值为对象,该对象中的所有key-value都会以props的形式传给Detail组件
                            // props: { a: 1, b: "hello" },

                            // props的第二种写法,值为布尔值,若布尔值为真,就会把该路由组价收到的所有params参数,以props的形式传给Detail组件
                            // props: true,

                            // props的第三种写法,值为函数
                            props($route) {
                                return {
                                    id: $route.query.id,
                                    title: $route.query.title,
                                    a: 1,
                                    b: "hello",
                                };
                            },
                        },
                    ],
                },
            ],
        },
    ],
});
