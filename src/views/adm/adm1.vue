<template>
    <div>
        <div class="addButton">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="0" class="demo-ruleForm">
                <el-form-item prop="name">
                    <el-select v-model="ruleForm.name" placeholder="请选择教师">
                        <el-option v-for="(item,index) in ruleForm" :value="item.id">{{item.name}}</el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="setCurrent(tableData[ruleForm.name-1])">搜索</el-button>
                </el-form-item>
            </el-form>
            <el-button type="primary" @click="add">添加</el-button>
            <el-button type="primary" @click="Refresh">点击刷新</el-button>
        </div>
        <el-table ref="singleTable" :data="tableData" highlight-current-row border height="433px">
            <el-table-column prop="id" label="编号" width="125" >
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
            <el-form :model="form" :rules="rules" ref="form" label-width="80px" >
                <el-form-item style="text-align: center">
                    添加教师信息
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
            <el-form :model="forms" :rules="rules" ref="forms" label-width="80px">
                <el-form-item style="text-align: center">
                    修改教师信息
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
    import { ref } from 'vue'

    export default {
        name: "adm1",
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
                currentRow: null,
                input: ref(''),
                tableData: [{
                    id: '',
                    gender: '',
                    name: '',
                    username:'',
                    password:'',
                    phone:'',
                    authority_id:''
                }],
                dialogFormVisible: false,
                dialogVisible: false,
                form: {
                    gender: '',
                    name: '',
                    username:'',
                    password:'',
                    phone:'',
                    authority_id:''
                },
                forms: {
                    id:'',
                    gender: '',
                    name: '',
                    username:'',
                    password:'',
                    phone:'',
                    authority_id:''
                },
                Authorities:{
                    id:'',
                    name:''
                },
                ruleForm:[{
                    id:'',
                    name:''
                }],
                formLabelWidth: '80px',
                rules: {
                    gender: [
                        { required: true, message: '请输入教师性别', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入教师姓名', trigger: 'blur' }
                    ],
                    username: [
                        { required: true, message: '请输入教师用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入用户密码', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, validator: isPhoneAvailable, trigger: 'blur' }
                    ],
                    authority_id:[
                        { required: true, message: '请输入身份ID', trigger: 'blur' }
                    ]
                }
            }
        },
        created() {
            const _this = this
            axios.get('/Authorities').then(resp => {
                _this.Authorities = resp.data
            })
            axios.get('/teacher/findAllTeacher').then(resp => {
                _this.tableData=resp.data
            })
            axios.get('/teacher/findAllTeacher').then(resp => {
                _this.ruleForm=resp.data
            })
        },
        methods:{
            add(){
                this.form={};
                this.dialogFormVisible=true
            },
            Refresh(){
                const _this = this
                axios.get('/teacher/findAllTeacher').then(resp => {
                    _this.tableData=resp.data
                })
            },
            edit(row){
                this.forms=row;
                this.dialogVisible=true;
            },
            deleteCourse(row){
                axios.delete('/teacher/deleteTeacher/'+row.id).then(resp => {
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
                        axios.post('/teacher/saveTeacher',this.form).then(resp => {
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
            saveForms(formName){
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
                        // console.log(data)
                        axios.put('/teacher/updateTeacher',data).then(resp => {
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
            setCurrent(row) {
                this.$refs.singleTable.setCurrentRow(row);
            },
        }
    }
</script>

<style scoped>
    .addButton{
        height: 40px;
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
        margin-bottom: 5px;
    }
    .demo-ruleForm{
        display: flex;
        flex-direction: row;
    }
</style>
