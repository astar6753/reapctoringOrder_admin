<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<%@include file="/WEB-INF/includes/orderDetail.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/manage/order.js"></script>
</head>
<body>
    <main>
        <section class="manageOrder">
            <h1>주문 조회</h1>
            <div class="tabelAraa">
                <table>
                    <thead>
                        <tr>
                            <td>순서</td>
                            <td>주문번호</td>
                            <td>영업장</td>
                            <td>주문자</td>
                            <td>주문자연락처</td>
                            <td>주문자등급</td>
                            <td>배달지</td>
                            <td>주문일시</td>
                            <td>주문상태</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody class="orderListArea">            
                    </tbody>
                </table>
            </div>
            <div class="pagerArea">

            </div>
        </section>
        

    </main>
</body>
</html>
