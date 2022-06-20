# 导入组件
使用方法
1. 导入包并注册组件
```js
import ImportTemplate from "@/components/ImportTemplate";
components: {  ImportTemplate }

//或者在main.js全局注册
import ImportTemplate from "@/components/ImportTemplate";
Vue.component('ImportTemplate', ImportTemplate)
```
2. 调用
```vue
<import-template
      @ok="getList"
      ref="importTemp"
      module="用户"
      importUrl="/system/user/importData"
      templateUrl="system/user/importTemplate"
    />
```
参数说明

| 参数        | 释义     | 是否必填 |
| ----------- | -------- | -------- |
| @ok         | 回调函数 | 否       |
| module      | 模块名称 | 否       |
| importUrl   | 上传路径 | 是       |
| templateUrl | 模板路径 | 否       |

3. 触发

   通过`$refs`调用
```js
this.$refs.importTemp.handleShow();
```