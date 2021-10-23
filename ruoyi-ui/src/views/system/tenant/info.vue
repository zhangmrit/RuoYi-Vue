<template>
  <div class="app-container">
    <!-- 添加或修改租户对话框 -->
    <el-row :gutter="20" type="flex" justify="center">
      <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>公司信息</span>
          </div>
          <el-form ref="form" :model="form" :rules="rules" label-width="100px">
            <el-form-item label="租户ID" prop="tenantId">
              <el-input v-model="form.tenantId" disabled />
            </el-form-item>
            <el-form-item label="租户名称" prop="tenantName">
              <el-input
                v-model="form.tenantName"
                placeholder="请输入租户名称"
              />
            </el-form-item>
            <el-form-item label="域名地址" prop="domain">
              <el-input v-model="form.domain" placeholder="请输入域名地址" />
            </el-form-item>
            <!-- <el-form-item label="系统背景" prop="backgroundUrl">
          <el-input v-model="form.backgroundUrl" placeholder="请输入内容" />
        </el-form-item>-->
            <el-form-item label="联系人" prop="linkman">
              <el-input v-model="form.linkman" placeholder="请输入联系人" />
            </el-form-item>
            <el-form-item label="联系电话" prop="contactNumber">
              <el-input
                v-model="form.contactNumber"
                placeholder="请输入联系电话"
              />
            </el-form-item>
            <el-form-item label="联系地址" prop="address">
              <el-input
                v-model="form.address"
                type="textarea"
                placeholder="请输入联系地址"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm">确 定</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getTenantInfo, updateTenant } from "@/api/system/tenant";

export default {
  name: "Tenant",
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 状态数据字典
      statusOptions: [],
      // 是否显示弹出层
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        tenantName: [
          { required: true, message: "租户名称不能为空", trigger: "blur" },
        ],
      },
    };
  },
  computed: {},
  created() {
    this.getDicts("sys_normal_disable").then((response) => {
      this.statusOptions = response.data;
    });
    this.getTenant();
  },
  methods: {
    /** 查询租户列表 */
    getTenant() {
      this.loading = true;
      getTenantInfo().then((response) => {
        this.form = response.data;
        this.loading = false;
      });
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        tenantId: null,
        tenantName: null,
        domain: null,
        backgroundUrl: null,
        linkman: null,
        contactNumber: null,
        address: null,
        accountNumber: -1,
        expireTime: null,
        createUser: null,
        createDept: null,
        createTime: null,
        updateUser: null,
        updateTime: null,
        status: "0",
        deleted: null,
      };
      this.resetForm("form");
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          updateTenant(this.form).then((response) => {
            this.msgSuccess("修改成功");
          });
        }
      });
    },
  },
};
</script>
