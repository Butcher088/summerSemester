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
      <el-form-item label="举报者" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入举报者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章id" prop="articleId">
        <el-input
          v-model="queryParams.articleId"
          placeholder="请输入文章id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被举报者" prop="reportedId">
        <el-input
          v-model="queryParams.reportedId"
          placeholder="请输入被举报者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="举报时间" prop="time">
        <el-date-picker
          clearable
          v-model="queryParams.time"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择举报时间"
        >
        </el-date-picker>
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
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="reportList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="举报编号" align="center" prop="reportId" />
      <el-table-column label="举报者" align="center" prop="userId" />
      <el-table-column label="文章id" align="center" prop="articleId" />
      <el-table-column label="被举报者" align="center" prop="reportedId" />
      <el-table-column label="举报内容" align="center" prop="content" />
      <el-table-column label="举报时间" align="center" prop="time" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.time }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="处理状态"
        align="center"
        prop="handle"
        width="100"
      >
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.handle === 0 ? 'primary' : 'success'"
            disable-transitions
            >{{ scope.row.handle === 0 ? '待处理' : '已处理' }}</el-tag
          >
        </template>
      </el-table-column>
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
            @click="handleEdit(scope.row)"
            >处理</el-button
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

    <el-dialog
      title="图片"
      :visible.sync="centerDialogVisible"
      width="20%"
      center
    >
      <el-image
        style="width: 250px; height: 200px"
        :src="url"
        :preview-src-list="srcList"
      >
      </el-image>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleUpdate">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  listReport,
  getReport,
  delReport,
  addReport,
  updateReport,
} from '@/api/system/report';
import Modal from '../../../utils/modal';
import { parseTime } from '../../../utils/fed.js';
export default {
  name: 'Report',
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
      reportList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        userId: null,
        articleId: null,
        reportedId: null,
        content: null,
        img: null,
        time: null,
        handle: null,
      },
      // 表单参数
      form: {},
      // 弹框
      centerDialogVisible: false,
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      srcList: [
        'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      ],
      reportId: '',
    };
  },

  created() {
    console.log('pareseTime=', parseTime('2022-07-27T14:20:04.000+00:00'));
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listReport(this.queryParams).then((response) => {
        this.reportList = response.data;
        this.total = response.data.length;
        console.log('response', response.data.data);
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
      this.queryParams.articleId = null;
      this.queryParams.reportedId = null;
      this.queryParams.content = null;
      this.queryParams.img = null;
      this.queryParams.time = null;
      this.queryParams.userId = null;
      this.queryParams.userId = null;
      this.queryParams.handle = null;
    },
    handleEdit(row) {
      this.centerDialogVisible = true;
      this.url = row.img;
      this.srcList = [row.img];
      this.reportId = row.reportId;
      console.log('row', row);
    },
    handleUpdate() {
      this.centerDialogVisible = false;
      this.$axios({
        url: '/admin/report/update/' + this.reportId,
        methods: 'get',
      }).then((res) => {
        console.log('更新接口', res);
        if (res.data.code === 200) {
          this.$message({
            message: '处理成功',
            type: 'success',
          });
          this.getList();
        }
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.reset();
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.reportId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const reportIds = row.reportId || this.ids;
      Modal.confirm(
        '是否确认删除【请填写功能名称】编号为"' + reportIds + '"的数据项？'
      )
        .then(function () {
          return delReport(reportIds);
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
