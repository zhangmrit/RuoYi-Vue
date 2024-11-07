<template>
  <el-date-picker
    v-model="dataRange"
    type="daterange"
    value-format="yyyy-MM-dd"
    format="yyyy-MM-dd"
    @change="handleChange"
    :clearable="clearable"
    range-separator="至"
    start-placeholder="开始日期"
    end-placeholder="结束日期"
    :size="size"
    :picker-options="pickerOptions"
    :disabled="disabled"
  />
</template>

<script>
export default {
  name: 'DateRangePicker',
  props: {
    //双向绑定的值 value
    value: { type: [String, Array, Date], required: true, default: '' },
    align: { type: String, default: 'center' },
    clearable: { type: Boolean, default: true },
    disabled: { type: Boolean, default: false },
    size: { type: String, default: 'small' },
  },
  data() {
    return {
      pickerOptions: {
        shortcuts: [
          {
            text: '今天',
            onClick(picker) {
              const end = new Date()
              const start = new Date(
                new Date(new Date().toLocaleDateString()).getTime(),
              ) // 当天0点
              picker.$emit('pick', [start, end])
            },
          },
          {
            text: '昨天',
            onClick(picker) {
              const end = new Date()
              end.setTime(end.getTime() - 3600 * 1000 * 24 * 1)
              end.setHours(23)
              end.setMinutes(59)
              end.setSeconds(59)
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 1)
              start.setHours(0)
              start.setMinutes(0)
              start.setSeconds(0)
              picker.$emit('pick', [start, end])
            },
          },
          {
            text: '当前月',
            onClick(picker) {
              // 当前月的第一天，到最后一天
              const start = new Date()
              start.setHours(0)
              start.setMinutes(0)
              start.setSeconds(0)
              start.setDate(1)
              const end = new Date()
              end.setHours(23)
              end.setMinutes(59)
              end.setSeconds(59)
              end.setMonth(end.getMonth() + 1)
              end.setDate(0)
              picker.$emit('pick', [start, end])
            },
          },
          {
            text: '上一个月',
            onClick(picker) {
              // 上一个月的第一天，到最后一天
              const start = new Date()
              start.setHours(0)
              start.setMinutes(0)
              start.setSeconds(0)
              start.setDate(1)
              start.setMonth(start.getMonth() - 1)
              const end = new Date()
              end.setHours(23)
              end.setMinutes(59)
              end.setSeconds(59)
              end.setDate(0)
              picker.$emit('pick', [start, end])
            },
          },
          {
            text: '最近一周',
            onClick(picker) {
              const end = new Date()
              end.setHours(23)
              end.setMinutes(59)
              end.setSeconds(59)
              const start = new Date()
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 6)
              start.setHours(0)
              start.setMinutes(0)
              start.setSeconds(0)
              picker.$emit('pick', [start, end])
            },
          },
          {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date()
              end.setHours(23)
              end.setMinutes(59)
              end.setSeconds(59)
              const start = new Date()
              start.setHours(0)
              start.setMinutes(0)
              start.setSeconds(0)
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 29)
              picker.$emit('pick', [start, end])
            },
          },
          {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date()
              end.setHours(23)
              end.setMinutes(59)
              end.setSeconds(59)
              const start = new Date()
              start.setHours(0)
              start.setMinutes(0)
              start.setSeconds(0)
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
              picker.$emit('pick', [start, end])
            },
          },
          {
            text: '最近一年',
            onClick(picker) {
              const end = new Date()
              end.setHours(23)
              end.setMinutes(59)
              end.setSeconds(59)
              const start = new Date()
              start.setHours(0)
              start.setMinutes(0)
              start.setSeconds(0)
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 365)
              picker.$emit('pick', [start, end])
            },
          },
        ],
      },
      dataRange: this.value,
    }
  },
  watch: {
    value(val) {
      this.dataRange = val
    },
  },
  methods: {
    handleChange(val) {
      let result = val ?? [] //清空时值为null，在这里判断一下，否则会报错
      this.$emit('input', result)
    },
  },
}
</script>
