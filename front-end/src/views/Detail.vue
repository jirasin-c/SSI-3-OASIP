<script setup>
import { useRoute } from "vue-router";
import { ref, onBeforeMount , onUpdated} from "vue";
import IcPerson from "../icons/IcBaselinePerson.vue";
import IcEmail from "../icons/IcBaselineEmail.vue";
import IcTimer from "../icons/IcBaselineTimer.vue";
import IcCalendar from "../icons/BiCalendarDateFill.vue";
import IcNote from "../icons/PhNoteFill.vue";
const selectedEvent = ref([]);
const events = ref([])
const eventCategory = ref([])
const getEventCategoryName = ref("");
let { params } = useRoute();
const bookingId = params.bookingId;
const editDate = ref(null)
const beforeEditDate = ref(null)
const editNote = ref(null)
const beforeEditNote = ref(null)
const isEdit = ref(false)
const currentTime = ref(null)
const isPast =ref(false)
const isOverlapped = ref(false)
const duration = ref()

const compareDate = (editDate,currentTime)=>{
    if (editDate>currentTime) {
        isPast.value = false
        return false
    }else if(editDate<currentTime){
        isPast.value = true
        return true
    }else{
        isPast.value = false
        return false
    }

}

onUpdated(() => {
    currentTime.value = new Date().getFullYear()+'-'+('0'+(new Date().getMonth()+1)).slice(-2)+"-"+new Date().getDate()+'T'+('0'+new Date().getHours()).slice(-2)+':'+('0'+new Date().getMinutes()).slice(-2)
    eventCategory.value.filter((findID) => {
        if (findID.eventCategoryName === getEventCategoryName.value) {
            duration.value = findID.eventDuration
        }
    });
})
const getEvents = async() =>{
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/events`)
  events.value = await res.json()
}
const getEventCategory = async () => {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/event-category`)
    eventCategory.value = await res.json()
    console.log(eventCategory.value);
}

const getDetailById = async () => {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/events/${bookingId}`);
  selectedEvent.value = await res.json();
  console.log(selectedEvent.value);
  getEventCategoryName.value =
    selectedEvent.value.eventCategoryID.eventCategoryName;
  editNote.value = selectedEvent.value.eventNotes
  beforeEditNote.value = editNote.value
  editDate.value = selectedEvent.value.eventStartTime.split('.')[0];
  beforeEditDate.value = editDate.value

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

const updateEvent = async () => {
    isOverlapped.value = false
    const compareStartTime = new Date(editDate.value).toLocaleString()
    const compareStartTimeISO = new Date(editDate.value)
    events.value.filter((findOvl)=>{
    const existingStartTime = new Date(findOvl.eventStartTime).toLocaleString()
    const existStartTimeToMillisec = new Date(findOvl.eventStartTime).getTime()
    const durationToMillisec = duration.value* 60000
    const existDuration = findOvl.eventDuration * 60000
    const existingEndTime = new Date(existStartTimeToMillisec+existDuration).toLocaleString()
    const alertExistEndTime = new Date(existStartTimeToMillisec+existDuration).getHours()+":"+('0'+new Date(existStartTimeToMillisec+existDuration).getMinutes()).slice(-2)+":"+('0'+ new Date(existStartTimeToMillisec+existDuration).getSeconds()).slice(-2)
    const startTimeToMillisec = new Date(compareStartTimeISO).getTime()
    const startTimePlusDuration = startTimeToMillisec + durationToMillisec
    const compareEndTime = new Date(startTimePlusDuration).toLocaleString()
           if ((findOvl.eventCategoryID.id === selectedEvent.value.eventCategoryID.id)) {
           if(((compareEndTime <= existingEndTime) && (compareEndTime > existingStartTime)) || ((compareStartTime >= existingStartTime) &&(compareStartTime < existingEndTime)))  {
           isOverlapped.value = true
           alert(`Sorry, the booking has Overlapped in ${existingStartTime} - ${alertExistEndTime}, Please select new date.`)
           }
       }
       })

  if (isOverlapped.value == true) {
      return
  }
  if (isPast.value == true) {
            editDate.value = editDate.value
            alert("Start time must be in the future")
            return
        }  
  if (confirm(`Are you sure to update the booking information ?`)) {
    const res = await fetch(`${import.meta.env.VITE_BASE_URL}api/events/${selectedEvent.value.id}`, {
      method: 'PUT',
      headers: {
        'content-type': 'application/json'
      },
      body: JSON.stringify({
        eventStartTime: editDate.value,
        eventNotes: editNote.value,
      })
    })
    if (res.status === 200) {
      alert("Event updated successfully")
      getDetailById()
      isEdit.value = false
    } else {
      alert("Event can't updated, please fill the require field")
      isEdit.value = true
    }
  }else{
    isEdit.value = true
  }
}
onBeforeMount(async () => {
  await getDetailById();
  await getEvents()
  await getEventCategory()
  currentTime.value = new Date().getFullYear()+'-'+('0'+(new Date().getMonth()+1)).slice(-2)+"-"+new Date().getDate()+'T'+('0'+new Date().getHours()).slice(-2)+':'+('0'+new Date().getMinutes()).slice(-2)

});

window.onbeforeunload = function () {
  return true;
};

</script>

<template>
  <div>
    <div class="hero min-h-screen bg-base-200">
      <div class="hero-content text-left">
        <div class="card w-auto lg:w-[1200px] h-full bg-gradient-to-r from-base-100 to-base-200 shadow-xl backdrop-blur-sm mb-12">
          <div class="card-body text-xl md:text-3xl place-self-center">
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
              <label for="notes">
                Notes: 
              </label>
            <p v-show="!isEdit" class="inline-block">{{ selectedEvent.eventNotes }}</p>
            <p><textarea type="text" rows="1" v-show="isEdit" id="notes" v-model="editNote"
                class="textarea textarea-secondary text-lg w-full overflow-auto "
                ></textarea></p>
            </p>
            <p v-else>
              <IcNote class="inline-block mr-5" />
              <label for="notes">
                Notes: 
              </label>
            <p v-show="!isEdit" class="inline-block">NO MESSAGE</p>
            <p><textarea type="text" rows="1" v-show="isEdit" id="notes" v-model="editNote"
                class="textarea textarea-secondary text-lg w-full overflow-auto "
                ></textarea></p>
            </p>
            <br />
            <p>
              <IcCalendar class="inline-block mr-5" />
              <label for="starttime">
                Start time  :
              </label>
            <p v-show="!isEdit" class="inline-block">{{ selectedEvent.eventStartTime }}</p>
            <!-- <span class="text-red-500 mr-2" v-show="isEdit" >*</span> -->
            <input v-show="isEdit" type="datetime-local"
              class="input input-bordered input-secondary w-auto max-w-xs text-lg" id="starttime" v-model="editDate" :min="currentTime"/>
            </p>
              <span class="text-sm text-red-500 pb-2 inline-block" v-show="compareDate(editDate,currentTime)">Start time must be in the future.</span>
          </div>
          <div class="card-actions justify-end m-5">
            <button class="btn btn-secondary border-none " @click="isEdit = !isEdit" v-show="!isEdit">Edit</button>
            <button class="btn btn-accent border-none " @click="isEdit = true, updateEvent()"
              v-show="isEdit">Apply</button>
            <button class="btn btn-secondary border-none " @click="isEdit = !isEdit, editDate = beforeEditDate, editNote = beforeEditNote" v-show="isEdit">Cancel</button>
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
::-webkit-calendar-picker-indicator {
  filter: invert(1);
}
</style>
