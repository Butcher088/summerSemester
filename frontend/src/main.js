import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//引入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//引入全局自定义组件
import Header from '@/components'

//全局组件挂载
Vue.use(ElementUI);
Vue.use(Header);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
