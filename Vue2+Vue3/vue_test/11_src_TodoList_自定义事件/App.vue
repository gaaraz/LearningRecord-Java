<template>
    <div id="app">
        <div class="todo-container">
            <div class="todo-wrap">
                <MyHeader @addTodo="addTodo" />
                <MyList
                    :todos="todos"
                    :checkTodo="checkTodo"
                    :deleteTodo="deleteTodo"
                />
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
            addTodo(item) {
                this.todos.unshift(item);
            },
            // 删除一个todo
            deleteTodo(id) {
                this.todos = this.todos.filter((item) => item.id !== id);
            },
            checkAllTodo(done) {
                this.todos.forEach((item) => {
                    item.done = done;
                });
            },
            clearAllTodo() {
                this.todos = this.todos.filter((item) => {
                    return !item.done;
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
