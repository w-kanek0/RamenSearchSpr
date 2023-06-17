<%@ page contentType="text/html ; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1 ">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="{{ asset('css/app.css') }}">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="/static/js/ramenscript.js"></script>
<style>
.table th,
.table td {
  border:none;
}
</style>
<title>ユーザー登録</title>
</head>
<body class="bg-light">
<div class="container w-50">
	<div class="card">
		<div class="card-header">
			<h3 class="text-secondary">ユーザー登録</h3>
		</div>
		<div class="card-body">
			<f:form action="RegisterUser" ModelAttribute="RegisterUserForm" name="form" method="POST">
				<table class="table">
					<tr>
						<th>ユーザーID<font color=red size="2">(※必須)</font></th>
						<td><div style="width:280px">
							<input type="text" name="userid" pattern="^([a-zA-Z0-9]{6,20})$" class="form-control form-control-sm" size="20" maxlength="20" placeholder="6～20字の半角英数字" required>
						</div></td>
					</tr>
					<tr>
						<th>パスワード<font color=red size="2">(※必須)</font></th>
						<td><div style="width:280px">
							<input type="password" name="password" pattern="^([a-zA-Z0-9]{8,20})$" class="form-control form-control-sm" size="20" maxlength="20" placeholder="8～20字の半角英数字" required>
						</div></td>
					</tr>
					<tr>
						<th>名前</th>
						<td><div style="width:280px">
							<input type="text" name="username" class="form-control form-control-sm" size="20" maxlength="30">
							</div></td>
					</tr>
					<tr>
						<th>メールアドレス<font color=red size="2">(※必須)</font></th>
						<td><div style="width:280px">
							<input type="email" name="email" class="form-control form-control-sm" size="20" maxlength="100" required>
						</div></td>
					</tr>
					<tr>
						<th></th>
						<td><font color=red size="2">${errorMsg}</font></td>
					</tr>
				</table>
				<div class="text-center">
					<input type="button" class="btn btn-primary mb-2" value="　登録　" onclick="user_register_confirm(this.form)">
					<a href="/" class="btn btn-secondary mb-2">　戻る　</a>
				</div>
			</f:form>
		</div>
	</div>
</div>
</body>
</html>