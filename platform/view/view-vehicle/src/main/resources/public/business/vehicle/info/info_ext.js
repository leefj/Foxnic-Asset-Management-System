/**
 * 车辆信息 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2022-04-02 17:59:08
 */

layui.config({
    dir: layuiPath,
    base: '/module/'
}).extend({
    xmSelect: 'xm-select/xm-select',
    foxnicUpload: 'upload/foxnic-upload'
})
//
layui.define(['form', 'table', 'util', 'settings', 'admin', 'upload','foxnic','xmSelect','laydate','foxnicUpload','dropdown'],function () {

    var admin = layui.admin,settings = layui.settings,form = layui.form,upload = layui.upload,laydate= layui.laydate,dropdown=layui.dropdown;
    table = layui.table,layer = layui.layer,util = layui.util,fox = layui.foxnic,xmSelect = layui.xmSelect,foxup=layui.foxnicUpload;

    //模块基础路径
    const moduleURL="/service-vehicle/vehicle-info";


    //列表页的扩展
    var list={
        /**
         * 列表页初始化前调用
         * */
        beforeInit:function () {
            console.log("list:beforeInit");


        },
        /**
         * 表格渲染前调用
         * @param cfg 表格配置参数
         * */
        beforeTableRender:function (cfg){
            console.log("list:beforeTableRender",cfg);
        },
        /**
         * 表格渲染后调用
         * */
        afterTableRender :function (){

        },
        afterSearchInputReady: function() {
            console.log("list:afterSearchInputReady");
        },
        /**
         * 对话框打开之前调用，如果返回 null 则不打开对话框
         * */
        beforeDialog:function (param){
            //param.title="覆盖对话框标题";
            return param;
        },
        /**
         * 对话框回调，表单域以及按钮 会自动改变为选中的值，此处处理额外的逻辑即可
         * */
        afterDialog:function (param,result) {
            console.log('dialog',param,result);
        },
        /**
         * 当下拉框别选择后触发
         * */
        onSelectBoxChanged:function(id,selected,changes,isAdd) {
            console.log('onSelectBoxChanged',id,selected,changes,isAdd);
        },
        /**
         * 当日期选择组件选择后触发
         * */
        onDatePickerChanged:function(id,value, date, endDate) {
            console.log('onDatePickerChanged',id,value, date, endDate);
        },
        /**
         * 查询前调用
         * @param conditions 复合查询条件
         * @param param 请求参数
         * @param location 调用的代码位置
         * */
        beforeQuery:function (conditions,param,location) {
            console.log('beforeQuery',conditions,param,location);
            return true;
        },
        /**
         * 查询结果渲染后调用
         * */
        afterQuery : function (data) {

        },
        /**
         * 进一步转换 list 数据
         * */
        templet:function (field,value,r) {
            if(value==null) return "";
            return value;
        },
        /**
         * 表单页面打开时，追加更多的参数信息
         * */
        makeFormQueryString:function(data,queryString,action) {
            return queryString;
        },
        /**
         * 在新建或编辑窗口打开前调用，若返回 false 则不继续执行后续操作
         * */
        beforeEdit:function (data) {
            console.log('beforeEdit',data);
            return true;
        },
        /**
         * 单行删除前调用，若返回false则不执行后续操作
         * */
        beforeSingleDelete:function (data) {
            console.log('beforeSingleDelete',data);
            return true;
        },
        afterSingleDelete:function (data){
            console.log('beforeSingleDelete',data);
            return true;
        },
        /**
         * 批量删除前调用，若返回false则不执行后续操作
         * */
        beforeBatchDelete:function (selected) {
            console.log('beforeBatchDelete',selected);
            return true;
        },
        /**
         * 批量删除后调用，若返回false则不执行后续操作
         * */
        afterBatchDelete:function (data) {
            console.log('afterBatchDelete',data);
            return true;
        },
        /**
         * 工具栏按钮事件前调用，如果返回 false 则不执行后续代码
         * */
        beforeToolBarButtonEvent:function (selected,obj) {
            console.log('beforeToolBarButtonEvent',selected,obj);
            return true;
        },
        /**
         * 列表操作栏按钮事件前调用，如果返回 false 则不执行后续代码
         * */
        beforeRowOperationEvent:function (data,obj) {
            console.log('beforeRowOperationEvent',data,obj);
            return true;
        },
        /**
         * 表格右侧操作列更多按钮事件
         * */
        moreAction:function (menu,data, it){
            // {"code":"card","id":"1","title":"打印资产卡片"},
            // {"code":"labelCard","id":"2","title":"打印资产标签"},
            // {"code":"labelDown","id":"3","title":"下载资产标签"}
            // {"code":"batchInsert","id":"1","title":"资产批量入库"},
            // {"code":"highExportData","id":"2","title":"资产数据导出"}
            console.log('moreAction',menu,data,it);
            if(menu.code=="batchInsert"){
                window.pageExt.list.batchInsert(data,null);
            }else if(menu.code=="highExportData"){
                window.pageExt.list.highExportData(data,null);
            }else if(menu.code=="downloadTpl"){
                window.pageExt.list.downloadTpl(data,null);
            }
        },
        downloadTpl:function (data){
            var value = {};
            value.businessCode={ inputType:"button",value: "1234567890" ,fuzzy: true,valuePrefix:"",valueSuffix:"" };
            var ps={searchField: "$composite", searchValue: JSON.stringify(value)};
            var downloadUrl="/service-vehicle/vehicle-info/export-excel"
            ps.id="123456789";
            fox.submit(downloadUrl,ps,"post",function(){
            });
        },
        batchInsert:function(data,item){
            var ps={}
            admin.request("/service-eam/eam-asset-data/batch-import-asset", ps, function (attributeData) {
            }, "POST");
            var queryString=""
            var index=admin.popupCenter({
                title: "数据导入",
                resize: false,
                offset: [15,null],
                area: ["80%","95%"],
                type: 2,
                id:"eam-asset-data-batch-insert-data-win",
                content: '/business/eam/asset/asset_excel_oper.html' + queryString,
                finish: function () {
                    window.module.refreshTableData();
                }
            });
            admin.putTempData('eam-asset-data-batch-insert-data-popup-index', index);

        },
        highExportData:function(data,item){
            function getSelectedValue(id,prop) { var xm=xmSelect.get(id,true); return xm==null ? null : xm.getValue(prop);}
            var value = {};
            value.vehicleStatus={ inputType:"select_box", value: getSelectedValue("#vehicleStatus","value") ,fillBy:["vehicleStatusDict"]  , label:getSelectedValue("#vehicleStatus","nameStr") };
            value.type={ inputType:"select_box", value: getSelectedValue("#type","value") ,fillBy:["vehicleTypeDict"]  , label:getSelectedValue("#type","nameStr") };
            value.vehicleCode={ inputType:"button",value: $("#vehicleCode").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
            value.model={ inputType:"button",value: $("#model").val()};
            value.registrant={ inputType:"button",value: $("#registrant").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
            value.engineNumber={ inputType:"button",value: $("#engineNumber").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
            value.frameNumber={ inputType:"button",value: $("#frameNumber").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
            value.drivingLicense={ inputType:"button",value: $("#drivingLicense").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
            value.insuranceCompany={ inputType:"button",value: $("#insuranceCompany").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
            value.insuranceExpireDate={ inputType:"date_input", begin: $("#insuranceExpireDate-begin").val(), end: $("#insuranceExpireDate-end").val() ,matchType:"auto" };
            var ps={searchField: "$composite", searchValue: JSON.stringify(value)};
            var downloadUrl="/service-vehicle/vehicle-info/export-excel"

            fox.submit(downloadUrl,ps,"post",function(){
                console.log("execute finish");
            });
        },

        /**
         * 末尾执行
         */
        ending:function() {

        }
    }

    //表单页的扩展
    var form={
        /**
         * 表单初始化前调用 , 并传入表单数据
         * */
        beforeInit:function (action,data) {
            //获取参数，并调整下拉框查询用的URL
            //var companyId=admin.getTempData("companyId");
            //fox.setSelectBoxUrl("employeeId","/service-hrm/hrm-employee/query-paged-list?companyId="+companyId);
            console.log("form:beforeInit")
        },
        /**
         * 窗口调节前
         * */
        beforeAdjustPopup:function () {
            console.log('beforeAdjustPopup');
            return true;
        },
        /**
         * 表单数据填充前
         * */
        beforeDataFill:function (data) {
            console.log('beforeDataFill',data);
        },
        /**
         * 表单数据填充后
         * */
        afterDataFill:function (data) {
            console.log('afterDataFill',data);
        },
        /**
         * 对话框打开之前调用，如果返回 null 则不打开对话框
         * */
        beforeDialog:function (param){
            //param.title="覆盖对话框标题";
            return param;
        },
        /**
         * 对话框回调，表单域以及按钮 会自动改变为选中的值，此处处理额外的逻辑即可
         * */
        afterDialog:function (param,result) {
            console.log('dialog',param,result);
        },
        /**
         * 当下拉框别选择后触发
         * */
        onSelectBoxChanged:function(id,selected,changes,isAdd) {
            console.log('onSelectBoxChanged',id,selected,changes,isAdd);
        },
        /**
         * 当日期选择组件选择后触发
         * */
        onDatePickerChanged:function(id,value, date, endDate) {
            console.log('onDatePickerChanged',id,value, date, endDate);
        },
        /**
         * 数据提交前，如果返回 false，停止后续步骤的执行
         * */
        beforeSubmit:function (data) {
            console.log("beforeSubmit",data);
            return true;
        },
        /**
         * 数据提交后窗口关闭前，如果返回 false，停止后续步骤的执行
         * */
        betweenFormSubmitAndClose:function (param,result) {
            console.log("betweenFormSubmitAndClose",result);
            return true;
        },
        /**
         * 数据提交后执行
         * */
        afterSubmit:function (param,result) {
            console.log("afterSubmitt",param,result);
        },

        /**
         * 文件上传组件回调
         *  event 类型包括：
         *  afterPreview ：文件选择后，未上传前触发；
         *  afterUpload ：文件上传后触发
         *  beforeRemove ：文件删除前触发
         *  afterRemove ：文件删除后触发
         * */
        onUploadEvent: function(e) {
            console.log("onUploadEvent",e);
        },
        /**
         * 末尾执行
         */
        ending:function() {

        }
    }
    //
    window.pageExt={form:form,list:list};
});