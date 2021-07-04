<template>

  <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
    <el-tab-pane
      v-for="(item, index) in editableTabs"
      :key="item.name"
      :label="item.title"
      :name="item.name">
    </el-tab-pane>
  </el-tabs>

</template>

<script>
export default {
name: "Tabs",
  data() {
    return {
      editableTabsValue: 'index',
      editableTabs: [{
        title: '首页1',
        name: 'index',
      }, {
        title: '用户管理',
        name: 'sysuser',
      }],
      tabIndex: 2
    }
  },
  methods: {
    addTab(targetName) {
      let newTabName = ++this.tabIndex + '';
      this.editableTabs.push({
        title: 'New Tab',
        name: newTabName,
      });
      this.editableTabsValue = newTabName;
    },
    removeTab(targetName) {
      let tabs = this.editableTabs;
      let activeName = this.editableTabsValue;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
      }

      this.editableTabsValue = activeName;
      this.editableTabs = tabs.filter(tab => tab.name !== targetName);
    }
  }
}
</script>

<style scoped>

</style>
