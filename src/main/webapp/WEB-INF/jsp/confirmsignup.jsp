<%@ page contentType="text/html ; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1 ">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="{{ asset('css/app.css') }}">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/ramenscript.js"></script>
<title>ユーザー登録が完了しました。</title>
</head>
<body class="bg-light">
<div class="container w-50">
	<div class="card text-center">
		<div class="card-body">
			<p class="text-secondary">ユーザー登録が完了しました。</p>
			<p><a href="/" class="btn btn-primary w-3" >ログイン</a></p> 
		</div>
	</div>
</div>
</body>
</html>