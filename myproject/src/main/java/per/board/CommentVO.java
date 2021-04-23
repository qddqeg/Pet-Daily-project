package per.board;

import java.util.Date;

public class CommentVO {
	private	int id;
	private	int mid;
	private	String comment;
	private	Date regdate;
	private String writer;
	
	public CommentVO() {
		
	}
	
	public CommentVO(int id, int mid, String comment, Date regdate, String writer) {
		this.id = id;
		this.mid = mid;
		this.comment = comment;
		this.regdate = regdate;
		this.writer = writer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
