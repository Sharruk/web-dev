<script setup>
import { ref } from 'vue'
import RegistrationForm from './components/RegistrationForm.vue'
import EventList from './components/EventList.vue'

const events = ref([])

const addEvent = (newEvent) => {
  events.value.push(newEvent)
}
</script>

<template>

  <RegistrationForm @add-event="addEvent" />

  <EventList :events="events" />

</template>