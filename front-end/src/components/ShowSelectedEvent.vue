<script setup>
const prop = defineProps({
  eventDetail: {
    type: Object,
    require: true,
  },
  eventCategory: {
    type: String,
    require: true,
  },
});
defineEmits(['clear'])

// const eventCategory = prop.eventDetail.eventCategoryID.eventCategoryName;
</script>

<template>
  <div>
    <div class="text-2xl mx-auto text-center pt-72">
      <b>Name: {{ prop.eventDetail.bookingName }}</b><br>
      <b>Category: {{ prop.eventCategory }}</b>
      <p>Date: {{ prop.eventDetail.eventStartTime }}</p>
      <p>Duration: {{ prop.eventDetail.eventDuration }}</p>
      <p>Email: {{ prop.eventDetail.bookingEmail }}</p>
      <p v-show="prop.eventDetail.eventNotes != ''">Notes: {{ prop.eventDetail.eventNotes }}</p>
      <button class="rounded-xl bg-blue-200 w-24 mt-10" @click="$emit('clear', $event)">Clear</button>
    </div>
  </div>
</template>

<style>
</style>
