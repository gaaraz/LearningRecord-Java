<template>
    <li>
        <label>
            <input
                type="checkbox"
                :checked="item.done"
                @change="handleCheck(item.id)"
            />
            <span>{{ item.title }}</span>
        </label>
        <button class="btn btn-danger" @click="handleDelete(item.id)">
            删除
        </button>
    </li>
</template>

<script>
    export default {
        name: "MyItem",
        props: ["item"],
        methods: {
            // 勾选or取消勾选
            handleDelete(id) {
                if (confirm("确定删除吗?")) {
                    // 通知App组件将对应的item对象的done值取反
                    // this.deleteTodo(id);
                    this.$bus.$emit("deleteTodo", id);
                }
            },
            // 删除
            handleCheck(id) {
                // 通知App组件将对应的item对象删除
                // this.checkTodo(id);
                this.$bus.$emit("checkTodo", id);
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
</style>
