<template>
  <main class="form-signin">
    <form @submit.prevent="createNewUser">
      <h1 class="h3 mb-3 fw-normal">Lütfen Giriş Yapınız</h1>

      <div class="form-floating">
        <input
          type="username"
          class="form-control"
          id="floatingInput"
          placeholder="username"
          v-model="username"
          required
        />
        <label for="floatingInput">Kullanıcı Adınız</label>
      </div>
      <div class="form-floating">
        <input
          type="password"
          class="form-control"
          id="floatingPassword"
          placeholder="Password"
          v-model="password"
          required
        >
        <label for="floatingPassword">Şİfreniz</label>
        
      </div>

      <button
        class="w-100 btn btn-lg btn-primary"
        type="submit"
        
      >
        Giriş Yap
      </button>

      <p class="mt-3">
        Ya da kayıt olmak için  <router-link :to="'/register'">Tıklayınız</router-link>
      </p>
      
      <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
    </form>
  </main>
</template>
<script>
import UserService from "@/service/UserService";
export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  created() {
    let isUser = JSON.parse(localStorage.getItem("user"));
    console.log("isUser", isUser);

    //if (isUser?.username!=undefined || isUser?.username!=null || isUser?.username!="") {
    if (isUser?.username) {
      this.$router.push("/");
    }
  },
  methods: {
    createNewUser() {
      //e.preventDefault();
      console.log(this.username, this.password);
      UserService.login({
        username: this.username,
        password: this.password,
      })
        .then((response) => {
          if(response.data.status=="active"){
            localStorage.setItem("user", JSON.stringify(response.data));
            this.$router.push("/");
          }else{
            alert("Hesabınız aktif değil");
          }
        })
        .catch(() => {
          alert("Yanlış Kullanıcı Adı veya Şifre Girdiniz");
         
        });
    },
  },
};
</script>


<style scoped>
html,
body {
  height: 100%;
}

body {
  display: flex;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="username"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>