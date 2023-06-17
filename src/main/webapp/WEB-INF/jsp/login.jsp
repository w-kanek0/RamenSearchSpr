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
<title>ログイン</title>
</head>
<body class="bg-light">
	<br>
	<div class="container w-50">
		<div class="card">
			<div class="card-header">
					<h3 class="text-secondary">ログイン</h3>
			</div>
			<div class="card-body">
				<f:form action="Login" name="form" modelAttribute="loginForm" method="POST">
					<div class="row">
						<label class="col-form-label col-sm-3">ユーザID：</label>
						<div class="col-sm-5"><input type="text" name="userid" pattern="^[a-zA-Z0-9]+$" class="form-control form-control-sm" maxlength="20" required></div>
					</div>
					<div class="row">
						<label class="col-form-label col-sm-3">パスワード：</label>
						<div class="col-sm-5"><input type="password" name="password" pattern="^[a-zA-Z0-9]+$" class="form-control form-control-sm" maxlength="20" required></div>
					</div>
					<font color=red size=3>${errorMsg}</font>
					<div class="text-center">
						<button type="button" class="btn btn-primary mt-3" onclick="login_confirm(this.form)">　ログイン　</button>  
						<button type="reset" class="btn btn-light mt-3">　リセット　</button>
					</div>
				</f:form>
			</div>
			<div class="card-body">
				<f:form action="Signup" method="POST">
					<div class="text-center">
					<button type="submit" class="btn btn-info">ユーザー登録</button>
					</div>
				</f:form>
			</div>
		</div>
	</div>
</body>
</html>