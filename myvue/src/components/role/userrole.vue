<template>

  <div>
    <el-card>
    <el-row :gutter="25">
      <el-col :span="8">
        <!-- 搜索区域 -->
        <el-input placeholder="请输入需要查询的用户名" v-model="queryInfo.query"
                  @keydown.enter.native="getList" clearable @clear="getList">
          <el-button slot="append" icon="el-icon-search" @click="getList"></el-button>
        </el-input>
      </el-col>

      <el-col :span="12">
        <el-cascader
          placeholder="请输入地区搜索"
          :options="options">
        </el-cascader>
        <el-button icon="el-icon-search"></el-button>
      </el-col>

      <el-col :span="4">
        <!-- 搜索按钮 -->
        <el-button type="primary"  @click="addDialogVisible = true "> 添加用户 </el-button>
      </el-col>
    </el-row>

      <!-- 用户内容展示 -->
      <el-table :data="userList" border stripe style="width: 100%">
        <el-table-column label="编号" prop="id"></el-table-column>
        <el-table-column label="用户名" prop="name"></el-table-column>
        <el-table-column label="城市" prop="city"></el-table-column>
        <el-table-column label="密码" prop="password"></el-table-column>
        <el-table-column label="状态" prop="state">

          <!-- 作用域插槽 -->
          <template slot-scope="scope">
            <!-- 每一行的数据 -->
            <!--            {{scope.row}}-->
            <el-switch v-model="scope.row.state" @change="userStateChanged(scope.row)"></el-switch>
          </template>

        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="160">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="getUser(scope.row.id)">编辑</el-button>
            <el-button type="text" size="small" @click="dUser(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    <div>

      <!-- 分页 -->
      <!-- @size-change 每页最大变化 @current-change  当前最大变化  layout 功能组件-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1,2,5,10]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
   </el-card>

    <!-- 添加用户 -->
    <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="40%" @close="addDialogClosed">
      <el-form :model="addForm" :rules="addRulesForm" ref="addRuleForms" label-width="80px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="addForm.name" @keydown.enter.native="addUser"></el-input>
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
        <el-button @click="addUser"> 添加 </el-button>
      </span>
    </el-dialog>

    <!-- 修改用户 -->
    <el-dialog title="修改用户" :visible.sync="editDialogVisible" width="40%" @close="editDialogClosed">
      <el-form :model="ditForm" :rules="editRulesForm" ref="editRuleForms" label-width="80px" class="demo-ruleForm">
        <el-form-item label="用户名">
          <el-input v-model="ditForm.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ditForm.password" @keydown.enter.native="editUser"></el-input>
        </el-form-item>
        <el-form-item label="所在城市" prop="city">
          <el-input v-model="ditForm.city" @keydown.enter.native="editUser"></el-input>
        </el-form-item>
      </el-form>
      <span>
        <el-button type="primary" @click="editNoUser"> 取消 </el-button>
        <el-button @click="editUser"> 确定 </el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "zhuti",
  data() {
    return {
      options: [
        {
          value: 'dfdsfsdf',
          label: 'this.queryInfo.pageSize',
        },
        // {
        //   value: 'dsfdsffdsf',
        //   label: '额外热翁无',
        // },
      ],
      //获取所有用户的信息
      userList:[],
      //分页用户总数
      total:0,
      //分页信息
      queryInfo:{
        query:"",
        pageNum:1,
        pageSize:5,
      },
      //添加用户的信息
      addForm:{
        name:'',
        password:'',
        city:'',
      },
      //添加用户框
      addDialogVisible:false,
      //修改用户框
      editDialogVisible:false,
      //修改用户的信息
      ditForm:{},
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
      //修改用户校验规则
      editRulesForm: {
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 4, max: 12,type: 'string', message: '长度在 4 到 12 个字符', trigger: 'blur'}
        ],
        city: [
          {required: true, message: '请输入所在城市', trigger: 'blur'},
          {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
        ],
      },
    }
  },

  //初始化vue组件时执行的函数
  created() {
    this.getList()
    // this.getPath();
  },

  methods: {

    // getPath(){
    //   this.$http.get('/user/getPath').then(ref =>{
    //     // this.options.label = ref.data[1]
    //     console.log(ref)
    //     this.options.label = ref.data.label[1]
    //   })
    //   console.log(this.options)
    // },

    userStateChanged(userinfo){
      this.$http.put('/user/upState?id='+userinfo.id + "&&state="+userinfo.state).then( ref =>{
        if (ref != "success") {
          this.$message.success("删除成功");
          return;
        }
        this.$message.error("删除失败");
      })
    },

    //删除用户
    dUser(id){
      this.$confirm('删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.delete("/user/deleteuser?id=" + id).then(ref=> {
          if (ref != "success") {
            this.$message.success("删除成功");
            this.getList();
            return;
          }
          this.$message.error("删除失败");
        }, ref=> {
          this.$message.error("删除失败");
        });
      }).catch(() => {
        this.$message.error("删除取消");
      });

    },

    //获取当前点击修改用户的信息
    getUser(id){
      this.$http.get("/user/getUserById?id="+id).then(ref =>{
        this.ditForm = ref.data
        this.editDialogVisible = true ;
      })
    },
    //取消修改用户信息
    editNoUser(){
      this.editDialogVisible = false;
      this.$message.info("取消修改");
    },

    //修改用户框清空
    editDialogClosed(){
      this.$refs.editRuleForms.resetFields();
    },
    //修改用户信息
    editUser(){
      this.$refs.editRuleForms.validate((valid)=>{
          if (!valid) return;
        this.$http.post('/user/upUser',this.ditForm).then(ref =>{
          if (ref != "error") {
            this.$message.success("修改成功");
            this.editDialogVisible = false;
            this.getList();
            return;
          }
          this.$message.error("删修改失败");
        })
      })
    },

    //添加用户框
    addDialogClosed(){
      this.$refs.addRuleForms.resetFields();
    },

    //添加用户
    addUser(){
      this.$refs.addRuleForms.validate((valid)=>{
          if (!valid) return;
        this.$http.post('/user/adduser',this.addForm).then(ref =>{
          if(ref.data.err == "ok"){
            this.$message.success("添加成功")
            this.addDialogVisible = false;
            this.getList();
          }else if (ref.data.err == "error"){
            this.$message.error('该用户名已存在，请重新输入')
          }else {
            this.$message.info("用户名不能为空")
          }
        })
      })
    },
    //获取所有用户信息用户
    getList(){
      this.$http.get("/user/allUser",{params:this.queryInfo}).then(ref =>{

        this.userList = ref.data.data
        this.total = ref.data.numbers;

      })
    },

    //分页信息，每页用户信息数
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.getList();
    },
    //分页信息，当前页数
    handleCurrentChange(newPage){
      this.queryInfo.pageNum = newPage;
      this.getList();
    },

  }

}
</script>

<style scoped>

.el-row{
  height: 40px;
  margin-bottom: 10px;
}

.el-table{
  text-align: center;
}

.el-table-column{
  text-align: center;
}

.el-breadcrumb{
  margin-bottom: 14px;
}
.el-main{
  line-height: 50px !important;
  text-align: center;
}

</style>
