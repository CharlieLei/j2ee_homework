import Vue from 'vue'
import axios from 'axios'
// import App from './App.vue'
import register from './register.vue'

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;


// new Vue({
//   render: h => h(Vue),
// }).$mount('#app')

new Vue({
  render: h => h(register),
}).$mount('#app');
