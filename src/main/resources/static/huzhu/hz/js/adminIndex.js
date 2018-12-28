comapny.detail={
     name:"",
     socialCreditCode:"",
     linkman :"",
     email:"",
     phone :"",
     address:"",
     type :"",
     auth:""
}

function addCompany(){
       var companyName = $("#companyName").val();
       var creditCode = $("#creditCode").val();
       var sLink = $("#sLink").val();
       var email = $("#email").val();
       var phone = $("#phone").val();
       var address = $("#address").val();
       var type = $("#type").val();
       var auth = $("#authA input[name='auth_situation']:checked").val();
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
                if (data.code == 0) {
                    $('#addCompany').modal('toggle');
                    toastr.success("数据新增成功");
                }
                toastr.error("数据新增失败");
            },
            error:function () {
                window.location.href = '/hzp/login.html';
            }

        });
}

function companyDetail(id){
    $.ajax({
        type: 'POST',
        data: {
            id:id,

        },
        dataType: "JSON",
        url: '/hz/admin/companyDetail',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
               toastr.error(data.msg);$('#companyDetailModal').modal('toggle');
            }else{
                var detail=data.extend.detail;
                    $("#companyNameD").html(detail.name);
                     $("#creditCodeD").html(detail.socialCreditCode);
                    $("#sLinkD").html(detail.linkman);
                    $("#emailD").html(detail.email);
                   $("#phoneD").html(detail.phone);
                    $("#addressD").html(detail.address);
                   $("#typeD").html(detail.type);
                   $("#remarkD").html(detail.remark);
                   if(detail.auth==1)
                   $("#authD").html("正常");
                   else{
                       $("#authD").html("禁用");
                   }
            }
        },
        error:function () {
            window.location.href = '/hzp/login.html';
        }

    });
}


function updateCompany(t){
    var id = $("#companyIdU").val();
    var companyName = $("#companyNameU").val();
    var creditCode = $("#creditCodeU").val();
    var sLink = $("#sLinkU").val();
    var email = $("#emailU").val();
    var phone = $("#phoneU").val();
    var address = $("#addressU").val();
    var type = $("#typeU").val();
    var auth = $("#authU input[name='auth_situation']:checked").val();

    $.ajax({
        type: 'POST',
        data: {
            id:id,
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
        url: '/hz/admin/updateCompany',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);
            }else{
                toastr.success(data.msg);$('#companyDetailModal').modal('toggle');
            }
        },
        error:function () {
            window.location.href = '/hzp/login.html';
        }

    });
}

function updateGetCompany(t) {
   var id=$(t).attr("companyId");
    $.ajax({
        type: 'POST',
        data: {
            id:id
        },
        dataType: "JSON",
        url: '/hz/admin/companyDetail',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);$('#reviseCompany').modal('toggle');
            }else{
                var detail=data.extend.detail;
                $("#companyIdU").val(detail.id);
                $("#companyNameU").val(detail.name);
                $("#creditCodeU").val(detail.socialCreditCode);
                $("#sLinkU").val(detail.linkman);
                $("#emailU").val(detail.email);
                $("#phoneU").val(detail.phone);
                $("#addressU").val(detail.address);
                $("#typeU").val(detail.type);
                $("#remarkU").val(detail.remark);
                if(detail.auth==1)
                    $("#authNormalU").attr("checked","checked");
                else{
                    $("#authForbidU").attr("checked","checked");
                }
            }
        },
        error:function () {
            window.location.href = '/hzp/login.html';
        }

    });
}