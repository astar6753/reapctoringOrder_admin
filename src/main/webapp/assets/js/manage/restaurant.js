let query = window.location.search;
let param = new URLSearchParams(query);
let page = param.get('page');
if(page==null) page=1;

let type = "restaurant";
let seq = 0;


$(function(){
    $("#cateName").change(function(){
        $("#cateList").html("");
        $("#cateName").attr("cate-seq","")
        let keyword = $("#cateName").val();
        $.ajax({
            url:"/api/restaurant/category?keyword="+keyword,
            type:"get",
            success:function(r) {
                console.log(r);
                if(r.searchResult.length==0)return;
                for (let i = 0; i < r.searchResult.length; i++) {
                    let tag = "<option id='select_cate"+i+"' value="+r.searchResult[i].cateSeq+">"+r.searchResult[i].cateName+"</option>";
                    $("#cateList").append(tag);
                }
                changeSelect();
            }
        })
    })

    $.ajax({
        url:"/api/restaurant/list?page="+page,
        type:"get",
        success:function(r) {
            // console.log(r);
            if(r.restList==null){return;}
            for (let i=0; i<r.restList.length; i++) {
                if(r.restList[i].restImgFile==null)r.restList[i].restImgFile="default.png";
                let tag =   "<tr>"+
                                "<td id='order"+r.restList[i].restSeq+"'>"+(i+1)+"</td>"+
                                "<td><a href='#' onclick='openImgPopUp("+r.restList[i].restSeq+")'><img class='restaurant_small_img' src='/api/img/"+type+"/"+r.restList[i].restImgFile+"'></a></td>"+
                                "<td id='cate"+r.restList[i].restSeq+"' cate-seq="+r.restList[i].cateSeq+">"+r.restList[i].cateName+"</td>"+
                                "<td id='name"+r.restList[i].restSeq+"'>"+r.restList[i].restName+"</td>"+
                                "<td id='price"+r.restList[i].restSeq+"'>"+r.restList[i].restMinPrice+"</td>"+
                                "<td id='fee"+r.restList[i].restSeq+"'>"+r.restList[i].restDeliveryFee+"</td>"+
                                "<td id='addr"+r.restList[i].restSeq+"'>"+r.restList[i].restAddress+"</td>"+
                                "<td>"+
                                    "<span id='open"+r.restList[i].restSeq+"'>"+r.restList[i].restOpenTime+"</span>"+
                                    "<span>~</span>"+
                                    "<span id='end"+r.restList[i].restSeq+"'>"+r.restList[i].restEndTime+"</span>"+
                                "</td>"+
                                "<td id='desc"+r.restList[i].restSeq+"'>"+r.restList[i].restDescription+"</td>"+
                                "<td><button class='dish_btn'><i class='fas fa-edit'></i><a href='/manage/dish?seq="+r.restList[i].restSeq+"'>메뉴관리</a></button></td>"+
                                "<td><button class='edit_btn' onclick='set_popup("+r.restList[i].restSeq+")'><i class='fas fa-edit'></i><span>수정</span></button></td>"+
                                "<td><button class='del_btn' onclick='deleteRestaurant("+r.restList[i].restSeq+")'><i class='fas fa-trash-alt'></i><span>삭제</span></button></td>"+
                            "</tr>";
                $("#restaurant_list").append(tag);
            }

            for(let i=0; i<r.totalPage;i++){
                let pager = '<a href="http://localhost:8888/manage/restaurant?page='+(i+1)+'">'+(i+1)+'</a>';
                $(".pager_area").append(pager);
            }
        },
        error:function(err){
            console.log(err);
            location.href="/member/login"
        }
    })
})

function addRestaurant(){
    if(valid_chk())return;
    let data = {
        memberSeq:"",
        cateSeq: $("#cateName").attr("cate-seq"),
        restName: $("#restName").val(),
        restMinPrice: $("#restMinPrice").val(),
        restDeliveryFee: $("#restDeliveryFee").val(),
        restAddress: $("#restAddress").val(),
        restOpenTime: $("#restOpenTime").val(),
        restEndTime: $("#restEndTime").val(),
        restDescription: $("#restDescription").val()
    }
    $.ajax({
        url:"/api/restaurant/insert",
        type:"put",
        contentType:"application/json",
        data:JSON.stringify(data),
        success:function(r) {
            alert(r.message);
            location.reload();
        },
        error:function(err){
            console.log(err);
            location.href="/member/login"
        }
    })
}
function deleteRestaurant(restSeq){
    if(!confirm("영업장 정보를 삭제하시겠습니까?\n삭제된 정보는 되돌릴 수 없습니다.")) return;
    $.ajax({
        url:"/api/restaurant/delete?restSeq="+restSeq,
        type:"delete",
        success:function(r) {
            alert(r.message);
            location.reload();
        },
        error:function(err){
            alert(err);
            location.href="/member/login"
        }
    })
}
function editRestaurant(){
    if(valid_chk())return;
    let data = {
        restSeq: $("#restName").attr("rest-seq"),
        memberSeq:"",
        cateSeq: $("#cateName").attr("cate-seq"),
        restName: $("#restName").val(),
        restMinPrice: $("#restMinPrice").val(),
        restDeliveryFee: $("#restDeliveryFee").val(),
        restAddress: $("#restAddress").val(),
        restOpenTime: $("#restOpenTime").val(),
        restEndTime: $("#restEndTime").val(),
        restDescription: $("#restDescription").val()
    }
    $.ajax({
        url:"/api/restaurant/update",
        type:"patch",
        contentType:"application/json",
        data:JSON.stringify(data),
        success:function(r) {
            alert(r.message);
            location.reload();
        },
        error:function(err){
            console.log(err);
            location.href="/member/login"
        }
    })
}

function openImgPopUp(restSeq){
    $(".img_popup_area").css({"display":"block"});
    seq = restSeq;
    
    imgView(type,0,seq);
}


function set_popup(mode){
    $(".popup_area").hide();
    $("#cateName").attr("cate-seq","");
    $("#cateList").html("")
    let tag = "<option value='' selected disabled>카테고리 선택</option>"
    $("#cateList").append(tag)
    $("#cateList option:eq(0)").html("카테고리 검색")
    $("#cateName").val("")
    $("#restName").val("")
    $("#restMinPrice").val("")
    $("#restDeliveryFee").val("")
    $("#restAddress").val("")
    $("#restOpenTime").val("")
    $("#restEndTime").val("")
    $("#restDescription").val("");
    if(mode=="add"){
        $(".popup_title").html("영업장 등록")
        $("#mod_btn").hide();
        $("#add_btn").show();
        $(".popup_area").show();
        return;
    }
    else if(mode!=null){
        $("#mod_btn").attr("rest-seq",mode)
        $(".popup_title").html("영업장 정보 수정")
        $("#add_btn").hide();
        $("#mod_btn").show();
        $(".popup_area").show();

        $("#cateName").val($("#cate"+mode+"").html());
        $("#cateName").attr("cate-seq",$("#cate"+mode+"").attr("cate-seq"));
        $("#restName").attr("rest-seq",mode);
        $("#restName").val($("#name"+mode+"").html());
        $("#restMinPrice").val($("#price"+mode+"").html());
        $("#restDeliveryFee").val($("#fee"+mode+"").html());
        $("#restAddress").val($("#addr"+mode+"").html());
        $("#restOpenTime").val($("#open"+mode+"").html());
        $("#restEndTime").val($("#end"+mode+"").html());
        $("#restDescription").val($("#desc"+mode+"").html());
    }
}
function valid_chk(){    
    if(isEmpty($("#cateName").val(),false)) {
        alert("카테고리 이름을 입력하세요");
        return false;
    }
    if(isEmpty($("#restName").val(),false)) {
        alert("영업장 이름을 입력하세요");
        return false;
    }
    if(isEmpty($("#restMinPrice").val(),false)) {
        alert("최소주문가격을 입력하세요");
        return false;
    }
    if(isEmpty($("#restDeliveryFee").val(),false)) {
        alert("배달료를 입력하세요");
        return false;
    }
    if(isEmpty($("#restAddress").val(),false)) {
        alert("주소를 입력하세요");
        return false;
    }
    if(isEmpty($("#restOpenTime").val(),false)) {
        alert("영업시간을 입력하세요");
        return false;
    }
    if(isEmpty($("#restEndTime").val(),false)) {
        alert("영업시간을 입력하세요");
        return false;
    }
}
function changeSelect(){
    $("#cateName").val($("#cateList option:selected").text());
    $("#cateName").attr("cate-seq",$("#cateList option:selected").val());
}