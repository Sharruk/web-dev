<script setup>
import { onMounted, ref } from 'vue'

const items = ref([])

async function getItems() {
  try {
    alert('starting')
    const response = await fetch('http://localhost:8080/api/products')
    // Directly assign the data array to the ref value
    items.value = await response.json()
  } 
  catch (error) {
    alert("Error fetching data:", error)
    if (items.value.length === 0) {
        alert('empty')
    }
    else {
        alert('full')
    } 
  } 
}   

onMounted(() => {
  getItems()
})
</script>

<template>
  <h1>Grocery Details</h1>
  <ul>
    <!-- Added missing quotes around the v-for directive -->
    <li v-for="i in items" :key="i.id">
      {{ i.name }} - {{ i.category }} - {{ i.price }} - {{ i.stock }}
    </li>
  </ul>
</template>
