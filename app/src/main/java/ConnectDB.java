import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }

    public ConnectDB() {

    }

    private String jdbUrl = "jdbc:mysql://localhost:3306/dmdm";
    private String dbId = "root";
    private String dbPw = "12345";
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt2 = null;
    private ResultSet rs = null;
    private String sql = "";
    private String sql2 = "";
    String returns = "";

    public String logindb(String id, String pw) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbUrl, dbId, dbPw);
            sql = "select id from customer where id=?&pw=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("id").equals(id) && rs.getString("pw").equals("pw")) {
                    returns = "1";
                } else {
                    returns = "2";
                }
            } else {
                returns = "0";
            }
        } catch (Exception e) {
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
        }
        return returns;
    }
}
