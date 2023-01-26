import java.sql.Connection;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalTime;

public class Student {
    Connection c;
    Integer id;
    String leader;
    String subject;
    String student;
    String album;
    String date;
    String log_in;
    String log_out;
    Duration session_time;
    public Student(Connection c, Integer id, String leader, String subject, String student, String album, String date, String log_in, String log_out) {
        this.c = c;
        this.id = id;
        this.leader = leader;
        this.subject = subject;
        this.student = student;
        this.album = album;
        this.date = date;
        this.log_in = log_in;
        this.log_out = log_out;
        LocalTime start = LocalTime.parse(this.log_in);
        LocalTime end = LocalTime.parse(this.log_out);
        this.session_time = Duration.between(start,end);
    }

    public void addStudent(){
        try{
            Statement stmt = this.c.createStatement();
            String query = "INSERT INTO Presence(" +
                    "ID, LEADER, SUBJECT, STUDENT, ALBUM, DATE, LOG_IN_TIME, LOG_OUT_TIME, SESSION_PERIOD)" +
                    "VALUES(%s,'%s','%s','%s','%s','%s','%s','%s','%s')"
                            .formatted(this.id, this.leader, this.subject, this.student, this.album, this.date, this.log_in, this.log_out, this.session_time.toString());
            stmt.executeUpdate(query);
            this.c.commit();
            stmt.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
