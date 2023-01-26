import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SelectFactory {
    Connection c;
    Statement stmt = null;
    public SelectFactory(Connection c){
        this.c = c;
    }
    public void selectData(String query){
        try{
            this.stmt = this.c.createStatement();
            ResultSet rs = this.stmt.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            while ( rs.next() ) {
                int counter = md.getColumnCount();
                String[] colName = new String[counter];
                Map<String, Object> field = new HashMap<>();
                for (int loop = 1; loop <= counter; loop++) {
                    int index = loop - 1;
                    colName[index] = md.getColumnLabel(loop);
                    field.put(colName[index], rs.getObject(colName[index]));
                }
                System.out.println(field);
            }
            rs.close();
            this.stmt.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
