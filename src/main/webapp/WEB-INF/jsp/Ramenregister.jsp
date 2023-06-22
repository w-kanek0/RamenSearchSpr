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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/ramencommon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/ramenregister.css">

<script src="${pageContext.request.contextPath}/static/js/ramenscript.js"></script>
<c:choose>
	<c:when test="${ramen.shopid == 0}">
		<title>ラーメン情報登録</title>
    </c:when>
    <c:otherwise>
    	<title>ラーメン情報更新</title>
    </c:otherwise>
</c:choose>
</head>
<body>
<div id="wrap">
    <div class="header">
        こんにちは、${user.username} さん<br />
        <a type="button" class="btn btn-clear" href="${pageContext.request.contextPath}/Logout">ログアウト</a>
    </div>
<c:choose>
	<c:when test="${ramen.shopid == 0}">
    	<h2>ラーメン情報登録フォーム</h2>
    </c:when>
    <c:otherwise>
    	<h2>ラーメン情報更新フォーム</h2>
    </c:otherwise>
</c:choose>
	<hr>
    <section class="register">
<c:choose>
	<c:when test="${ramen.shopid == 0}">
    	<form name="form" modelAttribute="RegisterForm" style="display:inline" action="RamenInsert" method="POST" enctype="multipart/form-data">
    </c:when>
    <c:otherwise>
    	<form name="form" style="display:inline" modelAttribute="UpdateForm" action="RamenUpdate" method="POST" enctype="multipart/form-data">
    </c:otherwise>
</c:choose>
			<table id="updatetable" class="updateTable">
					<tr>
							<th>
                                <label>店舗コード</label>
                            </th>
							<c:choose>
								<c:when test="${ramen.shopid > 0}">
								<td><div class="annotation">${ramen.shopid}</div>
								</td>							
								</c:when>
    							<c:otherwise>
                            <td>
									<div class="annotation">新規</div>
                            </td>
								</c:otherwise>
							</c:choose>
							</tr>
                        <tr>
                            <th>
                                <label>店舗名<font color= "color:#ff0000" size="2">(※必須)</font></label>
                            </th>
                            <td>
                                <input type="text" name="name" class="text-upd" value="${ramen.shopName}" maxlength="120" autocomplete="off" placeholder="店舗名を入力" required/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <label>エリア<font color= "color:#ff0000" size="2">(※必須)</font></label>
                            </th>
                            <td>
                                <input type="text" name="area" class="text-upd" value="${ramen.area}" maxlength="30" autocomplete="off" placeholder="エリアを市区町村まで入力(例：東京都新宿区)" required/>
                            </td>
                            <!--<td>
                                
                            </td>-->    
                        </tr>
                        <tr>
                            <th>
                                <label>オープン日</label>
                            </th>
                            <td>
                                <input type="date" name="opendate" class="date-upd" value="${ramen.openday.toString().substring(0,10)}"/>
                            </td>
                            <!--<td>
                            
                            </td>-->
                        </tr>
                        <tr>
                            <th>
                                <label>ジャンル<font color= "color:#ff0000" size="2">(※必須)</font></label>
                            </th>
                            <td>
                                <select name="genre" class="select-upd">
									<c:choose>
										<c:when test="${ramen.genreName == \"塩\"}">
											<option value="0" >ジャンルを選択</option>
											<option value="1" selected>塩</option>
											<option value="2">味噌</option>
											<option value="3">醤油</option>
											<option value="4">豚骨</option>
											<option value="5">鶏白湯</option>
											<option value="6">担々麺</option>
										</c:when>
										<c:when test="${ramen.genreName == \"味噌\"}">
											<option value="0" >ジャンルを選択</option>
											<option value="1">塩</option>
											<option value="2" selected>味噌</option>
											<option value="3">醤油</option>
											<option value="4">豚骨</option>
											<option value="5">鶏白湯</option>
											<option value="6">担々麺</option>
										</c:when>
										<c:when test="${ramen.genreName == \"醤油\"}">
											<option value="0" >ジャンルを選択</option>
											<option value="1">塩</option>
											<option value="2">味噌</option>
											<option value="3" selected>醤油</option>
											<option value="4">豚骨</option>
											<option value="5">鶏白湯</option>
											<option value="6">担々麺</option>
										</c:when>
										<c:when test="${ramen.genreName == \"豚骨\"}">
											<option value="0">ジャンルを選択</option>
											<option value="1">塩</option>
											<option value="2">味噌</option>
											<option value="3">醤油</option>
											<option value="4" selected>豚骨</option>
											<option value="5">鶏白湯</option>
											<option value="6">担々麺</option>
										</c:when>
										<c:when test="${ramen.genreName == \"鶏白湯\"}">
											<option value="0" >ジャンルを選択</option>
											<option value="1">塩</option>
											<option value="2">味噌</option>
											<option value="3">醤油</option>
											<option value="4">豚骨</option>
											<option value="5" selected>鶏白湯</option>
											<option value="6">担々麺</option>
										</c:when>
										<c:when test="${ramen.genreName == \"担々麺\"}">
											<option value="0" >ジャンルを選択</option>
											<option value="1">塩</option>
											<option value="2">味噌</option>
											<option value="3">醤油</option>
											<option value="4">豚骨</option>
											<option value="5">鶏白湯</option>
											<option value="6" selected>担々麺</option>
										</c:when>
										<c:otherwise>
											<option value="0" selected>ジャンルを選択</option>
											<option value="1">塩</option>
											<option value="2">味噌</option>
											<option value="3">醤油</option>
											<option value="4">豚骨</option>
											<option value="5">鶏白湯</option>
											<option value="6">担々麺</option>
										</c:otherwise>
									</c:choose>
                                </select>
                            </td>
                            <!--<td>
                            
                            </td>-->
                        </tr>
                        <tr>
                            <th>
                                <label>営業時間<font color= "color:#ff0000" size="2">(※必須)</font></label>
                            </th>
                            <td>
								<input type="time" name="opentime" class="time-upd" value="${ramen.opentime.toString().substring(0,5)}"/>
                                ～
								<input type="time" name="closetime" class="time-upd" value="${ramen.closetime.toString().substring(0,5)}"/>
                                <br />
                                <div class="annotation">※24時間営業の場合は、"0:00～23:59"と指定して下さい。</div>
                            </td>
                            <!--<td>
                            
                            </td>-->
                        </tr>
						<tr>
                            <th>
                                <label>評価(5段階)</label>
                            </th>
                            <td>
                                <select name="value" class="select-upd">
									<c:choose>
										<c:when test="${ramen.valueLabel == \"☆★★★★\"}">
											<option value="0">評価を選択</option>
											<option value="1" selected>☆</option>
											<option value="2">☆☆</option>
											<option value="3">☆☆☆</option>
											<option value="4">☆☆☆☆</option>
											<option value="5">☆☆☆☆☆</option>
										</c:when>
										<c:when test="${ramen.valueLabel == \"☆☆★★★\"}">
											<option value="0">評価を選択</option>
											<option value="1">☆</option>
											<option value="2" selected>☆☆</option>
											<option value="3">☆☆☆</option>
											<option value="4">☆☆☆☆</option>
											<option value="5">☆☆☆☆☆</option>
										</c:when>
										<c:when test="${ramen.valueLabel == \"☆☆☆★★\"}">
											<option value="0">評価を選択</option>
											<option value="1">☆</option>
											<option value="2">☆☆</option>
											<option value="3" selected>☆☆☆</option>
											<option value="4">☆☆☆☆</option>
											<option value="5">☆☆☆☆☆</option>
										</c:when>
										<c:when test="${ramen.valueLabel == \"☆☆☆☆★\"}">
											<option value="0">評価を選択</option>
											<option value="1">☆</option>
											<option value="2">☆☆</option>
											<option value="3">☆☆☆</option>
											<option value="4" selected>☆☆☆☆</option>
											<option value="5">☆☆☆☆☆</option>
										</c:when>
										<c:when test="${ramen.valueLabel == \"☆☆☆☆☆\"}">
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
                            </td>
                            <!--<td>
                            
                            </td>-->
                        </tr>
                        <tr>
                            <th>
                                <label>画像</label>
                            </th>
                            <td>
                                <input type="file" name="uploadfile" accept="image/*"/><div class="annotation">${ramen.filename}</div>
                            </td>
                            <!--<td>
                            
                            </td>-->
                        </tr>
                </table>
                <input type="hidden" id="shopid" name="shopid" value="${ramen.shopid}" />
                <br />
				<c:choose>
					<c:when test="${ramen.shopid == 0}">
                		<input type="button" class="btn btn-search" value="登録" onclick="register_confirm(form)"/>
					</c:when>
					<c:otherwise>
                		<input type="button" class="btn btn-update" value="更新" onclick="register_confirm(form)"/>
					</c:otherwise>
				</c:choose>
           </form>
           <f:form style="display:inline" action="DspRamenSearch" method="post">
                <input type="submit" class="btn btn-clear" value="一覧に戻る" />
           </f:form>
                <!--a href="DspRamenSearch" class="btn btn-clear">戻る</a-->
        </section>
</div>
<script type="text/javascript">
//reloadを禁止
//F5キーによるreloadを禁止
document.addEventListener("keydown", function (e) {

 if ((e.which || e.keyCode) == 116 ) {
	e.preventDefault();
 }

});
</script>
</body>
</html>
<%
	}else{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/"); // ログイン画面遷移
		dispatcher.forward(request, response);
	}
%>