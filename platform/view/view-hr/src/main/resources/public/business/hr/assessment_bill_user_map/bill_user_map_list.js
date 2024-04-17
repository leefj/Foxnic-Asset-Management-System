/**
 * 人员映射 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2024-03-10 21:50:16
 */


function ListPage() {

	var settings,admin,form,table,layer,util,fox,upload,xmSelect;

	//模块基础路径
	const moduleURL="/service-hr/hr-assessment-bill-user-map";
	const queryURL=moduleURL+'/query-paged-list';
	const deleteURL=moduleURL+'/delete';
	const batchDeleteURL=moduleURL+'/delete-by-ids';
	const getByIdURL=moduleURL+'/get-by-id';
	//
	var dataTable=null;
	var sort=null;

	var searchContent_orgId;
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
				skin: 'line' //行边框风格
				,even: true //开启隔行背景
				,size: 'sm' ,//小尺寸的表格
				method:"post",
				// ,autoColumnWidth: {
				// 	init: true
				// },
				cellMinWidth:100,
			//	cellMinWidth:'auto',
				toolbar: '#toolbarTemplate',
				defaultToolbar: ['filter', 'print',{title: fox.translate('刷新数据','','cmp:table'),layEvent: 'refresh-data',icon: 'layui-icon-refresh-3'}],
				url: queryURL,
				height: 'full-'+(h+28),
				limit: 50,
				where: ps,
				cols: [[
					{ fixed: 'left',type: 'numbers',rowspan:2 },
					{ fixed: 'left',type:'checkbox',rowspan:2 }
					,{ field: 'id',rowspan:2 ,align:"center",fixed:false,  hide:true, sort: false  , title: fox.translate('主键') , templet: function (d) { return templet('id',d.id,d);}  }
					,{ field: 'status',rowspan:2 ,align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('状态'), templet:function (d){ return templet('status',fox.getEnumText(RADIO_STATUS_DATA,d.status,'','status'),d);} ,minWidth: 80,width:80,}
					,{ field: 'assesseeId',rowspan:2 , align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('被考核人') , templet: function (d) { return templet('assesseeId',fox.getProperty(d,["assesseeUser","name"],0,'','assesseeId'),d);},minWidth: 80,width:100, }
					,{ field: 'aaa',colspan:2, align:"center" , title: fox.translate('自评信息') }
					,{ field: 'bbb',colspan:3, align:"center" , title: fox.translate('互评信息') }
					,{ field: 'ccc',colspan:3, align:"center" , title: fox.translate('直接领导考评') }
					,{ field: 'ddd',colspan:3, align:"center" , title: fox.translate('上上级领导考评') }
					,{ field: 'hrUserId',rowspan:2, align:"left",fixed:false,  hide:false, sort: true  , width: 160, title: fox.translate('HR复核人') , templet: function (d) { return templet('hrUserId',fox.getProperty(d,["hrUser","name"],0,'','hrUserId'),d);} }
					,{ field: 'isConfirm', rowspan:2,align:"left",fixed:false,  hide:false, sort: true  ,  width: 160,title: fox.translate('复核情况'), templet:function (d){ return templet('isConfirm',fox.getEnumText(RADIO_ISCONFIRM_DATA,d.isConfirm,'','isConfirm'),d);}}
					,{ field: fox.translate('空白列','','cmp:table'),rowspan:2, align:"center", hide:false, sort: false, title: "",minWidth:8,width:8,unresize:true}
					,{ field: 'row-ops', rowspan:2,fixed: 'right', align: 'center', toolbar: '#tableOperationTemplate', title: fox.translate('操作','','cmp:table'), width: 160}
				],
					[
						{ field: 'selfScoreValue', align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('自评分数') , templet: function (d) { return templet('selfScoreValue',fox.getProperty(d,["selfScorePaper","scoreValue"],0,'','selfScoreValue'),d);} }
						,{ field: 'incompleteSelfPaperCount', align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('自评未完成数') , templet: function (d) { return templet('incompleteSelfPaperCount',d.incompleteSelfPaperCount,d);}  }
						,{ field: 'sameUserAvgScoreValue', align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('互评平均评分') , templet: function (d) { return templet('sameUserAvgScoreValue',d.sameUserAvgScoreValue,d);}  }
						,{ field: 'incompleteSamePaperCount', align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('未完成数') , templet: function (d) { return templet('incompleteSamePaperCount',d.incompleteSamePaperCount,d);}  }
						 ,{ field: 'sameUserIds', align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('互评人员') , templet: function (d) { return templet('sameUserIds',fox.getProperty(d,["sameUserList","name"],0,'','sameUserIds'),d);} }
						,{ field: 'leaderScoreValue', align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('评分') , templet: function (d) { return templet('leaderScoreValue',fox.getProperty(d,["leaderScorePaper","scoreValue"],0,'','leaderScoreValue'),d);} }
						,{ field: 'incompleteLeaderPaperCount', align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('未完成数') , templet: function (d) { return templet('incompleteLeaderPaperCount',d.incompleteLeaderPaperCount,d);}  }
						 ,{ field: 'leaderId', align:"center",fixed:false,  hide:false, sort: true  , title: fox.translate('考评人员') , templet: function (d) { return templet('leaderId',fox.getProperty(d,["leaderUser","name"],0,'','leaderId'),d);} }
						,{ field: 'secondLeaderScoreValue', align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('评分') , templet: function (d) { return templet('secondLeaderScoreValue',fox.getProperty(d,["secondLeaderScorePaper","scoreValue"],0,'','secondLeaderScoreValue'),d);} }
						,{ field: 'incompleteSecondLeaderPaperCount', align:"center",fixed:false,  hide:false, sort: false  , title: fox.translate('未完成数') , templet: function (d) { return templet('incompleteSecondLeaderPaperCount',d.incompleteSecondLeaderPaperCount,d);}  }
						,{ field: 'secondLeaderId', align:"center",fixed:false,  hide:false, sort: true  , title: fox.translate('考评人员') , templet: function (d) { return templet('secondLeaderId',fox.getProperty(d,["secondLeaderUser","name"],0,'','secondLeaderId'),d);} }
						// ,{ field: 'leaderUserIdRel', align:"",fixed:false,  hide:false, sort: false  , title: fox.translate('直属领导【参考】') , templet: function (d) { return templet('leaderUserIdRel',fox.getProperty(d,["leaderUserRel","name"],0,'','leaderUserIdRel'),d);} }
						// ,{ field: 'secondLeaderUserIdRel', align:"",fixed:false,  hide:false, sort: false  , title: fox.translate('上上级领导【参考】') , templet: function (d) { return templet('secondLeaderUserIdRel',fox.getProperty(d,["secondLeaderUserRel","name"],0,'','secondLeaderUserIdRel'),d);} }
						// ,{ field: 'sameUserIdsRel', align:"",fixed:false,  hide:false, sort: false  , title: fox.translate('人员互评【参考】') , templet: function (d) { return templet('sameUserIdsRel',fox.getProperty(d,["sameUserListRel","name"],0,'','sameUserIdsRel'),d);} }
					]
				],

				done: function (data) {
					lockSwitchInputs();
					window.pageExt.list.afterQuery && window.pageExt.list.afterQuery(data);
				}
				,parseData: function(res){ //res 即为原始返回的数据
					console.log("res",res);
					return {
						"code": res.code, //解析接口状态
						"msg": res.message, //解析提示文本
						"count": res.totalRowCount, //解析数据长度
						"data": res.data.list //解析数据列表
					};
				},
				footer : {
					exportExcel : false ,
					importExcel : false
				}
			};
			window.pageExt.list.beforeTableRender && window.pageExt.list.beforeTableRender(tableConfig);
			//dataTable=table.renderTable(tableConfig);
			dataTable=table.render(tableConfig);
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
		value.status={ inputType:"radio_box", value: getSelectedValue("#status","value"), label:getSelectedValue("#status","nameStr") };
		value.assesseeId={ inputType:"button",value: $("#assesseeId").val(),fillBy:["assesseeUser","name"] ,label:$("#assesseeId-button").text() };
		value.leaderId={ inputType:"button",value: $("#leaderId").val(),fillBy:["leaderUser","name"] ,label:$("#leaderId-button").text() };
		value.secondLeaderId={ inputType:"button",value: $("#secondLeaderId").val(),fillBy:["secondLeaderUser","name"] ,label:$("#secondLeaderId-button").text() };
		value.hrUserId={ inputType:"button",value: $("#hrUserId").val(),fillBy:["hrUser","name"] ,label:$("#hrUserId-button").text() };
		value.result={ inputType:"radio_box", value: getSelectedValue("#result","value"), label:getSelectedValue("#result","nameStr") };
		value.createTime={ inputType:"date_input", begin: $("#createTime-begin").val(), end: $("#createTime-end").val() ,matchType:"auto" };
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


		if(searchContent_orgId){
			if(value.sOrgId){
				delete value.sOrgId ;
			}
			ps.sOrgId=searchContent_orgId;
		}

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

		//渲染 status 搜索框
		fox.renderSelectBox({
			el: "status",
			size: "small",
			radio: true,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("status",data.arr,data.change,data.isAdd);
				},1);
			},
			//toolbar: {show:true,showIcon:true,list:["CLEAR","REVERSE"]},
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
		//渲染 result 搜索框
		fox.renderSelectBox({
			el: "result",
			size: "small",
			radio: true,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("result",data.arr,data.change,data.isAdd);
				},1);
			},
			//toolbar: {show:true,showIcon:true,list:["CLEAR","REVERSE"]},
			transform:function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					if(window.pageExt.list.selectBoxDataTransform) {
						opts.push(window.pageExt.list.selectBoxDataTransform("result",{data:data[i],name:data[i].text,value:data[i].code},data[i],data,i));
					} else {
						opts.push({data:data[i],name:data[i].text,value:data[i].code});
					}
				}
				return opts;
			}
		});
		laydate.render({
			elem: '#createTime-begin',
			trigger:"click",
			done: function(value, date, endDate) {
				setTimeout(function () {
					window.pageExt.list.onDatePickerChanged && window.pageExt.list.onDatePickerChanged("createTime",value, date, endDate);
				},1);
			}
		});
		laydate.render({
			elem: '#createTime-end',
			trigger:"click",
			done: function(value, date, endDate) {
				setTimeout(function () {
					window.pageExt.list.onDatePickerChanged && window.pageExt.list.onDatePickerChanged("createTime",value, date, endDate);
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
			fox.switchSearchRow(2,function (ex){
				if(ex=="1") {
					$('#search-button-advance span').text("关闭");
				} else {
					$('#search-button-advance span').text("更多");
				}
			});
		});

		// 请选择人员对话框
		$("#assesseeId-button").click(function(){
			var assesseeIdDialogOptions={
				field:"assesseeId",
				inputEl:$("#assesseeId"),
				buttonEl:$(this),
				single:false,
				//限制浏览的范围，指定根节点 id 或 code ，优先匹配ID
				root: "",
				targetType:"emp",
				prepose:function(param){ return window.pageExt.list.beforeDialog && window.pageExt.list.beforeDialog(param);},
				callback:function(param,result){ window.pageExt.list.afterDialog && window.pageExt.list.afterDialog(param,result);}
			};
			fox.chooseEmployee(assesseeIdDialogOptions);
		});
		// 请选择人员对话框
		$("#leaderId-button").click(function(){
			var leaderIdDialogOptions={
				field:"leaderId",
				inputEl:$("#leaderId"),
				buttonEl:$(this),
				single:false,
				//限制浏览的范围，指定根节点 id 或 code ，优先匹配ID
				root: "",
				targetType:"emp",
				prepose:function(param){ return window.pageExt.list.beforeDialog && window.pageExt.list.beforeDialog(param);},
				callback:function(param,result){ window.pageExt.list.afterDialog && window.pageExt.list.afterDialog(param,result);}
			};
			fox.chooseEmployee(leaderIdDialogOptions);
		});
		// 请选择人员对话框
		$("#secondLeaderId-button").click(function(){
			var secondLeaderIdDialogOptions={
				field:"secondLeaderId",
				inputEl:$("#secondLeaderId"),
				buttonEl:$(this),
				single:false,
				//限制浏览的范围，指定根节点 id 或 code ，优先匹配ID
				root: "",
				targetType:"emp",
				prepose:function(param){ return window.pageExt.list.beforeDialog && window.pageExt.list.beforeDialog(param);},
				callback:function(param,result){ window.pageExt.list.afterDialog && window.pageExt.list.afterDialog(param,result);}
			};
			fox.chooseEmployee(secondLeaderIdDialogOptions);
		});
		// 请选择人员对话框
		$("#hrUserId-button").click(function(){
			var hrUserIdDialogOptions={
				field:"hrUserId",
				inputEl:$("#hrUserId"),
				buttonEl:$(this),
				single:false,
				//限制浏览的范围，指定根节点 id 或 code ，优先匹配ID
				root: "",
				targetType:"emp",
				prepose:function(param){ return window.pageExt.list.beforeDialog && window.pageExt.list.beforeDialog(param);},
				callback:function(param,result){ window.pageExt.list.afterDialog && window.pageExt.list.afterDialog(param,result);}
			};
			fox.chooseEmployee(hrUserIdDialogOptions);
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
					admin.putTempData('hr-assessment-bill-user-map-form-data', {});
					openCreateFrom();
					break;
				case 'batch-del':
					batchDelete(selected);
					break;
				case 'tool-append-data':
					window.pageExt.list.appendData && window.pageExt.list.appendData(selected,obj);
					break;
				case 'tool-reset-data':
					window.pageExt.list.resetData && window.pageExt.list.resetData(selected,obj);
					break;
				case 'tool-bill-task-down':
					window.pageExt.list.BillTaskDown && window.pageExt.list.BillTaskDown(selected,obj);
					break;
				case 'tool-create-paper':
					window.pageExt.list.createPaper && window.pageExt.list.createPaper(selected,obj);
					break;
				case 'tool-bill-task-lit':
					window.pageExt.list.BillTaskLit && window.pageExt.list.BillTaskLit(selected,obj);
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
			admin.putTempData('hr-assessment-bill-user-map-form-data-form-action', "create",true);
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
				top.layer.msg(fox.translate('请选择需要删除的'+'人员映射'+"!"));
				return;
			}
			//调用批量删除接口
			top.layer.confirm(fox.translate('确定删除已选中的'+'人员映射'+'吗？'), function (i) {
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


	function searchOrg(orgId){
		searchContent_orgId=orgId;
		refreshTableData()
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

			admin.putTempData('hr-assessment-bill-user-map-form-data-form-action', "",true);
			if (layEvent === 'edit') { // 修改
				top.layer.load(2);
				top.layer.load(2);
				admin.post(getByIdURL, { id : data.id }, function (data) {
					top.layer.closeAll('loading');
					if(data.success) {
						admin.putTempData('hr-assessment-bill-user-map-form-data-form-action', "edit",true);
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
						admin.putTempData('hr-assessment-bill-user-map-form-data-form-action', "view",true);
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

				top.layer.confirm(fox.translate('确定删除此'+'人员映射'+'吗？'), function (i) {
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
			else if (layEvent === 'bill-dtl') { // 考核人任务
				window.pageExt.list.BillDtl(data,this);
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
		var action=admin.getTempData('hr-assessment-bill-user-map-form-data-form-action');
		var queryString="";
		if(data && data.id) queryString='id=' + data.id;
		if(window.pageExt.list.makeFormQueryString) {
			queryString=window.pageExt.list.makeFormQueryString(data,queryString,action);
		}
		admin.putTempData('hr-assessment-bill-user-map-form-data', data);
		var area=admin.getTempData('hr-assessment-bill-user-map-form-area');
		var height= (area && area.height) ? area.height : ($(window).height()*0.6);
		var top= (area && area.top) ? area.top : (($(window).height()-height)/2);
		var title = fox.translate('人员映射');
		if(action=="create") title=fox.translate('添加','','cmp:table')+title;
		else if(action=="edit") title=fox.translate('修改','','cmp:table')+title;
		else if(action=="view") title=fox.translate('查看','','cmp:table')+title;

		admin.popupCenter({
			title: title,
			resize: false,
			offset: [top,null],
			area: ["75%",height+"px"],
			type: 2,
			id:"hr-assessment-bill-user-map-form-data-win",
			content: '/business/hr/assessment_bill_user_map/assessment_bill_user_map_form.html' + (queryString?("?"+queryString):""),
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
		searchOrg:searchOrg,
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