import axios from 'axios'

const API_KEY = 'RGAPI-80064fff-b604-4ae4-9f70-1a9065490f36'

export default {
  find_id (name) {
    return axios.get(`/lol/summoner/v4/summoners/by-name/${name}?api_key=${API_KEY}`)
  },

  find_league (id) {
    return axios.get(`/lol/league/v4/entries/by-summoner/${id}?api_key=${API_KEY}`)
  },
  find_match (accountId) {
    return axios.get(`/lol/match/v4/matchlists/by-account/${accountId}?api_key=${API_KEY}`, {params: {beginIndex: 0, endIndex: 20}})
  },
  find_detail_match (matchId) {
    return axios.get(`/lol/match/v4/matches/${matchId}?api_key=${API_KEY}`)
  }
}
