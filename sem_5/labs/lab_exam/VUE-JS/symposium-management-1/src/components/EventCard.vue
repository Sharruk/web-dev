<script setup>
import { computed } from 'vue'

const props = defineProps({
  event: {
    type: Object,
    required: true
  }
})

defineEmits(['view-details'])

// Requirement 6: Calculate available seats (maxParticipants - registeredParticipants)
const availableSeats = computed(() => {
  return props.event.maxParticipants - props.event.registeredParticipants
})

// Requirement 11: Dynamic title size using :style
const titleStyle = computed(() => {
  if (availableSeats.value > 20) {
    return { fontSize: '24px', fontWeight: '700' }
  } else if (availableSeats.value >= 10) {
    return { fontSize: '20px', fontWeight: '700' }
  } else {
    return { fontSize: '16px', fontWeight: '700' }
  }
})

// Requirement 4: Category class dynamic binding
const categoryClass = computed(() => {
  const cat = props.event.category.toLowerCase()
  return `category-${cat}`
})
</script>

<template>
  <!-- Requirement 4: Dynamic :class binding for category -->
  <div class="event-card" :class="categoryClass">
    <div class="card-header">
      <span class="category-badge">{{ event.category }}</span>
      
      <!-- Requirement 5: Status with v-if, v-else-if, v-else -->
      <span v-if="availableSeats > 10" class="status-badge status-open">
        Registration Open
      </span>
      <span v-else-if="availableSeats > 0" class="status-badge status-almost-full">
        Almost Full
      </span>
      <span v-else class="status-badge status-closed">
        Registration Closed
      </span>
    </div>

    <div class="card-body">
      <!-- Requirement 11: Dynamic title size using :style binding -->
      <h3 class="event-title" :style="titleStyle">{{ event.eventName }}</h3>

      <div class="event-info">
        <p><strong>Date:</strong> {{ event.date }}</p>
        <p><strong>Venue:</strong> {{ event.venue }}</p>
        <p><strong>Fee:</strong> ₹{{ event.registrationFee }}</p>
      </div>

      <div class="seat-stats">
        <div class="seat-row">
          <span>Max: {{ event.maxParticipants }}</span>
          <span>Registered: {{ event.registeredParticipants }}</span>
        </div>
        <div class="available-highlight">
          <strong>Available Seats:</strong> {{ availableSeats }}
        </div>
      </div>
    </div>

    <div class="card-footer">
      <!-- Requirement 7: View Details button emitting event -->
      <button class="btn-view-details" @click="$emit('view-details', event)">
        View Details
      </button>
    </div>
  </div>
</template>

<!-- Requirement 16: EventCard.vue uses <style scoped> -->
<style scoped>
.event-card {
  background: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1.25rem;
  border-top: 5px solid #ccc;
  transition: transform 0.2s, box-shadow 0.2s;
}

.event-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.12);
}

/* Requirement 4: Category styling */
.category-technical {
  border-top-color: #2196f3;
}
.category-technical .category-badge {
  background-color: #e3f2fd;
  color: #1976d2;
}

.category-cultural {
  border-top-color: #e91e63;
}
.category-cultural .category-badge {
  background-color: #fce4ec;
  color: #c2185b;
}

.category-workshop {
  border-top-color: #4caf50;
}
.category-workshop .category-badge {
  background-color: #e8f5e9;
  color: #388e3c;
}

.category-competition {
  border-top-color: #ff9800;
}
.category-competition .category-badge {
  background-color: #fff3e0;
  color: #f57c00;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.75rem;
  gap: 0.5rem;
}

.category-badge {
  font-size: 0.8rem;
  font-weight: 700;
  padding: 0.25rem 0.6rem;
  border-radius: 20px;
  text-transform: uppercase;
}

/* Status Badges */
.status-badge {
  font-size: 0.75rem;
  font-weight: 600;
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
}

.status-open {
  background-color: #e8f5e9;
  color: #2e7d32;
}

.status-almost-full {
  background-color: #fff8e1;
  color: #f57f17;
}

.status-closed {
  background-color: #ffebee;
  color: #c62828;
}

.event-title {
  color: #2c3e50;
  margin: 0.5rem 0 0.75rem 0;
  line-height: 1.3;
}

.event-info p {
  margin: 0.25rem 0;
  font-size: 0.9rem;
  color: #555;
}

.seat-stats {
  margin-top: 0.75rem;
  padding-top: 0.75rem;
  border-top: 1px dashed #e0e0e0;
}

.seat-row {
  display: flex;
  justify-content: space-between;
  font-size: 0.85rem;
  color: #777;
  margin-bottom: 0.25rem;
}

.available-highlight {
  font-size: 0.9rem;
  color: #333;
}

.card-footer {
  margin-top: 1rem;
}

.btn-view-details {
  width: 100%;
  padding: 0.6rem;
  background-color: #1e3c72;
  color: #ffffff;
  border: none;
  border-radius: 6px;
  font-size: 0.95rem;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.2s;
}

.btn-view-details:hover {
  background-color: #2a5298;
}
</style>
