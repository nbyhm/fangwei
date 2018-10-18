$(document).ready(function () {

    $('input').iCheck({
        checkboxClass: 'icheckbox_minimal-green',
        radioClass: 'iradio_minimal-green',
        increaseArea: '20%'
    });

    var $formPanelTwo = $('.form-panel.two');

    var panelOne = $formPanelTwo.height();
    var panelTwo = $formPanelTwo[0].scrollHeight;

    $formPanelTwo.not('.form-panel.two.active').on('click', function (e) {
        e.preventDefault();

        $('.form-toggle').addClass('visible');
        $('.form-panel.one').addClass('hidden');
        $('.form-panel.two').addClass('active');
        $('.form').animate({
            'height': panelTwo
        }, 200);
    });

    $('.form-toggle').on('click', function (e) {
        e.preventDefault();
        $(this).removeClass('visible');
        $('.form-panel.one').removeClass('hidden');
        $('.form-panel.two').removeClass('active');
        $('.form').animate({
            'height': panelOne + 92
        }, 200);
    });

});


function reloadCode() {
    $("#validateCodeImg").attr("src", ctx + "api/gifCode?data=" + new Date() + "");
}

function login() {
    var $loginButton = $("#loginButton");
    var mobile = $(".one input[name='mobile']").val().trim();
    var password = $(".one input[name='password']").val().trim();
    var code = $(".one input[name='code']").val().trim();
    var rememberMe = $(".one input[name='rememberme']").is(':checked');
    if (mobile === "") {
        $MB.n_warning("请输入手机号！");
        return;
    }
    if (password === "") {
        $MB.n_warning("请输入密码！");
        return;
    }
    if (code === "") {
        $MB.n_warning("请输入验证码！");
        return;
    }
    $loginButton.html("").append("<div class='login-loder'><div class='line-scale'><div></div><div></div><div></div><div></div><div></div></div></div>");

    $.ajax({
        type: "post",
        url: ctx + "api/login",
        data: {
            "mobile": mobile,
            "password": password,
            "code": code,
            "rememberMe": rememberMe
        },
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                location.href = ctx + 'index';
            } else {
                reloadCode();
                $MB.n_warning(r.msg);
                $loginButton.html("登录");
            }
        }
    });
}

function regist() {
    var mobile = $(".two input[name='mobile']").val().trim();
    var password = $(".two input[name='password']").val().trim();
    var cpassword = $(".two input[name='cpassword']").val().trim();
    if (mobile === "") {
        $MB.n_warning("手机号不能为空！");
        return;
    } else if (mobile.length > 12) {
        $MB.n_warning("手机号长度不能超过11个字符！");
        return;
    } else if (mobile.length < 3) {
        $MB.n_warning("手机号格式不正确！");
        return;
    }
    if (password === "") {
        $MB.n_warning("密码不能为空！");
        return;
    }
    if (cpassword === "") {
        $MB.n_warning("请再次输入密码！");
        return;
    }
    if (cpassword !== password) {
        $MB.n_warning("两次密码输入不一致！");
        return;
    }
    $.ajax({
        type: "post",
        url: ctx + "api/register",
        data: {
            "mobile": mobile,
            "password": password
        },
        dataType: "json",
        success: function (r) {
            if (r.code === 200) {
                $MB.n_success("注册成功，请登录");
                $(".two input[name='mobile']").val("");
                $(".two input[name='password']").val("");
                $(".two input[name='cpassword']").val("");
                $('.form-toggle').trigger('click');
            } else {
                $MB.n_warning(r.msg);
            }
        }
    });
}

document.onkeyup = function (e) {
    if (window.event)
        e = window.event;
    var code = e.charCode || e.keyCode;
    if (code === 13) {
        login();
    }
};