<template>
  <div id="participant-list">
    <p v-if="participants.length < 1" class="empty-table">
      No Participants
    </p>
    <table v-else>
      <thead>
        <tr>
          <th>Participant first name</th>
          <th>Participant second name</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="participant in participants" :key="participant.id">
          <td v-if="editing === participant.id">
            <input type="text" v-model="participant.firstName" />
          </td>
          <td v-else>{{participant.firstName}}</td>

          <td v-if="editing === participant.id">
            <input type="text" v-model="participant.secondName" />
          </td>
          <td v-else>{{participant.secondName}}</td>

          <td v-if="editing === participant.id">
            <button @click="editParticipant(participant)">Save</button>
            <button class="muted-button" @click="editing = null">Cancel</button>
          </td>
          <td v-else>
            <button @click="editMode(participant.id)">Edit</button>
            <button @click="$emit('delete:participant', participant.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  export default {
    name: 'participant-list',
    props: {
        participants: Array,
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

      editParticipant(participant) {
        if(participant.firstName === '' || participant.secondName === '') return
        this.$emit('edit:participant', participant.id, participant)
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