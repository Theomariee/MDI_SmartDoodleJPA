var participants = [];

function findParticipant (participantId) {
  return participants[findParticipantKey(participantId)];
}

function findParticipantKey (participantId) {
  for (var key = 0; key < participants.length; key++) {
    if (participants[key].id == participantId) {
      return key;
    }
  }
}

var participantService = {
  findAll(fn) {
    axios
      .get('/api/v1/participants')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
      .get('/api/v1/participants/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  create(participant, fn) {
    axios
      .post('/api/v1/participants', participant)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  update(id, participant, fn) {
    axios
      .put('/api/v1/participants/' + id, participant)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deleteParticipant(id, fn) {
    axios
      .delete('/api/v1/participants/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}

var List = Vue.extend({
  template: '#participant-list',
  data: function () {
    return {participants: [], searchKey: ''};
  },
  computed: {
    filteredparticipants() {
      return this.participants.filter((participant) => {
      	return participant.firstName.indexOf(this.searchKey) > -1
      	  || participant.secondName.indexOf(this.searchKey) > -1
      })
    }
  },
  mounted() {
    participantService.findAll(r => {this.participants = r.data; participants = r.data})
  }
});

var participant = Vue.extend({
  template: '#participant',
  data: function () {
    return {participant: findParticipant(this.$route.params.participant_id)};
  }
});

var participantEdit = Vue.extend({
  template: '#participant-edit',
  data: function () {
    return {participant: findParticipant(this.$route.params.participant_id)};
  },
  methods: {
    updateParticipant: function () {
      participantService.update(this.participant.id, this.participant, r => router.push('/'))
    }
  }
});

var participantDelete = Vue.extend({
  template: '#participant-delete',
  data: function () {
    return {participant: findParticipant(this.$route.params.participant_id)};
  },
  methods: {
    deleteParticipant: function () {
      participantService.deleteParticipant(this.participant.id, r => router.push('/'))
    }
  }
});

var AddParticipant = Vue.extend({
  template: '#add-participant',
  data() {
    return {
      participant: {firstName: '', secondName: ''}
    }
  },
  methods: {
    createparticipant() {
      participantService.create(this.participant, r => router.push('/'))
    }
  }
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/participant/:participant_id', component: participant, name: 'participant'},
		{path: '/add-participant', component: AddParticipant},
		{path: '/participant/:participant_id/edit', component: participantEdit, name: 'participant-edit'},
		{path: '/participant/:participant_id/delete', component: participantDelete, name: 'participant-delete'}
	]
});

new Vue({
  router
}).$mount('#app')
