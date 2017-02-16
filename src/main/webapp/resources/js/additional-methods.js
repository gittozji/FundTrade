/**
 * Created by imyu on 2017/2/16.
 */
/***/
jQuery.validator.addMethod("numberOrEnglish", function(value, element, param) {
    return this.optional(element) || (/^[a-zA-Z0-9]*$/.test(value));
}, $.validator.format("请输入字母或者数字"));




jQuery.validator.addMethod("byteMaxLength", function(value, element, param) {
    var length = value.length;
    for ( var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length <= param);
}, $.validator.format("不能超过{0}个字节(一个中文字算2个字节)"));

jQuery.validator.addMethod("valiEnglish",function(value,element){
        return this.optional(element) || /^[a-zA-Z ]*$/.test(value);
    },$.validator.format("请输入字母或者空格"));