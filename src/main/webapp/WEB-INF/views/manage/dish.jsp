<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/imgbox.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/manage/dish.js"></script>
    <link rel="stylesheet" href="/assets/css/manage/dish.css">
</head>
<body>
    <aside>
        <div class="aside_img">
            <div class="aside_restaurant_img">
                <img class="restaurant_img" src="" alt="">
            </div>
        </div>
        <div class="aside_restaurant_area">
            <div class="restaurant_title_area">
                <h2 class="restaurant_title"></h2>
            </div>
            
            <div class="restaurant_info">
                <h2 class="category"></h2>
                <div>
                    <p>최소주문가격
                        <span class="sep">|</span>
                        <span class="min_price"></span>
                    </p>
                </div>
                <div>
                    <p>배달료
                        <span class="sep">|</span>
                        <span class="delivery_fee"></span>
                    </span>
                </div>
                <div>
                    <p>영업시간
                        <span class="sep">|</span>
                        <span class="open_time"></span>
                        <span class="sep">~</span>
                        <span class="end_time"></span>
                    </p>
                </div>
                <div>
                    <h3>설명</h3>
                    <p class="rest_desc"><p>
                </div>
            </div>

            <div class="dish_list_area">
                <h1>메뉴</h1>
                <ul class="dish_list">

                </ul>
            </div>
        </div>
    </aside>

    <main>
        <div class="dish_area">
        </div>
    </main>
</body>
</html>

