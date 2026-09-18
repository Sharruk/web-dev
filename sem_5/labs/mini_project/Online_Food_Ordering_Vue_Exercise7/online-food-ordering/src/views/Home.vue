<template>
  <section class="page home-page">
    <div class="hero">
      <h1>Online Food Ordering</h1>
      <p class="tagline">Order your favorite food easily.</p>
      <RouterLink to="/order/menu" class="btn btn-primary btn-large">
        Browse Food Menu
      </RouterLink>
    </div>

    <div class="home-highlights">
      <div class="highlight-card">
        <h3>Wide Variety</h3>
        <p>Delicious food items prepared fresh.</p>
      </div>
      <div class="highlight-card">
        <h3>Easy Cart</h3>
        <p>Simple and instant cart management.</p>
      </div>
      <div class="highlight-card">
        <h3>Fast Ordering</h3>
        <p>Quick navigation with Vue Router.</p>
      </div>
    </div>
  </section>
</template>

<script setup>
// Simple Home landing page
</script>
