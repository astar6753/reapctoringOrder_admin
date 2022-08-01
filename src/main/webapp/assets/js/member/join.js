let dup_check = false;
$(function(){
    $("#joinId").keyup(function(){
        dup_check = false;
    })

    $("joinPwd, #joinPwd_confirm").keyup(function(){
        if($("#joinPwd").val() != $("#joinPwd_confirm").val()) {
            $("#pwd_not_confirm").html("비밀번호가 일치하지 않습니다.")
            return;
        }
        $("#pwd_not_confirm").html("")
        return;
    })

    $("#id_dup_chk").click(function(){
        if(isEmpty($("#joinId").val())) {
            $("#id_duplicate").html("아이디를 올바르게 입력해주세요")
            $("#id_duplicate").css("display","block");
            return;
        }
        idDuplicatedChk();    
    });
})


function idDuplicatedChk(){
    let id = $("#joinId").val();
    $.ajax({
        url:"/api/member/chk?id="+id,
        type: "get",
        success:function(result){
            if(result.status==false) {
                $("#id_duplicate").html(result.message);
                $("#id_duplicate").css("display","block");
            }
            else {
                dup_check = true;
                $("#id_duplicate").html(result.message);
                $("#id_duplicate").css("display","block");
            }
        }
    })
}
function memberJoin(){
    if(!dup_check) {
        alert("아이디를 중복 체크를 해주세요.");
        return;
    }
    if(!confirm("회원가입 하시겠습니까?"))return;

    if(isEmpty($("#joinPwd").val())) {alert("비밀번호를 올바르게 입력해주세요.");return;}
    if(isEmpty($("#joinName").val())) {alert("이름을 올바르게 입력해주세요.");return;}
    if(isEmpty($("#joinPhone").val())) {alert("전화번호를 올바르게 입력해주세요.");return;}

    if($("#joinPwd").val() != $("#joinPwd_confirm").val()) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }
    if(!nameValidate($("#joinName").val())) {
        alert("이름을 올바르게 입력해주세요");
        return;
    }
    if(!phoneNumberValidate($("#joinPhone").val())) {
        alert("전화번호 형식 틀림");
        return;
    }
    
    let data = {
        "memberId" : $("#joinId").val(),
        "memberPwd" : $("#joinPwd").val(),
        "memberName" : $("#joinName").val(),
        "memberPhone" : $("#joinPhone").val(),
        "memberAddress" : $("#joinAddress").val()
    }
    console.log(data);
    $.ajax({
        url:"/api/member/join",
        type:"put",
        data:JSON.stringify(data),
        contentType:"application/json",
        success:function(result){
            alert(result.message)
            location.href = "/"
        },
        error:function(err){
            console.log(err);
        }
    })
}

function cancel(){
        confirm("회원가입을 취소하고 돌아가시겠습니까?\n입력된 정보는 저장되지 않습니다.")
        location.href = "/member/login"
}
