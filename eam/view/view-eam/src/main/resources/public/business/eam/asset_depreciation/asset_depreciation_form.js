/**
 * 折旧方案 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2022-05-03 14:39:48
 */

function FormPage() {

	var settings,admin,form,table,layer,util,fox,upload,xmSelect,foxup;
	const moduleURL="/service-eam/eam-asset-depreciation";
	// 表单执行操作类型：view，create，edit
	var action=null;
	var disableCreateNew=false;
	var disableModify=false;
	var dataBeforeEdit=null;
	var categorySelect;
	/**
      * 入口函数，初始化
      */
	this.init=function(layui) {
     	admin = layui.admin,settings = layui.settings,form = layui.form,upload = layui.upload,foxup=layui.foxnicUpload;
		laydate = layui.laydate,table = layui.table,layer = layui.layer,util = layui.util,fox = layui.foxnic,xmSelect = layui.xmSelect;

		action=admin.getTempData('eam-asset-depreciation-form-data-form-action');
		//如果没有修改和保存权限
		if( !admin.checkAuth(AUTH_PREFIX+":update") && !admin.checkAuth(AUTH_PREFIX+":save")) {
			disableModify=true;
		}
		if(action=="view") {
			disableModify=true;
		}

		if(window.pageExt.form.beforeInit) {
			window.pageExt.form.beforeInit(action,admin.getTempData('eam-asset-depreciation-form-data'));
		}

		//渲染表单组件
		renderFormFields();

		//填充表单数据
		fillFormData();

		//绑定提交事件
		bindButtonEvent();

		//调整窗口的高度与位置
		adjustPopup();
	}

	/**
	 * 自动调节窗口高度
	 * */
	var adjustPopupTask=-1;
	function adjustPopup() {
		if(window.pageExt.form.beforeAdjustPopup) {
			var doNext=window.pageExt.form.beforeAdjustPopup();
			if(!doNext) return;
		}

		clearTimeout(adjustPopupTask);
		var scroll=$(".form-container").attr("scroll");
		if(scroll=='yes') return;
		adjustPopupTask=setTimeout(function () {
			var body=$("body");
			var bodyHeight=body.height();
			var footerHeight=$(".model-form-footer").height();
			var area=admin.changePopupArea(null,bodyHeight+footerHeight,'eam-asset-depreciation-form-data-win');
			if(area==null) return;
			admin.putTempData('eam-asset-depreciation-form-area', area);
			window.adjustPopup=adjustPopup;
			if(area.tooHeigh) {
				var windowHeight=area.iframeHeight;
				var finalHeight=windowHeight-footerHeight-16;
				//console.log("windowHeight="+windowHeight+',bodyHeight='+bodyHeight+",footerHeight="+footerHeight+",finalHeight="+finalHeight);
				$(".form-container").css("display","");
				$(".form-container").css("overflow-y","scroll");
				$(".form-container").css("height",finalHeight+"px");
				$(".form-container").attr("scroll","yes");
			}
		},250);
	}

	/**
      * 渲染表单组件
      */
	function renderFormFields() {
		fox.renderFormInputs(form);

		//渲染 status 下拉字段
		fox.renderSelectBox({
			el: "status",
			radio: true,
			filterable: false,
			on: function(data){
				setTimeout(function () {
					window.pageExt.form.onSelectBoxChanged && window.pageExt.form.onSelectBoxChanged("status",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			transform:function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var defaultValues=[],defaultIndexs=[];
				if(action=="create") {
					defaultValues = "".split(",");
					defaultIndexs = "".split(",");
				}
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					opts.push({data:data[i],name:data[i].text,value:data[i].code,selected:(defaultValues.indexOf(data[i].code)!=-1 || defaultIndexs.indexOf(""+i)!=-1)});
				}
				return opts;
			}
		});
		//渲染 method 下拉字段
		fox.renderSelectBox({
			el: "method",
			radio: true,
			filterable: false,
			on: function(data){
				setTimeout(function () {
					window.pageExt.form.onSelectBoxChanged && window.pageExt.form.onSelectBoxChanged("method",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			transform:function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var defaultValues=[],defaultIndexs=[];
				if(action=="create") {
					defaultValues = "".split(",");
					defaultIndexs = "".split(",");
				}
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					opts.push({data:data[i],name:data[i].text,value:data[i].code,selected:(defaultValues.indexOf(data[i].code)!=-1 || defaultIndexs.indexOf(""+i)!=-1)});
				}
				return opts;
			}
		});
		//渲染 firstDepreciationDate 下拉字段
		fox.renderSelectBox({
			el: "firstDepreciationDate",
			radio: true,
			filterable: false,
			on: function(data){
				setTimeout(function () {
					window.pageExt.form.onSelectBoxChanged && window.pageExt.form.onSelectBoxChanged("firstDepreciationDate",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			transform:function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var defaultValues=[],defaultIndexs=[];
				if(action=="create") {
					defaultValues = "".split(",");
					defaultIndexs = "".split(",");
				}
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					opts.push({data:data[i],name:data[i].text,value:data[i].code,selected:(defaultValues.indexOf(data[i].code)!=-1 || defaultIndexs.indexOf(""+i)!=-1)});
				}
				return opts;
			}
		});
		//渲染 categoryIds 下拉字段
		// fox.renderSelectBox({
		// 	el: "categoryIds",
		// 	radio: false,
		// 	filterable: false,
		// 	on: function(data){
		// 		setTimeout(function () {
		// 			window.pageExt.form.onSelectBoxChanged && window.pageExt.form.onSelectBoxChanged("categoryIds",data.arr,data.change,data.isAdd);
		// 		},1);
		// 	},
		// 	//转换数据
		// 	transform: function(data) {
		// 		//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
		// 		var defaultValues=[],defaultIndexs=[];
		// 		if(action=="create") {
		// 			defaultValues = "".split(",");
		// 			defaultIndexs = "".split(",");
		// 		}
		// 		var opts=[];
		// 		if(!data) return opts;
		// 		for (var i = 0; i < data.length; i++) {
		// 			if(!data[i]) continue;
		// 			opts.push({data:data[i],name:data[i].name,value:data[i].id,selected:(defaultValues.indexOf(data[i].id)!=-1 || defaultIndexs.indexOf(""+i)!=-1)});
		// 		}
		// 		return opts;
		// 	}
		// });

		//渲染 categoryId 下拉字段
		categorySelect = xmSelect.render({
			el: '#categoryIds',
			prop: {
				name: 'name',
				value: 'id',
			},
			filterable: false,
			tree: {
				showFolderIcon: true,
				show: true,
				strict: false,
				expandedKeys: [],
			},
			//处理方式
			on: function(data){
				console.log(data);
			},
			//显示为text模式
			model: { label: { type: 'text' } },
			//单选模式
			radio: false,
			//选中关闭
			clickClose: false,
			height: '450px',
			data:ASSET_CATEGORY_DATA
		})

		//渲染 categoryId 下拉字段
		// categorySelect = xmSelect.render({
		// 	el: '#categoryIds',
		// 	prop: {
		// 		name: 'name',
		// 		value: 'id',
		// 	},
		// 	filterable: true,
		// 	tree: {
		// 		showFolderIcon: true,
		// 		show: true,
		// 		strict: true,
		// 		expandedKeys: [],
		// 	},
		// 	//处理方式
		// 	on: function(data){
		// 		console.log(data);
		// 	},
		// 	//显示为text模式
		// 	model: { label: { type: 'text' } },
		// 	//单选模式
		// 	radio: false,
		// 	//选中关闭
		// 	clickClose: false,
		// 	height: '450px',
		// 	data:ASSET_CATEGORY_DATA
		// })

	}

	/**
      * 填充表单数据
      */
	function fillFormData(formData) {
		if(!formData) {
			formData = admin.getTempData('eam-asset-depreciation-form-data');
		}

		window.pageExt.form.beforeDataFill && window.pageExt.form.beforeDataFill(formData);

		var hasData=true;
		//如果是新建
		if(!formData || !formData.id) {
			adjustPopup();
			hasData=false;
		}
		var fm=$('#data-form');
		if (hasData) {
			fm[0].reset();
			form.val('data-form', formData);






			//设置  状态 设置下拉框勾选
			fox.setSelectValue4Enum("#status",formData.status,SELECT_STATUS_DATA);
			//设置  折旧方式 设置下拉框勾选
			fox.setSelectValue4Enum("#method",formData.method,SELECT_METHOD_DATA);
			//设置  首次折旧时间 设置下拉框勾选
			fox.setSelectValue4Enum("#firstDepreciationDate",formData.firstDepreciationDate,SELECT_FIRSTDEPRECIATIONDATE_DATA);
			//设置  资产分类 设置下拉框勾选
			// fox.setSelectValue4QueryApi("#categoryIds",formData.category);

			//处理fillBy


			//处理fillBy
			setTimeout(function(){
				if(categorySelect){
					if(formData.category&&formData.categoryIds){
						categorySelect.setValue(formData.categoryIds.split(","));
					}
					//categorySelect.update({disabled:true})
				}
			},150)

			//
	     	fm.attr('method', 'POST');
	     	fox.fillDialogButtons();
	     	renderFormFields();

			window.pageExt.form.afterDataFill && window.pageExt.form.afterDataFill(formData);

		}

		//渐显效果
		fm.css("opacity","0.0");
        fm.css("display","");
        setTimeout(function (){
            fm.animate({
                opacity:'1.0'
            },100);
        },1);

        //禁用编辑
		if((hasData && disableModify) || (!hasData &&disableCreateNew)) {
			fox.lockForm($("#data-form"),true);
			$("#submit-button").hide();
			$("#cancel-button").css("margin-right","15px")
		} else {
			$("#submit-button").show();
			$("#cancel-button").css("margin-right","0px")
		}

		//调用 iframe 加载过程
		var formIfrs=$(".form-iframe");
		for (var i = 0; i < formIfrs.length; i++) {
			var jsFn=$(formIfrs[i]).attr("js-fn");
			if(window.pageExt.form){
				jsFn=window.pageExt.form[jsFn];
				jsFn && jsFn($(formIfrs[i]),$(formIfrs[i])[0].contentWindow,formData);
			}
		}

		dataBeforeEdit=getFormData();

	}

	function getFormData() {
		var data=form.val("data-form");



		//获取 状态 下拉框的值
		data["status"]=fox.getSelectedValue("status",false);
		//获取 折旧方式 下拉框的值
		data["method"]=fox.getSelectedValue("method",false);
		//获取 首次折旧时间 下拉框的值
		data["firstDepreciationDate"]=fox.getSelectedValue("firstDepreciationDate",false);
		//获取 资产分类 下拉框的值
		//data["categoryIds"]=fox.getSelectedValue("categoryIds",true);
		data["categoryIds"]=categorySelect.getValue('valueStr');

		return data;
	}

	function verifyForm(data) {
		return fox.formVerify("data-form",data,VALIDATE_CONFIG)
	}

	function saveForm(param) {
		param.dirtyFields=fox.compareDirtyFields(dataBeforeEdit,param);
		var api=moduleURL+"/"+(param.id?"update":"insert");
		admin.post(api, param, function (data) {
			if (data.success) {
				var doNext=true;
				if(window.pageExt.form.betweenFormSubmitAndClose) {
					doNext=window.pageExt.form.betweenFormSubmitAndClose(param,data);
				}
				if(doNext) {
					admin.finishPopupCenterById('eam-asset-depreciation-form-data-win');
				}
			} else {
				fox.showMessage(data);
			}
			window.pageExt.form.afterSubmit && window.pageExt.form.afterSubmit(param,data);
		}, {delayLoading:1000,elms:[$("#submit-button")]});
	}

	/**
      * 保存数据，表单提交事件
      */
    function bindButtonEvent() {

	    form.on('submit(submit-button)', function (data) {
	    	//debugger;
			data.field = getFormData();

			if(window.pageExt.form.beforeSubmit) {
				var doNext=window.pageExt.form.beforeSubmit(data.field);
				if(!doNext) return ;
			}
			//校验表单
			if(!verifyForm(data.field)) return;

			saveForm(data.field);
	        return false;
	    });


	    //关闭窗口
	    $("#cancel-button").click(function(){ admin.finishPopupCenterById('eam-asset-depreciation-form-data-win'); });

    }

    window.module={
		getFormData: getFormData,
		verifyForm: verifyForm,
		saveForm: saveForm,
		fillFormData: fillFormData,
		adjustPopup: adjustPopup,
		action: action,
		setAction: function (act) {
			action = act;
		}
	};

	window.pageExt.form.ending && window.pageExt.form.ending();

}

layui.use(['form', 'table', 'util', 'settings', 'admin', 'upload','foxnic','xmSelect','foxnicUpload','laydate'],function() {
	var task=setInterval(function (){
		if(!window["pageExt"]) return;
		clearInterval(task);
		(new FormPage()).init(layui);
	},1);
});