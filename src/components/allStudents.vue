<template>
    <div>
        <div class="addButton">
            <el-button type="primary" @click="add">添加</el-button>
            <el-button type="primary" @click="Refresh">点击刷新</el-button>
        </div>
        <el-table :data="tableData" border height="433px">
            <el-table-column prop="id" label="编号" width="125">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="125">
            </el-table-column>
            <el-table-column prop="gender" label="性别" width="125">
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="125">
            </el-table-column>
            <el-table-column prop="password" label="密码" width="125">
            </el-table-column>
            <el-table-column prop="phone" label="手机号" >
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
                <el-form-item style="text-align: center">
                    添加考生信息
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-input v-model="form.gender"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="form.password"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="身份ID" prop="authority_id">
                    <el-select v-model="form.authority_id" placeholder="请选择身份ID">
                        <el-option v-for="(item,index) in Authorities" :value="item.id">{{item.name}}</el-option>
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
                <el-form-item style="text-align: center">
                    修改学生信息
                </el-form-item>
                <el-form-item label="编号" prop="id">
                    <el-input v-model="forms.id" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="forms.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-input v-model="forms.gender"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="forms.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="forms.password"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model="forms.phone"></el-input>
                </el-form-item>
                <el-form-item label="身份ID" prop="authority_id">
                    <el-input v-model="forms.authority_id" readonly="true"></el-input>
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

    export default {
        name: "allStudents",
        data() {
            // 判断手机号格式是否正确
            let isPhoneAvailable = (rule, value, callback) => {
                let myReg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
                if (value === '') {
                    callback(new Error('请输入手机号'));
                } else if (!myReg.test(value)) {
                    callback(new Error('手机号格式有误，请重新输入'));
                } else {
                    callback();
                }
            };
            return {
                //学生信息表
                tableData: [{
                    id: '',
                    gender: '',
                    name: '',
                    username:'',
                    password:'',
                    phone:'',
                    authority_id:''
                }],
                dialogFormVisible: false, //添加学生的弹框
                dialogVisible: false, // 修改学生信息的弹框
                form: {  //添加学生信息表
                    gender: '',
                    name: '',
                    username:'',
                    password:'',
                    phone:'',
                    authority_id:''
                },
                forms: {  // 修改学生信息表
                    id:'',
                    gender: '',
                    name: '',
                    username:'',
                    password:'',
                    phone:'',
                    authority_id:''
                },
                Authorities:{ // 角色表
                    id:'',
                    name:''
                },
                formLabelWidth: '80px',// 表单长度
                rules: {  // 定义表单规则
                    gender: [
                        { required: true, message: '请输入学生性别', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入学生姓名', trigger: 'blur' }
                    ],
                    username: [
                        { required: true, message: '请输入学生用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入用户密码', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true,validator: isPhoneAvailable, trigger: 'blur'}
                    ],
                    authority_id:[
                        { required: true, message: '请输入身份ID', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            const _this = this
            axios.get('/Authorities').then(resp => { // 获取所有角色信息
                _this.Authorities = resp.data
            })
            axios.get('/student/findAllStudent').then(resp => { // 获取所有学生信息
                _this.tableData=resp.data
                console.log(resp.data)
            })
        },
        methods:{
            add(){ // 添加
                this.form={};
                this.dialogFormVisible=true // 显示弹框
            },
            Refresh(){
                const _this = this
                axios.get('/student/findAllStudent').then(resp => { // 获取所有学生信息
                    _this.tableData=resp.data
                    // console.log(resp.data)
                })
            },
            edit(row){ // 修改
                this.forms=row; // 获取当前列的信息
                this.dialogVisible=true;// 显示弹框
            },
            deleteCourse(row){ // 删除当前列信息
                axios.delete('/student/deleteStudent/'+row.id).then(resp => {
                    if (resp.status === 200){
                        ElMessage.success({
                            message: '删除成功',
                            type: 'success'
                        });
                    }
                })
            },
            save(formName){ // 添加
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('/student/saveStudent',this.form).then(resp => {
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
            saveForms(formName){ // 修改
                let data={
                    id:this.forms.id,
                    name:this.forms.name,
                    gender:this.forms.gender,
                    username:this.forms.username,
                    password:this.forms.password,
                    phone:this.forms.phone,
                    authority_id:this.forms.authority_id
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('/student/updateStudent',data).then(resp => {
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
    }
</style>
