/**
 * Created by imyu on 2017/2/16.
 */
/**表单序列号为json对象*/
(function($){
    $.fn.serializeJson=function(){
        var param = {};

        this.find("input[type != 'checkbox']").each(function(index,item){
            param[$(this).attr("name")] = $(this).val();
        });

        this.find("input[type = 'checkbox']").each(function(index,item){
            param[$(this).attr("name")] = $(this).prop("checked");
        });

        this.find("select").each(function(index,item){
            param[$(this).attr("name")] = $(this).val();
        });

        return param;
    };
})(jQuery);