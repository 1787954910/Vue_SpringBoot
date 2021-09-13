const getters = {

  token: state => state.user.token,
  authorities: state => state.user.authorities,
  user: state => state.user.user,
  isLogin:state => state.user.isLogin,
  user1: state => state.user.user1,
}
export default getters
