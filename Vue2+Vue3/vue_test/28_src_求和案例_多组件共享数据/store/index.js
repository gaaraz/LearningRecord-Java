import Vue from "vue";
// 引入Vuex
import Vuex from "vuex";
// 应用Vuex插件
Vue.use(Vuex);

// 准备actions--用于相应组件中的动作
const actions = {
    // jia(context, value) {
    //     console.log("action中的jia被调用");
    //     context.commit("JIA", value);
    // },
    // jian(context, value) {
    //     console.log("action中的jian被调用");
    //     context.commit("JIAN", value);
    // },
    jiaOdd(context, value) {
        console.log("action中的jiaOdd被调用");
        if (context.state.sum % 2) {
            context.commit("JIA", value);
        }
    },
    jiaWait(context, value) {
        console.log("action中的jiaWait被调用");
        setTimeout(() => {
            context.commit("JIA", value);
        }, 500);
    },
};

// 准备mutations--用于操作数据(state)
const mutations = {
    JIA(state, value) {
        console.log("mutations中的JIA被调用");
        state.sum += value;
    },
    JIAN(state, value) {
        console.log("mutations中的JIAN被调用");
        state.sum -= value;
    },
    ADD_PERSON(state, value) {
        console.log("mutations中的ADD_PERSON被调用");
        state.personList.unshift(value);
    },
};

// 准备state--用于存储数据
const state = {
    sum: 0,
    school: "蓝翔",
    subject: "挖掘机",
    personList: [{ id: "001", name: "张三" }],
};

// 准备getters--用于将state中的数据进行加工
const getters = {
    bigSum(state) {
        return state.sum * 10;
    },
};

export default new Vuex.Store({
    actions,
    mutations,
    state,
    getters,
});
