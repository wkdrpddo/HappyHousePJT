import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter)


import MainPage from '@/components/MainPage.vue';
import DongSearchPage from '@/components/DongSearchPage.vue';
import AptSearchPage from '@/components/AptDetailPage.vue';
import DongList from '@/components/DongList.vue';
import UserInfoAndModify from "@/components/UserInfoAndModify.vue"
import RegisterUser from "@/components/RegisterUser.vue"
import FreeBoard from "@/components/FreeBoard.vue"
import NoticeBoard from "@/components/NoticeBoard.vue"
import LineChart from "@/components/LineChart.vue"
import store from "@/store/store";

import KakaoLogin from "@/components/kakaos/KakaoLogin.vue";


export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: MainPage,
    },
    {
      name: 'MainPage',
      path: '/MainPage',
      component: MainPage,
    },
    {
      name: 'DongSearchPage',
      path: '/DongSearchPage',
      component: DongSearchPage,
    },
    {
      name: 'DongList',
      path: '/DongList',
      component: DongList,
    },
    {
      name: 'AptSearchPage',
      path: '/AptSearchPage',
      component: AptSearchPage,
    },
    {
      path: '/userinfo',
      name: 'UserInfo',
      component: UserInfoAndModify,
      beforeEnter: (to, from, next) => {
        if (!store.state.login.isLogin) {
          next("/MainPage");
        } else {
          return next();
        }
      }
    },
    {
      path: '/regist',
      component: RegisterUser,
    },
    {
      name: 'FreeBoard',
      path: '/freeboard',
      component: FreeBoard,
      beforeEnter: (to, from, next) => {
        if (!store.state.login.isLogin) {
          next("/MainPage");
        } else {
          return next();
        }
      }
    },
    {
      name: 'NoticeBoard',
      path: '/noticeboard',
      component: NoticeBoard,
      beforeEnter: (to, from, next) => {
        if (!store.state.login.isLogin) {
          next("/MainPage");
        } else {
          return next();
        }
      }
    },

    {
      name: 'LineChart',
      path: '/chart',
      component: LineChart,
    },

    {
      name: 'KakaoLogin',
      path: '/kakaologin',
      component: KakaoLogin,
    },

    
  ]
});