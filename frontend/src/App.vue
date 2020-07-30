<template>
  <v-app id="app" theme="dark">
    <h1><b>Register/Login</b></h1>

    <keep-alive>
      <router-view class="view one" name="default"></router-view> 
    </keep-alive>

  </v-app>
</template>

<script>
import Vue from 'vue'
export default {
  name: 'app',
  data () {
    return {
    }
  },
  methods: {
  },
  created() {
    this.$eventHub.$on("change", this.test);
  },
  computed: {

  }
}
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
}

#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
     color: #42b983;
    }
  }
}
</style>
