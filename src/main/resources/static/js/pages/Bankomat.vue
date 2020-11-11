<template>
    <v-container class="grey lighten-5">
        <bankomat-rows></bankomat-rows>
    </v-container>
</template>

<script>
    import {mapGetters,mapActions} from 'vuex'
    import router from "plugins/router/router";
    import BankomatRows from "components/Bankomat/BankomatTable/BankomatRows.vue";
    export default {
        components: {
            BankomatRows
        },
        data: () => ({
        }),
        computed:{
            ...mapGetters(['current_card'])
        },
        methods:{
        },
        beforeMount() {
            if(this.current_card==null || this.current_card == undefined && this.current_card!=""){
                router.push({name:'authorization'})
            }
        }
    }
</script>

<style scoped>

</style>