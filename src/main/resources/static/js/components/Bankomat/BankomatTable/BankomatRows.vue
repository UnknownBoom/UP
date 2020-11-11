<template>
    <v-row no-gutters justify="center">
        <v-col cols="12" sm="12" align="center">
            <v-card class="pa-2 ma-4" outlined align="center">Операции</v-card>
        </v-col>
        <BankomatColBalance></BankomatColBalance>
        <BankomatColWithdrawMoney></BankomatColWithdrawMoney>
        <BankomatColTransferMoney></BankomatColTransferMoney>
        <BankomatColAddMoney></BankomatColAddMoney>

    </v-row>
</template>

<script>
    import {ValidationObserver, ValidationProvider,extend } from 'vee-validate'

    import BankomatColBalance from "components/Bankomat/BankomatTable/BankomatColBalance.vue";
    import BankomatColAddMoney from "components/Bankomat/BankomatTable/BankomatColAddMoney.vue";
    import BankomatColWithdrawMoney from "components/Bankomat/BankomatTable/BankomatColWithdrawMoney.vue";
    import BankomatColTransferMoney from "components/Bankomat/BankomatTable/BankomatColTransferMoney.vue";

    import router from "plugins/router/router";
    export default {
        components: {
            ValidationProvider,
            ValidationObserver,
            BankomatColBalance,
            BankomatColAddMoney,
            BankomatColWithdrawMoney,
            BankomatColTransferMoney
        },
        data: () => ({
        }),
        computed:{
        },
        methods:{
        },
    }
</script>
