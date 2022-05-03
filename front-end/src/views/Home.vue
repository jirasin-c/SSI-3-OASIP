<script setup>
import EventCard from "../components/EventCard.vue";
import ShowSelectedEvent from "../components/ShowSelectedEvent.vue";
import { onBeforeMount, ref } from "vue";
const event = ref([]);
const isEmpty = ref(false);
const selectedEvent = ref([]);
const getEventCategoryName = ref("");

const getEvent = async () => {
  const res = await fetch("http://localhost:3000/api/events");
  event.value = await res.json();
  event.value.filter((e) => {
    const localDate = new Date(e.eventStartTime).toLocaleString('th-TH', { weekday: "short", month: "short", day: "numeric", year: "2-digit", hour: "numeric", minute: "numeric" })
    e.eventStartTime = localDate;
  });
  if (event.value.length == 0) {
    isEmpty.value = true;
  } else {
    isEmpty.value = false;
  }
  console.log(isEmpty.value);
};


const getDetailById = async (eventId) => {
  const res = await fetch(`http://localhost:3000/api/events/${eventId}`);
  selectedEvent.value = await res.json();
  getEventCategoryName.value =
    selectedEvent.value.eventCategoryID.eventCategoryName;
  const localDate = new Date(selectedEvent.value.eventStartTime).toLocaleString('th-TH', { weekday: "short", month: "short", day: "numeric", year: "2-digit", hour: "numeric", minute: "numeric" })
  selectedEvent.value.eventStartTime = localDate;
};

const clearDetail = () => {
  selectedEvent.value = ''
}
onBeforeMount(async () => {
  await getEvent();
});

</script>

<template>
  <div>
    <div>
      <div class="mt-10 ml-16 text-2xl">CURRENT BOOKING</div>
    </div>
    <div class="flex flex-row h-screen">
      <div class="bg-gray-200 w-full h-3/4 ml-16 mt-12 mr-16 rounded-2xl overflow-auto">
        <div v-show="isEmpty" class="grid justify-items-center pt-72">
          <p class="text-2xl text-gray-400">List currently empty</p>
        </div>

        <div class="columns-3 gap-6 w-[1700px] mx-auto space-y-6 pb-28 text-2xl mt-10" id="style-1">
          <EventCard :allBooking="event" @viewDetail="getDetailById" />
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

<style scoped>
</style>
