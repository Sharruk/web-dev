<script setup>
import EventCard from './EventCard.vue'

defineProps({
  events: {
    type: Array,
    required: true
  }
})

defineEmits(['select-event'])
</script>

<template>
  <div class="event-list-section">
    <div v-if="events.length === 0" class="no-events">
      <p>No events found matching your search and category filter.</p>
    </div>

    <!-- Requirement 3: Use v-for to dynamically display all events -->
    <div v-else class="event-grid">
      <EventCard
        v-for="event in events"
        :key="event.eventId"
        :event="event"
        @view-details="$emit('select-event', $event)"
      />
    </div>
  </div>
</template>

<style scoped>
.event-list-section {
  width: 100%;
}

.event-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 1.5rem;
}

.no-events {
  text-align: center;
  padding: 3rem 1rem;
  background: #ffffff;
  border-radius: 8px;
  color: #777;
  font-size: 1.1rem;
  border: 1px dashed #ccc;
}
</style>
