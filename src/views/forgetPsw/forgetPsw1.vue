<template>
    <div class="psw">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="ruleForm" label-width="100px" >
            <el-form-item label="用户名：" prop="username">
                <el-input v-model="ruleForm.username" placeholder="请输入用户名" clearable></el-input>
            </el-form-item>
            <el-form-item label="手机号：" prop="phone">
                <el-input v-model="ruleForm.phone" placeholder="请输入已绑定手机号" clearable></el-input>
            </el-form-item>
            <el-form-item label="验证码：" prop="code">
                <el-input v-model="ruleForm.code" placeholder="请输入验证码"></el-input>
                <img :src="vcUrl" @click="updateVerifyCode" alt="" style="float: left;margin-top: 5px">
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">下一步</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>

    import axios from 'axios'
    import {ElMessage} from "element-plus";
    import store from '@/store'

    export default {
        name: "forgetPsw1",
        data() {
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
                vcUrl:'/verifyCode?time='+new Date(),
                ruleForm: {
                    username: '',
                    phone: '',
                    code:''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                    ],
                    phone: [
                        { required: true,validator: isPhoneAvailable, trigger: 'blur'}
                    ],
                    code: [
                        { required: true, message: '请输入验证码', trigger: 'blur' },
                    ]
                }
            };
        },
        methods:{
            updateVerifyCode(){
                this.vcUrl = '/verifyCode?time='+new Date()
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('/forgetPsw',this.ruleForm).then(resp => {
                            console.log(resp.data)
                            if(resp.data.state === false){
                                ElMessage.error(resp.data.msg);
                            }
                            else if(resp.data.state === true){
                                this.$router.push({
                                    path:'/forgetPsw3',
                                    query:{
                                            id:resp.data.user1.id,
                                            name:resp.data.user1.name,
                                            gender:resp.data.user1.gender,
                                            phone:resp.data.user1.phone,
                                            username:resp.data.user1.username,
                                            authority_id:resp.data.user1.authority_id,
                                        authority:resp.data.user1.authorities[0].authority
                                    }
                                })
                            }
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
    .psw{
        display: flex;
        justify-content: center;
    }
    .ruleForm{
       width: 50%;
    }
    .el-form-item__content{
        float: left;
        margin: auto 0;
    }
</style>
