<template>
    <div>
        <button>原生按钮</button>
        <input type="text" />
        <!-- 圆角按钮 -->
        <el-row>
            <el-button round>圆角按钮</el-button>
            <el-button type="primary" round>主要按钮</el-button>
            <el-button type="success" round>成功按钮</el-button>
            <el-button type="info" round>信息按钮</el-button>
            <el-button type="warning" round>警告按钮</el-button>
            <el-button type="danger" round>危险按钮</el-button>
        </el-row>
        <!-- 日期选择 -->
        <div class="block">
            <span class="demonstration">默认</span>
            <el-date-picker v-model="value1" type="date" placeholder="选择日期">
            </el-date-picker>
        </div>
        <!-- 圆按钮 -->
        <el-row>
            <el-button icon="el-icon-search" circle></el-button>
            <el-button type="primary" icon="el-icon-edit" circle></el-button>
            <el-button type="success" icon="el-icon-check" circle></el-button>
            <el-button type="info" icon="el-icon-message" circle></el-button>
            <el-button
                type="warning"
                icon="el-icon-star-off"
                circle
            ></el-button>
            <el-button type="danger" icon="el-icon-delete" circle></el-button>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "App",
        data() {
            return {
                value1: "",
            };
        },
    };
</script>
