import resource from 'plugins/resource/resource.js'

export  default resource.create({
    baseURL: '/auth',
    timeout: 10000,
});