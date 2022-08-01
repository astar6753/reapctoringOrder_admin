let offset = 0;

$(function(){
    $("#img_file").change(function(){
        let form = $("#img_form");
        let formData = new FormData(form[0]);
        if($(this).val() == ''||$(this).val() == null) return;
        $.ajax({
            url:"/api/img/"+type,
            type:"put",
            data:formData,
            contentType:false,
            processData:false,
            success:function(r) {
                console.log(r)
                imgView(type,0);
            },
            error:function(error) {
                console.log(error);
            }
        })
    });
})
function imgView(type,offset,seq){
    $(".img_list").html("");
    $.ajax({
        url:"/api/img/user/"+type+"?offset="+offset,
        type:"get",
        success:function(r) {
            console.log(r);
            for(let i = 0; i<r.imgList.length; i++){
                let tag = 
                '<div class="img_box">'
                +'<div class="img_file" filename="'+r.imgList[i].imgBackName+'" style="background-image:url(/api/img/'+type+'/'+r.imgList[i].imgBackName+')">'
                            +'<button onclick=selectImg("'+r.imgList[i].imgSeq+'","'+seq+'")>선택</button>'            
                            +'<button onclick=deleteImg("'+r.imgList[i].imgSeq+'")>&times;</button>'
                            +'</div>'
                            +'<p>'+r.imgList[i].imgFrontName+'</p>'
                            +'</div>';
                $(".img_list").append(tag);
            }
        },
        error:function(error) {
            console.log(error);
        }
    })
}

function next(){
    offset++;
    imgView(type,offset);
}

function prev(){
    if(offset<=0){offset=0; alert("처음입니다."); return;}
    offset--;
    imgView(type,offset);
    
}

function selectImg(imgSeq, seq) {
    $.ajax({
        url:"/api/img/"+type+"?imgSeq="+imgSeq+"&seq="+seq,
        type:"patch",
        success:function(r) {
            console.log(r.messege);
        },
        error:function(error) {
            console.log(error);
        }
    })
}

function deleteImg(imgSeq) {
    if(!confirm("이미지를 삭제하시겠습니까?"))return;
    $.ajax({
        url:"/api/img/"+type+"?imgSeq="+imgSeq,
        type:"delete",
        success:function(r) {
            console.log(r.messege);
            imgView(type,0,seq);
        },
        error:function(error) {
            console.log(error);
        }
    })
}

function closeImgBox(){
    $(".img_list").html("");
    $(".img_popup_area").css({"display":"none"});
    offset = 0;
}