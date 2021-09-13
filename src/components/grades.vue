<template>
    <div>
        <div class="addButton">
            <el-button type="primary" @click="add">添加</el-button>
            <el-button type="primary" @click="Refresh">点击刷新</el-button>
        </div>
        <el-table :data="tableData" border height="433px">
            <el-table-column prop="id" label="编号" width="125">
            </el-table-column>
            <el-table-column prop="student.name" label="姓名" width="125">
            </el-table-column>
            <el-table-column prop="courses.course" label="课程" width="125">
            </el-table-column>
            <el-table-column prop="grades" label="成绩" width="125">
            </el-table-column>
            <el-table-column prop="updateTime" label="提交时间">
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="scope">
                    <el-button @click="edit(scope.row)" type="primary" icon="el-icon-edit" circle></el-button>
                    <el-button @click="deleteGrades(scope.row)" type="danger" icon="el-icon-delete" circle></el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog v-model="dialogFormVisible" width="500px">
            <el-form :model="form" :rules="rules" ref="form" label-width="80px" class="demo-ruleForm">
                <el-form-item style="text-align: center">
                    添加学生成绩
                </el-form-item>
                <el-form-item label="姓名" prop="student_id">
                    <el-select v-model="form.student_id" placeholder="学生姓名">
                        <el-option v-for="(item,index) in studentForm" :value="item.id">{{item.name}}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="课程ID" prop="course_id" v-if="showPrise">
                    <el-select v-model="form.course_id" placeholder="课程名称">
                        <el-option v-for="(item1,index) in courseForm" :value="item1.id">{{item1.course}}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="成绩" prop="grades">
                    <el-input v-model="form.grades"></el-input>
                </el-form-item>
                <el-form-item label="提交时间" prop="updateTime">
                    <el-input v-model="form.updateTime"></el-input>
                </el-form-item>
                <el-form-item style="text-align: right">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="save('form')">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog v-model="dialogVisible" width="500px">
            <el-form :model="forms" :rules="rules" ref="forms" label-width="80px" class="demo-ruleForm">
                <el-form-item style="text-align: center">
                    修改学生成绩
                </el-form-item>
                <el-form-item label="编号" prop="id">
                    <el-input v-model="forms.id" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="student_id">
                    <el-input v-model="forms.student.name" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="课程" prop="course_id">
                    <el-input v-model="forms.courses.course" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="成绩" prop="grades">
                    <el-input v-model="forms.grades"></el-input>
                </el-form-item>
                <el-form-item label="提交时间" prop="updateTime">
                    <el-input v-model="forms.updateTime" readonly="true"></el-input>
                </el-form-item>
                <el-form-item style="text-align: right">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveForms('forms')">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    import axios from "axios";
    import {ElMessage} from "element-plus";
    import store from '@/store'

    export default {
        name: "grades",
        data() {
            return {
                showPrise:false,
                tableData: [{
                    id: '',
                    grades:'',
                    course_id:'',
                    teacher_id:'',
                    courses:{course:''},
                    student:{name:''},
                    updateTime:''
                }],
                dialogFormVisible: false,
                dialogVisible: false,
                form: {
                    grades:'',
                    student_id:'',
                    course_id:'',
                    updateTime:''
                },
                forms: {
                    id:'',
                    grades:'',
                    course_id:'',
                    student_id:'',
                    courses:{course:''},
                    student:{name:''},
                    updateTime:''
                },
                form1:{},
                studentForm:[{
                    id:'',
                    course:''
                }],
                courseForm:[{
                    id:'',
                    course:''
                }],
                formLabelWidth: '80px',
                rules: {
                    student_id: [
                        { required: true, message: '请输入学生ID', trigger: 'blur' }
                    ],
                    course_id: [
                        { required: true, message: '请输入课程ID', trigger: 'blur' }
                    ],
                    grades: [
                        { required: true, message: '请输入学生成绩', trigger: 'blur' }
                    ],
                    updateTime: [
                        { required: true, message: '请输入提交时间', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            const _this = this
            axios.get('/grades/findAllGrades').then(resp => {
                if (store.state.authorities[0].authority === 'ROLE_ADMIN'){
                    this.showPrise=true
                    _this.tableData=resp.data
                }else if (store.state.authorities[0].authority === 'ROLE_TEACHER'){
                    let i=0;
                    for( ; i<resp.data.length; i++){
                        if (store.state.user.courses.id === resp.data[i].course_id){
                            _this.tableData[i]=resp.data[i]
                        }
                    }
                }
            });
            axios.get('/student/findAllStudent').then(resp => {
                _this.studentForm=resp.data
            });
            axios.get('/course/findAllCourse').then(resp => {
                _this.courseForm=resp.data
            });
        },
        methods: {
            add() {
                this.form = {};
                this.dialogFormVisible = true
            },
            Refresh(){
                const _this = this
                axios.get('/grades/findAllGrades').then(resp => {
                    if (store.state.authorities[0].authority === 'ROLE_ADMIN'){
                        this.showPrise=true
                        _this.tableData=resp.data
                    }else if (store.state.authorities[0].authority === 'ROLE_TEACHER'){
                        let i=0;
                        for( ; i<resp.data.length; i++){
                            if (store.state.user.courses.id === resp.data[i].course_id){
                                _this.tableData[i]=resp.data[i]
                            }
                        }
                        console.log(resp.data.length)
                    }
                });
            },
            edit(row) {
                this.forms = row;
                this.dialogVisible = true;
            },
            deleteGrades(row) {
                axios.delete('/grades/deleteGrades/' + row.id).then(resp => {
                    if (resp.status === 200) {
                        ElMessage.success({
                            message: '删除成功',
                            type: 'success'
                        });
                    }
                })
            },
            save(formName) {
                if (store.state.authorities[0].authority === 'ROLE_ADMIN'){
                    this.form1=this.form
                }else if (store.state.authorities[0].authority === 'ROLE_TEACHER'){
                    this.form1={
                        grades:this.form.grades,
                        student_id:this.form.student_id,
                        course_id:store.state.user.courses.id,
                        updateTime:this.form.updateTime
                    }
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('/grades/saveGrades', this.form1).then(resp => {
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
                let data={
                    id : this.forms.id,
                    grades:this.forms.grades,
                    course_id:this.forms.course_id,
                    student_id:this.forms.student_id,
                    updateTime:this.forms.updateTime
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('/grades/updateGrades', data).then(resp => {
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
            }
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
        /*line-height: 50px;*/
    }
</style>
