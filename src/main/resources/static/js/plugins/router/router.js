import Vue from "vue"
import VueRouter from 'vue-router'
import Card_table from "pages/CardTable.vue";
import Bankomat from "pages/Bankomat.vue";
Vue.use(VueRouter)

const routes=[
    {path: '/cards',component: Card_table},
    {path: '/bankomat',component: Bankomat}
]

export default new VueRouter({
    mode:'history',
    routes

})