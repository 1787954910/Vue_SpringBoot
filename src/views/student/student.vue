<template>
    <el-container>
        <el-header>
            <my-header></my-header>
        </el-header>
        <el-container>
            <div style="display: flex;flex-direction: column">
                <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;margin-top: 20px">
                    <el-radio-button :label="true"><i class="el-icon-arrow-left"></i></el-radio-button>
                    <el-radio-button :label="false"><i class="el-icon-arrow-right"></i></el-radio-button>
                </el-radio-group>
                <el-menu
                         class="el-menu-vertical-demo"
                         background-color="rgba(0,0,0,0)"
                         text-color="white"
                         active-text-color="dodgerblue"
                         :collapse="isCollapse">
                    <el-menu-item index="1" >
                        <i class="el-icon-menu" ></i>
                        <template #title><router-link to="/student1-1" :style="selfStyle">我的课程</router-link></template>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <i class="el-icon-tickets"></i>
                        <template #title><router-link to="/student2-1" :style="selfStyle">我的成绩</router-link></template>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <i class="el-icon-user"></i>
                        <template #title><router-link to="/student3-1" :style="selfStyle">个人中心</router-link></template>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <i class="el-icon-edit-outline"></i>
                        <template #title><router-link to="/students/student4-1" :style="selfStyle">在线考试</router-link></template>
                    </el-menu-item>
                </el-menu>
            </div>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import MyHeader from "../../components/myHeader";
    export default {
        name: "student",
        components: {MyHeader},
        data(){
            return{
                isCollapse: true,
                selfStyle:{
                    textDecoration:'none',
                    color:'white'
                }
            }
        },
    }
</script>

<style scoped>
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        min-height: 250px;
    }
    .el-menu-vertical-demo a{
        color: white;
        text-decoration: none;
    }
</style>