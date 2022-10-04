<template>
  <div>
    <div class="header">
      <div class="inputDeep" style="width: 70%; margin-left: 1%">
        <el-input
          v-model="article.title"
          placeholder="输入文章标题..."
          class="inputHeader"
        ></el-input>
      </div>

      <div class="right-div">
        <el-button type="text" class="right-inner-div" @click="save"
          >保存草稿</el-button
        >
        <el-button size="small" class="btn1" @click="route('statistics/drafts')"
          >草稿箱</el-button
        >
        <el-popover
          ref="popoverRef"
          placement="bottom"
          width="200px"
          trigger="click"
        >
          <div class="publish-div">发布文章</div>
          <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="100px"
            class="demo-ruleForm"
            style="width: 400px"
          >
            <el-form-item label="文章简介" prop="summary">
              <el-input
                type="textarea"
                :row="5"
                v-model="ruleForm.summary"
                style="width: 220px"
                :autosize="{ minRows: 1, maxRows: 8 }"
              >
              </el-input>
            </el-form-item>
            <el-form-item label="文章封面" prop="cover">
              <el-upload
                class="avatar-uploader"
                action=""
                :show-file-list="false"
                :http-request="Upload"
                :before-upload="beforeAvatarUpload"
              >
                <div class="border">
                  <img v-if="cover" :src="cover" class="avatar" />
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </div>
                <div slot="tip" class="el-upload__tip">
                  只能上传jpg文件，且不超过2M
                </div>
              </el-upload>
            </el-form-item>
            <el-form-item label="标签" prop="label">
              <el-checkbox-group v-model="ruleForm.label">
                <el-checkbox
                  v-for="(item, index) in labels"
                  :key="index"
                  :label="item.name"
                  name="type"
                ></el-checkbox>
              </el-checkbox-group>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')"
                >确认并发布</el-button
              >
              <el-button @click="cancel" style="margin-left: 50px"
                >取消</el-button
              >
            </el-form-item>
          </el-form>

          <el-button type="primary" size="small" class="btn2" slot="reference"
            >发布文章</el-button
          >
        </el-popover>

        <el-popover placement="bottom" trigger="click" width="150">
          <el-menu>
            <el-menu-item @click="route('statistics/drafts')">
              <i class="el-icon-tickets"></i>
              <span>草稿箱</span>
            </el-menu-item>
            <el-menu-item @click="route('favourites')">
              <i class="el-icon-star-off"></i>
              <span>我的收藏</span>
            </el-menu-item>
            <el-menu-item @click="route('user')">
              <i class="el-icon-user"></i>
              <span>个人主页</span>
            </el-menu-item>
            <el-menu-item @click="logout">
              <i class="el-icon-circle-close"></i>
              <span>退出登录</span>
            </el-menu-item>
          </el-menu>
          <img slot="reference" class="head-img" :src="headImg" />
        </el-popover>
      </div>
    </div>

    <mavon-editor
      v-model="article.mdContent"
      ref="md"
      @change="change"
      @imgAdd="imgAdd"
      class="editor"
    />
  </div>
</template>

<script>
// 引入工具类
import { parseTime } from '@/utils/time';
import qs from 'qs';
import { client } from '@/utils/alioss';

export default {
  name: 'index',
  data() {
    return {
      headImg: '',
      userId: '',
      article: {
        id: '-1',
        title: '',
        htmlContent: '',
        mdContent: '',
        label: '',
      },
      labels: ['前端', '算法'],
      ruleForm: {
        summary: '',
        region: '',
        label: [],
      },
      rules: {
        summary: [{ required: true, message: '请输入', trigger: 'blur' }],
        label: [
          {
            type: 'array',
            required: true,
            message: '请至少选择一个标签',
            trigger: 'change',
          },
        ],
      },
      cover: '',
      // 请求头
      // headers: {
      //   'Content-Type': 'image/jpg'
      // }
    };
  },
  created() {
    this.$axios({
      url: '/queryAllLabel',
      method: 'get',
      params: {},
    }).then((res) => {
      console.log(res.data.data);
      this.labels = res.data.data;
    });
    this.$axios({
      url: '/create/getUser',
      method: 'get',
      params: {
        userId: this.$cookie.get("userId")
      }
    }).then(res => {
      let data = res.data.data[0]
      this.headImg = data.img
    });
  },
  methods: {
    //退出登录
    logout() {
      var that = this;
      this.$axios({
        url: '/userLayout',
        method: 'get',
        params: {
          userId: that.$cookie.get('userId'),
        },
      }).then((res) => {
        console.log(res.data, 'logout');
        if (res.data.code === 200) {
          Cookies.remove('token');
          Cookies.remove('userId');
          Cookies.remove('userName');
          location.href('/login');
        }
      });
    },
    // 封装路由跳转方法
    route(path) {
      this.$router.push({ path: path });
    },
    change(value, render) {
      // console.log(this.article.content);
      // console.log(value) // md内容
      // console.log(render) // html内容
      // 两者都要存储
      this.article.htmlContent = render;
      this.article.mdContent = value;
    },
    imgAdd(pos, $file) {
      var fileName = 'articleImg/' + `${Date.parse(new Date())}` + '.jpg'; //定义唯一的文件名
      //定义唯一的文件名，打印出来的uid其实就是时间戳
      client()
        .multipartUpload(fileName, $file)
        .then((result) => {
          var url = result.res.requestUrls[0];
          // url 后加此句限制大小
          // '?x-oss-process=image/resize,h_600,w_800'
          console.log('res:', url);
          this.$refs.md.$img2Url(pos, url);
        });
    },
    submitForm(formName) {
      console.log('article');
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios({
            url: '/addArticle',
            method: 'post',
             // 加权限验证后无法传递此数据类型
            data: qs.stringify(
              {
                articleId: '0',
                userId: this.$cookie.get('userId'),
                createTime: parseTime(new Date()),
                htmlContent: this.article.htmlContent,
                title: this.article.title,
                mdContent: this.article.mdContent,
                labels: this.ruleForm.label,
                summary: this.ruleForm.summary,
                cover: this.cover,
              },
              { indices: false }
            ),
          }).then((res) => {
            this.$message({
              message: '发布成功',
              type: 'success',
            });
            this.$refs.popoverRef.doClose();
            setTimeout(() => {
              //代码
              this.route('/statistics');
            }, 1000);
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 保存草稿
    save() {
      this.$axios({
        url: '/addDraft',
        method: 'post',

        data: qs.stringify(
          {
            articleId: '0',
            userId: this.$cookie.get('userId'),
            createTime: parseTime(new Date()),
            htmlContent: this.article.htmlContent,
            title: this.article.title,
            mdContent: this.article.mdContent,
            // labels: this.ruleForm.label,
            // summary: this.ruleForm.summary,
            // cover:this.cover,
          },
          { indices: false }
        ),
      }).then((res) => {
        this.$message({
          message: '保存成功',
          type: 'success',
        });
        setTimeout(() => {
          //代码
          this.route('/statistics');
        }, 1000);
      });
    },
    cancel() {
      // 根据ref找到节点
      this.$refs.popoverRef.doClose();
    },
    // handleAvatarSuccess(res, file) {
    //   this.imageUrl = URL.createObjectURL(file.raw);
    // },
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
      var fileName = 'articleImg/' + `${Date.parse(new Date())}` + '.jpg'; //定义唯一的文件名
      //定义唯一的文件名，打印出来的uid其实就是时间戳
      client()
        .multipartUpload(fileName, file.file)
        .then((result) => {
          // console.log("res:",result.res.requestUrls[0])
          this.cover =
            'https://our-blog.oss-cn-qingdao.aliyuncs.com/' + fileName;
          // console.log(this.cover)
        });
    },

    //检查是否是继续编辑草稿内容
    handleDraft(){
      if (this.$route.name === 'editDraft'){
        //获取文章的id
        this.article.id = this.$route.params.articleId;
        //请求获取文章的内容
        return new Promise((resolve, reject) => {
          this.$axios({
            url: '/create/getDraftArticle',
            method: 'get',
            params: {
              articleId: this.article.id
            }
          }).then(res => {
            //console.log(res.data)
            let data = res.data.data;
            this.article.title = data.title;
            this.article.mdContent = data.mdContent;
            this.ruleForm.summary = data.summary;
            this.cover = data.cover;
            this.$message({
              message: '已恢复草稿内容',
              type: "success"
            });
            resolve(res);
          }).catch(e =>{
            reject(e)
          })
        })

      }

    },
    //加载头像
    initUser(){
      this.$axios({
        url: 'create/getUser',
        params: {
          userId: this.userId,
        },
        method: 'get',
      }).then((res) => {
        this.headImg = res.data.data[0].img;
      });
    },

  },


  mounted() {
    this.userId = this.$cookie.get("userId");
    this.handleDraft();
    this.initUser();
  },
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50px;
  background: white;
}

/* 利用穿透，设置input边框隐藏 */
.inputDeep >>> .el-input__inner {
  border: 0;
}

.inputHeader {
  border: none;
  font-size: 24px;
}

.head-img {
  border-radius: 50%;
  height: 2.2rem;
  width: 2.2rem;
  cursor: pointer;
}

.right-div {
  display: flex;
  margin-right: 20px;
  height: 35px;
}

.right-inner-div {
  margin-right: 15px;
  align-self: center;
  /*color: #8a919f;*/
  font-size: 16px;
}

.btn1 {
  margin-right: 15px;
  align-self: center;
}

.btn2 {
  margin-right: 20px;
  align-self: center;
}
.editor {
  height: calc(100vh - 5.334rem);
  width: 100%;
}

.publish-div {
  font-size: 20px;
  color: black;
  margin-bottom: 10px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.border {
  border: 1px dashed #d9d9d9;
}
</style>
