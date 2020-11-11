<template>
    <v-col
            cols="12"
            sm="6"
            align="center"
    >
        <v-dialog
                v-model="dialog"
                max-width="600px"
        >
            <template v-slot:activator="{ on, attrs }">
                <v-card class="pa-2 ma-4"
                        v-bind="attrs"
                        v-on="on" outlined
                        @click="updateCardInfo()"
                        align="center">
                    Баланс
                </v-card>
            </template>
            <v-card>
                <v-card-title>
                    <span class="headline">Баланс</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col
                                    cols="12"
                                    sm="12"

                            >
                                <v-text-field
                                        label="Номер карты"
                                        :value="current_card.number"
                                        disabled
                                        prepend-icon="mdi-card"
                                ></v-text-field>
                            </v-col>
                            <v-col
                                    cols="12"
                                    sm="12"
                            >
                                <v-text-field
                                        label="Баланс"
                                        :value="current_card.money"
                                        prefix="₽"
                                        readonly
                                        prepend-icon="mdi-money"
                                ></v-text-field>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <div style="color: red">{{getMoneyError}}</div>
                    <v-spacer></v-spacer>
                    <v-btn
                            color="blue darken-1"
                            text
                            @click="close()"
                    >
                        Закрыть
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-col>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";

    export default {
        data:()=>({
            dialog:false,
            getMoneyError:''
        }),
        watch: {
            dialog (val) {
                val || this.close()
            }
        },
        methods:{
            ...mapActions(['updateCurrentCardAction']),
            updateCardInfo(){
                this.updateCurrentCardAction().then(r=>{ if(r!="Операция прошла успешно")this.getMoneyError = r})
            },
            close(){
                this.dialog = false
                this.getMoneyError = ''
            }
        },
        computed:{
            ...mapGetters(['current_card'])
        },
    }
</script>

<style scoped>

</style>