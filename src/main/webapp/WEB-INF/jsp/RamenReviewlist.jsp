<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.RamenDto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%
	if(session.getAttribute("user") != null){
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1 ">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<link rel="stylesheet" type="text/css" href="/static/css/ramencommon.css">
<link rel="stylesheet" type="text/css" href="/static/css/ramenreviewlist.css">

<script src="static/js/ramenscript.js"></script>
<title>${ramen.shopName} の口コミ</title>
</head>
<body>
<div id="wrap">
    <div class="header">
        こんにちは、${user.username} さん<br />
        <a type="button" class="btn btn-clear" href="Logout">ログアウト</a>
    </div>
    <h3>${ramen.shopName} の口コミ一覧</h3>
    <hr> 
    <c:forEach items="${reviewList}" var="review">
    <table class="review-list">
    	<tr><td>${review.userid}さんの口コミ
    	<c:if test="${user.userid == review.userid}">
    		<div class="right"><f:form style="display:inline" ModelAttribute="dspReviewUpdateForm" action="DspRamenReviewUpdate" method="POST">
				<input type="hidden" name="reviewid" value="${review.reviewid}"/>
				<input type="hidden" name="shopid" value="${ramen.shopid}"/>
				<input type="hidden" name="name" value="${ramen.shopName}"/>
				<input type="submit" id="register" class="btn btn-update" value="編集">
			</f:form>
			<f:form style="display:inline" ModelAttribute="dspReviewDeleteForm" action="RamenReviewDelete" method="POST">
				<input type="hidden" name="reviewid" value="${review.reviewid}"/>
				<input type="hidden" name="shopid" value="${ramen.shopid}"/>
				<input type="submit" class="btn btn-delete" id="3" value="削除" onclick="return confirm_review(this.id)"/>
			</f:form></div>
    	</c:if>
    	</td></tr>
    	<tr><td>評価：${review.valueLabel}</td></tr>
    	<tr><td>来訪日：${review.visitday.toString().substring(0, 10)}</td></tr>
    	<tr><td>投稿日時：${review.registerdate.toString().substring(0, 16)}</td></tr>
    	<tr><td>更新日時：${review.lastupdate.toString().substring(0, 16)}</td></tr>
    	<tr><td class="review-title">${review.reviewTitle}</td></tr>
    	<c:if test="${!empty review.filenames}">
    	<tr><td>
    		<c:forEach items="${review.filenames}" var="filename">
				<img src="/static/upload/review/${filename}" width="100" height="80">　
			</c:forEach>
    	</td></tr>
    	</c:if>
    	<tr><td>${review.reviewBr}</td></tr>
    </table>
    </c:forEach>
	<div class="center" >
	<c:if test="${ramen.reviewCount == 0}">
    	<f:form style="display:inline" ModelAttribute="dspReviewRegisterForm" action="DspRamenReviewInsert" id="register" method="POST">
			<input type="hidden" name="shopid" value="${ramen.shopid}"/>
			<input type="hidden" name="name" value="${ramen.shopName}"/>
			<input type="hidden" name="userid" value="${userId}"/>
			<input type="submit" id="register" class="btn btn-search" value="口コミを投稿">
		</f:form>
	</c:if>
		<form style="display:inline" action="DspRamenSearch" method="post">
			<input type="submit" class="btn btn-clear" value="一覧に戻る" />
		</form>
	</div>
</div>
</body>
</html>
<%
	}else{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/"); // ログイン画面遷移
		dispatcher.forward(request, response);
	}
%>