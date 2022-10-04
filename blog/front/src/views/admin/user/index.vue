<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="用户昵称" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户身份" prop="role">
        <el-input
          v-model="queryParams.role"
          placeholder="请输入用户身份,1为管理员，0为用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          >删除</el-button
        >
      </el-col>
    </el-row>

    <el-table
      v-loading="loading"
      :data="userList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="用户昵称" align="center" prop="userName" />
      <el-table-column label="用户身份" align="center" prop="role">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.role === 1 ? 'primary' : 'success'"
            disable-transitions
            >{{ scope.row.role === 0 ? '用户' : '管理员' }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column
        label="注册时间"
        align="center"
        prop="registTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.registTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电话号码" align="center" prop="phone" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户昵称" prop="userName">
          <el-input v-model="form.username" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="用户密码" prop="passWrod">
          <el-input v-model="form.password" placeholder="请输入用户密码" />
        </el-form-item>
        <el-form-item label="用户头像" prop="profilePhoto">
          <el-input v-model="form.profilePhoto" placeholder="请输入用户头像" />
        </el-form-item>
        <el-form-item label="用户身份" prop="role">
          <el-input
            v-model="form.role"
            placeholder="请输入用户身份,1为管理员，0为用户"
          />
        </el-form-item>
        <el-form-item label="注册时间" prop="registTime">
          <el-date-picker
            clearable
            v-model="form.registTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择注册时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="邮箱   " prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱   " />
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话号码" />
        </el-form-item>
        <el-form-item label="头像链接" prop="img">
          <el-input v-model="form.img" placeholder="请输入头像链接" />
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-input v-model="form.position" placeholder="请输入职位" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listUser,
  getUser,
  delUser,
  addUser,
  updateUser,
} from '@/api/system/user';
import Modal from '../../../utils/modal';
export default {
  name: 'User',
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 【请填写功能名称】表格数据
      userList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        username: null,
        password: null,
        profilePhoto: null,
        role: null,
        registTime: null,
        email: null,
        phone: null,
        img: null,
        position: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          { required: true, message: '用户昵称不能为空', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '用户密码不能为空', trigger: 'blur' },
        ],
      },
    };
  },
  created() {
    // 验证是否有权限
    this.$axios({
      url: '/admin/role',
      methods: 'get',
      params: {
        userName: this.$cookie.get('userName'),
      },
    }).then((res) => {
      console.log(res.data, '权限数据');
      this.$message({
        message: '欢迎你，管理员',
        type: 'success',
      });
      this.getList();
    });
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      console.log('getList');
      this.loading = true;
      listUser(this.queryParams).then((response) => {
        console.log('response.data', response.data);
        this.userList = response.data;
        this.total = response.data.length;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.queryParams.userId = null;
      this.queryParams.username = null;
      this.queryParams.password = null;
      this.queryParams.profilePhoto = null;
      this.queryParams.role = null;
      this.queryParams.registTime = null;
      this.queryParams.email = null;
      this.queryParams.phone = null;
      this.queryParams.img = null;
      this.queryParams.position = null;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm');
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.userId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = '添加【请填写功能名称】';
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids;
      getUser(userId).then((response) => {
        this.form = response.data;
        console.log('form', this.form);
        this.open = true;
        this.title = '修改【请填写功能名称】';
        // console.log('response handleUpdate', response);
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.userId != null) {
            updateUser(this.form).then((response) => {
              Modal.msgSuccess('修改成功');
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then((response) => {
              Modal.msgSuccess('新增成功');
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      Modal.confirm(
        '是否确认删除【请填写功能名称】编号为"' + userIds + '"的数据项？'
      )
        .then(function () {
          return delUser(userIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess('删除成功');
        })
        .catch(() => {});
    },
  },
};
</script>
