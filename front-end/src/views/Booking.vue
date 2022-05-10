<script setup>
import { computed } from '@vue/reactivity';
import { ref, onBeforeMount, onUpdated } from 'vue';
import { useRouter } from 'vue-router';
import MdiTrashCan from '../components/MdiTrashCan.vue';
const eventCategory = ref([])
const selectedCategory = ref('Project Management Clinic')
const startTime = ref(null)
const name = ref('')
const categoryID = ref()
const falseInput = ref(false)
const email = ref('')
const notes = ref('')
const duration = ref()
const appRouter = useRouter()

onUpdated(() => {
    eventCategory.value.filter((findID) => {
        if (findID.eventCategoryName === selectedCategory.value) {
            categoryID.value = findID.id
            duration.value = findID.eventDuration
        }
    });
})
const createEvent = async () => {
    const utc = new Date(startTime.value).toISOString()
    startTime.value = utc
    if (name.value == '' || email.value == '' || startTime.value == null) {
        falseInput.value = true
    } else {
        const res = await fetch(`${import.meta.env.BASE_URL}api/events`, {
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
            console.log('created');
            appRouter.push({ name: 'Home' })
        } else {
            console.log('can not create');
        }
    }
}
const getEventCategory = async () => {
    const res = await fetch(`${import.meta.env.BASE_URL}api/event-category`)
    eventCategory.value = await res.json()
    console.log(eventCategory.value);
}
onBeforeMount(async () => {
    await getEventCategory()
})


</script>
 
<template>
    <div>
        <div class="hero min-h-screen bg-base-200">
            <div class="hero-content text-left ">
                <div
                    class="card w-[1000px] bg-gradient-to-r from-base-100 to-base-200 mb-16 shadow-xl backdrop-blur-sm">
                    <div class="card-body text-3xl place-self-center">
                        <p
                            class="text-5xl font-bold text-transparent bg-clip-text bg-gradient-to-r from-purple-300 to-pink-600 pb-2 text-center">
                            Booking Event
                        </p>
                        <div class="divider"></div>
                        <div class="flex justify-center pb-5">
                            <div class="form-control w-full max-w-xs">
                                <label for="category" class="label">
                                    <span class="label-text text-base font-semibold">
                                        Category: <span class="text-red-500">*</span>
                                    </span>
                                </label>
                                <select class="select select-secondary w-full max-w-xs  text-lg" id="category"
                                    v-model="selectedCategory">
                                    <option v-for="category in eventCategory " :key="category">
                                        {{ category.eventCategoryName }}</option>
                                </select>
                                <label for="duration" class="label">
                                    <span class="label-text text-base font-semibold">
                                        Duration:
                                    </span>
                                </label>
                                <input type="text" :placeholder="duration"
                                    class="input input-bordered input-secondary w-full max-w-xs " disabled
                                    id="duration" />
                                <label for="starttime" class="label">
                                    <span class="label-text text-base font-semibold">
                                        Start time: <span class="text-red-500">*</span>
                                    </span>
                                </label>
                                <input type="datetime-local" placeholder="Type here"
                                    class="input input-bordered input-secondary w-full max-w-xs text-lg"
                                    v-model="startTime" id="starttime" />
                                <label for="name" class="label">
                                    <span class="label-text text-base font-semibold">
                                        Name : <span class="text-red-500">*</span>
                                    </span>
                                </label>
                                <input type="text" placeholder="Type yourname..."
                                    class="input input-bordered input-secondary w-full max-w-xs text-lg" v-model="name"
                                    id="name" />
                                <label for="email" class="label">
                                    <span class="label-text text-base font-semibold">
                                        Email : <span class="text-red-500">*</span>
                                    </span>
                                </label>
                                <input type="email" placeholder="example@mail.kmutt.ac.th"
                                    class="input input-bordered input-secondary w-full max-w-xs  text-lg"
                                    v-model="email" id="email" />
                                <label for="notes" class="label">
                                    <span class="label-text text-base font-semibold">
                                        Notes :
                                    </span>
                                </label>
                                <textarea id="notes" cols="30" rows="3" v-model="notes"
                                    class="textarea textarea-secondary  text-lg w-full overflow-auto"
                                    placeholder="Type something..."></textarea>
                            </div>
                        </div>
                        <div class="alert alert-error shadow-lg w-72 text-[16px] self-center" v-show="falseInput">
                            <div>
                                <svg xmlns="http://www.w3.org/2000/svg" class="stroke-current flex-shrink-0 h-6 w-6"
                                    fill="none" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                        d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z" />
                                </svg>
                                <span>Please fill the empty form</span>
                            </div>
                        </div>
                        <div class="card-actions justify-center">
                            <button @click="createEvent"
                                class="btn btn-secondary border-none bg-gradient-to-r from-yellow-500 to-orange-600 hover:from-pink-500 hover:to-yellow-500 mb-4 text-lg">Book
                                event</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>
 
<style scoped>
::-webkit-calendar-picker-indicator {
    filter: invert(1);
}
</style>