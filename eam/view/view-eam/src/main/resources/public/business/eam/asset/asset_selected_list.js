/**
 * 资产 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2021-09-20 20:11:28
 */


function ListPage() {
        
	var settings,admin,form,table,layer,util,fox,upload,xmSelect;
	//模块基础路径
	const moduleURL="/service-eam/eam-asset";
	const selectmoduleURL="/service-eam/eam-asset-selected-data";

	var dataTable=null;
	var selectedListAction;
	var billdata;
	var dataType="refresh";
	/**
      * 入口函数，初始化
      */
	this.init=function(layui) {
     	
     	admin = layui.admin,settings = layui.settings,form = layui.form,upload = layui.upload,laydate= layui.laydate;
		table = layui.table,layer = layui.layer,util = layui.util,fox = layui.foxnic,xmSelect = layui.xmSelect,dropdown=layui.dropdown;;

		selectedListAction=admin.getTempData('eam-asset-selected-action'+ASSET_SELECTED_CODE);
		billdata=admin.getTempData('eam-asset-selected-data'+ASSET_SELECTED_CODE);
		// billdata={assetOwnerId:""};
		if(window.pageExt.list.beforeInit) {
			window.pageExt.list.beforeInit();
		}
     	//渲染表格
     	renderTable();
		//初始化搜索输入框组件
		// initSearchFields();
		//绑定搜索框事件
		bindSearchEvent();
		//绑定按钮事件
		bindButtonEvent();
		//绑定行操作按钮事件
    	//bindRowOperationEvent();
     }
     
     
     /**
      * 渲染表格
      */
    function renderTable() {
		$(window).resize(function() {
			fox.adjustSearchElement();
		});
		fox.adjustSearchElement();
		//
		function renderTableInternal() {

			var ps={};
			var contitions={};
			if(window.pageExt.list.beforeQuery){
				window.pageExt.list.beforeQuery(contitions,ps,"tableInit");
			}
			if(Object.keys(contitions).length>0) {
				ps = {searchField: "$composite", searchValue: JSON.stringify(contitions)};
			}
			var templet=window.pageExt.list.templet;
			if(templet==null) {
				templet=function(field,value,row) {
					if(value==null) return "";
					return value;
				}
			}

			var checkboxhide=false;
			if (selectedListAction=="view"){
				checkboxhide=true;
			}
			var h=$(".search-bar").height();
			dataTable=fox.renderTable({
				elem: '#data-table',
				toolbar: '#toolbarTemplate',
				defaultToolbar: ['filter',{title: '刷新数据',layEvent: 'refresh-data',icon: 'layui-icon-refresh-3'}],
				url: moduleURL +'/query-paged-list-by-selected?assetSelectedCode='+ASSET_SELECTED_CODE+"&assetOwnerId="+billdata.assetOwnerId+"&dataType="+dataType,
				height: 400,
				limit: 50,
				where: ps,
				cols: [[
					{ fixed: 'left',type: 'numbers' },
					{ fixed: 'left',type:'checkbox', hide:checkboxhide}
					,{ field: 'categoryId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('资产分类'), templet: function (d) { return templet('categoryId',fox.joinLabel(d.category,"name"),d);}}
					,{ field: 'categoryCode', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('分类编码') , templet: function (d) { return templet('categoryCode',d.categoryCode,d);}  }
					,{ field: 'businessCode', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('业务编号') , templet: function (d) { return templet('businessCode',d.businessCode,d);}  }
					,{ field: 'procId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('流程') , templet: function (d) { return templet('procId',d.procId,d);}  }
					,{ field: 'status', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('办理状态'), templet:function (d){ return templet('status',fox.getEnumText(SELECT_STATUS_DATA,d.status),d);}}
					,{ field: 'batchCode', align:"left",fixed:false,  hide:true, sort: true, title: fox.translate('批次编码') , templet: function (d) { return templet('batchCode',d.batchCode,d);}  }
					,{ field: 'assetCode', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('资产编号') , templet: function (d) { return templet('assetCode',d.assetCode,d);}  }
					,{ field: 'assetStatus', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('资产状态'), templet:function (d){ return templet('assetStatus',fox.getEnumText(SELECT_ASSETSTATUS_DATA,d.assetStatus),d);}}
					,{ field: 'goodsId', align:"left",fixed:false,  hide:true, sort: true, title: fox.translate('物品档案'), templet: function (d) { return templet('goodsId',fox.joinLabel(d.goods,"name"),d);}}
					,{ field: 'name', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('名称') , templet: function (d) { return templet('name',d.name,d);}  }
					,{ field: 'manufacturerId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('厂商'), templet: function (d) { return templet('manufacturerId',fox.joinLabel(d.manufacturer,"manufacturerName"),d);}}
					,{ field: 'model', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('规格型号') , templet: function (d) { return templet('model',d.model,d);}  }
					,{ field: 'unit', align:"left",fixed:false,  hide:true, sort: true, title: fox.translate('计量单位') , templet: function (d) { return templet('unit',d.unit,d);}  }
					,{ field: 'serviceLife', align:"right",fixed:false,  hide:false, sort: true, title: fox.translate('使用期限') , templet: function (d) { return templet('serviceLife',d.serviceLife,d);}  }
					,{ field: 'serialNumber', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('序列号') , templet: function (d) { return templet('serialNumber',d.serialNumber,d);}  }
					,{ field: 'ownCompanyId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('所属公司') , templet: function (d) { return templet('ownCompanyId',d.ownCompanyId,d);}  }
					,{ field: 'managerId', align:"left",fixed:false,  hide:true, sort: true, title: fox.translate('管理人员') , templet: function (d) { return templet('managerId',d.managerId,d);}  }
					,{ field: 'useOrganizationId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('使用公司/部门') , templet: function (d) { return templet('useOrganizationId',d.useOrganizationId,d);}  }
					,{ field: 'useUserId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('使用人员') , templet: function (d) { return templet('useUserId',d.useUserId,d);}  }
					,{ field: 'positionId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('位置'), templet: function (d) { return templet('positionId',fox.joinLabel(d.position,"name"),d);}}
					,{ field: 'positionDetail', align:"left",fixed:false,  hide:true, sort: true, title: fox.translate('详细位置') , templet: function (d) { return templet('positionDetail',d.positionDetail,d);}  }
					 ,{ field: 'sourceId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('来源'), templet: function (d) { return templet('sourceId',fox.joinLabel(d.source,"label"),d);}}
				    ,{ field: 'financialCategoryId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('财务分类'), templet: function (d) { return templet('financialCategoryId',fox.joinLabel(d.categoryFinance,"hierarchyName"),d);}}
					,{ field: 'financialCode', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('财务编号') , templet: function (d) { return templet('financialCode',d.financialCode,d);}  }
					,{ field: 'supplierId', align:"left",fixed:false,  hide:false, sort: true, title: fox.translate('供应商'), templet: function (d) { return templet('supplierId',fox.joinLabel(d.supplier,"supplierName"),d);}}
				    ,{ field: 'label', align:"left",fixed:false,  hide:true, sort: true, title: fox.translate('标签') , templet: function (d) { return templet('label',d.label,d);}  }
			 	]],
				done: function (data) {
					dataType="keep";
					dataTable.config.url=moduleURL +'/query-paged-list-by-selected?assetSelectedCode='+ASSET_SELECTED_CODE+"&assetOwnerId="+billdata.assetOwnerId+"&dataType="+dataType;
					console.log('put keepdata ',dataTable)
					window.pageExt.list.afterQuery && window.pageExt.list.afterQuery(data); },
				footer : {

				}
			});
			//绑定排序事件
			table.on('sort(data-table)', function(obj){
			  refreshTableData(obj.field,obj.type);
			});
		}
		setTimeout(renderTableInternal,1);
    };

	/**
      * 刷新表格数据
      */
	function refreshTableData(sortField,sortType) {
		var value = {};
	 	var ps={};
		if(window.pageExt.list.beforeQuery){
			if(!window.pageExt.list.beforeQuery(value,ps,"refresh")) return;
		}
		if(sortField) {
			ps.sortField=sortField;
			ps.sortType=sortType;
		}
		table.reload('data-table', { where : ps });
	}


	function getDataListSize(){
		console.log(table.getData("data-table"));
		return table.getData("data-table").length;
	}

	/**
	  * 获得已经选中行的数据,不传入 field 时，返回所有选中的记录，指定 field 时 返回指定的字段集合
	  */
	function getCheckedList(field) {

		var checkStatus = table.checkStatus('data-table');
		var data = checkStatus.data;
		if(!field) return data;
		for(var i=0;i<data.length;i++) data[i]=data[i][field];
		return data;
	}


	/**
	 * 绑定搜索框事件
	 */
	function bindSearchEvent() {
		//回车键查询
        $(".search-input").keydown(function(event) {
			if(event.keyCode !=13) return;
		  	refreshTableData();
        });

        // 搜索按钮点击事件
        $('#search-button').click(function () {
           refreshTableData();
        });

		// 搜索按钮点击事件
		$('#search-button-advance').click(function () {
			fox.switchSearchRow(2,function (ex){
				if(ex=="1") {
					$('#search-button-advance span').text("关闭");
				} else {
					$('#search-button-advance span').text("更多");
				}
			});
		});

	}
	
	/**
	 * 绑定按钮事件
	  */
	function bindButtonEvent() {

		//头工具栏事件
		table.on('toolbar(data-table)', function(obj){
			var checkStatus = table.checkStatus(obj.config.id);
			var selected=getCheckedList("id");
			switch(obj.event){
				case 'create':
					openCreateFrom();
					break;
				case 'batch-del':
					batchDelete(selected);
					break;
				case 'refresh-data':
					refreshTableData();
					break;
				case 'other':
					break;
			};
		});


		//添加按钮点击事件
        function openCreateFrom() {
        	// //设置新增是初始化数据
        	var data={};
			admin.putTempData('eam-asset-select-action', "create",true);
			admin.putTempData('eam-asset-select-data'+ASSET_SELECTED_CODE, billdata,true);
			console.log("selected page put billdata:",billdata)
            showEditForm(data);
        };
		
        //批量删除按钮点击事件
        function batchDelete(selected) {


        	if(window.pageExt.list.beforeBatchDelete) {
				var doNext=window.pageExt.list.beforeBatchDelete(selected);
				if(!doNext) return;
			}

			var ids=getCheckedList("id");
            if(ids.length==0) {
				top.layer.msg(fox.translate('请选择需要删除的')+fox.translate('资产')+"!");
            	return;
            }

            //调用批量删除接口
			top.layer.confirm(fox.translate('确定删除已选中的')+fox.translate('资产')+fox.translate('吗？'), function (i) {
				top.layer.close(i);
				top.layer.load(2);
                admin.request(selectmoduleURL+"/delete-by-ids", { ids: ids,assetSelectedCode:ASSET_SELECTED_CODE,assetOwnerId:billdata.assetOwnerId}, function (data) {
					top.layer.closeAll('loading');
                    if (data.success) {
						if(window.pageExt.list.afterBatchDelete) {
							var doNext=window.pageExt.list.afterBatchDelete(data);
							if(!doNext) return;
						}
                    	top.layer.msg(data.message, {icon: 1, time: 500});
                        refreshTableData();
                    } else {
						top.layer.msg(data.message, {icon: 2, time: 1500});
                    }
                });

			});
        }
	}
     
    // /**
    //  * 绑定行操作按钮事件
    //  */
    // function bindRowOperationEvent() {
	// 	// 工具条点击事件
	// 	table.on('tool(data-table)', function (obj) {
	// 		var data = obj.data;
	// 		var layEvent = obj.event;
	// 		admin.putTempData('eam-asset-select-data-list-action', "",true);
	// 		if (layEvent === 'edit') { // 修改
	// 			//延迟显示加载动画，避免界面闪动
	// 			var task=setTimeout(function(){layer.load(2);},1000);
	// 			admin.request(moduleURL+"/get-by-id", { id : data.id }, function (data) {
	// 				clearTimeout(task);
	// 				layer.closeAll('loading');
	// 				if(data.success) {
	// 					admin.putTempData('eam-asset-select-data-list-action', "edit",true);
	// 					showEditForm(data.data);
	// 				} else {
	// 					 layer.msg(data.message, {icon: 1, time: 1500});
	// 				}
	// 			});
	// 		} else if (layEvent === 'view') { // 查看
	// 			//延迟显示加载动画，避免界面闪动
	// 			var task=setTimeout(function(){layer.load(2);},1000);
	// 			admin.request(moduleURL+"/get-by-id", { id : data.id }, function (data) {
	// 				clearTimeout(task);
	// 				layer.closeAll('loading');
	// 				if(data.success) {
	// 					admin.putTempData('eam-asset-select-data-list-action', "view",true);
	// 					showEditForm(data.data);
	// 				} else {
	// 					layer.msg(data.message, {icon: 1, time: 1500});
	// 				}
	// 			});
	// 		}
	// 		else if (layEvent === 'del') { // 删除
	//
	// 			if(window.pageExt.list.beforeSingleDelete) {
	// 				var doNext=window.pageExt.list.beforeSingleDelete(data);
	// 				if(!doNext) return;
	// 			}
	//
	// 			top.layer.confirm(fox.translate('确定删除此')+fox.translate('资产')+fox.translate('吗？'), function (i) {
	// 				top.layer.close(i);
	//
	// 				top.layer.load(2);
	// 				admin.request(moduleURL+"/delete", { id : data.id }, function (data) {
	// 					top.layer.closeAll('loading');
	// 					if (data.success) {
	// 						if(window.pageExt.list.afterSingleDelete) {
	// 							var doNext=window.pageExt.list.afterSingleDelete(data);
	// 							if(!doNext) return;
	// 						}
	// 						top.layer.msg(data.message, {icon: 1, time: 500});
	// 						refreshTableData();
	// 					} else {
	// 						top.layer.msg(data.message, {icon: 2, time: 1500});
	// 					}
	// 				});
	// 			});
	//
	// 		}
	//
	// 	});
	//
    // };
    
    /**
     * 打开编辑窗口
     */
	function showEditForm(data) {
		if(window.pageExt.list.beforeEdit) {
			var doNext=window.pageExt.list.beforeEdit(data);
			if(!doNext) return;
		}
		var top=2
		var index=admin.popupCenter({
			title: "选择资产",
			resize: false,
			offset: [top,null],
			area: ["95%","90%"],
			type: 2,
			id:"eam-asset-select-data-win",
			content: '/business/eam/asset/asset_select_list.html?assetSelectedCode='+ASSET_SELECTED_CODE,
			finish: function () {
				console.log("select form finish,");
				refreshTableData();
			}
		});
		admin.putTempData('eam-asset-select-data-popup-index', index);
	};

	window.module={
		getDataListSize:getDataListSize,
		refreshTableData: refreshTableData,
		getCheckedList: getCheckedList
	};

	window.pageExt.list.ending && window.pageExt.list.ending();

};


layui.use(['form', 'table', 'util', 'settings', 'admin', 'upload','foxnic','xmSelect','laydate','dropdown'],function() {
	var task=setInterval(function (){
		if(!window["pageExt"]) return;
		clearInterval(task);
		(new ListPage()).init(layui);
	},1);
});