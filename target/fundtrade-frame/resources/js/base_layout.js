$(document).ready(function(){
    $(".button-collapse").sideNav();
    //监听导航栏是否获取焦点
    $(document).on("mouseover mouseout",".main-container-nav",function(e){
        if(e.type == "mouseover"){
            $(this).css("overflow","auto");
        }else{
            $(this).css("overflow","hidden");
        }
    });
});