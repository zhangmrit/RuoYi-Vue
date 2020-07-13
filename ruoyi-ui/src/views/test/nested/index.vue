<template>
  <el-card
    :body-style="{ padding: '0px'}"
    style="margin:10px;border-radius: 10px;min-height:830px;"
  >
    <div class="custom-route">
      <div class="custom-route-top">
        <el-menu router :default-active="activeIndex" :mode="mode" @select="handleSelect">
          <el-menu-item index="/test/nested/nested1">路由一</el-menu-item>
          <el-menu-item index="/test/nested/nested2">路由二</el-menu-item>
        </el-menu>
      </div>
      <div class="custom-route-bottom">
        <div class="custom-route-title">
          <span>{{ $route.meta.title }}</span>
        </div>
        <router-view />
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: "nested",
  data() {
    return {
      // horizontal  inline
      mode: "horizontal",
      activeIndex: "",
      selectedKeys: []
    };
  },
  mounted() {
    this.updateMenu();
  },
  created() {
    console.log("00000000000");
  },
  methods: {
    handleSelect(key, keyPath) {
      // this.activeIndex = key;
    },
    updateMenu() {
      const routes = this.$route.matched.concat();
      this.selectedKeys = [routes.pop().path];
      this.activeIndex = this.selectedKeys[0];
      // console.log("selectedKeys", this.selectedKeys);
    }
  },
  watch: {
    $route(val) {
      this.updateMenu();
    }
  }
};
</script>

<style lang="scss" scoped>
.custom-route {
  width: 100%;
  height: 100%;
  overflow: auto;

  .custom-route-top {
    width: 100%;
    padding: 0px 20px;
  }

  .custom-route-bottom {
    flex: 1 1;
    padding: 8px 40px;

    .custom-route-title {
      color: rgba(0, 0, 0, 0.85);
      font-size: 20px;
      font-weight: 500;
      line-height: 28px;
      margin-bottom: 12px;
    }
  }
}
</style>