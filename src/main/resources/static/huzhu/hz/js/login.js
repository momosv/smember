toastr.options = {
    closeButton: false,                                            // 是否显示关闭按钮，（提示框右上角关闭按钮）
    debug: false,                                                    // 是否使用deBug模式
    progressBar: true,                                            // 是否显示进度条，（设置关闭的超时时间进度条）
    positionClass: "toast-bottom-center",              // 设置提示款显示的位置
    onclick: null,                                                     // 点击消息框自定义事件 
    showDuration: "300",                                      // 显示动画的时间
    hideDuration: "1000",                                     //  消失的动画时间
    timeOut: "2000",                                             //  自动关闭超时时间 
    extendedTimeOut: "1000",                             //  加长展示时间
    showEasing: "swing",                                     //  显示时的动画缓冲方式
    hideEasing: "linear",                                       //   消失时的动画缓冲方式
    showMethod: "fadeIn",                                   //   显示时的动画方式
    hideMethod: "fadeOut"                                   //   消失时的动画方式
};


function checkLogin(userType){
    $.ajax({
        type: 'POST',
        data: {userType:userType},
        dataType: "JSON",
        url: '/hz/login/getCurrent',
        success: function (data) {
            $("#login-btn").button('reset');
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                window.location.href = '/hzp/login.html';
            }
            $("#userName").text(data.extend.user.name);
        },
        error:function () {
            window.location.href = '/hzp/login.html';
        }

    });
}

function loginOut(userType){
    $.ajax({
        type: 'POST',
        data: {userType:userType},
        dataType: "JSON",
        url: '/hz/login/loginOut',
        success: function (data) {
            $("#login-btn").button('reset');
            // $("#login-btn").button('reset');
            if (data.code == 0) {
                window.location.href = '/hzp/login.html';
            }
        },
        error:function () {
            window.location.href = '/hzp/login.html';
        }

    });
}