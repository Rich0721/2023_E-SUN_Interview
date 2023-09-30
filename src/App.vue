<template>
  <!--<HelloWorld msg="Welcome to Your Vue.js App" />-->
  <seatInfroFront />
</template>

<script>
//import HelloWorld from './components/HelloWorld.vue'
import seatInfroFront from './components/seatInfroFront.vue';
export default {
  name: 'App',
  components: {
    seatInfroFront
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
</style>
