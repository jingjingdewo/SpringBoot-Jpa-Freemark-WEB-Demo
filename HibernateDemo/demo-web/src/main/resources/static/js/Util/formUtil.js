String.prototype.isNull = strNull;
String.prototype.isNumber = strNumber;
String.prototype.isDoubleNumber = strDoubleNumber;
String.prototype.isPostcode = strPostcode;
String.prototype.isPhone = strPhoneNumber;
String.prototype.isEmail = strEmail;
String.prototype.charLength = strCharLength;
String.prototype.isValidDate = strValidDate;
String.prototype.isMobile = strMobile;
String.prototype.isIdCard = checkIdcard;

function strNull(){
    if(this.replace(/(^\s*)|(\s*$)/g, '').length<=0)
    {
        return true;
    }else{
        return false;
    }
}

function strMobile(){
    if(!this.isNull()){
        var reg1 =/^((\(\d{2,3}\))|(\d{3}\-))?(13|15|18)\d{9}$/;
        if(!reg1.test(this)){
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }
}

function strNumber(){
    if(!this.isNull()){
        for(i=0;i<this.length;i++){
            if(this.charAt(i)<"0"||this.charAt(i)>"9"){
                return false;
            }
        }
        return true;
    }else{
        return true;
    }
}
function strPostcode(){
    if(!this.isNull()){
        if(this.length!=6){
            return false;
        }else{
            var rexTel=/^[0-9]+$/;
            if(!rexTel.test(this)){
                return false;
            }
        }
    }
    return true;
}
function strPhoneNumber(){
    if(!this.isNull()){
        var reg=/(^[0-9]{3,4}\-[0-9]{7,8}\-[0-9]{3,4}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}\-[0-9]{3,4}$)|(^[0-9]{7,15}$)/;
        if(!reg.test(this)){
            return false;
        }
        return true;
    }else{
        return true;
    }
}
function strEmail(){
    if(!this.isNull()){
        if(this.search(/^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/)!=-1){
            return true;
        }else{
            return false;
        }
    }else{
        return true;
    }
}
function strDoubleNumber(){
    var pointCount=0;
    for(var i=0;i<this.length;i++){
        if((this.charAt(i)<'0'||this.charAt(i)>'9')&&this.charAt(i)!='.'){
            return false;
        }else{
            if(this.charAt(i)=='.')pointCount++;
        }
    }
    if(pointCount>1){
        return false;
    }else if(pointCount==1&&this.trim().length==1){
        return false;
    }
    return true;
}
function strCharLength(){
    var byteLen=0,len=this.length;
    if(!this.isNull()){
        for(var i=0; i<len; i++){
            if(this.charCodeAt(i)>255){
                byteLen += 2;
            }else{
                byteLen++;
            }
        }
        return byteLen;
    }else{
        return 0;
    }
}
function strValidDate()
{
    var sDate=this.replace(/(^\s+|\s+$)/g,'');
    if(sDate=='') return true;
    var s = sDate.replace(/[\d]{ 4,4 }[\-/]{ 1 }[\d]{ 1,2 }[\-/]{ 1 }[\d]{ 1,2 }/g,'');
    if (s=='')
    {
        var t=new Date(sDate.replace(/\-/g,'/'));
        var ar = sDate.split(/[-/:]/);
        if(ar[0] != t.getYear() || ar[1] != t.getMonth()+1 || ar[2] != t.getDate())
        {
            alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');
            return false;
        }
    }
    else
    {
        alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');
        return false;
    }
    return true;
}

function checkIdcard(){
    var idcard = this.toUpperCase();
    var Errors={0:'true',1:'请使用18位身份证号码！',2:'身份证号码出生日期超出范围或含有非法字符！',3:'身份证号校验错误！',4:'身份证地区非法！',5:'请输入15位或18位身份证号！'};
    var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
    var iSum=0 ;
    if (!/^\d{17}(\d|x)$/i.test(idcard)) {
        return Errors[1];
    }
    /*if ((idcard.length != 18) && (idcard.length != 15)) {
        return Errors[5];
    }*/
    if (aCity[parseInt(idcard.substr(0, 2))] == null) {
        return Errors[4];
    }
    var sBirthday = "";
    if (idcard.length == 18) {
        sBirthday = idcard.substr(6, 4) + "-" + Number(idcard.substr(10, 2)) + "-" + Number(idcard.substr(12, 2));
    } else {
        sBirthday = "19" + idcard.substr(6, 2) + "-" + Number(idcard.substr(8, 2)) + "-" + Number(idcard.substr(10, 2));
    }
    var d = new Date(sBirthday.replace(/-/g, "/"))
    if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
        return Errors[2];
    }
    var JYM = "10X98765432";
    iSum = 0;
    if (/^\d{17}(\d|x)$/i.test(idcard)) {
        for (var i = 17; i > 0; i--)
            iSum += (Math.pow(2, i) % 11) * parseInt(idcard.charAt(17 - i), 11);
        var Y1 = iSum % 11;
        var M = "F";
        M = JYM.substr(Y1, 1);
        if (M == idcard.substr(17, 1)) {
            return Errors[0];
        } else
            return Errors[3];
    } else if (idcard.length == 18) {
        return Errors[2];
    }
    return Errors[0];
}