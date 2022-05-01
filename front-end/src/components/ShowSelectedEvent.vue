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

// const eventCategory = prop.eventDetail.eventCategoryID.eventCategoryName;
</script>

<template>
  <div>
    <div class="container mx-auto bg-white">
      {{ prop.eventDetail.bookingName }}<br>
      {{ prop.eventCategory }}<br>
      {{ prop.eventDetail.eventStartTime }}<br>
      {{ prop.eventDetail.eventDuration }}
    </div>
  </div>
</template>

<style></style>
