import AuthService from '../API/auth_service'

const user = JSON.parse(localStorage.getItem('user'))
const initialState = user ? {state: {loggedIn: true}, user} : {status: {}, user: null}

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    login ({commit}, user) {
      return AuthService.login(user)
        .then(user => {
          commit('loginSuccess', user)
          return Promise.resolve(user)
        }, error => {
          commit('loginFailure')
          return Promise.reject(error.response.data)
        })
    },
    logout ({commit}) {
      AuthService.logout()
      commit('logout')
    },
    register ({commit}, user) {
      return AuthService.register(user)
        .then(response => {
          commit('registerSuccess')
          return Promise.resolve(response.data)
        }, error => {
          return Promise.reject(error.response.data)
        })
    }
  }
}
