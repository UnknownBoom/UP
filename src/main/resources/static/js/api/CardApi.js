import Vue from 'vue'

import cardResource from 'plugins/resource/CardResource.js'

export default {
    add: card => cardResource.post('', card),
    update: card => cardResource.put('', card),
    remove: number => cardResource.delete(`/${number}`),
    get: number =>cardResource.get(`/${number}`)
}