<template>
    <div>
        <el-table :data="tableData" border style="width: 100%" height="480px">
            <el-table-column prop="id" label="编号" width="180">
            </el-table-column>
            <el-table-column prop="course" label="课程" >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="scope">
                    <el-button @click="choose(scope.row)" type="success" >进入测试</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import axios from 'axios'
    import store from '@/store'
    import PersonCenter from "../../components/personCenter";
    export default {
        name: "student4-1",
        components: {PersonCenter},
        data(){
            return{
                tableData:[{
                    id:'',
                    course:''
                }]
            }
        },
        created() {
            const _this=this
            axios.get('/course/findAllCourse').then(resp => {
                _this.tableData=resp.data
            })
            // this.tableData = store.state.user.courses
        },
        methods:{
            choose(row){
                this.$router.push({
                    path:'/exam1',
                    query:{
                        id:row.id
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>