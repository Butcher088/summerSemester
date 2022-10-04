<template>
  <div class="header">
    <div class="sort">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        router
        mode="horizontal"
      >
        <el-menu-item index="1" route="/notification">评论消息</el-menu-item>
        <el-menu-item index="2" route="/notification/digg"
          >点赞消息</el-menu-item
        >
        <el-menu-item index="3" route="/notification/follow"
          >关注消息</el-menu-item
        >
        <el-menu-item index="4" route="/notification/system"
          >系统消息</el-menu-item
        >
      </el-menu>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data() {
    return {
      activeIndex: '',
      title: '',
    };
  },
  methods: {
    resetData() {
      // 根据不同路由地址，返回不同数据
      if (this.$route.fullPath === '/notification') {
        this.title = '评论消息'; // 返回业务数据
        this.activeIndex = '1';
      } else if (this.$route.fullPath === '/notification/digg') {
        this.title = '点赞消息';
        this.activeIndex = '2';
      } else if (this.$route.fullPath === '/notification/follow') {
        this.title = '关注消息';
        this.activeIndex = '3';
      } else if (this.$route.fullPath === '/notification/system') {
        this.title = '系统消息';
        this.activeIndex = '4';
      }
    },
  },
  created() {
    this.resetData();
  },
};
</script>

<style scoped>
.header {
  margin-top: 4rem;
}
.sort {
  display: flex;
  justify-content: center;
  background-color: #fff;
  width: 100%;
  margin-top: 2px;
  margin-bottom: 0.67rem;
}
.divider {
  cursor: default;
}
.el-menu-item {
  font-size: medium;
}
</style>
