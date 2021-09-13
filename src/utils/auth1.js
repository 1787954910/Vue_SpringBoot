import request from './api'

export function login(username, password) {
    return request({
        url: '/login',
        method: 'post',
        data: {
            username,
            password
        }
    })
}
export function forgetPsw(username,phone,code) {
    return request({
        url:'/forgetPsw',
        method:'post',
        data:{
            username,
            phone,
            code
        }
    })

}
