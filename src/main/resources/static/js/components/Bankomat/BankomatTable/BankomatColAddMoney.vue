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
                        Положить деньги
                    </v-card>
                </template>
                <v-card>
                    <v-card-title>
                        <span class="headline">Положить деньги</span>
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
                                            name="Сумма внесения"
                                            :rules="'required|numeric|min_value:50|max:7'"
                                    >
                                        <v-text-field
                                                label="Сумма внесения"
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
                        <div :style="{color: addMoneyError=='Операция прошла успешно'?'green':'red'}">{{addMoneyError}}</div>
                        <v-spacer></v-spacer>
                        <v-btn
                                color="blue darken-1"
                                text
                                @click="confirm()"
                                :disabled="invalid"
                        >
                            Внести
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
            addMoneyError:'',
            money:0
        }),
        watch: {
            dialog (val) {
                val || this.close()
            }
        },
        methods:{
            ...mapActions(['addMoneyAction']),
            close(){
                this.dialog = false
                this.money = 0;
                this.addMoneyError = ''
            },
            confirm(){
                this.addMoneyAction(this.money).then(r=>this.addMoneyError = r)
            }
        },
        computed:{
            ...mapGetters(['current_card'])
        }
    }
</script>

<style scoped>

</style>