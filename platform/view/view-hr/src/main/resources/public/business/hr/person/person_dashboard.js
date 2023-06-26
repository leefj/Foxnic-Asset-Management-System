/**
 * 存放位置 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2021-10-12 12:47:14
 */

function ListPage() {
    var settings,admin,table,layer,util,fox,upload,xmSelect,echarts;

    const moduleURL="/service-hr/hr-person";
    //模块基础路径
    /**
     * 入口函数，初始化
     */
    this.init=function(layui) {
        admin = layui.admin,settings = layui.settings,upload = layui.upload,laydate= layui.laydate;
        table = layui.table,layer = layui.layer,util = layui.util,fox = layui.foxnic,xmSelect = layui.xmSelect,dropdown=layui.dropdown;;
        echarts=layui.echarts;
      //  var assetMaintainProject = echarts.init(document.getElementById('assetMaintainProject'));
        var education_dis_data = echarts.init(document.getElementById('education_dis_data'));
        var status_dis_data = echarts.init(document.getElementById('status_dis_data'));
        var task=setTimeout(function(){layer.load(2);},1000);
        var ps={};
        ps.labels="info_data,status_dis_data,education_dis_data"
        admin.request(moduleURL+"/query-report-data", ps, function (data) {
            clearTimeout(task);
            layer.closeAll('loading');
            if(data.success){
                var assetData=data.data;
                var info_data=assetData.info_data;
                $("#person_cnt").html(info_data.personCnt);
                $("#ontrial_paractice_cnt").html(info_data.ontrialParacticeCnt);
                $("#non_employee_cnt").html(info_data.nonEmployeeCnt);
                $("#online_cnt").html(info_data.onlineCnt);
                $("#offline_cnt").html(info_data.offlineCnt);
                $("#non_employee_setting_cnt").html(info_data.nonEmployeeSettingCnt);


                var defColor=['#EF9590','#8095FE','#D9E0E3','#7DD699','#F8CE52',];
                //
                // var assetMaintainProjectChartOption = {
                //     color: defColor,
                //     title: {
                //         text: ''
                //     },
                //     tooltip: {},
                //     legend: {
                //         data: ['数量']
                //     },
                //     xAxis: {
                //         data: assetData.assetMaintainProjectName
                //     },
                //     yAxis: {
                //         type: 'value'
                //     },
                //     series: [{
                //         name: '数量',
                //         type: 'bar', //柱状
                //         data:assetData.assetMaintainProjectCount,
                //         label:{
                //             normal:{
                //                 show:true,
                //                 position:"top",
                //             }
                //         },
                //         itemStyle: {
                //             normal: { //柱子颜色
                //                 color: '#7CD8A5'
                //             }
                //         },
                //     }]
                // };
                //
                // assetMaintainProject.setOption(assetMaintainProjectChartOption, true);



                status_dis_data.setOption({
                    color: defColor,
                    series : [
                        {
                            name: '人员状态分布占比',
                            type: 'pie',    // 设置图表类型为饼图
                            radius: '55%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                            label:{
                                normal:{
                                    show:true,
                                    formatter:'{b}:{c}({d}%)'
                                }
                            },
                            data:assetData.status_dis_data
                        }
                    ]
                })

                education_dis_data.setOption({
                    color: defColor,
                    series : [
                        {
                            name: '人员学历分布占比',
                            type: 'pie',    // 设置图表类型为饼图
                            radius: '55%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                            label:{
                                normal:{
                                    show:true,
                                    formatter:'{b}:{c}({d}%)'
                                }
                            },
                            data:assetData.education_dis_data
                        }
                    ]
                })




            }else{
                alert("获取数据失败!");
            }


        });




    }

};


layui.use(['echarts', 'util', 'settings','admin'],function() {
    var task=setInterval(function (){
        if(!window["pageExt"]) return;
        clearInterval(task);
        (new ListPage()).init(layui);
    },1);
});