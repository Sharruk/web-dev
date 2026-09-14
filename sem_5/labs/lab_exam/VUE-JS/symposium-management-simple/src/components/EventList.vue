<script setup>

import EventCard from './EventCard.vue'

defineProps({
  events: Array
})

const emit = defineEmits(['register'])

function registerEvent(event) {
  emit('register', event)
}

</script>

<template>

  <h2>Events</h2>

  <EventCard
    v-for="event in events"
    :key="event.eventId"
    :event="event"
    @register="registerEvent"
  />

</template>