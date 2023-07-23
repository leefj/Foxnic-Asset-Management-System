/**
 * 保养方案 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2023-07-23 09:00:12
 */


function ListPage() {

	var settings,admin,form,table,layer,util,fox,upload,xmSelect;
	
	//模块基础路径
	const moduleURL="/service-eam/eam-maintain-plan";
	const queryURL=moduleURL+'/query-paged-list';
	const deleteURL=moduleURL+'/delete';
	const batchDeleteURL=moduleURL+'/delete-by-ids';
	const getByIdURL=moduleURL+'/get-by-id';
	//
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
				url: queryURL,
				height: 'full-'+(h+28),
				limit: 50,
				where: ps,
				cols: [[
					{ fixed: 'left',type: 'numbers' },
					{ fixed: 'left',type:'checkbox'}
					,{ field: 'id', align:"left",fixed:false,  hide:true, sort: true  , title: fox.translate('主键') , templet: function (d) { return templet('id',d.id,d);}  }
					,{ field: 'code', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('计划单据') , templet: function (d) { return templet('code',d.code,d);}  }
					,{ field: 'name', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('计划名称') , templet: function (d) { return templet('name',d.name,d);}  }
					,{ field: 'status', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('计划状态'), templet:function (d){ return templet('status',fox.getEnumText(SELECT_STATUS_DATA,d.status,'','status'),d);}}
					,{ field: 'assetCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('保养设备') , templet: function (d) { return templet('assetCode',d.assetCode,d);}  }
					,{ field: 'assetName', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('设备名称') , templet: function (d) { return templet('assetName',d.assetName,d);}  }
					,{ field: 'assetModel', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('设备型号') , templet: function (d) { return templet('assetModel',d.assetModel,d);}  }
					,{ field: 'assetSn', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('设备序列') , templet: function (d) { return templet('assetSn',d.assetSn,d);}  }
					,{ field: 'groupId', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('保养班组'), templet: function (d) { return templet('groupId' ,fox.joinLabel(d.maintainGroup,"name",',','','groupId'),d);}}
					,{ field: 'startTime', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('开始时间') ,templet: function (d) { return templet('startTime',fox.dateFormat(d.startTime,"yyyy-MM-dd"),d); }  }
					,{ field: 'endTime', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('结束时间') ,templet: function (d) { return templet('endTime',fox.dateFormat(d.endTime,"yyyy-MM-dd"),d); }  }
					,{ field: 'totalCost', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('预计工时(时)') , templet: function (d) { return templet('totalCost',d.totalCost,d);}  }
					,{ field: 'timeout', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('超时工时(分)') , templet: function (d) { return templet('timeout',d.timeout,d);}  }
					,{ field: 'lastTime', align:"right", fixed:false, hide:true, sort: true   ,title: fox.translate('上次执行') ,templet: function (d) { return templet('lastTime',fox.dateFormat(d.lastTime,"yyyy-MM-dd HH:mm:ss"),d); }  }
					,{ field: 'nextTime', align:"right", fixed:false, hide:true, sort: true   ,title: fox.translate('下次执行') ,templet: function (d) { return templet('nextTime',fox.dateFormat(d.nextTime,"yyyy-MM-dd HH:mm:ss"),d); }  }
					,{ field: 'itemCount', align:"",fixed:false,  hide:false, sort: false  , title: fox.translate('保养项目数') , templet: function (d) { return templet('itemCount',d.itemCount,d);}  }
					,{ field: fox.translate('空白列','','cmp:table'), align:"center", hide:false, sort: false, title: "",minWidth:8,width:8,unresize:true}
					,{ field: 'row-ops', fixed: 'right', align: 'center', toolbar: '#tableOperationTemplate', title: fox.translate('操作','','cmp:table'), width: 250 }
				]],
				done: function (data) {
					lockSwitchInputs();
					window.pageExt.list.afterQuery && window.pageExt.list.afterQuery(data);
				},
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
			admin.post(getByIdURL, { id : data.id }, function (r) {
				if (r.success) {
					data = r.data;
					context.update(data);
					fox.renderFormInputs(form);
					lockSwitchInputs();
					window.pageExt.list.afterRefreshRowData && window.pageExt.list.afterRefreshRowData(data,remote,context);
				} else {
					fox.showMessage(data);
				}
			});
		} else {
			context.update(data);
			fox.renderFormInputs(form);
			lockSwitchInputs();
			window.pageExt.list.afterRefreshRowData && window.pageExt.list.afterRefreshRowData(data,remote,context);
		}
	}



	function lockSwitchInputs() {
	}

	function lockSwitchInput(field) {
		var inputs=$("[lay-id=data-table]").find("td[data-field='"+field+"']").find("input");
		var switchs=$("[lay-id=data-table]").find("td[data-field='"+field+"']").find(".layui-form-switch");
		inputs.attr("readonly", "yes");
		inputs.attr("disabled", "yes");
		switchs.addClass("layui-disabled");
		switchs.addClass("layui-checkbox-disabled");
		switchs.addClass("layui-form-switch-disabled");
	}

	/**
      * 刷新表格数据
      */
	function refreshTableData(sortField,sortType,reset) {
		function getSelectedValue(id,prop) { var xm=xmSelect.get(id,true); return xm==null ? null : xm.getValue(prop);}
		var value = {};
		value.code={ inputType:"button",value: $("#code").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.name={ inputType:"button",value: $("#name").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.status={ inputType:"select_box", value: getSelectedValue("#status","value"), label:getSelectedValue("#status","nameStr") };
		value.assetCode={ inputType:"button",value: $("#assetCode").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.assetName={ inputType:"button",value: $("#assetName").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.assetModel={ inputType:"button",value: $("#assetModel").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.assetSn={ inputType:"button",value: $("#assetSn").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.groupId={ inputType:"select_box", value: getSelectedValue("#groupId","value") ,fillBy:["maintainGroup"]  , label:getSelectedValue("#groupId","nameStr") };
		value.maintainType={ inputType:"select_box", value: getSelectedValue("#maintainType","value") ,fillBy:["maintainTypeDict"]  , label:getSelectedValue("#maintainType","nameStr") };
		value.cycleMethod={ inputType:"select_box", value: getSelectedValue("#cycleMethod","value"), label:getSelectedValue("#cycleMethod","nameStr") };
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

		fox.switchSearchRow(2);

		//渲染 status 下拉字段
		fox.renderSelectBox({
			el: "status",
			radio: true,
			size: "small",
			filterable: false,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("status",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			transform:function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					if(window.pageExt.list.selectBoxDataTransform) {
						opts.push(window.pageExt.list.selectBoxDataTransform("status",{data:data[i],name:data[i].text,value:data[i].code},data[i],data,i));
					} else {
						opts.push({data:data[i],name:data[i].text,value:data[i].code});
					}
				}
				return opts;
			}
		});
		//渲染 groupId 下拉字段
		fox.renderSelectBox({
			el: "groupId",
			radio: true,
			size: "small",
			filterable: true,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("groupId",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			searchField: "name", //请自行调整用于搜索的字段名称
			extraParam: {}, //额外的查询参数，Object 或是 返回 Object 的函数
			transform: function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					if(!data[i]) continue;
					if(window.pageExt.list.selectBoxDataTransform) {
						opts.push(window.pageExt.list.selectBoxDataTransform("groupId",{data:data[i],name:data[i].name,value:data[i].id},data[i],data,i));
					} else {
						opts.push({data:data[i],name:data[i].name,value:data[i].id});
					}
				}
				return opts;
			}
		});
		//渲染 maintainType 下拉字段
		fox.renderSelectBox({
			el: "maintainType",
			radio: true,
			size: "small",
			filterable: true,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("maintainType",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			searchField: "label", //请自行调整用于搜索的字段名称
			extraParam: {}, //额外的查询参数，Object 或是 返回 Object 的函数
			transform: function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					if(!data[i]) continue;
					if(window.pageExt.list.selectBoxDataTransform) {
						opts.push(window.pageExt.list.selectBoxDataTransform("maintainType",{data:data[i],name:data[i].label,value:data[i].code},data[i],data,i));
					} else {
						opts.push({data:data[i],name:data[i].label,value:data[i].code});
					}
				}
				return opts;
			}
		});
		//渲染 cycleMethod 下拉字段
		fox.renderSelectBox({
			el: "cycleMethod",
			radio: true,
			size: "small",
			filterable: false,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("cycleMethod",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			transform:function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					if(window.pageExt.list.selectBoxDataTransform) {
						opts.push(window.pageExt.list.selectBoxDataTransform("cycleMethod",{data:data[i],name:data[i].text,value:data[i].code},data[i],data,i));
					} else {
						opts.push({data:data[i],name:data[i].text,value:data[i].code});
					}
				}
				return opts;
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
			if(window.pageExt.list.beforeToolBarButtonEvent) {
				var doNext=window.pageExt.list.beforeToolBarButtonEvent(selected,obj);
				if(!doNext) return;
			}
			switch(obj.event){
				case 'create':
					admin.putTempData('eam-maintain-plan-form-data', {});
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
        	//设置新增是初始化数据
        	var data={};
			admin.putTempData('eam-maintain-plan-form-data-form-action', "create",true);
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
				top.layer.msg(fox.translate('请选择需要删除的'+'保养方案'+"!"));
            	return;
            }
            //调用批量删除接口
			top.layer.confirm(fox.translate('确定删除已选中的'+'保养方案'+'吗？'), function (i) {
                top.layer.close(i);
				admin.post(batchDeleteURL, { ids: ids }, function (data) {
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

			admin.putTempData('eam-maintain-plan-form-data-form-action', "",true);
			if (layEvent === 'edit') { // 修改
				top.layer.load(2);
				top.layer.load(2);
				admin.post(getByIdURL, { id : data.id }, function (data) {
					top.layer.closeAll('loading');
					if(data.success) {
						admin.putTempData('eam-maintain-plan-form-data-form-action', "edit",true);
						showEditForm(data.data);
					} else {
						 fox.showMessage(data);
					}
				});
			} else if (layEvent === 'view') { // 查看
				top.layer.load(2);
				admin.post(getByIdURL, { id : data.id }, function (data) {
					top.layer.closeAll('loading');
					if(data.success) {
						admin.putTempData('eam-maintain-plan-form-data-form-action', "view",true);
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

				top.layer.confirm(fox.translate('确定删除此'+'保养方案'+'吗？'), function (i) {
					top.layer.close(i);
					admin.post(deleteURL, { id : data.id }, function (data) {
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
			else if (layEvent === 'start') { // 启动
				window.pageExt.list.start(data,this);
			}
			else if (layEvent === 'stop') { // 停用
				window.pageExt.list.stop(data,this);
			}
			else if (layEvent === 'execute') { // 创建任务
				window.pageExt.list.execute(data,this);
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
		var action=admin.getTempData('eam-maintain-plan-form-data-form-action');
		var queryString="";
		if(data && data.id) queryString='id=' + data.id;
		if(window.pageExt.list.makeFormQueryString) {
			queryString=window.pageExt.list.makeFormQueryString(data,queryString,action);
		}
		admin.putTempData('eam-maintain-plan-form-data', data);
		var area=admin.getTempData('eam-maintain-plan-form-area');
		var height= (area && area.height) ? area.height : ($(window).height()*0.6);
		var top= (area && area.top) ? area.top : (($(window).height()-height)/2);
		var title = fox.translate('保养方案');
		if(action=="create") title=fox.translate('添加','','cmp:table')+title;
		else if(action=="edit") title=fox.translate('修改','','cmp:table')+title;
		else if(action=="view") title=fox.translate('查看','','cmp:table')+title;

		admin.popupCenter({
			title: title,
			resize: false,
			offset: [top,null],
			area: ["95%",height+"px"],
			type: 2,
			id:"eam-maintain-plan-form-data-win",
			content: '/business/eam/maintain_plan/maintain_plan_form.html' + (queryString?("?"+queryString):""),
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