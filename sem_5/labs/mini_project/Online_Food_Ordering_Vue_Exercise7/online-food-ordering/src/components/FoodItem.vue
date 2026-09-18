<template>
  <div class="food-card">
    <div class="food-info">
      <h3 class="food-name">{{ food }}</h3>
      <p class="food-price">Rs. {{ price }}</p>
    </div>
    <button class="btn btn-primary" @click="handleAddToCart">
      Add to Cart
    </button>
  </div>
</template>

<script setup>
const props = defineProps({
  id: { type: Number, required: true },
  food: { type: String, required: true },
  price: { type: Number, required: true }
})

const emit = defineEmits(['add-to-cart'])

function handleAddToCart() {
  emit('add-to-cart', {
    id: props.id,
    name: props.food,
    price: props.price
  })
}
</script>

<style scoped>
.food-card {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #ffffff;
  border: 1px solid #e0e0e0;
  border-radius: 6px;
  padding: 1rem 1.25rem;
}

.food-name {
  margin: 0;
  font-size: 1.1rem;
  color: #222;
}

.food-price {
  margin: 0.25rem 0 0;
  color: #e0672c;
  font-weight: 700;
  font-size: 1rem;
}
</style>
