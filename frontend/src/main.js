import Vue from 'vue'
import App from './App.vue'
import router from './router'

import axios from "axios";
import VueAxios from "vue-axios";
Vue.use(VueAxios, axios);

axios.defaults.baseURL = "http://localhost:8090";
axios.defaults.headers.common['Accept'] = "application/json";


// Add Vuetify to the Project
import Vuetify from "vuetify";
import 'vuetify/dist/vuetify.css'
import 'vuetify/dist/vuetify.js'
Vue.use(Vuetify);

// Add BootstrapVue to the Project
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue);

import Vuex from "vuex";
Vue.use(Vuex)

Vue.config.productionTip = false;

Vue.prototype.$eventHub = new Vue();



const store = new Vuex.Store({
    state: {
        text: "",
        jwttoken: "",
    },
    mutations: {
        setText(state, text) {
            state.text = text;
        },
        setJwttoken(state, newToken) {
            state.jwttoken = newToken;
        }
    }
})

// Add a request interceptor
axios.interceptors.request.use(function (config) {
    const token = store.state.jwttoken;
    config.headers.jwttoken =  token;

    return config;
});

new Vue({
    vuetify: new Vuetify(),
    router,
    store,
    render: h => h(App)
})
    .$mount('#app');

