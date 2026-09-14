<script setup>
// Header Component for College Symposium Management System
</script>

<template>
  <header class="app-header">
    <div class="header-container">
      <div class="brand">
        <span class="brand-badge">2026</span>
        <h1>IGNITE - College Symposium Management</h1>
      </div>
      <p class="tagline">Explore, Compete, and Innovate across Technical, Cultural, Workshops, and Competitions</p>
    </div>
  </header>
</template>

<!-- Requirement: Header.vue uses normal <style>, NOT scoped style -->
<style>
.app-header {
  background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
  color: #ffffff;
  padding: 2.2rem 1.5rem;
  text-align: center;
  border-radius: 12px;
  margin-bottom: 2rem;
  box-shadow: 0 8px 24px rgba(30, 60, 114, 0.2);
}

.app-header .header-container {
  max-width: 900px;
  margin: 0 auto;
}

.app-header .brand {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.75rem;
  flex-wrap: wrap;
  margin-bottom: 0.5rem;
}

.app-header .brand-badge {
  background-color: #ff9800;
  color: #ffffff;
  font-weight: 700;
  font-size: 0.85rem;
  padding: 0.25rem 0.6rem;
  border-radius: 6px;
  letter-spacing: 1px;
}

.app-header h1 {
  font-size: 2.1rem;
  font-weight: 800;
  letter-spacing: -0.5px;
  margin: 0;
}

.app-header .tagline {
  font-size: 1.05rem;
  opacity: 0.92;
  margin: 0.5rem 0 0;
}
</style>
