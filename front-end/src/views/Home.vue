<script setup>
import EventCard from "../components/EventCard.vue";
import ShowSelectedEvent from "../components/ShowSelectedEvent.vue";
import { useRouter } from 'vue-router';
import { onBeforeMount, onUpdated, ref } from "vue";
const appRouter = useRouter()
const event = ref([]);
const eventCategory = ref([])
const selectCategory = ref('All category')
const isEmpty = ref(false);

const getEvent = async () => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/events`);
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

  console.log(event.value);
};

const getEventCategory = async () =>{
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/event-category`)
  eventCategory.value = await res.json()
  console.log(eventCategory.value);
}

const filterCategory = async (ev) =>{
  const categoryId = ev.target.value
  console.log(categoryId);
  if (categoryId == 'All category') {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/events`)
    event.value = await res.json()
  }else{
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/events/filterCategory/${categoryId}`)
    event.value = await res.json()
  }
  
  if (event.value.length == 0) {
    isEmpty.value = true;
  } else {
    isEmpty.value = false;
  }

}
const getDetail = (id) => {
  // console.log(id);
  appRouter.push({ name: 'Detail', params: { bookingId: id.bookingId } })
}
const cancelEvent = async (id) => {
  id.event.stopPropagation()
  if (confirm(`Are you sure to delete Event name: ${id.deleteName} ?`)) {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/events/${id.deleteId}`, {
      method: 'DELETE'
    })
    if (res.status === 200) {
      event.value = event.value.filter((e) => e.id !== id.deleteId)
    } else {
      alert("Can't deleted")
    }
  }

}
onBeforeMount(async () => {
  await getEvent();
  await getEventCategory()
  // console.log(event.value);
});

// onUpdated(()=>{
//   console.log(selectCategory.value);
// })
</script>

<template>
  <div>
    <div>
      <div
        class="mt-10 ml-16 text-3xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-purple-400 to-pink-600">
        CURRENT BOOKING</div>
        <div class="flex items-center mt-2 justify-center">
          <div class="w-2/3 shadow p-5 rounded-lg bg-slate-500 bg-opacity-40">
            <div
        class="text-2xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-blue-400 to-blue-600">
        Filter</div>
            <div class="grid grid-cols-3 justify-item-center ">
              <select class="select select-bordered w-2/4 max-w-xs" name="" id="" v-model="selectCategory" @change="filterCategory">
                <option selected>All category</option>
                <option v-for="category in eventCategory" :key="category.id" :value="category.id">{{category.eventCategoryName}}</option>
                <!-- <option value="" selected>All category</option>
                <option value="" >Client-side</option>
                <option value="" >All category</option> -->
              </select>
            </div>
          </div>
        </div>
    </div>
    <div class="flex flex-row h-screen">
      <div class="shadow-inner shadow-lg glass w-screen h-3/4 ml-16 mt-12 mr-16 rounded-2xl overflow-auto">
        <div v-show="isEmpty" class="grid justify-items-center pt-72">
          <p class="text-2xl text-gray-400">List currently empty</p>
        </div>

        <!-- <div class=" columns-3 gap-6 w-[1700px] mx-auto space-y-6 pb-6 text-2xl mt-10 " id="style-1"> -->
        <div class=" w-auto text-sm lg:w-[1700px] mx-auto space-y-6 pb-6 lg:text-2xl mt-10">
          <EventCard :allBooking="event" @viewDetail="getDetail" @deleteEvent="cancelEvent"
            class="grid grid-cols-1  md:grid md:grid-cols-3 md:gap-6 " />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
::-webkit-scrollbar {
  width: 16px;

}

::-webkit-scrollbar-track {
  background-color: #e4e4e4;
  border-radius: 100px;
}

::-webkit-scrollbar-thumb {
  background-image: linear-gradient(180deg, #D0368A 0%, #708AD4 99%);
  box-shadow: inset 2px 2px 5px 0 rgba(#fff, 0.5);
  border-radius: 100px;
  border: 2px solid transparent;
  background-clip: content-box;
}
</style>
