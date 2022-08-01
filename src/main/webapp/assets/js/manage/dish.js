let query = window.location.search;
let param = new URLSearchParams(query);
let restSeq = param.get('seq');

let seq = 0;
let type = "dish";



$(function(){
    $.ajax({
        url:"/api/dish/list?restSeq="+restSeq,
        type:"get",
        success:function(r) {
            console.log(r);
            
            //aside정보 
            $(".restaurant_img").attr("src","/api/img/restaurant/"+r.resultData.restInfo.restImgFile)
            $(".restaurant_title").html(r.resultData.restInfo.restName);
            $(".category").html(r.resultData.restInfo.cateName);
            $(".min_price").html(r.resultData.restInfo.restMinPrice+"원");
            $(".delivery_fee").html(r.resultData.restInfo.restDeliveryFee+"원");
            $(".open_time").html(r.resultData.restInfo.restOpenTime);
            $(".end_time").html(r.resultData.restInfo.restEndTime);
            $(".rest_desc").html(r.resultData.restInfo.restDescription);
            
            let full_tag = "";
            
            if(r.resultData.dishList==null){
                full_tag = "<a href='#'>등록된 메뉴가 없습니다. 지금 등록하기</a>";
                $(".dish_area").append(full_tag);
                return;
            }
            else{
                for(let i = 0; i < r.resultData.dishList.length; i++) {
                    //aside메뉴 태그
                    let tag='<li>'+r.resultData.dishList[i].dishName+" "+r.resultData.dishList[i].dishPrice+'</li>';
                    $(".dish_list").append(tag);
                    let dish_list_tag=
                        '<div class="dish_list_wrap">'+
                            '<div class="content_left_box">'+
                                '<div class="dish_img_area">'+
                                    '<a class="img_box_link" href="#">'+
                                        '<img class="img_btn" onclick="open_img_box('+r.resultData.dishList[i].dishSeq+')" src="/api/img/dish/'+r.resultData.dishList[i].dishImgFile+'" alt="">'+
                                        '<img class="dish_img" src="/api/img/dish/'+r.resultData.dishList[i].dishImgFile+'" alt="">'+
                                    '</a>'+
                                '</div>'+
                                '<div class="dish_title_area">'+
                                    '<h1>'+r.resultData.dishList[i].dishName+'</h1>'+
                                '</div>'+
                            '</div>'+
                            '<div class="content_right_box">'+
                                '<div class="dish_block">'+
                                    '<p>가격<span class="sep"> | </span>'+r.resultData.dishList[i].dishPrice+'원</p>'+
                                    '<p class="desc">'+r.resultData.dishList[i].dishDescription+'</p>'+
                                '</div>'+
                                '<div class="option_area">';
                                full_tag += dish_list_tag;
                                    if(r.resultData.dishList[i].blockList!=null){
                                        if(r.resultData.dishList[i].blockList==0){
                                            let no_block_tag = "<a id='no_block' href='#'>등록된 옵션이 없습니다. 지금 등록하기</a>";
                                            full_tag += no_block_tag;
                                        }
                                        for(let j = 0; j < r.resultData.dishList[i].blockList.length; j++) {
                                            let option_box_tag1 =
                                            '<div class="option_block">'+
                                                '<h3 class="option_title">'+r.resultData.dishList[i].blockList[j].optionTitle+'|</h3>';
                                                full_tag += option_box_tag1;
                                                if(r.resultData.dishList[i].blockList[j].descList!=null){
                                                    for(let k = 0; k< r.resultData.dishList[i].blockList[j].descList.length; k++) {
                                                        let option_desc_tag = '<p class="option_desc">'+r.resultData.dishList[i].blockList[j].descList[k].descText+'|'+r.resultData.dishList[i].blockList[j].descList[k].dishPrice+'</p>'
                                                        full_tag += option_desc_tag;                            
                                                    }
                                                }
                                            let option_box_tag2 = '</div>';
                                            full_tag += option_box_tag2;
                                        }
                                    }
                            let close_tag = '</div>'+
                            '</div>'+
                        '</div>';
                        full_tag += close_tag;
                }
            }
            $(".dish_area").append(full_tag);


        }


    })
})


function open_img_box(dishSeq){
    seq = dishSeq
    $(".img_popup_area").css({"display":"block"});
    imgView(type,0,seq);
}
