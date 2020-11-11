import Vue from 'vue'

import cardResource from 'plugins/resource/CardResource.js'
import authResource from 'plugins/resource/AuthResource'
import operationResource from 'plugins/resource/OperationResource'

export default {
    add: card => cardResource.post('', card),
    update: card => cardResource.put('', card),
    remove: number => cardResource.delete(`/${number}`),
    get: number =>cardResource.get(`/${number}`),
    auth: card =>authResource.post(`${card.number}`,card),
    addMoney: (number,sum) => operationResource.put(`/add/${number}`,{decimal:sum}),
    removeMoney: (number,sum) => operationResource.put(`/remove/${number}`,{decimal:sum}),
    transferMoney: (fromCard,toCard,sum) => operationResource.put(`/transfer/${fromCard}/${toCard}`,{decimal:sum})
}