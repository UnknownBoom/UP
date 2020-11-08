<template>
    <v-container class="grey lighten-5">
        <v-row no-gutters>
            <v-col cols="12" sm="12">
                <v-card class="pa-2 ma-4" outlined align="center">Операции</v-card>
            </v-col>
            <v-col
                    cols="12"
                    sm="6"
            >
                <v-card
                        class="pa-2 ma-4"
                        outlined
                        tile
                >
                    Баланс
                </v-card>
                <v-card
                        class="pa-2 ma-4"
                        outlined
                        tile
                >
                    Снять деньги
                </v-card>
            </v-col>
            <v-col
                    cols="12"
                    sm="6"
            >
                <v-card
                        class="pa-2 ma-4"
                        outlined
                        tile
                >
                    Внести деньги
                </v-card>
                <v-card
                        class="pa-2 ma-4"
                        outlined
                        tile
                >
                    Перевести
                </v-card>
            </v-col>
        </v-row>

    </v-container>
</template>

<script>
    export default {
        name: "Bankomat"
    }
</script>

<style scoped>

</style>