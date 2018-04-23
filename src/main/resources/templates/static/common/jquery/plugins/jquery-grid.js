/**
 * 关闭明细弹窗
 * @param detailId
 */
function closeFormDetail(detailId){
	 var win = mini.get("editWindow_"+detailId);
     win.hide();
}
/**
 * 保存明细至Grid
 */
function saveFormDetail(detailId){
	 var form=new mini.Form('editForm_'+detailId);
	 var win = mini.get("editWindow_"+detailId);
	 form.validate();
	 if(!form.isValid()){
		 return;
	 }
	 
	 var grid=mini.get("grid_" +detailId);
	 
	 var uid=form.getData()._uid;
	 if(uid){
		 var row=grid.getRowByUID(uid);
		 if(row!=null){
			 grid.updateRow(row,form.getData());
		 }
	 }else{
		 grid.addRow(form.getData());
	 }
     win.hide();
}


function _addGridRow(gridId,edittype){
	var rowData=_jsonInit['grid_'+gridId] || {};
	var row=$.clone(rowData);
	
	if(window._addRow){
		_addRow(gridId,row);
	}
	
	if(edittype=='openwindow'){
		 var win = mini.get("editWindow_"+gridId);
		 var form=new mini.Form('editForm_'+gridId);
		 form.setData(row);
		 var fields = form.getFields();
         for (var i = 0, l = fields.length; i < l; i++) {
             var c = fields[i];
             if (c.setReadOnly) c.setReadOnly(false);
             if (c.removeCls) c.removeCls("asLabel");
         }
         mini.repaint(document.body);
		 win.show();
	}else if(edittype=='editform'){
		var container=$("#gct_"+gridId);
		var fheight=container.attr('fheight');
		var fwidth=container.attr('fwidth');
		var formKey=container.attr('formkey');
		var formname=container.attr('formname');
		if(!fheight || fheight==0 || fheight==''){
			fheight=350;
		}
		if(!fwidth || fwidth==0 || fwidth==''){
			fwidth=800;
		}
		_OpenWindow({
			title:'添加记录-'+formname,
			url:__rootPath+'/bpm/form/bpmFormView/form/'+formKey+'.do',
			max:true,
			height:fheight,
			width:fwidth,
			onload:function(){
			},
			ondestroy:function(action){
				if(action!='ok'){
					return;
				}
				var win=this.getIFrameEl().contentWindow;
				var grid=mini.get('grid_'+gridId);
				if(win.getData){
					var data=win.getData();
					grid.addRow(data);
				}
			}
		});
	}else{
		var grid=mini.get('grid_'+gridId);
		if(window)
		grid.addRow(row);
	}
}

//编辑表单
function _editGridRow(gridId,editType){
	var grid=mini.get('grid_'+gridId);
	
	var row = grid.getSelected();
	if(!row){
		alert('请选择行!');
		return;
	}
	
	var dealwith=grid.dealwith;
	if(dealwith && dealwith.indexOf("noedit")!=-1 && row.ID_){
		alert('选择数据不允许编辑!');
		return;
	}
	
	
	if(editType=='editform'){
		var container=$("#gct_"+gridId);
		var fheight=container.attr('fheight');
		var fwidth=container.attr('fwidth');
		var formname=container.attr('formname');
		var formKey=container.attr('formkey');
		var grid=mini.get('grid_'+gridId);
		if(!fheight || fheight==0 || fheight==''){
			fheight=350;
		}
		if(!fwidth || fwidth==0 || fwidth==''){
			fwidth=800;
		}
		_OpenWindow({
			title:'添加记录-'+formname,
			url:__rootPath+'/bpm/form/bpmFormView/form/'+formKey+'.do',
			max:true,
			height:fheight,
			width:fwidth,
			onload:function(){
				var win=this.getIFrameEl().contentWindow;
				if(win.setData){
					var selectRow=grid.getSelected();
					win.setData(selectRow);
				}
			},
			ondestroy:function(action){
				if(action!='ok'){
					return;
				}
				var win=this.getIFrameEl().contentWindow;
				if(win.getData){
					var data=win.getData();
					var selectRow=grid.getSelected();
					grid.updateRow(selectRow,data);
				}
			}
		});
	}else{
		 var form=new mini.Form('editForm_'+gridId);
		 form.setData(row);
		 var fields = form.getFields();
         for (var i = 0, l = fields.length; i < l; i++) {
             var c = fields[i];
             if (c.setReadOnly) c.setReadOnly(false);
             if (c.removeCls) c.removeCls("asLabel");
         }
         mini.repaint(document.body);
		 var win = mini.get("editWindow_"+gridId);
		 win.show();
	}
}

function _detailGridRow(gridId,editType){
	var grid=mini.get('grid_'+gridId);
	var row = grid.getSelected();
	if(!row){
		alert('请选择行!');
		return;
	}
	if(editType=='editform'){
		var container=$("#gct_"+gridId);
		var fheight=container.attr('fheight');
		var fwidth=container.attr('fwidth');
		var formname=container.attr('formname');
		var formKey=container.attr('formkey');
		var grid=mini.get('grid_'+gridId);
		if(!fheight || fheight==0 || fheight==''){
			fheight=350;
		}
		if(!fwidth || fwidth==0 || fwidth==''){
			fwidth=800;
		}
		_OpenWindow({
			title:'记录明细-'+formname,
			url:__rootPath+'/bpm/form/bpmFormView/form/'+formKey+'.do',
			max:true,
			height:fheight,
			width:fwidth,
			onload:function(){
				var win=this.getIFrameEl().contentWindow;
				if(win.setData){
					var selectRow=grid.getSelected();
					win.setData(selectRow);
				}
				if(win.setFieldsLabelMode){
					win.setFieldsLabelMode();
				}
			}
		});
	}else{
		 var form=new mini.Form('editForm_'+gridId);
		 form.setData(row);
		 //setReadOnly
		 var win = mini.get("editWindow_"+gridId);
		 var fields = form.getFields();                
         for (var i = 0, l = fields.length; i < l; i++) {
             var c = fields[i];
             if (c.setReadOnly) c.setReadOnly(true);     //只读
             if (c.setIsValid) c.setIsValid(true);      //去除错误提示
             if (c.addCls) c.addCls("asLabel");          //增加asLabel外观
         }
		 win.show();
	}
}

//删除
function _delGridRow(gridId){
	var grid=mini.get('grid_'+gridId);
	
	var dealwith=grid.dealwith;
	var allowDel=true;
	if(dealwith && dealwith.indexOf("nodel")!=-1){
		allowDel=false;
	}
	
	var selecteds=grid.getSelecteds();
	if(allowDel){
		grid.removeRows(selecteds);
	}
	else{
		var calDels=[];
		for(var i=0;i<selecteds.length;i++){
			var o=selecteds[i];
			//表示ID存在
			if(o.ID_) continue;
			calDels.push(o);
		}
		grid.removeRows(calDels);
	}
	if(formulaCalc_){
		formulaCalc_.handGridChange('grid_' +gridId)
	}
	
	/**
	 * 删除行
	 */
	if(window._removeRows){
		window._removeRows(gridId);
	}
}


//上移
function _upGridRow(gridId){
	var grid=mini.get(gridId);
	var row = grid.getSelected();
    if (row) {
        var index = grid.indexOf(row);
        grid.moveRow(row, index - 1);
    }
} 
//下移
function _downGridRow(gridId){
	var grid=mini.get(gridId);
	var row = grid.getSelected();
    if (row) {
        var index = grid.indexOf(row);
        grid.moveRow(row, index + 2);
    }
}



function onCellValidation(e){
	
}