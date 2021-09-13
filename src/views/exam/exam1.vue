<template>
    <el-container>
        <el-header>
           <my-header></my-header>
        </el-header>
        <el-main>
            <div class="exam">
                <div style="width: 100%;height: 50px;line-height: 50px;border-bottom: 1px solid darkgray">
                    <span style="margin: 20px">{{course}}</span>
                    <span style="margin: 20px">满分100分</span>
                    <span style="margin: 20px">单选题</span>
                    <span style="margin: 20px">考试时间：20分钟</span>
                    <span style="margin: 20px">倒计时：{{timer}}</span>
                </div>
                <el-card class="box-card" v-for="(item1,index) in questions" :index="index+'='">
                    <template #header>
                        <div class="card-header">
                            <span>{{index+1}} {{item1.question}}</span>
                        </div>
                    </template>
                    <el-radio-group v-model="radio[index]" class="radioGroup">
                        <el-radio :label="1">{{item1.pick1}}</el-radio>
                        <el-radio :label="2">{{item1.pick2}}</el-radio>
                        <el-radio :label="3">{{item1.pick3}}</el-radio>
                        <el-radio :label="4">{{item1.pick4}}</el-radio>
                    </el-radio-group>
                </el-card>
                <div>
                    <el-button type="primary" @click="onSubmit(f)">提交</el-button>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script>
    import axios from 'axios'
    import store from '@/store'
    import { formatDate,countdown,ff } from "../../utils/date";
    import MyHeader from "../../components/myHeader";

    export default {
        name: "exam1",
        components: {MyHeader},
        data(){
            return{
                timer:'',//时间倒计时
                radio:[], //保存考生所有选择题的选项
                index: '',//选择题数目
                course:'',//课程名称
                questions:[{ // 所有选择题集合
                    id:'',
                    question:'',
                    pick1:'',
                    pick2:'',
                    pick3:'',
                    pick4:'',
                }],
                answer1:[], // 答案集合
                picks1:[] // 所有选项集合
            }
        },
        created() {
            const _this=this
            axios.get('/course/findCourseById/'+this.$route.query.id).then(resp => {
                if (resp.status === 200){
                    _this.course=resp.data.course
                    _this.questions=resp.data.questions;
                    //选择题数目
                    _this.index=resp.data.questions.length
                    for(let i=0;i<resp.data.questions.length;i++){
                        //所有正确答案集合
                        _this.answer1[i] = resp.data.questions[i].correct
                        //所有选项集合
                        _this.picks1[i]=[resp.data.questions[i].pick1,resp.data.questions[i].pick2,resp.data.questions[i].pick3,resp.data.questions[i].pick4]
                    }
                    }
            });
            _this.countDowns(1200)
        },
        methods:{
            countDowns(number){
                setInterval(() => {  // 设置倒计时
                    let num = number--
                    this.timer = countdown(num)
                    if(this.timer === "00:00:00"){
                        //倒计时结束自动提交试卷
                        this.onSubmit(ff(this.index,this.radio,this.picks1,this.answer1))
                    }
                },1000)
            },
            onSubmit(f){
                //定义时间格式
                let nowDate = formatDate(new Date(), 'yyyy-MM-dd HH:mm:ss')
                // 上传成绩信息
                let data={
                    grades:f,
                    student_id:store.state.user.id,
                    course_id:this.$route.query.id,
                    updateTime:nowDate
                }
                axios.post('/grades/saveGrades',data)
                this.$router.push('/student2-1')
                this.clearTimer()
            },
            clearTimer(){
                //清除定时器
                clearInterval(this.timer);
                this.timer = null;
            },
        },
        computed:{
            // 计算成绩
            f:function () {
                let m = 100 / this.index;
                let n = 0;
                for (let i = 0; i < this.index; i++) {
                    this.radio[i] = this.picks1[i][this.radio[i] - 1];
                    if (this.radio[i] === undefined){
                        this.radio[i] = ""
                    }
                    if (this.radio[i] === this.answer1[i]) {
                        n = n + 1;
                    }
                }
                return Number(n * m).toFixed(1);//保留一位小数
            },
        }
    }
</script>

<style scoped>
    .exam{
        min-height: 600px;
        width: 90%;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-top: 20px;
        background-color: honeydew;
        margin: 0 auto;
    }
    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        height: 10px;
    }
    .box-card {
        width: 780px;
        margin: 10px;
    }
    .radioGroup{
        display: flex;
        flex-direction: column;
        align-items: flex-start;
    }
</style>