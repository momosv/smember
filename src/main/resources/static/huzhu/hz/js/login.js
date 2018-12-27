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
                window.location.href = '/hz/huzhu/login.html';
            }
            $("#userName").text(data.extend.user.name);
        },
        error:function () {
            window.location.href = '/hz/huzhu/login.html';
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
                window.location.href = '/hz/huzhu/login.html';
            }
        },
        error:function () {
            window.location.href = '/hz/huzhu/login.html';
        }

    });
}