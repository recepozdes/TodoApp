import http from "@/axios-config" //@ işareti src klasörünün path ini belirtir
class TaskService {
    create(data) {
        return http.post("/addTask", data);
    }
    createAll(data) {
        return http.post("/addTasks", data);
    }
    getAll() {
        let User = JSON.parse(localStorage.getItem("user"));
        return http.get(`/taskByName/${User?.username}`);
    }
    getById(id) {
        return http.get(`/taskById/${id}`);
    }
    findByTitle(name) {
        return http.get(`/taskByName/${name}`);
    }
    listTaskByUsername(username) {
        return http.get(`/listTaskByUsername/${username}`)
    }
    update(data) {
        return http.put(`/update`, data);
    }
    delete(id) {
        return http.delete(`/delete/${id}`);
    }
    setTaskStatus(data){
        return http.put(`/status`, data);
    }
}
export default new TaskService();