import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/login'
import Home from '../components/Home'
import Userrole from '../components/role/userrole'
import Adduser from '../components/adduser'
import Deleteuser from '../components/user/deleteuser'
import Updateuser from '../components/user/updateuser'
import Updaterole from '../components/graphics/updaterole'
import Adduer from '../components/user/adduer'
import Welcome from '../components/welcome'
import Ckyh from '../components/user/ckyh'
import Tabs from '../components/ins/tabs'




Vue.use(Router)

  const routes = [
    {
      path:'/',
      redirect:'/Login'
    },
    {
      path: '/login',
      component:Login
    },
    {path: '/adduser',
      component: Adduser,
    },
    {
      path: '/tab',
      component:Tabs
    },
    {
      path: '/home',
      component:Home,
      redirect:"/welcome",
      children:[
        {path: '/welcome',component: Welcome},
        {path: '/userrole', component:Userrole },
        {path: '/updateuser',component: Updateuser},
        {path: '/adduer',component: Adduer},
        {path: '/updaterole',component: Updaterole},
        {path: '/ckyh',component: Ckyh},
        {path: '/deleteuser',component: Deleteuser},

        ]
    },

  ]

const router = new Router({
  routes,
  mode:"history"
})

// 挂载路由
// to : 将要访问  from : 从哪访问    next : 接着访问

router.beforeEach((to,from,next)=>{
  if(to.path =='/login' || to.path =='/adduser') return next();

  const userFlag = window.sessionStorage.getItem("user");

  if (!userFlag) return next('/login');

  next();

})

// router.beforeEach((to, from, next) => {
//   if (to.matched.some(res => res.meta.requireAuth)) { // 验证是否需要登陆
//     if (sessionStorage.getItem('user')) { // 查询本地存储信息是否已经登陆
//       next();
//     } else {
//       next({
//         path: '/login', // 未登录则跳转至login页面
//         query: {redirect: to.fullPath} // 登陆成功后回到当前页面，这里传值给login页面，to.fullPath为当前点击的页面
//       });
//     }
//   } else {
//     next();
//   }
// });


export default router


