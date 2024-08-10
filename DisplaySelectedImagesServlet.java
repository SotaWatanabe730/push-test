package cont;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DisplaySelectedImagesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 送信された画像名のリストを取得
        String selectedImages = request.getParameter("selectedImages");
        if (selectedImages != null && !selectedImages.isEmpty()) {
            String[] imageNames = selectedImages.split(",");

            // 画像名を使って処理を行う（例: 画像の表示）
            response.setContentType("text/html;charset=UTF-8");
            try (java.io.PrintWriter out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<h2>Selected Images</h2>");
                for (String imageName : imageNames) {
                    String imagePath = request.getContextPath() + "/image/" + imageName;
                    out.println("<img src=\"" + imagePath + "\" alt=\"Selected Image\" style=\"width: 100px; height: 100px; border: 2px solid blue;\" />");
                }
                out.println("</body></html>");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No images selected.");
        }
    }
}
