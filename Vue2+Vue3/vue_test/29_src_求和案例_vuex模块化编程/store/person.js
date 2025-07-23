import axios from "axios";
import { nanoid } from "nanoid";
export default {
    namespaced: true,
    actions: {
        addPersonWang(context, value) {
            if (value.name.indexOf("王") === 0) {
                context.commit("ADD_PERSON", value);
            } else {
                alert("人员必须姓王!");
            }
        },
        addPersonServer(context) {
            axios.get("https://api.keguan.org.cn/api/yiyan/api.php").then(
                (response) => {
                    console.log(response.data);
                    context.commit("ADD_PERSON", {
                        id: nanoid(),
                        name: response.data.text,
                    });
                },
                (error) => {
                    alert(error.message);
                }
            );
        },
    },
    mutations: {
        ADD_PERSON(state, value) {
            console.log("mutations中的ADD_PERSON被调用");
            state.personList.unshift(value);
        },
    },
    state: {
        personList: [{ id: "001", name: "张三" }],
    },
    getters: {
        firstPersonName(state) {
            return state.personList[0].name;
        },
    },
};
