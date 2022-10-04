<template>
  <div>
    <el-skeleton v-if="show" :rows="20" animated />

      <div v-if="!show" class="card">
        <div class="header">
          <div class="title">数据概览</div>
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
          <div class="title">数据分析</div>
          <div class="buttons">
            <button class="button-48" role="button" @click="selChart='pie'"><span class="text">比例</span></button>
            <button class="button-48" role="button" @click="selChart='line'"><span class="text">趋势</span></button>
          </div>
        </div>
        <div class="divider"></div>
        <div class="charts">
          <div class="pie-chart" v-show="selChart==='pie'" ref="pieChartTotal"></div>
          <div class="pie-chart" v-show="selChart==='pie'" ref="pieChartToday"></div>
          <div class="line-chart" v-show="selChart==='line'" ref="lineChart"></div>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  name: "Content",
  data(){
    return{
      //骨架
      show: true,
      userId: 3,
      //网格数据
      total: {
        passage: 125,
        follow: 25,
        like: 135,
        comment: 465,
        favourite: 412,
        views: 4564
      },
      today: {
        passage: 15,
        follow: 5,
        like: 15,
        comment: 46,
        favourite: 42,
        views: 454
      },
      array: [
        {
          title: '阅读总数',
          prop: 'views'
        },
        {
          title: '收藏总数',
          prop: 'favourite'
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
          title: '文章总数',
          prop: 'passage'
        },
        {
          title: '粉丝总数',
          prop: 'follow'
        },
      ],
      //图表数据
      pieTotal: [
        {
          value: 135,
          name: '喜欢'
        },
        {
          value: 465,
          name: '评论'
        },
        {
          value: 412,
          name: '收藏'
        },
        {
          value: 3544,
          name: '浏览'
        }
      ],
      pieToday: [
        {
          value: 35,
          name: '喜欢'
        },
        {
          value: 65,
          name: '评论'
        },
        {
          value: 12,
          name: '收藏'
        },
        {
          value: 344,
          name: '浏览'
        }
      ],
      lineChart: [
        {
          name: '浏览',
          type: 'line',
          stack: 'Total',
          data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
          name: '收藏',
          type: 'line',
          stack: 'Total',
          data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
          name: '点赞',
          type: 'line',
          stack: 'Total',
          data: [150, 232, 201, 154, 190, 330, 410]
        },
        {
          name: '讨论',
          type: 'line',
          stack: 'Total',
          data: [320, 332, 301, 334, 390, 330, 320]
        },
        {
          name: '文章数',
          type: 'line',
          stack: 'Total',
          data: [820, 932, 901, 934, 1290, 1330, 1320]
        },
        {
          name: '关注数',
          type: 'line',
          stack: 'Total',
          data: [82, 92, 90, 93, 129, 130, 138]
        }
      ],
      dates: [],
      //趋势还是比例
      selChart: 'pie',
      //chart实例
      totalPie: '',
      todayPie: '',
      line: ''
    }
  },
  methods: {
    initPieChartTotal(){
      const chart = this.$refs.pieChartTotal
      if(chart){
        const myChart = this.$echarts.init(chart)
        const option = {
          legend: {
            top: 'bottom'
          },
          title: {
            text: '总计',
            left: 'center',
            top: '5%'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '40',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: this.pieTotal
            }
          ]        };
        myChart.setOption(option)
        window.addEventListener("resize", function() {
          myChart.resize()
        })
        this.totalPie = myChart
      }
      this.$on('hook:destroyed',()=>{
        window.removeEventListener("resize", function() {
          myChart.resize();
        });
      })
    },
    initPieChartToday(){
      const chart = this.$refs.pieChartToday
      if(chart){
        const myChart = this.$echarts.init(chart)
        const option = {
          legend: {
            top: 'bottom'
          },
          title: {
            text: '今日',
            left: 'center',
            top: '5%'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              itemStyle: {
                borderRadius: 10,
                borderColor: '#fff',
                borderWidth: 2
              },
              label: {
                show: false,
                position: 'center'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: '40',
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: this.pieToday
            }
          ]        };
        myChart.setOption(option)
        window.addEventListener("resize", function() {
          myChart.resize()
        })
        this.todayPie = myChart
      }
      this.$on('hook:destroyed',()=>{
        window.removeEventListener("resize", function() {
          myChart.resize();
        });
      })
    },
    initLineChart(){
      const chart = this.$refs.lineChart
      if(chart){
        const myChart = this.$echarts.init(chart)
        const option = {
          title: {
            text: '7日趋势',
            top: '3%'
          },
          tooltip: {
            trigger: 'axis',
          },
          legend: {
            top: '3%'
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: this.dates
          },
          yAxis: {
            type: 'value'
          },
          series: this.lineChart
        };
        myChart.setOption(option)
        window.addEventListener("resize", function() {
          myChart.resize()
        })
        this.line = myChart
      }
      this.$on('hook:destroyed',()=>{
        window.removeEventListener("resize", function() {
          myChart.resize();
        });
      })
    },
    initDates(){
      for (let i=6; i>=0; i--){
        let date = new Date(new Date() - 24*60*60*1000*i)
            .toLocaleDateString().split('/')
            .map(item=>{if (item<10){return '0'+ item}else {return item}}).join('-')
        this.dates.push(date)
      }
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
          this.recentPassage = data[0].slice(0,5);
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
          this.pieTotal[0].value = this.total.like
          this.pieToday[0].value = this.today.like
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
          this.pieTotal[1].value = this.total.comment
          this.pieToday[1].value = this.today.comment
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
          this.pieTotal[2].value = this.total.favourite
          this.pieToday[2].value = this.today.favourite
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
          this.pieTotal[3].value = this.total.views
          this.pieToday[3].value = this.today.views
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })
    },
    initLineData(userId){
      return new Promise((resolve, reject) => {
      this.$axios({
        url: '/create/getContentLineChart',
        method: "get",
        params: {
          userId: userId
        }
      }).then(res => {
        let data = res.data.data
        //console.log(data)
        for (let i=0; i<6; i++){
          for (let j=0; j<7; j++){
            this.lineChart[i].data[j] = data[6-j][i]
          }
        }
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
        this.initViews(userId),
        this.initLineData(userId)
      ])
          .then(res => {
            console.log("请求数据成功");
            this.show = false;

            this.initPieChartTotal()
            this.initPieChartToday()
            this.initLineChart()
            this.initDates()

          })
    }
  },
  mounted() {

    this.userId = this.$initUser()
    this.initAllData(this.userId)

  },

  //切换tag时候展示动画
  watch: {
    selChart: function (val) {
      if (val === 'pie' ){
        this.todayPie.clear()
        this.totalPie.clear()
        this.initPieChartTotal()
        this.initPieChartToday()
      }else {
        this.line.clear()
        this.initLineChart()
      }
    }

  }
}
</script>

<style scoped lang="scss">
.card{
  background-color: #fff;
  border-radius: 12px;
  margin-bottom: 16px;
  .charts{
    display: flex;
    justify-content: center;
    .pie-chart{
      height: 50vh;
      width: 50vw;
    }
    .line-chart{
      height: 50vh;
      width: 50vw;
    }
  }
  .header{
    display: flex;
    justify-content: space-between;
    padding: 20px;
    align-items: center;
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



/* CSS */
.button-48 {
  margin-left: 20px;
  border-radius: 8px;
  appearance: none;
  background-color: #FFFFFF;
  box-sizing: border-box;
  color: #000000;
  cursor: pointer;
  display: inline-block;
  font-family: Clarkson,Helvetica,sans-serif;
  font-size: 15px;
  letter-spacing: 0;
  line-height: 1em;
  opacity: 1;
  outline: 0;
  padding: .5em 2em;
  position: relative;
  text-align: center;
  text-decoration: none;
  text-rendering: geometricprecision;
  text-transform: uppercase;
  transition: opacity 300ms cubic-bezier(.694, 0, 0.335, 1),background-color 100ms cubic-bezier(.694, 0, 0.335, 1),color 100ms cubic-bezier(.694, 0, 0.335, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  vertical-align: baseline;
  white-space: nowrap;
}

.button-48:before {
  animation: opacityFallbackOut .5s step-end forwards;
  backface-visibility: hidden;
  background-color: #EBEBEB;
  clip-path: polygon(-1% 0, 0 0, -25% 100%, -1% 100%);
  content: "";
  height: 100%;
  left: 0;
  position: absolute;
  top: 0;
  transform: translateZ(0);
  transition: clip-path .5s cubic-bezier(.165, 0.84, 0.44, 1), -webkit-clip-path .5s cubic-bezier(.165, 0.84, 0.44, 1);
  width: 100%;
  border-radius: 8px;
}

.button-48:hover:before {
  animation: opacityFallbackIn 0s step-start forwards;
  clip-path: polygon(0 0, 101% 0, 101% 101%, 0 101%);
}

.button-48:after {
  background-color: #FFFFFF;
}

.button-48 span {
  z-index: 1;
  position: relative;
}

</style>

