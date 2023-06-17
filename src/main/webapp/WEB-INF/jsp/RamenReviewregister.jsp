<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="/static/css/ramenreviewregister.css">

<script src="static/js/ramenscript.js"></script>
<title>${review.shopName} の口コミ</title>
</head>
<body>
<div id="wrap">
	<div class="header">
		こんにちは、${user.username} さん<br />
		<a type="button" class="btn btn-clear" href="Logout">ログアウト</a>
	</div>
    <c:choose>
		<c:when test="${review.reviewid > 0}">
			<h3>${review.shopName} の口コミ更新</h3>
		</c:when>
		<c:otherwise>
			<h3>${review.shopName} の口コミ投稿</h3>
		</c:otherwise>
    </c:choose>
    <hr>
    	<c:choose>
			<c:when test="${review.reviewid > 0}">
				<form name="form" style="display:inline" ModelAttribute="reviewUpdateForm" action="RamenReviewUpdate" enctype="multipart/form-data" method="POST">
			</c:when>
			<c:otherwise>
				<form name="form" style="display:inline" ModelAttribute="reviewRegisterForm" action="RamenReviewInsert" enctype="multipart/form-data" method="POST">
			</c:otherwise>
		</c:choose>
		<table class="review-upd">
		<tr><th><label>写真</label></th><td><input type="file" name="uploadfiles" onchange="OnFileSelect( this );" accept="image/*" multiple>
		<ul id="ID001" ></ul>
		<br><br>
		<c:forEach items="${review.filenames}" var="filename">
			<img src="/static/upload/review/${filename}" width="100" height="80">
		</c:forEach>
		</td></tr>
		<tr><th><label>来訪日<font color= "color:#ff0000" size="2">(※必須)</font></label></th><td><input type="date" name="visitday" class="date-review" autocomplete="off" value="${review.visitday.toString().substring(0, 10)}" required /></td></tr>
		<tr><th><label>評価<font color= "color:#ff0000" size="2">(※必須)</font></label></th>
		<td>
		<select name="value" class="select-upd">
			<c:choose>
				<c:when test="${review.valueLabel == \"☆★★★★\"}">
					<option value="0">評価を選択</option>
					<option value="1" selected>☆</option>
					<option value="2">☆☆</option>
					<option value="3">☆☆☆</option>
					<option value="4">☆☆☆☆</option>
					<option value="5">☆☆☆☆☆</option>
				</c:when>
				<c:when test="${review.valueLabel == \"☆☆★★★\"}">
					<option value="0">評価を選択</option>
					<option value="1">☆</option>
					<option value="2" selected>☆☆</option>
					<option value="3">☆☆☆</option>
					<option value="4">☆☆☆☆</option>
					<option value="5">☆☆☆☆☆</option>
				</c:when>
				<c:when test="${review.valueLabel == \"☆☆☆★★\"}">
					<option value="0">評価を選択</option>
					<option value="1">☆</option>
					<option value="2">☆☆</option>
					<option value="3" selected>☆☆☆</option>
					<option value="4">☆☆☆☆</option>
					<option value="5">☆☆☆☆☆</option>
				</c:when>
				<c:when test="${review.valueLabel == \"☆☆☆☆★\"}">
					<option value="0">評価を選択</option>
					<option value="1">☆</option>
					<option value="2">☆☆</option>
					<option value="3">☆☆☆</option>
					<option value="4" selected>☆☆☆☆</option>
					<option value="5">☆☆☆☆☆</option>
				</c:when>
				<c:when test="${review.valueLabel == \"☆☆☆☆☆\"}">
					<option value="0">評価を選択</option>
					<option value="1">☆</option>
					<option value="2">☆☆</option>
					<option value="3">☆☆☆</option>
					<option value="4">☆☆☆☆</option>
					<option value="5" selected>☆☆☆☆☆</option>
				</c:when>
				<c:otherwise>
					<option value="0" selected>評価を選択</option>
					<option value="1">☆</option>
					<option value="2">☆☆</option>
					<option value="3">☆☆☆</option>
					<option value="4">☆☆☆☆</option>
					<option value="5">☆☆☆☆☆</option>
				</c:otherwise>
			</c:choose>
		</select>
		</td></tr>
    	<tr><th><label>タイトル<font color= "color:#ff0000" size="2">(※必須)</font></label></th><td><input type="text" name="reviewtitle" class="text-review" autocomplete="off" value="${review.reviewTitle}" required /></td></tr>
    	<tr><th><label>本文<font color= "color:#ff0000" size="2">(※必須)</font></label><td><textarea name="review" class="textarea-review" required>${review.review}</textarea></td></tr>
    	</table>
		<input type="hidden" name="shopid" value="${review.shopid}"/>
		<input type="hidden" name="name" value="${review.shopName}"/>
		<input type="hidden" name="userid" value="${loginName}"/>
		<input type="hidden" name="reviewid" value="${review.reviewid}"/>
		<c:choose>
		<c:when test="${review.reviewid > 0}">
			<input type="button" class="btn btn-update" value="更新" onclick="return registerreview_confirm(form)">
		</c:when>
		<c:otherwise>
			<input type="button" class="btn btn-search" value="投稿" onclick="return registerreview_confirm(form)">
		</c:otherwise>
		</c:choose>
	</form>
	<f:form style="display:inline" ModelAttribute="ReviewlistForm" action="DspRamenReviewSearch" method="post">
		<input type="hidden" name="shopid" value="${review.shopid}"/>
		<input type="hidden" name="name" value="${review.shopName}"/>
		<input type="submit" class="btn btn-clear" value="口コミ一覧に戻る" />
	</f:form>	
</div>	
</body>
</html>
<%
	}else{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/"); // ログイン画面遷移
		dispatcher.forward(request, response);
	}
%>