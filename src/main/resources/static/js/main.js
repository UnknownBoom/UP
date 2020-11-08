import App from 'pages/App.vue'
import Vue from "vue"
import vuetify  from "plugins/vuetify/Vuetify"
import 'vuetify/dist/vuetify.min.css'
import store from 'plugins/store/store.js'
import router from "plugins/router/router";
import 'core-js/stable';
import 'regenerator-runtime/runtime';
import 'plugins/validator/Validator.js'



new Vue({
    store,
    router,
    el: '#app',
    vuetify,
    render: a => a(App)
})

