$(document).ready(function(){
    $(".button-collapse").sideNav();

    $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 15,
        'format':'yyyy-mm-dd'
    });

    // 监听导航栏是否获取焦点
    $(document).on("mouseover mouseout",".main-container-nav",function(e){
        if(e.type == "mouseover"){
            $(this).css("overflow","auto");
        }else{
            $(this).css("overflow","hidden");
        }
    });

    // 初始化Select的Options
    function initSelect(htmlObject, selectName, selectItemMap){
        var selectData = selectItemMap[selectName];
        var selectOptions = "";
        for(var i = 0; i < selectData.length; i ++) {
            var element = selectData[i];
            if (element == null){
                continue;
            }
            selectOptions += "<option value='" + element.item + "'>" + element.caption + "</option>";
        }
        htmlObject.empty();
        htmlObject.append(selectOptions);
    }



});