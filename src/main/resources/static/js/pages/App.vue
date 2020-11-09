<template>
    <v-app>
        <v-parallax src="/static/image/bg.jpg" style="height: 100%;background-size: cover">
            <v-navigation-drawer app
                    v-model="drawer"
                    absolute
                    bottom
                    temporary
                    src="https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg"
            >
                <v-list
                        nav
                        dense
                >
                    <v-list-item-group
                            v-model="group"
                            active-class="deep-purple--text text--accent-4"
                    >
                        <v-list-item  @click="goToMain()">
                            <v-list-item-title>Главная страница</v-list-item-title>
                        </v-list-item>
                        <v-list-item v-if="current_card!=null && current_card!=undefined && current_card!=''" @click="goToBankomat()">
                            <v-list-item-title >Банкомат</v-list-item-title>
                        </v-list-item>
                        <v-list-item  @click="goToCards()">
                            <v-list-item-title>Все карты</v-list-item-title>
                        </v-list-item>
                        <v-list-item v-if="current_card==null || current_card==undefined || current_card==''" @click="goToAuth()">
                            <v-list-item-title>Авторизация</v-list-item-title>
                        </v-list-item>
                    </v-list-item-group>
                </v-list>
            </v-navigation-drawer>
            < <v-app-bar app>
                <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
                <v-toolbar-title >Title</v-toolbar-title>
            <v-spacer></v-spacer>
            <div v-if="current_card!=null && current_card!=undefined && current_card!=''" class="pa-3" style="max-width: 300px">
                номер карты: {{current_card.number}}
            </div>
            <v-btn v-if="current_card!=null && current_card!=undefined && current_card!=''" @click="logOut()">LogOut</v-btn>
            <v-btn v-else @click="goToAuth()">Login</v-btn>
            </v-app-bar>
            <v-content>
                <v-container fluid>
                    <router-view/>
                </v-container>
            </v-content>
        </v-parallax>
    </v-app>

</template>

<script>
    import {mapGetters,mapActions} from "vuex"
    import router from "../plugins/router/router";
    export default {
        name: "App",
        data() {
            return {
                drawer: false,
                group: null,
            }
        },
        computed:{
            ...mapGetters(['current_card'])
        },
        watch: {
            group () {
                this.drawer = false
            },
        },
        methods:{
            ...mapActions(['setCurrentCard']),
            goToCards(){
                this.$router.push("/cards")
            },
            goToMain(){
                this.$router.push("/")
            },
            goToBankomat(){
                this.$router.push("/bankomat")
            },
            goToAuth(){this.$router.push("/authorization")},
            logOut(){
                this.setCurrentCard();
                router.push({name:'authorization'})
            }
        }
    }
</script>

<style scoped>
</style>