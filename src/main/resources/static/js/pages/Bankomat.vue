<template>
    <v-container class="grey lighten-5">
        <v-row no-gutters justify="center">
            <v-col cols="12" sm="12" align="center">
                <v-card class="pa-2 ma-4" outlined align="center">Операции</v-card>
            </v-col>
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
                                @click="updateMoney()"
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
                            <v-spacer></v-spacer>
                            <v-btn
                                    color="blue darken-1"
                                    text
                                    @click="dialog = false"
                            >
                                Close
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
                <validation-observer
                        ref="observer"
                        v-slot="{ invalid }">
                <v-dialog
                        v-model="dialog1"
                        max-width="600px"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-card class="pa-2 ma-4"
                                v-bind="attrs"
                                v-on="on" outlined
                                @click="updateMoney()"
                                align="center">
                            Снять деньги
                        </v-card>
                    </template>
                    <v-card>
                        <v-card-title>
                            <span class="headline">Снять деньги</span>
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
                                                ref="current_money"
                                                readonly

                                        ></v-text-field>
                                        <validation-provider
                                                v-slot="{ errors }"
                                                name="cardNumber"
                                                :rules="'required|numeric|max:7|max_value:' + current_card.money"
                                        >
                                        <v-text-field
                                                label="Снять"
                                                prefix="₽"
                                                v-model="money"
                                                :error-messages="errors"
                                        ></v-text-field>
                                        </validation-provider>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                                    color="blue darken-1"
                                    text
                                    @click="dialog1 = false"
                                    :disabled="invalid"
                            >
                                save
                            </v-btn>
                            <v-btn
                                    color="blue darken-1"
                                    text
                                    @click="dialog1 = false"
                            >
                                Close
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
                </validation-observer>
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
    import {ValidationObserver, ValidationProvider,extend } from 'vee-validate'
    import {mapGetters,mapActions} from 'vuex'
    import router from "plugins/router/router";
    export default {
        components: {
            ValidationProvider,
            ValidationObserver,
        },
        data: () => ({
            dialog: false,
            dialog1:false,
            money:0,
        }),
        computed:{
            ...mapGetters(['current_card'])
        },
        methods:{
            ...mapActions(['updateCurrentCardAction']),
            updateMoney(){
                this.updateCurrentCardAction()
            }
        },
        beforeMount() {
            if(this.current_card==null || this.current_card == undefined && this.current_card!=""){
                router.push({name:'authorization'})
            }else{
                this.updateMoney()
            }
        }
    }
</script>

<style scoped>

</style>