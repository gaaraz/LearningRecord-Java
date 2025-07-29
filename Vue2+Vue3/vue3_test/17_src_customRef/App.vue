<template>
    <input type="text" v-model="keyWord" />
    <h3>{{ keyWord }}</h3>
</template>

<script>
    import { customRef } from "vue";
    export default {
        name: "App",
        setup() {
            function myRef(value, delay) {
                let timer;
                return customRef((track, trigger) => {
                    return {
                        get() {
                            console.log(
                                `有人从myRef这个容器中读取数据了，我把${value}给他了`
                            );
                            track(); //通知Vue追踪value的变化（提前和get商量一下，让他认为这个value是有用的）
                            return value;
                        },
                        set(newVal) {
                            console.log(
                                `有人把myRef这个容器中数据改为了：${newVal}`
                            );
                            clearTimeout(timer);
                            timer = setTimeout(() => {
                                value = newVal;
                                trigger(); //通知Vue去重新解析模板
                            }, delay);
                        },
                    };
                });
            }

            let keyWord = myRef("hello", 500);
            return { keyWord };
        },
    };
</script>

<style></style>
