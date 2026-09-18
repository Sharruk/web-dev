import { defineStore } from 'pinia'

// Pinia store for managing shopping cart state
export const useCartStore = defineStore('cart', {
  // 1. STATE: reactive array of food items
  state: () => ({
    items: [] // each item: { id, name, price, quantity }
  }),

  // 2. GETTERS: computed properties from state
  getters: {
    // Total count of all items in cart
    totalItems: (state) => {
      return state.items.reduce((sum, item) => sum + item.quantity, 0)
    },

    // Total cost of all items
    totalBill: (state) => {
      return state.items.reduce((sum, item) => sum + item.price * item.quantity, 0)
    }
  },

  // 3. ACTIONS: functions to modify state
  actions: {
    // Add item to cart (increase quantity if already exists)
    addToCart(food) {
      const existing = this.items.find((item) => item.id === food.id)
      if (existing) {
        existing.quantity++
      } else {
        this.items.push({ ...food, quantity: 1 })
      }
    },

    // Increment item quantity by 1
    increaseQuantity(id) {
      const item = this.items.find((item) => item.id === id)
      if (item) {
        item.quantity++
      }
    },

    // Decrement item quantity by 1 (minimum 1)
    decreaseQuantity(id) {
      const item = this.items.find((item) => item.id === id)
      if (item && item.quantity > 1) {
        item.quantity--
      }
    },

    // Remove single item from cart completely
    removeFromCart(id) {
      this.items = this.items.filter((item) => item.id !== id)
    },

    // Clear entire cart
    clearCart() {
      this.items = []
    }
  }
})
