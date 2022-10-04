// 引入写好的全局组件
import Header from "@/components/Header";
// 默认导出插件
export default {
    install: function(Vue) {
        // 在自定义的插件中注册为全局组件
        Vue.component('Header',Header)

    }
}
