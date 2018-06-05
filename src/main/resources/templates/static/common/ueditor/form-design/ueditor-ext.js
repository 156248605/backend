
function getInput(binding){
	var gridInput=binding.gridInput;
	if(!gridInput || gridInput.length==0) return "";
	var params=[];
	for(var i=0;i<gridInput.length;i++){
		var param=gridInput[i];
		if(param.type!="income") continue;
		params.push(param);
	}
	var rtn="";
	for(var i=0;i<params.length;i++){
		var param=params[i];
		var tmp=construtParam(param);
		if(!tmp) continue;
		if(!rtn){ 
			rtn=tmp;
		}
		else{
			rtn+="&" +tmp;
		}
	}
	return rtn;
}

/**
 * 构建参数。
 * @param param
 * @returns
 */
function construtParam(param){
	var mode=param.mode;
	var bindVal=param.bindVal;
	if(!bindVal) return "";
	var val="";
	if(mode=="mapping"){
		val=mini.getByName(bindVal).getValue();
	}
	else{
		var func = new Function(bindVal);
		var val=func();
	}
	if(!val) return "";
	return  param.fieldName +"=" + val;
	
}


function setRtnData(data,binding){
	if(data!=null && data.length>0)
	var fields=binding.returnFields;
	var gridName=binding.gridName;
	
	if(!data || data.length==0) return;
	//填充主表	
	if(gridName=="main"){
		for(var i=0;i<fields.length;i++){
			var field=fields[i];
		
			if(!field.bindField){continue;}
			var ctl=mini.getByName(field.bindField);
			if(!ctl) continue;
			var aryTmp=[];
			for(var j=0;j<data.length;j++){
				var row=data[j];
				var val=row[field.field];
				aryTmp.push(val);
			}
			ctl.setValue(aryTmp.join(","));
			if(ctl.doValueChanged){
				ctl.doValueChanged();
			}
			
		}
	}
	//填充子表。
	else{
		var uniquefield=binding.uniquefield;
		var grid=mini.get("grid_" + gridName);
		var obj={};
		for(var i=0;i<fields.length;i++){
			var o=fields[i];
			if(!o.bindField){continue;}
			obj[o.field]=o.bindField;
		}
		
		var rows=grid.getData();
	
		for(var i=0;i<data.length;i++){
			var row=data[i];
			var newRow={};
			for(var key  in obj){
				//newKey 绑定字段
				var newKey=obj[key];
				newRow[newKey]=row[key];
			}
			if(uniquefield){
				var isExist=false;
				var val=newRow[uniquefield];
				for(var i=0;i<rows.length;i++){
					var tmp=rows[i];
					if(tmp[uniquefield]==val){
						isExist=true;
					}
				}
				if(!isExist){
					rows.push(newRow);
				}
			}
			else{
				rows.push(newRow);
			}
			
		}
		grid.setData(rows);
	}
	
	
}



function getBtnEditInput(binding,row){
	var gridInput=binding.gridInput;
	if(!gridInput || gridInput.length==0) return "";
	var params=[];
	for(var i=0;i<gridInput.length;i++){
		var param=gridInput[i];
		if(param.type!="income") continue;
		params.push(param);
	}
	var rtn="";
	for(var i=0;i<params.length;i++){
		var param=params[i];
		var tmp=construtBtnEditParam(param,binding,row);
		if(!tmp) continue;
		if(!rtn){ 
			rtn=tmp;
		}
		else{
			rtn+="&" +tmp;
		}
	}
	return rtn;
	
}

/**
 * 构建参数。
 * @param param
 * @returns
 */
function construtBtnEditParam(param,binding,row){
	var mode=param.mode;
	var bindVal=param.bindVal;
	var val="";
	if(mode=="mapping"){
		if(binding.isMain){
			val=mini.getByName(bindVal).getValue();
		}
		else{
			val=row[bindVal];
		}
	}
	else{
		var func = new Function(bindVal);
		var val=func();
	}
	if(!val) return "";
	return param.fieldName +"=" + val;
	
}


var _jsonInit={};
/**
 * 设置表格参数
 */
function setGridConfig(){
	var parent=$("#form-panel");
	var grids=$("div.mini-datagrid",parent);
	for(var i=0;i<grids.length;i++){
	   var grid=grids[i];
	   var id=grid.id;
	   if(!id) continue;
	   var gd=mini.get(id);
	   if(!gd)  continue;

	   gd.on('cellendedit',function(e){
		if(e.column.displayField && e.editor.getText && e.editor.getText()){
			e.record[e.column.displayField]=e.editor.getText();
		}
	   });
	   //禁止编辑已添加的数据。
	   gd.on('cellbeginedit',function(e){
		   var sender=e.sender;
		   var dealwith=sender.dealwith;
		   var row=e.record;
		   if(dealwith && dealwith.indexOf("noedit")!=-1 && row.ID_){
			   e.cancel=true;
		   }
	   });
	   //画单元格
	   gd.on("drawcell",function(e){
		   if(window.drawcell){
			   window.drawcell(e);
		   }
	   })
	}
	
	// 初始化表格数据
	$('._initdata').each(function(){
		var gridId="grid_" + $(this).attr('grid');
		var grid=mini.get(gridId);
		var gridData=$(this).html().trim(); 
        try{
        	var json=mini.decode(gridData);
        	_jsonInit[gridId]=json.initData;
        	grid.setData(json.data);
        	mini.layout();
        }catch(e){
        }
	});
}

//加载审批历史
function loadCheckHisList(){
	if(mini.get("checkhilist")!=null){
		mini.get("checkhilist").on("drawcell", function (e) {
	        field = e.field,
	        value = e.value;
            if(field=='jumpType'){
            	if(value=='AGREE'){
            		e.cellHtml='同意';
            	}else if(value=='REFUSE'){
            		e.cellHtml='弃权';
            	}else if(value=='BACK'){
            		e.cellHtml='回退';
            	}else if(value=='ABACK_TO_STARTOR'){
            		e.cellHtml='回退发起人';
            	}else if(value=='UNHANDLE'){
            		e.cellHtml='未开始审批';
            	}
            }
        });
		mini.get("checkhilist").load();// 审批历史grid
	}
}


function saveOffice(fileId) {
	var ctl=document.getElementById(fileId);
	if(ctl!=null){
		ctl.WebSaveLocal();	
	}
}

function printOffice(fileId,type){
	var ctl=document.getElementById(fileId);
	if(ctl!=null){
		if(type=="pdf"){
			ctl.WebPrint(-1,'',0,0,true);
		}else{
			cl.WebOpenPrint();
		}
	}
}

function initCheckGrid(){
	var obj=mini.get("checkhilist");
	if(!obj) return;
	
	obj.load();// 审批历史grid
	obj.on("drawcell", function (e) {
        field = e.field,
        value = e.value;
        if(field=='jumpType'){
        	var json={AGREE:"同意",REFUSE:"弃权",BACK:"回退",ABACK_TO_STARTOR:"回退发起人",UNHANDLE:"未开始审批"};
        	e.cellHtml=json[value];
        }
    });
}

var formulaCalc_;
//解析及显示miniUI控件表单
function renderMiniHtml(conf,result){
	mini.parse();
	setGridConfig();
	//公式处理。
	formulaCalc_=new FormCalc("form-panel");
	formulaCalc_.parseFormula();
	
	customQuery=new CustomQuery("form-panel");
	customQuery.parseQuery();
	
	//初始化审批历史
	initCheckGrid();
	//打印表单处理
	tabPrintHandler();
	if(isExitsFunction('_onload')){
		try{
			_onload.call();
		}catch(e){}
	}
	if(conf.callback){
		conf.callback.call(this,result);
	}
}


/**
 * 处理是否打印TAB表单。
 */
function tabPrintHandler(){
	
	$("[name='chkPrint']").each(function(){
		var obj=$(this);
		obj.bind("click",function(){
			var ckObj=$(this);
			var id=ckObj.attr("id");
			var checked=ckObj[0].checked;
			var tabId=id.replace("tab_","form_");
			var form=$("#"+ tabId);
			if(checked){
				form.removeClass("noPrint")
			}
			else{
				form.addClass("noPrint")
			}
		});
	})
}



/**
 * 打印表单。
 * conf配置如下：
 * {
 * 	instId:"",
 * 	solId:"",
 * 	taskId:""
 * }
 * 
 * @param conf
 * @param data	表单数据
 */
function printForm(conf,formId){
	var params="";
	var aryPara=[];
	for(var key in conf){
		aryPara.push(key +"=" + conf[key]);
	}
	var url=__rootPath+'/bpm/form/bpmFormView/print.do?' + aryPara.join("&");
	var windowSize=getWindowSize();
	_OpenWindow({
		title:"打印表单",
		url:url,
		width:windowSize.width || 700,
		height:windowSize.height ||450,
		onload : function() {
			var result=getBoFormData(true);
			var data = mini.encode(result.data);
			var iframe = this.getIFrameEl();
			iframe.contentWindow.setData(data);
		}
	});
}


function onFileRender(e){
    var record = e.record;
    var files =record[e.field];
    if(!files) return "";
    var s="";
    var aryFile=mini.decode(files);
  	for(var i=0;i<aryFile.length;i++){
  		var file=aryFile[i];
  		s+=getFile(file) ;
  	}
    return s;
}

function getFile(file){
	var fileName=file.fileName;

	if(fileName.indexOf('doc')!=-1||fileName.indexOf('docx')!=-1
			||fileName.indexOf('pptx')!=-1||fileName.indexOf('ppt')!=-1||
			fileName.indexOf('xlsx')!=-1||fileName.indexOf('xls')!=-1) { 
		return '<a target="_blank" href="'+__rootPath+'/scripts/PDFShow/web/viewer.html?file='+
			__rootPath+'/sys/core/file/download/'+file.fileId+'.do">'+fileName+'</a>';
	}
	else{
		return '<a target="_blank" href="'+__rootPath+'/sys/core/file/previewFile.do?fileId='+file.fileId+'">'+fileName+'</a>';
	}
}

