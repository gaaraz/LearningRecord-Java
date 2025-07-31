import { defineStore } from "pinia";

export const useCountStore = defineStore("count", {
    actions: {
        increment(value) {
            this.sum += value;
        },
    },
    state() {
        return {
            sum: 0,
            school: "红溪幼儿园",
            subject: "绘画",
        };
    },
    getters: {
        bigSum(state) {
            return state.sum * 10;
        },
    },
});
