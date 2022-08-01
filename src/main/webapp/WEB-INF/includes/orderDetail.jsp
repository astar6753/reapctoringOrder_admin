<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/orderDetail.js"></script>
    <link rel="stylesheet" href="/assets/css/manage/orderDetail.css">
</head>
<body>
    <main>
        <section class="orderDetail">
            <div class="datailArea">
                <div class="top">
                    <h1>주문내역상세</h1>
                    <div class="">
                        <p>상호명</p>
                        <p class="detailRestName"></p>
                    </div>
                    <div class="">
                        <p>업장주소</p>
                        <p class="detailAddress"></p>
                    </div>
                    <div class="">
                        <p>고객이름</p>
                        <p class="detailMemberName"></p>
                    </div>
                    <div class="">
                        <p>전화번호</p>
                        <p class="detailPhone"></p>
                    </div>
                    <div class="">
                        <p>배달주소</p>
                        <p class="detailAddress"></p>
                    </div>
                    <div class="">
                        <p>주문일시</p>
                        <p class="detailRegDt"></p>
                    </div>
                </div>
                <div class="bottom">
                    <div class="eachDish">
                        <div class="firstRow">
                            <p class="dishName"></p>
                            <p class="basicPrice"></p>
                            <p class="eachQuantity"></p>
                            <p class="price"></p>
                        </div> 
                        <div class="optionArea">
                            <p class="optText"></p>
                            <p class="optPrice"></p>
                        </div>
                        <div class="eachPriceArea">
                            <p>단품합산가격</p>
                            <p class="eachPrice"></p>
                        </div>
                    </div>
                    <div class="totalPriceArea">
                        <p>총가격</p>
                        <p class="totalPrice"></p>
                    </div>
                </div>
            </div>
            <div class="btn">
                <button onclick="closePopup()">닫기</button>
            </div>
        </section> 
    </main>
</body>
</html>

