<template>
  <section class="page about-page">
    <h1>About This Application</h1>
    <p>
      This is a Vue.js Online Food Ordering application built as part of the
      Web Application Development Lab (Exercise 7). It demonstrates
      <strong>Vue Router</strong> with nested routes and
      <strong>Pinia</strong> for centralized state management.
    </p>
    <ul class="about-list">
      <li>Home, Order, and About pages linked with Vue Router</li>
      <li>Nested routes for Food Menu and Cart under <code>/order</code></li>
      <li>A Pinia store that holds cart items, actions, and getters</li>
      <li>Reusable components using props and emits</li>
    </ul>
    <p class="about-footer-note">
      Developed by Sharruk S &middot; Register No. 3122247001061
    </p>
  </section>
</template>

<script setup>
// Static informational page - no state or logic required.
</script>
