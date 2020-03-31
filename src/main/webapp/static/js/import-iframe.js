

function getRealPathWithProjectName(){
	var curWwwPath=window.document.location.href;
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	var localhostPath=curWwwPath.substring(0,pos);
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	var realPath=localhostPath+projectName;
	return realPath;
}

function getRealPathWithoutProjectName(){
	var curWwwPath=window.document.location.href;
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	var localhostPath=curWwwPath.substring(0,pos);
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	var realPath=localhostPath+projectName;
	return localhostPath;
}

//validator的通用消息展示方法
function errorPlacementFunction(error,element) {
	var el = null;
	if(element.parent().find('span').length > 0) {
		el = element.parent().find('span').first()
	}
	else {
		el = element.parent().parent().find('span').first();
	}
	error.appendTo(el);
}

/**
 * cookie操作
 */
var getCookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var s = [cookie, expires, path, domain, secure].join('');
        var secure = options.secure ? '; secure' : '';
        var c = [name, '=', encodeURIComponent(value)].join('');
        var cookie = [c, expires, path, domain, secure].join('')
        document.cookie = cookie;
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = cookies[i].replace(/(^\s*)|(\s*$)/g, ""); //jQuery.trim();
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};

/**
 * 获取浏览器语言类型
 * @return {string} 浏览器国家语言
 */
var getNavLanguage = function(){
    if(navigator.appName == "Netscape"){
        var navLanguage = navigator.language;
        return navLanguage.substr(0,2);
    }
    return false;
}


/**
 * 设置语言类型： 默认为中文
 */
var i18nLanguage = "zh_CN";

/*
设置一下网站支持的语言种类
 */
var webLanguage = ['zh_CN', 'en'];

function contains(arr, obj) {
	  var i = arr.length;
	  while (i--) {
	    if (arr[i] === obj) {
	      return true;
	    }
	  }
	  return false;
	}

/*
首先获取用户浏览器设备之前选择过的语言类型
*/
var getLang = function() {
	
	var langStr = getCookie('lang');
	if(langStr || 'null' == langStr) {
    	 i18nLanguage = langStr;
    	 getCookie('lang', i18nLanguage, {expires:30,path: '/'});
    } else {
        // 获取浏览器语言
        /*var navLanguage = getNavLanguage();
        if (navLanguage) {
            // 判断是否在网站支持语言数组里
            var charSize = contains(navLanguage, webLanguage);
            if (charSize > -1) {
                i18nLanguage = navLanguage;
           	 	getCookie('webLang', i18nLanguage, {expires:30,path: '/'});
            };
        }*/
    	

   	 getCookie('lang', i18nLanguage, {expires:30,path: '/'});
    }
	
	
	return i18nLanguage;
}

//var webRoot = getRealPathWithProjectName();
var webRoot = getRealPathWithoutProjectName();

var appRoot = webRoot + "/static/";

document.write("<link rel='stylesheet' href='"+appRoot+"components/bootstrap/dist/css/bootstrap.min.css' />");
document.write("<link rel='stylesheet' href='"+appRoot+"components/font-awesome/css/font-awesome.min.css' />");
document.write("<link rel='stylesheet' href='"+appRoot+"components/Ionicons/css/ionicons.min.css' />");
document.write("<link rel='stylesheet' href='"+appRoot+"css/content.css' />");
document.write("<link rel='stylesheet' href='"+appRoot+"css/eps-client.css' />");
document.write("<link rel='stylesheet' href='"+appRoot+"css/skins/_all-skins.min.css' />");
document.write("<link rel='stylesheet' href='"+appRoot+"components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css' />");
document.write("<link rel='stylesheet' href='"+appRoot+"components/bootstrap-daterangepicker/daterangepicker.css' />");
document.write("<link rel='stylesheet' href='"+appRoot+"components/jquery-pagination/style/pagination.css' />");

//document.write("<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic' />");


document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/jquery/dist/jquery.min.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/jquery-ui/jquery-ui.min.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/moment/min/moment.min.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/jquery-validation/jquery.validate.js'></script>");


document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/bootstrap/dist/js/bootstrap.min.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/jquery-slimscroll/jquery.slimscroll.min.js'></script>");

document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/bootstrap-daterangepicker/daterangepicker.js'></script>");

document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"js/demo.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"js/adminlte.js'></script>");


document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/jquery-pagination/js/jquery.pagination.min.js'></script>");

document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/jquery-i18n/jquery.i18n.properties.min.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"js/language.js'></script>");

document.write("<script type='text/javascript' language='javascript'>$.widget.bridge('uibutton', $.ui.button);</script>");


//验证框架国际化
var defaultLang = getLang();
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"components/jquery-validation/localization/messages_"+defaultLang+".js'></script>");

document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"js/system_business.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"js/base_tools.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"js/action.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"js/business.js'></script>");
document.write("<script type='text/javascript' language='javascript' src='"+appRoot+"js/jquery.form.js'></script>");
