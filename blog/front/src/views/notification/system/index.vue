<template>
  <div>
    <NotifyHeader></NotifyHeader>
    <div class="chat-view">
      <div class="view" v-for="(value, key, index) in list" :key="index">
        <a href="#">
          <img
            :alt="value.formUser"
            src="https://lf3-cdn-tos.bytescm.com/obj/static/xitu_juejin_web/f5188ac005a69d272fc6161b3c521d15.svg"
            class="lazy avatar avatar"
          />
        </a>
        <div class="right">
          <div class="top">
            <div class="content">
              <div class="profile">
                <span class="title">
                  你的
                  <a href="">
                    <a href="">
                      <span class="title-article"> 文章 </span>
                    </a> </a
                  >已被推荐至热门
                </span>
              </div>
              <div class="bottom">
                <span>{{ value.time }}</span>
              </div>
            </div>
            <div class="cover-image-wrap bgc">
              <a href="">
                <div class="cover-image mg">{{ value.title }}</div>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NotifyHeader from '@/components/notify-header.vue';
export default {
  data() {
    return {
      list: [
        {
          formUser: '给me一支烟', // xxx to xxx
          status: 0, // 0评论 1回复
          content: '文章写的太好了',
          url: '/pin/7121524004212441125', // 沸点路由
          date: 1, // 发布时间
          title: '尤雨溪主题演讲《2022 前端生态趋势》全记录',
          isLike: 0, // 0 无 1 有
          time: '1天前',
          img: "https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg"
        },
        {
          formUser: 'butcher', // xxx to xxx
          status: 1, // 0评论 1回复
          content: '获益匪浅', // 评论内容
          url: '/pin/7121524004212441125', // 沸点路由
          date: 1, // 发布时间
          title: '【基于PaddleHub的跳绳AI计数器】智能计数器', // 发布的沸点标题
          isLike: 0, // 0 无 1 有
          time: '1天前',
          img: "https://img0.baidu.com/it/u=3990430052,822773136&fm=253&app=138&size=w931&n=0&f=PNG&fmt=auto?sec=1658941200&t=ee24c8d953457cc54c645e3707dc577b"

        },
        {
          formUser: '渴死的鱼', // xxx to xxx
          status: 1, // 0评论 1回复
          content: '牛的', // 评论内容
          url: '/pin/7121524004212441125', // 沸点路由
          date: 1, // 发布时间
          title: '【基于PaddleHub的跳绳AI计数器】智能计数器', // 发布的沸点标题
          isLike: 0, // 0 无 1 有
          time: '1天前',
          img: 'https://p26-passport.byteacctimg.com/img/user-avatar/894557c5402a2da79a8aec8d5388db44~300x300.image'
        },
        {
          formUser: 'root', // xxx to xxx
          status: 0, // 0评论 1回复
          content: '谢谢大佬', // 评论内容
          url: '/pin/7121524004212441125', // 沸点路由
          date: 1, // 发布时间
          title: '数据库神器之索引', // 发布的沸点标题
          isLike: 0, // 0 无 1 有
          time: '1天前',
          img: 'https://p9-passport.byteacctimg.com/img/user-avatar/1b4b702f74bede46370020d618fdc14b~300x300.image',

        },
        {
          formUser: '小李睡不醒', // xxx to xxx
          status: 0, // 0评论 1回复
          content: '学到了学到了',
          url: '/pin/7121524004212441125', // 沸点路由
          date: 1, // 发布时间
          title: '数据库神器之索引',
          isLike: 0, // 0 无 1 有
          time: '2天前',
          img: 'https://p6-passport.byteacctimg.com/img/user-avatar/a8309022fd7d51c2d9cc30cfa35123d1~300x300.image'

        },
        {
          formUser: '给me一支烟', // xxx to xxx
          status: 1, // 0评论 1回复
          content: '有一个真的好用！！', // 评论内容
          url: '/pin/7121524004212441125', // 沸点路由
          date: 1, // 发布时间
          title: '推荐 6 个实用的 Vue 组件库', // 发布的沸点标题
          isLike: 0, // 0 无 1 有
          time: '2天前',
          img: 'https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg'
        },
        {
          formUser: 'upcfan', // xxx to xxx
          status: 1, // 0评论 1回复
          content: '666', // 评论内容
          url: '/pin/7121524004212441125', // 沸点路由
          date: 1, // 发布时间
          title: '数据库面试题(开发者必看)', // 发布的沸点标题
          isLike: 0, // 0 无 1 有
          time: '2天前',
          img: 'https://our-blog.oss-cn-qingdao.aliyuncs.com/articleImg/1658839491000.jpg'
        },
        {
          formUser: 'upcfan', // xxx to xxx
          status: 0, // 0评论 1回复
          content: '哈哈哈哈可以', // 评论内容
          url: '/pin/7121524004212441125', // 沸点路由
          date: 1, // 发布时间
          title: '12个适合做外包项目的开源后台管理系统', // 发布的沸点标题
          isLike: 0, // 0 无 1 有
          time: '2天前',
          img: 'https://our-blog.oss-cn-qingdao.aliyuncs.com/articleImg/1658839491000.jpg'

        },

      ],
    };
  },
  components: {
    NotifyHeader,
  },
};
</script>

<style lang="scss" scoped>
.chat-view {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.view {
  background-color: white;
  width: 700px;
  box-shadow: 0 1px 2px 0 rgb(0 0 0 / 5%);
  padding: 1.67rem;
  display: flex;
  margin-bottom: 0.67rem;
}
a {
  text-decoration: none;
  cursor: pointer;
  color: #909090;
}
.avatar {
  width: 45px;
  height: 45px;
  border-radius: 100%;
  margin-right: 2rem;
}
.lazy {
  position: relative;
  object-fit: cover;
}
img {
  border-style: none;
}
.right {
  display: flex;
  flex: 1;
  flex-direction: column;
  .top {
    display: flex;
    .content {
      flex: 1;
      margin-right: 1.67rem;
      .profile {
        margin-bottom: 10px;
        .name {
          color: #2e3135;
          font-size: 15px;
          font-weight: 500;
        }
        .title {
          font-size: 15px;
          font-weight: 400;
          .title-article {
            color: #007fff;
          }
          a {
            background-color: transparent;
          }
        }
      }
      .comment {
        font-size: 15px;
        padding: 10px;
        margin-bottom: 10px;
        background-color: #fafbfc;
        border-radius: 3px;
        border: 1px solid #f1f1f2;
        word-break: break-all; // 以字母为换行依据
      }
    }
    .cover-image-wrap {
      width: 65px;
      height: 65px;
      box-sizing: border-box;
      border-radius: 2px;
      word-break: break-all;
      .cover-image {
        object-fit: cover;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        font-size: 14px;
        line-height: 18px;
        font-weight: 400;
        color: #8a9aa9;
      }
    }
  }
  .bottom {
    font-size: 0.99rem;
    display: flex;
    color: #8a9aa9;
    justify-content: space-between;
    .action {
      display: flex;
      align-items: center;
      .action-item {
        display: flex;
        align-items: center;
        margin-left: 20px;
        cursor: pointer;
        .action-text {
          margin-left: 5px;
          font-size: 13px;
        }
      }
      :hover {
        color: #1e80ff;
        path {
          fill: #1e80ff;
        }
      }
    }
  }
}
.bgc {
  background: #f4f4f4;
}
.mg {
  width: calc(100% - 8px);
  margin: 4px;
  height: 56px;
}
</style>
