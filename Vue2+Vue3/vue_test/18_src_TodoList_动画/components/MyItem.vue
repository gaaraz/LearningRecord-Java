<template>
    <!-- <transition name="item" appear> -->
    <li>
        <label>
            <input
                type="checkbox"
                :checked="item.done"
                @change="handleCheck(item.id)"
            />
            <span v-show="!item.isEdit">{{ item.title }}</span>
            <input
                type="text"
                v-show="item.isEdit"
                :value="item.title"
                @blur="handleBlur(item, $event)"
                ref="inputTitle"
            />
        </label>

        <button class="btn btn-danger" @click="handleDelete(item.id)">
            删除
        </button>
        <button
            class="btn btn-edit"
            @click="handleEdit(item)"
            v-show="!item.isEdit"
        >
            编辑
        </button>
    </li>
    <!-- </transition> -->
</template>

<script>
    import pubsub from "pubsub-js";
    export default {
        name: "MyItem",
        props: ["item"],
        methods: {
            // 勾选or取消勾选
            handleDelete(id) {
                if (confirm("确定删除吗?")) {
                    // 通知App组件将对应的item对象的done值取反
                    // this.deleteTodo(id);
                    // this.$bus.$emit("deleteTodo", id);
                    pubsub.publish("deleteTodo", id);
                }
            },
            // 删除
            handleCheck(id) {
                // 通知App组件将对应的item对象删除
                // this.checkTodo(id);
                this.$bus.$emit("checkTodo", id);
            },
            // 编辑
            handleEdit(item) {
                if (item.hasOwnProperty("isEdit")) {
                    item.isEdit = true;
                } else {
                    this.$set(item, "isEdit", true);
                }
                this.$nextTick(function () {
                    this.$refs.inputTitle.focus();
                });
            },
            handleBlur(item, e) {
                item.isEdit = false;
                if (!e.target.value.trim()) {
                    return alert("输入不能为空!");
                }
                this.$bus.$emit("updateItem", item.id, e.target.value);
            },
        },
    };
</script>

<style scoped>
    /*item*/
    li {
        list-style: none;
        height: 36px;
        line-height: 36px;
        padding: 0 5px;
        border-bottom: 1px solid #ddd;
    }

    li label {
        float: left;
        cursor: pointer;
    }

    li label li input {
        vertical-align: middle;
        margin-right: 6px;
        position: relative;
        top: -1px;
    }

    li button {
        float: right;
        display: none;
        margin-top: 3px;
    }

    li:before {
        content: initial;
    }

    li:last-child {
        border-bottom: none;
    }

    li:hover {
        background-color: #ddd;
    }

    li:hover button {
        display: block;
    }

    .item-enter-active {
        animation: example 0.5s linear;
    }

    .item-leave-active {
        animation: example 0.5s linear reverse;
    }

    @keyframes example {
        from {
            transform: translateX(100%);
        }
        to {
            transform: translateX(0px);
        }
    }
</style>
