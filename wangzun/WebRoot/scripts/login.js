function singin() {
    if (zunmi.doing) return false;
    clearError();
    if (!zunmi.hasVal(u) || u.val()=='用户名/邮箱') {
        showError(u, "请输入用户名");
        return false;
    }
    if (!zunmi.hasVal(p)) {
        showError(p, "请输入密码");
        return false;
    }
    var data = "u=" + zunmi.val2(u)
        + "&p=" + zunmi.val2(p) 
        + "&remember=" + ($("#chkRemember").attr("checked") ? "1" : "0")
        + "&f=" + zunmi.val2("f") 
        + "&rand=" + Math.random();
    zunmi.doing = true;
    zunmi.loading($("#btnLogin")[0]);
    $.post(zunmi.acc_handler + "?t=log", data, function (data) {
        zunmi.doing = false;
        setTimeout("zunmi.loaded();", 6000);
        var f = zunmi.val("f"); //from
        var cb = zunmi.val("cb"); //callback
        data = zunmi.toJSON(data);
        if (data.status == true) {
        	 /*
             * 此处为服务器密码强度校验 暂不用
            var showpwdnote = zunmi.checkPW(p.val()) < 2 || u.val() == p.val();
            if (data.data.password == '1') {
                showpwdnote = true;
            }
            if (showpwdnote) {
                if (zunmi.parent.location.href.toLowerCase().indexOf("login.html") > 0) {
                    zunmi.parent.plow = true;
                } else {
                    alert("您的密码过于简单，建议您及时修改密码。");
                }
            }
            */
            if (cb) {
                sso_back_js = "(parent||top||window)." + cb + "(" + $.toJSON(data) + ")";
                sso_login();
                //eval("(parent||top||window)." + cb + "(" + $.toJSON(data) + ")");
            } else {
                if (!f) zunmi.parent.location.reload();
                //else zunmi.parent.location = f;
                else location = f;
            }
        } else {
            if (data.error.indexOf("激活") > -1) {
                if (cb) eval("(parent||top||window)." + cb + "(" + $.toJSON(data) + ")");
                else zunmi.parent.location = "/account/active?from=" + encodeURIComponent(f);
            } else {
                $("#sp_err").html(data.error);
                zunmi.loaded();
                p.select();
            }
        }
    });
    return false;
}
function showError(e, err) {
    var sp = document.createElement("span");
    sp.className = "error";
    sp.innerHTML = err;
    e.parent().append(sp);
    e.focus();
}
function clearError() {
    $(".error").each(function () {
        this.parentNode.removeChild(this);
    });
    $("#sp_err").html("");
}
var u, p;
$(function () {
    u = $("#userName"); p = $("#userPwd");
    $("#btnLogin").click(function () {
        singin();
        return false;
    });

    u.keydown(function (ev) {
        ev = ev || event;
        if (zunmi.isEnter(ev)) singin();
    });
    p.keydown(function (ev) {
        ev = ev || event;
        if (zunmi.isEnter(ev)) singin();
    });
    $("#chkRemember").keydown(function (ev) {
        ev = ev || event;
        if (zunmi.isEnter(ev)) singin();
    });

    if ($.cookie("UN")) {
        u.val($.cookie("UN"));
        p.focus();
    } else {
        u.val("用户名/邮箱");
        u.css("color", "#999");
    }
    u.focus(function () {
        if (u.val() == "用户名/邮箱") {
            u.val("");
            u.css("color", "#333");
        }
    }).blur(function () {
        if (u.val() == "") {
            u.val("用户名/邮箱");
            u.css("color", "#999");
        }
    });
    //showThirdButton();
    showTopErr();
});
function showThirdButton() {
    if (!zunmi.getQuery('hidethird')) {
        var arr = ['sinat', 'qq', 'iteye', 'google', 'aliyun'];
        var arr2 = ['新浪微博', '腾讯QQ', 'ITeye', 'Google', '阿里云'];

        var f = $('#f').val();
        if (f && f != 'http://www.zunmi.com/') {
            f = '?from=' + encodeURIComponent(f);
        } else {
            f = '';
        }
        var text = '';
        for (var i = 0; i < arr.length; i++) {
            text += "<a href='/auth/" + arr[i] + f + "' title='" + arr2[i] + "' target=_top>"
            + "<img src='/images/button/third_min_" + arr[i] + ".png' /></a>";
        }
        $('#td_third').html(text);
        $('#tr_third').show();
    }
}

function showTopErr() {
    var err = zunmi.getQuery('err', zunmi.parent.location.href);
    if (err) {
        $("#sp_err").html(err);
    }
}


/*sso*/
function sso_login() {
    var exp = $("#chkRemember").attr("checked") ? 7 : 0;
    var url = 'ssoindex.action?exp=' + exp;
    zunmi.load_frm(url, sso_login_ok);
}
function sso_login_ok() {
    eval(sso_back_js);
}
$(function () {
    setTimeout(function () {
	    var url = 'ssoindex.action?logout=1';
	    zunmi.load_frm(url);
    },1000);
});


try { var d = (parent || top).document; }
catch (err) { }