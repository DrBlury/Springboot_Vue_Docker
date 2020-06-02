import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/Dashboard'

Vue.use(Router);

const router = new Router({
    mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [
        { path: '/', component: Dashboard },
        // otherwise redirect to home
        { path: '*', redirect: '/' }
    ]
});

//Check login... Possible here!

export default router;