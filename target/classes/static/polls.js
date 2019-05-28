var polls = [];

function findPoll (pollId) {
  return polls[findPollKey(pollId)];
}

function findPollKey (pollId) {
  for (var key = 0; key < polls.length; key++) {
    if (polls[key].id == pollId) {
      return key;
    }
  }
}

var pollService = {
  findAll(fn) {
    axios
      .get('/api/v1/polls')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
      .get('/api/v1/polls/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  create(poll, fn) {
    axios
      .post('/api/v1/polls', poll)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  update(id, poll, fn) {
    axios
      .put('/api/v1/polls/' + id, poll)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deletePoll(id, fn) {
    axios
      .delete('/api/v1/polls/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}

var List = Vue.extend({
  template: '#poll-list',
  data: function () {
    return {polls: [], searchKey: ''};
  },
  computed: {
    filteredPolls() {
      return this.polls.filter((poll) => {
      	return poll.title.indexOf(this.searchKey) > -1
      	  || poll.summary.indexOf(this.searchKey) > -1
      })
    }
  },
  mounted() {
    pollService.findAll(r => {this.polls = r.data; polls = r.data})
  }
});

var Poll = Vue.extend({
  template: '#poll',
  data: function () {
    return {poll: findPoll(this.$route.params.poll_id)};
  }
});

var PollEdit = Vue.extend({
  template: '#poll-edit',
  data: function () {
    return {poll: findPoll(this.$route.params.poll_id)};
  },
  methods: {
    updatePoll: function () {
      pollService.update(this.poll.id, this.poll, r => router.push('/'))
    }
  }
});

var PollDelete = Vue.extend({
  template: '#poll-delete',
  data: function () {
    return {poll: findPoll(this.$route.params.poll_id)};
  },
  methods: {
    deletePoll: function () {
      pollService.deletePoll(this.poll.id, r => router.push('/'))
    }
  }
});

var AddPoll = Vue.extend({
  template: '#add-poll',
  data() {
    return {
      poll: {title: '', summary: ''}
    }
  },
  methods: {
    createPoll() {
      pollService.create(this.poll, r => router.push('/'))
    }
  }
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/poll/:poll_id', component: Poll, name: 'poll'},
		{path: '/add-poll', component: AddPoll},
		{path: '/poll/:poll_id/edit', component: PollEdit, name: 'poll-edit'},
		{path: '/poll/:poll_id/delete', component: PollDelete, name: 'poll-delete'}
	]
});

new Vue({
  router
}).$mount('#app')
