<template>

    <el-container class="el1-home">
      <!-- 左侧边栏 -->
      <el-aside :width="isCollapsed?'64px':'200px'">
        <div class="div2" @click="toggle">|||</div>
          <el-menu
            default-active="1"
            text-color="#0CACF1"
            active-text-color="#0C0C0C"
            unique-opened router :collapse="isCollapsed">
            <el-submenu :index="item.id+''" v-for="item in menulist" :key="item.id" >
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>{{item.name}}</span>
              </template>
                <el-menu-item :index="it.path" v-for="it in item.demo" :key="it.id">
                  <template>
                    <i class="el-icon-location"></i>
                    <span>{{it.name}}</span>
                  </template>
                </el-menu-item>

            </el-submenu>
          </el-menu>
      </el-aside>

      <!-- 主体 -->
      <el-container>
        <!-- 头部 -->
        <el-header>用户管理系统
          <div class="header-avatar">

            <el-dropdown>
  <span class="el-dropdown-link">
    <img src="../assets/log.png"/>
    <i class="el-icon-arrow-down el-icon--right"></i>
  </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人中心</el-dropdown-item>
<!--                <el-dropdown-item @click="logout">退出</el-dropdown-item>-->
                <el-button @click="logout" >退出</el-button>
              </el-dropdown-menu>
            </el-dropdown>

          </div>

        </el-header>

        <!-- 右侧内容 -->
        <el-main>
          <Table></Table>
          <router-view></router-view>
        </el-main>
      </el-container>

    </el-container>


</template>

<script>

import Table from './ins/tabs'

export default {
  components: {Table},

  data(){
    return{
      menulist:[],
      isCollapsed:true,
      userInfo: {
        id: "",
        username: "",
        avatar: ""
      }
    }
  },

  created() {
    this.getMenuList()
  },
  methods:{
    getMenuList(){
      this.$http.get('/user/getuserpath').then(ref =>{
        if(ref!=null){
          this.menulist = ref.data.userpaths
        }
      })
    },
    toggle(){
      this.isCollapsed = !this.isCollapsed;
    },

    logout() {
      window.sessionStorage.clear();//用户退出清楚记录
      this.$router.push("/login");//回到登入页面
      this.$message.success("退出成功");

    }

  }

}
</script>

<style lang="less" scoped>

.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}

img{
  width: 60px;
  height: 60px;
  border-radius: 50%;
  margin-right: -20px;

}

.header-avatar {
  float: right;
  width: 100px;
  height: 60px;
  line-height: 60px;
  margin-right: 10px;
  box-sizing:border-box;
}

.div2{
  height: 60px;
  line-height: 60px;
  color: #55a532;
  text-align: center;

  letter-spacing: 0.2em;
  cursor: pointer;

}
.el-submenu{
  font-weight: bolder;
  font-style: oblique;
}

.el-menu-item{
  padding:0 0;
}

.logout{
  background-color: #e8dede;
  float: right;
  font-size: 16px;
  border-radius: 8px;
  text-align: center;
  margin-top: 18px;
}

.col1{
  width: 200px;
}

.el-header {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  line-height: 60px;
  font-weight: bold;
  font-size: 22px;
  letter-spacing: 4px;
  font-style: oblique;
  border: 1px solid #AAA3A3;
}
.el-aside {
  background-color: #797979;
  color: #FFFFFF;
  text-align: center;
  line-height: 200px;
  .el-menu{
    border-right: none;
    background-color:  #797979;
  }
}

.el-main {
  background-color: #FFFFFF;
  color: #333;
  text-align: center;
  padding: 0;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}


.el1-home {
  height: 100%;
}


</style>
