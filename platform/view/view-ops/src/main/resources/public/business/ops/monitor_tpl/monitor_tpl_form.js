/**
 * 监控模版 列表页 JS 脚本
 * @author 金杰 , maillank@qq.com
 * @since 2022-07-14 16:26:07
 */

function FormPage() {

	var settings,admin,form,table,layer,util,fox,upload,xmSelect,foxup;
	const moduleURL="/service-ops/ops-monitor-tpl";
	// 表单执行操作类型：view，create，edit
	var action=null;
	var disableCreateNew=false;
	var disableModify=false;
	var dataBeforeEdit=null;
	const bpmIntegrateMode="none";
	var isInProcess=QueryString.get("isInProcess");

	/**
      * 入口函数，初始化
      */
	this.init=function(layui) {
     	admin = layui.admin,settings = layui.settings,form = layui.form,upload = layui.upload,foxup=layui.foxnicUpload;
		laydate = layui.laydate,table = layui.table,layer = layui.layer,util = layui.util,fox = layui.foxnic,xmSelect = layui.xmSelect;

		action=admin.getTempData('ops-monitor-tpl-form-data-form-action');
		//如果没有修改和保存权限
		if( !admin.checkAuth(AUTH_PREFIX+":update") && !admin.checkAuth(AUTH_PREFIX+":save")) {
			disableModify=true;
		}
		if(action=="view") {
			disableModify=true;
		}

		if(bpmIntegrateMode=="front" && isInProcess==1) {
			$(".model-form-footer").hide();
		}

		if(window.pageExt.form.beforeInit) {
			window.pageExt.form.beforeInit(action,admin.getTempData('ops-monitor-tpl-form-data'));
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
		var prevBodyHeight=-1;
		adjustPopupTask=setTimeout(function () {
			var body=$("body");
			var bodyHeight=body.height();
			var footerHeight=$(".model-form-footer").height();
			if(bpmIntegrateMode=="front" && isInProcess==1) {
				var updateFormIframeHeight=admin.getVar("updateFormIframeHeight");
				if(bodyHeight>0 && bodyHeight!=prevBodyHeight) {
					updateFormIframeHeight && updateFormIframeHeight(bodyHeight);
				} else {
					setTimeout(adjustPopup,1000);
				}
				prevBodyHeight = bodyHeight;
				return;
			}
			var area=admin.changePopupArea(null,bodyHeight+footerHeight,'ops-monitor-tpl-form-data-win');
			if(area==null) return;
			admin.putTempData('ops-monitor-tpl-form-area', area);
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

		form.on('radio(status)', function(data){
			var checked=[];
			$('input[type=radio][lay-filter=status]:checked').each(function() {
				checked.push($(this).val());
			});
			window.pageExt.form.onRadioBoxChanged && window.pageExt.form.onRadioBoxChanged("status",data,checked);
		});
		//渲染 type 下拉字段
		fox.renderSelectBox({
			el: "type",
			radio: true,
			filterable: true,
			paging: true,
			pageRemote: true,
			on: function(data){
				setTimeout(function () {
					window.pageExt.form.onSelectBoxChanged && window.pageExt.form.onSelectBoxChanged("type",data.arr,data.change,data.isAdd);
				},1);
			},
			//转换数据
			searchField: "name", //请自行调整用于搜索的字段名称
			extraParam: {}, //额外的查询参数，Object 或是 返回 Object 的函数
			transform: function(data) {
				//要求格式 :[{name: '水果', value: 1},{name: '蔬菜', value: 2}]
				var defaultValues=[],defaultIndexs=[];
				if(action=="create") {
					defaultValues = "".split(",");
					defaultIndexs = "".split(",");
				}
				var opts=[];
				if(!data) return opts;
				for (var i = 0; i < data.length; i++) {
					if(!data[i]) continue;
					opts.push({data:data[i],name:data[i].name,value:data[i].code,selected:(defaultValues.indexOf(data[i].code)!=-1 || defaultIndexs.indexOf(""+i)!=-1)});
				}
				return opts;
			}
		});
	}

	/**
	 * 根据id填充表单
	 * */
	function fillFormDataByIds(ids) {
		if(!ids) return;
		if(ids.length==0) return;
		var id=ids[0];
		if(!id) return;
		admin.post(moduleURL+"/get-by-id", { id : id }, function (r) {
			if (r.success) {
				fillFormData(r.data)
			} else {
				fox.showMessage(r);
			}
		});
	}

	/**
	 * 在流程提交前处理表单数据
	 * */
	function processFormData4Bpm (processInstanceId,param,cb) {
		window.pageExt.form.processFormData4Bpm && window.pageExt.form.processFormData4Bpm(processInstanceId,param,cb);
	}

	/**
      * 填充表单数据
      */
	function fillFormData(formData) {
		if(!formData) {
			formData = admin.getTempData('ops-monitor-tpl-form-data');
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






			//设置  归类 设置下拉框勾选
			fox.setSelectValue4QueryApi("#type",formData.tplType);

			//处理fillBy

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
            },100,null,function (){
				fm.css("opacity","1.0");});
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



		//获取 归类 下拉框的值
		data["type"]=fox.getSelectedValue("type",false);

		return data;
	}

	function verifyForm(data) {
		return fox.formVerify("data-form",data,VALIDATE_CONFIG)
	}

	function saveForm(param,callback) {

		if(window.pageExt.form.beforeSubmit) {
			var doNext=window.pageExt.form.beforeSubmit(param);
			if(!doNext) return ;
		}

		param.dirtyFields=fox.compareDirtyFields(dataBeforeEdit,param);
		var action=param.id?"edit":"create";
		var api=moduleURL+"/"+(param.id?"update":"insert");
		admin.post(api, param, function (data) {
			if (data.success) {
				var doNext=true;
				var pkValues=data.data;
				if(pkValues) {
					for (var key in pkValues) {
						$("#"+key).val(pkValues[key]);
					}
				}
				if(window.pageExt.form.betweenFormSubmitAndClose) {
					doNext=window.pageExt.form.betweenFormSubmitAndClose(param,data);
				}

				if(callback) {
					doNext = callback(data,action);
				}

				if(doNext) {
					admin.finishPopupCenterById('ops-monitor-tpl-form-data-win');
				}

				// 调整状态为编辑
				action="edit";

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


			//校验表单
			if(!verifyForm(data.field)) return;

			saveForm(data.field);
	        return false;
	    });


	    //关闭窗口
	    $("#cancel-button").click(function(){ admin.finishPopupCenterById('ops-monitor-tpl-form-data-win',this); });

    }

    window.module={
		getFormData: getFormData,
		verifyForm: verifyForm,
		saveForm: saveForm,
		fillFormData: fillFormData,
		fillFormDataByIds:fillFormDataByIds,
		processFormData4Bpm:processFormData4Bpm,
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