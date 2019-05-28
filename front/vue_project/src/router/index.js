import Vue from 'vue'
import Router from 'vue-router'
import SmartDoodle from '@/components/SmartDoodle'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'SmartDoodle',
      component: SmartDoodle
    }
  ]
})
