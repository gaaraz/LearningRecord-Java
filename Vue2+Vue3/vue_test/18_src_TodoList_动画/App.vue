<template>
    <div id="root">
        <div class="todo-container">
            <div class="todo-wrap">
                <MyHeader @addTodo="addTodo" />
                <MyList :todos="todos" />
                <MyFooter
                    :todos="todos"
                    @checkAllTodo="checkAllTodo"
                    @clearAllTodo="clearAllTodo"
                />
            </div>
        </div>
    </div>
</template>

<script>
    import pubsub from "pubsub-js";
    import MyHeader from "./components/MyHeader.vue";
    import MyList from "./components/MyList.vue";
    import MyFooter from "./components/MyFooter.vue";

    export default {
        name: "App",
        components: {
            MyHeader,
            MyList,
            MyFooter,
        },
        data() {
            return {
                todos: JSON.parse(localStorage.getItem("todos")) || [],
            };
        },
        methods: {
            // 勾选或取消勾选一个todo
            checkTodo(id) {
                this.todos.forEach((item) => {
                    if (item.id === id) item.done = !item.done;
                });
            },
            // 添加一个todo
            addTodo(item) {
                this.todos.unshift(item);
            },
            // 删除一个todo
            deleteTodo(_, id) {
                this.todos = this.todos.filter((item) => item.id !== id);
            },
            // 全选or取消全选
            checkAllTodo(done) {
                this.todos.forEach((item) => {
                    item.done = done;
                });
            },
            // 清除所有已经完成
            clearAllTodo() {
                this.todos = this.todos.filter((item) => {
                    return !item.done;
                });
            },
            updateItem(id, title) {
                this.todos.forEach((item) => {
                    if (item.id === id) item.title = title;
                });
            },
        },
        watch: {
            todos: {
                deep: true,
                handler(value) {
                    localStorage.setItem("todos", JSON.stringify(value));
                },
            },
        },
        mounted() {
            this.$bus.$on("checkTodo", this.checkTodo);
            this.$bus.$on("updateItem", this.updateItem);
            // this.$bus.$on("deleteTodo", this.deleteTodo);
            this.pid = pubsub.subscribe("deleteTodo", this.deleteTodo);
        },
        beforeDestroy() {
            this.$bus.$off("checkTodo");
            this.$bus.$off("updateItem");
            // this.$bus.$off("deleteTodo");
            pubsub.unsubscribe(this.pid);
        },
    };
</script>

<style>
    /*base*/
    body {
        background: #fff;
    }

    .btn {
        display: inline-block;
        padding: 4px 12px;
        margin-bottom: 0;
        font-size: 14px;
        line-height: 20px;
        text-align: center;
        vertical-align: middle;
        cursor: pointer;
        box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2),
            0 1px 2px rgba(0, 0, 0, 0.05);
        border-radius: 4px;
    }

    .btn-danger {
        color: #fff;
        background-color: #da4f49;
        border: 1px solid #bd362f;
    }

    .btn-edit {
        color: #fff;
        background-color: skyblue;
        border: 1px solid rgb(103, 159, 180);
        margin-right: 5px;
    }

    .btn-danger:hover {
        color: #fff;
        background-color: #bd362f;
    }

    .btn:focus {
        outline: none;
    }

    .todo-container {
        width: 600px;
        margin: 0 auto;
    }
    .todo-container .todo-wrap {
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
    }
</style>
