<template>
  <main class="form-signin">
    <form @submit.prevent="createNewUser">
      <h1 class="h3 mb-3 fw-normal">Kayıt Formu</h1>
      <div class="alert alert-secondary" role="alert" v-if="message.length!=0" >
        {{ message }}
      </div>

      <div class="form-floating">
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="fistName"
          v-model="firstName"
          required
        />
        <label for="floatingInput">Adınız</label>
      </div>
      <div class="form-floating">
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="lastName"
          v-model="lastName"
          required
        />
        <label for="floatingInput">Soyadınız</label>
      </div>
      <div class="form-floating">
        <input
          type=" email"
          class="form-control"
          id="floatingInput"
          placeholder="email"
          v-model="email"
          required
        />
        <label for="floatingInput">E-posta Adresiniz</label>
      </div>
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
        />
        <label for="floatingPassword">Şifreniz</label>
      </div>
      <div class="form-floating">
        <input
          type="password"
          class="form-control"
          id="floatingPassword"
          placeholder="controlPassword"
          v-model="controlPassword"
          required
        />
        <label for="floatingPassword">Şifrenizi Tekrarlayınız</label>
      </div>

      <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">
        Kayıt Ol
      </button>

      <p class="mt-3">
        Giriş Sayfasına Gitmek İçin
        <router-link :to="'/login'">Giriş Yap</router-link>'a tıklayın
      </p>

      <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
    </form>
  </main>
</template>

<script>
import UserService from "@/service/UserService";
export default {
  name: "RegisterView",
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      username: "",
      password: "",
      controlPassword: "",
      message: "",
    };
  },
  methods: {
    createNewUser() {
      if (this.password != this.controlPassword) {
        alert("Şifreler uyuşmuyor");
        return;
      }
      UserService.createUser({
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        username: this.username,
        password: this.password,
      })
        .then((response) => {
          console.log("response", response);
          this.message = "Kayıt Başarılı. Lütfen Onaylanmasını Bekleyiniz";
          //this.$router.push("/login");
        })
        .catch((error) => {
          this.message = "Kayıt Başarısız";
          console.log("error", error);
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
  margin-bottom: -1px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>