<template class="bg">
    <v-app >
        <v-parallax src="/static/image/bg.jpg" style="height: 100%;background-size: cover">
            <v-app-bar app dense>
                <v-app-bar-nav-icon  app>HEll</v-app-bar-nav-icon>
                <v-toolbar-title >Title</v-toolbar-title>
            </v-app-bar>
            <v-content>
                <card_table ></card_table>
            </v-content>
        </v-parallax>
    </v-app>

</template>

<script>
    import Card_table from "components/Card_table.vue";
    import {mapGetters} from "vuex"
    export default {
        name: "App",
        components: {Card_table},
        comments:{
            Card_table
        },
        data() {
            return {
            }
        }
    }
</script>

<style scoped>
</style>