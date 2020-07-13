<template>
  <div>
    页面1
    <router-link :to="{name:'notnest',params:{activeName:'second'}}">跳转非嵌套tab2</router-link>
  </div>
</template>
<script>
export default {
  name: "nested1",
  created() {
    console.log("1111111");
  }
};
</script>