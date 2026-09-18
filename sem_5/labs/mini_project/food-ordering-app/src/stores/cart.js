import { defineStore } from "pinia";

export const useCartStore = defineStore("cart", {
  state: () => ({
    cart: [],
  }),

  getters: {
    totalItems: (state) => {
      return state.cart.reduce((total, item) => total + item.quantity, 0);
    },

    totalBill: (state) => {
      return state.cart.reduce(
        (total, item) => total + item.price * item.quantity,
        0
      );
    },
  },

  actions: {
    addToCart(food) {
      const item = this.cart.find((item) => item.id === food.id);

      if (item) {
        item.quantity++;
      } else {
        this.cart.push({
          ...food,
          quantity: 1,
        });
      }
    },

    increaseQuantity(id) {
      const item = this.cart.find((item) => item.id === id);

      if (item) {
        item.quantity++;
      }
    },

    decreaseQuantity(id) {
      const item = this.cart.find((item) => item.id === id);

      if (item && item.quantity > 1) {
        item.quantity--;
      }
    },

    removeFromCart(id) {
      this.cart = this.cart.filter((item) => item.id !== id);
    },

    clearCart() {
      this.cart = [];
    },
  },
});