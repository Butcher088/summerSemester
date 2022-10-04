import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from 'axios';
//引入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//引入全局自定义组件
import Header from '@/components';
import Cookies from 'js-cookie';
// 引入路由守卫
import './permission';
// 导入jquery
import $ from 'jquery';
window.jQuery = $;
window.$ = $;
// 导入富文本编辑器
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
//echarts
import * as echarts from 'echarts';
Vue.prototype.$echarts = echarts;

import { Notification, MessageBox, Message, Loading } from 'element-ui';
import { getToken } from '@/utils/auth';

// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: 'http://localhost:8003',
  // 超时
  timeout: 10000,
});

// request拦截器
service.interceptors.request.use(
  (config) => {
    if (getToken()) {
      config.headers['token'] = getToken(); // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config;
  },
  (error) => {
    console.log(error);
    Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (res) => {
    // 未设置状态码则默认成功状态
    const code = res.data.code || 200;
    // 获取错误信息
    const msg = res.data.msg;
    console.log(code, '状态');
    if (code === 401) {
      MessageBox.confirm(
        '登录状态已过期，您可以继续留在该页面，或者重新登录',
        '系统提示',
        {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning',
        }
      )
        .then(() => {
          console.log('登录状态过期2');
          var userId = Cookies.get('userId');
          console.log('userId1', userId);
          Cookies.remove('token');
          Cookies.remove('userId');
          Cookies.remove('userName');
          try {
            this.$axios({
              url: '/userLayout',
              method: 'get',
              params: {
                userId: userId,
              },
            }).then((res) => {
              console.log(res.data, 'logout');
              if (res.data.code === 200) {
                setTimeout(() => {
                  location.href = '/login';
                }, 500);
              }
            });
          } catch (error) {
            location.href = '/login';
          }
        })
        .catch(() => {});
      return Promise.reject('无效的会话，或者会话已过期，请重新登录。');
    } else if (code === 403) {
      Message({
        message: msg,
        type: 'warning',
      });
      location.href = '/';
    } else if (code === 500) {
      Message({
        message: msg,
        type: 'error',
      });
      return Promise.reject(new Error(msg));
    } else if (code !== 200) {
      Notification.error({
        title: msg,
      });
      return Promise.reject('error');
    } else {
      return res;
    }
  },
  (error) => {
    let { message } = error;
    Message({
      message: message,
      type: 'error',
      duration: 5 * 1000,
    });
    return Promise.reject(error);
  }
);
// axios
Vue.prototype.$axios = service; // 给vue的原型添加一个公有的axios，各个组件都能直接用this.$axios，而不需要再引用
// 引入cookie
import jsCookie from 'js-cookie';
Vue.prototype.$cookie = jsCookie; // 在页面里可直接用 this.$cookie 调用

//挂载一个全局自定义方法
import initUser from './utils/initUser';
Vue.prototype.$initUser = initUser;

//全局组件挂载
Vue.use(ElementUI);
Vue.use(Header);
Vue.use(mavonEditor);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
