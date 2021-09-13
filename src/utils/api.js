import axios from "axios";
import { ElMessage } from 'element-plus'
import router from "../router/index";
import store from '@/store'

// 创建axios实例
const baseUrl = ''
const service = axios.create({
    baseURL: baseUrl,
    timeout: 10000 // 请求超时时间
})

// request拦截器
axios.interceptors.request.use(
    config => {
        const token=store.state.token

        if (token) {
            config.headers.common['Authorization'] = 'Bearer ' +token // 让每个请求携带自定义token 请根据实际情况自行修改
            config.headers.common.token = token
        }
        return config
    },
    error => {
        // Do something with request error
        console.log(error) // for debug
        Promise.reject(error)
    }
)
// response 拦截器
service.interceptors.response.use(
    response => {
        if(response.data){
            return response.data
        }
    },
    error => {
        ElMessage.error(error.message);
        return Promise.reject(error)
    }
)

export default service
