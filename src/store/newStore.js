import { createStore } from 'vuex'
import user from "./index";
import getters from "./getters";

export default createStore({
    modules:{
        user
    },
    getters
})