<template>
  <div class="wrapper">
    <div class="header">
      <div class="left-box" @click="route('/')">
        <img class="logo" src="../assets/images/logo.png">
      </div>
      <div class="center-box">
        <el-input class="search" clearable v-model="input" prefix-icon="el-icon-search" placeholder="搜索主题"></el-input>
      </div>
      <div class="right-box" @click="route('notification')">
        <el-badge :value="3" type="primary">
          <i class="el-icon-message-solid"></i>
        </el-badge>

        <el-popover
            placement="bottom"
            trigger="click"
            width="150px">
          <el-menu>
            <el-menu-item @click="route('newPassage')">
              <i class="el-icon-edit"></i>
              <span>写文章</span>
            </el-menu-item>
            <el-menu-item  @click="route('drafts')">
              <i class="el-icon-tickets"></i>
              <span>草稿箱</span>
            </el-menu-item>
            <el-menu-item  @click="route('favourites')">
              <i class="el-icon-star-off"></i>
              <span>我的收藏</span>
            </el-menu-item>
            <el-menu-item  @click="route('user')">
              <i class="el-icon-user"></i>
              <span>个人主页</span>
            </el-menu-item>
          </el-menu>
          <img slot="reference" class="head-img" src="../assets/images/head-img.png">
        </el-popover>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return {
      input: '',
    }
  },
  methods: {
    route(path){
      this.$router.push({path: path});
    }
  }
}
</script>

<style scoped>
.wrapper{
  background-color: #fff;
  width: 100%;
}
.header{
  display: flex;
  flex-direction: row;
  align-items: center;
}
.center-box{
  flex-grow: 1;
  display: flex;
  justify-content: center;
}
.search{
  width: 45rem;
}
>>> .el-input__inner{
  height: 3rem;
  border-radius: 10px;
  font-size: large;
  padding-left: 50px;
}
>>> .el-input__icon{
  font-size: large;
  width: 50px;
  margin-top: 1px;
}

.left-box{
  width: 10rem;
  height: 4rem;
  margin-left: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
.right-box{
  width: 10rem;
  height: 4rem;
  display: flex;
  margin-right: 10px;
  justify-content: space-around;
  align-items: center;
}
.head-img{
  border-radius: 50%;
  height: 2.5rem;
  width: 2.5rem;
  cursor: pointer;
}
.right-box i{
  font-size: 1.5rem;
  cursor: pointer;
  color: #8A919F;
}

</style>
