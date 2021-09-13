<template>
    <el-container>
        <el-header>
            <my-header></my-header>
        </el-header>
        <el-container>
            <el-aside width="200px">
                <el-menu router style="height: 100%;background-color: #2c3e50;" >
                    <el-submenu v-for="(item,index) in $router.options.routes[2].children"  :index="index+''" >
                        <template #title><div style="font-size: 15px;font-weight: bold;color: white" @click="opens(index)"><i class="el-icon-s-promotion"></i>{{item.name}}</div></template>
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
    import  axios from 'axios'
    import MyHeader from "../../components/myHeader";
    export default {
        name: "adm",
        components: {MyHeader},
        data(){
            return{
                course:[{
                    id:'',
                    course:''
                }]
            }
        },
        created() {
            const _this=this
            axios.get('/course/findAllCourse').then(resp => {
                _this.course=resp.data
            })
        },
        methods:{
            opens(index){
                if (index === 0)
                    this.$router.push({
                        name:'教师管理'
                    })
                else if(index === 1)
                    this.$router.push({
                        name:'考生管理'
                    })
                else if(index === 2)
                    this.$router.push({
                        name:'课程管理'
                    })
                else if(index === 3)
                    this.$router.push({
                        name:'考题管理'
                    })
                else if(index === 4)
                    this.$router.push({
                        name:'绩点管理'
                    })
                else if(index === 5)
                    this.$router.push({
                        name:'个人信息'
                    })
            },
        }
    }
</script>

<style scoped>

</style>