<template>
    <div>
        <el-card class="box-card" shadow="hover">
            <template #header>
                <div class="card-header">
                    <span>个人信息</span>
                    <el-button type="primary" icon="el-icon-edit" circle @click="edit(userInfo)"></el-button>
                </div>
            </template>
            <div class="text item">
                {{'姓名：'}} {{userInfo.name}}
            </div>
            <div class="text item">
                {{'性别：'}} {{userInfo.gender}}
            </div>
            <div class="text item">
                {{'手机号：'}} {{userInfo.phone}}
            </div>
            <div class="text item">
                {{'用户名：'}} {{userInfo.username}}
            </div>
            <div class="text item">
                {{'密码：'}} {{userInfo.password}}
            </div>
        </el-card>

        <el-dialog  v-model="dialogVisible" width="400px" :style="selfStyleDialog">
            <el-form :model="form" :rules="rules" ref="form" label-width="80px" class="demo-ruleForm" :style="selfStyleForm">
                <el-form-item label="编号" prop="id">
                    <el-input v-model="form.id" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-input v-model="form.gender"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username" readonly="true"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="form.password"></el-input>
                </el-form-item>
                <el-form-item style="text-align: right">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveForm('form')">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    import store from '@/store'
    import axios from "axios";
    import {ElMessage} from "element-plus";
    export default {
        name: "personCenter",
        data(){
            let isPhoneAvailable = (rule, value, callback) => {
                let myReg=/^[1][3,4,5,7,8,9][0-9]{9}$/;
                if (value === '') {
                    callback(new Error('请输入手机号'));
                } else if (!myReg.test(value)) {
                    callback(new Error('手机号格式不正确，请输入正确的手机号'));
                } else {
                    callback();
                }
            };
            return{
                selfStyleDialog:{
                    height:'400px',
                    paddingBottom: '0',
                    marginBottom:'0'
                },
                selfStyleForm:{
                    paddingBottom:'0',
                    marginBottom:'0'
                },
                dialogVisible: false,
                userInfo:{
                    id:store.state.user.id,
                    name: store.state.user.name,
                    gender: store.state.user.gender,
                    phone:store.state.user.phone,
                    username: store.state.user.username,
                    password: store.state.user.password,
                    authority_id:store.state.user.authority_id,
                },
                form:{
                    id:'',
                    name:'',
                    gender:'',
                    phone:'',
                    username:'',
                    password:'',
                    authority_id:''
                },
                rules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ],
                    gender: [
                        { required: true, message: '请输入性别', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true,validator: isPhoneAvailable, trigger: 'blur'}
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            }
        },
        methods:{
            edit(userInfo) {
                this.form = userInfo;
                this.dialogVisible = true;
                console.log(userInfo)
            },
            saveForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (store.state.authorities[0].authority === 'ROLE_ADMIN'){
                            axios.put('/admin/updateAdmin',this.form).then(resp => {
                                if (resp.status === 200) {
                                ElMessage.success({
                                    message: '修改成功',
                                    type: 'success'
                                });
                                this.dialogVisible = false
                            } else
                                ElMessage.error('修改失败');
                            })
                        }else if (store.state.authorities[0].authority === 'ROLE_TEACHER'){
                            axios.put('/teacher/updateTeacher',this.form).then(resp => {
                                if (resp.status === 200) {
                                    ElMessage.success({
                                        message: '修改成功',
                                        type: 'success'
                                    });
                                    this.dialogVisible = false
                                } else
                                    ElMessage.error('修改失败');
                            })
                        }else
                        if(store.state.authorities[0].authority === 'ROLE_STUDENT'){
                            console.log(this.form)
                            axios.put('/student/updateStudent',this.form).then(resp => {
                                if (resp.status === 200) {
                                    ElMessage.success({
                                        message: '修改成功',
                                        type: 'success'
                                    });
                                    this.dialogVisible = false
                                } else
                                    ElMessage.error('修改失败');
                            })
                        }
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
    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 30px;
    }
    .text {
        font-size: 20px;
    }
    .item {
        height: 50px;
        line-height: 50px;
        text-align: left;
        padding-left: 170px;
        background-color: honeydew;
    }
    .box-card {
        width: 880px;
    }
</style>
