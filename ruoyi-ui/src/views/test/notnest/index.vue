<template>
  <el-card
    :body-style="{ padding: '0px'}"
    style="margin:10px;border-radius: 10px;min-height:830px;"
  >
    <div class="custom-route">
      <div class="custom-route-top">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="用户管理" name="first">
            <page-one ref="first" />
          </el-tab-pane>
          <el-tab-pane label="配置管理" name="second">
            <page-two ref="second" />
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </el-card>
</template>

<script>
import pageOne from "./pageOne";
import pageTwo from "./pageTwo";
export default {
  name: "notnest",
  components: {
    pageOne,
    pageTwo
  },
  data() {
    return {
      activeName: "first"
    };
  },
  mounted() {
    let activeName = this.$route.params.activeName;
    if (activeName) this.activeName = activeName;
    this.childInit();
  },
  created() {
    console.log("ssssssssss");
  },
  methods: {
    childInit() {
      this.$refs[this.activeName].init();
    },
    handleClick(tab, event) {
      this.childInit();
    }
  },
  watch: {}
};
</script>

<style lang="scss" scoped>
.custom-route {
  width: 100%;
  height: 100%;
  overflow: auto;

  .custom-route-top {
    width: 100%;
    padding: 10px 20px;
  }
}
</style>