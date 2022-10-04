<template>
  <div class="container">
    <el-skeleton v-if="show" :rows="15" animated />
    <div v-if="!show" class="card">
      <div class="header">
        <div class="title">草稿箱</div>
        <el-input v-model="input" placeholder="请输入标题关键字"
                  size="medium">
          <el-button @click="search" slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </div>
      <div class="divider"></div>
      <div class="list">
        <div class="list-item" v-for="(item,index) in passages.slice((currentPage-1)*pageSize,currentPage*pageSize)" :key="index">
          <div class="up">
            <div class="left">
              <span class="name" @click="editPassage(item.articleId)">{{item.title}}</span>
            </div>
          </div>
          <div class="down">
            <span class="date">{{item.createTime}}</span>
            <div class="button">
              <button @click="editPassage(item.articleId)" class="button-55" role="button">编辑</button>
              <el-popconfirm title="确认删除这篇草稿吗？"
                             @confirm="deletePassage(item.articleId)">
                <button slot="reference" class="button-55" role="button">删除</button>
              </el-popconfirm>
            </div>
          </div>
        </div>
      </div>
      <div class="pagination">
        <el-pagination
            layout="total, prev, pager, next"
            :total="passages.length"
            :page-size="pageSize"
            @current-change="handleCurrentChange"
            :current-page="currentPage">
        </el-pagination>
      </div>
    </div>
  </div>

</template>

<script>
import {parseTime} from "@/utils/time";

export default {
  name: "Drafts",
  data(){
    return {
      show: true,
      userId: '',
      passages: [],

      //搜索
      input: '',
      //分页
      currentPage: 1,
      pageSize: 6
    }
  },
  methods: {
    //分页
    handleCurrentChange(val){
      this.currentPage = val;
    },
    search(){
      this.$axios({
        url: '/create/searchDraft',
        method: 'get',
        params: {
          title: this.input,
          userId: this.userId
        }
      }).then(res => {
        this.passages = res.data.data;
        let passages = this.passages;
        passages.forEach(item=>{
          item.createTime = parseTime(item.createTime);
        })
        this.passages = passages;
      })
    },
    deletePassage(id){
      this.$axios({
        url: '/create/deleteDraft',
        method: 'get',
        params: {
          articleId: id
        }
      }).then(res => {
        //删除后重新加载资源
        this.initPassage(this.userId)
      })

    },


    initPassage(userId){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getDrafts',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;
          this.passages = data;
          let passages = this.passages;
          passages.forEach(item=>{
            item.createTime = parseTime(item.createTime);
          })
          this.passages = passages;
          this.show = false
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },
    editPassage(id){
      this.$router.push('/draft/'+id);
    },

  },
  mounted() {
    this.userId = this.$initUser()
    this.initPassage(this.userId)
  }
}
</script>

<style scoped lang="scss">
.container{
  height: 82vh;
}
.card{
  background-color: #fff;
  border-radius: 12px;
  height: 80vh;

  .header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 20px;
    align-items: center;
    .title {
      font-size: larger;
    }
    .el-input{
      width: 50vh;
    }
  }
  .divider{
    background-color: #cbccce;
    height: 1px;
    margin: 0 20px;
  }
  .list{
    .list-item{
      padding: 7px 20px;
      .up{
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 3px;
        .left{
          display: flex;
          align-self: center;
          .el-tag{
            margin-left: 14px;
          }
          .name:hover{
            color: #66B1FF;
            cursor: pointer;
          }
        }

      }
      .down{
        display: flex;
        justify-content: space-between;
        align-items: center;
        .date{
          font-size: 15px;
          color: #646975;
        }
        .button-55{
          margin-left: 12px;
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
    }
    .list-item:hover{
      background-color: #f9f9f9;
    }
  }
  .pagination{
    display: flex;
    justify-content: flex-end;
    margin-top: 24px;
    padding-bottom: 24px;
  }
}


/* CSS */
.button-55 {
  align-self: center;
  background-color: #fff;
  background-image: none;
  background-position: 0 90%;
  background-repeat: repeat no-repeat;
  background-size: 4px 3px;
  border-style: solid;
  border-width: 2px;
  box-shadow: rgba(0, 0, 0, .2) 15px 28px 25px -18px;
  box-sizing: border-box;
  color: #41403e;
  cursor: pointer;
  display: inline-block;
  font-family: Neucha, sans-serif;
  font-size: small;
  line-height: 23px;
  outline: none;
  padding: .1rem .8rem;
  text-decoration: none;
  transition: all 235ms ease-in-out;
  border-bottom-left-radius: 15px 255px;
  border-bottom-right-radius: 225px 15px;
  border-top-left-radius: 255px 15px;
  border-top-right-radius: 15px 225px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-55:hover {
  box-shadow: rgba(0, 0, 0, .3) 2px 8px 8px -5px;
  transform: translate3d(0, 2px, 0);
}

.button-55:focus {
  box-shadow: rgba(0, 0, 0, .3) 2px 8px 4px -6px;
}
</style>
