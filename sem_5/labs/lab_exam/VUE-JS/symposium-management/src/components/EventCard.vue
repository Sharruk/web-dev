<script setup>
const props = defineProps({
  event: Object
})
</script>

<template>
  <div>
    <h2>{{ props.event.eventName }}</h2>
    <p>Category: {{ props.event.category }}</p>
  </div>
</template>
