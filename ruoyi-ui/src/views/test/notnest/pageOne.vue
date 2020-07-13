<template>
  <div>页面1</div>
</template>
<script>
export default {
  name: "pageOne",
  props: {
    // activeName: {
    //   type: String,
    //   required: true
    // }
  },
  data() {
    return {};
  },
  methods: {
    init() {
      this.getList();
    },
    getList() {
      console.log("pageOne getList");
    }
  }
};
</script>