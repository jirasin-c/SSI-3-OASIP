<script setup></script>

<template>
  <div class="w-screen">
    <div class="navbar bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500 text-primary-content">
      <div class="hidden md:block md:w-auto">
        <router-link :to="{ name: 'Home' }" class="btn btn-ghost normal-case text-xl">
        <div class="avatar pr-2">
          <div class="w-[40px] rounded-full">
            <img src="../assets/trimitr.png" />
          </div>
        </div>TRIMITR GROUP
      </router-link>
      </div>
      <div class="md:hidden">
        <router-link :to="{ name: 'Home' }" class="btn btn-ghost normal-case text-sm mb-2">
          <div class="w-[40px] rounded-full">
            <img src="../assets/trimitr.png" />
            trimitr
          </div>
      </router-link>
      </div>
      <div class="ml-5">

        <router-link :to="{ name: 'Booking' }" class="btn btn-ghost normal-case text-lg">BOOKS</router-link>
      <router-link :to="{ name: 'EventCategory' }" class="btn btn-ghost normal-case text-lg">CATEGORIES</router-link>
      <!-- <a class="btn btn-ghost normal-case text-xl">TRIMITR GROUP</a> -->
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<style>
</style>
