/**
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});


$.extend($.fn.validatebox.defaults.rules, {
	isPwd : {
		validator : function(value,param) {
			var reg =/^[a-zA-Z0-9_]\w{8,12}$/;
			return reg.test(value);  
		},
		message : '以字母开头，长度在8-12之间，只能包含字符、数字和下划线。!'
	}
});

/**
 * 扩展tree和combotree，使其支持平滑数据格式
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.fn.tree.defaults.loadFilter = function(data, parent) {
		var opt = $(this).data().tree.options;
		var idField, textField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			textField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	};
	
	/**
	 * @author 孙宇
	 * 
	 * @requires jQuery, EasyUI
	 * 
	 * 防止panel/window/dialog组件超出浏览器边界
	 * @param left
	 * @param top
	 */
	var easyuiPanelOnMove = function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var button = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (button > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({
			left : l,
			top : t
		});
	};
	$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
	$.fn.window.defaults.onMove = easyuiPanelOnMove;
	$.fn.panal.defaults.onMove = easyuiPanelOnMove;



	/**
	 * @author 孙宇
	 * 
	 * @requires jQuer
	 * 
	 * 将form表单元素的值序列化成对象
	 * 
	 * @returns object
	 */
serializeObject = function(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = 0[this['name']] + ',' + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});

		return o;
	};
	
	
    $.extend($.fn.datagrid.defaults.editors, {
        datetimebox: {
    		init: function(container, options){
    			var editor = $('<input />').appendTo(container);
    			options.editable= false;
    			editor.datetimebox(options);
    			return editor;
    		},
    		destroy: function(target){
    			$(target).datetimebox('destory');
    		},
    		getValue: function(target){
    			return $(target).datetimebox('getValue');
    		},
    		setValue: function(target, value){
    			$(target).datetimebox('setValue', value);
    		},
    		resize: function(target, width){
    			$(target).datetimebox('resize', width);
    		}
        }
    });
    
    
    /**
     * @author 孙宇
     * 
     * @requires jQuery,EasyUI
     * 
     * 扩展datagrid，添加动态增加或删除Editor的方法
     * 
     * 例子如下，第二个参数可以是数组
     * 
     * datagrid.datagrid('removeEditor', 'cpwd');
     * 
     * datagrid.datagrid('addEditor', [ { field : 'ccreatedatetime', editor : { type : 'datetimebox', options : { editable : false } } }, { field : 'cmodifydatetime', editor : { type : 'datetimebox', options : { editable : false } } } ]);
     * 
     */
    $.extend($.fn.datagrid.methods, {
    	addEditor : function(jq, param) {
    		if (param instanceof Array) {
    			$.each(param, function(index, item) {
    				var e = $(jq).datagrid('getColumnOption', item.field);
    				e.editor = item.editor;
    			});
    		} else {
    			var e = $(jq).datagrid('getColumnOption', param.field);
    			e.editor = param.editor;
    		}
    	},
    	removeEditor : function(jq, param) {
    		if (param instanceof Array) {
    			$.each(param, function(index, item) {
    				var e = $(jq).datagrid('getColumnOption', item);
    				e.editor = {};
    			});
    		} else {
    			var e = $(jq).datagrid('getColumnOption', param);
    			e.editor = {};
    		}
    	}
    });





