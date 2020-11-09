import Vue from "vue"
import VueRouter from 'vue-router'
import Card_table from "pages/CardTable.vue";
import Bankomat from "pages/Bankomat.vue";
import Authorization from 'pages/Authorization.vue'
Vue.use(VueRouter)

const routes=[
    {path: '/cards',component: Card_table,name:'card'},
    {path: '/bankomat',component: Bankomat,name:'bankomat'},
    {path: '/authorization',component: Authorization,name: 'authorization'},
    //{path:'*',component: }
]

export default new VueRouter({
    mode:'history',
    routes
})