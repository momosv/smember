function addCompany(){
       var companyName = $("#companyName").val();
       var creditCode = $("#creditCode").val();
       var sLink = $("#sLink").val();
       var email = $("#email").val();
       var phone = $("#phone").val();
       var address = $("#address").val();
       var type = $("#type").val();
       var auth = $("input[name='auth_situation']:checked").val();
        $.ajax({
            type: 'POST',
            data: {
                name:companyName,
                socialCreditCode:creditCode,
                linkman:sLink,
                email:email,
                phone:phone,
                address:address,
                type:type,
                auth:auth
            },
            dataType: "JSON",
            url: '/hz/admin/addCompany',
            success: function (data) {
                // $("#login-btn").button('reset');
                if (data.code != 0) {
                    alert(data.msg)
                }
                alert(data.msg);
            },
            error:function () {
                window.location.href = '/hzp/login.html';
            }

        });



}