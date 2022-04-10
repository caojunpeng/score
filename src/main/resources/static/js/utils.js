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
        debugger
        $.ajax({
            type: "POST",
            url: url,
            async: false,
            success: function (obj) {
                $(".layui-body").html(obj);
            }
        });
    }
    /**
     * 获取合法的url 防止url中出现非法字符
     * @param url
     */
    utils.getLegalUrl = function getLegalUrl(url){
        var finalUrl = "";
        if(url.indexOf("?")>0){
            var urlPrefix = url.substring(0,url.indexOf("?")+1);
            var urlParam = url.substring(url.indexOf("?")+1,url.length);
            if(urlParam.indexOf("&")>0){
                var finalUrlParam = "";
                var strings = urlParam.split("&");
                strings.forEach(function (e,i) {
                    var index = e.indexOf("=")+1;
                    if(index>0){
                        var param = e.substring(0,index);
                        var value = e.substring(index,e.length);
                        var val = encodeURIComponent(value);
                        finalUrlParam += param + val;
                    }else {
                        finalUrlParam += e;
                    }
                    if(i!=strings.length-1){
                        finalUrlParam += "&";
                    }
                })
                finalUrl = urlPrefix + finalUrlParam;
            }else{
                var index = urlParam.indexOf("=")+1;
                var onlyParam = urlParam.substring(0,index);
                var onlyValue = urlParam.substring(index,urlParam.length);
                var val = encodeURIComponent(onlyValue);
                finalUrl = urlPrefix + onlyParam + val;
            }
        }else{
            finalUrl = url;
        }
        return finalUrl;
    }


    /**
     * 是否为数字
     */
    utils.isNum = function(para){
        if(/^\d+$/.test(para)){
            return true;
        }else{
            return false;
        }
    }

    //将null转换成空
    utils.changeBlank = function changeBlank(content){
        if(content==null){
            return "";
        }
        return content;
    }

}(window.utils = (window.utils || {})));

