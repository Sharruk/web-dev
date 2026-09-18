<template>
  <div>
    <h1>Order Page</h1>

    <nav>
      <router-link to="/order/menu">Food Menu</router-link>
      |
      <router-link to="/order/cart">Cart</router-link>
    </nav>

    <router-view />
  </div>
</template>

<style scoped>
div {
  text-align: center;
  padding: 20px;
}

nav {
  margin: 20px;
}

a {
  margin: 10px;
  color: #42b883;
}
</style>