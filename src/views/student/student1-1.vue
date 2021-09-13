<template>
    <div>
        <el-table :data="tableData" border style="width: 100%" height="480px">
            <el-table-column prop="id" label="编号" width="180">
            </el-table-column>
            <el-table-column prop="course" label="课程" >
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import store from '@/store'
    import axios from "axios";
    export default {
        name: "student1-1",
        data() {
            return {
                tableData: [{
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
        }
    }
</script>

<style scoped>

</style>