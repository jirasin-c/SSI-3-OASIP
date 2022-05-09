<script setup></script>

<template>
  <div class="fixed w-screen">
    <div class="navbar bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500 text-primary-content">
      <router-link :to="{ name: 'Home' }" class="btn btn-ghost normal-case text-xl">
        <div class="avatar pr-2">
          <div class="w-[40px] rounded-full">
            <img src="../../img/logo.png" />
          </div>
        </div>TRIMITR GROUP
      </router-link>
      <router-link :to="{ name: 'Booking' }" class="btn btn-ghost normal-case text-lg">Booking</router-link>
      <!-- <a class="btn btn-ghost normal-case text-xl">TRIMITR GROUP</a> -->
    </div>
    <router-view></router-view>
  </div>
</template>

<style>
</style>
