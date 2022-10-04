<template>
  <div class="container">
    <Header></Header>
    <div class="admin">
      <adminSideBar></adminSideBar>
      <div class="router">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import adminSideBar from './adminSideBar.vue';
export default {
  data() {
    return {};
  },
  components: {
    adminSideBar,
  },
};
</script>

<style>
.container {
  height: 100%;
  margin: 0%;
  padding: 0%;
}
.admin {
  margin-top: 4rem;
  display: flex;
  flex-direction: row;
  height: 100%;
}
.router {
  width: calc(100% - 100px);
}
</style>
