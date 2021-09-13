<template>
    <div>
        <div class="addButton">
            <el-button type="primary" @click="add">添加</el-button>
            <el-button type="primary" @click="Refresh">点击刷新</el-button>
        </div>
        <el-table :data="tableData" border style="width: 100%" height="433px">
            <el-table-column prop="id" label="编号" width="50">
            </el-table-column>
            <el-table-column prop="question" label="试题内容" width="125">
            </el-table-column>
            <el-table-column prop="pick1" label="选项一" width="125">
            </el-table-column>
            <el-table-column prop="pick2" label="选项二" width="125">
            </el-table-column>
            <el-table-column prop="pick3" label="选项三" width="125">
            </el-table-column>
            <el-table-column prop="pick4" label="选项四" width="125">
            </el-table-column>
            <el-table-column prop="correct" label="答案" >
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="scope">
                    <el-button @click="edit(scope.row)" type="primary" icon="el-icon-edit" circle></el-button>
                    <el-button @click="deleteCourse(scope.row)" type="danger" icon="el-icon-delete" circle></el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog v-model="dialogFormVisible" width="500px">
            <el-form :model="form" :rules="rules" ref="form" label-width="80px" class="demo-ruleForm">
                <el-form-item style="text-align: center;font-size: 20px">
                    添加试题
                </el-form-item>
                <el-form-item label="试题内容" prop="question">
                    <el-input v-model="form.question"></el-input>
                </el-form-item>
                <el-form-item label="选项一" prop="pick1">
                    <el-input v-model="form.pick1"></el-input>
                </el-form-item>
                <el-form-item label="选项二" prop="pick2">
                    <el-input v-model="form.pick2"></el-input>
                </el-form-item>
                <el-form-item label="选项三" prop="pick3">
                    <el-input v-model="form.pick3"></el-input>
                </el-form-item>
                <el-form-item label="选项四" prop="pick4">
                    <el-input v-model="form.pick4"></el-input>
                </el-form-item>
                <el-form-item label="答案" prop="correct">
                    <el-input v-model="form.correct"></el-input>
                </el-form-item>
                <el-form-item label="课程ID" prop="course_id" v-if="showPrise">
                    <el-select v-model="form.course_id" placeholder="请选择课程">
                        <el-option v-for="(item,index) in courseForm" :value="item.id">{{item.course}}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="text-align: right">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="save('form')">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog v-model="dialogVisible" width="500px">
            <el-form :model="forms" :rules="rules" ref="forms" label-width="80px" class="demo-ruleForm">
                <el-form-item style="text-align: center;font-size: 20px">
                    修改试题
                </el-form-item>
                <el-form-item label="编号" prop="id">
                    <el-input v-model="forms.id" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="试题内容" prop="question">
                    <el-input v-model="forms.question"></el-input>
                </el-form-item>
                <el-form-item label="选项一" prop="pick1">
                    <el-input v-model="forms.pick1"></el-input>
                </el-form-item>
                <el-form-item label="选项二" prop="pick2">
                    <el-input v-model="forms.pick2"></el-input>
                </el-form-item>
                <el-form-item label="选项三" prop="pick3">
                    <el-input v-model="forms.pick3"></el-input>
                </el-form-item>
                <el-form-item label="选项四" prop="pick4">
                    <el-input v-model="forms.pick4"></el-input>
                </el-form-item>
                <el-form-item label="答案" prop="correct">
                    <el-input v-model="forms.correct"></el-input>
                </el-form-item>
                <el-form-item label="课程ID" prop="course_id">
                    <el-input v-model="forms.course_id" readonly="true"></el-input>
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
    import axios from 'axios'
    import { ElMessage } from 'element-plus'
    import store from '@/store'

    export default {
        inject:['reload'],
        name: "questions",
        data() {
            return {
                showPrise:false,
                tableData: [{  //承载试题的表单
                    id: '',
                    question: '',
                    pick1:'',
                    pick2:'',
                    pick3:'',
                    pick4:'',
                    correct:'',
                    course_id:''
                }],
                dialogFormVisible: false, // 管理员添加试题的弹框
                dialogVisible: false,// 教师添加试题的弹框
                form: {    //添加试题
                    question: '',
                    pick1:'',
                    pick2:'',
                    pick3:'',
                    pick4:'',
                    correct:'',
                    course_id:''
                },
                forms: {  //修改试题
                    id:'',
                    question: '',
                    pick1:'',
                    pick2:'',
                    pick3:'',
                    pick4:'',
                    correct:'',
                    course_id:''
                },
                form1:{},
                courseForm:[{
                    id:'',
                    course:''
                }],
                formLabelWidth: '80px',
                rules: {
                    question: [
                        { required: true, message: '请输入试题内容', trigger: 'blur' }
                    ],
                    pick1: [
                        { required: true, message: '请输入选项一', trigger: 'blur' }
                    ],
                    pick2: [
                        { required: true, message: '请输入选项二', trigger: 'blur' }
                    ],
                    pick3: [
                        { required: true, message: '请输入选项三', trigger: 'blur' }
                    ],
                    pick4: [
                        { required: true, message: '请输入选项四', trigger: 'blur' }
                    ],
                    correct: [
                        { required: true, message: '请输入答案', trigger: 'blur' }
                    ],
                    course_id: [
                        { required: true, message: '请课程名称', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            const _this = this
            if (store.state.authorities[0].authority === 'ROLE_ADMIN'){
                this.showPrise=true
                axios.get('/questions/findAllQuestions').then(resp => {
                    _this.tableData=resp.data
                });
            }else if (store.state.authorities[0].authority === 'ROLE_TEACHER'){
                this.showPrise=false
                _this.tableData = store.state.user.courses.questions
            }
            axios.get('/course/findAllCourse').then(resp => {
                _this.courseForm=resp.data
            })
        },
        methods:{
            add(){
                this.form={};
                this.dialogFormVisible=true
            },
            Refresh(){
                const _this = this
                if (store.state.authorities[0].authority === 'ROLE_ADMIN'){
                    axios.get('/questions/findAllQuestions').then(resp => {
                        _this.tableData=resp.data
                        console.log(resp.data)
                    });
                }else if (store.state.authorities[0].authority === 'ROLE_TEACHER'){
                    console.log(store.state.user.id)
                    axios.get('/teacher/findTeacherById/'+store.state.user.id).then(resp => {
                        _this.tableData = resp.data.courses.questions
                    })
                }
            },
            edit(row){
                this.forms=row;
                this.dialogVisible=true;
            },
            deleteCourse(row){
                axios.delete('/questions/deleteQuestionsById/'+row.id).then(resp => {
                    if (resp.status === 200){
                        ElMessage.success({
                            message: '删除成功',
                            type: 'success'
                        });
                    }
                })
            },
            save(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (store.state.authorities[0].authority === 'ROLE_ADMIN'){
                            this.form1=this.form
                        }else if (store.state.authorities[0].authority === 'ROLE_TEACHER'){
                            this.form1={
                                question: this.form.question,
                                pick1:this.form.pick1,
                                pick2:this.form.pick2,
                                pick3:this.form.pick3,
                                pick4:this.form.pick4,
                                correct:this.form.correct,
                                course_id:store.state.user.courses.id
                            }
                        }
                        axios.post('/questions/saveQuestions',this.form1).then(resp => {
                            if (resp.status === 200){
                                ElMessage.success({
                                    message: '添加成功',
                                    type: 'success'
                                });
                                this.dialogFormVisible=false
                            }else
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
                        axios.put('/questions/updateQuestions',this.forms).then(resp => {
                            if (resp.status === 200){
                                ElMessage.success({
                                    message: '修改成功',
                                    type: 'success'
                                });
                                this.dialogVisible=false
                            }else
                                ElMessage.error('修改失败');
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
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
