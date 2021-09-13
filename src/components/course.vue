<template>
    <div>
        <div class="addButton">
            <el-button type="primary" @click="add">添加</el-button>
            <el-button type="primary" @click="Refresh">点击刷新</el-button>
        </div>
        <el-table :data="tableData" border style="width: 100%" height="433px">
            <el-table-column prop="id" label="编号" width="180">
            </el-table-column>
            <el-table-column prop="course" label="课程" >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="scope">
                    <el-button @click="edit(scope.row)" type="primary" icon="el-icon-edit" circle></el-button>
                    <el-button @click="deleteCourse(scope.row)" type="danger" icon="el-icon-delete" circle></el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog v-model="dialogFormVisible" width="400px">
            <el-form :model="form" :rules="rules" ref="form" label-width="80px" class="demo-ruleForm">
                <el-form-item style="text-align: center">
                    添加课程
                </el-form-item>
                <el-form-item label="课程名称" prop="course">
                    <el-input v-model="form.course"></el-input>
                </el-form-item>
                <el-form-item label="教师ID" prop="teacher_id">
                    <el-select v-model="form.teacher_id" placeholder="请选择教师">
                        <el-option v-for="(item,index) in teachersForm" :value="item.id">{{item.name}}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="text-align: right">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="save('form')">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog v-model="dialogVisible" width="400px">
            <el-form :model="forms" :rules="rules" ref="forms" label-width="80px" class="demo-ruleForm">
                <el-form-item style="text-align: center">
                    修改课程
                </el-form-item>
                <el-form-item label="编号" prop="id">
                    <el-input v-model="forms.id" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="课程名称" prop="course">
                    <el-input v-model="forms.course"></el-input>
                </el-form-item>
                <el-form-item label="教师ID" prop="teacher_id">
                    <el-input v-model="forms.teacher_id"></el-input>
                </el-form-item>
                <el-form-item style="text-align: center">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveForms('forms')">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios'
    import { ElMessage } from 'element-plus'
    export default {
        name: "course",
        data() {
            return {
                tableData: [{ // 课程信息表单
                    id: '',
                    course: '',
                    teacher_id:''
                }],
                dialogFormVisible: false, // 添加课程弹框
                dialogVisible: false,  // 修改课程弹框
                form: {  // 添加课程信息表单
                    course: '',
                    teacher_id:''
                },
                forms: { // 修改课程信息表单
                    course: '',
                    teacher_id:''
                },
                teachersForm:[{
                    id:'',
                    name:''
                }],
                formLabelWidth: '80px',
                rules: {
                    course: [
                        { required: true, message: '请输入课程名称', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            const _this = this
            axios.get('/course/findAllCourse').then(resp => {
                _this.tableData=resp.data
            })
            axios.get('/teacher/findAllTeacher').then(resp => {
                _this.teachersForm=resp.data
            })
        },
        methods: {
            add() {
                this.form = {};
                this.dialogFormVisible = true
            },
            Refresh(){
                const _this = this
                axios.get('/course/findAllCourse').then(resp => {
                    _this.tableData=resp.data
                })
            },
            edit(row) {
                this.forms = row;
                this.dialogVisible = true;
            },
            save(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('/course/saveCourse', this.form).then(resp => {
                            if (resp.status === 200) {
                                ElMessage.success({
                                    message: '添加成功',
                                    type: 'success'
                                });
                                this.dialogFormVisible = false
                            } else
                                ElMessage.error('添加失败');
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            saveForms(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('/course/updateCourse', this.forms).then(resp => {
                            if (resp.status === 200) {
                                ElMessage.success({
                                    message: '修改成功',
                                    type: 'success'
                                });
                                this.dialogVisible = false
                            } else
                                ElMessage.error('修改失败');
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            deleteCourse(row) {
                axios.delete('/course/deleteCourse/' + row.id).then(resp => {
                    if (resp.status === 200){
                        ElMessage.success({
                            message: '删除成功',
                            type: 'success'
                        });
                    }
                })
            },
        }
    }
</script>

<style scoped>
    .addButton{
        height: 40px;
        display: flex;
        flex-direction: row;
        justify-content: left;
        margin-bottom: 5px;
    }
    .Footer{
        height: 50px;
    }
</style>
