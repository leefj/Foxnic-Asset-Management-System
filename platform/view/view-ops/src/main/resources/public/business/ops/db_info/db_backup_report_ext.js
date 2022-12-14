/**
 * 存放位置 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2021-10-12 02:47:14
 */

layui.config({
	dir: layuiPath,
	base: '/module/'
}).extend({
	echarts: 'echarts/echarts'
})
//
layui.define(['echarts', 'util', 'settings', 'admin'],function () {

	var admin = layui.admin,settings = layui.settings,upload = layui.upload,laydate= layui.laydate,dropdown=layui.dropdown;
	echarts=layui.echarts, table = layui.table,layer = layui.layer,util = layui.util,fox = layui.foxnic,xmSelect = layui.xmSelect,foxup=layui.foxnicUpload;


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
			param.title="覆盖对话框标题";
			return param;
		},
		/**
		 * 对话框回调，表单域以及按钮 会自动改变为选中的值，此处处理额外的逻辑即可
		 * */
		afterDialog:function (param,result) {
			console.log('dialog',param,result);
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
			console.log('moreAction',menu,data,it);
		},
		/**
		 * 末尾执行
		 */
		ending:function() {

		}
	}

	//
	window.pageExt={list:list};
});