<template>
  <div id="app" class="small-container">
    <h1>Polls</h1>
    <poll-form @add:poll="addPoll" />
    <poll-list 
      v-bind:polls="polls" 
      @edit:poll="editPoll"
      @delete:poll="deletePoll"
    />
  </div>
</template>

<script>
  import PollList from '@/components/PollList.vue'
  import PollForm from '@/components/PollForm.vue'

  export default {
    name: 'app',
    components: {
      PollList,
      PollForm
    },
    data() {
      return {
        polls: [],
      }
    },
    mounted() {
      this.getPolls()
    },
    methods: {
      async getPolls() {
        try {
          const response = await fetch('http://localhost:8080/api/v1/polls')
          const data = await response.json()
          this.polls = data
        } catch (error) {
          console.error(error)
        }
      },

      async addPoll(poll) {
        try {
          const response = await fetch('http://localhost:8080/api/v1/polls', {
            method: 'POST',
            body: JSON.stringify(poll),
            headers: { 'Content-type': 'application/json; charset=UTF-8' },
          })
          const data = await response.json()
          this.polls = [...this.polls, data]
        } catch (error) {
          console.error(error)
        }
      },

      async editPoll(id, updatedPoll) {
        try {
          const response = await fetch(`http://localhost:8080/api/v1/polls/${id}`, {
            method: 'PUT',
            body: JSON.stringify(updatedPoll),
            headers: { 'Content-type': 'application/json; charset=UTF-8' },
          })
          const data = await response.json()
          this.polls = this.polls.map(poll => (poll.id === id ? data : poll))
        } catch (error) {
          console.error(error)
        }
      },

      async deletePoll(id) {
        try {
          await fetch(`http://localhost:8080/api/v1/polls/${id}`, {
            method: "DELETE"
          });
          this.polls = this.polls.filter(poll => poll.id !== id);
        } catch (error) {
          console.error(error);
        }
      }
    }
  }
</script>

<style>
  button {
    background: #009435;
    border: 1px solid #009435;
  }

  .small-container {
    max-width: 680px;
  }
</style>
