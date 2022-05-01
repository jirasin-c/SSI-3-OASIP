<script setup>
import EventCard from "../components/EventCard.vue";
import ShowSelectedEvent from "../components/ShowSelectedEvent.vue";
import { onBeforeMount, ref } from "vue";
const event = ref([]);
const isEmpty = ref(false);
const selectedEvent = ref([]);
const getEventCategoryName = ref("");

const getEvent = async () => {
  const res = await fetch("http://localhost:3000/api/event");
  event.value = await res.json();
  if (event.value.length == 0) {
    isEmpty.value = true;
  } else {
    isEmpty.value = false;
  }
  console.log(isEmpty.value);
};

const convertToLocalDate = async () => {
  event.value.filter((e) => {
    const localDate = new Date(e.eventStartTime).toLocaleString();
    e.eventStartTime = localDate;
  });
};

const getDetailById = async (eventId) => {
  const res = await fetch(`http://localhost:3000/api/event/${eventId}`);
  selectedEvent.value = await res.json();
  getEventCategoryName.value =
    selectedEvent.value.eventCategoryID.eventCategoryName;
};

onBeforeMount(async () => {
  await getEvent();
  await convertToLocalDate();
});
</script>

<template>
  <div>
    <div>
      <div class="mt-10 ml-16 text-2xl">CURRENT BOOKING</div>
    </div>
    <div class="flex flex-row h-screen">
      <div
        class="bg-gray-200 w-5/6 h-3/4 ml-16 mt-12 mr-16 rounded-2xl overflow-auto"
      >
        <div v-show="isEmpty" class="grid justify-items-center pt-72">
          <p class="text-2xl text-gray-400">List currently empty</p>
        </div>

        <div
          class="columns-3 gap-6 w-[1100px] mx-auto space-y-6 pb-28 text-2xl mt-10"
          id="style-1"
        >
          <EventCard :allBooking="event" @viewDetail="getDetailById" />
        </div>
      </div>

      <div class="bg-gray-200 w-2/6 h-3/4 mt-12 mr-16 rounded-2xl">
        <p
          v-if="selectedEvent.length == 0"
          class="text-center pt-72 text-2xl text-gray-400"
        >
          Please select some event.
        </p>
        <ShowSelectedEvent :eventDetail="selectedEvent" :eventCategory="getEventCategoryName" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
