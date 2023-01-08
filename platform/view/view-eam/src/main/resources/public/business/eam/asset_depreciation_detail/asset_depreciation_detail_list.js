/**
 * 折旧明细 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-08 11:31:31
 */


function ListPage() {

	var settings,admin,form,table,layer,util,fox,upload,xmSelect;
	const languageContext='eam_asset_dep_detail'; 
	//模块基础路径
	const moduleURL="/service-eam/eam-asset-depreciation-detail";
	var dataTable=null;
	var sort=null;
	/**
      * 入口函数，初始化
      */
	this.init=function(layui) {

     	admin = layui.admin,settings = layui.settings,form = layui.form,upload = layui.upload,laydate= layui.laydate;
		table = layui.table,layer = layui.layer,util = layui.util,fox = layui.foxnic,xmSelect = layui.xmSelect,dropdown=layui.dropdown;

		if(window.pageExt.list.beforeInit) {
			window.pageExt.list.beforeInit();
		}
     	//渲染表格
     	renderTable();
		//初始化搜索输入框组件
		initSearchFields();
		//绑定搜索框事件
		bindSearchEvent();
		//绑定按钮事件
		bindButtonEvent();
		//绑定行操作按钮事件
    	bindRowOperationEvent();
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
		 var marginTop=$(".search-bar").height()+$(".search-bar").css("padding-top")+$(".search-bar").css("padding-bottom")
		 $("#table-area").css("margin-top",marginTop+"px");
		//
		function renderTableInternal() {

			var ps={searchField: "$composite"};
			var contitions={};

			if(window.pageExt.list.beforeQuery){
				window.pageExt.list.beforeQuery(contitions,ps,"tableInit");
			}
			ps.searchValue=JSON.stringify(contitions);

			var templet=window.pageExt.list.templet;
			if(templet==null) {
				templet=function(field,value,row) {
					if(value==null) return "";
					return value;
				}
			}
			var h=$(".search-bar").height();
			var tableConfig={
				elem: '#data-table',
				toolbar: '#toolbarTemplate',
				defaultToolbar: ['filter', 'print',{title: fox.translate('刷新数据','','cmp:table'),layEvent: 'refresh-data',icon: 'layui-icon-refresh-3'}],
				url: moduleURL +'/query-paged-list',
				height: 'full-'+(h+28),
				limit: 50,
				where: ps,
				cols: [[
					{ fixed: 'left',type: 'numbers' },
					{ fixed: 'left',type:'checkbox'}
					,{ field: 'id', align:"left",fixed:false,  hide:true, sort: true  , title: fox.translate('主键',null,languageContext) , templet: function (d) { return templet('id',d.id,d);}  }
					,{ field: 'firstDepreciationMethod', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('首次折旧方式',null,languageContext), templet:function (d){ return templet('firstDepreciationMethod',fox.getEnumText(SELECT_FIRSTDEPRECIATIONMETHOD_DATA,d.firstDepreciationMethod,'','firstDepreciationMethod'),d);}}
					,{ field: 'depreciationMethod', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('折旧方式',null,languageContext), templet:function (d){ return templet('depreciationMethod',fox.getEnumText(SELECT_DEPRECIATIONMETHOD_DATA,d.depreciationMethod,'','depreciationMethod'),d);}}
					,{ field: 'businessDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('业务日期',null,languageContext) ,templet: function (d) { return templet('businessDate',fox.dateFormat(d.businessDate,"yyyy-MM"),d); }  }
					,{ field: 'result', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('折旧规则',null,languageContext), templet:function (d){ return templet('result',fox.getEnumText(SELECT_RESULT_DATA,d.result,'','result'),d);}}
					,{ field: 'resultStatus', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('折旧结果',null,languageContext), templet:function (d){ return templet('resultStatus',fox.getEnumText(SELECT_RESULTSTATUS_DATA,d.resultStatus,'','resultStatus'),d);}}
					,{ field: 'resultDetail', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('结果明细',null,languageContext) , templet: function (d) { return templet('resultDetail',d.resultDetail,d);}  }
					,{ field: 'assetCategoryName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('资产类别',null,languageContext) , templet: function (d) { return templet('assetCategoryName',d.assetCategoryName,d);}  }
					,{ field: 'assetFinanceCategoryName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('财务分类',null,languageContext) , templet: function (d) { return templet('assetFinanceCategoryName',d.assetFinanceCategoryName,d);}  }
					,{ field: 'assetCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('资产编码',null,languageContext) , templet: function (d) { return templet('assetCode',d.assetCode,d);}  }
					,{ field: 'assetName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('资产名称',null,languageContext) , templet: function (d) { return templet('assetName',d.assetName,d);}  }
					,{ field: 'assetModel', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('资产型号',null,languageContext) , templet: function (d) { return templet('assetModel',d.assetModel,d);}  }
					,{ field: 'assetStatusName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('资产状态',null,languageContext) , templet: function (d) { return templet('assetStatusName',d.assetStatusName,d);}  }
					,{ field: 'assetPurchaseDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('采购日期',null,languageContext) ,templet: function (d) { return templet('assetPurchaseDate',fox.dateFormat(d.assetPurchaseDate,"yyyy-MM-dd"),d); }  }
					,{ field: 'assetRegisterDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('入账日期',null,languageContext) ,templet: function (d) { return templet('assetRegisterDate',fox.dateFormat(d.assetRegisterDate,"yyyy-MM-dd"),d); }  }
					,{ field: 'assetOriginalUnitPrice', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('资产原值',null,languageContext) , templet: function (d) { return templet('assetOriginalUnitPrice',d.assetOriginalUnitPrice,d);}  }
					,{ field: 'assetPurchaseUnitPrice', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('含税单价',null,languageContext) , templet: function (d) { return templet('assetPurchaseUnitPrice',d.assetPurchaseUnitPrice,d);}  }
					,{ field: 'assetNavPrice', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('资产净值',null,languageContext) , templet: function (d) { return templet('assetNavPrice',d.assetNavPrice,d);}  }
					,{ field: 'assetTaxAmountRate', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('税额',null,languageContext) , templet: function (d) { return templet('assetTaxAmountRate',d.assetTaxAmountRate,d);}  }
					,{ field: 'assetServiceLife', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('可使用期限(资产)',null,languageContext) , templet: function (d) { return templet('assetServiceLife',d.assetServiceLife,d);}  }
					,{ field: 'assetFinanceServiceLife', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('可使用期限(财务)',null,languageContext) , templet: function (d) { return templet('assetFinanceServiceLife',d.assetFinanceServiceLife,d);}  }
					,{ field: 'assetResidualsRate', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('本期残值率',null,languageContext) , templet: function (d) { return templet('assetResidualsRate',d.assetResidualsRate,d);}  }
					,{ field: 'assetResidualsPrice', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('本期残值',null,languageContext) , templet: function (d) { return templet('assetResidualsPrice',d.assetResidualsPrice,d);}  }
					,{ field: 'sOriginalPrice', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(期初)期初原值',null,languageContext) , templet: function (d) { return templet('sOriginalPrice',d.sOriginalPrice,d);}  }
					,{ field: 'sDepreciationAmount', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(期初)期初累计折旧',null,languageContext) , templet: function (d) { return templet('sDepreciationAmount',d.sDepreciationAmount,d);}  }
					,{ field: 'sNavAmount', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(期初)期初净值',null,languageContext) , templet: function (d) { return templet('sNavAmount',d.sNavAmount,d);}  }
					,{ field: 'sRecoverableAmount', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(期初)期初可回收净额',null,languageContext) , templet: function (d) { return templet('sRecoverableAmount',d.sRecoverableAmount,d);}  }
					,{ field: 'cUsedServiceLife', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('已使用期限',null,languageContext) , templet: function (d) { return templet('cUsedServiceLife',d.cUsedServiceLife,d);}  }
					,{ field: 'cOriginalPriceIncrease', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(本期发生)原值增加',null,languageContext) , templet: function (d) { return templet('cOriginalPriceIncrease',d.cOriginalPriceIncrease,d);}  }
					,{ field: 'cDepreciationAmount', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(本期发生)本期折旧额',null,languageContext) , templet: function (d) { return templet('cDepreciationAmount',d.cDepreciationAmount,d);}  }
					,{ field: 'cYearDepreciationAmount', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(本期发生)本年累计折旧额',null,languageContext) , templet: function (d) { return templet('cYearDepreciationAmount',d.cYearDepreciationAmount,d);}  }
					,{ field: 'eOriginalPrice', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(期末)期末原值',null,languageContext) , templet: function (d) { return templet('eOriginalPrice',d.eOriginalPrice,d);}  }
					,{ field: 'eDepreciationAmount', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(期末)期末累计折旧',null,languageContext) , templet: function (d) { return templet('eDepreciationAmount',d.eDepreciationAmount,d);}  }
					,{ field: 'eNavAmount', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(期末)期末净值',null,languageContext) , templet: function (d) { return templet('eNavAmount',d.eNavAmount,d);}  }
					,{ field: 'eRecoverableAmount', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('(期末)期末可回收金额',null,languageContext) , templet: function (d) { return templet('eRecoverableAmount',d.eRecoverableAmount,d);}  }
					,{ field: 'accountingServiceLife', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('会计期间已使用期限',null,languageContext) , templet: function (d) { return templet('accountingServiceLife',d.accountingServiceLife,d);}  }
					,{ field: 'firstDepreciation', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('首次折旧',null,languageContext) , templet: function (d) { return templet('firstDepreciation',d.firstDepreciation,d);}  }
					,{ field: 'useUserName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('使用人',null,languageContext) , templet: function (d) { return templet('useUserName',d.useUserName,d);}  }
					,{ field: 'managerName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('管理人员',null,languageContext) , templet: function (d) { return templet('managerName',d.managerName,d);}  }
					,{ field: 'useOrgName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('使用部门',null,languageContext) , templet: function (d) { return templet('useOrgName',d.useOrgName,d);}  }
					,{ field: 'financialOptionName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('财务选项',null,languageContext) , templet: function (d) { return templet('financialOptionName',d.financialOptionName,d);}  }
					,{ field: 'expenseItemName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('费用项目',null,languageContext) , templet: function (d) { return templet('expenseItemName',d.expenseItemName,d);}  }
					,{ field: 'customerInfo', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('客户情况',null,languageContext) , templet: function (d) { return templet('customerInfo',d.customerInfo,d);}  }
					,{ field: 'lastOperTime', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('上次折旧时间',null,languageContext) ,templet: function (d) { return templet('lastOperTime',fox.dateFormat(d.lastOperTime,"yyyy-MM-dd HH:mm:ss"),d); }  }
					,{ field: 'label', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('标签',null,languageContext) , templet: function (d) { return templet('label',d.label,d);}  }
					,{ field: fox.translate('空白列','','cmp:table'), align:"center", hide:false, sort: false, title: "",minWidth:8,width:8,unresize:true}
					,{ field: 'row-ops', fixed: 'right', align: 'center', toolbar: '#tableOperationTemplate', title: fox.translate('操作','','cmp:table'), width: 160 }
				]],
				done: function (data) { window.pageExt.list.afterQuery && window.pageExt.list.afterQuery(data); },
				footer : {
					exportExcel : false ,
					importExcel : false 
				}
			};
			window.pageExt.list.beforeTableRender && window.pageExt.list.beforeTableRender(tableConfig);
			dataTable=fox.renderTable(tableConfig);
			//绑定排序事件
			table.on('sort(data-table)', function(obj){
			  refreshTableData(obj.sortField,obj.type);
			});
			window.pageExt.list.afterTableRender && window.pageExt.list.afterTableRender();
		}
		setTimeout(renderTableInternal,1);
    };

	/**
	 * 刷新单号数据
	 * */
	function refreshRowData(data,remote) {
		var context=dataTable.getDataRowContext( { id : data.id } );
		if(context==null) return;
		if(remote) {
			admin.post(moduleURL+"/get-by-id", { id : data.id }, function (r) {
				if (r.success) {
					data = r.data;
					context.update(data);
					fox.renderFormInputs(form);
				} else {
					fox.showMessage(data);
				}
			});
		} else {
			context.update(data);
			fox.renderFormInputs(form);
		}
	}

	/**
      * 刷新表格数据
      */
	function refreshTableData(sortField,sortType,reset) {
		function getSelectedValue(id,prop) { var xm=xmSelect.get(id,true); return xm==null ? null : xm.getValue(prop);}
		var value = {};
		value.result={ inputType:"select_box", value: getSelectedValue("#result","value"), label:getSelectedValue("#result","nameStr") };
		value.resultStatus={ inputType:"select_box", value: getSelectedValue("#resultStatus","value"), label:getSelectedValue("#resultStatus","nameStr") };
		value.resultDetail={ inputType:"button",value: $("#resultDetail").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.assetCategoryName={ inputType:"button",value: $("#assetCategoryName").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.assetFinanceCategoryName={ inputType:"button",value: $("#assetFinanceCategoryName").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.assetCode={ inputType:"button",value: $("#assetCode").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.assetName={ inputType:"button",value: $("#assetName").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.assetModel={ inputType:"button",value: $("#assetModel").val()};
		value.assetStatusName={ inputType:"button",value: $("#assetStatusName").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.assetPurchaseDate={ inputType:"date_input", begin: $("#assetPurchaseDate-begin").val(), end: $("#assetPurchaseDate-end").val() ,matchType:"auto" };
		value.assetRegisterDate={ inputType:"date_input", begin: $("#assetRegisterDate-begin").val(), end: $("#assetRegisterDate-end").val() ,matchType:"auto" };
		value.financialOptionName={ inputType:"button",value: $("#financialOptionName").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.expenseItemName={ inputType:"button",value: $("#expenseItemName").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.customerInfo={ inputType:"button",value: $("#customerInfo").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		var ps={searchField:"$composite"};
		if(window.pageExt.list.beforeQuery){
			if(!window.pageExt.list.beforeQuery(value,ps,"refresh")) return;
		}
		ps.searchValue=JSON.stringify(value);
		if(sortField) {
			ps.sortField=sortField;
			ps.sortType=sortType;
			sort={ field : sortField,type : sortType} ;
		} else {
			if(sort) {
				ps.sortField=sort.field;
				ps.sortType=sort.type;
			} 		}
		if(reset) {
			table.reload('data-table', { where : ps , page:{ curr:1 } });
		} else {
			table.reload('data-table', { where : ps });
		}
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
	 * 重置搜索框
	 */
	function resetSearchFields() {
		$('#search-field').val("");
		$('#search-input').val("");
		layui.form.render();
	}

	function initSearchFields() {

		fox.switchSearchRow(1);

		//渲染 result 下拉字段
		fox.renderSelectBox({
			el: "result",
			radio: true,
			size: "small",
			filterable: false,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("result",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			transform:function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					opts.push({data:data[i],name:data[i].text,value:data[i].code});
				}
				return opts;
			}
		});
		//渲染 resultStatus 下拉字段
		fox.renderSelectBox({
			el: "resultStatus",
			radio: true,
			size: "small",
			filterable: false,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("resultStatus",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			transform:function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					opts.push({data:data[i],name:data[i].text,value:data[i].code});
				}
				return opts;
			}
		});
		laydate.render({
			elem: '#assetPurchaseDate-begin',
			trigger:"click",
			done: function(value, date, endDate) {
				setTimeout(function () {
					window.pageExt.list.onDatePickerChanged && window.pageExt.list.onDatePickerChanged("assetPurchaseDate",value, date, endDate);
				},1);
			}
		});
		laydate.render({
			elem: '#assetPurchaseDate-end',
			trigger:"click",
			done: function(value, date, endDate) {
				setTimeout(function () {
					window.pageExt.list.onDatePickerChanged && window.pageExt.list.onDatePickerChanged("assetPurchaseDate",value, date, endDate);
				},1);
			}
		});
		laydate.render({
			elem: '#assetRegisterDate-begin',
			trigger:"click",
			done: function(value, date, endDate) {
				setTimeout(function () {
					window.pageExt.list.onDatePickerChanged && window.pageExt.list.onDatePickerChanged("assetRegisterDate",value, date, endDate);
				},1);
			}
		});
		laydate.render({
			elem: '#assetRegisterDate-end',
			trigger:"click",
			done: function(value, date, endDate) {
				setTimeout(function () {
					window.pageExt.list.onDatePickerChanged && window.pageExt.list.onDatePickerChanged("assetRegisterDate",value, date, endDate);
				},1);
			}
		});
		fox.renderSearchInputs();
		window.pageExt.list.afterSearchInputReady && window.pageExt.list.afterSearchInputReady();
	}

	/**
	 * 绑定搜索框事件
	 */
	function bindSearchEvent() {
		//回车键查询
        $(".search-input").keydown(function(event) {
			if(event.keyCode !=13) return;
		  	refreshTableData(null,null,true);
        });

        // 搜索按钮点击事件
        $('#search-button').click(function () {
			refreshTableData(null,null,true);
        });

		// 搜索按钮点击事件
		$('#search-button-advance').click(function () {
			fox.switchSearchRow(1,function (ex){
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
			if(window.pageExt.list.beforeToolBarButtonEvent) {
				var doNext=window.pageExt.list.beforeToolBarButtonEvent(selected,obj);
				if(!doNext) return;
			}
			switch(obj.event){
				case 'create':
					admin.putTempData('eam-asset-depreciation-detail-form-data', {});
					openCreateFrom();
					break;
				case 'batch-del':
					batchDelete(selected);
					break;
				case 'tool-depreciation-start':
					window.pageExt.list.depreciationStart && window.pageExt.list.depreciationStart(selected,obj);
					break;
				case 'tool-depreciation-execute':
					window.pageExt.list.depreciationExecute && window.pageExt.list.depreciationExecute(selected,obj);
					break;
				case 'tool-depreciation-sync':
					window.pageExt.list.depreciationSync && window.pageExt.list.depreciationSync(selected,obj);
					break;
				case 'tool-depreciation-exclude':
					window.pageExt.list.depreciationExclude && window.pageExt.list.depreciationExclude(selected,obj);
					break;
				case 'tool-depreciation-export':
					window.pageExt.list.depreciationExport && window.pageExt.list.depreciationExport(selected,obj);
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
        	//设置新增是初始化数据
        	var data={};
			admin.putTempData('eam-asset-depreciation-detail-form-data-form-action', "create",true);
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
				top.layer.msg(fox.translate('请选择需要删除的'+'折旧明细'+"!",null,languageContext));
            	return;
            }
            //调用批量删除接口
			top.layer.confirm(fox.translate('确定删除已选中的'+'折旧明细'+'吗？',null,languageContext), function (i) {
                top.layer.close(i);
				admin.post(moduleURL+"/delete-by-ids", { ids: ids }, function (data) {
                    if (data.success) {
						if(window.pageExt.list.afterBatchDelete) {
							var doNext=window.pageExt.list.afterBatchDelete(data);
							if(!doNext) return;
						}
						fox.showMessage(data);
                        refreshTableData();
                    } else {
						if(data.data>0) {
							refreshTableData();
						}
						fox.showMessage(data);
                    }
                },{delayLoading:200,elms:[$("#delete-button")]});
			});
        }
	}

    /**
     * 绑定行操作按钮事件
     */
    function bindRowOperationEvent() {
		// 工具条点击事件
		table.on('tool(data-table)', function (obj) {
			var data = obj.data;
			var layEvent = obj.event;

			if(window.pageExt.list.beforeRowOperationEvent) {
				var doNext=window.pageExt.list.beforeRowOperationEvent(data,obj);
				if(!doNext) return;
			}

			admin.putTempData('eam-asset-depreciation-detail-form-data-form-action', "",true);
			if (layEvent === 'edit') { // 修改
				admin.post(moduleURL+"/get-by-id", { id : data.id }, function (data) {
					if(data.success) {
						admin.putTempData('eam-asset-depreciation-detail-form-data-form-action', "edit",true);
						showEditForm(data.data);
					} else {
						 fox.showMessage(data);
					}
				});
			} else if (layEvent === 'view') { // 查看
				admin.post(moduleURL+"/get-by-id", { id : data.id }, function (data) {
					if(data.success) {
						admin.putTempData('eam-asset-depreciation-detail-form-data-form-action', "view",true);
						showEditForm(data.data);
					} else {
						fox.showMessage(data);
					}
				});
			}
			else if (layEvent === 'del') { // 删除

				if(window.pageExt.list.beforeSingleDelete) {
					var doNext=window.pageExt.list.beforeSingleDelete(data);
					if(!doNext) return;
				}

				top.layer.confirm(fox.translate('确定删除此'+'折旧明细'+'吗？',null,languageContext), function (i) {
					top.layer.close(i);
					admin.post(moduleURL+"/delete", { id : data.id }, function (data) {
						top.layer.closeAll('loading');
						if (data.success) {
							if(window.pageExt.list.afterSingleDelete) {
								var doNext=window.pageExt.list.afterSingleDelete(data);
								if(!doNext) return;
							}
							fox.showMessage(data);
							refreshTableData();
						} else {
							fox.showMessage(data);
						}
					},{delayLoading:100, elms:[$(".ops-delete-button[data-id='"+data.id+"']")]});
				});
			}
			
		});

    };

    /**
     * 打开编辑窗口
     */
	function showEditForm(data) {
		if(window.pageExt.list.beforeEdit) {
			var doNext=window.pageExt.list.beforeEdit(data);
			if(!doNext) return;
		}
		var action=admin.getTempData('eam-asset-depreciation-detail-form-data-form-action');
		var queryString="";
		if(data && data.id) queryString='id=' + data.id;
		if(window.pageExt.list.makeFormQueryString) {
			queryString=window.pageExt.list.makeFormQueryString(data,queryString,action);
		}
		admin.putTempData('eam-asset-depreciation-detail-form-data', data);
		var area=admin.getTempData('eam-asset-depreciation-detail-form-area');
		var height= (area && area.height) ? area.height : ($(window).height()*0.6);
		var top= (area && area.top) ? area.top : (($(window).height()-height)/2);
		var title = fox.translate('折旧明细',null,languageContext);
		if(action=="create") title=fox.translate('添加','','cmp:table')+title;
		else if(action=="edit") title=fox.translate('修改','','cmp:table')+title;
		else if(action=="view") title=fox.translate('查看','','cmp:table')+title;

		admin.popupCenter({
			title: title,
			resize: false,
			offset: [top,null],
			area: ["80%",height+"px"],
			type: 2,
			id:"eam-asset-depreciation-detail-form-data-win",
			content: '/business/eam/asset_depreciation_detail/asset_depreciation_detail_form.html' + (queryString?("?"+queryString):""),
			finish: function () {
				if(action=="create") {
					refreshTableData();
				}
				if(action=="edit") {
					false?refreshTableData():refreshRowData(data,true);
				}
			}
		});
	};

	window.module={
		refreshTableData: refreshTableData,
		refreshRowData: refreshRowData,
		getCheckedList: getCheckedList,
		showEditForm: showEditForm
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