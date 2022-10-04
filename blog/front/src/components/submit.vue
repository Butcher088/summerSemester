<template>
  <div class="form-box">
    <el-input
      class="input"
      type="textarea"
      placeholder="回复xxx"
      v-model="textarea"
      :autosize="{ minRows: 2 }"
    >
    </el-input>
    <div class="action-box">
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
        <el-button type="primary" class="submit-btn" @click="commentTo"
          >发表</el-button
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['item', 'articleId', 'myId'],
  data() {
    return {
      textarea: '',
    };
  },
  methods: {
    commentTo() {
      // console.log('组件textarea', this.textarea);
      // console.log('组件文章id', this.articleId);
      // console.log('组件用户id', this.myId);
      // console.log('当前时间', new Date());
      // console.log('内容', this.textarea);
      // console.log('root_id', this.item.root_id);
      // console.log('father_id', this.item.father_id);
      if (this.myId == this.item.from_id) {
        this.$message.error('自己不能给自己评论');
        this.textarea = '';
        this.$emit('transfer');
      } else {
        this.$axios({
          url: '/comment/submit',
          method: 'post',
          data: {
            from_id: this.myId,
            article_id: this.articleId,
            time: new Date(),
            status: 1,
            content: this.textarea,
            root_id:
              this.item.root_id === null
                ? this.item.comment_id
                : this.item.root_id,
            father_id: this.item.comment_id,
          },
        }).then((res) => {
          // console.log(res.data);
          this.textarea = '';
          if (res.data != null) {
            this.$message.success('评论成功');
            this.$emit('transfer');
            this.$parent.$parent.commentCountChange(1);
          } else {
            this.$message.error('失败');
          }
        });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.form-box {
  flex: 1 1 auto;
  position: relative;
  display: flex;
  flex-direction: column;
  margin-top: 12px;
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
</style>
