<template>
    <div>
        <h1>当前求和为：{{ sum }}</h1>
        <h3>当前求和放大10倍为：{{ bigSum }}</h3>
        <h3>我在{{ school }}，学习{{ subject }}</h3>
        <h3 style="color: red">
            Person组件的总人数是：{{ personStore.personList.length }}
        </h3>
        <select v-model.number="n">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
        </select>
        <button @click="increment">+</button>
        <button @click="decrement">-</button>
    </div>
</template>

<script setup name="Count">
    import { ref } from "vue";
    import { useCountStore } from "../store/count";
    import { usePersonStore } from "@/store/person";
    import { storeToRefs } from "pinia";

    const personStore = usePersonStore();
    const countStore = useCountStore();
    const { sum, school, subject, bigSum } = storeToRefs(countStore);
    let n = ref(1);

    function increment() {
        // 第一种修改方式
        // countStore.sum += n.value;

        // 第二种修改方式
        // countStore.$patch({
        //     school: "香梅小学",
        //     subject: "弹琴",
        // });

        // 第三种修改方式
        countStore.increment(n.value);
    }

    function decrement() {
        countStore.sum -= n.value;
    }
</script>

<style>
    button {
        margin-left: 5px;
    }
</style>
