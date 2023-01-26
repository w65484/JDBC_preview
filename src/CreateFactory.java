import java.sql.*;

public class CreateFactory {
    Connection c;
    Statement stmt = null;
    public CreateFactory(Connection c){
        this.c = c;
    }
    public void createTable(String query){
        try{
            this.stmt = this.c.createStatement();
            this.stmt.executeUpdate(query);
            this.stmt.close();
            this.c.commit();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
