aAcount.detail={
     name:"",
     account :"",
     psw:""
}

function getAAccountList(page, size) {
    var name = $("#AANameSearch").val();
    $.ajax({
        type: 'POST',
        data: {
            pageNum:page,
            pageSize:size,
            name:name
        },
        dataType: "JSON",
        url: '/hz/admin/getAAccountList',
        success: function (data) {
            // $("#login-btn").button('reset');
            if(data.code!=0) {
                toastr.error(data.msg);
            }
            var htmlstr=$("#accountDetailListModel").html();
            var htmlstr0="";
            var page = data.extend.page;
            var list = page.list;
            $.each(list,function (k, v) {
                htmlstr0+=htmlstr
                    .replace("{aid}",v.id)
                    .replace("{aidu}",v.id)
                    .replace("{aidd}",v.id)
                    .replace("{aName}",v.name)
                    .replace("{aName0}",v.name)
                    .replace("{account}",v.account)
                    .replace("{account0}",v.account)
                    .replace("{grade}",v.grade)
                    .replace("{parent}",v.parentName)
            });


        // <li><a onclick="getCompanyList(1,10)">&laquo;</a></li>
        //     <li class="active"><a href="#">1</a></li>
        //     <li><a onclick="getCompanyList(1,10)">2</a></li>
        //     <li><a onclick="getCompanyList(1,10)">&raquo;</a></li>
            var firstPage="<li><a onclick=\"getAAccountList("+page.firstPage+",10)\">&laquo;</a></li>";
            var lastPage="<li><a onclick=\"getAAccountList("+page.lastPage+",10)\">&raquo;</a></li>";
            var pageNum="";
            $.each(page.navigatepageNums,function (k, v) {
                var iinn="";
                if(v==page.pageNum)iinn = "class=\"active\"";
                pageNum+="<li "+iinn+" ><a onclick=\"getAAccountList("+v+",10)\">"+v+"</a></li>"
            })

            $("#accountDetailListBody").html(htmlstr0);
            $("#adminAccountPaginationTotal").html("共"+page.pages+"页 "+"共"+page.total+"条");
            $("#adminAccountPagination").html(firstPage+pageNum+lastPage);
        },
        error:function () {
            toastr.error("系统出现异常，请稍后再试");
        }

    });
}

function addAAccount(){
       var name = $("#aAName").val();
       var account = $("#aAccount").val();
       var psw = $("#aAPsw").val();
       var psw0 = $("#aAPsw0").val();
    if(psw!=psw0){$("#pswTips").show();return;}
        $.ajax({
            type: 'POST',
            data: {
                name:name,
                account:account,
                psw:psw
            },
            dataType: "JSON",
            url: '/hz/admin/addAAccount',
            success: function (data) {
                // $("#login-btn").button('reset');
                if (data.code == 0) {
                    $('#addAAccount').modal('toggle');
                    toastr.success("数据新增成功");
                    getAAccountList(1,10);
                }else {
                    toastr.error(data.msg);
                }
            },
            error:function () {
                toastr.error("系统出现异常，请稍后再试");
            }

        });
}

function aAccountDetail(t){
    var id=$(t).attr("aid");
    $.ajax({
        type: 'POST',
        data: {
            id:id,

        },
        dataType: "JSON",
        url: '/hz/admin/aAccountDetail',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
               toastr.error(data.msg);$('#aAccountDetailModal').modal('toggle');
            }else{
                $('#aAccountDetailModal').modal('toggle');
                var detail=data.extend.detail;
                    $("#aANameD").html(detail.name);
                    $("#aAIdD").attr("aaid",detail.id);
                     $("#aAccountD").html(detail.account);
                    $("#aParentD").html(detail.parentName);
                    $("#aGradeD").html(detail.grade);
            }
        },
        error:function () {
            toastr.error(data.msg);
        }

    });
}

function hidePswTips() {
    $("#changePswTips").hide();
    $("#pswTips").hide();
}

function updateAAccount(t){
    var id = $("#aAIdU").val();
    var name = $("#aANameU").val();
    var account = $("#aAccountU").val();
    var psw = $("#aAPswU").val();
    var psw0 = $("#aAPsw0U").val();
    if(psw!=psw0){$("#pswTipsU").show();return;}
    $.ajax({
        type: 'POST',
        data: {
            id:id,
            name:name,
            account:account,
            psw:psw

        },
        dataType: "JSON",
        url: '/hz/admin/updateAAccount',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);
            }else{
                toastr.success(data.msg);$('#reviseAAccount').modal('toggle');
                getAAccountList(1,10);
            }
        },
        error:function () {
            toastr.error(data.msg);
        }

    });
}
function showDeleteAAModal(t) {
    var id=$(t).attr("aaid");
    $("#deleteAAM").attr("aaid",id);
}

function deleteAAccount(t) {
    var id=$(t).attr("aaid");
    $.ajax({
        type: 'POST',
        data: {
            id:id
        },
        dataType: "JSON",
        url: '/hz/admin/deleteAAccount',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);
            }else{
                toastr.success("删除成功");
                getAAccountList(1,10);
            }
        },
        error:function () {
            toastr.error("系统出现异常");
        }

    });
}

function updateGetAAccount(t) {
   var id=$(t).attr("aaid");
    $.ajax({
        type: 'POST',
        data: {
            id:id
        },
        dataType: "JSON",
        url: '/hz/admin/aAccountDetail',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);$('#reviseAAccount').modal('toggle');
            }else{
                var detail=data.extend.detail;
                 $("#aAIdU").val(detail.id);
                 $("#aANameU").val(detail.name);
                 $("#aAccountU").val(detail.account);
            }
        },
        error:function () {
            toastr.error(data.msg);
        }

    });
}
function getRuleList() {
    $.ajax({
        type: 'POST',
        data: {
        },
        dataType: "JSON",
        url: '/hz/admin/getRuleList',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);
            }else{
                var list=data.extend.rule;
                var manpar = $("#manRow");
                var womanpar = $("#womanRow");
                manpar.find(".row").remove();
                womanpar.find(".row").remove();


                $.each(list,function (k,v) {

                    if(v.type==1){
                        $(".slider-minmaxnum"+v.category).val(v.ratio,true);
                    }else {
                        if (v.type == 2) {

                            manpar.append($("#ageModel").html());
                            var preNodeNum = manpar.find(".row .slider-minmaxnumc:last")[0];
                            var preNodeAge = manpar.find(".row .slider-minmaxagec:last")[0];
                            huadong(preNodeNum, "", v.ratio, 0, 10);
                            huadongAge(preNodeAge, "", v.leftAge, v.rightAge, 0, 100);
                        } else if (v.type == 3) {

                            womanpar.append($("#ageModel").html());
                            var preNodeNum = womanpar.find(".row .slider-minmaxnumc:last")[0];
                            var preNodeAge = womanpar.find(".row .slider-minmaxagec:last")[0];
                            huadong(preNodeNum, "", v.ratio, 0, 10);
                            huadongAge(preNodeAge, "", v.leftAge, v.rightAge, 0, 100);
                        }
                    }
                })
            }
        },
        error:function () {
            toastr.error(data.msg);
        }

    });
}

function getFutureRuleList() {
    $.ajax({
        type: 'POST',
        data: {
        },
        dataType: "JSON",
        url: '/hz/admin/getFutureRuleList',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);
            }else{

            }
        },
        error:function () {
            toastr.error(data.msg);
        }

    });
}

function updateRule() {
    var rule=new Object();
    var obj = $("#categoryRow .slider-minmaxnum");
    var categoryarr=new Array()
    $.each(obj,function (k,v) {
        categoryarr.push($(v).val());
    });
    rule.category=categoryarr;

    var ageobj = $("#manRow .slider-minmaxagec");
    var numobj = $("#manRow .slider-minmaxnumc");
    var managearr=new Array()
    $.each(ageobj,function (k,v) {
        var valLow = Math.floor($(v).val()[0]);
        var valUpper = Math.floor($(v).val()[1]);
        managearr.push( [valLow,valUpper,Number($(numobj[k]).val())]);
    });
    rule.manage=managearr;

    var ageobj = $("#womanRow .slider-minmaxagec");
    var numobj = $("#womanRow .slider-minmaxnumc");
    var womanagearr=new Array()
    $.each(ageobj,function (k,v) {
        var valLow = Math.floor($(v).val()[0]);
        var valUpper = Math.floor($(v).val()[1]);
        womanagearr.push( [valLow,valUpper,Number($(numobj[k]).val())]);
    });
    rule.womanage=womanagearr;

   var ruleStr = JSON.stringify(rule);

   var future = $("#activeTime").val();

    $.ajax({
        type: 'POST',
        data: {rule : ruleStr,future:future
        },
        dataType: "JSON",
        url: '/hz/admin/saveRule',
        success: function (data) {
            // $("#login-btn").button('reset');
            if (data.code != 0) {
                toastr.error(data.msg);
            }else{
                getRuleList();
                toastr.success(data.msg);
            }
        },
        error:function () {
            toastr.error(data.msg);
        }
    });
}
