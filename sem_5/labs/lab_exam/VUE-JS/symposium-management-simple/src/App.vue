<script setup>

import { ref } from 'vue'
import EventList from './components/EventList.vue'
import RegistrationForm from './components/RegistrationForm.vue'

const events = ref([
  {
    eventId: 1,
    eventName: 'Coding Contest',
    category: 'Technical',
    maxParticipants: 50,
    registeredParticipants: 35
  },
  {
    eventId: 2,
    eventName: 'Quiz Competition',
    category: 'Competition',
    maxParticipants: 100,
    registeredParticipants: 80
  }
])

const selectedEvent = ref(null)

function openRegistration(event) {
  selectedEvent.value = event
}

function registerStudent(event) {
  event.registeredParticipants++

  alert('Registration Successful!')

  selectedEvent.value = null
}

</script>

<template>

  <h1>College Symposium</h1>

  <EventList
    :events="events"
    @register="openRegistration"
  />

  <RegistrationForm
    v-if="selectedEvent"
    :event="selectedEvent"
    @submit-registration="registerStudent"
  />

</template>