
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>数据统计</title>
	<!-- 引入 echarts.js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/sources/js/echarts.min.js"></script>
	<!-- 引入jquery.js -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/sources/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/sources/js/china.js"></script>
	<script type="text/javascript">
        function loadWin() {
            //2秒自动刷新一次,2秒取得一次数据.
            setInterval("window.location.reload()",30000);
        }
	</script>

</head>
<body onload="loadWin();">
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<div id="pie" style="width: 600px;height:400px;float: right;margin-top: -400px"></div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    var option={

        tooltip: {},
        visualMap: {
            min: 0,
            max: 1500,
            left: 'left',
            top: 'bottom',
            text: ['High','Low'],
            seriesIndex: [1],
            inRange: {
                color: ['#e0ffff', '#006edd']
            },
            calculable : true
        },
        geo: {
            map: 'china',
            roam: true,
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        color: 'rgba(0,0,0,0.4)'
                    }
                }
            },
            itemStyle: {
                normal:{
                    borderColor: 'rgba(0, 0, 0, 0.2)'
                },
                emphasis:{
                    areaColor: null,
                    shadowOffsetX: 0,
                    shadowOffsetY: 0,
                    shadowBlur: 20,
                    borderWidth: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        },
        series : [
            {
                type: 'scatter',
                coordinateSystem: 'geo',
                data: [],
                symbolSize: 20,
                symbol: 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z',
                symbolRotate: 35,
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#F06C00'
                    }
                }
            },
            {
                name: 'ip访问量',
                type: 'map',
                geoIndex: 0,
//                 tooltip: {show: false},
                data:[]
            }
        ]
    };
    myChart.setOption(option);
    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
    var names=[];    //类别数组（实际用来盛放X轴坐标值）
    var nums=[];    //销量数组（实际用来盛放Y坐标值）
    $.post(
        "/show/addressCount",
        {        },
        function(data){

            if(data.state) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                var map=data.data;
                for(var key in map){
                    var obj = new Object();
                    obj.name = key;
                    obj.value = map[key];
                    names.push(key);
                    nums.push(obj); //挨个取出销量并填入销量数组
                }

                myChart.hideLoading();    //隐藏加载动画
                myChart.setOption({        //加载数据图表
                    xAxis: {

                        data: names
                    },
                    series: [{
                        name: 'ip访问量',
                        data: nums
                    }]
                });
            }else{
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myChart.hideLoading();
            }
        }
    );
</script>
<script type="text/javascript">
    var myChart1 = echarts.init(document.getElementById('pie'));
    // 显示标题，图例和空的坐标轴
    myChart1.setOption({
        title : {
            text: '浏览器使用量统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:[]
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},

                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'使用量',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[]
            }
        ]
    });
    myChart1.showLoading();    //数据加载完之前先显示一段简单的loading动画
    var names1=[];    //类别数组（实际用来盛放X轴坐标值）
    var nums1=[];    //销量数组（实际用来盛放Y坐标值）
    $.post(
        "/show/browerCount",
        {        },
        function(data){
            if(data.state) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                var map=data.data;
                for(var key in map){
                    var obj = new Object();
                    obj.name = key;
                    obj.value = map[key];
                    names1.push(key);
                    nums1.push(obj); //挨个取出销量并填入销量数组
                }
                myChart1.hideLoading();    //隐藏加载动画
                myChart1.setOption({        //加载数据图表
                    legend: {
                        data:names1
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: "使用量",
                        data: nums1
                    }]
                });
            }else{
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myChart1.hideLoading();
            }
        }
    );

</script>

</body>
</html>
