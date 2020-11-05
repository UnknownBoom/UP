import App from 'pages/App.vue'
import Vue from "vue"
import Vuetify from "vuetify"
import 'vuetify/dist/vuetify.min.css';
import VueResource from 'vue-resource'
import store from 'plugins/store.js'


Vue.use(Vuetify)
Vue.use(VueResource)


const dbApi = Vue.resource('/card{/card_number}')
new Vue({
    store,
    el: '#app',
    vuetify: new Vuetify(),
    render: a => a(App)
})

