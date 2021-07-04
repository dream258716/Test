<template>

  <div>
  <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="adduser_form" label-width="0" @loginForm>
    <!-- 用户名-->
    <el-form-item prop="name" >
      <!--  placeholder="用户名  给用户名框设置提示文字  -->
      <el-input v-model="loginForm.name" prefix-icon="iconfont icon-yonghu" placeholder="用户名"></el-input>
    </el-form-item>
    <!-- 用户密码-->
    <el-form-item prop="password">
      <!--  placeholder="密码"  给密码框设置提示文字  type="password"  将密码设置为隐藏  -->
      <el-input v-model="loginForm.password" prefix-icon="iconfont icon-mima" type="password" placeholder="密码"></el-input>
    </el-form-item>
    <!-- 按钮-->
    <el-form-item class="btns">
      <el-button type="primary" @click="login">注册</el-button>
      <el-button type="info" @click="resetLoginForm()">重置</el-button>
    </el-form-item>
  </el-form>

   登入页中的注册页
  </div>

</template>

<script>
export default {
name: "adduer",
  data(){
  return{
    loginForm:{
      name:"admin",
      password:"123456"
    },
    loginRules:{
      //用户名校验
      name:[
        //
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
      ],
      //密码校验
      password:[
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, max: 14, message: '长度在 8 到 14 个字符', trigger: 'blur' }
      ],
    },

  }
  },
  methods:{
    resetLoginForm(){
      this.$refs.loginFormRef.resetFields();
    },

    login(){
      this.$http.post('/user/adduser',this.loginForm).then(ref =>{
        if(ref.data.err == "ok"){
          this.$message.success("添加成功")
          console.log(ref)
          this.$router.push({path:"/home"});
        }else {
          this.$message.error('该用户名已存在，请重新输入')
        }
      })
    },
  }
}
</script>

<style scoped>

.adduser_form {
  position: absolute;
  left: 50%;
  top: 50%;
  /*transform: translate(-50%, -50%);*/
}


</style>



































