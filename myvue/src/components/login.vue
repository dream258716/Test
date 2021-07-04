<template>

  <div class="login_con">
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/logo.png" alt="" />
      </div>
      <!-- 表单区域 -->
      <!--  :rules="loginRules"  设置登入框的用户名与密码的验证   :model="loginForm"  与data中的登入表单绑定 -->
      <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_form" label-width="0" @loginForm>
        <!-- 用户名-->
        <el-form-item prop="name" >
          <!--  placeholder="用户名  给用户名框设置提示文字  -->
          <el-input v-model="loginForm.name" prefix-icon="iconfont icon-yonghu" placeholder="用户名" @keydown.enter.native="login"></el-input>
        </el-form-item>
        <!-- 用户密码-->
        <el-form-item prop="password">
          <!--  placeholder="密码"  给密码框设置提示文字  type="password"  将密码设置为隐藏  -->
          <el-input v-model="loginForm.password" prefix-icon="iconfont icon-mima" type="password" placeholder="密码" @keydown.enter.native="login"></el-input>
        </el-form-item>
        <!-- 按钮-->
        <el-form-item class="btns" @keydown.enter.native="login">
          <el-button type="primary" @click="login">登入</el-button>
          <el-button type="info" @click="resetLoginForm()">重置</el-button>
          <el-button type="primary" class="buttonn" @click="addDialogVisible = true "> 注册用户 </el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 添加用户 -->
    <el-dialog title="注册用户" :visible.sync="addDialogVisible" width="40%" @close="addDialogClosed" class="loginUser">
      <el-form :model="addForm" :rules="addRulesForm" ref="addRuleForms" label-width="80px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="name" @keydown.enter.native="addUser">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addForm.password" @keydown.enter.native="addUser"></el-input>
        </el-form-item>
        <el-form-item label="所在城市" prop="city">
          <el-input v-model="addForm.city" @keydown.enter.native="addUser"></el-input>
        </el-form-item>
      </el-form>
      <span>
        <el-button type="primary" @click="addDialogVisible = false "> 取消 </el-button>
        <el-button @click="addUser"> 注册 </el-button>
      </span>
    </el-dialog>

  </div>

</template>

<script>

export default {

  data(){
    return{

      //添加用户校验规则
      addRulesForm: {
        name: [
          {required: true, message: '用户名不能为空', trigger: 'blur'},
          {min: 2, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 4, max: 12, message: '长度在 4 到 12 个字符', trigger: 'blur'}
        ],
        city: [
          {required: true, message: '请输入所在城市', trigger: 'blur'},
          {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
        ],
      },

      //添加用户的信息
      addForm:{
        name:'',
        password:'',
        city:'',
      },
      //添加用户框
      addDialogVisible:false,
      //登入表单
      loginForm:{
        name:"admin",
        password:"123456"
      },
      //验证对象
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

    };
  },

  created() {
    window.sessionStorage.clear();
  },

  methods:{
    resetLoginForm(){
      this.$refs.loginFormRef.resetFields();
    },

    login(){
      this.$refs.loginFormRef.validate((valid)=>{
          if (!valid) return;
        this.$http.post('/user/getuser',this.loginForm).then(ref =>{
          if(ref.data.err == "ok"){
            this.$message.success("登入成功")
            window.sessionStorage.setItem("user",ref.data.user.name);
            this.$router.push("/home")
          }else {
            this.$message.error('用户名或密码错误')
          }
        })
      })
    },

    //注册用户框
    addDialogClosed(){
      this.$refs.addRuleForms.resetFields();
    },

    //注册用户
    addUser(){
      this.$refs.addRuleForms.validate((valid)=>{
        if (!valid) return;
        this.$http.post('/user/adduser',this.addForm).then(ref =>{
          if(ref.data.err == "ok"){
            this.$message.success("注册成功")
            this.addDialogVisible = false;
          }else {
            this.$message.error('该用户名已存在，请重新输入')
          }
        })
      })

    },

  }

}

</script>

<style lang="less" scoped>

.buttonn{
  font-size: 14px;
  color: #ffffff;
  background-color: #dccbf3;
  border: none;
}
.loginUser{

}

el-dialog{
  text-align: center;
}

.login_con {
  height: 100%;
  background-color: #e9e9e9;

  .login_box {
    width: 450px;
    height: 300px;
    background-color: #dccbf3;
    border-radius: 4px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);

    .avatar_box {
      width: 130px;
      height: 130px;
      border: 1px solid skyblue;
      border-radius: 50%;
      padding: 10px;
      box-shadow: 0 0 4px skyblue;
      position: absolute;
      left: 50%;
      transform: translate(-50%, -50%);
      background-color: #d4dee2;
      img{
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #eaffea;
      }
    }
  }
}

.btns{
  display: flex;
  justify-content: flex-end;
  margin-right: 12px;
}
.login_form{
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 10px;
  box-sizing: border-box;
}

</style>
