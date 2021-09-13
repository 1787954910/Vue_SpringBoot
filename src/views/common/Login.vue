<template>
    <div>
        <div class="login">
            <div class="login_title">考试登录</div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="form">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" prefix-icon="el-icon-user-solid"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="ruleForm.password" prefix-icon="el-icon-s-cooperation" show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="login('ruleForm')" class="Button">登录</el-button>
                    <router-link to="/forgetPsw">忘记密码？</router-link>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import { ElMessage } from 'element-plus'
    import store from '@/store'

    export default {
        name: "Login",
        data() {
            return {
                ruleForm: {
                    username: 'stu1',
                    password: '123456',
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            async login(formName) {
                await this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //根据用户角色决定路由跳转的页面
                        this.$store.dispatch('Login',this.ruleForm).then(() => {
                            //管理员
                            if (store.state.authorities[0].authority === 'ROLE_ADMIN'){
                                this.$router.push('/adms')
                                //教师
                            }else if (store.state.authorities[0].authority === 'ROLE_TEACHER'){
                                this.$router.push('/teachers')
                                //学生
                            }else if(store.state.authorities[0].authority === 'ROLE_STUDENT'){
                                this.$router.push('/exam')
                            }
                        }).catch(() => {
                            ElMessage.error("登录失败!");
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
    .login{
        height: 350px;
        width: 350px;
        display: flex;
        flex-direction: column;
        align-items: center;
        background-color: #2c3e50;
        background-color: rgba(96,96,96,0.8);
        color: white;
        position: absolute;
        left: 50%;
        top:50%;
        margin-top: -175px;
        margin-left: -175px;
    }
    .login_title{
        text-align: center;
        font-size: 25px;
        font-weight: bold;
        height: 80px;
        width: 120px;
        line-height: 50px;
        margin-top: 40px;
    }
    .form{
        width:80%;
        height: 300px;
    }
    .Button{
        width: 100%;
        margin-bottom: 40px;
    }
    .form a{
        color: white;
        text-decoration: none
    }
</style>
