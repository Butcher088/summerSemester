<template>
  <transition>
    <div v-show="show" class="wrapper">
      <div class="header">
        <div class="left-box" @click="route('/')">
          <img class="logo" src="../assets/images/logo.gif" />
        </div>
        <div class="center-box">
          <el-popover placement="bottom" trigger="click" width="600">
            <div class="search-history">
              <span class="title">搜索历史</span>
              <i class="el-icon-error" @click="clearHistory"></i>
            </div>
            <el-tag
              v-for="(item, index) in searchHistory"
              :key="index"
              @click="selTag(item)"
              type="info"
              size="small"
              >{{ item }}</el-tag
            >
            <div class="title">热门推荐</div>
            <el-tag
              @click="selTag(item)"
              v-for="(item, index) in searchRec"
              :key="index"
              type="info"
              size="small"
              >{{ item }}</el-tag
            >

            <el-input
              slot="reference"
              class="search"
              @focus="inputFocus"
              @blur="inputBlur"
              @change="search"
              clearable
              v-model="input"
              prefix-icon="el-icon-search"
              placeholder="搜索主题"
            ></el-input>
          </el-popover>
        </div>
        <div class="right-box">
          <button class="button-85" role="button" @click="route('/statistics')">
            创作者中心
          </button>
            <i
              class="el-icon-message-solid"
              @click="route('/notification')"
            ></i>

          <el-popover placement="bottom" trigger="click" width="150">
            <el-menu>
              <el-menu-item @click="route('/newPassage')">
                <i class="el-icon-edit"></i>
                <span>写文章</span>
              </el-menu-item>
              <el-menu-item @click="route('/statistics/drafts')">
                <i class="el-icon-tickets"></i>
                <span>草稿箱</span>
              </el-menu-item>
              <el-menu-item @click="route('/favourites')">
                <i class="el-icon-star-off"></i>
                <span>我的收藏</span>
              </el-menu-item>
              <el-menu-item @click="route('/user/' + userId)">
                <i class="el-icon-user"></i>
                <span>个人主页</span>
              </el-menu-item>
              <el-menu-item @click="route('/admin/tag')">
                <i class="el-icon-s-check"></i>
                <span>管理员</span>
              </el-menu-item>
              <el-menu-item @click="logout">
                <i class="el-icon-circle-close"></i>
                <span>退出登录</span>
              </el-menu-item>
            </el-menu>
            <img
              slot="reference"
              class="head-img"
              :src="headImg"
            />
          </el-popover>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import Cookies from 'js-cookie';
export default {
  name: 'Header',
  data() {
    return {
      show: true,
      headImg: '',
      top: '',
      input: '',
      userId: '',
      searchHistory: ['人工智能', '前端'],
      searchRec: ['数据库', '算法'],
    };
  },
  methods: {
    route(path) {
      this.$router.push({ path: path });
    },
    inputFocus() {
      this.initSearch();
      document.getElementsByClassName('search')[0].style.width = '45rem';
    },
    inputBlur() {
      document.getElementsByClassName('search')[0].style.width = '30rem';
    },
    // 触发搜索事件
    search() {
      // 非空才执行
      if(this.input != "") {
        this.$axios({
          url: '/addSearch',
          method: 'get',
          params: {
            content: this.input,
            userId: this.$cookie.get("userId"),
          },
        }).then((res) => {
          console.log(res)
        });
        this.$router.push({
          path: '/',
          query:{
            title: this.input
          }
        })


      } else {
        console.log("没执行")
      }
    },
    selTag(item) {
      this.input = item;
    },
    clearHistory() {
      this.searchHistory = [];
      this.$axios({
        url: '/deleteAllSearch',
        method: 'get',
        params: {
          userId: this.$cookie.get("userId"),
        },
      }).then((res) => {
        console.log(res)
      });
    },
    onScroll() {
      this.top =
        document.documentElement.scrollTop ||
        document.body.scrollTop ||
        window.pageYOffset;
    },
    // // 退出登录
    // quit() {
    //   this.route('/login');
    //   this.$cookie.remove('userId');
    //   this.$cookie.remove('userName');
    // },
    //退出登录
    // logout() {
    //   var that = this;
    //   this.$axios({
    //     url: '/userLayout',
    //     method: 'get',
    //     params: {
    //       userId: Cookies.get('userId'),
    //     },
    //   }).then((res) => {
    //     console.log(res.data, 'logout');
    //     if (res.data.code === 200) {
    //       Cookies.remove('token');
    //       Cookies.remove('userId');
    //       Cookies.remove('userName');
    //       this.$message({
    //         message: '恭喜你，退出成功',
    //         type: 'success',
    //       });
    //       setTimeout(() => {
    //         location.href = '/login';
    //       }, 500);
    //     }
    //   });
    // },
    //退出登录
    logout() {
      console.log('退出登录', this.$cookie.get('userId'));
      this.$axios({
        url: '/userLayout',
        method: 'get',
        params: {
          userId: this.$cookie.get('userId'),
        },
      }).then((res) => {
        console.log(res.data, 'logout');
        if (res.data.code === 200) {
          Cookies.remove('token');
          Cookies.remove('userId');
          Cookies.remove('userName');
          location.href = '/login';
        }
      });
    },
    //加载头像
    initUser(){
      this.$axios({
        url: 'create/getUser',
        params: {
          userId: this.userId
        },
        method: 'get'
      }).then( res => {
        this.headImg = res.data.data[0].img
          })
    },
    initSearch() {
      this.$axios({
        url: '/getAllSearch',
        method: 'get',
        params: {
          userId: this.$cookie.get('userId'),
        },
      }).then((res) => {
        console.log("历史",res.data.data)
        this.searchHistory = res.data.data
      })
      }
  },
  mounted() {
    //获取userId
    this.userId = this.$initUser();
    this.initUser();
    //在创作者中心界面不启用此方法
    if (this.$route.path.includes('/statistics')) {
      return;
    }
    window.addEventListener('scroll', this.onScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.onScroll);
  },
  watch: {
    top(newValue, oldValue) {
      // 等新值大于100的时候再做变化（优化一下）
      //   if (newValue > 100) {
      if (newValue > 64) {
        if (newValue > oldValue) {
          this.show = false;
          //隐藏
        } else {
          this.show = true;
          //显示
        }
      }
    },
  },
};
</script>

<style scoped lang="scss">
.v-enter-active {
  animation: slidein 0.5s linear;
}
.v-leave-active {
  animation: slidein 0.5s linear reverse;
}

@keyframes slidein {
  from {
    transform: translateY(-240px);
  }
  to {
    transform: translateY(0px);
  }
}
/* CSS */
.button-85 {
  padding: 0.6em 2em;
  border: none;
  outline: none;
  color: rgb(255, 255, 255);
  background: #111;
  cursor: pointer;
  position: relative;
  z-index: 0;
  border-radius: 10px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}
.button-85:before {
  content: '';
  background: linear-gradient(
    45deg,
    #ff0000,
    #ff7300,
    #fffb00,
    #48ff00,
    #00ffd5,
    #002bff,
    #7a00ff,
    #ff00c8,
    #ff0000
  );
  position: absolute;
  top: -2px;
  left: -2px;
  background-size: 400%;
  z-index: -1;
  filter: blur(5px);
  -webkit-filter: blur(5px);
  width: calc(100% + 4px);
  height: calc(100% + 4px);
  animation: glowing-button-85 20s linear infinite;
  transition: opacity 0.3s ease-in-out;
  border-radius: 10px;
}
@keyframes glowing-button-85 {
  0% {
    background-position: 0 0;
  }
  50% {
    background-position: 400% 0;
  }
  100% {
    background-position: 0 0;
  }
}
.button-85:after {
  z-index: -1;
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  background: #222;
  left: 0;
  top: 0;
  border-radius: 10px;
}

.wrapper {
  z-index: 10;
  background-color: #fff;
  width: 100%;
  height: 4rem;
  position: fixed;
  top: 0;
}
.header {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.center-box {
  flex-grow: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
.search {
  width: 30rem;
  transition: width 0.5s ease;
}
>>> .el-input__inner {
  height: 3rem;
  font-size: large;
  padding-left: 50px;
}
>>> .el-input__icon {
  font-size: large;
  width: 50px;
  margin-top: 1px;
}

.left-box {
  width: 20%;
  height: 4rem;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
.right-box {
  width: 20%;
  height: 4rem;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.head-img {
  border-radius: 50%;
  height: 2.5rem;
  width: 2.5rem;
  cursor: pointer;
}
.right-box i {
  font-size: 1.5rem;
  cursor: pointer;
  color: #8a919f;
}
.right-box i:hover{
  color: #515767;
}
.title {
  color: #66b1ff;
  font-size: small;
}
.el-tag {
  margin: 5px;
  cursor: pointer;
}
.search-history {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
