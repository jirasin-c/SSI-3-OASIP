<script setup>
import MdiTrashCan from "./MdiTrashCan.vue";
import { ref } from "vue";
const prop = defineProps({
  allBooking: {
    type: Array,
    require: true,
  },
});

// const bookingList = ref(allBooking);

// const selectedEvent = [];

// const getSelectedDetail = (selected) => {
//   selectedEvent.push(bookingList.selected);
//   console.log(selectedEvent);
// };
</script>

<template>
  <div>
    <div
      v-for="(booking, index) in allBooking"
      :key="booking.id"
      class="grid break-inside-avoid rounded-xl p-10 bg-white w-full break-words drop-shadow-lg text-ellipsis overflow-hidden mt-10"
      @click="getSelectedDetail(index)"
    >
      <div class="flex flex-row">
        <div>
          <span
            ><b>Name: {{ booking.name }}</b
            ><br />
            <b>Category: {{ booking.category }}</b></span
          >

          <br />
          Date: {{ booking.date }}<br />
          Duration: {{ booking.duration }}<br />
          Notes: {{ booking.notes }}
        </div>
        <div>
          <button
            @click="editMsg"
            class="btn btn-outline btn-circle h-[30px] w-[30px]"
          >
            <MdiTrashCan />
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style></style>
