/**
 * 分单规则 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2022-06-02 09:38:41
 */
function ListPage() {
	var settings,admin,form,table,layer,util,fox,upload,xmSelect;
	/**
	 * 入口函数，初始化
	 */
	this.init=function(layui) {
		admin = layui.admin,settings = layui.settings,form = layui.form,upload = layui.upload,laydate= layui.laydate;
		table = layui.table,layer = layui.layer,util = layui.util,fox = layui.foxnic,xmSelect = layui.xmSelect,dropdown=layui.dropdown;;
		//调用 iframe 加载过程
		var formIfrs=$(".form-iframe");
		for (var i = 0; i < formIfrs.length; i++) {
			var jsFn=$(formIfrs[i]).attr("js-fn");
			if(window.pageExt.list){
				jsFn=window.pageExt.list[jsFn];
				jsFn && jsFn($(formIfrs[i]),$(formIfrs[i])[0].contentWindow,'idle');
			}
		}

		queryTabData("none");

		$("#wait_repair_count_tab").on('click', function(){
			queryTabData("none");
		//	$("#orderWaitRepair")[0].contentWindow.location.reload();
			$("#orderWaitRepair")[0].contentWindow.calWidth();
		});

		$("#repairing_count_tab").on('click', function(){
			queryTabData("none");
			//$("#orderRepairing")[0].contentWindow.location.reload();
			$("#orderRepairing")[0].contentWindow.calWidth();
		});
		$("#wait_acceptance_count_tab").on('click', function(){
			queryTabData("none");
			//$("#orderWaitAcceptance")[0].contentWindow.location.reload();
			$("#orderWaitAcceptance")[0].contentWindow.calWidth();
		});
		$("#acceptance_failed_count_tab").on('click', function(){
			queryTabData("none");
			//$("#orderAcceptanceFailed")[0].contentWindow.location.reload();
			$("#orderAcceptanceFailed")[0].contentWindow.calWidth();
		});

		$("#orderFinish_tab").on('click', function(){
			//$("#orderAcceptanceFailed")[0].contentWindow.location.reload();
			$("#orderFinish")[0].contentWindow.calWidth();
		});

		$("#all_tab").on('click', function(){
			//$("#orderAcceptanceFailed")[0].contentWindow.location.reload();
			$("#orderAll")[0].contentWindow.calWidth();
		});
	}
	function queryTabData(label){
		admin.post("/service-eam/eam-repair-order/query-status-count-data", { label :label }, function (r) {
			if (r.success) {
				var data=r.data;
				//$("#not_dispatch_count").html(data.notDispatchCount);
				$("#wait_repair_count").html(data.waitRepairCount);
				$("#repairing_count").html(data.repairingCount);
				$("#wait_acceptance_count").html(data.waitAcceptanceCount);
				$("#acceptance_failed_count").html(data.acceptanceFailedCount);
			} else {
				fox.showMessage(r);
			}
		});
	}

	window.module={};
	window.pageExt.list.ending && window.pageExt.list.ending();
};
layui.use(['form', 'table', 'util', 'settings', 'admin', 'upload','foxnic','xmSelect','laydate','dropdown'],function() {
	var task=setInterval(function (){
		if(!window["pageExt"]) return;
		clearInterval(task);
		(new ListPage()).init(layui);
	},1);
});