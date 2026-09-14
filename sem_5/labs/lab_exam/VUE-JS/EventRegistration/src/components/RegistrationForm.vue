<script setup>
import { ref } from 'vue'

const eventId = ref('')
const eventName = ref('')
const category = ref('')
const date = ref('')
const venue = ref('')
const registrationFee = ref('')

const emit = defineEmits(['add-event'])

const submitForm = () => {
  const newEvent = {
    eventId: eventId.value,
    eventName: eventName.value,
    category: category.value,
    date: date.value,
    venue: venue.value,
    registrationFee: registrationFee.value
  }

  emit('add-event', newEvent)
}
</script>

<template>
  <h2>Add New Event</h2>

  <form @submit.prevent="submitForm">

    <input v-model="eventId" placeholder="Event ID">

    <input v-model="eventName" placeholder="Event Name">

    <input v-model="category" placeholder="Category">

    <input v-model="date" placeholder="Date">

    <input v-model="venue" placeholder="Venue">

    <input v-model="registrationFee" placeholder="Registration Fee">

    <button type="submit">Add Event</button>

  </form>
</template>