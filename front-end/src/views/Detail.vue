<script setup>
import { useRoute } from "vue-router";
import { ref, onBeforeMount } from "vue";
import IcPerson from "../icons/IcBaselinePerson.vue";
import IcEmail from "../icons/IcBaselineEmail.vue";
import IcTimer from "../icons/IcBaselineTimer.vue";
import IcCalendar from "../icons/BiCalendarDateFill.vue";
import IcNote from "../icons/PhNoteFill.vue";
const selectedEvent = ref([]);
const getEventCategoryName = ref("");
let { params } = useRoute();
const bookingId = params.bookingId;

const getDetailById = async () => {
  const res = await fetch(`/api/events/${bookingId}`);
  selectedEvent.value = await res.json();
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
});

window.onbeforeunload = function () {
  return true;
};

</script>

<template>
  <div>
    <div class="hero min-h-screen bg-base-200">
      <div class="hero-content text-left">
        <div class="card w-[1500px] h-full bg-gradient-to-r from-base-100 to-base-200 shadow-xl backdrop-blur-sm">
          <div class="card-body text-3xl place-self-center">
            <p
              class="text-5xl font-bold text-transparent bg-clip-text bg-gradient-to-r from-purple-300 to-pink-600 m-4 pb-1 text-center ">
              {{ getEventCategoryName }}
            </p>
            <div class="divider"></div>

            <p>
              <IcPerson class="inline-block mr-5" /> Name: {{ selectedEvent.bookingName }}
            </p>
            <br />
            <p>
              <IcEmail class="inline-block mr-5 " />
              Email: {{ selectedEvent.bookingEmail }}
            </p>
            <br />
            <p>
              <IcTimer class="inline-block mr-5" />
              Duration: {{ selectedEvent.eventDuration }} mins
            </p>
            <br />
            <p v-if="selectedEvent.eventNotes != ''">

              <IcNote class="inline-block mr-5" />
              Notes: {{ selectedEvent.eventNotes }}
            </p>
            <p v-else>
              <IcNote class="inline-block mr-5" />
              Notes: NO MESSAGE
            </p>
            <br />
            <p>
              <IcCalendar class="inline-block mr-5" />
              Date: {{ selectedEvent.eventStartTime }}
            </p>
          </div>
          <div class="card-actions justify-end m-5">
            <router-link :to="{ name: 'Home' }"><button
                class="btn btn-secondary border-none bg-gradient-to-r from-yellow-500 to-orange-600 hover:from-pink-500 hover:to-yellow-500">Go
                back</button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
</style>
