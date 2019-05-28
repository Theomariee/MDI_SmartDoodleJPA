<template>
  <div id="poll-form">
    <form @submit.prevent="handleSubmit">
      <label>Poll Title</label>
      <input 
        ref="title"
        type="text"
        :class="{ 'has-error': submitting && invalidTitle }"
        v-model="poll.title"
        @focus="clearStatus"
        @keypress="clearStatus"
      />

      <label>Poll Summary</label>
      <textarea 
        ref="summary"
        type="text"
        :class="{ 'has-error': submitting && invalidSummary }"
        v-model="poll.summary"
        @focus="clearStatus"
      >
      </textarea>

      <p v-if="error && submitting" class ="error-message">
        ❗ Required fields missing
      </p>
      <p v-if="success" class ="success-message">
        ✅ Poll succesfully added
      </p>

      <button>Add Poll</button>
    </form>
  </div>
</template>

<script>
  export default {
    name: 'poll-form',
    data() {
      return {
        submitting: false,
        error: false,
        success: false,
        poll: {
          title: '',
          summary: '',
        },
      }
    },
    methods: {
        handleSubmit() {
            this.submitting = true
            this.clearStatus()

            if(this.invalidTitle || this.invalidSummary) {
                this.error = true
                return
            }

            this.$emit('add:poll', this.poll)
            this.$refs.title.focus()

            this.poll = {
                title: '',
                summary: '',
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
        invalidTitle() {
            return this.poll.title === ''
        },
        invalidSummary() {
            return this.poll.summary === ''
        }
    }
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