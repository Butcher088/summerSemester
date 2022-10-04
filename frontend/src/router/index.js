import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('../views/index'),
    children: [
      {
        path: '/',
        name: 'recommend',
        component: () => import('../views/main/Main')
      },
      {
        path: '/heat',
        name: 'heat',
        component: () => import('../views/main/Main')
      },
      {
        path: '/latest',
        name: 'latest',
        component: () => import('../views/main/Main')
      },
      {
        path: '/news',
        name: 'news',
        component: () => import('../views/main/Main')
      },
      {
        path: '/business',
        name: 'business',
        component: () => import('../views/main/Main')
      },
      {
        path: '/cs',
        name: 'cs',
        component: () => import('../views/main/Main')
      }
    ]
  },
  {
    path: '/notification',
    name: 'notification',
    component: () => import('../views/notification/index')
  },
  {
    path: '/newPassage',
    name: 'newPassage',
    component: () => import('../views/new-passage/index')
  },
  {
    path: '/drafts',
    name: 'draft',
    component: () => import('../views/drafts/index')
  },
  {
    path: '/favourites',
    name: 'favourites',
    component: () => import('../views/favorites/index')
  },
  {
    path: '/user',
    name: 'user',
    component: () => import('../views/user/index')
  }
]


const router = new VueRouter({
  routes,
  mode:"history"
})

export default router
