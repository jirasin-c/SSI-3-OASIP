<script setup>
import { ref, onBeforeMount } from 'vue';
import MdiTrashCan from '../components/MdiTrashCan.vue';
const eventCategory = ref([])
const selectedCategory = ref('Project Management Clinic')
const startTime = ref('')
const name = ref('')
const categoryID = ref()
// const firstName = ref('')
// const lastName = ref('')
const email = ref('')
const notes = ref('')
const duration = ref()
const dolog = async () => {
    // console.log(date.value);
    const utc = new Date(startTime.value).toISOString()
    startTime.value = utc
    // console.log(utc);
    // console.log(selectedCategory.value);

    eventCategory.value.filter((findID) => {
        // console.log(findID);
        // console.log(findID.eventCategoryName);
        // console.log(findID.eventCategoryName === selectedCategory.value);
        // console.log("current:" + selectedCategory.value);

        if (findID.eventCategoryName === selectedCategory.value) {
            // console.log(findID.id);
            // console.log(findID.eventDuration);
            categoryID.value = findID.id
            duration.value = findID.eventDuration
            // console.log(duration.value);
        }
    });

    // console.log("categoryID: " + categoryID.value, "category: " + selectedCategory.value, "start time: " + startTime.value, "name: " + name.value, "email: " + email.value, "notes: " + notes.value, "duration:" + duration.value + "mins");

    await createEvent()
}

const createEvent = async () => {

    const res = await fetch('/api/events', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            bookingName: name.value,
            bookingEmail: email.value,
            eventStartTime: startTime.value,
            eventDuration: duration.value,
            eventNotes: notes.value,
            eventCategoryID: {
                id: categoryID.value,
            }
        })
    })
    if (res.status === 201) {
        //     const addStory = await res.json()
        //     stories.value.push(addStory)
        //     console.log('added');
        console.log('created');
    } else {
        console.log('can not create');
    }
    // inputStory.value = ''
    // inputTitle.value = ''
    // console.log(inputStory.value, inputTitle.value);
}
const getEventCategory = async () => {
    const res = await fetch("/api/event-category")
    eventCategory.value = await res.json()
    console.log(eventCategory.value);
}
onBeforeMount(async () => {
    await getEventCategory()
})


</script>
 
<template>
    <div>
        <div class="hero min-h-screen bg-base-200" style="fo">
            <div class="hero-content text-center">
                <div
                    class="card w-[1500px] h-full bg-gradient-to-r from-base-100 to-base-200 m-4 shadow-xl backdrop-blur-sm">
                    <div class="card-body text-3xl">
                        <p
                            class="text-5xl font-bold text-transparent bg-clip-text bg-gradient-to-r from-purple-300 to-pink-600 m-4 pb-4">
                            Booking Event
                        </p>
                        <div class="divider"></div>
                        <div>
                            <label for="">Category</label>
                            <!-- <label class="input-group col-span-3 md:w-11/12 md:mr-6"> -->
                            <select name="" id="" v-model="selectedCategory">
                                <option v-for="category in eventCategory" :key="category">
                                    {{ category.eventCategoryName }}</option>
                            </select>
                            <!-- </label> -->
                        </div>
                        <div>
                            <label for="">Start time</label>
                            <input type="datetime-local" v-model="startTime">
                        </div>
                        <div>
                            <label for="">Name</label>
                            <input type="text" v-model="name" placeholder="Type yourname...">
                        </div>
                        <!-- <div>
                            <label for="">Last Name</label>
                            <input type="text" v-model="lastName">
                        </div> -->
                        <div>
                            <label for="">Email</label>
                            <input type="email" v-model="email" placeholder="example@mail.kmutt.ac.th">
                        </div>
                        <div>
                            <label for="">Notes</label>
                            <textarea name="" id="" cols="30" rows="4" v-model="notes"
                                placeholder="Type something..."></textarea>
                        </div>
                        <div class="card-actions justify-center">

                            <button @click="dolog"
                                class="btn btn-secondary border-none bg-gradient-to-r from-yellow-500 to-orange-600">Book
                                event</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>
 
<style>
</style>