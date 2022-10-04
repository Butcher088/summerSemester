<template>
  <div class="wrapper">
    <Header></Header>
    <el-skeleton v-if="loading" style="margin: 5rem 80px" :rows="25" animated :loading="true"/>
    <div v-if="!loading" class="body">
      <div class="left-wrapper">
        <div class="back-round" @click="like">
          <el-badge :value="article.likeCount" type="info">
            <i class="iconfont icon-dianzan" :style="whetherLike?'color: #FEBF32':''"></i>
          </el-badge>

        </div>
        <div class="back-round" @click="reply">
          <el-badge :value="article.commentCount" type="info">
            <i class="iconfont icon-pinglun"></i>
          </el-badge>
        </div>
        <div class="back-round" @click="collect">
          <i class="iconfont icon-shoucang" :style="whetherCollect?'color: #FEBF32':''"></i>
        </div>
        <el-popover
            placement="right"
            title="微信扫一扫"
            width="150"
            trigger="click">
          <div id="qrcodeCanvas"></div>
          <div class="back-round" slot="reference" @click="weChatQrCode">
            <i class="iconfont icon-zhuanfa"></i>
          </div>
        </el-popover>

        <div class="divider"></div>
        <div class="back-round" @click="dialogFormVisible = true">
          <i class="iconfont icon-jubao"></i>
        </div>
      </div>
      <div class="blog-wrapper">
        <div class="title">
          {{ article.title }}
        </div>
        <div class="writer-info">
          <div class="left">
            <img v-if="!userinfo.img" :src="headImg" />
            <img v-else :src="userinfo.img" />
            <div class="text">
              <div class="name">{{this.userinfo.userName}}</div>
              <div class="blog-info">
                <span class="date">{{ formTime(article.createTime) }}</span>
                <span class="point">·</span>
                <span class="views">阅读：{{ article.clickCount }}</span>
              </div>
            </div>
          </div>
          <button v-if="!whetherFollow" @click="follow"  class="button-86" role="button">+ 关注</button>
          <button v-if="whetherFollow" @click="cancelFollow" class="button-86" role="button">已关注</button>

        </div>
        <div v-html="article.htmlContent" style="margin: 0 25px 0 25px"></div>
        <mycomment :id="passageId"  ref="myComment"></mycomment>
      </div>
      <div :class="fixed ? 'right-wrapper-fixed' : 'right-wrapper'">
        <div class="right">
          <div class="writer-info">
            <div class="up">
              <div>
                <img v-if="!userinfo.img" :src="headImg" />
                <img v-else :src="userinfo.img" />
                <span class="name">{{ userinfo.userName }}</span>
              </div>
              <button v-if="!whetherFollow" @click="follow"  class="button-86" role="button">+</button>
              <button v-if="whetherFollow" @click="cancelFollow" class="button-86" role="button">√</button>
            </div>
            <div class="divider"></div>
            <div class="down">
              <div class="module">
                <div class="i-back">
                  <i class="el-icon-view"></i>
                </div>
                <span class="text">文章被阅读</span>
                <span class="num">{{ userinfo.views }}</span>
              </div>
              <div class="module">
                <div class="i-back">
                  <i class="iconfont icon-dianzan1"></i>
                </div>
                <span class="text">获得点赞</span>
                <span class="num">{{ userinfo.likeNum }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="right">
          <div class="passage-info">
            <div class="title">文章标签</div>
            <div class="divider"></div>
            <div class="tags">
              <el-tag
                v-for="(item, index) in this.tags"
                :key="index"
                size="medium"
              >
                {{ item }}
              </el-tag>
            </div>
          </div>
        </div>
        <div class="right">
          <div class="relative">
            <span class="title">相关文章</span>
            <div class="divider"></div>
            <div class="list">
              <div
                  @click="route('/passage/'+item.articleId)"
                class="list-item"
                v-for="(item, index) in blogRelative"
                :key="index"
              >
                <div class="title">
                  {{ item.title }}
                </div>
                <div class="info">
                  <span>点赞{{ item.likeCount}}</span>
                  <span class="point">·</span>
                  <span>评论{{ item.comment_count }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <el-dialog title="文章举报" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="举报信息" :prop="reportContent" required>
          <el-input
              type="textarea"
              :rows="4"
              placeholder="请输入举报原因"
              v-model="reportContent">
          </el-input>
        </el-form-item>
        <el-form-item label="上传截图" :prop="reportImg" required>
          <el-upload
              class="avatar-uploader"
              action=""
              :show-file-list="false"
              :http-request="Upload"
              :before-upload="beforeAvatarUpload">
            <div class="border">
              <img v-if="reportImg" :src="reportImg" class="avatar ">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </div>
            <div slot="tip" class="el-upload__tip">只能上传jpg文件，且不超过2M</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="confirmReport">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { parseTime } from '@/utils/time';
import mycomment from '../../components/MyComment';
import {client} from "@/utils/alioss";
import QrCodeWithLogo from '../../../node_modules/qr-code-with-logo'
import qs from "qs";

export default {
  name: 'index',
  data() {
    return {
      code: false,
      dialogFormVisible: false,
      reportContent: '',
      reportImg: '',
      userId: '',
      loading: true,
      fixed: false,
      top: '',
      passageId: '',
      article: {},
      userinfo: {
        img: '',
        userName: '',
        views: '',
        likeNum: '',
      },
      tags: [],
      headImg: require('../../assets/images/head-img.png'),
      blog: {
        title: '我裁完兄弟们后，辞职了，转行做了一名小职员',
        content: '',
        tags: ['职场', '金融', '感悟'],
        writer: {
          name: 'TF男孩',
          headImg: require('../../assets/images/head-img.png'),
          likeNum: 575,
          views: 62345,
        },
        date: '2022年06月17日 23:54',
        views: 14181,
        likeNum: 299,
        comNum: 170,
      },
      blogRelative: [
        {
          id: 0,
          title: '大学生没钱怎么办',
          likeNum: 12,
          comNum: 54,
        },
        {
          id: 0,
          title: '大学生没钱怎么办',
          likeNum: 12,
          comNum: 54,
        },
      ],
      //用户是否已经关注作者
      whetherFollow: false,
      //是否收藏
      whetherCollect: false,
      //是否点赞
      whetherLike: false,
    };
  },
  methods: {

    //生成二维码
    weChatQrCode () {
      if(this.code){
        return
      }
      const qrcode = document.getElementById(`qrcodeCanvas`)
      const myCanvas = document.createElement('canvas')
      qrcode.appendChild(myCanvas)
      QrCodeWithLogo.toCanvas({
        canvas: myCanvas,
        content: window.location.href, // 这里的url就是要分享的页面
        width: 140,
        errorCorrectionLevel: 'L',
        borderSize: 0, //计算模式,
        margin: 0,
        // 二维码里放的图标
        logo: {
          src: require('../../assets/images/logo.png')
        }
      })
      this.code = true;
    },

    //上传图片相关
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    // 上传文章封面至阿里云
    Upload(file) {
      var fileName = 'articleImg/' + `${Date.parse(new Date())}`+'.jpg';  //定义唯一的文件名
      //定义唯一的文件名，打印出来的uid其实就是时间戳
      client().multipartUpload(fileName, file.file).then(
          result => {
            // console.log("res:",result.res.requestUrls[0])
            this.reportImg='https://our-blog.oss-cn-qingdao.aliyuncs.com/'+fileName;
            console.log(this.reportImg)
          })
    },
    //举报
    confirmReport(){
      if(this.reportContent === '' || this.reportImg === ''){
        this.$message('请填写必要信息')
        return
      }
      this.$axios({
        url: '/report',
        method: 'post',
        params: {
          userId: this.userId,
          articleId: this.article.articleId,
          reportedId: this.article.userId,
          content: this.reportContent,
          img: this.reportImg
        }
      }).then(res => {
        if(res){
          this.$message({
            message: '举报成功，后台将及时处理',
            type: "success"
          })
          this.dialogFormVisible = false

        }
      })
    },

    //评论数增加或者减少,提供给评论子组件使用
      //  i的值为1或-1
    commentCountChange(i){
      this.article.commentCount = this.article.commentCount + i;
    },

    //通过路由的参数值渲染文章数据
    initPassageById() {
      this.passageId = this.$route.params.passageId;

      this.$axios({
        url: '/getArticleById',
        method: 'get',
        params: {
          articleId: this.passageId,
        },
      }).then(res => {
        this.article = res.data.data
        this.initAuthor(this.article.userId)
        this.initLabel()
        this.initAll()
        this.addViews()

      });
    },
    onScroll() {
      this.top =
        document.documentElement.scrollTop ||
        document.body.scrollTop ||
        window.pageYOffset;
    },
    formTime(time) {
      return parseTime(time);
    },
    // 初始化文章标签
    initLabel() {
      this.$axios({
        url: '/getLabelsById',
        method: 'get',
        params: {
          articleId: this.passageId
        },
      }).then(res => {
        this.tags = res.data.data
        this.initRelative(res.data.data)
      });
    },
    // 初始化相关文章
    initRelative(labels) {
      console.log("tag:",labels)
      this.$axios({
        url: '/getRelative',
        method: 'post',
        data: qs.stringify(
            {
              labels: labels
            },
            { indices: false }
        ),
      }).then(res => {
        this.blogRelative = res.data.data
      });
    },
    //请求作者信息
    initAuthor(userId) {
      this.getUser(userId)
      this.getAllClick(userId)
      this.getAllLike(userId)
    },
    getUser(userId) {
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getUser',
          method: 'get',
          params: {
            userId: userId
          },
        }).then(res => {
          this.userinfo = res.data.data[0]
        });
      })
    },
    getAllClick(userId) {
      return new Promise((resolve,reject) => {
        this.$axios({
          url: '/getAllClick',
          method: 'get',
          params: {
            userId: userId
          },
        }).then(res => {
          this.userinfo.views = res.data.data.length
        });
      })
    },
    getAllLike(userId) {
      return new Promise((resolve,reject) => {
        this.$axios({
          url: '/getAllLike',
          method: 'get',
          params: {
            userId: userId
          },
        }).then(res => {
          this.userinfo.likeNum = res.data.data.length
        });
      })
    },
    //请求评论数
    initCommentNum(){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/getCommentCount',
          method: 'get',
          params: {
            articleId: this.article.articleId
          }
        }).then(res => {
          let data = res.data.data;
          this.article.commentCount = data
          resolve(res);

        }).catch(e =>{
          reject(e)
        })
      })

    },
    //请求点赞数
    initLikeNum(){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/getLikeCount',
          method: 'get',
          params: {
            articleId: this.article.articleId
          }
        }).then(res => {
          let data = res.data.data;
          this.article.likeCount = data
          resolve(res);

        }).catch(e =>{
          reject(e)
        })
      })

    },
    //判断是否关注了用户
    judgeFollow(){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/follow/whetherFollow',
          method: 'get',
          params: {
            userId: this.userId,
            followedId: this.article.userId
          }
        }).then(res => {
          let data = res.data.data;
          if(data === 1){
            this.whetherFollow = true;
          }
          resolve(res);
          //console.log("judge follow")
        }).catch(e =>{
          reject(e)
        })
      })

    },
    //关注
    follow(){
      if(this.userId == this.article.userId){
        this.$message('自己不能关注自己');
        return
      }
      this.$axios({
        url: '/follow/followUser',
        method: 'get',
        params: {
          userId: this.userId,
          followedId: this.article.userId
        }
      }).then(res =>{
        if(res){
          this.$message({
            message: '关注成功',
            type: 'success'
          });
          this.whetherFollow = true;
        }
      })
    },
    //取关
    cancelFollow(){
      this.$axios({
        url: '/follow/cancelFollow',
        method: 'get',
        params: {
          userId: this.userId,
          followedId: this.article.userId
        }
      }).then(res =>{
        if(res){
          this.$message('取消关注');
          this.whetherFollow = false;
        }
      })
    },
    //浏览量增加
    addViews(){
      this.$axios({
        url: 'addViews',
        method: 'get',
        params: {
          articleId: this.article.articleId
        }
      })
    },
    //收藏
    collect(){
      if(!this.whetherCollect){
        this.$axios({
          url: '/collect/collect',
          method: 'get',
          params: {
            userId: this.userId,
            articleId: this.article.articleId,
            collectedId: this.article.userId
          }
        }).then(res =>{
         if(res){
           this.$message({
             message: '收藏成功',
             type: 'success'
           });
           this.whetherCollect = true;
         }
        })
      }else {
        this.cancelCollect()
      }
    },
    //取消收藏
    cancelCollect(){
      this.$axios({
        url: '/collect/cancelCollect',
        method: 'get',
        params: {
          userId: this.userId,
          articleId: this.article.articleId
        }
      }).then(res =>{
        if(res){
          this.$message('取消收藏');
          this.whetherCollect = false;
        }
      })
    },
    //判断是否收藏
    judgeCollect(){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/collect/whetherCollect',
          method: 'get',
          params: {
            userId: this.userId,
            articleId: this.article.articleId
          }
        }).then(res => {
          let data = res.data.data;
          if(data === 1){
            this.whetherCollect = true;
          }
          resolve(res);
          //console.log("judge collect")

        }).catch(e =>{
          reject(e)
        })
      })

    },
    //点赞
    like(){
      if(!this.whetherLike){
        this.$axios({
          url: '/like/like',
          method: 'get',
          params: {
            userId: this.userId,
            articleId: this.article.articleId,
            likedId: this.article.userId
          }
        }).then(res =>{
          if(res){
            this.$message({
              message: '点赞成功',
              type: 'success'
            });
            this.whetherLike = true;
            this.article.likeCount++;
          }
        })
      }else {
        this.cancelLike()
      }
    },
    //取消点赞
    cancelLike(){
      this.$axios({
        url: '/like/cancelLike',
        method: 'get',
        params: {
          userId: this.userId,
          articleId: this.article.articleId
        }
      }).then(res =>{
        if(res){
          this.$message('取消点赞');
          this.whetherLike = false;
          this.article.likeCount--;
        }
      })
    },
    //判断是否点赞
    judgeLike(){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/like/whetherLike',
          method: 'get',
          params: {
            userId: this.userId,
            articleId: this.article.articleId
          }
        }).then(res => {
          let data = res.data.data;
          if(data === 1){
            this.whetherLike = true;
          }
          resolve(res);

        }).catch(e =>{
          reject(e)
        })
      })

    },

    initAll(){
      Promise.all([
          this.judgeFollow(),
          this.judgeCollect(),
          this.judgeLike(),
          this.initCommentNum(),
          this.initLikeNum(),
      ])
          .then(res => {
            console.log("判断数据成功");
            this.loading = false;
          })
    },

    //回复
    reply(){
      this.$refs.myComment.inputFocus();
    },
    //路由跳转
    route(path){
      this.$router.push(path);
    },

  },
  created() {
    this.userId = this.$initUser()
    this.initPassageById();

  },
  mounted() {

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
          this.fixed = true; //隐藏
        } else {
          this.fixed = false; //显示
        }
      }
    },
  },
  components: {
    mycomment,
  },
};
</script>

<style scoped lang="scss">

.body {
  display: flex;
  justify-content: space-between;
  margin-top: 4rem;
  background-color: #f6f8fc;
}

.right {
  padding: 15px;
  background-color: white;
  border-radius: 12px;
  margin-bottom: 12px;
  .divider {
    background-color: #e1e1e1;
    height: 1px;
    margin: 15px 2px;
  }
  .writer-info {
    .up {
      display: flex;
      align-items: center;
      justify-content: space-between;
      img {
        height: 3.5rem;
        width: 3.5rem;
        border-radius: 50%;
        margin-right: 15px;
        vertical-align: middle;
      }
    }
    .down {
      .module {
        display: flex;
        justify-content: flex-start;
        align-items: baseline;
        margin-bottom: 12px;
        color: #56585b;
        .i-back {
          background-color: #e1efff;
          border-radius: 50%;
          height: 20px;
          width: 20px;
          display: flex;
          justify-content: center;
          align-items: center;
          i {
            color: #7bb9ff;
            font-size: 15px;
          }
        }
        .text {
          font-size: 15px;
          margin: 0 10px;
        }
      }
    }
  }
  .passage-info {
    .tags {
      display: flex;
      justify-content: space-evenly;
    }
    .divider {
      margin: 10px 2px;
    }
    .el-tag {
      background-color: white;
    }
  }
  .relative {
    .list-item {
      margin-bottom: 12px;
      .title {
        display: -webkit-box;
        overflow: hidden;
        text-overflow: ellipsis;
        word-break: break-all;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 1;
      }
      .info {
        font-size: 14px;
        color: #8a919f;
        margin: 2px 0;
        .point {
          margin: 0 5px;
          font-weight: bolder;
        }
      }
    }
  }
}

.blog-wrapper {
  width: 50vw;
  height: 300vh;
  margin: 2vh 15vw 0 15vw;
  background-color: white;
  border-radius: 12px;

  .title {
    font-size: xx-large;
    font-weight: bolder;
    margin: 25px;
  }
  .writer-info {
    display: flex;
    justify-content: space-between;

    .left {
      display: flex;
    }
    img {
      height: 2.5rem;
      width: 2.5rem;
      border-radius: 50%;
      margin: 0 10px 0 25px;
    }
    .name {
      margin-top: -2px;
    }
    .blog-info {
      color: #8a919f;
      font-size: 14px;
      margin-top: 4px;
    }
    .point {
      font-weight: bolder;
      margin: 0 10px;
    }
  }
}

.right-wrapper {
  position: fixed;
  top: 5rem;
  right: 15vw;
  width: 17vw;
  transition: all 0.5s;
}
.right-wrapper-fixed {
  position: fixed;
  top: 1rem;
  right: 15vw;
  width: 17vw;
  transition: all 0.5s;
}

.left-wrapper {
  width: 4vw;
  position: fixed;
  left: 9vw;
  top: 20vh;

  .divider {
    height: 1px;
    margin: 0 10px;
    background-color: #e5e5e5;
  }
  i {
    font-size: x-large;
    color: #8a919f;
  }
  i:hover {
    color: #515767;
  }
  .back-round {
    height: 3rem;
    width: 3rem;
    background-color: #fff;
    border-radius: 50%;
    margin: 25px auto;
    display: flex;
    justify-content: center;
    align-items: center;
    box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
    cursor: pointer;
  }
}

::v-deep .el-badge__content--info {
  background-color: #c2c8d1;
  margin-right: -7px;
  margin-top: -5px;
}


.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 150px;
  height: 150px;
  line-height: 150px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.border {border: 1px dashed #d9d9d9;}
.el-upload__tip{
  margin-left: 5vw;
}

/* CSS */
.button-86 {
  all: unset;
  margin-right: 25px;
  font-size: 16px;
  background: transparent;
  border: none;
  position: relative;
  color: #f0f0f0;
  cursor: pointer;
  z-index: 1;
  padding: 5px 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  white-space: nowrap;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-86::after,
.button-86::before {
  content: '';
  position: absolute;
  bottom: 0;
  right: 0;
  z-index: -99999;
  transition: all 0.4s;
}

.button-86::before {
  transform: translate(0%, 0%);
  width: 100%;
  height: 100%;
  background: #53a8ff;
  border-radius: 10px;
}

.button-86::after {
  transform: translate(10px, 10px);
  width: 35px;
  height: 35px;
  background: #ffffff15;
  backdrop-filter: blur(5px);
  -webkit-backdrop-filter: blur(5px);
  border-radius: 50px;
}

.button-86:hover::before {
  transform: translate(5%, 20%);
  width: 110%;
  height: 110%;
}

.button-86:hover::after {
  border-radius: 10px;
  transform: translate(0, 0);
  width: 100%;
  height: 100%;
}

.button-86:active::after {
  transition: 0s;
  transform: translate(0, 5%);
}
</style>
