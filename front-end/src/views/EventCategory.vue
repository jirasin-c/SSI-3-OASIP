<script setup>
import { onBeforeMount, ref } from 'vue';
import CategoryCard from '../components/CategoryCard.vue';

const eventCategory = ref([])

onBeforeMount(async()=>{
    await getEventCategory()
    // console.log(eventCategory.value);
})

const getEventCategory = async() =>{
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/event-categories`)
    eventCategory.value = await res.json()
}
</script>
 
<template>
<div>
    <div>
        <div
            class="mt-10 ml-16 text-3xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-purple-400 to-pink-600">
            EVENT CATEGORY
        </div>
    </div>
    <div class="flex flex-row h-screen">
        <div class="shadow-inner shadow-lg glass w-screen h-3/4 ml-16 mt-12 mr-16 rounded-2xl overflow-auto">
            <!-- <div v-show="isEmpty" class="grid justify-items-center pt-72"> -->
            <!-- <p class="text-2xl text-gray-400">List currently empty</p> -->
        <!-- </div> -->

        <!-- <div class=" columns-3 gap-6 w-[1700px] mx-auto space-y-6 pb-6 text-2xl mt-10 " id="style-1"> -->
        <div class=" w-auto text-sm lg:w-[1700px] mx-auto space-y-6 pb-6 lg:text-2xl mt-10">
          <!-- <EventCard :allBooking="event" @viewDetail="getDetail" @deleteEvent="cancelEvent"
            class="grid grid-cols-1  md:grid md:grid-cols-3 md:gap-6 " /> -->
            <CategoryCard :all-category="eventCategory" class="grid grid-cols-1  md:grid md:grid-cols-3 md:gap-6 " />
        </div>
        </div>
    </div>
</div>
</template>
 
<style>

</style>