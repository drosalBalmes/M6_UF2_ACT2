import java.sql.*;
import java.util.Scanner;

public class Connexio {
    private String driver,url,user,password;

    public Connexio(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void query(PreparedStatement query) throws SQLException {

        Connection con = getCon();
        Statement st = con.createStatement();
             ResultSet rs = query.executeQuery();
            int colNum = getColumnNames(rs);
            if(colNum>0)
                while(rs.next()) {
                    for(int i =0; i<colNum; i++) {
                        if(i+1 == colNum) {
                            System.out.println(rs.getString(i+1));
                        } else {
                            System.out.print(rs.getString(i+1)+ ", ");
                        } //endif
                    } //endfor
                } //endwhile
        } //endif


    public Connection getCon(){
        try {
            Connection con = DriverManager.getConnection(url,user,password);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public PreparedStatement intervalAnys(Scanner scanner) throws SQLException {
        Connection con = getCon();
        System.out.println("Insereix l'interval d'anys: ");
        System.out.println("any 1: ");
        int any1 = scanner.nextInt();
        System.out.println("any 2: ");
        int any2 = scanner.nextInt();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM films WHERE (year(DataEstrena) BETWEEN ? AND ?)");
        ps.setInt(1,any1);
        ps.setInt(2,any2);
        return ps;
    }

    public PreparedStatement director() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = getCon();
        System.out.println("Insereix el director de qui vulguis veure les pelicules: ");
        /*Es necesari fer un scanner nou
        *sembla ser que si es fa primer un scanner tipus next despres no es pot fer tipus nextLine amb el mateix scanner
         */
        String director = sc.nextLine();
        PreparedStatement ps = con.prepareStatement("SELECT titol,YEAR(DataEstrena) FROM films INNER JOIN Director ON films.idDirector = Director.idDirector WHERE (Director.nom = ?)");
        ps.setString(1,director);
        return ps;
    }

    public void insert(String tableName, Scanner scanner) throws SQLException {
            Connection con = getCon();
            Scanner sc = new Scanner(System.in);
            Statement st = con.createStatement();
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);
        System.out.println("Insereix el titol de la pelicula: ");
        String titol = sc.nextLine();

        System.out.println("Insereix la data d'estrena(yyyy-mm-dd): ");
        String data = scanner.next();
        System.out.println("Insereix el país d'origen: ");
        String pais = sc.nextLine();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Films(titol,DataEstrena, pais) VALUES (?,?,?)");
        ps.setString(1,titol);
        ps.setString(2,data);
        ps.setString(3,pais);

            if (tables.next()) {
                ps.executeUpdate();
                System.out.println("Insert fet correctament");
            }
            else {
                System.out.println("La taula " + tableName + " no existeix");
            }
        }
    public static int getColumnNames(ResultSet rs) throws SQLException {
        int numberOfColumns = 0;
        if (rs != null) {   //create an object based on the Metadata of the result set
            ResultSetMetaData rsMetaData = rs.getMetaData();
            numberOfColumns = rsMetaData.getColumnCount();   //Use the getColumn method to get the number of columns returned
            for (int i = 1; i < numberOfColumns + 1; i++) {  //get and print the column names, column indexes start from 1
                String columnName = rsMetaData.getColumnName(i);
                System.out.print(columnName + ", ");
            }//endfor
        }//endif
        System.out.println();   //place the cursor on a new line in the console
        return numberOfColumns;
    }//end method getColumnNames


}
