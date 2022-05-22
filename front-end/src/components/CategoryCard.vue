<script setup>
const prop = defineProps({
    allCategory:{
        type: Array,
        require: true
    }
})
</script>
 
<template>
<div>
    <div v-for="category in prop.allCategory">
        <!-- {{category}} -->
        <div
          class="hover:bg-slate-500 hover:text-white grid break-inside-avoid rounded-xl p-4 mb-8 w-full break-words drop-shadow-md text-ellipsis overflow-hidden bg-gray-200 text-slate-500">
          <!-- <div class="badge badge badge-accent badge-outline mb-1">
            {{ category.eventCategoryName }}
          </div> -->
          <div class="flex flex-row">
            <div>
              <span><b>{{category.eventCategoryName}}</b><br />
                </span>
              Duration: {{ category.eventDuration }} mins.<br />

              <br />
              
              {{ category.eventCategoryDescription }}<br />

            </div>
          </div>
          <!-- <div class="justify-self-end flex bg-slate-100 hover:bg-slate-600 rounded-full  w-9 h-9 lg:w-auto lg:h-14">
            <button
              @click="$emit('deleteEvent', { deleteId: booking.id, deleteName: booking.bookingName, event: $event })">
              <RiDeleteBin6Line class=" mx-2 lg:mx-3" />
            </button>
          </div> -->
        </div>
    </div>
</div>
</template>
 
<style>

</style>