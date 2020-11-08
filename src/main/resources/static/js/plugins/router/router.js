import Vue from "vue"
import VueRouter from 'vue-router'
import Card_table from "pages/CardTable.vue";
Vue.use(VueRouter)

const routes=[
    {path:'/',component:Card_table }
]

export default new VueRouter({
    mode:'history',
    routes

})