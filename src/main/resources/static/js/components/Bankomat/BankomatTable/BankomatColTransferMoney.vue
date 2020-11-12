<template>
    <v-col
            cols="12"
            sm="6"
            align="center"
    >
        <validation-observer
                ref="observer"
                v-slot="{ invalid }">
            <v-dialog
                    v-model="dialog"
                    max-width="600px"
            >
                <template v-slot:activator="{ on, attrs }">
                    <v-card class="pa-2 ma-4"
                            v-bind="attrs"
                            v-on="on" outlined
                            align="center"
                            style="background: linear-gradient(to right,rgba(142, 158, 171, 0.8), rgba(238, 242, 243, 0.8)); border-radius: 8px;border: 0px">
                        Перевести деньги
                    </v-card>
                </template>
                <v-card style="background: linear-gradient(90deg, rgba(142,158,171,0.8) 0%, rgba(238,242,243,0.8) 45%, rgba(142,158,171,0.8) 100%); border-radius: 8px;border: 0px">
                    <v-card-title>
                        <span class="headline">Перевести деньги</span>
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
                                    <validation-provider
                                            v-slot="{ errors }"
                                            name="Номер карты получателя"
                                            :rules="'required|numeric|length:16'"
                                    >
                                        <v-text-field
                                                label="Номер карты получателя"
                                                v-model="cardTo"
                                                :counter="16"
                                                :error-messages="errors"
                                        ></v-text-field>
                                    </validation-provider>
                                </v-col>
                                <v-col
                                        cols="12"
                                        sm="12"
                                >
                                    <validation-provider
                                            v-slot="{ errors }"
                                            name="Сумма перевода"
                                            :rules="'required|min_value:50|max:12|max_value:' + current_card.money"
                                    >
                                        <v-text-field
                                                label="Сумма перевода"
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
                        <div :style="{color: transferMoneyError=='Операция прошла успешно'?'green':'red'}">{{transferMoneyError}}</div>
                        <v-spacer></v-spacer>
                        <v-btn
                                color="blue darken-1"
                                text
                                @click="confirm()"
                                :disabled="invalid"
                        >
                            Перевести
                        </v-btn>
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
        </validation-observer>
    </v-col>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";
    import {ValidationObserver, ValidationProvider,extend } from 'vee-validate'

    extend("float",(v)=>{ if (value >= 0) {return true;}
            return 'This value must be a positive number';
        }
    )
    export default {
        components:{
            ValidationProvider,
            ValidationObserver,
        },
        data:()=>({
            dialog:false,
            transferMoneyError:'',
            cardTo:null,
            money:0
        }),
        watch: {
            dialog (val) {
                val || this.close()
            }
        },
        methods:{
            ...mapActions(['transferMoneyAction']),
            close(){
                this.dialog = false
                this.money = 0
                this.cardTo = null
                this.transferMoneyError = ''
            },
            confirm(){
                this.transferMoneyAction({cardTo:this.cardTo,sum:this.money}).then(r=>this.transferMoneyError = r)

            }
        },
        computed:{
            ...mapGetters(['current_card'])
        },

    }
</script>


<style scoped>

</style>