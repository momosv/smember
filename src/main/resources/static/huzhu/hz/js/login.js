function checkLogin(userType){
    $.ajax({
        type: 'POST',
        data: {userType:userType},
        dataType: "JSON",
        url: 'http://127.0.0.1:8081/hz/login/getCurrent',
        success: function (data) {
            $("#login-btn").button('reset');
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                window.location.href = 'http://127.0.0.1:8081/hz/login.html';
            }
            $("#userName").text(data.extend.user.name);
        },
        error:function () {
            window.location.href = 'http://127.0.0.1:8081/hz/login.html';
        }

    });
}

function loginOut(userType){
    $.ajax({
        type: 'POST',
        data: {userType:userType},
        dataType: "JSON",
        url: 'http://127.0.0.1:8081/hz/login/getCurrent',
        success: function (data) {
            $("#login-btn").button('reset');
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                window.location.href = 'http://127.0.0.1:8081/hz/login.html';
            }
            $("#userName").text(data.extend.user.name);
        },
        error:function () {
            window.location.href = 'http://127.0.0.1:8081/hz/login.html';
        }

    });
}