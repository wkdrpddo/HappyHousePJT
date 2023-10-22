const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  


  outputDir: "C:/Users/rhkdt/Desktop/FinalPJT/Final_Project_HappyHouse/HappyHouseVue/src/main/resources/static",

  // indexPath: "C:/Users/rhkdt/Desktop/FinalPJT/Final_Project_HappyHouse/HappyHouseVue/src/main/resources/static/index.html",
  // devServer: {
  //   proxy: "http://localhost:8080"
  // },
  // chainWebpack: config => {
  //   const svgRule = config.module.rule("svg");
  //   svgRule.uses.clear();
  //   svgRule.use("vue-svg-loader").loader("vue-svg-loader");
  // }
})
