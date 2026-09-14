<script setup>
import EventCard from './EventCard.vue'

const props = defineProps({
  events: Array
})
</script>

<template>

  <h2>Event List</h2>

  <div v-for="event in props.events" :key="event.eventId">

    <EventCard :event="event" />

  </div>

</template>