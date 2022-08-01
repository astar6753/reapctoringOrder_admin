$(function(){
    $.ajax({
        url:"/api/member/info",
        type:"get",
        success:function(r){
            console.log(r);
            $(".memberId").html(r.memberInfo.memberId)
            $(".memberName").html(r.memberInfo.memberName)
            $(".memberAddress").html(r.memberInfo.memberAddress)
            $(".memberPhone").html(r.memberInfo.memberPhone)
        },
        error:function(err){
            console.log(err);
            location.href = "/";
        }
    })
    
    $("#modify").click(function(){
    })
})

function setPopUp(){
    $("#modInfoOpen").css({"display":"none"});
    $("#modPwdOpen").css({"display":"none"});
    $(".memberinfoArea").css({"display":"none"});
}
function modifyInfoOpen(){
    setPopUp();
    $("#modifyInfoBtn").css({"display":"block"});
    $("#cancel").css({"display":"block"});
    $(".modifyInfoArea").css({"display":"block"});

    $("#modifyName").val($(".memberName").html());
    $("#modifyAddress").val($(".memberAddress").html());
    $("#modifyPhone").val($(".memberPhone").html());
    $("#modifyPwd").val("");
}

function modifyPwdOpen(){
    setPopUp();
    $("#originPwd").val("");
    $("#newPwd").val("");
    $("#pwd_confirm").val("");

    $("#modifyPwdBtn").css({"display":"block"});
    $("#cancel").css({"display":"block"});
    $(".modifyPwdArea").css({"display":"block"});
    
}
function cancel(){
    $("#modifyInfoBtn").css({"display":"none"});
    $("#modifyPwdBtn").css({"display":"none"});
    $(".modifyInfoArea").css({"display":"none"});
    $(".modifyPwdArea").css({"display":"none"});
    $("#cancel").css({"display":"none"});
    $(".memberinfoArea").css({"display":"block"});
    $("#modInfoOpen").css({"display":"block"});
    $("#modPwdOpen").css({"display":"block"});
    
    




}
function modifyInfo(){
    if(!confirm("회원정보를 수정하시겠습니까?"))return;


    if(isEmpty($("#modifyName").val())) {alert("이름을 올바르게 입력해주세요.");return;}
    if(isEmpty($("#modifyPhone").val())) {alert("전화번호를 올바르게 입력해주세요.");return;}
    if(isEmpty($("#modifyAddress").val(),false)) {alert("주소를 올바르게 입력해주세요.");return;}
    if(isEmpty($("#modifyPwd").val())) {alert("비밀번호를 올바르게 입력해주세요.");return;}

    if(!nameValidate($("#modifyName").val())) {
        alert("이름을 올바르게 입력해주세요");
        return;
    }
    if(!phoneNumberValidate($("#modifyPhone").val())) {
        alert("전화번호 형식 틀림");
        return;
    }
    
    let data = {
        "memberSeq" : "",  //컨트롤러에서 세션값으로 채워줌
        "memberPwd" : $("#modifyPwd").val(),
        "memberName" : $("#modifyName").val(),
        "memberPhone" : $("#modifyPhone").val(),
        "memberAddress" : $("#modifyAddress").val()
    }
    console.log(data);
    $.ajax({
        url:"/api/member/info/update",
        type:"patch",
        data:JSON.stringify(data),
        contentType:"application/json",
        success:function(r){
            alert(r.message);
            location.reload();
        },
        error:function(err){
            console.log(err);
            location.href = "/";
        }
    })

}
function modifyPwd(){
    if(!confirm("비밀번호를 변경하시겠습니까?"))return;

    if(isEmpty($("#originPwd").val())) {alert("비밀번호를 올바르게 입력해주세요.");return;}
    if(isEmpty($("#newPwd").val())) {alert("비밀번호를 올바르게 입력해주세요.");return;}
    if(isEmpty($("#pwd_confirm").val())) {alert("비밀번호를 올바르게 입력해주세요.");return;}
    
    //변경할 비밀번호 비밀번호 확인 값 일치체크
    if(!($("#newPwd").val()==$("#pwd_confirm").val())){alert("비밀번호를 올바르게 입력해주세요.");return;}
    
    let data = {
        "memberSeq" : "",  //컨트롤러에서 세션값으로 채워줌
        "newPwd" : $("#newPwd").val(),
        "originPwd" : $("#originPwd").val()
    }
    $.ajax({
        url:"/api/member/pwd/update",
        type:"patch",
        data:JSON.stringify(data),
        contentType:"application/json",
        success:function(r){
            alert(r.message);
            location.reload();
        },
        error:function(err){
            console.log(err);
            location.href = "/";
        }
    })
}