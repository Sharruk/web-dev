<script setup>
import { computed } from 'vue'

const props = defineProps({
  event: {
    type: Object,
    required: true
  }
})

defineEmits(['open-registration', 'close-details'])

// Requirement 6: Calculate available seats
const availableSeats = computed(() => {
  return props.event.maxParticipants - props.event.registeredParticipants
})
</script>

<template>
  <div class="modal-overlay" @click.self="$emit('close-details')">
    <div class="details-card">
      <div class="details-header">
        <div>
          <span class="category-pill">{{ event.category }}</span>
          <h2>{{ event.eventName }}</h2>
        </div>
        <button class="btn-close" @click="$emit('close-details')">×</button>
      </div>

      <div class="details-body">
        <div class="info-grid">
          <div class="info-item">
            <span class="label">Event ID:</span>
            <span class="value">#{{ event.eventId }}</span>
          </div>
          <div class="info-item">
            <span class="label">Category:</span>
            <span class="value">{{ event.category }}</span>
          </div>
          <div class="info-item">
            <span class="label">Date:</span>
            <span class="value">{{ event.date }}</span>
          </div>
          <div class="info-item">
            <span class="label">Venue:</span>
            <span class="value">{{ event.venue }}</span>
          </div>
          <div class="info-item">
            <span class="label">Registration Fee:</span>
            <span class="value">₹{{ event.registrationFee }}</span>
          </div>
          <div class="info-item">
            <span class="label">Max Participants:</span>
            <span class="value">{{ event.maxParticipants }}</span>
          </div>
          <div class="info-item">
            <span class="label">Registered Participants:</span>
            <span class="value">{{ event.registeredParticipants }}</span>
          </div>
          <div class="info-item highlight-item">
            <span class="label">Available Seats:</span>
            <span class="value">{{ availableSeats }}</span>
          </div>
          <div class="info-item">
            <span class="label">Event Status:</span>
            <!-- Requirement 5: Status with v-if, v-else-if, v-else -->
            <span v-if="availableSeats > 10" class="status-badge status-open">Registration Open</span>
            <span v-else-if="availableSeats > 0" class="status-badge status-almost-full">Almost Full</span>
            <span v-else class="status-badge status-closed">Registration Closed</span>
          </div>
        </div>
      </div>

      <div class="details-footer">
        <button class="btn-secondary" @click="$emit('close-details')">Back to List</button>

        <!-- Requirement 8: Register button ONLY when registration is open and available seats > 0 -->
        <button
          v-if="availableSeats > 0"
          class="btn-primary"
          @click="$emit('open-registration', event)"
        >
          Register for Event
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  padding: 1rem;
}

.details-card {
  background: #ffffff;
  width: 100%;
  max-width: 580px;
  border-radius: 12px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.25);
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.details-header {
  background: #1e3c72;
  color: #ffffff;
  padding: 1.25rem 1.5rem;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.category-pill {
  display: inline-block;
  background: rgba(255, 255, 255, 0.2);
  color: #ffffff;
  padding: 0.2rem 0.6rem;
  border-radius: 12px;
  font-size: 0.8rem;
  font-weight: 600;
  margin-bottom: 0.4rem;
  text-transform: uppercase;
}

.details-header h2 {
  font-size: 1.4rem;
  margin: 0;
  color: #ffffff;
}

.btn-close {
  background: transparent;
  border: none;
  color: #ffffff;
  font-size: 1.8rem;
  line-height: 1;
  cursor: pointer;
  padding: 0 0.5rem;
}

.details-body {
  padding: 1.5rem;
}

.info-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
}

@media (max-width: 500px) {
  .info-grid {
    grid-template-columns: 1fr;
  }
}

.info-item {
  display: flex;
  flex-direction: column;
  padding: 0.5rem;
  background: #f8f9fa;
  border-radius: 6px;
  border-left: 3px solid #1e3c72;
}

.highlight-item {
  background: #eef5ff;
  border-left-color: #ff9800;
}

.label {
  font-size: 0.8rem;
  color: #666;
  font-weight: 600;
  text-transform: uppercase;
  margin-bottom: 0.2rem;
}

.value {
  font-size: 1rem;
  font-weight: 600;
  color: #2c3e50;
}

.status-badge {
  display: inline-block;
  font-size: 0.85rem;
  font-weight: 700;
  padding: 0.25rem 0.5rem;
  border-radius: 4px;
  width: fit-content;
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

.details-footer {
  padding: 1rem 1.5rem;
  background: #f1f3f5;
  display: flex;
  justify-content: flex-end;
  gap: 0.75rem;
}

.btn-secondary {
  padding: 0.6rem 1.2rem;
  background: #e0e0e0;
  color: #333;
  border: none;
  border-radius: 6px;
  font-weight: 600;
  cursor: pointer;
}

.btn-secondary:hover {
  background: #d5d5d5;
}

.btn-primary {
  padding: 0.6rem 1.2rem;
  background: #2e7d32;
  color: #ffffff;
  border: none;
  border-radius: 6px;
  font-weight: 600;
  cursor: pointer;
}

.btn-primary:hover {
  background: #1b5e20;
}
</style>
