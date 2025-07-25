import VueRouter from "vue-router";
import About from "../pages/About";
import Home from "../pages/Home";
import News from "../pages/News";
import Message from "../pages/Message";
import Detail from "../pages/Detail";

const router = new VueRouter({
    routes: [
        {
            name: "about",
            path: "/about",
            component: About,
            meta: { title: "关于" },
        },
        {
            name: "home",
            path: "/home",
            component: Home,
            meta: { title: "主页" },
            children: [
                {
                    name: "news",
                    path: "news",
                    component: News,
                    meta: { title: "新闻", isAuth: true },
                },
                {
                    name: "message",
                    path: "message",
                    component: Message,
                    meta: { title: "消息", isAuth: true },
                    children: [
                        {
                            name: "detail",
                            // path: "detail/:id/:title",   //params必要写法
                            path: "detail",
                            component: Detail,
                            meta: { title: "详情", isAuth: true },

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

//全局前置路由守卫---初始化的时候被调、每次路由切换之前被调用
router.beforeEach((to, from, next) => {
    console.log("前置路由守卫", to, from);
    if (to.meta.isAuth) {
        if (localStorage.getItem("token") === "123456") {
            next();
        } else {
            alert("需要权鉴");
        }
    } else {
        next();
    }
});

//全局后置路由守卫---初始化的时候被调、每次路由切换之后被调用
router.afterEach((to, from) => {
    console.log("后置路由守卫", to, from);
    document.title = to.meta.title || "Vue系统";
});

export default router;
