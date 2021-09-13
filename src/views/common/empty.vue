<template>

</template>

<script>
    export default {
        name: "empty",
        created(){
            this.$router.replace({ path:'/teacher2-1' })
            console.log(this.$route.query.path)
        }
    }
</script>

<style scoped>

</style>
