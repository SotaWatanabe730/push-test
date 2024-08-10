package cont;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;

public class DisplayUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDAO();  // DAOクラスのインスタンスを作成
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // DAOを使ってデータベースからユーザーリストを取得
            List<User> userList = userDao.getUsersWithNoAdmin();
            List<User> adminList = userDao.getUsersWithAdmin();
            // リクエストスコープにユーザーリストを設定
            request.setAttribute("userList", userList);
            request.setAttribute("adminList", adminList);
            // JSPページにフォワード
            request.getRequestDispatcher("displayUsers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
