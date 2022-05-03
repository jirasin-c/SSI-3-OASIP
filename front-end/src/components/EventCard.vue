<script setup>
import MdiTrashCan from "./MdiTrashCan.vue";
import { ref } from "vue";
const prop = defineProps({
  allBooking: {
    type: Array,
    require: true,
  },
});

defineEmits(["viewDetail"]);
</script>

<template>
  <div>
    <div
      v-for="booking in allBooking"
      :key="booking.id"
      class="cursor hover:bg-zinc-200 transition duration-300 grid break-inside-avoid rounded-xl p-4 mb-8 bg-white w-full break-words drop-shadow-md text-ellipsis overflow-hidden"
      @click="$emit('viewDetail', booking.id)"
    >
      <div class="badge badge-outline">
        {{ booking.eventCategoryID.eventCategoryName }}
      </div>
      <div class="flex flex-row">
        <div>
          <span
            ><b>Name: {{ booking.bookingName }}</b
            ><br />
            <b
              >Category: {{ booking.eventCategoryID.eventCategoryName }}</b
            ></span
          >

          <br />
          Date: {{ booking.eventStartTime }}<br />
          Duration: {{ booking.eventDuration }}<br />
          <label for="my-modal-3" class="btn modal-button mt-2">open modal</label>
        </div>
      </div>
    </div>
    <input type="checkbox" id="my-modal-3" class="modal-toggle" />
    <div class="modal">
      <div class="modal-box relative">
        <label
          for="my-modal-3"
          class="btn btn-sm btn-circle absolute right-2 top-2"
          >✕</label
        >
        <h3 class="text-lg font-bold">Congratulations random Interner user!</h3>
        <p class="py-4">
          You've been selected for a chance to get one year of subscription to
          use Wikipedia for free!
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.button2:hover {
  box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24),
    0 17px 50px 0 rgba(0, 0, 0, 0.19);
}

.cursor {
  cursor: pointer;
}
</style>
