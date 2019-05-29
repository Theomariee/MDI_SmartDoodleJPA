import Vue from 'vue'
import Router from 'vue-router'
import PollList from '@/components/PollList.vue'
import PollForm from '@/components/PollForm.vue'
import ParticipantList from '@/components/ParticipantList.vue'
import ParticipantForm from '@/components/ParticipantForm.vue'

Vue.use(Router)

const routes = [
  { path: '',              component: PollList },
  { path: '/poll',         component: PollList },
  { path: '/participant',  component: ParticipantList },
]
export default new Router({
  routes
})