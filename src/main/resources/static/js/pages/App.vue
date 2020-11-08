<template class="bg">
    <v-app >
        <v-parallax src="/static/image/bg.jpg" style="height: 100%;background-size: cover">
            <v-app-bar app dense>
                <v-app-bar-nav-icon  app>HEll</v-app-bar-nav-icon>
                <v-toolbar-title >Title</v-toolbar-title>
            </v-app-bar>
            <v-main>
                <v-container fluid>
                    <router-view></router-view>
                </v-container>
            </v-main>

        </v-parallax>
    </v-app>

</template>

<script>
    import {mapGetters} from "vuex"
    export default {
        name: "App",
        data() {
            return {
            }
        },
    }
</script>

<style scoped>
</style>