<template>
  <div class="page">
    <h1>Welcome to Food Ordering App</h1>

    <p>Order your favorite food easily!</p>

    <router-link to="/order/menu">
      <button>Order Food</button>
    </router-link>
  </div>
</template>

<style scoped>
.page {
  text-align: center;
  padding: 40px;
}

button {
  padding: 10px 20px;
  background-color: #42b883;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>