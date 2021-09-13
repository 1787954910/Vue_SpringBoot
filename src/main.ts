import { createApp } from 'vue'
import App from './App.vue'
import axios from "axios";
import VueAxios from "vue-axios";
// @ts-ignore
import router from './router/index'
import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css';
// @ts-ignore
import store from './store'

import './assets/css/global.css'
import myHeader from '../src/components/myHeader.vue'
import Course from '../src/components/course.vue'
import Questions from '../src/components/questions.vue'
import Grades from '../src/components/grades.vue'
import allStudents from '../src/components/allStudents.vue'
import personCenter from '../src/components/personCenter.vue'

axios.defaults.headers.post['Content-Type']='application/json'

const app = createApp(App).use(store)
app.use(store).use(router).use(ElementPlus).use(VueAxios,axios).mount('#app')

app.component('myHeader',myHeader)
app.component('course',Course)
app.component('questions',Questions)
app.component('grades',Grades)
app.component('allStudents',allStudents)
app.component('personCenter',personCenter)