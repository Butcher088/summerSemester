import axios from 'axios';
import { Notification, MessageBox, Message, Loading } from 'element-ui';
import { getToken } from '@/utils/auth';
import Cookies from 'js-cookie';
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
    // console.log(code, ';');
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
          console.log('登录状态过期1');
          var userId = this.$cookie.get('userId');
          console.log('userId=', userId);
          Cookies.remove('token');
          Cookies.remove('userId');
          Cookies.remove('userName');
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
        })
        .catch(() => {});
      return Promise.reject('无效的会话，或者会话已过期，请重新登录。');
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
      return res.data;
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
console.log(service, '--------------');
export default service;
