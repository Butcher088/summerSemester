<template>
  <div class="container comment-list-box" id="comment-box">
    <div class="comment-form focused">
      <div class="header">
        <span class="header-title">评论</span>
      </div>
      <div class="content" v-clickoutside="hideReplyBtn">
        <div class="avatar-box">
          <img
            src="https://p26-passport.byteacctimg.com/img/user-avatar/e964f654e43fde0d98c430bda3189e8b~300x300.image"
            class="lazy avatar"
          />
        </div>
        <div class="form-box">
          <el-input
            class="input"
            ref="input"
            type="textarea"
            placeholder="输入评论（Enter换行）"
            v-model="textarea"
            :autosize="{ minRows: 2 }"
            @focus="showReplyBtn"
          >
          </el-input>
          <div class="action-box" v-show="btnShow">
            <div class="emoji-container">
              <div class="emoji-box">
                <svg
                  t="1658391961172"
                  class="icon"
                  viewBox="0 0 1024 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="2693"
                  width="16"
                  height="16"
                >
                  <path
                    d="M512 64a448 448 0 1 1 0 896A448 448 0 0 1 512 64z m0 64a384 384 0 1 0 0 768A384 384 0 0 0 512 128zM282.624 611.2H351.36c35.52 60.416 82.112 96.128 160.64 96.128 73.92 0 117.888-27.072 155.008-84.928l6.848-11.2h67.52a246.208 246.208 0 0 1-453.76 11.84l-4.992-11.84H351.36z m387.2-251.264a48 48 0 1 1 0 96 48 48 0 0 1 0-96z m-315.2 0a48 48 0 1 1 0 96 48 48 0 0 1 0-96z"
                    p-id="2694"
                  ></path>
                </svg>
                <span>表情</span>
              </div>
              <div class="image-btn">
                <svg
                  t="1658392321361"
                  class="icon"
                  viewBox="0 0 1024 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="3633"
                  width="16"
                  height="16"
                >
                  <path
                    d="M841.71335 65.290005 182.285626 65.290005c-64.511269 0-116.995621 52.484352-116.995621 116.995621L65.290005 841.71335c0 64.511269 52.484352 116.995621 116.995621 116.995621l659.427724 0c64.511269 0 116.995621-52.484352 116.995621-116.995621L958.708971 182.285626C958.708971 117.774357 906.225643 65.290005 841.71335 65.290005zM182.285626 107.833961l659.427724 0c41.051975 0 74.451666 33.398668 74.451666 74.451666l0 136.557142c-150.09446 5.26184-290.370297 66.084091-396.978337 172.692131-49.960879 49.961902-89.841168 107.331517-118.694309 169.625282-83.496669-70.835302-204.372667-75.376735-292.65841-13.617136L107.833961 182.285626C107.833961 141.232628 141.232628 107.833961 182.285626 107.833961zM107.833961 841.71335 107.833961 702.627618c76.54228-74.311473 198.833511-74.234725 275.272437 0.24457-24.303522 65.298192-37.026288 135.112234-37.026288 206.91149 0 2.223644 0.343831 4.366448 0.977257 6.381337L182.285626 916.165016C141.232628 916.165016 107.833961 882.766348 107.833961 841.71335zM841.71335 916.165016 387.646807 916.165016c0.633427-2.01489 0.977257-4.157693 0.977257-6.381337 0-146.71755 57.053414-284.572244 160.647817-388.166647 98.570993-98.570993 228.166583-154.963351 366.894158-160.204725L916.166039 841.71335C916.165016 882.766348 882.766348 916.165016 841.71335 916.165016z"
                    p-id="3634"
                  ></path>
                  <path
                    d="M312.397986 413.458683c60.8376 0 110.332874-49.494251 110.332874-110.332874s-49.494251-110.332874-110.332874-110.332874-110.332874 49.494251-110.332874 110.332874S251.559363 413.458683 312.397986 413.458683zM312.397986 235.337913c37.378306 0 67.788919 30.40959 67.788919 67.788919s-30.40959 67.788919-67.788919 67.788919-67.788919-30.40959-67.788919-67.788919S275.018657 235.337913 312.397986 235.337913z"
                    p-id="3635"
                  ></path>
                </svg>
                <span>图片</span>
              </div>
            </div>
            <div class="submit">
              <span class="font">Ctrl + Enter</span>
              <el-button
                type="primary"
                class="submit-btn"
                @click="submitMyComment"
                >发表评论</el-button
              >
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="comment-container">
      <div class="image-viewer-box">
        <div class="title">
          热门评论
          <svg
            t="1658397183709"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="4625"
            width="22"
            height="22"
            style="margin-left: 8px"
          >
            <path
              d="M336 972.8c-60.8-128-28.8-201.6 19.2-268.8 51.2-76.8 64-150.4 64-150.4s41.6 51.2 25.6 134.4c70.4-80 83.2-208 73.6-256 160 112 230.4 358.4 137.6 537.6 492.8-281.6 121.6-700.8 57.6-745.6 22.4 48 25.6 128-19.2 166.4-73.6-281.6-256-336-256-336 22.4 144-76.8 300.8-172.8 419.2-3.2-57.6-6.4-96-38.4-153.6-6.4 105.6-86.4 188.8-108.8 294.4C89.6 758.4 140.8 860.8 336 972.8L336 972.8z"
              p-id="4626"
              fill="#d81e06"
            ></path>
          </svg>
        </div>
      </div>
      <div class="list" v-for="(item, j) in comments" :key="j">
        <div class="comment">
          <div class="user-popover">
            <div class="user-link" @click="route(item.from_id)">
              <img class="lazy avatar" :src="item.img" />
            </div>
          </div>
          <div class="content-box">
            <div class="comment-main">
              <span class="delete" v-show="false">删除</span>
              <div class="user-box">
                <div class="user-popver">
                  <a href="" class="username">
                    <span class="name" style="max-width: 128px">{{
                      item.from_name
                    }}</span>
                  </a>
                </div>
                <span class="position">{{ item.position }}</span>
                <time class="time">{{ item.time }}</time>
              </div>
              <div class="content">{{ item.content }}</div>
              <div class="limit-btn" style="display: none">展开</div>
              <div class="limit-btn" style="display: none">收起</div>
              <div class="action-box">
                <div
                  class="item"
                  @click="giveLike(item, j)"
                  v-if="item.isYouLike === 0"
                >
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
                  <span>{{ item.like === 0 ? '点赞' : item.like }}</span>
                </div>
                <div class="item" @click="concelLike(item, j, i)" v-else>
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
                      fill="#1E80FF"
                    ></path>
                  </svg>
                  <span style="color: #1e80ff">{{ item.like }}</span>
                </div>
                <div class="item" @click="item.show = !item.show">
                  <div v-if="!item.show">
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
                    <span>{{
                      item.child === null ? '评论' : item.child.length
                    }}</span>
                  </div>
                  <div v-else>
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
                        fill="#1E80FF"
                      ></path>
                    </svg>
                    <span style="color: #1e80ff">取消回复</span>
                  </div>
                </div>
              </div>
            </div>
            <submit
              v-show="item.show"
              :item="item"
              :articleId="id"
              :myId="myId"
              @transfer="getComment"
            ></submit>
            <div class="subcomment-wrapper" v-show="item.child != null">
              <div
                class="sub-comment"
                :style="
                  item.father_id != comments[j].comment_id || i != 0
                    ? 'margin-top: 2rem'
                    : ''
                "
                v-for="(item, i) in item.child"
                :key="i+10"
              >
                <div class="user-popover">
                  <div @click="route(item.from_id)" class="user-link">
                    <img :src="item.img" class="avatar lazy" />
                  </div>
                </div>
                <div class="content-box">
                  <div class="content-wrapper">
                    <span class="delete" v-show="false">删除</span>
                    <div class="user-box">
                      <div class="user-popover">
                        <a href="" class="username">
                          <span class="name">{{ item.from_name }}</span>
                        </a>
                      </div>
                      <span class="author-badge-text" style="display: none"
                        >(作者)</span
                      >
                      <div
                        class="rely-box"
                        v-show="item.father_id != comments[j].comment_id"
                      >
                        <span>回复</span>
                        <div class="user-popover">
                          <a
                            href=""
                            style="max-width: 10em"
                            class="repliedname"
                          >
                            {{ item.father_name }}</a
                          >
                        </div>
                      </div>
                      <time class="time">{{ item.time }}</time>
                    </div>
                    <div class="content">{{ item.content }}</div>
                    <div class="limit-btn" style="display: none">展开</div>
                    <div class="lilmit-btn" style="display: none">收起</div>
                    <div
                      class="parent-wrapper"
                      v-show="item.father_id != comments[j].comment_id"
                    >
                      <div class="parent-content">
                        {{ item.father_content }}
                      </div>
                    </div>
                    <div class="action-box">
                      <div
                        class="item"
                        @click="giveLike(item, j, i)"
                        v-if="item.isYouLike === 0"
                      >
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
                        <span>{{ item.like === 0 ? '点赞' : item.like }}</span>
                      </div>
                      <div class="item" @click="concelLike(item, j, i)" v-else>
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
                            fill="#1E80FF"
                          ></path>
                        </svg>
                        <span style="color: #1e80ff">{{ item.like }}</span>
                      </div>
                      <div class="item" @click="item.show = !item.show">
                        <div v-if="!item.show">
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
                          <span>回复</span>
                        </div>
                        <div v-else>
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
                              fill="#1E80FF"
                            ></path>
                          </svg>
                          <span style="color: #1e80ff">取消回复</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <submit
                    class="myPanel"
                    v-show="item.show"
                    :item="item"
                    :articleId="id"
                    :myId="myId"
                    @transfer="getComment"
                  ></submit>
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
import submit from '@/components/submit.vue';
import clickoutside from 'element-ui/src/utils/clickoutside';
import { parseTime } from '@/utils/time';
export default {
  name: 'comment',
  props: ['id'],
  data() {
    return {
      myId: 1,
      currentUser: '',
      textarea: '',
      textarea1: '',
      btnShow: false,
      index: '0',
      replyComment: '',
      myName: 'Lana Del Rey',
      position: 'cv高级工程师', // 职业
      myHeader:
        'https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg',
      to: '',
      toId: -1,
      comments: [
        {
          comment_id: 1,
          content: '你好呀',
          from_id: 3,
          status: 1,
          time: '2019-09-16 18:43:00',
          like: 11,
          article_id: 1,
          father_id: null,
          father_name: null,
          father_content: null,
          root_id: null,
          child: [
            {
              comment_id: 2,
              content: '你好',
              from_id: 1,
              status: 1,
              time: '2019-09-16 18:43:00',
              like: 11,
              article_id: 1,
              father_id: 1,
              father_name: '杰夫瑞',
              father_content: '你好呀',
              root_id: 1,
              child: null,
              img: 'https://ae01.alicdn.com/kf/Hd60a3f7c06fd47ae85624badd32ce54dv.jpg',
              from_name: 'root',
              position: 'cv工程师',
            },
            {
              comment_id: 3,
              content: '好',
              from_id: 3,
              status: 1,
              time: '2019-09-16 18:43:00',
              like: 11,
              article_id: 1,
              father_id: 2,
              father_name: 'root',
              father_content: '你好',
              root_id: 1,
              child: null,
              img: 'https://p3-passport.byteacctimg.com/img/user-avatar/42af7b201c410d60470537f85814455d~300x300.image',
              from_name: '杰夫瑞',
              position: 'cv工程师',
            },
          ],
          img: 'https://p3-passport.byteacctimg.com/img/user-avatar/42af7b201c410d60470537f85814455d~300x300.image',
          from_name: '杰夫瑞',
          position: 'cv工程师',
        },
        {
          comment_id: 4,
          content: '哦',
          from_id: 3,
          status: 1,
          time: '2019-09-16 18:43:00',
          like: 11,
          article_id: 1,
          father_id: null,
          father_name: null,
          father_content: null,
          root_id: null,
          child: null,
          img: 'https://p3-passport.byteacctimg.com/img/user-avatar/42af7b201c410d60470537f85814455d~300x300.image',
          from_name: '杰夫瑞',
          position: 'cv工程师',
        },
      ],
    };
  },
  methods: {
    //提供给父组件的方法，输入框聚焦
    inputFocus(){
      this.$refs.input.focus();
    },
    //跳转到用户主页
    route(id){
      this.$router.push('/user/'+id);
    },

    async parentReply() {
      await setTimeout(() => {
        this.list[0].show = true;
        console.log('触发回复');
      }, 500);
    },
    showReplyBtn() {
      this.btnShow = true;
    },
    hideReplyBtn() {
      //   console.log('失焦', blur);
      this.btnShow = false;
    },
    outsideHide(...args) {
      console.log('outsideHide执行了', args);
      if (args.length === 1) {
        this.list[args[0]].show = false;
      } else {
        this.list[args[0]].childList[args[1]].show = false;
      }
    },
    submitMyComment() {
      //   console.log('textarea', this.textarea);
      //   console.log('文章id', this.id);
      //   console.log('用户id', this.myId);
      //   console.log('当前时间', parseTime(new Date()));
      this.$axios({
        url: '/comment/submit',
        method: 'post',
        data: {
          from_id: this.myId,
          article_id: this.id,
          time: new Date(),
          status: 1,
          content: this.textarea,
        },
      }).then((res) => {
        this.textarea = '';
        this.btnShow = false;
        console.log(res.data);
        if (res.data != null) {
          this.$message.success('评论成功');
          this.getComment();
          this.$parent.commentCountChange(1);
        } else {
          this.$message.error('失败');
        }
      });
    },
    getComment() {
      this.$axios({
        url: '/comment/' + this.id,
        methods: 'GET',
        params: {
          myId: this.myId,
        },
      }).then((res) => {
        //   console.log('comments', res.data);
        var comments = res.data;
        comments.forEach((element) => {
          //   console.log('time', this.dateStr(element.time));
          element.time = this.dateStr(element.time);
          element.show = false; // 评论是否展开
          element.textareaValue = ''; // 评论的内容
          if (element.child != null) {
            element.child.forEach((item) => {
              //   console.log('time', this.dateStr(item.time));
              item.time = this.dateStr(item.time);
              item.show = false;
              item.textareaValue = '';
            });
          }
        });
        this.comments = comments;
        console.log('comments', comments);
      });
    },
    // 点赞
    giveLike(item, j, i) {
      //   console.log('几排', j);
      //   console.log('几列', i);
      //   console.log('点赞的列', item);

      this.$axios({
        url: '/comment/giveLike',
        method: 'post',
        data: {
          userId: this.myId,
          articleId: this.id,
          time: new Date(),
          status: 1,
          commentId: item.comment_id,
          likedId: item.from_id,
        },
      }).then((res) => {
        console.log(res.data);
        if (i != undefined) {
          this.comments[j].child[i].like += 1;
          this.comments[j].child[i].isYouLike = 1;
        } else {
          this.comments[j].like += 1;
          this.comments[j].isYouLike = 1;
        }
      });
    },

    //取消点赞
    concelLike(item, j, i) {
      this.$axios({
        url: '/comment/cancelLike',
        method: 'post',
        data: {
          userId: this.myId,
          articleId: this.id,
          time: new Date(),
          commentId: item.comment_id,
          likedId: item.from_id,
        },
      }).then((res) => {
        console.log(res.data);
        if (i != undefined) {
          this.comments[j].child[i].like -= 1;
          this.comments[j].child[i].isYouLike = 0;
        } else {
          this.comments[j].like -= 1;
          this.comments[j].isYouLike = 0;
        }
      });
    },
    dateStr(date) {
      date = new Date(parseTime(date));
      //   console.log('date', date);
      //获取js 时间戳
      var time = new Date().getTime();
      //   console.log('time时间戳', time);
      //去掉 js 时间戳后三位，与php 时间戳保持一致
      time = parseInt((time - date) / 1000);
      //存储转换值
      var s;
      if (time < 60 * 10) {
        //十分钟内`
        return '刚刚';
      } else if (time < 60 * 60 && time >= 60 * 10) {
        //超过十分钟少于1小时
        s = Math.floor(time / 60);
        return s + '分钟前';
      } else if (time < 60 * 60 * 24 && time >= 60 * 60) {
        //超过1小时少于24小时
        s = Math.floor(time / 60 / 60);
        return s + '小时前';
      } else if (time < 60 * 60 * 24 * 30 && time >= 60 * 60 * 24) {
        //超过1天少于30天内
        s = Math.floor(time / 60 / 60 / 24);
        return s + '天前';
      } else {
        //超过30天ddd
        // var date = new Date(parseInt(date));
        return (
          date.getFullYear() +
          '/' +
          (date.getMonth() + 1) +
          '/' +
          date.getDate()
        );
      }
    },
  },
  components: {
    submit,
  },
  directives: {
    clickoutside,
  },
  created() {
    this.myId = this.$cookie.get('userId');
    // 获取用户信息
    this.$axios({
      url: '/comment/user/' + this.myId,
      methods: 'get',
    }).then((res) => {
      console.log('正在登录的用户', res.data.data);
      this.currentUser = res.data.data;
    });
    this.getComment();
    console.log('子组件接收的参数', this.id);
  },
};
</script>

<style lang="less" scoped>
.comment-list-box {
  border-radius: 12px;
  background-color: #fff;
  padding-left: 2.67rem;
  padding-right: 2.67rem;
  box-sizing: border-box;
  max-width: 1140px;
  padding-bottom: 2rem;
}
.comment-form {
  display: flex;
  position: relative;
  padding-top: 2rem;
  border-radius: 2rem;
  flex-direction: column;
  .header {
    margin-bottom: 2rem;
    .header-title {
      font-size: 22px;
      line-height: 30px;
      font-weight: 600;
      color: #252933;
    }
  }
  .content {
    display: flex;
    align-items: flex-start;
    .form-box {
      flex: 1 1 auto;
      position: relative;
      display: flex;
      flex-direction: column;
      .input {
        color: #252933;
        min-height: 64px;
        box-sizing: border-box;
        font-size: 14px;
        resize: both;
        outline: none;
        line-height: 22px;
      }
      .action-box {
        margin-top: 8px;
        display: flex;
        justify-content: space-between;
        .image-btn {
          margin-left: 24px;
        }
        .emoji-container {
          display: flex;
          flex-direction: row;
          .icon {
            margin-right: 4px;
            vertical-align: middle;
          }
        }
        .font {
          font-size: 14px;
          line-height: 22px;
          color: #86909c;
          margin-right: 16px;
          letter-spacing: 0.2px;
        }
      }
    }
  }
}
.comment-container {
  padding-top: 32px;
  .image-viewer-box {
    .title {
      position: relative;
      padding-bottom: 8px;
      font-size: 22px;
      color: #252933;
      width: 100%;
      display: flex;
      align-items: center;
      line-height: 30px;
      font-weight: 600;
    }
  }
  a {
    text-decoration: none;
    cursor: pointer;
    // color: #909090;
    color: #2e3135;
    font-size: 18px;
  }
  .comment {
    display: flex;
    padding: 16px 0;

    .content-box {
      flex: 1 1 auto;
      margin-left: 16px;
      .comment-main {
        position: relative;
        .delete {
          position: absolute;
          right: 0;
          bottom: 0;
          font-size: 14px;
          line-height: 22px;
          color: #f53f3f;
          cursor: pointer;
          z-index: 9;
        }
        .user-box {
          display: flex;
          align-items: center;
          .user-popover {
            display: inline;
            .username {
              display: flex;
              align-items: center;
              font-size: 22px;
              .name {
                font-weight: 500;
                font-size: 15px;
                line-height: 26px;
                max-width: 128px;
              }
            }
          }
          .position {
            font-size: 14px;
            color: #8a919f;
            line-height: 22px;
            margin-left: 13px;
            max-width: 160px;
          }
          .time {
            margin-left: auto;
            font-size: 14px;
            color: #8a919f;
            line-height: 22px;
          }
        }
        .content {
          display: -webkit-box;
          overflow: hidden;
          text-overflow: ellipsis;
          -webkit-box-orient: vertical;
          font-weight: 400;
          font-size: 14px;
          line-height: 2rem;
          color: #515767;
          margin-top: 8px;
          -webkit-line-clamp: 6;
        }
        .limit-btn {
          margin-top: 8px;
          cursor: pointer;
          font-size: 14px;
          line-height: 22px;
          color: #1e80ff;
        }
        .action-box {
          display: flex;
          align-items: center;
          .item {
            margin-right: 16px;
            .icon {
              margin-right: 4px;
              vertical-align: middle;
            }
          }
        }
      }
      .subcomment-wrapper {
        margin-top: 16px;
        display: flex;
        padding: 16px;
        background: rgba(247, 248, 250, 0.7);
        border-radius: 12px;
        flex-direction: column;
        .sub-comment {
          width: 100%;
          display: flex;
          border-radius: 12px;
          .content-box {
            flex: 1 1 auto;
            margin-left: 12px;
            .content-wrapper {
              position: relative;
              .delete {
                position: absolute;
                right: 0;
                bottom: 0;
                font-size: 14px;
                line-height: 22px;
                color: #f53f3f;
                cursor: pointer;
                z-index: 9;
                text-align: right;
              }
              .user-box {
                display: flex;
                align-items: center;
                position: relative;
                .user-popover {
                  display: inline;
                  .username {
                    display: flex;
                    align-items: center;
                    font-size: 1.25rem;
                    font-weight: 600;
                    .name {
                      font-weight: 500;
                      font-size: 15px;
                      line-height: 26px;
                      max-width: 128px;
                    }
                  }
                }
                .rely-box {
                  display: flex;
                  align-items: center;
                  span {
                    padding: 0 12px;
                    font-size: 14px;
                    line-height: 22px;
                    color: #8a919f;
                  }
                  .repliedname {
                    font-weight: 500;
                    font-size: 15px;
                    color: #252933;
                    display: block;
                    line-height: 26px;
                    white-space: nowrap;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    word-break: break-all;
                  }
                }
                .position {
                  font-size: 14px;
                  color: #8a919f;
                  line-height: 22px;
                  margin-left: 13px;
                  max-width: 160px;
                }
                .time {
                  margin-left: auto;
                  font-size: 14px;
                  color: #8a919f;
                  line-height: 22px;
                }
              }
              .author-badge-text {
                font-size: 14px;
                color: #8a919f;
                margin-left: 5px;
                line-height: 22px;
              }
              .content {
                display: -webkit-box;
                overflow: hidden;
                text-overflow: ellipsis;
                -webkit-box-orient: vertical;
                font-weight: 400;
                font-size: 14px;
                line-height: 2rem;
                color: #515767;
                margin-top: 8px;
                -webkit-line-clamp: 6;
              }
              .limit-btn {
                margin-top: 8px;
                cursor: pointer;
                font-size: 14px;
                line-height: 22px;
                color: #1e80ff;
              }
              .parent-wrapper {
                display: flex;
                background: #f2f3f5;
                border: 1px solid #e4e6eb;
                padding: 0 12px;
                line-height: 36px;
                height: 36px;
                font-size: 14px;
                color: #8a919f;
                margin-top: 8px;
                border-radius: 7px;
              }
              .action-box {
                display: flex;
                align-items: center;
                margin-top: 8px;
                .item {
                  margin-right: 16px;
                  .icon {
                    margin-right: 4px;
                    vertical-align: middle;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
.user-popover {
  //   height: 33px;
  display: inline;
  .user-link {
    flex: 0 0 auto;
    cursor: pointer;
  }
}
.avatar-box {
  margin-right: 16px;
}
.avatar {
  width: 45px;
  height: 45px;
  border-radius: 100%;
}
.lazy {
  position: relative;
  object-fit: cover;
}
</style>
