<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
<link rel="stylesheet" href="Login.css">
<script>
	function validateForm() {
		var password = document.getElementById("password").value;
		var userId = document.getElementById("userId").value;
			
		if (userId === "") {
			alert("ユーザーIDを入力してください。");
			return false;
		}
		
		if (password.length < 8 || password.length > 16) {
			alert("パスワードは8文字以上16文字以下で入力してください。");
			return false;
		}
		
		return true;
	}
 </script>
</head>
<body>
<div class="form-container">
	<form action="NewAccount.jsp" method="post"   class="new-account-form">
		<button type="submit">新規登録</button>
	</form>
<h2>ConDateへようこそ！</h2>
    <form method="post" action="LoginServlet" onsubmit="return validateForm();">
        <div class="form-group">
                <label for="userId">ユーザーID:</label>
                <input type="text" id="userId" name="userId" required>
            </div>
            <div class="form-group">
                <label for="password">パスワード:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <button type="submit">ログイン</button>
            </div>
            <div style="color: red;">
                <c:if test="${not empty errorMessage}">
                    ${errorMessage}
                </c:if>
            </div>
        </form>
    </div>
</body>
</html>