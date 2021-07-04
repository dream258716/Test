<template>

  <div id="main" style="width: 600px;height:400px;"></div>

</template>

<script>
import Echarts from "echarts";

export default {

  data(){
    return{

    }

  },
  mounted() {

    this.$http.get("/user/barVo").then(ref =>{

      let myChart = Echarts.init(document.getElementById('main'));
      let option = {
        title:{
          text:'用户地区分布图',
          left:'center',
          top:30,
          textStyle:{
            color:'#0AB6FC'
          }
        },
        xAxis: {
          type: 'category',
          data: ref.data.name
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: ref.data.value,
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
          }
        }]
      };
      myChart.setOption(option);
    })
  }

}
</script>

<style scoped>

#main{
  margin: 60px auto;

}

</style>
