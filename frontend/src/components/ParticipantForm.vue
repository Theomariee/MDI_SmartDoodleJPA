<template>
  <div id="participant-form">
    <form @submit.prevent="handleSubmit">
      <label>Participant First Name</label>
      <input 
            ref="first"
            type="text"
            :class="{ 'has-error': submitting && invalidFirstName }"
            v-model="participant.firstName"
            @focus="clearStatus"
            @keypress="clearStatus"
      />

      <label>Participant Second Name</label>
      <input
            ref="second"
            type="text"
            :class="{ 'has-error': submitting && invalidSecondName }"
            v-model="participant.secondName" 
            @focus="clearStatus"
        />

        <p v-if="error && submitting" class="error-message">
          ❗ Please fill out all required fields
        </p>
        <p v-if="success" class="success-message">
          ✅ Participant successfully added
        </p>

      <button>Add Participant</button>
    </form>
  </div>
</template>

<script>
  export default {
    name: 'participant-form',
    data() {
      return {
        submitting: false,
        error: false,
        success: false,
        participant: {
          firstName: '',
          secondName: '',
        },
      }
    },
    methods: {
        handleSubmit() {
            this.clearStatus()
            this.submitting = true

            if (this.invalidFirstName || this.invalidSecondName) {
                this.error = true
                return
            }

            this.$emit('add:participant', this.participant)
            this.$refs.first.focus()

            this.participant = {
                firstName: '',
                secondName: '',
            }

            this.error = false
            this.success = true
            this.submitting = false
        },

        clearStatus() {
            this.success = false
            this.error = false
        }
    },
    computed: {
        invalidFirstName() {
            return this.participant.firstName === ''
        },

        invalidSecondName() {
            return this.participant.secondName === ''
        },
    },
  }
</script>

<style scoped>
  form {
    margin-bottom: 2rem;
  }

  [class*='-message'] {
    font-weight: 500;
  }

  .error-message {
    color: #d33c40;
  }

  .success-message {
    color: #32a95d;
  }
</style>