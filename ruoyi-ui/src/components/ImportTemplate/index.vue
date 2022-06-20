<template>
  <!-- 导入对话框 -->
  <el-dialog
    :title="module + '导入'"
    :visible.sync="open"
    width="400px"
    append-to-body
  >
    <el-upload
      ref="upload"
      :limit="1"
      accept=".xlsx, .xls"
      action=""
      :disabled="isUploading"
      :on-progress="handleFileUploadProgress"
      :on-success="handleFileSuccess"
      :auto-upload="false"
      :http-request="handleUpload"
      drag
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip text-center" slot="tip">
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="updateSupport" />
          是否更新已经存在的数据
        </div>
        <span>仅允许导入xls、xlsx格式文件。</span>
        <el-link
          type="primary"
          :underline="false"
          style="font-size: 12px; vertical-align: baseline"
          @click="importTemplate"
          v-if="templateUrl"
          >下载模板</el-link
        >
      </div>
    </el-upload>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitFileForm">确 定</el-button>
      <el-button @click="open = false">取 消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import request from "@/utils/request";
export default {
  props: {
    // 上传地址
    importUrl: {
      type: String,
      required: true,
    },
    //模板地址
    templateUrl: {
      type: String,
      default: "",
    },
    // 模块名称
    module: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      // 是否显示弹出层
      open: false,
      // 弹出层标题
      isUploading: false,
      // 是否更新已经存在的用户数据
      updateSupport: 0,
    };
  },
  methods: {
    handleShow() {
      this.open = true;
    },
    handleUpload(option) {
      const file = option.file;
      let formData = new FormData();
      formData.append("file", file);
      formData.append("updateSupport", this.updateSupport);
      const config = {
        headers: { "Content-Type": "multipart/form-data" },
      };
      return request.post(this.importUrl, formData, config);
    },
    /** 导入按钮操作 */
    handleImport() {
      this.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download(
        this.templateUrl,
        {},
        `${this.module}模板_${new Date().getTime()}.xlsx`
      );
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.open = false;
      this.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.$emit("ok");
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
  },
};
</script>
