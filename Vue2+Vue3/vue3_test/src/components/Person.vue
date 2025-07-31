<template>
    <div>
        <h1>人员列表</h1>
        <h3 style="color: red">Count组件求和为：{{ countStore.sum }}</h3>
        <!-- <input type="text" placeholder="请输入名字" v-model="name" />
        <button @click="add">添加</button> -->
        <button @click="addPerson">添加一个人，名字随机</button>
        <ul>
            <li v-for="p in personList" :key="p.id">
                {{ p.name }}
            </li>
        </ul>
    </div>
</template>

<script setup name="Person">
    import { usePersonStore } from "@/store/person";
    import { useCountStore } from "@/store/count";
    import { storeToRefs } from "pinia";
    const personStore = usePersonStore();
    const countStore = useCountStore();

    const { personList } = storeToRefs(personStore);

    function addPerson() {
        personStore.addPerson();
    }
</script>

<style></style>
