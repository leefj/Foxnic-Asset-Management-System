/**
 * 我的收藏 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2023-09-18 14:20:22
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
    const moduleURL="/service-oa/oa-netdisk-my-favorite";


    //列表页的扩展
    var list={
        /**
         * 列表页初始化前调用
         * */
        beforeInit:function () {
            console.log("list:beforeInit");
        },
        /**
         * 按事件名称移除表格按钮栏的按钮
         * */
        removeOperationButtonByEvent(event) {
            var template=$("#tableOperationTemplate");
            var content=template.text();
            content=content.split("\n");
            var buttons=[]
            for (let i = 0; i < content.length ; i++) {
                if(content[i] && content[i].indexOf("lay-event=\""+event+"\"")==-1) {
                    buttons.push(content[i]);
                }
            }
            template.text(buttons.join("\n"))
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
        processNavData:function(){
            console.log("CURRENT_NODE:",CURRENT_NODE)
            console.log("CURRENT_NODE fdHierarchy length:", CURRENT_NODE.fdHierarchy.split("-").length)
            if(CURRENT_NODE.fdHierarchy.split("-").length>1){
                setTimeout(function(){
                    $("#lastNode").show();
                    $("#fileNav").show();},50);
            }else if (CURRENT_NODE.fdHierarchy.split("-").length==1) {
                if(CURRENT_NODE.fdHierarchy.length==0){
                    //不存在数据
                    $("#lastNode").hide();
                    $("#fileNav").hide();
                    CURRENT_NODE={
                        fdHierarchyName:"",
                        fdHierarchy:"",
                        fdId:"space",
                    };
                    CURRENT_LAST_NODE={
                        fdHierarchyName:"",
                        fdHierarchy:"",
                        fdId:"space",
                    };
                }else{
                    setTimeout(function(){
                        $("#lastNode").show();
                        $("#fileNav").show();},50);
                }
            }else{
                alert("异常");
            }
            setTimeout(function(){
                $("#fileNav").text(CURRENT_NODE.fdHierarchyName);
                },50);

        },
        afterTableRender :function (){
            window.pageExt.list.processNavData();

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
            param.fdId=CURRENT_NODE.fdId;
            console.log('beforeQuery',conditions,param,location);
            return true;
        },
        /**
         * 查询结果渲染后调用
         * */
        afterQuery : function (data) {
            window.pageExt.list.processNavData();
            for(var i=0;i<data.length;i++){
                console.log(data[i].fdType,data[i]);
                if(data[i].fdType=="folder"){
                    fox.disableButton($('.ops-review-button').filter("[data-id='" + data[i].id + "']"), true);
                    fox.disableButton($('.ops-download-button').filter("[data-id='" + data[i].id + "']"), true);
                }else if(data[i].fdType=="file"){
                    console.log("##file")
                }else{
                    console.log("##none")
                }
            }
        },
        /**
         * 单行数据刷新后调用
         * */
        afterRefreshRowData: function (data,remote,context) {

        },
        /**
         * 进一步转换 list 数据
         * */
        templet:function (field,value,r) {
            if(field=="fdName"){
               if(r.fdType=="folder"){
                   return "<image style='margin-right:5px;width:35px;height: 35px' src='/assets/netdiskimg/folder.png'></image>"+value
               }else if(r.fdType=="file"){
                   var html="<span style='margin-right:40px' >"+value+"</span>";
                   if(r.netdiskOriginFile&&r.netdiskOriginFile.fileType){
                        if(r.netdiskOriginFile.fileType=="file_doc"){
                            html="<image style='margin-right:5px;width:25px;height:25px' src='/assets/netdiskimg/document3.png'></image>"+value
                        }else if(r.netdiskOriginFile.fileType=="file_photo"){
                            html="<image style='margin-right:5px;width:25px;height: 25px' src='/assets/netdiskimg/photo2.png'></image>"+value
                        }else if(r.netdiskOriginFile.fileType=="file_video"){
                            html="<image style='margin-right:5px;width:25px;height: 25px' src='/assets/netdiskimg/folder.png'></image>"+value
                        }else if(r.netdiskOriginFile.fileType=="file_music"){
                            html="<image style='margin-right:5px;width:25px;height: 25px' src='/assets/netdiskimg/music.png'></image>"+value
                        }else if(r.netdiskOriginFile.fileType=="file_zip"){
                            html="<image style='margin-right:5px;width:25px;height: 25px' src='/assets/netdiskimg/zip.png'></image>"+value
                        }else {
                            html="<image style='margin-right:5px;width:25px;height: 25px' src='/assets/netdiskimg/document3.png'></image>"+value
                        }
                   }
                   return html
               }else{
                   return value
               }
            }
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
            if(obj.event&&obj.event=="fdNameEvent"){
                window.pageExt.list.fdNameClick(data);
            }
            return true;
        },
        fdNameClick:function (data){
            if(data.fdType=="folder"){
                console.log("CURRENT_NODE",CURRENT_NODE)
                CURRENT_LAST_NODE_DATA.push(CURRENT_NODE)
                CURRENT_NODE=data
                window.pageExt.list.processNavData();
                window.module.refreshTableData();
            }
        },
        lastNode:function(select,obj){
            console.log("CURRENT_LAST_NODE_DATA",CURRENT_LAST_NODE_DATA)
            if(CURRENT_LAST_NODE_DATA.length>0){
                CURRENT_NODE=CURRENT_LAST_NODE_DATA[CURRENT_LAST_NODE_DATA.length-1];
                CURRENT_LAST_NODE_DATA.pop();
            }
            window.pageExt.list.processNavData();
            window.module.refreshTableData();
        },
        /**
         * 表格右侧操作列更多按钮事件
         * */
        review:function(data){
            console.log("review####",data)
            var netdiskOriginFile=data.netdiskOriginFile;
            var url="/business/oa/netdisk_file/view.html?id="+data.netdiskOriginFile.id;
            window.open(url);
        },
        moreAction:function (menu,data, it){
            console.log('moreAction',menu,data,it);
            if(menu.id=="del"){
                top.layer.confirm(fox.translate('确定删除吗？'), function (i) {
                    var ps={};
                    ps.id=data.fdId;
                    ps.type=data.fdType;
                    top.layer.close(i);
                    admin.post("/service-oa/oa-netdisk-virtual-fd/delete", ps, function (data) {
                        if (data.success) {
                            fox.showMessage(data);
                            window.module.refreshTableData()
                        } else {
                            fox.showMessage(data);
                        }
                    });
                });
            }else if(menu.id=="fav"){

            }else if(menu.id=="share"){
                var fdData=[];
                var fdObj={fdId:data.fdId,fdType:"file"};
                fdData.push(fdObj);
                $("#userId").val("");
                var userIdDialogOptions={
                    field:"userId",
                    formData: {},
                    inputEl:$("#userId"),
                    buttonEl:$(this),
                    single:false,
                    autoWidth:false,
                    //限制浏览的范围，指定根节点 id 或 code ，优先匹配ID
                    root: "",
                    targetType:"emp",
                    prepose:function(param){ return window.pageExt.form.beforeDialog && window.pageExt.form.beforeDialog(param);},
                    callback:function(param,result){
                        console.log("param",param)
                        console.log("result",result)
                        var userIds=result.selectedIds;
                        var ps={};
                        ps.userIds=userIds;
                        ps.data=JSON.stringify(fdData);
                        console.log("userIds",userIds+",userIds size",userIds.length);
                        if(userIds.length>0){
                            admin.post("/service-oa/oa-netdisk-virtual-fd/share", ps, function (data) {
                                if (data.success) {
                                    fox.showMessage(data);
                                    window.module.refreshTableData()
                                } else {
                                    fox.showMessage(data);
                                }
                            });
                        }
                    }
                };
                fox.chooseEmployee(userIdDialogOptions);
            }else if(menu.id=="rename"){
                layer.prompt({title:'请输入文件夹名称'},function(val,index){
                    console.log(val,index)
                    var ps={};
                    ps.id=data.fdId;
                    ps.name=val;
                    ps.type=data.fdType;
                    admin.post("/service-oa/oa-netdisk-virtual-fd/rename", ps, function (data) {
                        if (data.success) {
                            layer.close(index);
                            fox.showMessage(data);
                            window.module.refreshTableData()
                        } else {
                            fox.showMessage(data);
                        }
                    });
                });
            }
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
        beforeAdjustPopup:function (arg) {
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
        onRadioBoxChanged:function(id,data,checked) {
            console.log('onRadioChanged',id,data,checked);
        },
        onCheckBoxChanged:function(id,data,checked) {
            console.log('onCheckBoxChanged',id,data,checked);
        },

        /**
         * 在流程提交前处理表单数据
         * */
        processFormData4Bpm:function(processInstanceId,param,callback) {
            // 设置流程变量，并通过回调返回
            var variables={};
            // 此回调是必须的，否则流程提交会被中断
            callback(variables);
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