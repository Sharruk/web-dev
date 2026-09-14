# Vue.js Learning Guide: College Symposium Management System

Welcome to the study guide for the **College Symposium Management System**. This guide is designed to help you understand every Vue.js concept used in this project, see how all components connect, and prepare for your college lab viva exam.

---

## 1. Project Overview

### What the Application Does
The **College Symposium Management System** is a Single-Page Application (SPA) built using Vue 3 that allows college students to:
1. Browse various symposium events across four categories: **Technical**, **Cultural**, **Workshop**, and **Competition**.
2. Search events in real-time by name.
3. Filter events by category buttons.
4. View comprehensive event details (venue, fee, dates, max participants, registered participants, and available seats).
5. Register for open events via a validated registration form that automatically updates seat count and event availability status reactively.

### Component Hierarchy Diagram

```text
App.vue (Root State & Event Data)
│
├── Header.vue             (Symposium Title & Branding)
│
├── EventList.vue          (Event Grid Container)
│   └── EventCard.vue      (Individual Event Card with dynamic styles & status)
│
├── EventDetails.vue       (Detailed Modal View & Register Trigger)
│
├── RegistrationForm.vue   (Registration Modal with Form Validation)
│
└── Footer.vue             (Copyright & Lab Info)
```

### Data Flow in Simple Terms
- **Top-Down (Props):** The master event list and user selection states live in `App.vue`. Data flows downward from parent components to child components via **props**.
- **Bottom-Up (Emits):** User interactions (clicking "View Details", clicking "Register", or submitting the registration form) send events back up to `App.vue` via **emits**.

---

## 2. Important Vue Concepts Used

| Concept | Where Used | What It Does in This Project |
| :--- | :--- | :--- |
| **`ref()`** | `App.vue`, `RegistrationForm.vue` | Creates reactive variables (e.g., `events`, `searchQuery`, `studentName`) whose changes immediately update the UI. |
| **`computed()`** | `App.vue`, `EventCard.vue`, `EventDetails.vue` | Automatically calculates derived values like `filteredEvents`, `availableSeats`, and dynamic `:style` sizing. |
| **`defineProps()`** | `EventList.vue`, `EventCard.vue`, `EventDetails.vue`, `RegistrationForm.vue` | Declares props received from parent components (e.g., `events`, `event`). |
| **`defineEmits()`** | `EventList.vue`, `EventCard.vue`, `EventDetails.vue`, `RegistrationForm.vue` | Declares custom events sent back to parent components (e.g., `@view-details`, `@submit-registration`). |
| **`v-for`** | `App.vue`, `EventList.vue` | Loops through arrays to render category buttons and dynamic `EventCard` components. |
| **`v-if`, `v-else-if`, `v-else`** | `EventCard.vue`, `EventDetails.vue`, `RegistrationForm.vue`, `App.vue` | Conditionally renders elements (e.g., event status badges, modal dialogs, validation messages). |
| **`:class`** | `App.vue`, `EventCard.vue` | Dynamically applies CSS classes based on event category or active filter state. |
| **`:style`** | `EventCard.vue` | Dynamically adjusts the font size of event titles based on available seat count. |
| **`v-model`** | `App.vue`, `RegistrationForm.vue` | Two-way binds form input values (text fields, select dropdowns) to reactive variables. |
| **`@click` & `@submit.prevent`** | All components | Handles user click events and form submission without reloading the browser page. |

---

## 3. App.vue (Root Component)

`App.vue` is the central orchestrator of the entire application. It stores the master data and coordinates child components.

### A. Imports
```js
import { ref, computed } from 'vue'
import Header from './components/Header.vue'
import EventList from './components/EventList.vue'
import EventDetails from './components/EventDetails.vue'
import RegistrationForm from './components/RegistrationForm.vue'
import Footer from './components/Footer.vue'
```
- **Why imported:** To break down the application into modular, reusable Single File Components (SFCs).

### B. Event Data
```js
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
  // ...other sample events
])
```
- **Structure:** Each event contains unique `eventId`, `eventName`, `category`, `date`, `venue`, `registrationFee`, `maxParticipants`, `registeredParticipants`, and `status`.
- **Why in `App.vue`:** As required by the assignment, centralized state in the root component allows multiple child components (`EventList`, `EventDetails`, `RegistrationForm`) to share and update the same data without third-party state managers.

### C. `selectedCategory`
```js
const selectedCategory = ref('All Events')
```
- Stores the active category filter (e.g., `'All Events'`, `'Technical'`, `'Cultural'`, `'Workshop'`, `'Competition'`). When a user clicks a category button, `selectedCategory.value` changes.

### D. `searchQuery`
```js
const searchQuery = ref('')
```
- Binds to the search input via `v-model`. As the student types, `searchQuery.value` updates on every keystroke.

### E. `computed(filteredEvents)`
```js
const filteredEvents = computed(() => {
  return events.value.filter((event) => {
    const matchesCategory =
      selectedCategory.value === 'All Events' ||
      event.category.toLowerCase() === selectedCategory.value.toLowerCase()

    const matchesSearch = event.eventName
      .toLowerCase()
      .includes(searchQuery.value.trim().toLowerCase())

    return matchesCategory && matchesSearch
  })
})
```
- **Logic Flow:**
  ```text
  events array
       ↓
  Filtered by Category (or 'All Events')
       ↓
  Filtered by Search Name (case-insensitive)
       ↓
  filteredEvents (Passed as prop to EventList.vue)
  ```
- **Why `computed()` is used:** It is cached and automatically re-calculates only when `events`, `selectedCategory`, or `searchQuery` changes, keeping the UI fast and declarative without manual DOM manipulation.

### F. Selected Event & Registration State
```js
const selectedEvent = ref(null)
const registeringEvent = ref(null)
```
- `selectedEvent`: Holds the event object currently opened in `EventDetails.vue` (or `null` when closed).
- `registeringEvent`: Holds the event object currently opened in `RegistrationForm.vue` (or `null` when closed).

### G. Event Handlers
- `handleSelectEvent(event)`: Opens the event details modal by assigning `selectedEvent.value = event`.
- `handleOpenRegistration(event)`: Closes details and opens the registration form by assigning `registeringEvent.value = event`.
- `handleRegistrationSubmit(formData)`: Finds the event by `eventId`, increments `registeredParticipants` by 1, and updates `status`.

---

## 4. Header.vue

- **Role:** Displays the symposium branding ("IGNITE 2026"), title, and subtitle.
- **Why a separate component:** Keeps the header layout isolated and reusable.
- **Unscoped `<style>` vs `<style scoped>`:**
  - `Header.vue` uses normal unscoped `<style>` as requested by the assignment specification.
  - Unlike scoped CSS (which adds attributes like `data-v-xxxxx`), normal `<style>` applies globally.

---

## 5. EventList.vue

- **Role:** A grid container that receives the filtered list of events and renders an `EventCard` for each one.
- **Data Flow:**
  ```text
  App.vue (passes filteredEvents)
     ↓ props: :events="filteredEvents"
  EventList.vue (loops with v-for)
     ↓ props: :event="event"
  EventCard.vue
  ```
- **Code implementation:**
  ```vue
  <template>
    <div class="event-grid">
      <EventCard
        v-for="event in events"
        :key="event.eventId"
        :event="event"
        @view-details="$emit('select-event', $event)"
      />
    </div>
  </template>
  ```
- **Forwarding Events:** When `EventCard` emits `@view-details`, `EventList` immediately emits `@select-event` to `App.vue`.

---

## 6. EventCard.vue

`EventCard.vue` is a key component demonstrating core Vue template features.

### A. Props
```js
const props = defineProps({
  event: {
    type: Object,
    required: true
  }
})
```
- Receives a single `event` object from `EventList.vue`.

### B. Available Seats Calculation
```js
const availableSeats = computed(() => {
  return props.event.maxParticipants - props.event.registeredParticipants
})
```
- Calculated dynamically rather than stored as an independent data variable.

### C. Status with `v-if`, `v-else-if`, `v-else`
```vue
<span v-if="availableSeats > 10" class="status-badge status-open">
  Registration Open
</span>
<span v-else-if="availableSeats > 0" class="status-badge status-almost-full">
  Almost Full
</span>
<span v-else class="status-badge status-closed">
  Registration Closed
</span>
```
- Conditionally renders one of three status badges depending on `availableSeats`.

### D. Dynamic `:class`
```js
const categoryClass = computed(() => {
  return `category-${props.event.category.toLowerCase()}`
})
```
```vue
<div class="event-card" :class="categoryClass">
```
- Applies CSS classes dynamically:
  - `category-technical` &rarr; Blue theme
  - `category-cultural` &rarr; Pink theme
  - `category-workshop` &rarr; Green theme
  - `category-competition` &rarr; Orange theme

### E. Dynamic `:style`
```js
const titleStyle = computed(() => {
  if (availableSeats.value > 20) {
    return { fontSize: '24px', fontWeight: '700' }
  } else if (availableSeats.value >= 10) {
    return { fontSize: '20px', fontWeight: '700' }
  } else {
    return { fontSize: '16px', fontWeight: '700' }
  }
})
```
```vue
<h3 class="event-title" :style="titleStyle">{{ event.eventName }}</h3>
```
- Dynamically assigns inline font sizes (`24px`, `20px`, `16px`) based on seat urgency.

### F. Why `:key` is used with `v-for`
- `:key="event.eventId"` provides a unique identifier for Vue's Virtual DOM to track which specific items changed, reordered, or were removed, optimizing rendering performance.

### G. "View Details" Button
```vue
<button class="btn-view-details" @click="$emit('view-details', event)">
  View Details
</button>
```
- Clicking emits `view-details` along with the `event` object to the parent component.

---

## 7. EventDetails.vue

- **Role:** Modal dialog displaying full details for a chosen event.
- **Displayed Properties:** Event ID, Name, Category, Date, Venue, Registration Fee, Max Participants, Registered Participants, Available Seats, and Status.
- **Conditional Register Button (`v-if`):**
  ```vue
  <button
    v-if="availableSeats > 0"
    class="btn-primary"
    @click="$emit('open-registration', event)"
  >
    Register for Event
  </button>
  ```
  - Only visible when `availableSeats > 0`. If the event is full, the button is automatically removed from the DOM.
- **Action:** Clicking "Register for Event" emits `@open-registration` to `App.vue`.

---

## 8. RegistrationForm.vue

### Form Fields & `v-model`
```js
const studentName = ref('')
const registerNumber = ref('')
const email = ref('')
const department = ref('')
```
- **`v-model`** provides two-way data binding:
  ```text
  Input Element Value <═════ (v-model) ═════> Vue ref Variable
  ```
  When the user types in the input, the variable updates. If the variable is cleared in code, the input resets.

### Form Submission & Validation Flow
```js
const handleSubmit = () => {
  if (
    !studentName.value.trim() ||
    !registerNumber.value.trim() ||
    !email.value.trim() ||
    !department.value.trim()
  ) {
    errorMessage.value = 'Please fill in all fields (Student Name, Register Number, Email, Department).'
    return
  }

  emit('submit-registration', {
    eventId: props.event.eventId,
    studentName: studentName.value.trim(),
    registerNumber: registerNumber.value.trim(),
    email: email.value.trim(),
    department: department.value.trim()
  })

  successMessage.value = `Registration successful! ${studentName.value} is registered for ${props.event.eventName}.`
  // Clear fields
  studentName.value = ''
  registerNumber.value = ''
  email.value = ''
  department.value = ''
}
```

---

## 9. Footer.vue

- **Role:** Displays copyright information and lab assignment details.
- **Why a separate component:** Promotes modularity and keeps `App.vue` clean.
- **Scoped Style:** Uses `<style scoped>` to ensure footer styles never leak to other components.

---

## 10. Parent-Child Communication

In Vue.js, components follow the standard **"Props Down, Events Up"** pattern:

```text
┌────────────────────────────────────────┐
│               App.vue                  │
└───────┬────────────────────────▲───────┘
        │                        │
  props │ (Data down)     emits  │ (Events up)
        │                        │
┌───────▼────────────────────────┴───────┐
│       Child Components                 │
│  (EventList / Details / Form)          │
└────────────────────────────────────────┘
```

### Exact Examples from This Project:

1. **Props (Parent &rarr; Child):**
   - `App.vue` passes `:events="filteredEvents"` to `EventList.vue`.
   - `EventList.vue` passes `:event="event"` to `EventCard.vue`.
   - `App.vue` passes `:event="selectedEvent"` to `EventDetails.vue`.

2. **Emits (Child &rarr; Parent):**
   - `EventCard.vue` emits `view-details` &rarr; received by `EventList.vue`.
   - `EventList.vue` emits `select-event` &rarr; received by `App.vue`.
   - `EventDetails.vue` emits `open-registration` &rarr; received by `App.vue`.
   - `RegistrationForm.vue` emits `submit-registration` &rarr; received by `App.vue`.

---

## 11. Complete User Flow

Here is what happens step-by-step during application execution:

```text
1. Application Mounts
   └── App.vue initializes reactive events array and filter refs.

2. Initial Display
   └── computed(filteredEvents) evaluates all events.
   └── EventList loops via v-for and renders EventCard components.
   └── EventCard applies dynamic :class for category and :style for title size.

3. Student Searches / Filters
   └── Typing in search input updates searchQuery.
   └── Clicking category button updates selectedCategory.
   └── computed(filteredEvents) instantly filters the card list.

4. Student Clicks "View Details"
   └── EventCard emits view-details → EventList emits select-event → App.vue.
   └── App.vue sets selectedEvent.value = event.
   └── EventDetails modal appears with complete event information.

5. Student Clicks "Register for Event"
   └── EventDetails emits open-registration to App.vue.
   └── App.vue closes details modal and sets registeringEvent.value = event.
   └── RegistrationForm modal opens.

6. Student Submits Form
   └── Validation runs on submit:
       - If any of 4 fields are empty → Displays error message.
       - If valid → Emits submit-registration to App.vue.
   └── App.vue finds event by eventId and increments registeredParticipants by 1.
   └── availableSeats decreases automatically across the app.
   └── Event status updates automatically (Open → Almost Full → Closed).
   └── RegistrationForm displays "Registration successful!" and resets inputs.
```

---

## 12. Viva Questions and Answers

### Q1: Why is `events` stored in `App.vue`?
> **Answer:** Because `App.vue` is the root component. Storing the data in `App.vue` allows multiple child components (`EventList`, `EventDetails`, and `RegistrationForm`) to share, read, and update the same state via props and emits without external state libraries.

### Q2: Why is `ref()` used?
> **Answer:** `ref()` creates a reactive reference. When its value changes, Vue automatically detects the change and re-renders only the parts of the DOM that depend on it.

### Q3: What is a computed property (`computed()`)?
> **Answer:** A computed property is a reactive calculation that is cached based on its reactive dependencies. It only re-evaluates when its dependent reactive data changes.

### Q4: Why is `computed()` used for filtering events?
> **Answer:** `filteredEvents` depends on both `selectedCategory` and `searchQuery`. Using `computed()` ensures filtering happens automatically and efficiently without manually attaching change event listeners or manipulating the DOM.

### Q5: What are props in Vue?
> **Answer:** Props are custom attributes used to pass data down from a parent component to a child component. In `<script setup>`, they are defined using `defineProps()`.

### Q6: What is `defineEmits()`?
> **Answer:** `defineEmits()` is a compiler macro used in `<script setup>` to declare custom events that a child component can emit to notify its parent component of user actions.

### Q7: What is the difference between props and emits?
> **Answer:** Props pass data downwards (Parent &rarr; Child), while emits send messages/events upwards (Child &rarr; Parent).

### Q8: Why is `v-for` used?
> **Answer:** `v-for` is a directive used to render a list of items by iterating over an array (such as rendering an `EventCard` for each event in `events`).

### Q9: Why do we need `:key` with `v-for`?
> **Answer:** `:key` gives each rendered element a unique identity so Vue's Virtual DOM algorithm can track, update, and reuse DOM nodes efficiently when items change or are filtered.

### Q10: What is `:class` dynamic binding?
> **Answer:** `:class` allows class names to be dynamically applied or removed based on component data or computed properties (e.g., `:class="categoryClass"` to style Technical vs. Cultural events).

### Q11: What is `:style` dynamic binding?
> **Answer:** `:style` binds inline CSS styles directly from JavaScript objects (e.g., `:style="titleStyle"` to adjust font size dynamically based on available seats).

### Q12: What is the difference between `v-if` and normal HTML?
> **Answer:** Normal HTML elements are always present in the DOM. The `v-if` directive conditionally creates or completely destroys the element in the DOM depending on whether the expression evaluates to `true` or `false`.

### Q13: How are available seats calculated?
> **Answer:** As a computed value: `props.event.maxParticipants - props.event.registeredParticipants`. It is never hardcoded as a separate state variable.

### Q14: How does registration update the event count?
> **Answer:** When the valid form is submitted, it emits `submit-registration` with the `eventId`. `App.vue` finds that event in its `events.value` array and increments `registeredParticipants` by 1.

### Q15: How does the status change automatically from "Registration Open" to "Almost Full" or "Registration Closed"?
> **Answer:** Because the status badges and calculations depend reactively on `registeredParticipants`. As soon as `registeredParticipants` increments, `availableSeats` re-calculates, causing `v-if / v-else-if / v-else` to update the badge immediately.

---

## 13. Code Patterns to Memorize (Cheat Sheet)

```js
// 1. Reactive Variable: Creates reactive state that updates UI when changed
const value = ref('initial')

// 2. Computed Property: Returns a cached derived calculation from reactive data
const filtered = computed(() => items.value.filter(...))

// 3. Props Definition: Declares data passed down from the parent component
const props = defineProps({ event: Object })

// 4. Emits Definition: Declares custom events sent up to the parent component
const emit = defineEmits(['select-event'])
```

```vue
<!-- 5. Interpolation: Outputs text directly into HTML -->
<span>{{ event.eventName }}</span>

<!-- 6. v-for: Loops through an array to render elements -->
<EventCard v-for="event in events" :key="event.eventId" :event="event" />

<!-- 7. v-if, v-else-if, v-else: Conditionally renders HTML elements -->
<span v-if="seats > 10">Open</span>
<span v-else-if="seats > 0">Almost Full</span>
<span v-else>Closed</span>

<!-- 8. Dynamic :class: Applies CSS classes based on data -->
<div :class="`category-${event.category.toLowerCase()}`"></div>

<!-- 9. Dynamic :style: Applies inline CSS rules dynamically -->
<h3 :style="{ fontSize: seats > 20 ? '24px' : '16px' }">Title</h3>

<!-- 10. @click: Listens to mouse clicks and triggers methods or emits -->
<button @click="$emit('view-details', event)">View Details</button>

<!-- 11. v-model: Two-way binds form inputs to reactive ref variables -->
<input v-model="studentName" type="text" placeholder="Enter name" />
```
