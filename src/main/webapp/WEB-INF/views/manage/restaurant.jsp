<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/imgbox.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/manage/restaurant.js"></script>
    <link rel="stylesheet" href="/assets/css/manage/restaurant.css">
</head>
<body>
    <main>
        <section class="manage_restaurant">
            <h1>영업장 관리</h1>
            <button id="open_popup" onclick="set_popup('add')">
                <i class="fas fa-plus-square"></i><span>영업장 추가</span>
            </button>
            <div class="restaurant_table">
                <table>
                    <thead>
                        <tr>
                            <td>번호</td>
                            <td>로고</td>
                            <td>분류</td>
                            <td>상호명</td>
                            <td>최소주문가격</td>
                            <td>배달료</td>
                            <td>위치</td>
                            <td>영업시간</td>
                            <td>설명</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody id="restaurant_list">
                    </tbody>
                </table>
            </div>
            <div class="pager_area">
                
            </div>
        </section>

        <section class="popup_area">
            <div class="restaurant_add">
                <h1 class="popup_title"></h1>
                <div class="content">
                    <p>분류</p>
                        <input type="text" id="cateName" placeholder="카테고리 검색">
                        <div class="search_area">
                            <select id="cateList" onchange="changeSelect()">
                                <option value='' selected disabled>카테고리 선택</option>
                            </select>                            
                        </div>
                    <p>상호명</p><input type="text" id="restName">
                    <p>최소주문가격</p><input type="text" id="restMinPrice">
                    <p>배달료</p><input type="text" id="restDeliveryFee">
                    <p>위치</p><input type="text" id="restAddress">
                    <p>영업시간</p><input type="text" id="restOpenTime"><span>~</span><input type="text" id="restEndTime">
                    <p>설명</p><textarea id="restDescription"></textarea>
                </div>
                <div class="btn">
                    <button id="add_btn" onclick="addRestaurant()">등록</button>
                    <button id="mod_btn" onclick="editRestaurant()">수정</button>
                    <button id="cancel" onclick="set_popup()">취소</button>
                </div>
            </div>
        </section>

    </main>
</body>
</html>
