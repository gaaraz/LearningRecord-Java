const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave: false,
    chainWebpack: (config) => {
        config.plugin("define").tap((definitions) => {
            Object.assign(definitions[0], {
                __VUE_OPTIONS_API__: "true",
                __VUE_PROD_DEVTOOLS__: "false",
                __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: "false",
            });
            return definitions;
        });
    },
    // devServer: {
    //     proxy: {
    //         "/api": {
    //             target: "http://api.uomg.com/api/rand.qinghua?format=json",
    //             pathRewrite: { "^/api": "" },
    //             // ws: true, //用于支持websocket
    //             // changeOrigin: true //用于控制请求头中的host值
    //         },
    //     },
    // },
});
