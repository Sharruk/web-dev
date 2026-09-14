<script setup>
import { ref, computed } from 'vue'

const props = defineProps({
  event: {
    type: Object,
    required: true
  }
})

const emit = defineEmits(['submit-registration', 'close-form'])

// Form fields using simple ref and v-model
const studentName = ref('')
const registerNumber = ref('')
const email = ref('')
const department = ref('')

// Message states
const errorMessage = ref('')
const successMessage = ref('')

const availableSeats = computed(() => {
  return props.event.maxParticipants - props.event.registeredParticipants
})

// Requirement 10: Form submission logic
const handleSubmit = () => {
  errorMessage.value = ''
  successMessage.value = ''

  // Step 1 & 2: Check that all four fields are filled
  if (
    !studentName.value.trim() ||
    !registerNumber.value.trim() ||
    !email.value.trim() ||
    !department.value.trim()
  ) {
    errorMessage.value = 'Please fill in all fields (Student Name, Register Number, Email, Department).'
    return
  }

  // Step 3: Emit valid registration data to parent App.vue
  emit('submit-registration', {
    eventId: props.event.eventId,
    studentName: studentName.value.trim(),
    registerNumber: registerNumber.value.trim(),
    email: email.value.trim(),
    department: department.value.trim()
  })

  // Display success message
  successMessage.value = `Registration successful! ${studentName.value} is registered for ${props.event.eventName}.`

  // Reset form fields
  studentName.value = ''
  registerNumber.value = ''
  email.value = ''
  department.value = ''
}
</script>

<template>
  <div class="modal-overlay" @click.self="$emit('close-form')">
    <div class="form-card">
      <div class="form-header">
        <div>
          <h2>Event Registration</h2>
          <p class="subtitle">{{ event.eventName }} (₹{{ event.registrationFee }})</p>
        </div>
        <button class="btn-close" @click="$emit('close-form')">×</button>
      </div>

      <div class="form-body">
        <!-- Success Alert -->
        <div v-if="successMessage" class="alert alert-success">
          <strong>Success!</strong> {{ successMessage }}
        </div>

        <!-- Validation Error Alert -->
        <div v-if="errorMessage" class="alert alert-danger">
          <strong>Error:</strong> {{ errorMessage }}
        </div>

        <!-- Seats warning if full -->
        <div v-if="availableSeats <= 0" class="alert alert-danger">
          This event has reached maximum capacity. Registration is Closed.
        </div>

        <!-- Form (visible only if seats available or when user wants to see status) -->
        <form v-if="availableSeats > 0" @submit.prevent="handleSubmit">
          <div class="form-group">
            <label for="studentName">Student Name *</label>
            <input
              id="studentName"
              v-model="studentName"
              type="text"
              placeholder="e.g. John Doe"
            />
          </div>

          <div class="form-group">
            <label for="registerNumber">Register Number *</label>
            <input
              id="registerNumber"
              v-model="registerNumber"
              type="text"
              placeholder="e.g. 717822P101"
            />
          </div>

          <div class="form-group">
            <label for="email">Email Address *</label>
            <input
              id="email"
              v-model="email"
              type="email"
              placeholder="e.g. john@college.edu"
            />
          </div>

          <div class="form-group">
            <label for="department">Department *</label>
            <select id="department" v-model="department">
              <option value="" disabled>Select your Department</option>
              <option value="Computer Science & Engineering">Computer Science & Engineering</option>
              <option value="Information Technology">Information Technology</option>
              <option value="Electronics & Communication">Electronics & Communication</option>
              <option value="Electrical & Electronics">Electrical & Electronics</option>
              <option value="Mechanical Engineering">Mechanical Engineering</option>
              <option value="Civil Engineering">Civil Engineering</option>
            </select>
          </div>

          <div class="form-actions">
            <button type="button" class="btn-cancel" @click="$emit('close-form')">
              Cancel
            </button>
            <button type="submit" class="btn-submit">
              Confirm Registration
            </button>
          </div>
        </form>

        <div v-else class="closed-actions">
          <button type="button" class="btn-cancel" @click="$emit('close-form')">
            Close
          </button>
        </div>
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
  z-index: 1100;
  padding: 1rem;
}

.form-card {
  background: #ffffff;
  width: 100%;
  max-width: 500px;
  border-radius: 12px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.25);
  overflow: hidden;
}

.form-header {
  background: #1e3c72;
  color: #ffffff;
  padding: 1.25rem 1.5rem;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.form-header h2 {
  font-size: 1.3rem;
  margin: 0;
  color: #ffffff;
}

.form-header .subtitle {
  font-size: 0.9rem;
  opacity: 0.9;
  margin: 0.25rem 0 0 0;
}

.btn-close {
  background: transparent;
  border: none;
  color: #ffffff;
  font-size: 1.8rem;
  line-height: 1;
  cursor: pointer;
}

.form-body {
  padding: 1.5rem;
}

.alert {
  padding: 0.75rem 1rem;
  border-radius: 6px;
  margin-bottom: 1rem;
  font-size: 0.9rem;
}

.alert-success {
  background-color: #e8f5e9;
  color: #2e7d32;
  border: 1px solid #c8e6c9;
}

.alert-danger {
  background-color: #ffebee;
  color: #c62828;
  border: 1px solid #ffcdd2;
}

.form-group {
  margin-bottom: 1rem;
  display: flex;
  flex-direction: column;
}

.form-group label {
  font-size: 0.85rem;
  font-weight: 600;
  color: #333;
  margin-bottom: 0.35rem;
}

.form-group input,
.form-group select {
  padding: 0.6rem 0.8rem;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 0.95rem;
  outline: none;
  transition: border-color 0.2s;
}

.form-group input:focus,
.form-group select:focus {
  border-color: #1e3c72;
  box-shadow: 0 0 0 2px rgba(30, 60, 114, 0.15);
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 0.75rem;
  margin-top: 1.5rem;
}

.closed-actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 1rem;
}

.btn-cancel {
  padding: 0.6rem 1.2rem;
  background: #e0e0e0;
  color: #333;
  border: none;
  border-radius: 6px;
  font-weight: 600;
  cursor: pointer;
}

.btn-submit {
  padding: 0.6rem 1.2rem;
  background: #1e3c72;
  color: #ffffff;
  border: none;
  border-radius: 6px;
  font-weight: 600;
  cursor: pointer;
}

.btn-submit:hover {
  background: #2a5298;
}
</style>
