import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const originalPush = VueRouter.prototype.push;

VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login/login'),
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/register/register'),
  },
  {
    path: '/',
    name: 'index',
    component: () => import('../views/index'),
    children: [
      {
        path: '/',
        name: 'recommend',
        component: () => import('../views/main/Main'),
      },
      {
        path: '/heat',
        name: 'heat',
        component: () => import('../views/main/Main'),
      },
      {
        path: '/latest',
        name: 'latest',
        component: () => import('../views/main/Main'),
      },
      {
        path: '/news',
        name: 'news',
        component: () => import('../views/main/Main'),
      },
      {
        path: '/business',
        name: 'business',
        component: () => import('../views/main/Main'),
      },
      {
        path: '/cs',
        name: 'cs',
        component: () => import('../views/main/Main'),
      },
    ],
  },
  {
    path: '/admin',
    name: 'admin',
    component: () => import('../components/adminLayout.vue'),
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/admin/user/index.vue'),
      },
      {
        path: 'tag',
        name: 'tag',
        component: () => import('@/views/admin/tag/index.vue'),
      },
      {
        path: 'complaint',
        name: 'complaint',
        component: () => import('@/views/admin/complaint/index.vue'),
      },
    ],
  },
  {
    path: '/notification',
    name: 'notification',
    component: () => import('../views/notification/index'),
    children: [
      {
        path: '/',
        name: 'commit',
        component: () => import('@/views/notification/comment/index'),
      },
      {
        path: '/notification/digg',
        name: 'digg',
        component: () => import('@/views/notification/digg/index'),
      },
      {
        path: '/notification/follow',
        name: 'follow',
        component: () => import('@/views/notification/follow/index'),
      },
      {
        path: '/notification/system',
        name: 'system',
        component: () => import('@/views/notification/system/index'),
      },
    ],
  },
  {
    path: '/newPassage',
    name: 'newPassage',
    component: () => import('../views/new-passage/index'),
  },
  {
    path: '/draft/:articleId',
    name: 'editDraft',
    component: () => import('../views/new-passage/index'),
  },
  {
    path: '/favourites',
    name: 'favourites',
    component: () => import('../views/favorites/index'),
  },
  {
    path: '/user/:userId',
    name: 'user',
    component: () => import('../views/user/index'),
  },
  {
    path: '/statistics',
    name: 'statistics',
    component: () => import('../views/statistics/index'),
    children: [
      {
        path: '/statistics/',
        name: 'main',
        component: () => import('../components/statistics/Main'),
      },
      {
        path: '/statistics/passageManage',
        name: 'passageManage',
        component: () => import('../components/statistics/PassageManage'),
      },
      {
        path: '/statistics/drafts',
        name: 'drafts',
        component: () => import('../components/statistics/Drafts'),
      },
      {
        path: '/statistics/content',
        name: 'content',
        component: () => import('../components/statistics/Content'),
      },
      {
        path: '/statistics/follow',
        name: 'follow',
        component: () => import('../components/statistics/Follow'),
      },
    ],
  },
  {
    path: '/passage/:passageId',
    name: 'passage',
    component: () => import('../views/passage/index'),
  },
];

const router = new VueRouter({
  routes,
  mode: 'history',
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
});

export default router;
