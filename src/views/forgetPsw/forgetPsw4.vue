<template>
        <div>
            密码重置成功，请重新登录！
            <br />
            <el-button type="success" icon="el-icon-check" @click="goLogin" circle></el-button>返回登录
        </div>
</template>

<script>
    import store from '@/store'
    export default {
        name: "forgetPsw4",
        created() {
            console.log(store.state.user1)
        },
        methods:{
            goLogin(){
                this.$router.push('/login')
            }
        }
    }
</script>

<style scoped>

</style>