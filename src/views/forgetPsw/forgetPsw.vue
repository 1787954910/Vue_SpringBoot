<template>
    <el-container>
        <el-header>重置密码</el-header>
        <el-main>
            <div style="display: flex;flex-direction: column;height: 450px;background-color: honeydew">
                <div style="height:300px;margin-top: 30px ">
                    <router-view></router-view>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        name: "forgetPsw",
        data() {
            return {
                active: 0
            };
        }
    }
</script>

<style scoped>
    .el-header{
        background-color: #2c3e50;
        color: white;
        text-align: center;
        line-height: 60px;
    }

    .el-main {
        background-color: white;
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

</style>
