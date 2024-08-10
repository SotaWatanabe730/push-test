<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="cont.ImageListServlet.ImageData" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image List</title>
    <link rel="stylesheet" href="imageList.css">
</head>
<body>
    <h2>Uploaded Images by Material Group</h2>
    <form id="imageForm" action="DisplaySelectedImagesServlet" method="post">
        <div class="image-container">
            <%
                Map<String, List<ImageData>> imagesByGroup = (Map<String, List<ImageData>>) request.getAttribute("imagesByGroup");
                if (imagesByGroup != null && !imagesByGroup.isEmpty()) {
                    for (Map.Entry<String, List<ImageData>> entry : imagesByGroup.entrySet()) {
                        String materialGroup = entry.getKey();
                        List<ImageData> imageList = entry.getValue();
            %>
            <div class="group">
                <div class="group-heading">Group: <%= materialGroup %></div>
                <div class="scroll-container">
                    <div class="scroll-content">
                        <%
                            for (ImageData imageData : imageList) {
                                String imagePath = imageData.getImagePath();
                                String materialName = imageData.getMaterialName();
                                String imageName = imagePath.substring(imagePath.lastIndexOf("/") + 1); // 画像名のみ抽出
                        %>
                        <div class="group-item" onclick="toggleSelect(this, '<%= imageName %>')">
                            <img src="<%= imagePath %>" alt="<%= materialName %>" />
                            <p><%= materialName %></p>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
            <%
                    }
                } else {
            %>
            <p>No images found.</p>
            <%
                }
            %>
        </div>
        <input type="hidden" id="selectedImages" name="selectedImages"/>
        <button type="submit">Display Selected Images</button>
    </form>

    <script src="imageList.js"></script>
</body>
</html>
