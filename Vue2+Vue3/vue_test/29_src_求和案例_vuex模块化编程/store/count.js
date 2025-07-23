export default {
    namespaced: true,
    actions: {
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
    },
    mutations: {
        JIA(state, value) {
            console.log("mutations中的JIA被调用");
            state.sum += value;
        },
        JIAN(state, value) {
            console.log("mutations中的JIAN被调用");
            state.sum -= value;
        },
    },
    state: {
        sum: 0,
        school: "蓝翔",
        subject: "挖掘机",
    },
    getters: {
        bigSum(state) {
            return state.sum * 10;
        },
    },
};
