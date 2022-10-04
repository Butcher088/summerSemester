// 引入写好的全局组件
import Header from "@/components/Header";
import notifyHeaderVue from "./notify-header.vue";
// 默认导出插件
export default {
    install: function(Vue) {
        // 在自定义的插件中注册为全局组件
        Vue.component('Header',Header)
        Vue.component('notifyHeaderVue',notifyHeaderVue)
    }
}
