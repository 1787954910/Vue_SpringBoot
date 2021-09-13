import { createStore } from 'vuex'
import { getToken, setToken,removeToken } from "@/utils/auth";
import { login,forgetPsw } from "@/utils/auth1";
import axios from "axios";
import {ElMessage} from "element-plus";

const user = createStore({
  state: {
    token: getToken(),
    authorities:[],
    isLogin:false,
    user:{},
    user1:{}
  },
  mutations: {
    SET_TOKEN:(state, token) => {
      state.token = token
      sessionStorage.token = token
    },
    SET_AUTHORITIES:(state, authorities) => {
      state.authorities = authorities
    },
    SET_ISLOGIN:(state,boolean) => {
      state.isLogin = boolean
    },
    SET_USER:(state,user) => {
      state.user = user
    },
    SET_USER1:(state,user1) => {
      state.user1 = user1
    }
  },
  actions: {
    // 登录
    Login({commit}, userInfo) {
      const username = userInfo.username
      const password = userInfo.password
      return new Promise((resolve, reject) => {
        login(username, password).then(res => {

          commit('SET_TOKEN', res.token)
          commit('SET_AUTHORITIES', res.authorities)
          commit('SET_ISLOGIN', true)
          commit('SET_USER', res.user)
          setToken(res.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    ForgetPsw({commit}, userInfo) {
      const username = userInfo.username
      const phone = userInfo.phone
      const code = userInfo.code
      return new Promise((resolve, reject) => {
        forgetPsw(username, phone,code).then(res => {

          commit('SET_USER1', res.user1)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 登出
    Logout({commit}) {
      return new Promise((resolve) => {
        commit('SET_TOKEN', '')
        removeToken();
        commit('SET_AUTHORITIES', [])
        resolve()
      })
    },
  },
  modules: {
  },
})
export default user
