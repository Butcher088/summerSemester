<template>
  <div style="background-color: #f6f8fc; margin-top: 4rem">
    <div :class="sortFix ? 'sort-fixed' : 'sort'" id="sort">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        router
        mode="horizontal"
      >
        <el-menu-item index="1" route="/">推荐</el-menu-item>
        <el-menu-item index="2" route="heat">热度</el-menu-item>
        <el-menu-item index="3" route="latest">最新</el-menu-item>
        <el-menu-item class="divider">|</el-menu-item>
        <el-menu-item index="4" route="news">前端</el-menu-item>
        <el-menu-item index="5" route="business">后端</el-menu-item>
        <el-menu-item index="6" route="cs">人工智能</el-menu-item>
      </el-menu>
    </div>

    <el-skeleton v-if="loading" style="margin: 20px 80px" :rows="25" animated :loading="true"/>

    <div class="body" v-if="!loading">
      <div class="card-wrapper">
        <div
          class="card"
          @click="viewPassage(item.articleId)"
          v-for="(item, index1) in this.blogs"
          :key="index1"
        >
          <div class="header">
            <img class="head-img" v-if="!item.img" :src="require('../../assets/images/head-img.png')" />
            <img class="head-img" v-else :src="item.img" />
            <span class="writer">{{item.userName}}</span>
            <span class="divider"></span>
            <span class="date">{{ formTime(item.createTime) }}</span>
            <span class="divider"></span>
            <el-tag
              v-for="(tag, index2) in item.labels"
              :key="index2"
              type="info"
              size="mini"
              >{{ tag }}</el-tag
            >
          </div>
          <div class="down">
            <div class="main">
              <div class="title">{{ item.title }}</div>
              <div class="content">{{ item.summary }}</div>
              <div class="footer">
                <i class="el-icon-view"></i>
                <span class="num">{{ item.clickCount }}</span>
                <i class="el-icon-star-off"></i>
                <span class="num">{{ item.collectCount }}</span>
                <i class="el-icon-chat-square"></i>
                <span class="num">{{ item.comment_count }}</span>
                <i class="iconfont icon-dianzan1"></i>
                <span class="num">{{ item.likeCount }}</span>
              </div>
            </div>
            <div>
              <img v-if="item.cover" :src="item.cover" />
              <img v-else :src="require('../../assets/images/aaa.png')" />
            </div>
          </div>
        </div>
      </div>
      <div :class="sortFix ? 'right-wrapper-fixed' : 'right-wrapper'">
        <div class="create-center">
          <div class="header">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-drxx18"></use>
            </svg>
            <span class="title">创作中心</span>
          </div>
          <div class="up">
            <div class="option" @click="route('/newPassage')">
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-xianxingyumaobi"></use>
              </svg>
              <span class="text">写文章</span>
            </div>
            <div class="option" @click="route('/notification')">
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-xinxi"></use>
              </svg>
              <span class="text">读信息</span>
            </div>
            <div class="option" @click="route('/statistics/drafts')">
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-caogao"></use>
              </svg>
              <span class="text">草稿箱</span>
            </div>
          </div>
          <div class="down">
            <div class="module">
              <span class="tag">今日被浏览</span>
              <div class="num">{{ creator[0] }}</div>
            </div>
            <div class="module">
              <span class="tag">今日收获赞</span>
              <div class="num">{{ creator[1] }}</div>
            </div>
          </div>
        </div>
        <div class="rank">
          <div class="header">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-fenlei-remen"></use>
            </svg>
            <span class="title">热门文章</span>
          </div>
          <div class="rank-list">
            <div
              class="list-item-passage"
              v-for="(item, index3) in rankPassages"
              :key="index3"
              @click="route('/passage/'+item.id)"
            >
              <span class="no">{{ index3 + 1 }}</span>
              <span class="name">{{ item.title }}</span>
            </div>
          </div>
        </div>
        <div class="rank">
          <div class="header">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-jiangpai"></use>
            </svg>
            <span class="title">作者榜单</span>
          </div>
          <div class="rank-list">
            <div
              class="list-item-writer"
              v-for="(item, index4) in rankWriter"
              :key="index4"
              @click="route('/user/'+item.id)"
            >
              <img :src=item.img />
              <span class="name">{{item.name}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//iconfont
import '@/assets/icon/iconfont.css';
import '@/assets/icon/iconfont.js';
import { parseTime } from '@/utils/time';

export default {
  name: 'Main',
  data() {
    return {
      userId: '',
      activeIndex: '',
      title: '',
      loading: true,
      blogs: [
        // {
        //   id: 0,
        //   writer: 'butcher',
        //   writerHeadImg: require("../../assets/images/head-img.png"),
        //   date: '2022-7-15',
        //   title: '雅思口语part2只让说了1分半就被打断了。。是不是翻水了?',
        //   content: '雅思口语Part2让众多烤鸭头疼，流程的话相信同学们都知道，就是考官先给你一分钟准备一个话题（cue card）' +
        //       '、一支铅笔和一张白纸（用于记录一些关键词），然后再进行一到两分钟的陈述。',
        //   likeNum: 15,
        //   favNum: 10,
        //   comNum: 23,
        //   views: 125,
        //   img: [require('../../assets/images/aaa.png')],
        //   tags: ['财富', '英语', '雅思']
        // },
      ],
      // 暂未从后端取到的假数据
      tags: ['财富', '英语', '雅思'],
      // 评论数没取 作者名没取 等等。。。。

      sortFix: false,
      creator: [0, 0],
      rankPassages: [],
      rankWriter: [],
      // 分页数据
      startIndex: 0,
      pageSize: 5,
    };
  },
  methods: {
    //页面加载时请求数据
    resetData() {
      // 根据不同路由地址，返回不同数据
      if (this.$route.fullPath === '/') {
        this.title = '推荐' // 返回业务数据
        this.activeIndex = '1'
        this.getAllByTitle(this.title)
      } else if (this.$route.fullPath === '/heat') {
        this.title = '热度'
        this.activeIndex = '2'
        this.getAllByTitle(this.title)
      } else if (this.$route.fullPath === '/latest') {
        this.title = '最新'
        this.activeIndex = '3'
        this.getAllByTitle(this.title)
      } else if (this.$route.fullPath === '/news') {
        this.title = '前端';
        this.activeIndex = '4';
        this.getAllByTitle(this.title)
      } else if (this.$route.fullPath === '/business') {
        this.title = '后端';
        this.activeIndex = '5';
        this.getAllByTitle(this.title)
      } else if (this.$route.fullPath === '/cs') {
        this.title = '人工智能';
        this.activeIndex = '6';
        this.getAllByTitle(this.title)
      }
    },
    //封装分类请求方法
    getAllByTitle(title) {
      this.$axios({
        url: '/getAllByTitle',
        method: 'get',
        params:{
          title: title,
          startIndex: this.startIndex,
          pageSize: this.pageSize,
        }
      }).then(res => {
        // 必须拼接才能实现下拉刷新的效果
          this.blogs = this.blogs.concat(res.data.data)
        console.log("打印:",this.blogs)
      })
    },
    //固定sort和右侧卡片
    handleScroll() {
      //计算滚动条位置
      var scrollTop =
        window.pageYOffset ||
        document.documentElement.scrollTop ||
        document.body.scrollTop
      // 获取滚动的高度（获取整个html的高度）
      let scrollHeight=document.documentElement.scrollHeight;
      // 获取屏幕(浏览器)高度
      let clienHeight=document.documentElement.clientHeight;
      // 滚动的距离 + 屏幕高度 - 内容高度 >= 0 表示滚动到底部了      (下拉加载判断条件)

      //计算绑定div位置
      var offsetTop = document.querySelector('#sort').offsetTop;
      //进行比较设置位置fixed
      if (!this.sortFix) {
        if (scrollTop > offsetTop) {
          this.sortFix = true;
        }
      }
      if (scrollTop <= 64) {
        this.sortFix = false;
      }
      // console.log("计算值：",scrollTop+clienHeight-scrollHeight)
      // 下拉刷新
      if(scrollTop+clienHeight-scrollHeight >= -50){
        console.log("我到底了");
        this.startIndex = this.startIndex + this.pageSize;
        //滚动至底部后请求数据
        this.getAllByTitle(this.title)
      }

    },
    //路由跳转 查看文章
    viewPassage(articleId) {
      this.$router.push('/passage/' + articleId);
    },
    // 引入工具方法，转化时间格式
    formTime(time) {
      return parseTime(time);
    },
    //路由跳转
    route(path){
      this.$router.push(path);
    },
    //获取今日浏览
    initViews(){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getClick',
          method: 'get',
          params: {
            userId: this.userId
          }
        }).then(res => {
          let data = res.data.data;
          this.creator[0] = data[1].length;
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },
    //获取今日获赞
    initLike(){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getLike',
          method: 'get',
          params: {
            userId: this.userId
          }
        }).then(res => {
          let data = res.data.data;
          this.creator[1] = data[1].length;
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },
    //获取热门文章
    initHeatPassage(){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/getHeatArticle',
          method: 'get'
        }).then(res => {
          let data = res.data.data;
          this.rankPassages = data.map(item => {
            return {
              id: item.articleId,
              title: item.title
            }
          })

          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })

    },
    //获取榜单作者
    initHeatWriter(){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/getHeatWriter',
          method: 'get'
        }).then(res => {
          let data = res.data.data;
          this.rankWriter = data.map(item => {
            return {
              id: item.userId,
              name: item.userName,
              img: item.img
            }
          })

          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })

    },
    //加载数据
    initAllData(){
      Promise.all([
        this.initViews(),
        this.initLike(),
        this.initHeatPassage(),
        this.initHeatWriter()
      ])
          .then(res => {
            console.log("请求数据成功");
            this.loading = false;
          })

    }
  },
  created() {
    let title = this.$route.query.title
    console.log(title)
    console.log(typeof title)

    if (!title) {
      this.resetData();
    } else {
      this.title = title
      this.getAllByTitle(this.title)
    }
  },
  mounted() {
    this.userId = this.$initUser()
    this.initAllData()

    window.addEventListener('scroll', this.handleScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll);
  },
};
</script>

<style scoped lang="scss">
.create-center .up,
.create-center .down {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}
.create-center .option {
  font-size: 14px;
  color: #555;
  display: flex;
  align-items: center;
  cursor: pointer;
}
.create-center .down {
  background-color: #f5f5f5;
  border-radius: 8px;
  margin: 12px 16px 0;
}
.create-center {
  padding-bottom: 12px;
}

.create-center .module {
  color: #555;
  padding: 6px 0;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.create-center .module .tag {
  font-size: xx-small;
}
.create-center .module .num {
  font-size: large;
  font-weight: bolder;
}
.create-center .up .icon {
  width: 1rem;
  height: 1rem;
  margin-right: 4px;
}

.right-wrapper {
  width: 17vw;
  margin: 2vh 15vw 1vh 0;
  overflow: hidden;
}
.right-wrapper-fixed {
  position: fixed;
  width: 17vw;
  top: 10.5vh;
  right: 15vw;
  overflow: hidden;
}
.rank,
.create-center {
  background-color: #fff;
  border-radius: 12px;
  margin-bottom: 10px;
}

.rank .header,
.create-center .header {
  display: flex;
  align-items: center;
  font-size: medium;
  padding: 12px 0 16px 12px;
}
.create-center .title,
.rank .header .title {
  margin-left: 12px;
}

.rank .list-item-passage {
  padding-bottom: 12px;
  display: flex;
  flex-direction: row;
  align-items: baseline;
  cursor: pointer;
}
.rank .no {
  font-weight: bolder;
  font-style: oblique;
  background-image: -webkit-linear-gradient(bottom, #fff200, #fd8403, #ff0026);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin: 0 12px 0 14px;
}
.rank .name {
  font-size: 14px;
  color: #555;
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-all;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1; //此处为行数，可以改为任意数字
}
.rank img {
  height: 2rem;
  width: 2rem;
  border-radius: 50%;
  margin: 0 12px 0 14px;
}
.rank .list-item-writer {
  padding-bottom: 12px;
  display: flex;
  flex-direction: row;
  align-items: center;
  cursor: pointer;
}

.sort-fixed {
  position: sticky;
  top: 0;
  z-index: 9;
  display: flex;
  justify-content: center;
  background-color: #fff;
  width: 100%;
}

.sort {
  display: flex;
  justify-content: center;
  background-color: #fff;
  width: 100%;
}

.divider {
  cursor: default;
}

.el-menu-item {
  font-size: medium;
}

.card {
  width: 50vw;
  margin: 2vh 0 10px 15vw;
  background-color: white;
  border-radius: 12px;
  overflow: auto;
  cursor: pointer;
  transition: all 0.5s;
}
.card:hover {
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}

.card .header {
  display: flex;
  align-items: center;
  padding-top: 1.25rem;
}

.card .head-img {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  margin: 0 16px;
}

.card .writer,
.card .date,
.card .divider {
  color: #909399;
}

.card .writer {
  font-weight: bold;
}

.card .divider {
  margin: 0 16px;
  background-color: #8a919f;
  width: 1px;
  height: 12px;
  display: block;
}

.card .el-tag {
  margin: 0 5px;
}

.card .down {
  margin: 0 16px;
  display: flex;
  justify-content: space-between;
}

.card .main {
  margin-right: 50px;
}

.card .title {
  font-weight: bolder;
  font-size: larger;
  margin: 16px 0;
  -webkit-line-clamp: 1; //此处为行数，可以改为任意数字
}

.card .content {
  font-size: 14px;
  color: #636569;
  -webkit-line-clamp: 2; //此处为行数，可以改为任意数字
}

.card .title,
.card .content {
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-all;
  -webkit-box-orient: vertical;
}

.down img {
  width: 8rem;
  height: 8rem;
  border-radius: 12px;
}

.footer {
  margin: 16px 0;
  color: #4e4f52;
}

.footer .num {
  margin: 0 32px 0 5px;
}

.body {
  display: flex;
  justify-content: space-between;
}

.icon {
  width: 1.5rem;
  height: 1.5rem;
  fill: currentColor;
  overflow: hidden;
}
</style>
