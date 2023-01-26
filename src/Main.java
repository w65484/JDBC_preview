import java.sql.*;
public class Main {
    public static void main( String args[] ) {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            CreateFactory factory = new CreateFactory(c);
            String create = "CREATE TABLE PRESENCE " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " LEADER TEXT NOT NULL, " +
                    " SUBJECT TEXT NOT NULL, " +
                    " STUDENT TEXT NOT NULL, " +
                    " ALBUM CHAR(6) NOT NULL, " +
                    " DATE DATE NOT NULL, " +
                    " LOG_IN_TIME TIME NOT NULL, " +
                    " LOG_OUT_TIME TIME NOT NULL, " +
                    " SESSION_PERIOD TEXT NOT NULL)";
            factory.createTable(create);
            Student s = new Student(c,2, "Jan Nowak","JDBC","Artur Kadzidło",
                    "w65498","2022-07-28", "09:00:00", "10:45:00");
            s.addStudent();
            SelectFactory select = new SelectFactory(c);
            String selectQuery = "SELECT * FROM PRESENCE";
            select.selectData(selectQuery);
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}