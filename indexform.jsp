<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>画像アップロード</title>
</head>
<body>
    <h2>画像アップロードフォーム</h2>
    <form action="ImgUploadServlet" method="post" enctype="multipart/form-data">
        <label for="imageFile">画像ファイル:</label>
        <input type="file" name="imageFile" id="imageFile" accept="image/*" required><br>
        <label for="materialName">材料名:</label>
        <input type="text" name="materialName" id="materialName" required><br>
        <label for="materialgroup">材料グループ:</label>
        <input type="radio" name="materialgroup" value="1" required>お肉
        <input type="radio" name="materialgroup" value="2">魚
        <input type="radio" name="materialgroup" value="3">野菜
        <input type="radio" name="materialgroup" value="4">その他<br>
        <button type="submit">アップロード</button>
    </form>
</body>
</html>
