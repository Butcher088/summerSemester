module.exports = {
  lintOnSave: false, // 取消格式化

  devServer: {
    proxy: 'http://127.0.0.1:8003', // 请求数据的地址
  },
};
