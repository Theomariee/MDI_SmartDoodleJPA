<template>
  <div id="app" class="small-container">
    <h1>Polls</h1>
    <poll-form @add:poll="addPoll" />
    <poll-list 
      v-bind:polls="polls" 
      @edit:poll="editPoll"
      @delete:poll="deletePoll"
    />

    <h1>Participant</h1>
    <participant-form @add:participant="addParticipant" />
    <participant-list
      v-bind:participants="participants"
      @edit:participant="editParticipant"
      @delete:participant="deleteParticipant"
    />
  </div>
</template>

<script>
  import PollList from '@/components/PollList.vue'
  import PollForm from '@/components/PollForm.vue'
  import ParticipantList from '@/components/ParticipantList.vue'
  import ParticipantForm from '@/components/ParticipantForm.vue'
  import axios from 'axios';

  export default {
    name: 'app',
    components: {
      PollList,
      PollForm,
      ParticipantList,
      ParticipantForm
    },
    data() {
      return {
        polls: [],
        participants: []
      }
    },
    mounted() {
      this.getPolls(),
      this.getParticipants()
    },
    methods: {
      async getPolls() {
        axios
          .get('http://localhost:8080/api/v1/polls')
          //.then(response => (this.polls = response.data))
          .then(response => {
            let array = []
            for(var i in response.data) {
              this.polls.push(response.data[i])
            }
          })
          .catch(error => console.log(error))
      },

      async addPoll(poll) {
        try {
          const response = await fetch('http://localhost:8080/api/v1/polls', {
            method: 'POST',
            body: JSON.stringify(poll),
            headers: { "Content-type": "application/json; charset=UTF-8" }
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
      },

      async getParticipants() {
        axios
          .get('http://localhost:8080/api/v1/participants')
          .then(response => {
            let array = []
            for(var i in response.data) {
              this.participants.push(response.data[i])
            }
          })
          .catch(error => console.log(error))
      },

      async addParticipant(participant) {
          try {
            const response = await fetch('http://localhost:8080/api/v1/participants', {
              method: 'POST',
              body: JSON.stringify(participant),
              headers: { 'Content-type': 'application/json; charset=UTF-8' },
            })
            const data = await response.json()
            this.participants = [...this.participants, data]
          } catch (error) {
          }
      },

      async editParticipant(id, updatedParticipant) {
        try {
          const response = await fetch(`http://localhost:8080/api/v1/participants/${id}`, {
            method: 'PUT',
            body: JSON.stringify(updatedParticipant),
            headers: { 'Content-type': 'application/json; charset=UTF-8' },
          })
          const data = await response.json()
          this.participants = this.participants.map(participant => (participant.id === id ? data : participant))
        } catch (error) {
        }
      },

      async deleteParticipant(id) {
        try {
          await fetch(`http://localhost:8080/api/v1/participants/${id}`, {
            method: "DELETE"
          });
          this.participants = this.participants.filter(participant => participant.id !== id);
        } catch (error) {
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
