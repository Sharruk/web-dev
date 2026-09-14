# Vue.js Learning Guide: College Symposium Management System

Welcome to the study guide for the **College Symposium Management System** (Lab Exam Edition). This guide is specifically written to help you understand every Vue.js concept in the simplified code and confidently explain it in your lab viva.

---

## 1. Project Overview

### What the Application Does
The **College Symposium Management System** is a simple Single-Page Application (SPA) built with Vue 3 that allows college students to:
1. Browse 6 symposium events across 4 categories: **Technical**, **Cultural**, **Workshop**, and **Competition**.
2. Search events in real-time by event name (case-insensitive).
3. Filter events using a simple category dropdown (`All Events`, `Technical`, `Cultural`, `Workshop`, `Competition`).
4. Click **View Details** to open an in-page event details section showing all event specifications and available seats.
5. Click **Register for Event** to open a simple registration form.
6. Submit student details with basic validation. When submitted, `registeredParticipants` increments, available seats decrease, and the status updates reactively.

### Component Hierarchy Diagram

```text
App.vue (Stores event data, search & filter state)
│
├── Header.vue             (Displays symposium title with unscoped <style>)
│
├── EventList.vue          (Receives filteredEvents; loops with v-for)
│   └── EventCard.vue      (Displays event card with dynamic :class, :style, and v-if status)
│
├── EventDetails.vue       (In-page details view; conditional Register button)
│
├── RegistrationForm.vue   (Form with v-model, validation, and submit emit)
│
└── Footer.vue             (Displays simple copyright text)
```

### Data Flow in Simple Terms
```text
Parent (App.vue)
  │
  │ props (Data down: :events, :event)
  ↓
Child Components (EventList / EventDetails / RegistrationForm)
  │
  │ emits (Events up: @select-event, @register, @submit-registration)
  ↓
Parent (App.vue)
```

---

## 2. Important Vue Concepts Used

| Concept | Where Used | What It Does in This Project |
| :--- | :--- | :--- |
| **`ref()`** | `App.vue`, `RegistrationForm.vue` | Stores reactive data (e.g. `events`, `searchQuery`, `studentName`) that triggers UI updates when changed. |
| **`computed()`** | `App.vue`, `EventCard.vue`, `EventDetails.vue` | Computes derived values: `filteredEvents`, `availableSeats`, `titleStyle`, and `categoryClass`. |
| **`defineProps()`** | `EventList.vue`, `EventCard.vue`, `EventDetails.vue`, `RegistrationForm.vue` | Receives data passed from parent components. |
| **`defineEmits()`** | `EventCard.vue`, `EventList.vue`, `EventDetails.vue`, `RegistrationForm.vue` | Emits custom events to notify the parent component of user actions. |
| **`v-for` & `:key`** | `EventList.vue` | Loops over `events` array to render an `EventCard` for each event using a unique `:key="event.eventId"`. |
| **`v-if`, `v-else-if`, `v-else`** | `EventCard.vue`, `EventDetails.vue`, `App.vue` | Displays status badges ("Registration Open", "Almost Full", "Registration Closed") and conditionally renders details and form sections. |
| **`:class`** | `EventCard.vue` | Dynamically applies category classes (`category-technical`, `category-cultural`, `category-workshop`, `category-competition`). |
| **`:style`** | `EventCard.vue` | Dynamically sets title font size based on available seats (`>20` &rarr; 24px, `10-20` &rarr; 20px, `<10` &rarr; 16px). |
| **`v-model`** | `App.vue`, `RegistrationForm.vue` | Two-way binds form inputs and dropdowns (`searchQuery`, `selectedCategory`, `studentName`, etc.). |
| **`@click`** | Buttons across components | Listens to button clicks (e.g. View Details, Register, Close). |
| **`@submit.prevent`** | `RegistrationForm.vue` | Handles form submission without reloading the browser page. |

---

## 3. App.vue

`App.vue` is the root component that holds the application state and connects all child components.

### A. Imports
```js
import { ref, computed } from 'vue'
import Header from './components/Header.vue'
import EventList from './components/EventList.vue'
import EventDetails from './components/EventDetails.vue'
import RegistrationForm from './components/RegistrationForm.vue'
import Footer from './components/Footer.vue'
```
- Imports Vue reactive functions and all 5 child components.

### B. Event Data
```js
const events = ref([
  {
    eventId: 101,
    eventName: 'Coding Contest',
    category: 'Technical',
    date: '2026-09-15',
    venue: 'Computer Lab 1',
    registrationFee: 100,
    maxParticipants: 50,
    registeredParticipants: 15,
    status: 'Registration Open'
  },
  // ...other sample events
])
```
- **Structure:** Array of event objects containing `eventId`, `eventName`, `category`, `date`, `venue`, `registrationFee`, `maxParticipants`, `registeredParticipants`, and `status`.
- **Why in `App.vue`:** Centralizes state so child components can receive and update data through standard props and emits.

### C. `searchQuery` & `selectedCategory`
```js
const searchQuery = ref('')
const selectedCategory = ref('All Events')
```
- Two-way bound to the search input box and category dropdown via `v-model`.

### D. `computed(filteredEvents)`
```js
const filteredEvents = computed(() => {
  return events.value.filter((event) => {
    const matchesCategory =
      selectedCategory.value === 'All Events' ||
      event.category === selectedCategory.value

    const matchesSearch = event.eventName
      .toLowerCase()
      .includes(searchQuery.value.toLowerCase().trim())

    return matchesCategory && matchesSearch
  })
})
```
- **Logic:** Evaluates every event against the category filter and case-insensitive name search. Automatically updates whenever `searchQuery` or `selectedCategory` changes.

### E. Selected Event & Registration Handlers
```js
const selectedEvent = ref(null)
const registeringEvent = ref(null)

const handleSelectEvent = (event) => {
  selectedEvent.value = event
}

const handleOpenRegistration = (event) => {
  registeringEvent.value = event
}

const handleRegistrationSubmit = (eventId) => {
  const event = events.value.find((e) => e.eventId === eventId)
  if (event) {
    event.registeredParticipants += 1
    const available = event.maxParticipants - event.registeredParticipants
    if (available > 10) {
      event.status = 'Registration Open'
    } else if (available > 0) {
      event.status = 'Almost Full'
    } else {
      event.status = 'Registration Closed'
    }
  }
}
```

---

## 4. Header.vue

- **Displays:** "College Symposium Management System"
- **Style Requirement:** Uses normal `<style>` (unscoped) as specifically required by the assignment:
  ```vue
  <style>
  .header {
    text-align: center;
    background-color: #2c3e50;
    color: white;
    padding: 20px;
    margin-bottom: 20px;
    border-radius: 6px;
  }
  </style>
  ```

---

## 5. EventList.vue

- **Props:** Receives `events` array from `App.vue` using `defineProps({ events: Array })`.
- **Emits:** Declares `defineEmits(['select-event'])`.
- **Template with `v-for`:**
  ```vue
  <EventCard
    v-for="event in events"
    :key="event.eventId"
    :event="event"
    @view-details="$emit('select-event', $event)"
  />
  ```
- **Data flow:**
  ```text
  App.vue (passes filteredEvents)
     ↓ props
  EventList.vue (loops with v-for)
     ↓ props
  EventCard.vue
  ```

---

## 6. EventCard.vue

### A. Props & Emits
```js
const props = defineProps({ event: Object })
defineEmits(['view-details'])
```

### B. Available Seats Calculation
```js
const availableSeats = computed(() => {
  return props.event.maxParticipants - props.event.registeredParticipants
})
```

### C. Status Logic with `v-if`, `v-else-if`, `v-else`
```vue
<span v-if="availableSeats > 10" class="status-open">Registration Open</span>
<span v-else-if="availableSeats > 0" class="status-almost-full">Almost Full</span>
<span v-else class="status-closed">Registration Closed</span>
```

### D. Dynamic `:class`
```js
const categoryClass = computed(() => 'category-' + props.event.category.toLowerCase())
```
```vue
<div class="event-card" :class="categoryClass">
```
- Sets CSS classes: `category-technical`, `category-cultural`, `category-workshop`, `category-competition`.

### E. Dynamic `:style`
```js
const titleStyle = computed(() => {
  if (availableSeats.value > 20) return { fontSize: '24px' }
  else if (availableSeats.value >= 10) return { fontSize: '20px' }
  else return { fontSize: '16px' }
})
```
```vue
<h3 :style="titleStyle">{{ event.eventName }}</h3>
```

### F. View Details Button
```vue
<button class="btn-details" @click="$emit('view-details', event)">
  View Details
</button>
```

---

## 7. EventDetails.vue

- **Props:** Receives the selected `event` object.
- **Emits:** `@register` and `@close`.
- **Displays:** All event attributes in a clean table format.
- **Conditional Register Button:**
  ```vue
  <button
    v-if="availableSeats > 0"
    class="btn-register"
    @click="$emit('register', event)"
  >
    Register for Event
  </button>
  <p v-else class="msg-full">Registration is closed for this event.</p>
  ```

---

## 8. RegistrationForm.vue

### A. Form Fields & `v-model`
```js
const studentName = ref('')
const registerNumber = ref('')
const email = ref('')
const department = ref('')
```
- `v-model` links each text input directly to its corresponding reactive variable.

### B. Submission & Validation (`@submit.prevent`)
```js
const handleSubmit = () => {
  if (
    !studentName.value.trim() ||
    !registerNumber.value.trim() ||
    !email.value.trim() ||
    !department.value.trim()
  ) {
    message.value = 'Please fill all fields.'
    isSuccess.value = false
    return
  }

  emit('submit-registration', props.event.eventId)
  message.value = 'Registration successful!'
  isSuccess.value = true

  studentName.value = ''
  registerNumber.value = ''
  email.value = ''
  department.value = ''
}
```

---

## 9. Footer.vue

- **Displays:** `© 2026 College Symposium Management System. All rights reserved.`
- **Structure:** Clean, simple footer component using `<style scoped>`.

---

## 10. Parent-Child Communication

```text
       App.vue (Parent)
       ┌───────────────┐
       │   events      │
       └───┬───────▲───┘
   :events │       │ @select-event
   (props) │       │ (emit)
       ┌───▼───────┴───┐
       │ EventList.vue │
       └───┬───────▲───┘
    :event │       │ @view-details
   (props) │       │ (emit)
       ┌───▼───────┴───┐
       │ EventCard.vue │
       └───────────────┘
```

1. **Props (Parent &rarr; Child):**
   - `App.vue` &rarr; `EventList.vue` (`:events="filteredEvents"`)
   - `EventList.vue` &rarr; `EventCard.vue` (`:event="event"`)
   - `App.vue` &rarr; `EventDetails.vue` (`:event="selectedEvent"`)
   - `App.vue` &rarr; `RegistrationForm.vue` (`:event="registeringEvent"`)

2. **Emits (Child &rarr; Parent):**
   - `EventCard.vue` &rarr; `EventList.vue` (`@view-details="event"`)
   - `EventList.vue` &rarr; `App.vue` (`@select-event="event"`)
   - `EventDetails.vue` &rarr; `App.vue` (`@register="event"`)
   - `RegistrationForm.vue` &rarr; `App.vue` (`@submit-registration="eventId"`)

---

## 11. Complete User Flow

```text
1. User opens the application
   └── App.vue loads the events list and renders Header, Controls, EventList, Footer.

2. User searches or filters
   └── Typing in search input or selecting a category updates searchQuery / selectedCategory.
   └── computed(filteredEvents) automatically filters the event list.

3. User clicks "View Details" on an EventCard
   └── EventCard emits view-details → EventList emits select-event → App.vue sets selectedEvent.
   └── EventDetails section displays complete info.

4. User clicks "Register for Event"
   └── EventDetails emits register → App.vue sets registeringEvent.
   └── RegistrationForm appears.

5. User fills and submits the form
   └── If any field is empty: Displays "Please fill all fields."
   └── If valid: Emits submit-registration(eventId) to App.vue.
   └── App.vue increments registeredParticipants by 1.
   └── availableSeats decreases automatically.
   └── Status updates reactively (Registration Open → Almost Full → Registration Closed).
   └── "Registration successful!" is displayed.
```

---

## 12. 15 Important Viva Questions and Answers

### Q1: Why is `events` stored in `App.vue`?
> **Answer:** `App.vue` is the root component. Centralizing data in `App.vue` allows multiple child components (`EventList`, `EventDetails`, `RegistrationForm`) to access and modify the same data via props and emits.

### Q2: What is `ref()` in Vue 3?
> **Answer:** `ref()` creates a reactive variable. When its value changes, Vue automatically updates any part of the UI that displays it.

### Q3: What is a computed property (`computed()`)?
> **Answer:** A computed property is a function that returns a derived reactive value. It is cached and only recalculates when its dependent reactive data changes.

### Q4: Why is `computed()` used for filtering events?
> **Answer:** It automatically combines the category selection and search query to produce `filteredEvents` without manual DOM manipulation.

### Q5: What are props?
> **Answer:** Props are custom attributes used to pass data from a parent component down to a child component.

### Q6: What is `defineEmits()`?
> **Answer:** `defineEmits()` declares custom events that a child component can trigger to communicate upwards to its parent.

### Q7: What is the difference between props and emits?
> **Answer:** Props pass data downwards (Parent &rarr; Child), while emits send events upwards (Child &rarr; Parent).

### Q8: Why is `v-for` used?
> **Answer:** `v-for` loops through an array to dynamically render multiple components or HTML elements.

### Q9: Why is `:key` mandatory with `v-for`?
> **Answer:** `:key` gives each item a unique identifier so Vue's Virtual DOM can track and update elements efficiently.

### Q10: What is dynamic `:class` binding?
> **Answer:** `:class` applies or removes CSS classes dynamically based on data expressions (e.g. `:class="categoryClass"` for category styling).

### Q11: What is dynamic `:style` binding?
> **Answer:** `:style` binds inline CSS styles directly from JavaScript (e.g. `:style="titleStyle"` to adjust font size based on seat availability).

### Q12: How does `v-if` work?
> **Answer:** `v-if` conditionally adds or removes an element from the DOM based on whether the condition is true or false.

### Q13: How are available seats calculated?
> **Answer:** As a computed value: `props.event.maxParticipants - props.event.registeredParticipants`.

### Q14: How does `v-model` work?
> **Answer:** `v-model` provides two-way data binding between a form input element and a reactive JavaScript variable.

### Q15: How does the event status change automatically upon registration?
> **Answer:** Because `availableSeats` is computed from `registeredParticipants`. When registration increments `registeredParticipants`, `availableSeats` recalculates, triggering `v-if / v-else-if / v-else` to update the status badge immediately.

---

## 13. Code Patterns to Memorize (Cheat Sheet)

```js
// 1. Reactive Variable
const count = ref(0)

// 2. Computed Property
const availableSeats = computed(() => event.maxParticipants - event.registeredParticipants)

// 3. Props Declaration
const props = defineProps({ event: Object })

// 4. Emits Declaration
const emit = defineEmits(['select-event'])
```

```vue
<!-- 5. Interpolation -->
<p>{{ event.eventName }}</p>

<!-- 6. v-for with :key -->
<EventCard v-for="event in events" :key="event.eventId" :event="event" />

<!-- 7. v-if, v-else-if, v-else -->
<span v-if="seats > 10">Open</span>
<span v-else-if="seats > 0">Almost Full</span>
<span v-else>Closed</span>

<!-- 8. Dynamic :class -->
<div :class="'category-' + event.category.toLowerCase()"></div>

<!-- 9. Dynamic :style -->
<h3 :style="{ fontSize: seats > 20 ? '24px' : '16px' }">Title</h3>

<!-- 10. Click Event -->
<button @click="$emit('view-details', event)">View</button>

<!-- 11. Form Submit with Prevent -->
<form @submit.prevent="handleSubmit">

<!-- 12. Two-way Data Binding -->
<input v-model="studentName" type="text" />
```
