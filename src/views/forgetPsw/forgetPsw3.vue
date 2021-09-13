<template>
    <div>
        <div class="psw1">
            重置密码...
        </div>
        <div class="psw2">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">下一步</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import store from '@/store'
    export default {
        name: "forgetPsw3",
        data(){
            let validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            let validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.pass) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return{
                ruleForm: {
                    pass: '',
                    checkPass: '',
                },
                rules: {
                    pass: [
                        { required: true,validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { required: true,validator: validatePass2, trigger: 'blur' }
                    ]
                }
            };
        },
        methods:{
            submitForm(formName) {
                // let data={
                //     id:store.state.user1.id,
                //     name:store.state.user1.name,
                //     phone:store.state.user1.phone,
                //     username:store.state.user1.username,
                //     password:this.ruleForm.checkPass,
                //     gender:store.state.user1.gender,
                //     authority_id:store.state.user1.authority_id
                // }
                let data={
                    id:this.$route.query.id,
                    name:this.$route.query.name,
                    gender:this.$route.query.gender,
                    phone:this.$route.query.phone,
                    username:this.$route.query.username,
                    authority_id:this.$route.query.authority_id,
                    password:this.ruleForm.checkPass,
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.$route.query.authority)
                        this.data=this.$route.query.user1
                        console.log(data)
                        if(this.$route.query.authority === 'ROLE_ADMIN'){
                            axios.put('/admin/updateAdmin',data).then(resp => {
                                if(resp.data === 'success'){
                                    this.$router.push('/forgetPsw4')
                                }
                            })
                        }else if(this.$route.query.authority === 'ROLE_TEACHER'){
                            axios.put('/teacher/updateTeacher',data).then(resp => {
                                if(resp.data === 'success'){
                                    this.$router.push('/forgetPsw4')
                                }
                            })
                        }else if(this.$route.query.authority === 'ROLE_STUDENT' ){
                            console.log(data)
                            axios.put('/student/updateStudent',data).then(resp => {
                                if(resp.data === 'success'){
                                    this.$router.push('/forgetPsw4')
                                }
                            })
                        }
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
    .psw1{
        height: 50px;
        line-height: 50px;
        margin-top: 30px;
        text-align: left;
        margin-bottom: 30px;
        padding-left: 410px;
    }
    .psw2{
        height: 250px;
        justify-content: center;
    }
    .demo-ruleForm{
        width: 50%;
        margin: 0 auto;
    }
</style>
