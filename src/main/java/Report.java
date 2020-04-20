import java.util.*;
public class Report(String ID, String Comment){
	String Comment,ID;
	public Report(String ID, String Comment){
		this.ID=ID;
		this.Comment=Comment;
	}
	public persist(String adminact){
		adminact.sendEmail(ID, Comment);

	}
}
