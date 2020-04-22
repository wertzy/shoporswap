import java.util.*;

public class Report{
    public String comment,iD;
    public User user;
    public Report(String iD, String comment, User user){
        this.iD=iD;
        this.comment=comment;
        this.user=user;
    }

    public void persist(User adminact){
        user.sendMessage(this.iD,this.comment,adminact);
    }
}