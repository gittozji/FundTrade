/**
 * Created by imyu on 2017/2/16.
 */

jQuery.validator.addMethod("numberOrEnglish", function(value, element) {
    return this.optional(element) || (/^[a-zA-Z0-9]*$/.test(value));
}, $.validator.format("请输入字母或者数字"));

jQuery.validator.addMethod("isPhone", function(value, element) {
    return this.optional(element) || (/^((13|14|15|18|17)\d{9})$/.test(value));
}, $.validator.format("请输入正确手机号码"));

jQuery.validator.addMethod("isEmail", function(value, element) {
    return this.optional(element) || (/^[a-zA-Z0-9_\-\.]+@[a-zA-Z0-9\-_]+(\.[a-zA-Z0-9\-_]{2,})+([\.][a-zA-Z\-_]{2,})?$/.test(value));
}, $.validator.format("请输入正确邮箱格式"));
