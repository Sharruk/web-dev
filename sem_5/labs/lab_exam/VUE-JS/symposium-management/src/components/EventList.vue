<script setup>
import EventCard from './EventCard.vue'

const props = defineProps({
  events: Array
})
</script>

<template>
  <div>
    <EventCard
      v-for="event in props.events"
      :key="event.eventId"
      :event="event"
    />
  </div>
</template>
