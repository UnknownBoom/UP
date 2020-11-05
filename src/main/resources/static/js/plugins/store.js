import Vuex from 'vuex'
import Vue from "vue"

Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        cards: frontendData,
    },
    getters:{
        cards: state => state.cards
    },
    mutations:{
        addCardMutation(state,card){
            state.cards = [
                ...state.cards,
                card
            ]
        }
    },

    actions:{
        async addCardAction({commit}, card){
            const result = await dbApi.add(card)
            const data = await result.json();
            const index  = this.cards.findIndex(t=>t.card_number == data.card_number);

            if(index >-1){
               commit("updateCardMutation",data);
            }else{
                commit("addCardMutation",data);
            }
        }
    }
})