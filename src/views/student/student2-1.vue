<template>
    <div>
        <el-table :data="tableData" border style="width: 100%" height="480px">
            <el-table-column prop="id" label="编号" width="180">
            </el-table-column>
            <el-table-column prop="courses.course" label="课程" width="180">
            </el-table-column>
            <el-table-column prop="grades" label="成绩"  width="180">
            </el-table-column>
            <el-table-column prop="updateTime" label="提交时间">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import axios from "axios";
    import store from '@/store'
    export default {
        name: "student2-1",
        data() {
            return {
                tableData: [{
                    id: '',
                    courses: {course:''},
                    grades: '',
                    updateTime:''
                }]
            }
        },
        created() {
            const _this = this
            axios.get('/grades/findAllGrades').then(resp => {
                let i=0;
                for( ; i<resp.data.length; i++){
                    if (store.state.user.id === resp.data[i].student_id){
                        _this.tableData[i]=resp.data[i]
                    }
                }
            })
        }
    }
</script>

<style scoped>

</style>