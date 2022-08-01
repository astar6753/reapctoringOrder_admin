<%@page language ="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="/assets/js/member/info.js"></script>
    <link rel="stylesheet" href="/assets/css/member/info.css">
</head>
<body>
    <main>
        <h1 class="title">회원정보</h1>
        <div class="memberInfoBox">
            <div class="memberinfoArea">
                <table>
                    <tbody>
                        <tr>
                            <td class="">ID</td>
                            <td class="memberId">ID</td>
                        </tr>
                        <tr>
                            <td class="">이름</td>
                            <td class="memberName">이름</td>
                        </tr>
                        <tr>
                            <td class="">전화번호</td>
                            <td class="memberAddress">전화번호</td>
                        </tr>
                        <tr>
                            <td class="">주소</td>
                            <td class="memberPhone">주소</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="modifyInfoArea">
                <table>
                    <tbody>
                        <tr>
                            <td>이름</td>
                            <td>
                                <input type="text" id="modifyName" placeholder="이름 (50자 이내)" autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <td>전화번호</td>
                            <td>
                                <input type="text" id="modifyPhone" placeholder="- 을 빼고 입력하세요." autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <textarea name="" id="modifyAddress" cols="21" rows="3"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td>
                                <input type="password" id="modifyPwd" placeholder="비밀번호 입력">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="modifyPwdArea">
                <table>
                    <tbody>
                        <tr>
                            <td>기존 비밀번호</td>
                            <td>
                                <input type="password" id="originPwd" placeholder="기존 비밀번호 (16자 이내)">
                            </td>
                        </tr>
                        <tr>
                            <td>변경할 비밀번호</td>
                            <td>
                                <input type="password" id="newPwd" placeholder="변경할 비밀번호 (16자 이내)">
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호 확인</td>
                            <td>
                                <input type="password" id="pwd_confirm" placeholder="비밀번호 확인">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="btn">
                <a href="#" id="modInfoOpen" onclick="modifyInfoOpen()">회원정보 수정하기</a>                
                <button id="modifyInfoBtn" onclick="modifyInfo()">회원정보 수정</button>
                <a href="#" id="modPwdOpen" onclick="modifyPwdOpen()">비밀번호 변경하기</a>
                <button id="modifyPwdBtn" onclick="modifyPwd()">비밀번호 변경</button>
                <button id="cancel" onclick="cancel()">취소</button>
            </div>
        </div>
    </main>
</body>
</html>