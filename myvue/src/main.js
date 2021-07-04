// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/global.css'
import './assets/font/iconfont.css'
import Echarts from 'echarts'

Vue.prototype.$echarts = Echarts;

//引入axios
import axios from 'axios'
Vue.prototype.$http = axios
// 设置axios访问的端口号
axios.defaults.baseURL = "http://localhost:8081"
//引入Element-ui
Vue.use(Element)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App),//引入Element-ui

  // components: { App },
  // template: '<App/>'
}).$mount('#app')
