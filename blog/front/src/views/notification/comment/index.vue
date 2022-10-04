<template>
  <div>
    <NotifyHeader></NotifyHeader>
    <div class="chat-view">
      <div class="view" v-for="(value, key, index) in list" :key="index">
        <a href="#">
          <img
            :alt="value.formUser"
            :src="value.img"
            class="lazy avatar avatar"
          />
        </a>
        <div class="right">
          <div class="top">
            <div class="content">
              <div class="profile">
                <a href="">
                  <span class="name">{{ value.formUser }}</span>
                </a>
                <span v-if="value.status === 0" class="title">
                  评论了你的
                  <a href="">
                    <span class="title-article">文章</span>
                  </a>
                </span>
                <span v-else-if="value.status === 1" class="title">
                  回复了你在
                  <a href="">
                    <a href="">
                      <span class="title-article">文章</span>
                    </a> </a
                  >下的评论
                </span>
              </div>
              <div class="comment">
                {{ value.content }}
              </div>
            </div>
            <div class="cover-image-wrap bgc">
              <a href="">
                <div class="cover-image mg">{{ value.title }}</div>
              </a>
            </div>
          </div>
          <div class="bottom">
            <span>{{value.time}}</span>
            <div class="action">
              <div class="action-item">
                <svg
                  t="1658201008839"
                  class="icon"
                  viewBox="0 0 1024 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="3209"
                  width="16"
                  height="16"
                >
                  <path
                    d="M857.28 344.992h-264.832c12.576-44.256 18.944-83.584 18.944-118.208 0-78.56-71.808-153.792-140.544-143.808-60.608 8.8-89.536 59.904-89.536 125.536v59.296c0 76.064-58.208 140.928-132.224 148.064l-117.728-0.192A67.36 67.36 0 0 0 64 483.04V872c0 37.216 30.144 67.36 67.36 67.36h652.192a102.72 102.72 0 0 0 100.928-83.584l73.728-388.96a102.72 102.72 0 0 0-100.928-121.824zM128 872V483.04c0-1.856 1.504-3.36 3.36-3.36H208v395.68H131.36A3.36 3.36 0 0 1 128 872z m767.328-417.088l-73.728 388.96a38.72 38.72 0 0 1-38.048 31.488H272V476.864a213.312 213.312 0 0 0 173.312-209.088V208.512c0-37.568 12.064-58.912 34.72-62.176 27.04-3.936 67.36 38.336 67.36 80.48 0 37.312-9.504 84-28.864 139.712a32 32 0 0 0 30.24 42.496h308.512a38.72 38.72 0 0 1 38.048 45.888z"
                    p-id="3210"
                    fill="#8a9aa9"
                  ></path>
                </svg>
                <span class="action-text">点赞</span>
              </div>
              <div class="action-item">
                <svg
                  t="1658201465332"
                  class="icon"
                  viewBox="0 0 1099 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="4435"
                  width="25"
                  height="25"
                >
                  <path
                    d="M378.06 436.1c0 19.6 15.5 35.5 34.6 35.5 19.1 0 34.6-15.9 34.6-35.5s-15.5-35.5-34.6-35.5C393.56 400.6 378.06 416.5 378.06 436.1zM731.26 436.1c0-19.6-15.5-35.5-34.6-35.5-19.1 0-34.6 15.9-34.6 35.5s15.5 35.5 34.6 35.5C715.76 471.6 731.26 455.7 731.26 436.1zM772.36 665.9l79.2 0c11.7 0 17.2-8.8 17.2-18.6L868.76 231.7c0-21.3-11.6-34.2-35.3-34.2L269.66 197.5c-17.9 0-26.3 10-26.3 27.2l0 411.9c0 16.3 6.7 29.7 23.9 30l237.8-0.5 202.6 188.6c14.2 11.6 28.1 12.8 39.4 5.8 8.3-5.1 12.6-13.4 12.6-23.7 0-25.7 0-141.1 0-160.9C759.56 669.3 764.66 665.9 772.36 665.9zM703.46 783.7 542.26 627.9c-5.3-5.9-10.5-11.4-22.2-11.4L315.96 616.5c-15.7 0-23.3-7.9-23.3-24.1L292.66 273.6c0-16.2 7.9-25.2 23.6-25.2L797.16 248.4c15.7 0 22.8 7.4 22.8 23.5l0 332.7c0 12.8-4.8 13.5-16.7 13.5l-76.6 0c-10.5 0-14.5 4.7-14.4 13.7 0.2 15 0.1 137.6 0.1 146.4C712.46 789.1 710.86 791.2 703.46 783.7zM520.06 436.1c0 19.6 15.5 35.5 34.6 35.5 19.1 0 34.6-15.9 34.6-35.5s-15.5-35.5-34.6-35.5C535.56 400.6 520.06 416.5 520.06 436.1z"
                    p-id="4436"
                    fill="#8a9aa9"
                  ></path>
                </svg>
                <span class="action-text">回复</span>
              </div>
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
