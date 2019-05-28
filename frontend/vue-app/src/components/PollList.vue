<template>
  <div id="poll-list">
    <p v-if="polls.length < 1" class="empty-table">
      No polls
    </p>
    <table v-else>
      <thead>
        <tr>
          <th>Poll title</th>
          <th>Poll summary</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="poll in polls" :key="poll.id">
          <td v-if="editing === poll.id">
            <input type="text" v-model="poll.title" />
          </td>
          <td v-else>{{poll.title}}</td>

          <td v-if="editing === poll.id">
            <textarea v-model="poll.summary">
            </textarea>
          </td>
          <td v-else>{{poll.summary}}</td>

          <td v-if="editing === poll.id">
            <button @click="editPoll(poll)">Save</button>
            <button class="muted-button" @click="editing = null">Cancel</button>
          </td>
          <td v-else>
            <button @click="editMode(poll.id)">Edit</button>
            <button @click="$emit('delete:poll', poll.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  export default {
    name: 'poll-list',
    props: {
      polls: Array,
    },
    data() {
      return {
        editing: null,
      }
    },
    methods: {
      editMode(id) {
        this.editing = id
      },

      editPoll(poll) {
        if(poll.title === '' || poll.summary === '') return
        this.$emit('edit:poll', poll.id, poll)
        this.editing = null
      }
    }
  }
</script>

<style scoped>
  button {
    margin: 0 0.5rem 0 0;
  }
</style>