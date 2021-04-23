package per.main;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class img_commentVO {
 private int wid;
 private String pid;
 private String comment;
 private int score;
 private Date regdate;
 private String userID;
 private String delFlag;
 
 public img_commentVO() {
	 
 }
 
 
public img_commentVO(int wid, String pid, String comment, int score, Date regdate, String userID, String delFlag) {
	this.wid = wid;
	this.pid = pid;
	this.comment = comment;
	this.score = score;
	this.regdate = regdate;
	this.userID = userID;
	this.delFlag = delFlag;
}


public int getWid() {
	return wid;
}
public void setWid(int wid) {
	this.wid = wid;
}
public String getPid() {
	return pid;
}
public void setPid(String pid) {
	this.pid = pid;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public Date getRegdate() {
	return regdate;
}
public void setRegdate(Date regdate) {
	this.regdate = regdate;
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public String getDelFlag() {
	return delFlag;
}
public void setDelFlag(String delFlag) {
	this.delFlag = delFlag;
}
 
 
}
