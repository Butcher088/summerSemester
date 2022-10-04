<template>
  <div>
    <el-skeleton v-if="show" :rows="20" animated />

    <div v-if="!show" class="card">
      <div class="header">
        <el-tabs active-name="first">
          <el-tab-pane label="数据概览" name="first">
            <div class="grid-container">
              <div class="item" v-for="(item,index) in follow" :key="index">
                <div class="title">{{ item.name }}</div>
                <div class="num">{{item.num}}</div>
                <div class="today">
                  <span class="today-title">昨日</span>
                  <span class="today-num">{{item.numYes}}</span>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="关注我的" name="second">
              <div class="list">
                <div class="list-item" v-for="(item,index) in fans" :key="index">
                  <div class="user">
                    <img :src="item.headImg"/>
                    <span class="username">{{item.name}}</span>
                  </div>
                  <div class="button" @click="viewUser(item.id)">
                    <button class="button-86" role="button">查看</button>
                  </div>
                </div>
              </div>
          </el-tab-pane>
          <el-tab-pane label="我的关注" name="third">
            <div class="list">
              <div class="list-item" v-for="(item,index) in follows" :key="index">
                <div class="user">
                  <img :src="item.headImg"/>
                  <span class="username">{{item.name}}</span>
                </div>
                <div class="button" @click="cancelFollow(userId,item.id)">
                  <button class="button-86" role="button">取关</button>
                </div>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
    <div class="card">
      <div class="header">
        <div class="title">数据分析</div>
      </div>
      <div class="divider"></div>
      <div class="charts">
        <div class="line-chart" ref="lineChart"></div>
      </div>
    </div>

  </div>
</template>

<script>
import * as echarts from 'echarts';
import user from "@/views/user";

export default {
  name: "Follow",
  data(){
    return{
      userId: 3,
      show: true,
      follow: [
        {
          name: '我的粉丝',
          num: '',
          numYes: ''
        },
        {
          name: '新增粉丝',
          num: '',
          numYes: ''
        },
        {
          name: '取关粉丝',
          num: '',
          numYes: ''
        },
        {
          name: '我的关注',
          num: '',
          numYes: ''
        },
        {
          name: '新增关注',
          num: '',
          numYes: ''
        },
        {
          name: '取消关注',
          num: '',
          numYes: ''
        },
      ],
      fans: [],
      follows: [],
      lineChart: [
        {
          name: '我的粉丝',
          type: 'line',
          stack: 'Total',
          data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
          name: '新增粉丝',
          type: 'line',
          stack: 'Total',
          data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
          name: '取关粉丝',
          type: 'line',
          stack: 'Total',
          data: [150, 232, 201, 154, 190, 330, 410]
        },
        {
          name: '我关注的',
          type: 'line',
          stack: 'Total',
          data: [320, 332, 301, 334, 390, 330, 320]
        },
        {
          name: '新增关注',
          type: 'line',
          stack: 'Total',
          data: [820, 932, 901, 934, 1290, 1330, 1320]
        },
        {
          name: '取消关注',
          type: 'line',
          stack: 'Total',
          data: [82, 92, 90, 93, 129, 130, 138]
        }
      ],
      dates: [],
    }
  },
  methods: {

    //取消关注
    cancelFollow(userId,followedId){
      this.$axios({
        url: '/follow/cancelFollow',
        method: 'get',
        params: {
          userId: userId,
          followedId: followedId
        }
      }).then(res => {
        this.initFollows(this.userId)
        console.log('取关成功')
      })
    },
    //查看用户主页
    viewUser(userId){
      this.$router.push('/user/'+userId)
    },


    initLineChart(){
      const chart = this.$refs.lineChart
      if(chart){
        const myChart = this.$echarts.init(chart)
        const option = {
          color: ['#80FFA5', '#00DDFF', '#37A2FF', '#FF0087', '#FFBF00','#F35353FF'],
          title: {
            text: '七日趋势',
            top: '3%'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#6a7985'
              }
            },
          },
          grid: {
            left: '2%',
            right: '6%',
            bottom: '5%',
            containLabel: true
          },
          legend: {
            top: '3%'
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          xAxis: [
            {
              type: 'category',
              boundaryGap: false,
              data: this.dates
            }
          ],
          yAxis: [
            {
              type: 'value'
            }
          ],
          series: [
            {
              name: this.lineChart[0].name,
              type: 'line',
              stack: 'Total',
              smooth: true,
              lineStyle: {
                width: 0
              },
              showSymbol: false,
              areaStyle: {
                opacity: 0.8,
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: 'rgb(128, 255, 165)'
                  },
                  {
                    offset: 1,
                    color: 'rgb(1, 191, 236)'
                  }
                ])
              },
              emphasis: {
                focus: 'series'
              },
              data: this.lineChart[0].data
            },
            {
              name: this.lineChart[1].name,
              type: 'line',
              stack: 'Total',
              smooth: true,
              lineStyle: {
                width: 0
              },
              showSymbol: false,
              areaStyle: {
                opacity: 0.8,
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: 'rgb(0, 221, 255)'
                  },
                  {
                    offset: 1,
                    color: 'rgb(77, 119, 255)'
                  }
                ])
              },
              emphasis: {
                focus: 'series'
              },
              data: this.lineChart[1].data
            },
            {
              name: this.lineChart[2].name,
              type: 'line',
              stack: 'Total',
              smooth: true,
              lineStyle: {
                width: 0
              },
              showSymbol: false,
              areaStyle: {
                opacity: 0.8,
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: 'rgb(55, 162, 255)'
                  },
                  {
                    offset: 1,
                    color: 'rgb(116, 21, 219)'
                  }
                ])
              },
              emphasis: {
                focus: 'series'
              },
              data: this.lineChart[2].data
            },
            {
              name: this.lineChart[3].name,
              type: 'line',
              stack: 'Total',
              smooth: true,
              lineStyle: {
                width: 0
              },
              showSymbol: false,
              areaStyle: {
                opacity: 0.8,
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: 'rgb(255, 0, 135)'
                  },
                  {
                    offset: 1,
                    color: 'rgb(135, 0, 157)'
                  }
                ])
              },
              emphasis: {
                focus: 'series'
              },
              data: this.lineChart[3].data
            },
            {
              name: this.lineChart[4].name,
              type: 'line',
              stack: 'Total',
              smooth: true,
              lineStyle: {
                width: 0
              },
              showSymbol: false,
              label: {
                show: true,
                position: 'top'
              },
              areaStyle: {
                opacity: 0.8,
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: 'rgb(255, 191, 0)'
                  },
                  {
                    offset: 1,
                    color: 'rgb(224, 62, 76)'
                  }
                ])
              },
              emphasis: {
                focus: 'series'
              },
              data: this.lineChart[4].data
            },
            {
              name: this.lineChart[5].name,
              type: 'line',
              stack: 'Total',
              smooth: true,
              lineStyle: {
                width: 0
              },
              showSymbol: false,
              label: {
                show: true,
                position: 'top'
              },
              areaStyle: {
                opacity: 0.8,
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: 'rgb(243,83,83)'
                  },
                  {
                    offset: 1,
                    color: 'rgb(250,134,184)'
                  }
                ])
              },
              emphasis: {
                focus: 'series'
              },
              data: this.lineChart[5].data
            }
          ]
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

    initGridData(userId){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getFollowMainData',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;

          this.follow[0].num = data[0][0]
          this.follow[0].numYes = data[0][1]
          this.follow[1].num = data[0][2]
          this.follow[1].numYes = data[0][3]
          this.follow[2].num = data[0][4]
          this.follow[2].numYes = data[0][5]
          this.follow[3].num = data[1][0]
          this.follow[3].numYes = data[1][1]
          this.follow[4].num = data[1][2]
          this.follow[4].numYes = data[1][3]
          this.follow[5].num = data[1][4]
          this.follow[5].numYes = data[1][5]

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
          let data = res.data.data[0];
          let fans = [];
          fans = data.map((item) => {
            return {
              id : item.userId,
              headImg: item.img,
              name: item.userName
            }
          })
          this.fans = fans
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })

    },
    initFollows(userId){
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getFollow',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;
          let follows = [];
          follows = data.map((item) => {
            return {
              id : item.userId,
              headImg: item.img,
              name: item.userName
            }
          })
          this.follows = follows
          resolve(res);
        }).catch(e =>{
          reject(e)
        })
      })

    },
    initLineData(userId) {
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/create/getFollowLineChart',
          method: 'get',
          params: {
            userId: userId
          }
        }).then(res => {
          let data = res.data.data;
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
           this.initGridData(userId),
           this.initFans(userId),
           this.initFollows(userId),
           this.initLineData(userId)
      ])
          .then(res => {
            console.log("请求数据成功");
            //初始化折线图
            this.initDates()
            this.initLineChart()
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
::v-deep .el-tabs__item{
  font-size: large !important;
}
.el-tabs{
  width: 100%
}
::v-deep .el-tabs__nav-wrap::after{
  background-color: #cbccce;
  height: 1px;
}
::v-deep .el-tabs__header{
  margin: 0 0 0 0 !important;
}
.card{
  background-color: #fff;
  border-radius: 12px;
  margin-bottom: 16px;
  transition: all 0.5s;
  .charts{
    display: flex;
    justify-content: center;
    .line-chart{
      height: 50vh;
      width: 50vw;
    }
  }
  .header{
    display: flex;
    justify-content: flex-start;
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
    padding: 20px 0 0 0;
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
  .list{
    overflow-x: hidden;
    overflow-y: auto;
    height: 41.6vh;
    .list-item{
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin: 15px 20px 15px 0;
      .user:hover{
        color: #66B1FF;
        cursor: pointer;
      }
      img{
        height: 2.7rem;
        width: 2.7rem;
        border-radius: 50%;
        vertical-align: middle;
        margin-right: 10px;
      }
    }
    .list-item:hover{
      background-color: #f9f9f9;
      border-radius: 12px;
    }
  }

}
::-webkit-scrollbar{
  background-color: #ffffff;
  width: 10px;
}
::-webkit-scrollbar-thumb{
  border-radius: 10px;
  background-color: #C1C1C1;
  -webkit-box-shadow:inset 0 0 6px rgba(0,0,0,.3);

}


/* CSS */
.button-86 {
  all: unset;
  background: transparent;
  border: none;
  position: relative;
  color: #f0f0f0;
  cursor: pointer;
  z-index: 1;
  padding: 5px 20px;
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
  transition: all .4s;
}

.button-86::before {
  transform: translate(0%, 0%);
  width: 100%;
  height: 100%;
  background: #409EFF;
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

