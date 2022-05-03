<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, onBeforeMount, onBeforeUnmount } from "vue";
import IcPerson from "../icons/IcBaselinePerson.vue";
import IcEmail from "../icons/IcBaselineEmail.vue";
import IcTimer from "../icons/IcBaselineTimer.vue";
import IcCalendar from "../icons/BiCalendarDateFill.vue";
import IcNote from "../icons/PhNoteFill.vue";
const selectedEvent = ref([]);
const getEventCategoryName = ref("");
let { params } = useRoute();
const bookingId = params.bookingId;
const recentBooking = ref("");

const getDetailById = async () => {
  if (recentBooking.value !== "") {
    const res = await fetch(`/api/events/${recentBooking}`);
    selectedEvent.value = await res.json();
  }
  const res = await fetch(`/api/events/${bookingId}`);
  selectedEvent.value = await res.json();
  recentBooking.value = bookingId;
  getEventCategoryName.value =
    selectedEvent.value.eventCategoryID.eventCategoryName;
  const localDate = new Date(selectedEvent.value.eventStartTime).toLocaleString(
    "th-TH",
    {
      weekday: "short",
      month: "short",
      day: "numeric",
      year: "2-digit",
      hour: "numeric",
      minute: "numeric",
    }
  );
  selectedEvent.value.eventStartTime = localDate;
};

onBeforeMount(async () => {
  await getDetailById();
  console.log(selectedEvent.value);
});

window.onbeforeunload = function () {
  return "Dude, are you sure you want to leave? Think of the kittens!";
};
</script>

<template>
  <div>
    <div class="hero min-h-screen bg-base-200">
      <div class="hero-content text-center">
        <div
          class="card w-[1500px] h-full bg-gradient-to-r from-base-100 to-base-200 m-4 shadow-xl backdrop-blur-sm"
        >
          <div class="card-body text-3xl">
            <p
              class="text-5xl font-bold text-transparent bg-clip-text bg-gradient-to-r from-purple-300 to-pink-600 m-4"
            >
              {{ getEventCategoryName }}
            </p>
            <div class="divider"></div>

            <p>Name: {{ selectedEvent.bookingName }}</p>
            <br />
            <p>Email: {{ selectedEvent.bookingEmail }}</p>
            <br />
            <p>Duration: {{ selectedEvent.eventDuration }} min.</p>
            <br />
            <p v-if="selectedEvent.eventNotes != ''">
              Notes: {{ selectedEvent.eventNotes }}
            </p>
            <p v-else>Notes: no message.</p>
            <br />
            <p>Date: {{ selectedEvent.eventStartTime }}</p>
            <div class="card-actions justify-end">
              <router-link :to="{ name: 'Home' }"
                ><button class="btn btn-primary">Go back</button></router-link
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
