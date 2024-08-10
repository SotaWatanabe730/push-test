package cont;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import bean.ImageData;
import dao.ImageDataDAO;

@MultipartConfig
public class ImgUploadServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // MySQL JDBC ドライバーをロード
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC ドライバーのロードに失敗しました", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Webアプリケーションのルートディレクトリに基づくパスを取得
        String imageDirPath = getServletContext().getRealPath("/") + "image/";
        
        // ディレクトリが存在しない場合は作成する
        File imageDir = new File(imageDirPath);
        if (!imageDir.exists()) {
            imageDir.mkdirs(); // ディレクトリを作成
        }

        // 画像ファイルの取得
        Part filePart = request.getPart("imageFile");
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = filePart.getSubmittedFileName();
            File file = new File(imageDir, fileName);
            filePart.write(file.getAbsolutePath()); // ファイルを指定パスに保存
        }

        // その他のフォームデータの取得
        String materialName = request.getParameter("materialName");
        String materialGroupStr = request.getParameter("materialgroup");
        int materialGroup = materialGroupStr != null ? Integer.parseInt(materialGroupStr) : 0;

        // データベースへの保存
        ImageData imageData = new ImageData(materialName, materialGroup, request.getContextPath() + "/image/" + fileName);
        ImageDataDAO imageDataDAO = new ImageDataDAO();
        try {
            imageDataDAO.insertImageData(imageData);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データベースへの保存に失敗しました。");
            return;
        }

        // 結果の表示
        response.setContentType("text/html;charset=UTF-8");
        try (java.io.PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>アップロード成功</h2>");
            out.println("<p><strong>材料名:</strong> " + (materialName != null ? materialName : "なし") + "</p>");
            out.println("<p><strong>材料グループ:</strong> " + materialGroup + "</p>");
            if (fileName != null) {
                // 相対パスを計算
                String imagePath = request.getContextPath() + "/image/" + fileName;
                out.println("<p><strong>アップロードされた画像:</strong> <img src=\"" + imagePath + "\" alt=\"Uploaded Image\"></p>");
                out.println("<p><strong>画像のパス:</strong> " + imagePath + "</p>");
            } else {
                out.println("<p><strong>アップロードされた画像:</strong> なし</p>");
            }
            out.println("</body></html>");
        }
    }
}