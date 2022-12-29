/**
 * 人员信息 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2022-12-29 15:01:37
 */


function ListPage() {

	var settings,admin,form,table,layer,util,fox,upload,xmSelect;
	
	//模块基础路径
	const moduleURL="/service-hr/hr-person";
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
					,{ field: 'jobNumber', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('工号') , templet: function (d) { return templet('jobNumber',d.jobNumber,d);}  }
					,{ field: 'name', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('姓名') , templet: function (d) { return templet('name',d.name,d);}  }
					,{ field: 'sexCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('性别'), templet: function (d) { return templet('sexCode' ,fox.joinLabel(d.sexDict,"label",',','','sexCode'),d);}}
					,{ field: 'birthday', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('出生日期') ,templet: function (d) { return templet('birthday',fox.dateFormat(d.birthday,"yyyy-MM-dd"),d); }  }
					,{ field: 'nativePlaceCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('籍贯') , templet: function (d) { return templet('nativePlaceCode',d.nativePlaceCode,d);}  }
					,{ field: 'nationCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('民族') , templet: function (d) { return templet('nationCode',d.nationCode,d);}  }
					,{ field: 'maritalStatus', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('婚姻状况'), templet: function (d) { return templet('maritalStatus' ,fox.joinLabel(d.maritalStatusDict,"label",',','','maritalStatus'),d);}}
					,{ field: 'employeeStatus', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('员工状态') , templet: function (d) { return templet('employeeStatus',d.employeeStatus,d);}  }
					,{ field: 'employeeTypeCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('人员类型'), templet: function (d) { return templet('employeeTypeCode' ,fox.joinLabel(d.employeeOwnerTypeDict,"label",',','','employeeTypeCode'),d);}}
					,{ field: 'identityCard', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('身份证') , templet: function (d) { return templet('identityCard',d.identityCard,d);}  }
					,{ field: 'contactInformation', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('联系方式') , templet: function (d) { return templet('contactInformation',d.contactInformation,d);}  }
					,{ field: 'email', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('电子邮件') , templet: function (d) { return templet('email',d.email,d);}  }
					,{ field: 'emergencyContact', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('紧急联系人') , templet: function (d) { return templet('emergencyContact',d.emergencyContact,d);}  }
					,{ field: 'emergencyContactNo', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('紧急人电话') , templet: function (d) { return templet('emergencyContactNo',d.emergencyContactNo,d);}  }
					,{ field: 'homeAddress', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('家庭地址') , templet: function (d) { return templet('homeAddress',d.homeAddress,d);}  }
					,{ field: 'weixinId', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('微信号') , templet: function (d) { return templet('weixinId',d.weixinId,d);}  }
					,{ field: 'educationCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('学历') , templet: function (d) { return templet('educationCode',d.educationCode,d);}  }
					,{ field: 'graduationSchool', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('毕业院校') , templet: function (d) { return templet('graduationSchool',d.graduationSchool,d);}  }
					,{ field: 'major', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('所学专业') , templet: function (d) { return templet('major',d.major,d);}  }
					,{ field: 'graduationDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('毕业时间') ,templet: function (d) { return templet('graduationDate',fox.dateFormat(d.graduationDate,"yyyy-MM-dd HH:mm:ss"),d); }  }
					,{ field: 'foreignLanguage', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('所学外语') , templet: function (d) { return templet('foreignLanguage',d.foreignLanguage,d);}  }
					,{ field: 'foreignLanguageLevel', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('外语证书') , templet: function (d) { return templet('foreignLanguageLevel',d.foreignLanguageLevel,d);}  }
					,{ field: 'computerAbility', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('计算机能力') , templet: function (d) { return templet('computerAbility',d.computerAbility,d);}  }
					,{ field: 'computerLevel', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('计算机等级') , templet: function (d) { return templet('computerLevel',d.computerLevel,d);}  }
					,{ field: 'politicCountenanceCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('政治面貌') , templet: function (d) { return templet('politicCountenanceCode',d.politicCountenanceCode,d);}  }
					,{ field: 'joinPartDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('入党时间') ,templet: function (d) { return templet('joinPartDate',fox.dateFormat(d.joinPartDate,"yyyy-MM-dd"),d); }  }
					,{ field: 'bloodType', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('血型'), templet: function (d) { return templet('bloodType' ,fox.joinLabel(d.bloodTypeDict,"label",',','','bloodType'),d);}}
					,{ field: 'bodyHeight', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('身高') , templet: function (d) { return templet('bodyHeight',d.bodyHeight,d);}  }
					,{ field: 'bodyWeight', align:"right",fixed:false,  hide:false, sort: true  , title: fox.translate('体重') , templet: function (d) { return templet('bodyWeight',d.bodyWeight,d);}  }
					,{ field: 'employmentDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('入职日期') ,templet: function (d) { return templet('employmentDate',fox.dateFormat(d.employmentDate,"yyyy-MM-dd"),d); }  }
					,{ field: 'employmentConfirmDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('转正日期') ,templet: function (d) { return templet('employmentConfirmDate',fox.dateFormat(d.employmentConfirmDate,"yyyy-MM-dd"),d); }  }
					,{ field: 'firstEmploymentDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('初次日期') ,templet: function (d) { return templet('firstEmploymentDate',fox.dateFormat(d.firstEmploymentDate,"yyyy-MM-dd"),d); }  }
					,{ field: 'firstWorkDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('参加工作时间') ,templet: function (d) { return templet('firstWorkDate',fox.dateFormat(d.firstWorkDate,"yyyy-MM-dd"),d); }  }
					,{ field: 'orgId', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('员工部门') , templet: function (d) { return templet('orgId',d.orgId,d);}  }
					,{ field: 'positionCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('员工岗位'), templet: function (d) { return templet('positionCode' ,fox.joinLabel(d.position,"name",',','','positionCode'),d);}}
					,{ field: 'employeeTitleCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('员工职称'), templet: function (d) { return templet('employeeTitleCode' ,fox.joinLabel(d.professionalLevel,"name",',','','employeeTitleCode'),d);}}
					,{ field: 'rankCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('员工职级'), templet: function (d) { return templet('rankCode' ,fox.joinLabel(d.rank,"code",',','','rankCode'),d);}}
					,{ field: 'leaveDate', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('离职日期') ,templet: function (d) { return templet('leaveDate',fox.dateFormat(d.leaveDate,"yyyy-MM-dd"),d); }  }
					,{ field: 'leaveReson', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('离职原因') , templet: function (d) { return templet('leaveReson',d.leaveReson,d);}  }
					,{ field: 'payrollCardBankCode', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('工资卡开户行') , templet: function (d) { return templet('payrollCardBankCode',d.payrollCardBankCode,d);}  }
					,{ field: 'payrollCard', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('工资卡帐号') , templet: function (d) { return templet('payrollCard',d.payrollCard,d);}  }
					,{ field: 'employeeId', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('员工') , templet: function (d) { return templet('employeeId',fox.getProperty(d,["employee","name"],0,'','employeeId'),d);} }
					,{ field: 'note', align:"left",fixed:false,  hide:false, sort: true  , title: fox.translate('备注') , templet: function (d) { return templet('note',d.note,d);}  }
					,{ field: 'createTime', align:"right", fixed:false, hide:false, sort: true   ,title: fox.translate('创建时间') ,templet: function (d) { return templet('createTime',fox.dateFormat(d.createTime,"yyyy-MM-dd HH:mm:ss"),d); }  }
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
		value.jobNumber={ inputType:"button",value: $("#jobNumber").val()};
		value.name={ inputType:"button",value: $("#name").val() ,fuzzy: true,splitValue:false,valuePrefix:"",valueSuffix:"" };
		value.employeeStatus={ inputType:"button",value: $("#employeeStatus").val()};
		value.identityCard={ inputType:"button",value: $("#identityCard").val()};
		value.employmentDate={ inputType:"date_input", begin: $("#employmentDate-begin").val(), end: $("#employmentDate-end").val() ,matchType:"auto" };
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

		laydate.render({
			elem: '#employmentDate-begin',
			trigger:"click",
			done: function(value, date, endDate) {
				setTimeout(function () {
					window.pageExt.list.onDatePickerChanged && window.pageExt.list.onDatePickerChanged("employmentDate",value, date, endDate);
				},1);
			}
		});
		laydate.render({
			elem: '#employmentDate-end',
			trigger:"click",
			done: function(value, date, endDate) {
				setTimeout(function () {
					window.pageExt.list.onDatePickerChanged && window.pageExt.list.onDatePickerChanged("employmentDate",value, date, endDate);
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
					admin.putTempData('hr-person-form-data', {});
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
			admin.putTempData('hr-person-form-data-form-action', "create",true);
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
				top.layer.msg(fox.translate('请选择需要删除的'+'人员信息'+"!"));
            	return;
            }
            //调用批量删除接口
			top.layer.confirm(fox.translate('确定删除已选中的'+'人员信息'+'吗？'), function (i) {
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

			admin.putTempData('hr-person-form-data-form-action', "",true);
			if (layEvent === 'edit') { // 修改
				admin.post(moduleURL+"/get-by-id", { id : data.id }, function (data) {
					if(data.success) {
						admin.putTempData('hr-person-form-data-form-action', "edit",true);
						showEditForm(data.data);
					} else {
						 fox.showMessage(data);
					}
				});
			} else if (layEvent === 'view') { // 查看
				admin.post(moduleURL+"/get-by-id", { id : data.id }, function (data) {
					if(data.success) {
						admin.putTempData('hr-person-form-data-form-action', "view",true);
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

				top.layer.confirm(fox.translate('确定删除此'+'人员信息'+'吗？'), function (i) {
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
		var action=admin.getTempData('hr-person-form-data-form-action');
		var queryString="";
		if(data && data.id) queryString='id=' + data.id;
		if(window.pageExt.list.makeFormQueryString) {
			queryString=window.pageExt.list.makeFormQueryString(data,queryString,action);
		}
		admin.putTempData('hr-person-form-data', data);
		var area=admin.getTempData('hr-person-form-area');
		var height= (area && area.height) ? area.height : ($(window).height()*0.6);
		var top= (area && area.top) ? area.top : (($(window).height()-height)/2);
		var title = fox.translate('人员信息');
		if(action=="create") title=fox.translate('添加','','cmp:table')+title;
		else if(action=="edit") title=fox.translate('修改','','cmp:table')+title;
		else if(action=="view") title=fox.translate('查看','','cmp:table')+title;

		admin.popupCenter({
			title: title,
			resize: false,
			offset: [top,null],
			area: ["65%",height+"px"],
			type: 2,
			id:"hr-person-form-data-win",
			content: '/business/hr/person/person_form.html' + (queryString?("?"+queryString):""),
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