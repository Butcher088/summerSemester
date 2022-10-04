<template>
  <div>
    <Header></Header>
    <div class="main-wrapper">
      <div class="left-bar">
        <div class="user-info">
          <img :src="user.headImg">
          <span class="username">{{user.name}}</span>
          <div class="button" @click="route('/newPassage')">
            <button class="button-48" role="button"><span class="text">写文章</span></button>
          </div>
        </div>
        <el-menu
            router
            :default-active="activeIndex"
            class="el-menu-vertical-demo">
          <el-menu-item index="/statistics">
            <i class="el-icon-s-home"></i>
            <span>首页</span>
          </el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-document-copy"></i>
              <span>内容管理</span>
            </template>
            <el-menu-item index="/statistics/passageManage" >文章管理</el-menu-item>
            <el-menu-item index="/statistics/drafts" >草稿箱</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-data-line"></i>
              <span>数据中心</span>
            </template>
            <el-menu-item index="/statistics/content">内容数据</el-menu-item>
            <el-menu-item index="/statistics/follow">关注数据</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
      <div class="component">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
  data(){
    return{
      activeIndex: '/',
      user: {
        id: '',
        headImg: '',
        name: ''
      }
    }
  },
  methods: {
    route(path){
      this.$router.push(path)
    },
    initUser(){
      //获取用户Id
      this.user.id = this.$initUser()
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getUser',
          method: 'get',
          params: {
            userId: this.user.id
          }
        }).then(res => {
          let data = res.data.data[0]
          this.user.headImg = data.img
          this.user.name = data.userName
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })


    }
  },
  //如果是通过头像点击草稿箱进入，相应菜单展开
  mounted() {
    if(this.$route.path){
      this.activeIndex = this.$route.path
    }

    this.initUser()

  },
  //监听路由信息，动态渲染左侧导航栏的active状态
  watch: {
    $route: {
      handler(newVal, oldVal) {
        //判断newVal有没有值监听路由变化
        if(newVal !== oldVal){
          this.activeIndex = newVal.path
        }

      },
    }
  },

}
</script>

<style scoped lang="scss">
.main-wrapper{
  width: 100%;
  background-color: #F6F8FC;
}
.component{
  padding: 5.5rem 10vw 5.5vh 27.5vw;
}

.left-bar{
  position: fixed;
  width: 16vw;
  margin: 5.5rem 0 0 10vw;
  height: 80vh;
  background-color: #fff;
  border-radius: 12px;

  .user-info{
    .button{
      display: flex;
      justify-content: center;
      margin-bottom: 15px;
    }
    img{
      height: 3.2rem;
      width: 3.2rem;
      border-radius: 50%;
      margin: 15px;
      vertical-align: middle;
    }
  }
  .el-menu-vertical-demo{
    //仅仅是为了去除elementui自带的边框
    border-right: #66B1FF;
  }
}


/* CSS */
.button-48 {
  border-radius: 12px;
  appearance: none;
  background-color: #409EFF;
  border-width: 0;
  box-sizing: border-box;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  font-family: Clarkson,Helvetica,sans-serif;
  font-size: 14px;
  font-weight: 500;
  letter-spacing: 0;
  line-height: 1em;
  margin: 0 auto;
  opacity: 1;
  outline: 0;
  padding: 1em 5em;
  position: relative;
  text-align: center;
  text-decoration: none;
  text-rendering: geometricprecision;
  text-transform: uppercase;
  transition: opacity 300ms cubic-bezier(.694, 0, 0.335, 1),background-color 100ms cubic-bezier(.694, 0, 0.335, 1),color 100ms cubic-bezier(.694, 0, 0.335, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  vertical-align: baseline;
  white-space: nowrap;
}

.button-48:before {
  border-radius: 12px;
  animation: opacityFallbackOut .5s step-end forwards;
  backface-visibility: hidden;
  background-color: #6cb3ff;
  clip-path: polygon(-1% 0, 0 0, -25% 100%, -1% 100%);
  content: "";
  height: 100%;
  left: 0;
  position: absolute;
  top: 0;
  transform: translateZ(0);
  transition: clip-path .5s cubic-bezier(.165, 0.84, 0.44, 1), -webkit-clip-path .5s cubic-bezier(.165, 0.84, 0.44, 1);
  width: 100%;
}

.button-48:hover:before {
  animation: opacityFallbackIn 0s step-start forwards;
  clip-path: polygon(0 0, 101% 0, 101% 101%, 0 101%);
}

.button-48:after {
  background-color: #FFFFFF;
}

.button-48 span {
  z-index: 1;
  position: relative;
}
</style>
>>>>>>> 0c9ff53ebeda6306ee498d8e070c38c41a7ecb1e
