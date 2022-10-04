<template>
  <div class="body">
    <div class="login-box">
      <h2>UPSee</h2>
      <form action="/login">
        <div class="user-box">
          <input type="text" name="userName" v-model="userName" />
          <label>用户名：</label>
        </div>
        <div class="user-box">
          <input type="password" name="passWord" v-model="passWord" />
          <label>密码：</label>
        </div>
      </form>
      <div style="display: flex; justify-content: space-around">
        <button @click="login" class="button-86" role="button">登录</button>
        <button @click="register" class="button-86" role="button">注册</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'login',
  data() {
    return {
      userName: '',
      passWord: '',
    };
  },
  methods: {
    login() {
      console.log('开始登录');
      this.$axios({
        url: '/login',
        method: 'get',
        params: {
          userName: this.userName,
          passWord: this.passWord,
        },
      }).then((res) => {
        console.log('登录结果', res);
        // 将用户信息存入cookie
        var info = res.data.data;
        this.$cookie.set('userId', info.userId);
        this.$cookie.set('userName', info.userName);
        this.$cookie.set('token', info.token);
        this.$router.push('/');
      });
    },
    register() {
      this.$router.push('/register');
    },
  },
};
</script>

<style scoped>
.body {
  background-image: url('../../assets/images/background.png');
  background-repeat: no-repeat;
  background-size: 100%;
  background-position: 0px -50px;
  height: 100%;
  width: 100%;
}

.login-box {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 400px;
  padding: 40px;
  transform: translate(-50%, -50%);
  background: rgba(0, 0, 0, 0.5);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
  border-radius: 12px;
}

.login-box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
}

.login-box .user-box {
  position: relative;
}

.login-box .user-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}

.login-box .user-box label {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: 0.5s;
}

.login-box .user-box input:focus ~ label,
.login-box .user-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #ffffff;
  font-size: 12px;
}

/* CSS */
.button-86 {
  all: unset;
  font-size: 16px;
  background: transparent;
  border: none;
  position: relative;
  color: #f0f0f0;
  cursor: pointer;
  z-index: 1;
  padding: 10px 40px;
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
  background: #1b1e21;
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
