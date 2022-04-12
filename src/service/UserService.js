import http from "@/axios-config"
class USerService {
    login(data) {
        return http.post("/login", data);
    }
    createUser(data) {
        return http.post("/createUser", data);
    }
    
}
export default new USerService();