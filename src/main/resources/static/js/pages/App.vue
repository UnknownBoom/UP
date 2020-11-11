<template >
    <v-app >
        <v-navigation-drawer app
                             v-model="drawer"
                             absolute
                             bottom
                             temporary
                             color="rgba(142, 158, 171, 1)"
        >
            <v-list
                    nav
                    dense
                    align="center"
            >
                <v-list-item-group
                        v-model="group"
                        active-class="deep-purple--text text--accent-4"
                        align="center"

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


        <v-app-bar app style="background: linear-gradient(to right,rgba(142, 158, 171, 0.7), rgba(238, 242, 243, 0.5)); color: rgb(41, 50, 60);">
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title >Title</v-toolbar-title>
            <v-spacer></v-spacer>
            <div v-if="current_card!=null && current_card!=undefined && current_card!=''" class="pa-3" style="max-width: 300px">
                номер карты: {{current_card.number}}
            </div>
            <v-btn v-if="current_card!=null && current_card!=undefined && current_card!=''" @click="logOut()">LogOut</v-btn>
            <v-btn v-else @click="goToAuth()">Login</v-btn>
        </v-app-bar>
        <v-content style="background: url('static/image/bg.jpg') no-repeat center center fixed ;background-size: cover;">
            <v-container fluid>
                <router-view/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import {mapGetters,mapActions,mapMutations} from "vuex"
    import router from "plugins/router/router";

    export default {
        components:{

        },
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
            ...mapMutations(['setCurrentCardMutation']),
            goToAuth(){this.$router.push("/authorization")},
            goToCards(){
                this.$router.push("/cards")
            },
            goToMain(){
                this.$router.push("/")
            },
            goToBankomat(){
                this.$router.push("/bankomat")
            },
            logOut(){
                this.setCurrentCardMutation(null);
                router.push({name:'authorization'})
            }
        }
    }
</script>

<style scoped >
</style>