<template>
    <div>
        <h1>人员列表</h1>
        <h3 style="color: red">Count组价求和为:{{ sum }}</h3>
        <input type="text" placeholder="请输入名字" v-model="name" />
        <button @click="add">添加</button>
        <button @click="addWang">添加一个姓王的人</button>
        <button @click="addPersonServer">添加一个姓名随机的人</button>
        <ul>
            <li v-for="p in personList" :key="p.id">{{ p.name }}</li>
        </ul>
    </div>
</template>

<script>
    import { nanoid } from "nanoid";
    export default {
        name: "Person",
        data() {
            return {
                name: "",
            };
        },
        computed: {
            personList() {
                return this.$store.state.personOptions.personList;
            },
            sum() {
                return this.$store.state.countOptions.sum;
            },
        },
        methods: {
            add() {
                const p = { id: nanoid(), name: this.name };
                this.$store.commit("personOptions/ADD_PERSON", p);
                this.name = "";
            },
            addWang() {
                const p = { id: nanoid(), name: this.name };
                this.$store.dispatch("personOptions/addPersonWang", p);
                this.name = "";
            },
            addPersonServer() {
                this.$store.dispatch("personOptions/addPersonServer");
            },
        },
    };
</script>

<style></style>
