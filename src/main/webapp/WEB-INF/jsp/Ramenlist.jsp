<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page contentType="text/html ; charset=utf-8" %>
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
<link rel="stylesheet" type="text/css" href="/static/css/ramensearch.css">

<script src="static/js/ramenscript.js"></script>
<title>ラーメン情報検索システム</title>
</head>
<body>

<div id="wrap">
    <div class="header">
        こんにちは、${user.username} さん<br />
        <a type="button" class="btn btn-clear" href="/Logout">ログアウト</a>
    </div>
    <h2>検索画面</h2>
    <hr>
    <section class="search">
        <f:form modelAttribute="searchForm" action="RamenSearch"  name="search" method="POST">
        <table class="searchTable">
            <tr>
                <th><label>店舗名</label></th>
                <th><label>ジャンル</label></th>
                <!--th><label></label></th -->
            </tr>
            <tr>
                <td><input type="text" id="s_name" name="s_name" class="text-search" placeholder="店舗名(一部入力可)を入力" value="${search.shopName}" autocomplete="off"/></td>
                <td><select name="s_genre" class="select-search">
					<c:choose>
						<c:when test="${search.genreid == 1}">
							<option value="0" >ジャンルを選択</option>
							<option value="1" selected>塩</option>
							<option value="2">味噌</option>
							<option value="3">醤油</option>
							<option value="4">豚骨</option>
							<option value="5">鶏白湯</option>
							<option value="6">担々麺</option>
						</c:when>
						<c:when test="${search.genreid == 2}">
							<option value="0" >ジャンルを選択</option>
							<option value="1">塩</option>
							<option value="2" selected>味噌</option>
							<option value="3">醤油</option>
							<option value="4">豚骨</option>
							<option value="5">鶏白湯</option>
							<option value="6">担々麺</option>
						</c:when>
						<c:when test="${search.genreid == 3}">
							<option value="0" >ジャンルを選択</option>
							<option value="1">塩</option>
							<option value="2">味噌</option>
							<option value="3" selected>醤油</option>
							<option value="4">豚骨</option>
							<option value="5">鶏白湯</option>
							<option value="6">担々麺</option>
						</c:when>
						<c:when test="${search.genreid == 4}">
							<option value="0">ジャンルを選択</option>
							<option value="1">塩</option>
							<option value="2">味噌</option>
							<option value="3">醤油</option>
							<option value="4" selected>豚骨</option>
							<option value="5">鶏白湯</option>
							<option value="6">担々麺</option>
						</c:when>
						<c:when test="${search.genreid == 5}">
							<option value="0" >ジャンルを選択</option>
							<option value="1">塩</option>
							<option value="2">味噌</option>
							<option value="3">醤油</option>
							<option value="4">豚骨</option>
							<option value="5" selected>鶏白湯</option>
							<option value="6">担々麺</option>
						</c:when>
						<c:when test="${search.genreid == 6}">
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
                    </select></td>
                <!-- td></td -->
            </tr>
            <tr>
                <th><label>エリア</label></th>
                <th><label>評価</label></th>
                <!--th><label></label></th -->
            </tr>
            <tr>
                <td><input type="text" name="s_area" class="text-search" placeholder="エリア(一部入力可)を入力" value="${search.area}" autocomplete="off"/></td>
                <td><select name="s_value" class="select-search">
					<c:choose>
						<c:when test="${search.value == 1}">
							<option value="0">評価を選択</option>
							<option value="1" selected>☆</option>
							<option value="2">☆☆</option>
							<option value="3">☆☆☆</option>
							<option value="4">☆☆☆☆</option>
							<option value="5">☆☆☆☆☆</option>
						</c:when>
						<c:when test="${search.value == 2}">
							<option value="0">評価を選択</option>
							<option value="1">☆</option>
							<option value="2" selected>☆☆</option>
							<option value="3">☆☆☆</option>
							<option value="4">☆☆☆☆</option>
							<option value="5">☆☆☆☆☆</option>
						</c:when>
						<c:when test="${search.value == 3}">
							<option value="0">評価を選択</option>
							<option value="1">☆</option>
							<option value="2">☆☆</option>
							<option value="3" selected>☆☆☆</option>
							<option value="4">☆☆☆☆</option>
							<option value="5">☆☆☆☆☆</option>
						</c:when>
						<c:when test="${search.value == 4}">
							<option value="0">評価を選択</option>
							<option value="1">☆</option>
							<option value="2">☆☆</option>
							<option value="3">☆☆☆</option>
							<option value="4" selected>☆☆☆☆</option>
							<option value="5">☆☆☆☆☆</option>
						</c:when>
						<c:when test="${search.value == 5}">
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
                    <select name="equality" class="select-search">
					<c:choose>
						<c:when test="${search.equality == \">=\"}">
							<option value="=">と等しい</option>
							<option value=">=" selected>以上</option>
							<option value="<=">以下</option>
						</c:when>
						<c:when test="${search.equality == \"<=\"}">
							<option value="=">と等しい</option>
							<option value=">=">以上</option>
							<option value="<=" selected>以下</option>
						</c:when>
						<c:otherwise>
							<option value="=" selected>と等しい</option>
							<option value=">=">以上</option>
							<option value="<=">以下</option>
						</c:otherwise>
					</c:choose>
					</select></td>
                <!-- td></td -->
            </tr>
        </table>
        <table class="searchTable">
            <tr>
                <th>営業時間</th>
            </tr>
            <tr>
                <td><input type="time" name="s_opentime" class="time-search" value="${search.inputOpentime}" autocomplete="off"/> 時に入れる</td>
            </tr>
        </table>
        <table class="searchTable">
            <tr>
                <th>オープン日</th>
            </tr>
            <tr>
                <td><input type="date" name="s_opendatestart" class="date-search" value="${search.inputOpendayStart}"/> ～ 
                    <input type="date" name="s_opendateend" class="date-search" value="${search.inputOpendayEnd}"/></td>
            </tr>
        </table>
        <div class="left">
        	<input type="submit" class="btn btn-search" value="検索" onclick="trim_search(this.form)"/>
            <input type="button" name="reset" class="btn btn-clear" value="クリア" onClick="clearFormAll();" />
        </div>
        </f:form>
    </section>
    <section class="rameninfodisp">
    	<f:form id="ramens_info">
            <table id="ramens" class="ramensTable">
                    <tr>
                        <th>
                            <label>選択</label>
                        </th>
                        <th>
                            <label>写真</label>
                        </th>
                        <th>
                            <label>店舗コード</label>
                        </th>
                        <th>
                            <label>店舗名</label>
                        </th>
                        <th>
                            <label>エリア</label>
                        </th>
                        <th>
                            <label>ジャンル</label>
                        </th>
                        <th>
                            <label>営業時間</label>
                        </th>
                        <th>
                            <label>評価</label>
                        </th>
                        <th>
                            <label>オープン日</label>
                        </th>
                        <th>
                            <label>最終更新日時</label>
                        </th>
                    </tr>
                    <c:forEach items="${ramenList}" var="ramen">
						<tr>
							<td>
								<input type="radio" name="select" value="${ramen.shopid}" autocomplete="off"/>
			 				</td>
			 				<td><c:if test="${!empty ramen.filename}">
								<img src="${pageContext.request.contextPath}/static/upload/thumbnail/${ramen.filename}" width="100" height="80">
							</c:if></td>
							<td>${ramen.shopid}</td>
							<td>${ramen.shopName}</td>
							<td>${ramen.area}</td>
							<td>${ramen.genreName}</td>
							<td>
								<c:choose>
									<c:when test="${ramen.opentime.toString().substring(0, 5) == \"00:00\" 
												and ramen.closetime.toString().substring(0, 5) == \"23:59\"}">
										24時間営業
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${ramen.opentime.toString().substring(0, 1) == \"0\"}">
												${ramen.opentime.toString().substring(1, 5)}
											</c:when>
											<c:otherwise>
												${ramen.opentime.toString().substring(0, 5)}
											</c:otherwise>
										</c:choose>
										 ～ 
										<c:choose>
											<c:when test="${ramen.closetime.toString().substring(0, 1) == \"0\"}">
												${ramen.closetime.toString().substring(1, 5)}
											</c:when>
											<c:otherwise>
												${ramen.closetime.toString().substring(0, 5)}
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</td>
							<td>${ramen.valueLabel}</td>
							<td>${ramen.openday.toString().substring(0, 10)}</td>
							<td>${ramen.lastupdate.toString().substring(0, 16)}</td>
						</tr>
                    </c:forEach>
			</table>
		</f:form>
         <div class="center" >
			<f:form style="display:inline" modelAttribute="dspRegisterForm" action="DspRamenInsert" id="register" method="POST">
				<input type="hidden" name="shopid" value="0"/>
				<input type="submit" id="register" class="btn btn-search" value="登録">
			</f:form>
			<input type="button" id="update" class="btn btn-update" value="更新" onclick="update_click()">
			<input type="button" id="delete" class="btn btn-delete" value="削除" onclick="delete_click()">
			<input type="button" id="reviewlist" class="btn btn-review" value="口コミを見る" onclick="reviewlist_click()">
<%
			// 現在日時を取得
			LocalDateTime ldt = LocalDateTime.now();
			// 日付時刻の表示形式を指定、yyyymmdd_hhmissに変換
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS");

			// CSVファイル名を指定。Task_現在日時(yyyymmdd_hhmmSSsss).csv
			String csvFilename = "Ramens_" + dtf.format(ldt) + ".csv";
%>
			<a id="download" href="#" download="<%=csvFilename%>" class="btn btn-csv" onclick="handleDownload()">CSV出力</a>
		</div>
	</section>
</div>
</body>
</html>
<%
	}else{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/"); // ログイン画面遷移
		dispatcher.forward(request, response);
	}
%>