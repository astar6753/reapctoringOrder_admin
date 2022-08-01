popupOpen = false;

$(function(){})
function openPopup(seq){
    $.ajax({
        url:"/api/order/detail?orderSeq="+seq,
        type:"get",
        success:function(r) {
            console.log(r);
            $(".detailRestName").html(r.orderDetail.ri_name);
            $(".detailAddress").html(r.orderDetail.oi_address);
            $(".detailMemberName").html(r.orderDetail.mi_name);
            $(".detailPhone").html(r.orderDetail.mi_phone);
            $(".detailAddress").html(r.orderDetail.ri_address);
            $(".detailRegDt").html(r.orderDetail.oi_reg_dt);
            let totalPrice = r.orderDetail.totalPrice;
            
            $(".bottom").html("");
            let fullTag="";
            for(let i=0; i<r.orderDetail.eachOrderList.length; i++){

                let dishName = r.orderDetail.eachOrderList[i].di_name;
                let basicPrice = r.orderDetail.eachOrderList[i].each_price;
                let eachQuantity = r.orderDetail.eachOrderList[i].each_quantity;
                let price = basicPrice*eachQuantity;
                let eachDishTag = 
                '<div class="eachDish">'+
                    '<div class="firstRow">'+
                        '<p class="dishName">'+dishName+'</p>'+
                        '<p class="basicPrice">'+basicPrice+'원</p>'+
                        '<p class="eachQuantity">'+eachQuantity+'개</p>'+
                        '<p class="price">'+price+'원</p>'+
                    '</div>';
                fullTag += eachDishTag;
                
                if(r.orderDetail.eachOrderList[i].optionList.length!=0){
                    for(let j=0; j<r.orderDetail.eachOrderList[i].optionList.length; j++){
                        let optText = r.orderDetail.eachOrderList[i].optionList[j].desc_name;
                        let optPrice= r.orderDetail.eachOrderList[i].optionList[j].desc_price;
                        let optionTag =
                            '<div class="optionArea">'+
                                '<p class="optText">'+optText+'</p>'+
                                '<p class="optPrice">'+optPrice+'</p>'+
                            '</div>';
                        fullTag += optionTag;
                    }
                }
                let eachPrice = r.orderDetail.eachOrderList[i].each_price;
                let eachPriceTag =                
                    '<div class="eachPriceArea">'+
                        '<p>단품합산가격</p>'+
                        '<p class="eachPrice">'+eachPrice+'원</p>'+
                    '</div>'+
                '</div>';
                fullTag += eachPriceTag;
            }
            
            let totalPriceTag =
                '<div class="totalPriceArea">'+
                    '<p>총가격</p>'+
                    '<p class="totalPrice">'+totalPrice+'원</p>'+
                '</div>';
            fullTag += totalPriceTag;
            $(".bottom").append(fullTag);
            $(".orderDetail").css({"display":"block"});
        },
        error:function(error) {
            console.log(error);
        }
    })
}
function closePopup(){
    $(".orderDetail").css({"display":"none"});
}