(function (a) {
    //阻止事件向上冒泡（jQuery下使用）
    utils.stopBubble = function(){
        debugger
        if(event && event.stopPropagation){
            event.stopPropagation();
        }else{
            event.cancelBubble = true;
        }
    }
    //阻止冒泡
    function cancelBubble(){
        var e=getEvent();
        if(window.event){
            //e.returnValue=false;//阻止自身行为
            e.cancelBubble=true;//阻止冒泡
        }else if(e.preventDefault){
            //e.preventDefault();//阻止自身行为
            e.stopPropagation();//阻止冒泡
        }
    }

    //点击"#layerTip"触发函数
    $(document).on("click", "#layerTip", function(event) {
        utils.stopBubble(); //阻止冒泡
        return false;
    });

    //菜单跳转
    utils.menuToPage = function(url){
        $.ajax({
            type: "POST",
            url: url,
            async: false,
            success: function (obj) {
                $(".layui-body").html(obj);
            }
        });
    }


}(window.utils = (window.utils || {})));

