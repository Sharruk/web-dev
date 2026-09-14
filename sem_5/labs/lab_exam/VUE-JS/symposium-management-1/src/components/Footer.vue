<script setup>
// Footer Component
const currentYear = new Date().getFullYear()
</script>

<template>
  <footer class="app-footer">
    <div class="footer-content">
      <p>© {{ currentYear }} IGNITE College Symposium Management System.</p>
      <p class="sub-text">Developed for Web Development Lab Assignment - Vue.js</p>
    </div>
  </footer>
</template>

<style scoped>
.app-footer {
  margin-top: 3rem;
  padding: 1.5rem 1rem;
  background-color: #ffffff;
  border-top: 1px solid #e0e0e0;
  border-radius: 8px;
  text-align: center;
  color: #666;
  font-size: 0.9rem;
}

.footer-content p {
  margin: 0.2rem 0;
}

.sub-text {
  font-size: 0.8rem;
  color: #888;
}
</style>
