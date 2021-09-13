<template>
    <el-container>
        <el-header>
            <my-header></my-header>
        </el-header>
        <el-container>
            <el-aside width="200px">
                <el-menu router style="height: 100%;background-color: #2c3e50;">
                    <el-submenu v-for="(item,index) in $router.options.routes[3].children" :index="index+''" >
                        <template #title ><div style="font-size: 15px;font-weight: bold;color: white" @click="open1(index)"><i class="el-icon-s-promotion"></i>{{item.name}}</div></template>
                   </el-submenu>
                </el-menu>
            </el-aside>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import MyHeader from "../../components/myHeader";
    export default {
        name: "teacher",
        components: {MyHeader},
        methods:{
            open1(index){
                if (index === 0){
                    this.$router.push({
                        name: '试题管理',
                    })
                }
                else if(index === 1){
                    this.$router.push({
                        name: '学生管理',
                    })
                }
                else if(index === 2){
                    this.$router.push({
                        name: '成绩管理',
                    })
                }
                else if(index === 3){
                    this.$router.push({
                        name: '个人中心',
                    })
                }
            },
        }
    }
</script>

<style scoped>
    .el-aside {
        background-color: white;
        color: white;
        text-align: center;
        line-height: 200px;
    }
</style>