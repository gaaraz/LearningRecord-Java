import { defineStore } from "pinia";
import axios from "axios";
import { nanoid } from "nanoid";

export const usePersonStore = defineStore("person", {
    actions: {
        async addPerson() {
            let {
                data: { msg: name },
            } = await axios.get("http://101.35.2.25/api/zici/sjwm.php");
            let obj = { id: nanoid(), name };
            this.personList.unshift(obj);
        },
    },
    state() {
        return {
            personList: [],
        };
    },
});
