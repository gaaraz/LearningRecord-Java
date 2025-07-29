<template>
    <h4>当前sum值为:{{ sum }}</h4>
    <button @click="sum++">点击sum++</button>
    <hr />
    <h2>姓名:{{ name }}</h2>
    <h2>年龄:{{ age }}</h2>
    <h2>薪资:{{ job.j1.salary }}</h2>
    <h3 v-show="person.car">汽车信息:{{ person.car }}</h3>
    <button @click="name += '#'">修改姓名</button>
    <button @click="age++">增长年龄</button>
    <button @click="job.j1.salary++">涨薪</button>

    <button @click="showRawPerson">输出最原始的person</button>
    <button @click="addCar">添加一台车</button>
    <button @click="person.car.name += '!'">换车名</button>
    <button @click="changePrice">换车价格</button>
</template>

<script>
    import { ref, reactive, toRefs, toRaw, markRaw } from "vue";

    export default {
        name: "Demo",
        setup(props, context) {
            let sum = ref(0);
            let person = reactive({
                name: "张三",
                age: 18,
                job: {
                    j1: {
                        salary: 20,
                    },
                },
            });

            function showRawPerson() {
                // 将一个由reactive生成的响应式对象转为普通对象
                const p = toRaw(person);
                p.age++;
                console.log(p);
            }

            function addCar() {
                let car = { name: "马之达", price: 20 };
                // 标记一个对象，使其永远不会再成为响应式对象
                person.car = markRaw(car);
                // person.car = car;
            }

            function changePrice() {
                person.car.price++;
                console.log(person.car.price);
            }

            return {
                person,
                ...toRefs(person),
                sum,
                showRawPerson,
                addCar,
                changePrice,
            };
        },
    };
</script>
