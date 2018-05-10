(function($){
	var d= $.fn.layout.paneldefaults;
	 var buttonDir = {east:'left',west:'right'};
	    $.extend($.fn.layout.paneldefaults,{
	        onBeforeCollapse:function(){
	            /**/
	            var popts = $(this).panel('options');
	            var dir = popts.region;
	            var btnDir = buttonDir[dir];
	            $('#mainFrame').tabs({  
	              width: $("#mainFrame").width()
	            }); 
	            if(!btnDir) return false;
	        },
	        onBeforeExpand:function()
	        {
	        	var popts = $(this).panel('options');
	        	$('#mainFrame').tabs({  
              width: $(document.body).width()-popts.width
            }); 
	        }
	    });
	})(jQuery);