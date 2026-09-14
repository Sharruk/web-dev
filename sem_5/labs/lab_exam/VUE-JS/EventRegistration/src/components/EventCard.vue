<script setup>
const props = defineProps({
  event: Object
})
</script>

<template>

  <div>

    <h3>{{ props.event.eventName }}</h3>

    <p>Event ID: {{ props.event.eventId }}</p>

    <p>Category: {{ props.event.category }}</p>

    <p>Date: {{ props.event.date }}</p>

    <p>Venue: {{ props.event.venue }}</p>

    <p>Registration Fee: ₹{{ props.event.registrationFee }}</p>

  </div>

</template>