export default{
    install(Vue){
        // 全局过滤器
        Vue.filter('mySlice', function(value){
            return value.slice(0, 5);
        });

        // 定义全局指令
        Vue.directive('fbind', {
            // 指令与元素成功绑定时(一上来)
            bind(el, binding) {
                console.log(binding.value);
                el.value = binding.value;
            },
            // 指令所在元素被插入页面时调用
            inserted(el, binding) {
                console.log(binding.value);
                el.focus();
            },
            // 指令所在模板被重新解析时调用
            update(el, binding) {
                console.log(binding.value);
                el.value = binding.value;
            }
        });

        // 定义混入
        Vue.mixin({
            data() {
                return {
                    x:100,
                    y:200
                }
            },
        });

        // 给Vue原型上添加一个方法(vm和vc就都能用了)
        Vue.prototype.hello = ()=>{alert('Hello')}
    }
}