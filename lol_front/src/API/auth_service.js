import axios from 'axios'
import authHeader from './auth_header'

const API_URL = 'http://localhost:8080/api/auth'

class AuthService {
  login (user) {
    return axios.post(API_URL + '/signin', {email: user.username, password: user.password})
      .then(response => {
        if (response.data.token) {
          localStorage.setItem('user', JSON.stringify(response.data))
        }
        return response.data
      })
  }
  logout () {
    localStorage.removeItem('user')
  }
  register (user) {
    return axios.post(API_URL + '/signup', {username: user.username, email: user.email, password: user.password})
  }
  handleResponse (response) {
    if (response.status === 401) {
      this.logout()
      // 강제 새로고침
      location.reload()
      const error = response.data && response.data.message
      return Promise.resolve(error)
    }
  }
  getUserContent () {
    return axios.get(API_URL + '/user', {headers: authHeader()})
  }
}
export default new AuthService()
