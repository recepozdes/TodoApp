<template>
  <button @click="cikisYap" class="btn btn-outline-danger cikisButonu" v-if="isUser">
        <svg
      xmlns="http://www.w3.org/2000/svg"
      width="16"
      height="16"
      fill="currentColor"
      class="bi bi-box-arrow-right"
      viewBox="0 0 16 16"
    >
      <path
        fill-rule="evenodd"
        d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"
      />
      <path
        fill-rule="evenodd"
        d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"
      />
    </svg>
    Çıkış Yap
  </button>
  <router-view />
</template>
<script>
export default {
  data() {
    return {
      isUser: false,
    };
  },
  methods: {
    cikisYap() {
      localStorage.removeItem("user");
      this.$router.push("/login");
    },
  },
  computed: {
    isUserExist() {
      return JSON.parse(localStorage.getItem("user"))?.username ? true : false;
    },
  },
  mounted: function () {
    let isUser = JSON.parse(localStorage.getItem("user"));
    console.log("isUser", isUser);
    if (isUser?.username) {
      this.isUser = true;
    } else {
      this.isUser = false;
    }
  },
};
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
