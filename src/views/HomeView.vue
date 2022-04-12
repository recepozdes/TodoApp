<template>
  <header class="header">
    <form @submit.prevent="addToDo()">
      <h1>Yapılacaklar Listesi</h1>
      <input
        v-model="name"
        class="newName"
        autofocus
        placeholder="Görev Nedir?"
        required
      />
      <br />
      <input
        v-model="description"
        class="newDescription"
        autofocus
        placeholder="Açıklama Nedir?"
        required
      />
      <br />
      <button type="submit" class="btn btn-primary">Ekle</button>
    </form>
  </header>
  <div class="container">
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Görev</th>
          <th scope="col">Açıklama</th>
          <th scope="col" @click="sorting('status')">Durum</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(task, index) in sliceTask"
          :key="task"
          :class="{
            'text-decoration-line-through table-success':
              task.status != 'yapılacak',
          }"
        >
          <th scope="row">
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="flexCheckChecked"
                :checked="task.status != 'yapılacak'"
                :disabled="task.status != 'yapılacak'"
                @click="checked(index, task.id)"
              />
            </div>
          </th>
          <td>{{ task.name }}</td>
          <td>{{ task.description }}</td>
          <td>{{ task.status }}</td>
        </tr>
      </tbody>
    </table>

    <nav aria-label="">
      <ul class="pagination">
        <li class="page-item disabled">
          <a class="page-link">Previous</a>
        </li>
        <span v-for="page in totalPage" :key="page">
          <li 
            class="page-item"
            :class="{active: page == currPage}"
            >
            <span 
              @click="currPage=page"
              class="page-link"> 
              {{page}} </span>
          </li>
        </span>
        <li class="page-item">
          <a class="page-link" href="#">Next</a>
        </li>
      </ul>
    </nav>

  </div>
</template>


<script>
// @ is an alias to /src

import taskService from "@/service/TaskService";

export default {
  name: "HomeView",
  data() {
    return {
      countOfPage: 5,
      currPage: 1,
      totalPage: null,
      name: "",
      description: "",
      tasks: [],
      sliceTask:[],
      sort: {
        name: "desc",
        param: "",
      },
    };
  },
  async created() {
    let isUser = JSON.parse(localStorage.getItem("user"));

    if (!isUser?.username) {
      this.$router.push("/login");
    }

    await taskService.getAll().then((response) => {
      console.log("VERİLER", response.data);
      this.tasks = response.data;
    });

    // pagination total page
    this.totalPage = await Math.ceil(this.tasks.length / this.countOfPage);

    // slice task
    this.sliceTask = this.tasks.slice(
      (this.currPage - 1) * this.countOfPage,
      this.currPage * this.countOfPage
    );
  },
  methods: {
    checked(index, id) {
      taskService.setTaskStatus({ id: id }).then((response) => {
        console.log(response);
      });
      
      let task = this.tasks.filter((task) => task.id == id)[0];
      console.log("123",task);

      this.tasks.filter((task) => task.id == id)[0] = {
        ...task,
        status: task.status == "yapıldı",
      };
      console.log("456",this.tasks);

      let slice_Task = this.sliceTask[index]
      this.sliceTask[index] = {
        ...slice_Task,
        status: slice_Task.status == "yapıldı",
      };
    },
    sorting(param) {
      // table column sorting
      this.tasks.sort((a, b) => {
        if (a[param] < b[param]) {
          return -1;
        }
        if (a[param] > b[param]) {
          return 1;
        }
        return 0;
      });
      if (this.sort.param === param) {
        this.sort.name = this.sort.name === "asc" ? "desc" : "asc";
        if (this.sort.name === "desc") {
          this.tasks.reverse();
        }
      } else {
        this.sort.name = "asc";
        this.sort.param = param;
      }
    },

    addToDo() {
      taskService
        .create({
          name: this.name,
          description: this.description,
          status: "yapılacak",
          username: JSON.parse(localStorage.getItem("user")).username,
        })
        .then((response) => {
          this.tasks.unshift(response.data);
          if (this.currPage == 1) {
            this.sliceTask.unshift(response.data);
          } else {
            this.sliceTask = this.tasks.slice(
              (this.currPage - 1) * this.countOfPage,
              this.currPage * this.countOfPage
            );
          }
          this.name = "";
          this.description = "";
        });
    },
  },
  watch: {
    currPage(newPage) {
      // pagination
      this.sliceTask = this.tasks.slice(
        (newPage - 1) * this.countOfPage,
        newPage * this.countOfPage
      );
    },
  },
};
</script>
