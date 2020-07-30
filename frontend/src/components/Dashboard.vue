<template>
  <div class="dashboard">
    <!-- <img src="./../assets/logo.png"> -->
    <v-card>
      <v-container>
        <v-row>
          <v-col cols="6" sm="6" md="6">
            <v-text-field v-model="username"
                label="Username"
            ></v-text-field>
          </v-col>
          <v-col cols="6" sm="6" md="6">
            <v-text-field v-model="password"
                label="Password"
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="3" sm="3" md="6">
            <v-text-field v-model="firstName"
                label="FirstName"
            ></v-text-field>
          </v-col>
          <v-col cols="3" sm="3" md="6">
            <v-text-field v-model="lastName"
                label="LastName"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-switch v-model="admin" label="Admin?"></v-switch>
          <v-switch v-model="moderator" label="Moderator?"></v-switch>
        </v-row>

      </v-container>

    </v-card>


    <br>

    <v-card>
      <br>
      <v-btn color="blue" @click="this.create">Register</v-btn>
      <v-btn color="success" @click="this.login">Login</v-btn>
      <v-btn color="success" @click="this.trystuff">Try</v-btn>
      <v-card-text><b>{{ this.responseText }}</b></v-card-text>
    </v-card>
  </div>
</template>

<script>
  import Vue from 'vue'
  export default {
    data() {
      return {
        errors: [],
        username: "",
        password: "",
        firstName: "",
        lastName: "",
        JWTToken: "",
        admin: false,
        moderator: false,
      }
    },
    methods: {
      create() {
        Vue.axios.get(`/register/create?username=` + this.username + `&password=` + this.password)
                .then(response => {
                  console.log(response.data);
                  // JSON responses are automatically parsed.
                  this.$store.commit('setText', response.data);
                }).catch(e => {
          this.errors.push(e)
        })
      },
      login() {
        console.log(this.username)
        Vue.axios.get(`/register/login?username=` + this.username + `&password=` + this.password)
            .then(response => {
              this.$store.commit('setText', response.data);
              this.$store.commit('setJwttoken', response.headers['jwttoken']);

            }).catch(e => {
          this.errors.push(e)
        })
      },
      trystuff() {
        Vue.axios.get(`/register/try`)
            .then(response => {
              console.log(response.data);
              // JSON responses are automatically parsed.
              this.$store.commit('setText', response.data);

            }).catch(e => {
          this.errors.push(e)
        })
      },
      event () {
        this.$eventHub.$emit('change');
      },
    },

    beforeMount() {

    },
    computed: {
      responseText () {
        return this.$store.state.text
      }
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
