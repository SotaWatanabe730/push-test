<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Login.css">
<title>管理者ページ</title>
</head>
<body>
<div class="form-container">
<h2>管理者用ページ</h2>
	
	<form action="indexform.jsp" method="post">
		<button>材料編集画面へ</button>
	</form>
	<br>
	<form action="end.jsp" method="post" >
		<button>料理編集画面へ</button>
	</form>
	<br>
	<form action="DisplayUsersServlet" method="post">
		<button>管理者権限画面へ</button>
	</form>
	<br>
	<form action="Sample1.jsp" method="post" >
		<button>料理検索画面へ</button>
	</form>
	<br>
	<form action="Login.jsp" method="post">
		<button>ログアウト</button>
	</form>
	</div>
</body>
</html>