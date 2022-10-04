<template>
  <div class="wrapper">
    <Header></Header>
    <router-view :key="$route.fullPath"/>
  </div>
</template>

<script>
export default {
  name: "index",
}
</script>

<style scoped>
.wrapper{
}
</style>
