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
                                            readonly
                                    ></v-text-field>
                                    <validation-provider
                                            v-slot="{ errors }"
                                            name="Сумма снятия"
                                            :rules="'required|numeric|min_value:50|max:7|max_value:' + current_card.money"
                                    >
                                        <v-text-field
                                                label="Сумма снятия"
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
                        <div :style="{color: withdrawMoneyError=='Операция прошла успешно'?'green':'red'}">{{withdrawMoneyError}}</div>

                        <v-spacer></v-spacer>
                        <v-btn
                                color="blue darken-1"
                                text
                                @click="confirm()"
                                :disabled="invalid"
                        >
                            Снять
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
    import {ValidationObserver, ValidationProvider } from 'vee-validate'

    export default {
        components:{
            ValidationProvider,
            ValidationObserver,
        },
        data:()=>({
            dialog:false,
            withdrawMoneyError:'',
            money:0
        }),
        watch: {
            dialog (val) {
                val || this.close()
            }
        },
        methods:{
            ...mapActions(['removeMoneyAction']),
            close(){
                this.dialog = false
                this.money = 0;
                this.withdrawMoneyError = ''
            },
            confirm(){
                this.removeMoneyAction(this.money).then(r=>this.withdrawMoneyError = r)

            }
        },
        computed:{
            ...mapGetters(['current_card'])
        }
    }
</script>

<style scoped>

</style>