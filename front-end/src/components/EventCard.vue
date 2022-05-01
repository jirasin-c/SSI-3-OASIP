<script setup>
const prop = defineProps({
  allBooking: {
    type: Array,
    require: true,
  },
});
</script>

<template>
  <div>
    <div
      v-for="booking in allBooking"
      :key="booking.id"
      class="grid break-inside-avoid rounded-xl p-10 bg-white w-full break-words drop-shadow-lg text-ellipsis overflow-hidden mt-10"
    >
      <span
        ><b>Name: {{ booking.name }}</b
        ><br />
        <b>Category: {{ booking.category }}</b></span
      ><br />
      Date: {{ booking.date }}<br />
      Duration: {{ booking.duration }}<br />
      Notes: {{ booking.notes }}
    </div>
  </div>
</template>

<style></style>
