<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
          <router-link class="navbar-brand" :to="{ name: 'home' }">新苗迎新</router-link>
          <div class="collapse navbar-collapse" id="navbarText">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                      <router-link :class="route_name == 'map_index' ? ' nav-link active' : 'nav-link'"
                          :to="{ name: 'map_index' }">地图</router-link>
                  </li>
                  <li class="nav-item">
                      <router-link :class="route_name == 'person_index' ? ' nav-link active' : 'nav-link'"
                          :to="{ name: 'person_index' }">人才</router-link>
                  </li>
                  <li class="nav-item">
                      <router-link :class="route_name == 'discuss_index' ? ' nav-link active' : 'nav-link'"
                          :to="{ name: 'discuss_index' }">新生问答</router-link>
                  </li>
                  <li class="nav-item">
                      <router-link :class="route_name == 'task_index' ? ' nav-link active' : 'nav-link'"
                          :to="{ name: 'task_index' }">任务</router-link>
                  </li>
              </ul>
              <ul class="navbar-nav" v-if="$store.state.user.is_login">
                  <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                          aria-expanded="false">
                          {{ $store.state.user.username }}
                      </a>
                      <ul class="dropdown-menu" style="margin:0">
                          <li>
                              <router-link class="dropdown-item" :to="{ name: 'user_account_index' }">用户中心</router-link>
                          </li>
                          <li>
                              <hr class="dropdown-divider">
                          </li>
                          <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
                      </ul>
                  </li>
              </ul>
              <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
                  <li class="nav-item">
                      <router-link class="nav-link" :to="{ name: 'user_account_login' }" role="button">
                          登录
                      </router-link>
                  </li>
                  <li class="nav-item">
                      <router-link class="nav-link" :to="{ name: 'user_account_register' }" role="button">
                          注册
                      </router-link>
                  </li>
              </ul>
          </div>
      </div>
  </nav>
</template>

<script>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

export default {
  setup() {
      const store = useStore();
      const route = useRoute();
      let route_name = computed(() => route.name)

      const logout = () => {
          store.dispatch("logout");
      }

      return {
          route_name,
          logout
      }
  }
}
</script>

<style scoped></style>