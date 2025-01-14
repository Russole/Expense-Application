import axios from "axios";
import { AuthResponse } from "../model/AuthResponse";

const apiClient = axios.create({
    baseURL:'http://localhost:8080/api/v1',
})

apiClient.interceptors.request.use(config => {
    if (!config.url?.includes("/login") && !config.url?.includes("/register")) {
        const authObject = localStorage.getItem("user");
        if (authObject) {
            const {token} = JSON.parse(authObject) as AuthResponse;
            config.headers.Authorization = `Bearer ${token}`;
        }
    }
    return config;
}, error => Promise.reject(error));

export default apiClient;