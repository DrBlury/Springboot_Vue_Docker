<template>
  <div class="dashboard">
    <!-- <img src="./../assets/logo.png"> -->

    <v-btn color="success" @click="callRestApiCamunda">Call REST API Camunda</v-btn>
    <v-btn color="success" @click="callRestApiTest">Call REST API Test</v-btn>

    <v-card>
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
      }
    },
    methods: {
      callRestApiCamunda() {
        Vue.axios.get(`/api/camunda`)
                .then(response => {
                  console.log(response.data);
                  // JSON responses are automatically parsed.
                  this.$store.commit('setText', response.data);
                }).catch(e => {
          this.errors.push(e)
        })
      },
      callRestApiTest() {
        Vue.axios.get(`/api/test`)
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
