/**
 * 数据库 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2023-01-11 11:52:08
 */


function ListPage() {

	var settings,admin,form,table,layer,util,fox,upload,xmSelect;
	
	//模块基础路径
	const moduleURL="/service-ops/ops-db-info";
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
					,{ field: 'id', align:"left",fixed:false,  hide:true, sort: true  , title: fox.translate('主键') , templet: function (d) { return templet('id',d.id,d);}  }
					,{ field: 'hostId', align:"left",fixed:false,  hide:false, sort: false  , title: fox.translate('主机名称'), templet: function (d) { return templet('hostId' ,fox.joinLabel(d.host,"hostName",',','','hostId'),d);}}
					,{ field: 'typeId', align:"left",fixed:false,  hide:false, sort: false  , title: fox.translate('库类型'), templet: function (d) { return templet('typeId' ,fox.joinLabel(d.type,"name",',','','typeId'),d);}}
					,{ field: 'name', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('数据库名') , templet: function (d) { return templet('name',d.name,d);}  }
					,{ field: 'status', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('数据库状态'), templet:function (d){ return templet('status',fox.getEnumText(RADIO_STATUS_DATA,d.status,'','status'),d);}}
					,{ field: 'backupStatus', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('备份状态'), templet:function (d){ return templet('backupStatus',fox.getEnumText(RADIO_BACKUPSTATUS_DATA,d.backupStatus,'','backupStatus'),d);}}
					,{ field: 'deployMode', align:"left",fixed:false,  hide:false, sort: false  , title: fox.translate('部署模式'), templet: function (d) { return templet('deployMode' ,fox.joinLabel(d.deployModeDict,"label",',','','deployMode'),d);}}
					,{ field: 'dbSize', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('大小(M)') , templet: function (d) { return templet('dbSize',d.dbSize,d);}  }
					,{ field: 'logMethod', align:"left", fixed:false, hide:false, sort: true  , title: fox.translate('日志模式'), templet:function (d){ return templet('logMethod',fox.getDictText(RADIO_LOGMETHOD_DATA,d.logMethod,'','logMethod'),d);}}
					,{ field: 'adminUserList', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('管理员账户') , templet: function (d) { return templet('adminUserList',d.adminUserList,d);}  }
					,{ field: 'appUserList', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('应用账户') , templet: function (d) { return templet('appUserList',d.appUserList,d);}  }
					,{ field: 'opsUserList', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('运维账户') , templet: function (d) { return templet('opsUserList',d.opsUserList,d);}  }
					,{ field: 'otherUserList', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('其他账户') , templet: function (d) { return templet('otherUserList',d.otherUserList,d);}  }
					,{ field: 'userUseInfo', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('使用情况') , templet: function (d) { return templet('userUseInfo',d.userUseInfo,d);}  }
					,{ field: 'voucherStr', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('凭证信息') , templet: function (d) { return templet('voucherStr',d.voucherStr,d);}  }
					,{ field: 'dbPort', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('服务端口') , templet: function (d) { return templet('dbPort',d.dbPort,d);}  }
					,{ field: 'backupStrategy', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('本地备份策略') , templet: function (d) { return templet('backupStrategy',d.backupStrategy,d);}  }
					,{ field: 'toolStrategy', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('工具备份策略') , templet: function (d) { return templet('toolStrategy',d.toolStrategy,d);}  }
					,{ field: 'disasterRecoveryStrategy', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('灾备策略') , templet: function (d) { return templet('disasterRecoveryStrategy',d.disasterRecoveryStrategy,d);}  }
					,{ field: 'clearStrategy', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('清理策略') , templet: function (d) { return templet('clearStrategy',d.clearStrategy,d);}  }
					,{ field: 'backupInfo', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('备份备注') , templet: function (d) { return templet('backupInfo',d.backupInfo,d);}  }
					,{ field: 'notes', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('备注') , templet: function (d) { return templet('notes',d.notes,d);}  }
					,{ field: 'createTime', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('创建时间') ,templet: function (d) { return templet('createTime',fox.dateFormat(d.createTime,"yyyy-MM-dd HH:mm:ss"),d); }  }
					,{ field: 'resHostIp', align:"",fixed:false,  hide:false, sort: false  , title: fox.translate('IP') , templet: function (d) { return templet('resHostIp',fox.getProperty(d,["host","hostIp"],0,'','resHostIp'),d);} }
					,{ field: 'labelIds', align:"",fixed:false,  hide:false, sort: false  , title: fox.translate('标签'), templet: function (d) { return templet('labelIds' ,fox.joinLabel(d.labelList,"label",',','','labelIds'),d);}}
					,{ field: 'dataLocIds', align:"",fixed:false,  hide:false, sort: false  , title: fox.translate('数据存放'), templet: function (d) { return templet('dataLocIds' ,fox.joinLabel(d.dataLocData,"label",',','','dataLocIds'),d);}}
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
		value.name={ inputType:"button",value: $("#name").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.status={ inputType:"radio_box", value: getSelectedValue("#status","value"), label:getSelectedValue("#status","nameStr") };
		value.backupStatus={ inputType:"radio_box", value: getSelectedValue("#backupStatus","value"), label:getSelectedValue("#backupStatus","nameStr") };
		value.deployMode={ inputType:"select_box", value: getSelectedValue("#deployMode","value") ,fillBy:["deployModeDict"]  , label:getSelectedValue("#deployMode","nameStr") };
		value.notes={ inputType:"button",value: $("#notes").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.createTime={ inputType:"date_input", begin: $("#createTime-begin").val(), end: $("#createTime-end").val() ,matchType:"auto" };
		value.labelIds={ inputType:"select_box", value: getSelectedValue("#labelIds","value") ,fillBy:["labelList"]  , label:getSelectedValue("#labelIds","nameStr") };
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
					opts.push({data:data[i],name:data[i].text,value:data[i].code});
				}
				return opts;
			}
		});
		//渲染 backupStatus 搜索框
		fox.renderSelectBox({
			el: "backupStatus",
			size: "small",
			radio: true,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("backupStatus",data.arr,data.change,data.isAdd);
				},1);
			},
			//toolbar: {show:true,showIcon:true,list:["CLEAR","REVERSE"]},
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
		//渲染 deployMode 下拉字段
		fox.renderSelectBox({
			el: "deployMode",
			radio: true,
			size: "small",
			filterable: true,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("deployMode",data.arr,data.change,data.isAdd);
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
					opts.push({data:data[i],name:data[i].label,value:data[i].code});
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
		//渲染 labelIds 下拉字段
		fox.renderSelectBox({
			el: "labelIds",
			radio: true,
			size: "small",
			filterable: false,
			on: function(data){
				setTimeout(function () {
					window.pageExt.list.onSelectBoxChanged && window.pageExt.list.onSelectBoxChanged("labelIds",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			transform: function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					if(!data[i]) continue;
					opts.push({data:data[i],name:data[i].label,value:data[i].code});
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
					admin.putTempData('ops-db-info-form-data', {});
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
			admin.putTempData('ops-db-info-form-data-form-action', "create",true);
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
				top.layer.msg(fox.translate('请选择需要删除的'+'数据库'+"!"));
            	return;
            }
            //调用批量删除接口
			top.layer.confirm(fox.translate('确定删除已选中的'+'数据库'+'吗？'), function (i) {
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

			admin.putTempData('ops-db-info-form-data-form-action', "",true);
			if (layEvent === 'edit') { // 修改
				admin.post(moduleURL+"/get-by-id", { id : data.id }, function (data) {
					if(data.success) {
						admin.putTempData('ops-db-info-form-data-form-action', "edit",true);
						showEditForm(data.data);
					} else {
						 fox.showMessage(data);
					}
				});
			} else if (layEvent === 'view') { // 查看
				admin.post(moduleURL+"/get-by-id", { id : data.id }, function (data) {
					if(data.success) {
						admin.putTempData('ops-db-info-form-data-form-action', "view",true);
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

				top.layer.confirm(fox.translate('确定删除此'+'数据库'+'吗？'), function (i) {
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
			else if (layEvent === 'open-backup-window') { // 备份记录
				window.pageExt.list.openBackupWindow(data,this);
			}
			else if (layEvent === 'box-window') { // 密文箱
				window.pageExt.list.boxWindow(data,this);
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
		var action=admin.getTempData('ops-db-info-form-data-form-action');
		var queryString="";
		if(data && data.id) queryString='id=' + data.id;
		if(window.pageExt.list.makeFormQueryString) {
			queryString=window.pageExt.list.makeFormQueryString(data,queryString,action);
		}
		admin.putTempData('ops-db-info-form-data', data);
		var area=admin.getTempData('ops-db-info-form-area');
		var height= (area && area.height) ? area.height : ($(window).height()*0.6);
		var top= (area && area.top) ? area.top : (($(window).height()-height)/2);
		var title = fox.translate('数据库');
		if(action=="create") title=fox.translate('添加','','cmp:table')+title;
		else if(action=="edit") title=fox.translate('修改','','cmp:table')+title;
		else if(action=="view") title=fox.translate('查看','','cmp:table')+title;

		admin.popupCenter({
			title: title,
			resize: false,
			offset: [top,null],
			area: ["80%",height+"px"],
			type: 2,
			id:"ops-db-info-form-data-win",
			content: '/business/ops/db_info/db_info_form.html' + (queryString?("?"+queryString):""),
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