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
      <br/>
      <input
        v-model="description"
        class="newDescription"
        autofocus
        placeholder="Açıklama Nedir?"
        required
      />
      <br/>
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
        <li class="page-item" :class="{disabled: currentPage == 1}">
          <span
            @click="currentPage != 1 ? currentPage-- : false"
            class="page-link">
            Previous</span>
        </li>
        <span v-for="page in totalPage" :key="page">
          <li
            class="page-item"
            :class="{active: page == currentPage}"
          >
            <span
              @click="currentPage=page"
              class="page-link">
              {{ page }} </span>
          </li>
        </span>
        <li class="page-item" :class="{disabled: currentPage == totalPage}">
          <span
            @click="currentPage != totalPage ? currentPage++ : false"
            class="page-link">
            Next</span>
        </li>
      </ul>
    </nav>
  </div>
</template>


<script>
import taskService from "@/service/TaskService";

export default {
  name: "HomeView",
  data() {
    return {
      countOfPage: 5,
      currentPage: 1,
      totalPage: null,
      name: "",
      description: "",
      tasks: [],
      sliceTask: [],
      sort: {
        name: "desc",
        param: "",
      },
    };
  },
  /**
   * Vue componenti oluşturulduğunda çağrılır.
   * @returns {Promise<void>}
   */
  async created() {
    // kullanıcı var mı?
    let isUser = JSON.parse(localStorage.getItem("user"));
    if (!isUser?.username) {
      this.$router.push("/login");
    }

    // tüm verileri getir ve ilgili değişkene ata
    await taskService.getAll()
      .then((response) => {
        this.tasks = response.data;
      });

    // toplam sayfa sayısı
    this.paginationTotalPage();

    // tabloda gösterilecek veriler
    this.tableSlice();
  },
  methods: {

    /**
     * @description: checkbox yani durumu seçildiyse genel listeyi ve sliceTask'ı güncelle
     * @param index
     * @param id
     * @returns {Promise<void>}
     */
    checked: async function (index, id) {
      await taskService.setTaskStatus({id: id})
        .then(() => {

          this.tasks.find((task) => task.id == id).status = "yapıldı";
          this.sliceTask[index].status = "yapıldı";

        });
    },

    /**
     * sıralama işlemi
     * @param param
     */
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
      // sıralama işlemi sonrası tabloda gösterilecek verileri güncelle
      this.tableSlice();
    },

    /**
     * yeni task ekleme
     */
    addToDo() {
      taskService
        .create({
          name: this.name,
          description: this.description,
          status: "yapılacak",
          username: JSON.parse(localStorage.getItem("user")).username,
        })
        .then((response) => {
          this.tasks.unshift(response.data); // genel task listesine ekle
          if (this.currentPage == 1) {
            this.sliceTask.unshift(response.data); // eğer o anda 1 sayfada ise ekle
          }
          this.tableSlice(); // tekrar tabloda gösterilecek veriyi düzenle
          this.paginationTotalPage(); // sayfa sayısını güncelle

          // reset - inputları temizle
          this.name = "";
          this.description = "";
        });
    },

    /**
     * tabloda gösterilecek verilerin kısmi oluşturulması
     */
    tableSlice() {
      this.sliceTask = this.tasks.slice(
        (this.currentPage - 1) * this.countOfPage,
        this.currentPage * this.countOfPage
      );
    },

    /**
     * sayfa sayısını bulma
     */
    paginationTotalPage() {
      this.totalPage = Math.ceil(this.tasks.length / this.countOfPage);
    },
  },
  watch: {
    /**
     * @description sayfa sayısı değiştiğinde tabloda gösterilecek verileri güncelle
     */
    currentPage(newPageNumber) {
      this.sliceTask = this.tasks.slice(
        (newPageNumber - 1) * this.countOfPage,
        newPageNumber * this.countOfPage
      );
    },
  },
};
</script>
