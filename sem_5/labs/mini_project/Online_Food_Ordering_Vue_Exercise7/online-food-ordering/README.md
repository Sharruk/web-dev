# Online Food Ordering Application

## Student Details
| Field | Value |
|---|---|
| Student Name | Nathan |
| Subject Name | Web Application Development Lab |
| Exercise | 7 – Online Food Ordering Application using Vue JS Routing and State Management |

---

## 1. Problem Statement & Architecture
Develop a Vue.js Online Food Ordering Single Page Application (SPA) using:
- **Vue 3 (`<script setup>` Composition API)**
- **Vue Router 4** with **Nested Child Routes** (`/order/menu` and `/order/cart`)
- **Pinia 2** for centralized Shopping Cart state management
- **Clean Orange & White Theme** (No unnecessary external CSS libraries)

### Component Hierarchy
```
                   App.vue (Navbar + Global Footer)
                         │
                  <RouterView />
           ┌─────────────┼─────────────┐
           ▼             ▼             ▼
        Home.vue      Order.vue     About.vue
                         │
                 Nested <RouterView />
                 ┌───────┴───────┐
                 ▼               ▼
            FoodMenu.vue      Cart.vue
                 │               │
         (Props & Emits)   (Reads Pinia Store)
                 ▼               ▲
            FoodItem.vue         │
                 └───────────────┘
             Dispatches to Pinia: cartStore.js
```

---

## 2. Code Explanations & Annotations Guide

### A. Vue 3 `<script setup>`
- **What it is**: Syntactic sugar for the Composition API in Single File Components (SFC).
- **Why use it**: Less boilerplate code. Any top-level variable, function, or import declared inside `<script setup>` is automatically exposed and usable directly in the `<template>` without needing a `return { ... }` block.

### B. Vue Directives Used
- `v-for="item in foodItems" :key="item.id"`: Iterates over the array and renders a component for each item. The `:key` gives Vue a unique ID to optimize virtual DOM updates.
- `v-if` / `v-else`: Conditionally renders elements in the DOM (e.g., showing empty cart message vs. showing item list).
- `@click="functionName"`: Event listener syntax (shorthand for `v-on:click`).
- `:id="item.id"`: Property binding syntax (shorthand for `v-bind:id`).

### C. Component Communication: Props & Emits
- **Props (Parent to Child)**: `FoodMenu.vue` passes properties (`id`, `food`, `price`) down to `FoodItem.vue`.
  ```javascript
  const props = defineProps({
    id: { type: Number, required: true },
    food: { type: String, required: true },
    price: { type: Number, required: true }
  })
  ```
- **Emits (Child to Parent)**: `FoodItem.vue` notifies `FoodMenu.vue` when the button is clicked.
  ```javascript
  const emit = defineEmits(['add-to-cart'])
  function handleAddToCart() {
    emit('add-to-cart', { id: props.id, name: props.food, price: props.price })
  }
  ```

### D. Vue Router & Nested (Child) Routing
Defined in `src/router/index.js`:
- `/` renders `Home.vue`
- `/about` renders `About.vue`
- `/order` renders `Order.vue` which contains a nested `<RouterView />`:
  - `/order/menu` renders `FoodMenu.vue` inside `Order.vue`
  - `/order/cart` renders `Cart.vue` inside `Order.vue`
- `<RouterLink to="...">`: Generates client-side navigation links that prevent full browser page refreshes.

### E. Pinia State Management (`src/stores/cartStore.js`)
- **`state`**: Holds reactive data (`items: []`).
- **`getters`**: Computed properties derived from state (`totalItems`, `totalBill`). They auto-update whenever `items` changes.
- **`actions`**: Methods that mutate the state (`addToCart`, `increaseQuantity`, `decreaseQuantity`, `removeFromCart`, `clearCart`).

---

## 3. Top 12 Live Coding & Viva Modifications (Teacher Questions)

Here are the exact changes examiners ask students to make live during lab evaluation:

---

### Task 1: "Add a new food item to the menu"
**File to open**: `src/views/FoodMenu.vue`  
**Where to change**: Find `const foodItems = [...]` and add a new object:
```javascript
const foodItems = [
  { id: 1, name: 'Pizza', price: 250 },
  { id: 2, name: 'Burger', price: 150 },
  { id: 3, name: 'Pasta', price: 200 },
  { id: 4, name: 'Coke', price: 80 },
  { id: 5, name: 'Sandwich', price: 120 } // <-- Added item
]
```

---

### Task 2: "Apply a 10% Discount to the Total Bill"
**File to open**: `src/stores/cartStore.js`  
**Where to change**: In `getters`, add or modify `totalBill`:
```javascript
getters: {
  totalBill: (state) => {
    const rawTotal = state.items.reduce((sum, item) => sum + item.price * item.quantity, 0)
    return Math.round(rawTotal * 0.90) // 10% off
  }
}
```

---

### Task 3: "Add 5% GST / Tax to the Bill"
**File to open**: `src/stores/cartStore.js`  
**Where to change**: Add a new getter or change `totalBill`:
```javascript
totalBillWithTax: (state) => {
  const subtotal = state.items.reduce((sum, item) => sum + item.price * item.quantity, 0)
  return Math.round(subtotal * 1.05) // 5% GST
}
```
In `src/views/Cart.vue`:
```html
<p class="cart-total">Total (incl. 5% GST): Rs. {{ cart.totalBillWithTax }}</p>
```

---

### Task 4: "Auto-remove an item when quantity reaches 0"
*(Right now quantity stops at 1 when pressing `-`)*  
**File to open**: `src/stores/cartStore.js`  
**Where to change**: In `actions.decreaseQuantity`:
```javascript
decreaseQuantity(id) {
  const item = this.items.find((item) => item.id === id)
  if (item) {
    if (item.quantity > 1) {
      item.quantity--
    } else {
      this.removeFromCart(id) // removes completely if reduced below 1
    }
  }
}
```

---

### Task 5: "Change the Theme Color (e.g. from Orange to Blue or Green)"
**File to open**: `src/style.css`  
**Where to change**: Replace `#e0672c` with your target color:
- Blue: `#1e6091` or `#2563eb`
- Green: `#16a34a`
- Change in `.navbar`, `.navbar-brand`, `.btn-primary`, `.router-link-active`.

---

### Task 6: "Add a Food Category property to each food item"
**Step 1**: In `src/views/FoodMenu.vue`:
```javascript
const foodItems = [
  { id: 1, name: 'Pizza', price: 250, category: 'Fast Food' },
  { id: 2, name: 'Burger', price: 150, category: 'Fast Food' },
  { id: 3, name: 'Pasta', price: 200, category: 'Italian' },
  { id: 4, name: 'Coke', price: 80, category: 'Beverage' }
]
```
Pass `:category="item.category"` to `<FoodItem />`.  
**Step 2**: In `src/components/FoodItem.vue`:
Add `category: { type: String, required: false }` to `defineProps`, and display `<span class="category-badge">{{ category }}</span>`.

---

### Task 7: "Display an alert confirmation before clearing the cart"
**File to open**: `src/views/Cart.vue`  
**Where to change**: Create a handler function inside `<script setup>`:
```javascript
function confirmClearCart() {
  if (confirm("Are you sure you want to clear your entire cart?")) {
    cart.clearCart()
  }
}
```
In `<template>`:
```html
<button class="btn btn-danger" @click="confirmClearCart">Clear Cart</button>
```

---

### Task 8: "Add a 'Checkout' / 'Place Order' button"
**File to open**: `src/views/Cart.vue`  
**Where to change**: Under `<div class="cart-summary">`:
```html
<button class="btn btn-primary" @click="placeOrder">Place Order</button>
```
In `<script setup>`:
```javascript
function placeOrder() {
  alert(`Order placed successfully! Total amount: Rs. ${cart.totalBill}`)
  cart.clearCart()
}
```

---

### Task 9: "Add an upper limit / maximum quantity per item (e.g., max 5)"
**File to open**: `src/stores/cartStore.js`  
**Where to change**: In `actions.increaseQuantity`:
```javascript
increaseQuantity(id) {
  const item = this.items.find((item) => item.id === id)
  if (item) {
    if (item.quantity < 5) {
      item.quantity++
    } else {
      alert("Maximum quantity limit of 5 reached for this item.")
    }
  }
}
```

---

### Task 10: "Add a Search / Filter bar for food items"
**File to open**: `src/views/FoodMenu.vue`  
**Where to change**:
```javascript
import { ref, computed } from 'vue'

const searchQuery = ref('')

const filteredFoodItems = computed(() => {
  return foodItems.filter(item =>
    item.name.toLowerCase().includes(searchQuery.value.toLowerCase())
  )
})
```
In `<template>`:
```html
<input v-model="searchQuery" placeholder="Search food..." style="padding: 0.5rem; margin-bottom: 1rem; width: 100%;" />
<FoodItem v-for="item in filteredFoodItems" ... />
```

---

### Task 11: "Add a Delivery Fee (Free if Total > Rs. 300, else Rs. 40)"
**File to open**: `src/stores/cartStore.js`  
**Where to change**: In `getters`:
```javascript
deliveryFee: (state) => {
  const total = state.items.reduce((sum, item) => sum + item.price * item.quantity, 0)
  if (total === 0) return 0
  return total >= 300 ? 0 : 40
},
finalBill: (state) => {
  const subtotal = state.items.reduce((sum, item) => sum + item.price * item.quantity, 0)
  const fee = subtotal >= 300 ? 0 : 40
  return subtotal + fee
}
```

---

### Task 12: "Redirect automatically to Menu page if Cart is empty when visiting `/order/cart`"
**File to open**: `src/views/Cart.vue`  
**Where to change**:
```javascript
import { useRouter } from 'vue-router'
import { onMounted } from 'vue'

const router = useRouter()

onMounted(() => {
  if (cart.items.length === 0) {
    // Optionally redirect: router.push('/order/menu')
  }
})
```

---

## 4. How to Run the Application
```bash
cd C:\Users\natha\Downloads\Online_Food_Ordering_Vue_Exercise7\online-food-ordering
npm install
npm run dev
```
Open your browser at `http://localhost:5173`.
