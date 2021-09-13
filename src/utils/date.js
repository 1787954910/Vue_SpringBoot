
// 日期时间格式化过滤器（2019-12-17 15:31:00）
import axios from "axios";

export function formatDate(value) {
    let date = new Date(value);
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let seconds = date.getSeconds();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}

export function countdown(value) {
    let time = value
    let h=Math.floor((time/3600)%24);
    let m=Math.floor((time/60)%60);
    let s=Math.floor(time%60);
    if(s<10){
        s = "0" + s;
    }
    if(m<10){
        m = "0" + m;
    }
    if(h<10){
        h = "0" + h;
    }
    return h + ":" + m + ":" + s;
}

export function ff(index,radio,picks,answer) {
    let m = 100 / index;
    let n = 0;
    for (let i = 0; i < index; i++) {
        radio[i] = picks[i][radio[i] - 1];
        if (radio[i] === answer[i]) {
            n = n + 1;
        }
    }
    return Number(n * m).toFixed(1);//保留一位小数
}
