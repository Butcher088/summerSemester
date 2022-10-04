<template>
  <div>
    <Header></Header>
    <div class="card-wrapper">
      <div class="card" @click="viewPassage(item.articleId)" v-for="(item,index) in blogs" :key="index">
        <div class="header">
          <img class="head-img" :src="item.img">
          <span class="writer">{{ item.userName }}</span>
          <span class="divider"></span>
          <span class="date">{{formTime(item.article.createTime)}}</span>
          <span class="divider"></span>
          <el-tag v-for="(tag,index2) in item.article.labels" :key="index2" type="info" size="mini">{{ tag }}
          </el-tag>
        </div>
        <div class="down">
          <div class="main">
            <div class="title">{{ item.article.title }}</div>
            <div class="content">{{ item.article.summary }}</div>
            <div class="footer">
              <i class="el-icon-view"></i>
              <span class="num">{{ item.article.clickCount }}</span>
              <i class="el-icon-star-off"></i>
              <span class="num">{{ item.article.collectCount }}</span>
              <i class="el-icon-chat-square"></i>
              <span class="num">{{ item.article.collectCount }}</span>
              <i class="iconfont icon-dianzan1"></i>
              <span class="num">{{ item.article.likeCount }}</span>
            </div>
          </div>
          <div>
            <img v-if="item.article.cover" :src="item.article.cover">
            <img v-else :src='require("../../assets/images/aaa.png")'>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import '@/assets/icon/iconfont.css'
  import '@/assets/icon/iconfont.js'
  import {parseTime} from "@/utils/time";
  import axios from 'axios';
  export default {
    name: "index",
    data() {
      return {
        blogs: {},
        userId: 0,
      }
    },
    created() {
      this.userId = this.$cookie.get("userId");
      this.$axios({
        url: '/collectSelect',
        method: 'get',
        params: {
          userId: this.userId,
        }
      }).then(res => {

        console.log(res)
        console.log("11111111111111111111111111111111111")
        if(res.data.code == 500){
          //没有收藏
          var i;
          for(i=0;i<3;i++){
            this.$message("您还没有收藏文章")
          }
        }
        if(res.data.code == 200){
            this.blogs = res.data.data;
          //   console.log(this.blogs)
          // console.log('111111111')
        }

      })
    },
    beforeCreate() {
      document
              .querySelector('body')
              .setAttribute('style', 'background-color: rgba(0,0,0,0.06)')
    },
    methods: {
      //路由跳转 查看文章
      viewPassage(articleId){
        this.$router.push('/passage/'+articleId)
      },
      // 引入工具方法，转化时间格式
      formTime(time) {
        return parseTime(time)
      }
    },
  }
</script>


<style scoped>
  .card-wrapper{
    margin-top: 7vh;
  }
  .card {
    width: 50vw;
    margin: 0vh 0 10px 25vw;
    border-top: 10px solid rgba(0,0,0,0.06);
    background-color: white;
    border-radius: 12px;
    overflow: auto;
    cursor: pointer;
    transition: all 0.5s;
  }
  .card:hover{
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

  .card .writer, .card .date, .card .divider {
    color: #909399;
  }

  .card .writer {
    font-weight: bold;
  }

  .card .divider {
    margin: 0 16px;
    background-color: #8A919F;
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
    -webkit-line-clamp: 1;
  }


  .card .content {
    font-size: 14px;
    color: #636569;
    -webkit-line-clamp: 2;}

  .card .title, .card .content {
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
  .icon {
    width: 1.5rem;
    height: 1.5rem;
    fill: currentColor;
    overflow: hidden;
  }
</style>
