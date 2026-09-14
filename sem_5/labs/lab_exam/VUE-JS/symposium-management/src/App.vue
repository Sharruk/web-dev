<script setup>
import { ref } from 'vue'
import Header from './components/Header.vue'
import EventList from './components/EventList.vue'
import Footer from './components/Footer.vue'

const events = ref([
  {
    eventId: 1,
    eventName: 'Coding Contest',
    category: 'Technical'
  },
  {
    eventId: 2,
    eventName: 'Dance Competition',
    category: 'Cultural'
  }
])
</script>

<template>
  <div>
    <Header />
    <EventList :events="events" />
    <Footer />
  </div>
</template>
