<template>
  <div>
  <div>
    <input type="text" v-model="name">
    <button @click="find_id">검색</button>
  </div>
  <div>{{name}}</div>
    <div>{{message}}</div>
  <div>
    <h3>솔로랭크</h3>
    <div>{{solo.tier + solo.rank}}</div>
    <div>{{solo.wins}}승</div>
    <div>{{solo.losses}}패</div>
  </div>

  <div>
    <h3>자유랭크</h3>
    <div>{{free.tier + free.rank}}</div>
    <div>{{free.wins}}승</div>
    <div>{{free.losses}}패</div>
  </div>

  <div>
    <h3>디테일 10개</h3>

  </div>

  <div>
    <h3>최근 10게임</h3>
    <div v-for="(i,index) in match" :key="index">
      <div>챔피언: {{i.champion}}</div>
      <div >게임id: {{find_match_detail(i.gameId)}}</div>
      <div>라인: {{i.lane}}</div>
      <div>큐: {{i.queue}}</div>
      <div>역할: {{i.role}}</div>
      <div>시즌: {{i.season}}</div>
      <div>시간: {{$moment(i.timestamp).format('YYYY-MM-DD HH:mm:SS')}}</div>
      <div>--------------------------------</div>
    </div>
  </div>
  </div>
</template>

<script>
import lolAPI from '../API/lolAPI'
export default {
  name: 'Home',
  data () {
    return {
      name: '',
      message: '',
      data: '',
      solo: [],
      free: [],
      match: [],
      detail_match: []

    }
  },
  methods: {
    find_id () {
      lolAPI.find_id(this.name).then(response => {
        this.data = response.data.id
        let accountId = response.data.accountId

        lolAPI.find_match(accountId).then(response => {
          this.match = response.data.matches
        })

        lolAPI.find_league(this.data).then(response => {
          if (response.data[0].queueType === 'RANKED_FLEX_SR') {
            this.free = response.data[0]
            if (response.data.length === 1) {
              this.solo = ''
            }
          }
          if (response.data[0].queueType === 'RANKED_SOLO_5x5') {
            this.solo = response.data[0]
            if (response.data.length === 1) {
              this.free = ''
            }
          }
          if (response.data[1].queueType === 'RANKED_FLEX_SR') {
            this.free = response.data[1]
          }
          if (response.data[1].queueType === 'RANKED_SOLO_5x5') {
            this.solo = response.data[1]
          }
        })
      }).catch(error => {
        if (error.response) {
          console.log(error.response)
          this.message = '찾을 수 없는 사용자입니다.'
        }
      })
    },
    find_match_detail (matchId) {
      lolAPI.find_detail_match(matchId).then(response => {
        this.detail_match = response.data
      })
    }
  }
}
</script>

<style scoped>

</style>
