<template>
  <div>
    <h1>Online Food Ordering</h1>

    <nav>
      <router-link to="/">Home</router-link>
      |
      <router-link to="/order/menu">Menu</router-link>
      |
      <router-link to="/order/cart">Cart</router-link>
      |
      <router-link to="/about">About</router-link>
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
  margin: 8px;
  color: #42b883;
}
</style>