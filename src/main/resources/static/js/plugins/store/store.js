import Vuex from 'vuex'
import Vue from "vue"

import cardResource from 'plugins/resource/CardResource.js'

import createPersistedState from "vuex-persistedstate";

import CardApi from "api/CardApi";
import router from 'plugins/router/router'

Vue.use(Vuex)


export default new Vuex.Store({
    state:{
        cards: [],
        current_card:null,
    },
    plugins: [createPersistedState()],
    getters:{
        cards: state => state.cards,
        current_card: state => state.current_card
    },
    setters:{
        current_card: state => state.current_card
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

            if (deleteIndex > -1) {
                state.cards = [
                    ...state.cards.slice(0, deleteIndex),
                    ...state.cards.slice(deleteIndex + 1)
                ]
            }
        },
        setCurrentCardMutation(state,card){
            state.current_card = card;
        },
        updateCurrentCardMutation(state,card){
            state.current_card = card;
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
        async updateCardAction({commit,state}, card) {
            const result = await CardApi.update(card)
            const data = await result.data
            commit('updateCardMutation', data)
            if(state.current_card !=null && state.current_card !=undefined && state.current_card!=""){
                if(state.current_card.number !=null && state.current_card.number !=undefined
                    && state.current_card.number ==data.number){
                    console.log('god')
                    state.current_card = data
                }
            }
                },
        async removeCardAction({commit,state}, index) {
             const number  = state.cards[index].number
            const result = await CardApi.remove(number)
            if (result.status == 200) {
                commit('deleteCardMutation', index)
            }
        },
        async authCardAction({commit}, card) {
             try{
                 const result = await CardApi.auth(card)
                 const data = await result.data
                 if (result.status == 200) {
                     commit('setCurrentCardMutation', data)
                     router.push('/bankomat')
                 }else{
                     router.push({
                         name: 'authorization',
                         query: { error: 'Неверные данные' }
                     })
                 }
             }catch (e) {
                 router.push({
                     name: 'authorization',
                     query: { error: 'Неверные данные' }
                 })
             }
        },
        async updateCurrentCardAction({commit,state}){
             try{
             if(state.current_card!=null && state.current_card !=undefined && state.current_card!=""){
                 const result = await CardApi.get(state.current_card.number)
                 const data = await result.data
                 commit("setCurrentCardMutation",data)
                 return "Операция прошла успешно"
             }}catch (e) {
                 return "Ошибка при выполнении операции"
             }
        },
        async addMoneyAction({commit,state},sum){
             try{
                 if(state.current_card!=null && state.current_card !=undefined && state.current_card!=""){
                     const result = await CardApi.addMoney(state.current_card.number,sum)
                     const data = await result.data
                     commit("setCurrentCardMutation",data)
                     return "Операция прошла успешно"
                 }
             }catch (e) {
                 return "Ошибка при выполнении операции"
             }
        },
        async removeMoneyAction({commit,state},sum){
            try{
                if(state.current_card!=null && state.current_card !=undefined && state.current_card!=""){
                    const result = await CardApi.removeMoney(state.current_card.number,sum)
                    const data = await result.data
                    commit("setCurrentCardMutation",data)
                    return "Операция прошла успешно"
                }
            }catch (e) {
                if(e.response.status==412){
                    return "Недостаточно денег на счету"
                }else {
                    return "Ошибка при выполнении операции"
                }

            }
        },
        async transferMoneyAction({commit,state},{cardTo,sum}){
            try{
                if(state.current_card!=null && state.current_card !=undefined && state.current_card!=""){
                    const result = await CardApi.transferMoney(state.current_card.number,cardTo,sum)
                    const data = await result.data
                    commit("setCurrentCardMutation",data)
                    return "Операция прошла успешно"
                }
            }catch (e) {
                if(e.response.status==412){
                    return "Недостаточно денег на счету"
                }else {
                    return "Ошибка при выполнении операции"
                }
            }
        },
    }
})