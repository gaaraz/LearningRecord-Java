import { reactive, onMounted, onBeforeUnmount } from "vue";

export default function () {
    let point = reactive({
        x: 0,
        y: 0,
    });

    // 实现鼠标打点相关的方法
    function savePoint(event) {
        point.x = event.pageX;
        point.y = event.pageY;
        console.log(event.pageX, event.pageY);
    }

    // 实现鼠标打点相关的生命周期
    onMounted(() => {
        console.log("---onMounted---");
        window.addEventListener("click", savePoint);
    });

    onBeforeUnmount(() => {
        console.log("---onBeforeUnmount---");
        window.removeEventListener("click", savePoint);
    });

    return point;
}
