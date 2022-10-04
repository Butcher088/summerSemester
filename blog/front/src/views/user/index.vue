<template>
    <div class="all">
        <Header></Header>
      <el-skeleton v-if="loading" style="margin: 5rem 80px" :rows="25" animated :loading="true"/>
      <div v-if="!loading" class="body">

            <div class="div-top">
                <div class="div-user">
                    <div class="div-user-left">
                        <img class="user-avatar" :src="form.img">
                        <div class="div-user-text">
                            <span class="div-user-text-top"><b>{{form.name}}</b></span>
                        </div>
                    </div>
                    <el-button v-if="loginUserId===showUserId" plain class="div-user-button" @click="dialogFormVisible = true">编辑个人资料</el-button>
                    <el-dialog title="编辑个人资料" :visible.sync="dialogFormVisible" center>
                        <el-form :model="form">

                            <el-form-item label="用户名：" prop="name">
                                <el-input v-model="form.name" placeholder="请输入用户名" name="name"></el-input>
                            </el-form-item>

                            <el-form-item label="密码：" prop="password">
                                <el-input v-model="form.password" placeholder="请输入密码" name="password"></el-input>
                            </el-form-item>

                            <el-form-item label="邮箱：" prop="email">
                                <el-input v-model="form.email" placeholder="" name="email"></el-input>
                            </el-form-item>

                            <el-form-item label="电话号码：" prop="phone">
                                <el-input v-model="form.phone" placeholder="" name="phone"></el-input>
                            </el-form-item>

                            <el-form-item label="头像上传：" prop="img">
                                <el-upload
                                        class="avatar-uploader"
                                        action=""
                                        :show-file-list="false"
                                        :http-request="Upload"
                                        :before-upload="beforeAvatarUpload">
                                    <div>
                                        <img v-if="form.img" :src="form.img" style="height: 50px">
                                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                    </div>
                                    <div slot="tip">只能上传jpg文件，且不超过2M</div>
                                </el-upload>
                            </el-form-item>
                        </el-form>
                        <div slot="footer">
                            <el-button @click="dialogFormVisible = false">取 消</el-button>
                            <el-button type="primary" @click="save">保 存</el-button>
                        </div>
                    </el-dialog>
                </div>
            </div>

            <div class="div-bottom">
                <div class="div-title">
                    <div class="div-title-article">文章</div>
                    <div class="div-title-button">
                        <el-button type="text">最新</el-button>
                    </div>
                </div>


                <div class="div-list">
                    <div class="card-wrapper">
                        <div class="card" @click="viewPassage(item.articleId)" v-for="(item,index1) in this.blogs" :key="index1">
                            <div class="header">
                                <img class="head-img" :src="form.img">
                                <span class="writer">{{ form.name }}</span>
                                <span class="divider"></span>
                                <span class="date">{{formTime(item.createTime)}}</span>
                                <span class="divider"></span>
                                <el-tag v-for="(tag,index2) in item.labels" :key="index2" type="info" size="mini">{{ tag }}
                                </el-tag>
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
                                        <span class="num">{{ item.collectCount }}</span>
                                        <i class="iconfont icon-dianzan1"></i>
                                        <span class="num">{{ item.likeCount }}</span>
                                    </div>
                                </div>
                                <div>
                                    <img v-if="item.cover" :src="item.cover">
                                    <img v-else :src='require("../../assets/images/aaa.png")'>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>






            </div>
        </div>
    </div>


</template>

<script>
    import {client} from "@/utils/alioss";
    //iconfont
    import '@/assets/icon/iconfont.css'
    import '@/assets/icon/iconfont.js'
    import {parseTime} from "@/utils/time";
    import axios from 'axios';

    export default {
        name: "index",
        data() {
            return {
              loading: true,
                dialogFormVisible: false,
                form: {
                    name: '',
                    password: '',
                    email: '',
                    phone: '',
                    img: ''
                },
                blogs:[],
                // isEdit:-1,
                loginUserId: '',
                showUserId: '',
            }
        },
        created() {
            //在这里修改可以显示个人主页或者别人的主页
            this.loginUserId = this.$cookie.get('userId');
            this.showUserId = this.$route.params.userId;
            //console.log('userId'+this.showUserId)

            this.$axios({
                url: '/findById',
                method: 'get',
                params: {
                    userId: this.showUserId,
                }
            }).then(res => {
                // console.log(res.data.data);
                var message = res.data.data;
                this.form.name = message.userName;
                this.form.password = message.passWord;
                this.form.email = message.email;
                this.form.phone = message.phone;
                this.form.img = message.img;
                // console.log(message)
            })

            this.$axios({
                url: '/create/getArticleByUser',
                method: 'get',
                params: {
                    userId: this.showUserId,
                }
            }).then(res => {
                this.blogs = res.data.data[0];
                this.loading = false;
                //console.log(res.data.data)
            })
            console.log(this.blogs)
            if(!this.blogs){
                var i;
                for(i=0;i<3;i++){
                    this.$message('您还没有发布文章')
                }
            }

        },

        beforeCreate() {
            document
                .querySelector('body')
                .setAttribute('style', 'background-color: #f6f8fc')
        },
        methods: {
            //路由跳转 查看文章
            viewPassage(articleId){
                this.$router.push('/passage/'+articleId)
            },
            // 引入工具方法，转化时间格式
            formTime(time) {
                return parseTime(time)
            },
            save() {
                if (this.form.name == '' || this.form.password == '') {
                    this.$message("用户名或者密码不能为空")
                } else {
                    var e = 0;
                    var p = 0;
                    if (this.form.email != '') {
                        var re = /^\w+@[a-z0-9]+\.[a-z]{2,4}$/;
                        if (re.test(this.form.email)) {
                            e = 1;
                        }else{
                            var c;
                            for(c=0;c<3;c++){
                                this.$message('邮箱格式错误');
                            }
                        }
                    } else {
                        e = 1;
                    }
                    if (this.form.phone != '') {
                        var reg = /^1[3456789]\d{9}$/;
                        if (reg.test(this.form.phone)) {
                            //对了
                            p = 1;
                        }else{
                            var c;
                            for(c=0;c<3;c++){
                                this.$message('电话号码格式错误');
                            }
                        }
                    } else {
                        p = 1;
                    }
                    if(e == 1 && p == 1){
                        this.$axios({
                            url: '/editProfile',
                            method: 'get',
                            params: {
                                userId: this.loginUserId,
                                userName: this.form.name,
                                passWord: this.form.password,
                                email: this.form.email,
                                phone: this.form.phone,
                                img: this.form.img,
                            }
                        }).then(res => {
                            var i;
                            for (i = 0; i < 3; i++) {
                                this.$message({
                                    message: '保存成功',
                                    type: 'success'
                                });
                            }
                        });
                    }
                }
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');       //  !!!!!!!!!!!!!!!!!!!
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            //上传图片到阿里云
            Upload(file) {
                var fileName = 'articleImg/' + `${Date.parse(new Date())}` + '.jpg';  //定义唯一的文件名
                //定义唯一的文件名，打印出来的uid其实就是时间戳
                client().multipartUpload(fileName, file.file).then(
                    result => {
                        // console.log("res:",result.res.requestUrls[0])
                        this.form.img = 'https://our-blog.oss-cn-qingdao.aliyuncs.com/' + fileName;
                        console.log(this.form.img)
                    })
            }
        },
      watch: {
        $route: {
          handler(newVal, oldVal) {
            //判断newVal有没有值监听路由变化
            if(newVal !== oldVal){
              location.reload()
            }

          },
        }
      },
    }
</script>

<style scoped>
    .body {
        display: flex;
        flex-direction: column;
        margin-top: 9vh;
        justify-content: center;
        align-items: center;
    }

    .div-top {
        display: flex;
        height: 20vh;
        width: 50vw;
    }

    .div-user {
        border-top: 1vh solid rgba(0, 0, 0, 0.06);
        display: flex;
        background-color: white;
        justify-content: space-between;
        width: 46vw;
      border-radius: 12px;
    }

    .div-user-left {
        display: flex;
        margin-top: 2vh;
        padding-left: 1vw
    }

    .user-avatar {
        background-color: #f9f9f9;
        border-radius: 50%;
        border: 2px solid rgba(0,0,0,0.1);
        height: 12vh;
        width: 12vh;
        padding: 12px;
    }

    .div-user-text {
        display: flex;
        flex-direction: column;
        margin: -3px 0px 0px 20px;
    }

    .div-user-text-top {
        color: #000;
        font-size: 35px;
        margin-bottom: 10px;
        margin-top: 12px;
    }

    .div-user-text-middle {
        color: #7B68EE;
        font-size: medium;
        margin-bottom: 12px;
    }

    .div-user-text-bottom {
        color: #7B68EE;
        font-size: medium;
        margin-bottom: 10px;
    }

    .div-user-button {
        border: solid 1px;
        margin: 14vh 2vh 2vh 0vh;
        padding-top: 0vh;
        padding-bottom: 0vh;
        font-size: medium;
        color: #66B1FF;
    }

    .div-bottom {
        display: flex;
        flex-direction: column;
        width: 50vw;

    }

    .div-title {
        display: flex;
        justify-content: space-between;
        width: 46vw;
        margin-top: 10px;
        margin-right: 20px;
        background-color: white;
        border-bottom: 3px solid rgba(0, 0, 0, 0.06);
      border-radius: 12px;

    }

    .div-title-article {
        padding: 10px 0px 0px 30px;
    }

    .div-title-button {
        padding: 0px 20px 0px 0px;
    }

    .card {
        width: 46vw;
        margin: 0px 0 10px 0vw;
        border-top: 0px solid rgba(0,0,0,0.06);
        background-color: white;
        overflow: auto;
        cursor: pointer;
        transition: all 0.5s;
       border-radius: 12px;

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
