<template>
  <div class="container">
    <header class="jumbotron">
      <h3>username : {{content.username}}</h3>
      <h3>email : {{content.email}}</h3>
    </header>
  </div>
</template>

<script>
import AuthService from '../API/auth_service'

export default {
  name: 'Profile',
  data () {
    return {
      content: ''
    }
  },
  mounted () {
    console.log('마운트')
    AuthService.getUserContent()
      .then(response => {
        this.content = response.data
      }, error => {
        this.content = error.response.data.message
      })
  }
}
</script>

<style scoped>

</style>
