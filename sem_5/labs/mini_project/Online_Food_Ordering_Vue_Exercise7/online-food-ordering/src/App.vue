<template>
  <div id="app-shell">
    <header class="navbar">
      <div class="navbar-brand">FoodExpress</div>
      <nav class="nav-links">
        <RouterLink to="/" class="nav-link">Home</RouterLink>
        <RouterLink to="/order/menu" class="nav-link">Order</RouterLink>
        <RouterLink to="/about" class="nav-link">About</RouterLink>
      </nav>
    </header>

    <main class="page-content">
      <RouterView />
    </main>

    <footer class="app-footer">
      Web Application Development Lab &middot; Exercise 7 &middot; Vue Router + Pinia
    </footer>
  </div>
</template>

<script setup>
// App.vue provides the top-level layout (Navbar + Page Content + Footer)
</script>

<style>
@import './style.css';
</style>
