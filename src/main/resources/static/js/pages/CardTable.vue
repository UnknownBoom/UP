<template>
    <v-data-table
            :headers="headers"
            :items="cards"
            sort-by="number"
            class="elevation-1"
            :search="search"
    >
        <template v-slot:top>
            <v-toolbar flat class="py-4" >
                <template>
                    <v-text-field
                            v-model="search"
                            label="Search"
                            class="mx-4"
                    ></v-text-field>
                </template>
                <v-spacer></v-spacer>
                <v-dialog
                        v-model="dialog"
                        max-width="700px"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn
                                color="primary"
                                dark
                                class="mb-2"
                                v-bind="attrs"
                                v-on="on"
                        >
                            New Item
                        </v-btn>
                    </template>
                    <v-card>
                        <validation-observer
                                ref="observer"
                                v-slot="{ invalid}"
                        >
                            <v-card-title>
                                <span class="headline">{{ formTitle }}</span>
                            </v-card-title>

                            <v-card-text>
                                <v-container>
                                    <v-row>
                                        <v-col cols="12" sm="6" md="4">
                                            <ValidationProvider name="Номер карты" rules="required|numeric|length:16" v-slot="{ errors, valid  } ">
                                                <v-text-field
                                                        v-model="editedItem.number"
                                                        label="Номер карты"
                                                        :counter="16"
                                                        :success="valid"
                                                        :error-messages="errors"
                                                        :disabled="formTitle == 'Edit Cards'"
                                                ></v-text-field>
                                            </ValidationProvider>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="4">
                                            <v-menu
                                                    ref="menu1"
                                                    v-model="menu1"
                                                    :close-on-content-click="false"
                                                    transition="scale-transition"
                                                    offset-y
                                                    max-width="390px"
                                                    min-width="290px"
                                            >
                                                <template v-slot:activator="{ on, attrs }">
                                                    <ValidationProvider name="Дата получения" rules="required" v-slot="{ errors, valid  }">

                                                        <v-text-field
                                                                v-model="editedItem.issue_date"
                                                                label="Дата получения"
                                                                persistent-hint
                                                                prepend-icon="mdi-calendar"
                                                                v-bind="attrs"
                                                                ref="issue_date"
                                                                v-on="on"
                                                                :success="valid"
                                                                :error-messages="errors"
                                                        ></v-text-field>
                                                    </ValidationProvider>
                                                </template>
                                                <v-date-picker
                                                        v-model="editedItem.issue_date"
                                                        no-title
                                                        @input="menu1 = false"
                                                ></v-date-picker>
                                            </v-menu>
                                        </v-col>

                                        <v-col cols="12" sm="6" md="4">
                                            <v-menu
                                                    ref="menu2"
                                                    v-model="menu2"
                                                    :close-on-content-click="false"
                                                    transition="scale-transition"
                                                    offset-y
                                                    max-width="290px"
                                                    min-width="290px"
                                            >
                                                <template v-slot:activator="{ on, attrs }">
                                                    <ValidationProvider name="Дата получения" rules="required" v-slot="{ errors, valid  }">
                                                        <v-text-field
                                                                v-model="editedItem.end_date"
                                                                label="Дата окончания"
                                                                ref="end_date"
                                                                persistent-hint
                                                                prepend-icon="mdi-calendar"
                                                                v-bind="attrs"
                                                                v-on="on"
                                                                :success="valid"
                                                                :error-messages="errors"
                                                        ></v-text-field>
                                                    </ValidationProvider>
                                                </template>
                                                <v-date-picker
                                                        v-model="editedItem.end_date"
                                                        no-title
                                                        @input="menu2 = false"
                                                ></v-date-picker>
                                            </v-menu>


                                        </v-col>
                                        <v-col
                                                cols="12"
                                                sm="6"
                                                md="4"
                                        >
                                            <ValidationProvider name="Имя" rules="required|alpha|max:50" v-slot="{ errors, valid  }">
                                                <v-text-field
                                                        v-model="editedItem.owner_name"
                                                        label="Имя"
                                                        :success="valid"
                                                        :error-messages="errors"
                                                        :counter="50"
                                                ></v-text-field>
                                            </ValidationProvider>
                                        </v-col>
                                        <v-col
                                                cols="12"
                                                sm="6"
                                                md="4"
                                        >
                                            <ValidationProvider name="Фамилия" rules="required|alpha|max:50" v-slot="{ errors, valid  }">
                                                <v-text-field
                                                        v-model="editedItem.owner_surname"
                                                        label="Фамилия"

                                                        :success="valid"
                                                        :error-messages="errors"
                                                        :counter="50"
                                                ></v-text-field>
                                            </ValidationProvider>
                                        </v-col>
                                        <v-col
                                                cols="12"
                                                sm="6"
                                                md="4"
                                        >
                                            <ValidationProvider name="Отчество" rules="required|alpha|max:50" v-slot="{ errors, valid  }">
                                                <v-text-field
                                                        v-model="editedItem.owner_patronymic"
                                                        label="Отчество"

                                                        :success="valid"
                                                        :error-messages="errors"
                                                        :counter="50"
                                                ></v-text-field>
                                            </ValidationProvider>
                                        </v-col>
                                        <v-col
                                                cols="12"
                                                sm="6"
                                                md="4"
                                        >
                                            <ValidationProvider name="Cvv2" rules="required|digits:3" v-slot="{ errors, valid  }">
                                                <v-text-field
                                                        v-model="editedItem.cvv2"
                                                        label="Cvv2"
                                                        :success="valid"
                                                        :error-messages="errors"
                                                        :counter="3"
                                                ></v-text-field>
                                            </ValidationProvider>
                                        </v-col>
                                        <v-col
                                                cols="12"
                                                sm="6"
                                                md="4"
                                        >
                                            <ValidationProvider name="password" rules="required|digits:4" v-slot="{ errors, valid  }">
                                                <v-text-field
                                                        v-model="editedItem.password"
                                                        label="password"
                                                        :success="valid"
                                                        :error-messages="errors"
                                                        :counter="50"
                                                ></v-text-field>
                                            </ValidationProvider>
                                        </v-col>
                                    </v-row>
                                </v-container>

                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                        color="blue darken-1"
                                        text
                                        @click="close"
                                >
                                    Cancel
                                </v-btn>
                                <v-btn
                                        color="blue darken-1"
                                        text
                                        @click="save"
                                        :disabled="invalid"
                                >
                                    Save
                                </v-btn>
                            </v-card-actions>
                        </validation-observer>
                    </v-card>

                </v-dialog>
                <v-dialog v-model="dialogDelete" max-width="500px">
                    <v-card>
                        <v-card-title class="headline">Are you sure you want to delete this item?</v-card-title>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
                            <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
                            <v-spacer></v-spacer>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-toolbar>
        </template>
        <template v-slot:item.actions="{ item }">
            <v-icon
                    small
                    class="mr-2"
                    @click="editItem(item)"
            >
                mdi-pencil
            </v-icon>
            <v-icon
                    small
                    @click="deleteItem(item)"
            >
                mdi-delete
            </v-icon>
        </template>

        <template v-slot:item.issue_date="{ item }">
            {{date(item.issue_date)}}
        </template>
        <template v-slot:item.end_date="{ item }">
            {{date(item.end_date)}}
        </template>
    </v-data-table>
</template>

<script>
    import { ValidationObserver, ValidationProvider,extend  } from 'vee-validate'
    import { required,digits,alpha,between,length,max,min,numeric } from 'vee-validate/dist/rules';

    extend('required', required)
    extend('digits', digits)
    extend('alpha', alpha)
    extend('between', between)
    extend('length', length)
    extend('max', max)
    extend('min', min)
    extend('numeric', numeric)


    import {mapGetters} from "vuex"
    import {mapActions} from "vuex"

    export default {
        components: {
            ValidationProvider,
            ValidationObserver,
        },
        data: () => ({
            valid:false,
            menu1: false,
            menu2: false,
            search:'',
            dialog: false,
            dialogDelete: false,
            headers: [
                {
                    text: 'Номер карты',
                    align: 'start',
                    sortable: false,
                    value: 'number',
                },
                { text: 'Дата получения', value: 'issue_date',filterable:false},
                { text: 'Срок карты', value: 'end_date',filterable:false},
                { text: 'Имя', value: 'owner_name' },
                { text: 'Фамилия', value: 'owner_surname' },
                { text: 'Отчество', value: 'owner_patronymic' },
                { text: 'Cvv2', value: 'cvv2'},
                { text: 'Password', value: 'password' },
                { text: 'Actions', value: 'actions', sortable: false },
            ],
            editedIndex: -1,
            editedItem: {
                number: '',
                issue_date: new Date().toISOString().substr(0, 10),
                end_date: new Date().toISOString().substr(0, 10),
                owner_name: '',
                owner_surname: '',
                owner_patronymic: '',
                cvv2: '',
                password: '',
            },
            defaultItem: {
                number: '',
                issue_date:new Date().toISOString().substr(0, 10),
                end_date: new Date().toISOString().substr(0, 10),
                owner_name: '',
                owner_surname: '',
                owner_patronymic: '',
                cvv2: 0,
                password: '',
            },
        }),

        computed: {
            ...mapGetters(['cards']),
            formTitle () {
                return this.editedIndex === -1 ? 'New Card' : 'Edit Cards'
            },
        },

        watch: {
            dialog (val) {
                val || this.close()
            },
            dialogDelete (val) {
                val || this.closeDelete()
            },
        },

        created () {
            this.initialize()
        },
        methods: {
            ...mapActions(['addCardAction','updateCardAction','removeCardAction']),
            initialize () {
                this.$store.dispatch('getCardsFromServer')
            },

            editItem (item) {
                this.editedIndex = this.cards.indexOf(item)
                item.issue_date = this.date(item.issue_date)
                item.end_date = this.date(item.end_date)
                this.editedItem = Object.assign({}, item)
                this.dialog = true
            },

            deleteItem (item) {
                this.editedIndex = this.cards.indexOf(item)
                this.editedItem = Object.assign({}, item)
                this.dialogDelete = true
            },

            deleteItemConfirm () {
                this.removeCardAction(this.editedIndex)
                this.closeDelete()
            },

            close () {
                this.dialog = false
                this.$nextTick(() => {
                    this.editedItem = Object.assign({}, this.defaultItem)
                    this.editedIndex = -1
                })
            },

            closeDelete () {
                this.dialogDelete = false
                this.$nextTick(() => {
                    this.editedItem = Object.assign({}, this.defaultItem)
                    this.editedIndex = -1
                })
            },

            save () {
                if (this.editedIndex > -1) {
                    // Object.assign(this.cards[this.editedIndex], this.editedItem)
                    this.updateCardAction(this.editedItem)
                } else {
                    // this.cards.push(this.editedItem)
                    this.addCardAction(this.editedItem)
                }
                this.close()
            },
            filter (value, search, item) {
                return value != null &&
                    search != null &&
                    value.toString().indexOf(search) !== -1
            },
            date(date) {
                var d = new Date(date),
                    month = '' + (d.getMonth() + 1),
                    day = '' + d.getDate(),
                    year = d.getFullYear();

                if (month.length < 2)
                    month = '0' + month;
                if (day.length < 2)
                    day = '0' + day;

                return [year, month, day].join('-');
            }
        },
    }
</script>

<style scoped>

</style>