<template>
    <el-container>
        <el-header>
            <my-header></my-header>
        </el-header>
        <el-main>
            <div>
                <div class="exam">
                    <span style="margin-left: 55px">试卷列表</span>
                </div>
                <div class="list">
                    <el-card class="box-card" v-for="(item,index) in courses">
                        <template #header>
                            <div class="card-header">
                                <span>{{item.course}}</span>
                                <span style="height: 30px;line-height: 30px;font-size: 10px"><i class="el-icon-time">限时20分钟</i>&nbsp;&nbsp;&nbsp;<i class="el-icon-tickets">满分100分</i></span>
                            </div>
                        </template>
                        <div class="item">
                            <el-button class="button" type="text" @click="openExam(item)"><i class="el-icon-edit-outline"></i>进入考试</el-button>
                        </div>
                    </el-card>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script>
    import  axios from 'axios'
    import MyHeader from "../../components/myHeader";
    export default {
        name: "exam",
        components: {MyHeader},
        data(){
            return{
                courses:[{
                    id:'',
                    course:''
                }]
            }
        },
        created() {
            const _this=this
            axios.get('/course/findAllCourse').then(resp => {
                _this.courses=resp.data
            })
        },
        methods:{
            // 将课程 id通过路由传递
            openExam(item){
                this.$router.push({
                    path:'/exam1',
                    query:{
                        id:item.id,
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .exam{
        background-color: honeydew;
        width: 83%;
        margin: 0 auto;
        height: 50px;
        line-height: 75px;
        border-bottom:1px solid darkgray;
        display: flex;
    }
    .list{
        min-height: 750px;
        width: 83%;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
        background-color: honeydew;
        margin: 0 auto;
    }
    .card-header {
        display: flex;
        text-align: left;
        line-height: 80px;
        height: 150px;
        font-size: 20px;
        padding: 5px;
        flex-direction: column;
    }
    .item {
        height:20px;
        margin-bottom: 10px;
        line-height: 20px;
    }
    .box-card {
        width: 290px;
        margin: 20px 10px 5px 10px;
    }
</style>