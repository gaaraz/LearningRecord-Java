<template>
    <div>
        <div class="row">
            <div class="col-xs-offset-2 col-xs-8">
                <div class="page-header"><h2>Vue Router Demo</h2></div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-2 col-xs-offset-2">
                <div class="list-group">
                    <!-- <a class="list-group-item" href="./about.html">About</a>
                    <a class="list-group-item active" href="./home.html"
                        >Home</a
                    > -->
                    <router-link
                        class="list-group-item"
                        active-class="active"
                        to="/about"
                        >About</router-link
                    >
                    <router-link
                        class="list-group-item"
                        active-class="active"
                        to="/home"
                        >Home</router-link
                    >
                </div>
            </div>
            <div class="col-xs-6">
                <div class="panel">
                    <div class="panel-body">
                        <!-- 指定组件的呈现位置 -->
                        <router-view></router-view>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "App",
    };
</script>

<style></style>
