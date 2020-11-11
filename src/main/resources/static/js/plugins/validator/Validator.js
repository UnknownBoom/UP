

import { ValidationProvider } from 'vee-validate'
import Vue from "vue"
import { localize } from 'vee-validate'
import ru from 'vee-validate/dist/locale/ru.json';


Vue.component('ValidationProvider', ValidationProvider)
localize('ru',ru)
