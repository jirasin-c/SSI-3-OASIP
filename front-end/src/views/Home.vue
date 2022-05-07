<script setup>
import EventCard from "../components/EventCard.vue";
import ShowSelectedEvent from "../components/ShowSelectedEvent.vue";
import { onBeforeMount, ref } from "vue";
const event = ref([]);
const isEmpty = ref(false);

const getEvent = async () => {
  const res = await fetch("http://localhost:3000/api/events");
  event.value = await res.json();
  event.value.filter((e) => {
    const localDate = new Date(e.eventStartTime).toLocaleString("th-TH", {
      weekday: "short",
      month: "short",
      day: "numeric",
      year: "2-digit",
      hour: "numeric",
      minute: "numeric",
    });
    e.eventStartTime = localDate;
  });
  if (event.value.length == 0) {
    isEmpty.value = true;
  } else {
    isEmpty.value = false;
  }
  // console.log(isEmpty.value);
};

onBeforeMount(async () => {
  await getEvent();
});
</script>

<template>
  <div>
    <div>
      <div class="mt-10 ml-16 text-3xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-purple-400 to-pink-600">CURRENT BOOKING</div>
    </div>
    <div class="flex flex-row h-screen">
      <div
        class="shadow-inner shadow-lg glass w-full h-3/4 ml-16 mt-12 mr-16 rounded-2xl overflow-auto"
      >
        <div v-show="isEmpty" class="grid justify-items-center pt-72">
          <p class="text-2xl text-gray-400">List currently empty</p>
        </div>

        <div
          class="columns-3 gap-6 w-[1700px] mx-auto space-y-6 pb-6 text-2xl mt-10"
          id="style-1"
        >
          <EventCard :allBooking="event" />
        </div>
      </div>
      <!-- 
      <div class="bg-gray-200 w-2/6 h-3/4 mt-12 mr-16 rounded-2xl">
        <p v-if="selectedEvent.length == 0" class="text-center pt-72 text-2xl text-gray-400">
          Please select some event.
        </p>
        <ShowSelectedEvent v-else :eventDetail="selectedEvent" :eventCategory="getEventCategoryName"
          @clear="clearDetail" />
      </div> -->
    </div>
  </div>
</template>

<style scoped></style>
