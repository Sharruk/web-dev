<script setup>
import { ref, computed } from 'vue'
import Header from './components/Header.vue'
import EventList from './components/EventList.vue'
import EventDetails from './components/EventDetails.vue'
import RegistrationForm from './components/RegistrationForm.vue'
import Footer from './components/Footer.vue'

// Requirement 1: Event data stored inside App.vue
const events = ref([
  {
    eventId: 101,
    eventName: 'CodeSprint: National Hackathon',
    category: 'Technical',
    date: '2026-09-15',
    venue: 'Computing Lab 3',
    registrationFee: 150,
    maxParticipants: 50,
    registeredParticipants: 15,
    status: 'Registration Open'
  },
  {
    eventId: 102,
    eventName: 'AI & Cloud Computing Workshop',
    category: 'Workshop',
    date: '2026-09-16',
    venue: 'Seminar Hall A',
    registrationFee: 200,
    maxParticipants: 30,
    registeredParticipants: 16,
    status: 'Registration Open'
  },
  {
    eventId: 103,
    eventName: 'Rhythm & Beats: Group Dance',
    category: 'Cultural',
    date: '2026-09-16',
    venue: 'Main Auditorium',
    registrationFee: 100,
    maxParticipants: 15,
    registeredParticipants: 12,
    status: 'Almost Full'
  },
  {
    eventId: 104,
    eventName: 'Tech Quiz Showdown',
    category: 'Competition',
    date: '2026-09-17',
    venue: 'Lecture Hall 2',
    registrationFee: 50,
    maxParticipants: 20,
    registeredParticipants: 20,
    status: 'Registration Closed'
  },
  {
    eventId: 105,
    eventName: 'Full Stack Web Dev Workshop',
    category: 'Workshop',
    date: '2026-09-17',
    venue: 'Web Lab 1',
    registrationFee: 120,
    maxParticipants: 25,
    registeredParticipants: 10,
    status: 'Registration Open'
  },
  {
    eventId: 106,
    eventName: 'Battle of the Bands',
    category: 'Cultural',
    date: '2026-09-18',
    venue: 'Open Air Theatre',
    registrationFee: 300,
    maxParticipants: 10,
    registeredParticipants: 8,
    status: 'Almost Full'
  },
  {
    eventId: 107,
    eventName: 'Paper Presentation Contest',
    category: 'Technical',
    date: '2026-09-18',
    venue: 'Conference Hall',
    registrationFee: 100,
    maxParticipants: 40,
    registeredParticipants: 12,
    status: 'Registration Open'
  },
  {
    eventId: 108,
    eventName: 'Gaming Arena: Valorant & BGMI',
    category: 'Competition',
    date: '2026-09-19',
    venue: 'E-Sports Arena',
    registrationFee: 250,
    maxParticipants: 16,
    registeredParticipants: 15,
    status: 'Almost Full'
  }
])

// Categories available for filtering
const categories = ['All Events', 'Technical', 'Cultural', 'Workshop', 'Competition']

// State for search and filter
const selectedCategory = ref('All Events')
const searchQuery = ref('')

// State for selected event details modal and registration modal
const selectedEvent = ref(null)
const registeringEvent = ref(null)

// Helper function to update status based on seat availability
const updateEventStatus = (event) => {
  const available = event.maxParticipants - event.registeredParticipants
  if (available > 10) {
    event.status = 'Registration Open'
  } else if (available > 0) {
    event.status = 'Almost Full'
  } else {
    event.status = 'Registration Closed'
  }
}

// Requirement 14: Vue computed() property for combined category filtering and name searching
const filteredEvents = computed(() => {
  return events.value.filter((event) => {
    // Category check
    const matchesCategory =
      selectedCategory.value === 'All Events' ||
      event.category.toLowerCase() === selectedCategory.value.toLowerCase()

    // Search query check (case-insensitive)
    const matchesSearch = event.eventName
      .toLowerCase()
      .includes(searchQuery.value.trim().toLowerCase())

    return matchesCategory && matchesSearch
  })
})

// Event handlers
const handleSelectEvent = (event) => {
  selectedEvent.value = event
}

const handleCloseDetails = () => {
  selectedEvent.value = null
}

const handleOpenRegistration = (event) => {
  selectedEvent.value = null // close details modal
  registeringEvent.value = event // open registration form
}

const handleCloseForm = () => {
  registeringEvent.value = null
}

// Requirement 10: Increase registeredParticipants on form submission
const handleRegistrationSubmit = (formData) => {
  const targetEvent = events.value.find((e) => e.eventId === formData.eventId)
  if (targetEvent) {
    if (targetEvent.registeredParticipants < targetEvent.maxParticipants) {
      targetEvent.registeredParticipants += 1
      updateEventStatus(targetEvent)
    }
  }
}
</script>

<template>
  <div class="symposium-app">
    <!-- Header Component -->
    <Header />

    <!-- Search and Category Filter Section -->
    <section class="controls-section">
      <div class="search-box">
        <label for="searchInput">Search Events:</label>
        <input
          id="searchInput"
          v-model="searchQuery"
          type="text"
          placeholder="Search events by name..."
        />
      </div>

      <div class="filter-box">
        <label>Filter by Category:</label>
        <div class="category-buttons">
          <button
            v-for="cat in categories"
            :key="cat"
            class="cat-btn"
            :class="{ active: selectedCategory === cat }"
            @click="selectedCategory = cat"
          >
            {{ cat }}
          </button>
        </div>
      </div>
    </section>

    <!-- Event Count Summary -->
    <div class="results-summary">
      <span>Showing {{ filteredEvents.length }} of {{ events.length }} events</span>
    </div>

    <!-- Event List Component -->
    <main>
      <EventList :events="filteredEvents" @select-event="handleSelectEvent" />
    </main>

    <!-- Event Details Modal / Component -->
    <EventDetails
      v-if="selectedEvent"
      :event="selectedEvent"
      @open-registration="handleOpenRegistration"
      @close-details="handleCloseDetails"
    />

    <!-- Registration Form Modal / Component -->
    <RegistrationForm
      v-if="registeringEvent"
      :event="registeringEvent"
      @submit-registration="handleRegistrationSubmit"
      @close-form="handleCloseForm"
    />

    <!-- Footer Component -->
    <Footer />
  </div>
</template>

<style scoped>
.symposium-app {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1.5rem;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
  color: #333333;
}

.controls-section {
  background: #ffffff;
  padding: 1.5rem;
  border-radius: 10px;
  box-shadow: 0 4px 14px rgba(0, 0, 0, 0.06);
  margin-bottom: 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 1.25rem;
}

@media (min-width: 768px) {
  .controls-section {
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
  }
}

.search-box {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
}

.search-box label,
.filter-box label {
  font-size: 0.85rem;
  font-weight: 700;
  color: #555;
  text-transform: uppercase;
}

.search-box input {
  padding: 0.65rem 1rem;
  border: 1.5px solid #d1d5db;
  border-radius: 8px;
  font-size: 0.95rem;
  outline: none;
  transition: border-color 0.2s;
}

.search-box input:focus {
  border-color: #1e3c72;
}

.filter-box {
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
}

.category-buttons {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
}

.cat-btn {
  padding: 0.5rem 0.9rem;
  border: 1px solid #d1d5db;
  background-color: #f9fafb;
  color: #4b5563;
  border-radius: 20px;
  font-size: 0.85rem;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
}

.cat-btn:hover {
  background-color: #e5e7eb;
}

.cat-btn.active {
  background-color: #1e3c72;
  color: #ffffff;
  border-color: #1e3c72;
}

.results-summary {
  margin-bottom: 1rem;
  font-size: 0.9rem;
  color: #666;
  font-weight: 500;
}
</style>
