import axios from 'axios'

let baseURL
// Voir https://juliensalinas.com/fr/connecter-SPA-frontend-vuejs-axios-API-backend/
if (!process.env.NODE_ENV || process.env.NODE_ENV === 'development') {
  baseURL = 'http://127.0.0.1/'
} else {
  baseURL = 'http://api.example.com'
}

export const HTTP = axios.create(
  {
    baseURL: baseURL
  })

  /*
   * Dans chaque fichier vue nécessitant Axios, importez HTTP au lieu d’Axios :

    import {HTTP} from '../http-constants'

    HTTP.get(...).then(...).catch(...)
  */ 