<script setup>

defineProps({
  event: Object
})

const emit = defineEmits(['register'])

function register() {
  emit('register')
}

</script>

<template>

  <div class="event">

    <h2>{{ event.eventName }}</h2>

    <p>Category: {{ event.category }}</p>

    <p>
      Participants:
      {{ event.registeredParticipants }}
      /
      {{ event.maxParticipants }}
    </p>

    <button @click="register">
      Register
    </button>

  </div>

</template>

<style scoped>

.event {
  width: 300px;
  padding: 20px;
  border: 1px solid gray;
  margin: 10px;
}

button {
  padding: 8px 15px;
}

</style>