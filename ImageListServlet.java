package cont;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ImageListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 内部クラスImageDataの定義
    public static class ImageData {
        private String imagePath;
        private String materialName;
        private String materialGroup;

        // コンストラクタ
        public ImageData(String imagePath, String materialName, String materialGroup) {
            this.imagePath = imagePath;
            this.materialName = materialName;
            this.materialGroup = materialGroup;
        }

        // ゲッター
        public String getImagePath() { return imagePath; }
        public String getMaterialName() { return materialName; }
        public String getMaterialGroup() { return materialGroup; }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // データベースから画像データを取得
        Map<String, List<ImageData>> imagesByGroup = new HashMap<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT image_path, material_name, material_group FROM image_data");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String imagePath = rs.getString("image_path");
                String materialName = rs.getString("material_name");
                String materialGroup = rs.getString("material_group");

                // ImageDataオブジェクトを作成
                ImageData imageData = new ImageData(imagePath, materialName, materialGroup);

                // グループごとにリストを管理
                if (!imagesByGroup.containsKey(materialGroup)) {
                    imagesByGroup.put(materialGroup, new ArrayList<>());
                }
                imagesByGroup.get(materialGroup).add(imageData);
            }
        } catch (Exception e) {
            throw new ServletException("Database access error", e);
        }

        // リクエストにデータをセット
        request.setAttribute("imagesByGroup", imagesByGroup);
        request.getRequestDispatcher("imageList.jsp").forward(request, response);
    }

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/image_db";
        String user = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
