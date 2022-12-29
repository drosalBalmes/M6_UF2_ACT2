import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/cinema?&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        try {
            Class.forName(driver);

            Connexio cn = new Connexio(driver,url,user,password);
            cn.query(cn.intervalAnys(scanner));
            System.out.println("\n-----------------------------------------------------------------------------\n");
            cn.query(cn.director());
            System.out.println("\n-----------------------------------------------------------------------------\n");
            cn.insert("Films",scanner);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
