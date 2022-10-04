<template>
  <div>
    <div class="sort">
      <el-menu :default-active="activeIndex" class="el-menu-demo" router mode="horizontal">
        <el-menu-item index="1" route="/" >推荐</el-menu-item>
        <el-menu-item index="2" route="heat">热度</el-menu-item>
        <el-menu-item index="3" route="latest">最新</el-menu-item>
        <el-menu-item class="divider">|</el-menu-item>
        <el-menu-item index="4" route="news">新闻</el-menu-item>
        <el-menu-item index="5" route="business">商业</el-menu-item>
        <el-menu-item index="6" route="cs">计算机</el-menu-item>
      </el-menu>
    </div>

    <div>
      {{title}}
    </div>
  </div>
</template>

<script>
export default {
  name: "Main",
  data(){
    return{
      activeIndex: '',
      title: '',

    }
  },
  methods: {

    resetData () {
      // 根据不同路由地址，返回不同数据
      if (this.$route.fullPath === '/') {
        this.title = '推荐' // 返回业务数据
        this.activeIndex = '1'
      } else if (this.$route.fullPath === '/heat') {
        this.title = '热度'
        this.activeIndex = '2'
      } else if (this.$route.fullPath === '/latest') {
        this.title = '最新'
        this.activeIndex = '3'
      }else if (this.$route.fullPath === '/news') {
        this.title = '新闻'
        this.activeIndex = '4'
      }else if (this.$route.fullPath === '/business') {
        this.title = '商业'
        this.activeIndex = '5'
      }else if (this.$route.fullPath === '/cs') {
        this.title = '计算机'
        this.activeIndex = '6'
      }
    },
  },
  created() {
    this.resetData()
  }
}
</script>

<style scoped>
.sort{
  display: flex;
  justify-content: center;
  background-color: #fff;
  width: 100%;

}
.divider{
  cursor: default;
}
.el-menu-item{
  font-size: medium;
}
</style>
