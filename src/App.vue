<template>
  <div id="app">
    <el-container>
      <el-main :style="selfstyle0" class="background">
        <router-view v-if="isRouterAlive"></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>

export default {
  name: 'App',
  provide(){
    return{
      reload:this.reload
    }
  },
  data(){
    return{
      selfstyle0: {
        padding:0,
      },
      isRouterAlive:true
    }
  },
  methods:{
    reload(){
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
  .background{
    background-image: url('https://photoos.macsc.com/171110/Books_2/yV17rmBTOi.jpg');
    background-repeat: no-repeat;
    background-size: 100% 100%;
  }
</style>
