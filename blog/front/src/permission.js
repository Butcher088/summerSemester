import router from './router';
import store from './store';
import { Message } from 'element-ui';
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';
import { getToken } from '@/utils/auth';
import { isRelogin } from '@/utils/request';

NProgress.configure({ showSpinner: false });

const whiteList = [
  '/login',
  '/auth-redirect',
  '/bind',
  '/register',
  '/home',
  '/userLayout',
  '/addArticle',
];

router.beforeEach((to, from, next) => {
  NProgress.start();
  if (getToken()) {
    console.log('token', getToken());
    /* has token*/
    if (to.path === '/login') {
      console.log('to login');
      next({ path: '/' });
      NProgress.done();
    } else {
      next();
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1 || to.path === '/login') {
      // 在免登录白名单，直接进入
      next();
    } else {
      // next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
      next('/login');
      NProgress.done();
    }
  }
});

router.afterEach(() => {
  NProgress.done();
});
