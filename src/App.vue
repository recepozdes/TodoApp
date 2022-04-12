<template>

  <button @click="cikisYap" class="btn btn-danger cikisButonu" v-if="isUser">Çıkış Yap</button>
  <router-view/>
</template>
<script>


export default {
  data() {
    return {
      isUser: false,
    }
  },
 methods: {
    cikisYap() {
      localStorage.removeItem("user");
      this.$router.push("/login");
    },
  },
  computed:  {
    isUserExist() {
      return JSON.parse(localStorage.getItem("user"))?.username ? true : false;
    },
  },
  mounted: function() {
    let isUser = JSON.parse(localStorage.getItem("user"));
    console.log("isUser", isUser);
    if (isUser?.username) {
      this.isUser = true;
    
    }else{
      this.isUser=false;
    }
  },
     
}
</script>

 

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
 
.cikisButonu {
  position: absolute;
  top: 10px;
  right: 10px;
}
</style>
