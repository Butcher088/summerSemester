<template>
  <div>
    <el-skeleton v-if="show" :rows="25" animated />
    <template v-if="!show">
      <div class="card">
        <div class="header">
          <div class="title">数据概览</div>
          <div class="more" @click="route('/statistics/content')">查看更多></div>
        </div>
        <div class="divider"></div>
        <div class="grid-container">
          <div class="item" v-for="(item,index) in array" :key="index">
            <div class="title">{{ item.title }}</div>
            <div class="num">{{total[item.prop]}}</div>
            <div class="today">
              <span class="today-title">今日</span>
              <span class="today-num">{{today[item.prop]}}</span>
            </div>
          </div>
        </div>
      </div>
      <div class="card">
        <div class="header">
          <div class="title">近期发布</div>
          <div class="more" @click="route('/statistics/passageManage')">查看更多></div>

        </div>
        <div class="divider"></div>
        <div class="list">
          <div class="list-item" v-for="(item,index) in recentPassage" :key="index">
            <div class="up">
              <span class="name" @click="route('/passage/'+item.articleId)">{{item.title}}</span>
              <span class="date">{{item.createTime}}</span>
            </div>
            <div class="info">
              <span>点赞{{item.likeCount}}</span>
              <span class="point">·</span>
              <span>评论{{item.collectCount}}</span>
            </div>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
import {parseTime} from "@/utils/time";

export default {
  name: "Main",
  data(){
    return {
      //骨架的显示
      show: true,
      total: {
        passage: '',
        follow: '',
        like: '',
        comment: '',
        favourite: '',
        views: ''
      },
      today: {
        passage: '',
        follow: '',
        like: '',
        comment: '',
        favourite: '',
        views: ''
      },
      array: [
        {
          title: '文章总数',
          prop: 'passage'
        },
        {
          title: '获赞总数',
          prop: 'like'
        },
        {
          title: '评论总数',
          prop: 'comment'
        },
        {
          title: '收藏总数',
          prop: 'favourite'
        },
        {
          title: '粉丝总数',
          prop: 'follow'
        },{
          title: '阅读总数',
          prop: 'views'
        }
      ],
      recentPassage: [],
      //静态的userid
      userId: 3
    }
  },
  methods: {
    route(path){
      this.$router.push(path)
    },
    initPassage(userId){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getArticleByUser',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;
          this.total.passage = data[0].length;
          this.today.passage = data[1].length;
          if(this.total.passage<=5){
            this.recentPassage = data[0]
          }else {
            this.recentPassage = data[0].slice(0,5);
          }
          let recentPassage = this.recentPassage
          for (let i=0; i<recentPassage.length; i++){
            recentPassage[i].createTime = parseTime(recentPassage[i].createTime)
          }
          this.recentPassage = recentPassage
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },
    initLike(userId){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getLike',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;
          this.total.like = data[0].length;
          this.today.like = data[1].length;
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },
    initComment(userId){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getComment',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;
          this.total.comment = data[0].length;
          this.today.comment = data[1].length;
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },
    initCollect(userId){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getCollect',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;
          this.total.favourite = data[0].length;
          this.today.favourite = data[1].length;
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },
    initFans(userId){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getFans',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;
          this.total.follow = data[0].length;
          this.today.follow = data[1].length;
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },
    initViews(userId){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getClick',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;
          this.total.views = data[0].length;
          this.today.views = data[1].length;
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },


    initAllData(userId){
      Promise.all([
          this.initPassage(userId),
          this.initLike(userId),
          this.initComment(userId),
          this.initCollect(userId),
          this.initFans(userId),
          this.initViews(userId)
      ])
          .then(res => {
        console.log("请求数据成功");
        this.show = false;
      })

    }
  },
  mounted() {
    this.userId = this.$initUser()
    this.initAllData(this.userId)

  }

}
</script>

<style scoped lang="scss">
.card{
  background-color: #fff;
  border-radius: 12px;
  margin-bottom: 16px;
  .header{
    display: flex;
    justify-content: space-between;
    padding: 20px;
    .title{
      font-size: larger;
    }
    .more{
      color: #646975;
    }
    .more:hover{
      color: #66B1FF;
      cursor: pointer;
    }
  }
  .divider{
    background-color: #cbccce;
    height: 1px;
    margin: 0 20px;
  }

  .grid-container {
    display: grid;
    grid-template-rows: repeat(2, 1fr);
    grid-template-columns: repeat(3, 1fr);
    padding: 20px;
    grid-gap: 20px;
    grid-auto-flow: row;
    .item {
      display: flex;
      flex-direction: column;
      justify-content: space-evenly;
      height: 18vh;
      background: #f5f6f8;
      border-radius: 12px;
      color: #55555a;
      padding: 0 18px;
      .title{
        font-size: 15px;
        }
      .num{
        font-size: xx-large;
        font-weight: bolder;
        color: black;
      }
      .today{
        font-size: small;
        .today-title{
          margin-right: 10px;
        }
      }
    }
  }


}
.card{
  .list{
    .list-item{
      padding: 7px 20px;
      .up{
        display: flex;
        justify-content: space-between;
        align-items: center;
        .name:hover{
          color: #66B1FF;
          cursor: pointer;
        }
        .date{
          font-size: 15px;
          color: #646975;
        }
      }
      .info{
        font-size: small;
        color: #646975;
        .point{
          font-size: medium;
          font-weight: bolder;
          margin: 0 10px;
        }
      }
    }
    .list-item:hover{
      background-color: #f9f9f9;
    }
  }
}
</style>
