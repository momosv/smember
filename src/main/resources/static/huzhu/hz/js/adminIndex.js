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

function getCompanyList(page, size) {
    var companyName = $("#companyNameSearch").val();
    $.ajax({
        type: 'POST',
        data: {
            pageNum:page,
            pageSize:size,
            companyName:companyName
        },
        dataType: "JSON",
        url: '/hz/admin/getCompanyList',
        success: function (data) {
            // $("#login-btn").button('reset');
            if(data.code!=0) {
                toastr.error(data.msg);
            }
            var htmlstr=$("#detailListModel").html();
            var htmlstr0="";
            var page = data.extend.page;
            var list = page.list;
            $.each(list,function (k, v) {
                var authStr="未知";
                if(v.auth==1)
                    authStr="正常";
                else{
                    authStr="禁用";
                }

                htmlstr0+=htmlstr
                    .replace("{cid}",v.id)
                    .replace("{cidu}",v.id)
                    .replace("{cidd}",v.id)
                    .replace("{companyName}",v.name)
                    .replace("{companyName0}",v.name)
                    .replace("{code}",v.socialCreditCode)
                    .replace("{code0}",v.socialCreditCode)
                    .replace("{linkman}",v.linkman)
                    .replace("{phone}",v.phone)
                    .replace("{email}",v.email)
                    .replace("{email0}",v.email)
                    .replace("{auth}",authStr)
            });


        // <li><a onclick="getCompanyList(1,10)">&laquo;</a></li>
        //     <li class="active"><a href="#">1</a></li>
        //     <li><a onclick="getCompanyList(1,10)">2</a></li>
        //     <li><a onclick="getCompanyList(1,10)">&raquo;</a></li>
            var firstPage="<li><a onclick=\"getCompanyList("+page.firstPage+",10)\">&laquo;</a></li>";
            var lastPage="<li><a onclick=\"getCompanyList("+page.lastPage+",10)\">&raquo;</a></li>";
            var pageNum="";
            $.each(page.navigatepageNums,function (k, v) {
                var iinn="";
                if(v==page.pageNum)iinn = "class=\"active\"";
                pageNum+="<li "+iinn+" ><a onclick=\"getCompanyList("+v+",10)\">"+v+"</a></li>"
            })

            $("#companyListBody").html(htmlstr0);
            $("#companyPaginationTotal").html("共"+page.pages+"页 "+"共"+page.total+"条");
            $("#companyPagination").html(firstPage+pageNum+lastPage);
        },
        error:function () {
            window.location.href = '/hzp/login.html';
        }

    });
}

function addCompany(){
       var companyName = $("#companyName").val();
       var creditCode = $("#creditCode").val();
       var sLink = $("#sLink").val();
       var email = $("#email").val();
       var phone = $("#phone").val();
       var address = $("#address").val();
       var type = $("#type").val();
       var auth = $("#authAA").val();
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
                    getCompanyList(1,10);
                }else {
                    toastr.error("数据新增失败");
                }
            },
            error:function () {
                window.location.href = '/hzp/login.html';
            }

        });
}

function companyDetail(t){

    var id=$(t).attr("cid");
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
                $('#companyDetailModal').modal('toggle');
                var detail=data.extend.detail;
                    $("#companyNameD").html(detail.name);
                    $("#companyIdD").attr("companyId",detail.id);
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
    var remark = $("#remarkU").val();
    var auth = $("#authUU").val();

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
            auth:auth,
            remark:remark

        },
        dataType: "JSON",
        url: '/hz/admin/updateCompany',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);
            }else{
                toastr.success(data.msg);$('#reviseCompany').modal('toggle');
                getCompanyList(1,10);
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
                $("#authUU").val(detail.auth);

            }
        },
        error:function () {
            window.location.href = '/hzp/login.html';
        }

    });
}

function getCompanyAmount(t){
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
                toastr.error(data.msg);$('#addCompanyAmount').modal('toggle');
            }else{
                var detail=data.extend.detail;
                $("#companyIdAmountU").val(detail.id);
                $("#preAmount").html(detail.amount);
            }
        },
        error:function () {
            toastr.error("系统出现异常");
        }

    });
}


function hideAmountTips() {
    $("#amountTips").hide();
}
function updateCompanyAmount(){
    var id=$("#companyIdAmountU").val();
    var amount= $("#addAmount").val();
    var amount0 = $("#addAmount0").val();
    if(amount!=amount0){$("#amountTips").show();return;}
    $.ajax({
        type: 'POST',
        data: {
            id:id,
            amount:amount
        },
        dataType: "JSON",
        url: '/hz/admin/updateCompanyAmount',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);
            }else{
                $("#addCompanyAmount").modal("toggle");
                toastr.success(data.msg);
            }

        },
        error:function () {
            toastr.error("系统出现异常");
        }

    });
}
function amountToChinese(t,area) {
    var money = $(t).val();
    var fraction = ['角','分'];
    var digit = ['零','壹','贰','叁','肆','伍','陆','柒','捌','玖'];
    var unit = [['元','万','亿'],['','拾','佰','仟']];
    var head = money < 0?'欠':'';
    money = Math.abs(money);
    var s = '';
    for (var i = 0; i < fraction.length; i++) {
        s += (digit[Math.floor(money * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
    }
    s = s || '整';
    money = Math.floor(money);
    for (var i = 0; i < unit[0].length && money > 0; i++) {
        var p = '';
        for (var j = 0; j < unit[1].length && money > 0; j++) {
            p = digit[money % 10] + unit[1][j] + p;
            money = Math.floor(money / 10);
        }
        s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
    }
    var sum= head + s.replace(/(零.)*零元/,'元').replace(/(零.)+/g, '零').replace(/^整$/, '零元整');
    $("#"+area).html(sum);

}
function hideChangePSWTips() {
    $("#changePswTips").hide();
}

function changePsw(){
    var oldPsw = $("#oldPSW").val();
    var psw = $("#newPSW").val();
    var psw0 = $("#newPSW0").val();
    if(psw!=psw0){$("#changePswTips").show();return;}

    $.ajax({
        type: 'POST',
        data: {
            psw:psw,
            oldPsw:oldPsw
        },
        dataType: "JSON",
        url: '/hz/admin/changePsw',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);
            }else{
                toastr.success(data.msg);
            }

        },
        error:function () {
            toastr.error("系统出现异常");
        }

    });
    $("#oldPSW").val("");
    $("#newPSW").val("");
    $("#newPSW0").val("");
}

