<template>
    <v-simple-table>
        <template v-slot:default>
            <thead>
            <tr>
                <th class="text-left">
                    card_number
                </th>
                <th class="text-left">
                    owner_name
                </th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="card in cards" :key="card.card_number">
                <td>{{ card.card_number }}</td>
                <td>{{ card.owner_name }}</td>
            </tr>

            </tbody>
        </template>
    </v-simple-table>
</template>

<script>
    import {mapGetters} from "vuex"
    export default {
        name: "Card_table",
        data() {
            return {}
        },
        created() {
            console.log(this.cards)
        },
        computed:mapGetters(['cards'])
    }
</script>

<style scoped>

</style>