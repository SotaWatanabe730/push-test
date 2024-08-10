package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.ImageData;

public class ImageDataDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/image_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertImageData(ImageData imageData) throws SQLException {
        String sql = "INSERT INTO image_data (material_name, material_group, image_path) VALUES (?, ?, ?)";

        try (Connection conn = getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, imageData.getMaterialName());
            stmt.setInt(2, imageData.getMaterialGroup());
            stmt.setString(3, imageData.getImagePath());
            stmt.executeUpdate();
        }
    }
}
