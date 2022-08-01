let query = window.location.search;
let param = new URLSearchParams(query);
let page = param.get('page');
if(page==null) page=1;

$(function(){
    $.ajax({
        url:"/api/order/list?page="+page,
        type:"get",
        success:function(r) {
            console.log(r);
            for(let i=0; i<r.orderList.length; i++){
                
                let orderSeq=r.orderList[i].oi_seq;
                let restName=r.orderList[i].ri_name;
                let memberId=r.orderList[i].mi_id;
                let memberName=r.orderList[i].mi_name;
                let memberPhone=r.orderList[i].mi_phone;
                let memberGrade=r.orderList[i].mi_grade;
                if(memberGrade==2) memberGrade="Bronze";
                else if(memberGrade==3) memberGrade="Silver";
                else if(memberGrade==4) memberGrade="Gold";
                let orderAddress=r.orderList[i].oi_address;
                let orderRegDt=r.orderList[i].oi_reg_dt;
                let orderStatus=r.orderList[i].oi_status;
                if(orderStatus==1) memberGrade="주문완료";
                else if(orderStatus==2) memberGrade="배달중";
                else if(orderStatus==3) memberGrade="배달완료";

                let tag =
                '<tr>'+
                    '<td class="no">'+(i+1)+'</td>'+
                    '<td class="orderSeq">'+orderSeq+'</td>'+
                    '<td class="restName">'+restName+'</td>'+
                    '<td class="memberName">'+memberName+'('+memberId+')'+'</td>'+
                    '<td class="memberPhone">'+memberPhone+'</td>'+
                    '<td class="memberGrade">'+memberGrade+'</td>'+
                    '<td class="orderAddress">'+orderAddress+'</td>'+
                    '<td class="orderRegDt">'+orderRegDt+'</td>'+
                    '<td class="orderStatus">'+orderStatus+'</td>'+
                    '<td><a href="#" onclick="openPopup('+orderSeq+')">상세</a></td>'+
                '</tr>';
                $(".orderListArea").append(tag);
            }

            for(let i=0; i<r.totalPage;i++){
                let pager = '<a href="http://localhost:8888/manage/order?page='+(i+1)+'">'+(i+1)+'</a>';
                $(".pagerArea").append(pager);
            }

        }
    })

})