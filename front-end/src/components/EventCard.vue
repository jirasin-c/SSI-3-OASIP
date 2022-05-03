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
    <div v-for="booking in allBooking" :key="booking.id">
      <router-link :to="{ name: 'Detail', params: { bookingId: booking.id } }">
        <div
          class="cursor hover:bg-zinc-200 transition duration-300 grid break-inside-avoid rounded-xl p-4 mb-8 w-full break-words drop-shadow-md text-ellipsis overflow-hidden bg-gradient-to-r from-white to-gray-200 text-slate-500"
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
              Duration: {{ booking.eventDuration }} min.<br />
            </div>
          </div>
        </div>
      </router-link>
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
