<template>
    <div class="row">
        <!-- 展示用户列表 -->
        <div
            class="card"
            v-show="info.users.length"
            v-for="user in info.users"
            :key="user.id"
        >
            <a :href="user.html_url" target="_blank">
                <img :src="user.avatar_url" style="width: 100px" />
            </a>
            <p class="card-text">{{ user.login }}</p>
        </div>
        <!-- 展示初始化消息 -->
        <h1 v-show="info.isFirst">Welcome!</h1>
        <!-- 展示加载中 -->
        <h1 v-show="info.isLoading">Loading...</h1>
        <!-- 展示错误信息 -->
        <h1 v-show="info.errMsg">{{ info.errMsg }}</h1>
    </div>
</template>

<script>
    export default {
        name: "List",
        data() {
            return {
                info: {
                    users: [],
                    isFirst: true,
                    isLoading: false,
                    errMsg: "",
                },
            };
        },
        mounted() {
            this.$bus.$on("updateListData", (data) => {
                this.info = { ...this.info, ...data };
            });
        },
    };
</script>

<style scoped>
    .album {
        min-height: 50rem; /* Can be removed; just added for demo purposes */
        padding-top: 3rem;
        padding-bottom: 3rem;
        background-color: #f7f7f7;
    }

    .card {
        float: left;
        width: 33.333%;
        padding: 0.75rem;
        margin-bottom: 2rem;
        border: 1px solid #efefef;
        text-align: center;
    }

    .card > img {
        margin-bottom: 0.75rem;
        border-radius: 100px;
    }

    .card-text {
        font-size: 85%;
    }
</style>
