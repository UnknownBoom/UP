import Vuex from 'vuex'
import Vue from "vue"

import cardResource from 'plugins/resource/CardResource.js'

import CardApi from "api/CardApi";

Vue.use(Vuex)


export default new Vuex.Store({
    state:{
        cards: [],
    },
    getters:{
        cards: state => state.cards
    },
    mutations:{
        setCards(state, cards){
            state.cards = cards;
        },
        addCardMutation(state,card){
            state.cards = [
                ...state.cards,
                card
            ]
        },
        updateCardMutation(state,card){
            const updateIndex = state.cards.findIndex(item => item.number === card.number)
            state.cards = [
                ...state.cards.slice(0, updateIndex),
                card,
                ...state.cards.slice(updateIndex + 1)
            ]
        },
        deleteCardMutation(state,deleteIndex){
            //const deleteIndex = state.cards.findIndex(item => item.number === card.number)
            console.log("del")

            if (deleteIndex > -1) {
                state.cards = [
                    ...state.cards.slice(0, deleteIndex),
                    ...state.cards.slice(deleteIndex + 1)
                ]
            }
        },
    },

    actions:{
         getCardsFromServer({commit}){
            const result =  cardResource.request().then(r=> commit('setCards',r.data))
        },
        async addCardAction({commit,state}, card){
            const result = await CardApi.add(card)
            const data = await result.data
            const index  = state.cards.findIndex(t=>t.number == data.number);

            if(index >-1){
                commit("updateCardMutation",data);
            }else{
                commit("addCardMutation",data);
            }
        },
        async updateCardAction({commit}, card) {
            const result = await CardApi.update(card)
            const data = await result.data
            commit('updateCardMutation', data)
        },
        async removeCardAction({commit,state}, index) {
             const number  = state.cards[index].number
            const result = await CardApi.remove(number)
            if (result.status == 200) {
                commit('deleteCardMutation', index)
            }
        },
    }
})