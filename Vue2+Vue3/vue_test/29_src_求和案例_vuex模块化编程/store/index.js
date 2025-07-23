import Vue from "vue";
// 引入Vuex
import Vuex from "vuex";
import countOptions from "./count";
import personOptions from "./person";
// 应用Vuex插件
Vue.use(Vuex);

// const countOptions = {
//     namespaced: true,
//     actions: {
//         jiaOdd(context, value) {
//             console.log("action中的jiaOdd被调用");
//             if (context.state.sum % 2) {
//                 context.commit("JIA", value);
//             }
//         },
//         jiaWait(context, value) {
//             console.log("action中的jiaWait被调用");
//             setTimeout(() => {
//                 context.commit("JIA", value);
//             }, 500);
//         },
//     },
//     mutations: {
//         JIA(state, value) {
//             console.log("mutations中的JIA被调用");
//             state.sum += value;
//         },
//         JIAN(state, value) {
//             console.log("mutations中的JIAN被调用");
//             state.sum -= value;
//         },
//     },
//     state: {
//         sum: 0,
//         school: "蓝翔",
//         subject: "挖掘机",
//     },
//     getters: {
//         bigSum(state) {
//             return state.sum * 10;
//         },
//     },
// };

// import axios from "axios";
// import { nanoid } from "nanoid";
// const personOptions = {
//     namespaced: true,
//     actions: {
//         addPersonWang(context, value) {
//             if (value.name.indexOf("王") === 0) {
//                 context.commit("ADD_PERSON", value);
//             } else {
//                 alert("人员必须姓王!");
//             }
//         },
//         addPersonServer(context) {
//             axios.get("https://api.keguan.org.cn/api/yiyan/api.php").then(
//                 (response) => {
//                     context.commit("ADD_PERSON", {
//                         id: nanoid(),
//                         name: response.data.text,
//                     });
//                 },
//                 (error) => {
//                     alert(error.message);
//                 }
//             );
//         },
//     },
//     mutations: {
//         ADD_PERSON(state, value) {
//             console.log("mutations中的ADD_PERSON被调用");
//             state.personList.unshift(value);
//         },
//     },
//     state: {
//         personList: [{ id: "001", name: "张三" }],
//     },
//     getters: {
//         firstPersonName(state) {
//             return state.personList[0].name;
//         },
//     },
// };

export default new Vuex.Store({
    modules: {
        countOptions,
        personOptions,
    },
});
