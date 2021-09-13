import {createRouter, createWebHashHistory, RouteRecordRaw} from "vue-router";
import store from "@/store"
import user from "../store";
import axios from "axios";

const router = createRouter({
  history: createWebHashHistory(),
  routes:[
    {
      path:'/',
      redirect:'login',
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('../views/common/Login.vue'),
    },
    {
      path:'/adms',
      name:'adms',
      component:() => import('../views/adm/adm.vue'),
      children:[{
        path: '/adm1',
        name: '教师管理',
        component:() => import('../views/adm/adm1.vue'),
      },{
        path: '/adm2',
        name: '考生管理',
        component:() => import('../views/adm/adm2.vue')
      },{
        path: '/adm3',
        name: '课程管理',
        component:() => import('../views/adm/adm3.vue')
      },{
        path: '/adm4',
        name: '考题管理',
        component:() => import('../views/adm/adm4.vue')
      },{
        path: '/adm5',
        name: '绩点管理',
        component:() => import('../views/adm/adm5.vue')
      },{
        path: '/adm6',
        name: '个人信息',
        component:() => import('../views/adm/adm6.vue')
      }],
      redirect:'adm6'
    },
    {
      path:'/teachers',
      name:'Teachers',
      component:() => import('../views/teacher/teacher.vue'),
      children:[{
          path: '/teacher2-1',
          name: '试题管理',
          component:() => import('../views/teacher/teacher2-1.vue')
    },{
        path: '/teacher3-1',
        name: '学生管理',
        component:() => import('../views/teacher/teacher3-1.vue')
      },{
        path: '/teacher4-1',
        name: '成绩管理',
        component:() => import('../views/teacher/teacher4-1.vue')
      },{
        path: '/teacher6-1',
        name: '个人中心',
        component:() => import('../views/teacher/teacher6-1.vue')
      }],
      redirect:'teacher6-1'
    },
    {
      path:'/students',
      name:'Students',
      component:() => import('../views/student/student.vue'),
      children:[{
        path: '/student1-1',
        name: '我的课程',
        component:() => import('../views/student/student1-1.vue')
      },{
        path: '/student2-1',
        name: '我的成绩',
        component:() => import('../views/student/student2-1.vue')
      },{
        path: '/student3-1',
        name: '个人设置',
        component:() => import('../views/student/student3-1.vue')
      },{
        path: 'student4-1',
        name: '在线考试',
        component:() => import('../views/student/student4-1.vue')
      }]
    },
    {
      path:'/exam',
      name:'Exam',
      component:() => import('../views/exam/exam.vue'),
      meta:{
        requiredLogin: true
      },
    },
    {
      path:'/exam1',
      name:'Exam1',
      component:() => import('../views/exam/exam1.vue'),
      meta:{
        requiredLogin: true
      },
    },
    {
      path:'/forgetPsw',
      name:'forgetPsw',
      component:() => import('../views/forgetPsw/forgetPsw.vue'),
      children:[{
        path: '/forgetPsw1',
        name: 'forgetPsw1',
        component:() => import('../views/forgetPsw/forgetPsw1.vue')
      },{
        path: '/forgetPsw3',
        name: 'forgetPsw3',
        component:() => import('../views/forgetPsw/forgetPsw3.vue')
      },{
        path: '/forgetPsw4',
        name: 'forgetPsw4',
        component:() => import('../views/forgetPsw/forgetPsw4.vue')
      }],
      redirect:'forgetPsw1'
    },
    {
      path:'/empty',
      name:'empty',
      component:() => import('../views/common/empty.vue')
    }
  ]
})
router.beforeEach((to,from,next) => {
  if (to.name !== 'Login' && !store.state.isLogin){
    next({ name: 'Login'}) || next({ name: 'forgetPsw'})
  }
  else {
    next()
  }
})
export default router
