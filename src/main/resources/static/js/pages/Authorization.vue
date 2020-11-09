<template>
    <validation-observer
            ref="observer"
            v-slot="{ invalid }">
            <v-card class="mx-auto " max-width="95%" style="opacity: 0.95">
                <v-card-title primary-title class="justify-center">Авторизация</v-card-title>
                <v-card-text>
                    <validation-provider
                            v-slot="{ errors }"
                            name="cardNumber"
                            rules="required|length:16|numeric"
                    >
                        <v-text-field
                                v-model="cardNumber"
                                :counter="16"
                                :error-messages="errors"
                                label="Номер карты"
                        ></v-text-field>
                    </validation-provider>
                    <validation-provider
                            v-slot="{ errors }"
                            name="cardPassword"
                            rules="required|length:4|numeric"
                    >
                        <v-text-field
                                v-model="cardPassword"
                                :counter="4"
                                :error-messages="errors"
                                label="Пароль от карты"
                        ></v-text-field>
                    </validation-provider>
                </v-card-text>
                <v-card-actions>
                    <div class="red--text ml-4">
                        {{$route.query.error}}
                    </div>
                    <v-spacer></v-spacer>
                    <v-btn
                            color="blue darken-1"
                            text
                            :disabled="invalid"
                            @click="authCard()"
                    >
                        Войти
                    </v-btn>
                </v-card-actions>
            </v-card>
        </validation-observer>
</template>

<script>
    import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'
    import {mapActions,mapGetters} from "vuex"
    import cardApi from 'api/CardApi'
    import router from "../plugins/router/router";
    export default {
        components: {
            ValidationProvider,
            ValidationObserver,
        },
        data:()=>({
                cardNumber:'',
            cardPassword:''
        }),
        computed:{
            ...mapGetters(['current_card'])
        },
        methods:{
            ...mapActions(['authCardAction']),
            authCard(){
                this.authCardAction({number: this.cardNumber,password:this.cardPassword})
            }
        },
        beforeMount() {
            if(this.current_card!=null && this.current_card!=undefined && this.current_card!=""
                && this.current_card.number !=null && this.current_card.number!=undefined){
                router.push({name: 'bankomat'})
            }

        }
    }
</script>

<style scoped>

</style>