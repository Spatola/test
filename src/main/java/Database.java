import java.sql.*;

public class Database {
    private Connection conn = null;
    private Statement st = null;

    private String dBfilename = null;

    public void setDBfilename(String pFilename) {
        this.dBfilename = pFilename;
    }

    public void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + this.dBfilename);
            st = conn.createStatement();
            st.setQueryTimeout(30);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createLogTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS message (" +
                    "       type INTEGER NOT NULL, " +
                    "       message TEXT NOT NULL )";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMessage(int type, String mesagge) {
        try {
            String s = "insert into message values (" + type + ", '" + mesagge + "')";
            st.executeUpdate(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnectionBD(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
