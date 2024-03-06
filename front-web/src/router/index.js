import { createRouter, createWebHistory } from 'vue-router'
import IndexView from '@/views/index/IndexView.vue'
import MapIndexView from '@/views/map/MapIndexView.vue'
import PersonIndexView from '@/views/person/PersonIndexView.vue'
import DiscussIndexView from '@/views/discuss/DiscussIndexView.vue'
import TaskIndexView from '@/views/task/TaskIndexView.vue'
import NotFound from '@/views/error/NotFound'
import UserAccountLoginView from '../views/user/account/UserAccountLoginView'
import UserAccountRegisterView from '../views/user/account/UserAccountRegisterView'
import store from '@/store/index'
import UserAccountIndexView from '@/views/user/account/UserAccountIndexView.vue'

const routes = [
  {
    path: "/",
    name: "home",
    component: IndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/map/",
    name: "map_index",
    component: MapIndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/person/",
    name: "person_index",
    component: PersonIndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/discuss/",
    name: "discuss_index",
    component: DiscussIndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/task/",
    name: "task_index",
    component: TaskIndexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/account/",
    name: "user_account_index",
    component: UserAccountIndexView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/user/account/login/",
    name: "user_account_login",
    component: UserAccountLoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/user/account/register/",
    name: "user_account_register",
    component: UserAccountRegisterView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/",
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({ name: "user_account_login" });
  } else {
    next();
  }
});

export default router
