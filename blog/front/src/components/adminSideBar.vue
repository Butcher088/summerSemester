<template>
  <div>
    <el-menu default-active="1" class="el-menu-vertical-demo" router>
      <el-menu-item index="/admin/user">
        <i class="el-icon-user-solid"></i>
        <span slot="title">人员管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/tag">
        <i class="el-icon-menu"></i>
        <span slot="title">标签管理</span>
      </el-menu-item>
      <el-menu-item index="/admin/complaint">
        <i class="el-icon-document"></i>
        <span slot="title">投诉管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 100%;
}
</style>
