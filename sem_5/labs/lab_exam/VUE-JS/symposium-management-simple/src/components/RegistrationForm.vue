<script setup>

import { ref } from 'vue'

const props = defineProps({
  event: Object
})

const emit = defineEmits(['submit-registration'])

const studentName = ref('')

function register() {

  if (studentName.value == '') {
    alert('Please enter your name')
    return
  }

  emit('submit-registration', props.event)

  studentName.value = ''

}

</script>

<template>

  <div class="form">

    <h2>Registration Form</h2>

    <p>
      Event: {{ event.eventName }}
    </p>

    <input
      v-model="studentName"
      placeholder="Enter your name"
    >

    <br><br>

    <button @click="register">
      Register
    </button>

  </div>

</template>

<style scoped>

.form {
  width: 300px;
  padding: 20px;
  border: 1px solid gray;
  margin: 20px 10px;
}

input {
  padding: 8px;
}

button {
  padding: 8px 15px;
}

</style>