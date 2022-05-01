<script setup>
import EventCard from "../components/EventCard.vue";
const dataList = [
  {
    id: 1,
    name: "Jirasin",
    category: "Database Clinic",
    date: "2022-04-23",
    duration: 30,
    notes: "อยากสอบถามเกี่ยวกับการออกแบบ Databases ครับ",
  },
  {
    id: 2,
    name: "Jirasin",
    category: "Database Clinic",
    date: "2022-04-23",
    duration: 30,
    notes: null,
  },
  {
    id: 3,
    name: "Jirasin",
    category: "Database Clinic",
    date: "2022-04-23",
    duration: 30,
    notes: "อยากสอบถามเกี่ยวกับการออกแบบ Databases ครับ",
  },
  {
    id: 4,
    name: "Jirasin",
    category: "Database Clinic",
    date: "2022-04-23",
    duration: 30,
    notes: "อยากสอบถามเกี่ยวกับการออกแบบ Databases ครับ",
  },
  {
    id: 5,
    name: "Jirasin",
    category: "Database Clinic",
    date: "2022-04-23",
    duration: 30,
    notes: null,
  },
  {
    id: 6,
    name: "Jirasin",
    category: "Database Clinic",
    date: "2022-04-23",
    duration: 30,
    notes: null,
  },
  {
    id: 7,
    name: "Jirasin",
    category: "Database Clinic",
    date: "2022-04-23",
    duration: 30,
    notes: "อยากสอบถามเกี่ยวกับการออกแบบ Databases ครับ",
  },
  {
    id: 8,
    name: "Jirasin",
    category: "Database Clinic",
    date: "2022-04-23",
    duration: 30,
    notes: null,
  },
  {
    id: 9,
    name: "Jirasin",
    category: "Database Clinic",
    date: "2022-04-23",
    duration: 30,
    notes: "อยากสอบถามเกี่ยวกับการออกแบบ Databases ครับ",
  },
];
</script>

<template>
  <div>
    <div class="flex justify-between">
      <div class="mt-10 ml-16 text-2xl">CURRENT BOOKING</div>
      <div class="mt-10 mr-96 text-2xl">SELECTED EVENT</div>
    </div>
    <div class="flex flex-row h-screen">
      <div
        class="bg-gray-100 w-5/6 h-3/4 ml-16 mt-12 mr-16 rounded-2xl overflow-auto"
      >
        <div
          class="columns-3 gap-6 w-[1100px] mx-auto space-y-6 pb-28 text-2xl mt-10"
          id="style-1"
        >
          <p v-if="dataList.length === 0" class="text-2xl text-gray-400">
            List currently empty
          </p>

          <EventCard :allBooking="dataList" />
        </div>
      </div>
      <div class="bg-gray-100 w-2/6 h-3/4 mt-12 mr-16 rounded-2xl">
        <p class="text-center pt-72 text-2xl text-gray-400">
          Please select some event.
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
