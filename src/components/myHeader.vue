<template>
    <div class="ad">
        <div class="ad1"><i class="el-icon-s-platform i1"></i>基于springboot2+vue3的在线考试系统</div>
        <div class="ad2">
            <span class="userName" >{{name}}</span>
            <el-dropdown>
                <el-avatar shape="circle" :size="50" :fit="fit" :src="src"></el-avatar>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item @click="person">个人中心</el-dropdown-item>
                        <el-dropdown-item @click="signOut">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script>
    import store from '@/store'
    export default {
        name: "myHeader",
        data(){
            return{
                name:store.state.user.name,
                src: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            }
        },
        methods:{
            person(){
                if (store.state.authorities[0].authority === 'ROLE_ADMIN'){
                    this.$router.push('/adm6')
                }else if (store.state.authorities[0].authority === 'ROLE_TEACHER'){
                    this.$router.push('/teacher6-1')
                }else if(store.state.authorities[0].authority === 'ROLE_STUDENT'){
                    this.$router.push('/student3-1')
                }
            },
            signOut(){
                this.$store.dispatch('Logout').then(() => {
                    this.$router.replace({path: '/login'});
                })
            }
        }
    }
</script>

<style scoped>
    .ad{
        display: flex;
        flex-direction: row;
        justify-content: space-between;
    }
    .ad1{
        margin-left: 50px;
    }
    .i1{
        font-size: 25px;
        margin-right: 10px;
    }
    .ad2{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        margin-right: 50px;
    }
    .userName{
        margin-right: 10px;
    }
</style>