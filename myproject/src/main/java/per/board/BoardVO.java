package per.board;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {

		int id;
		String title;
		String writer_id;
		String content;
		Date regDate;
		int hit;
		String files;
		boolean pub;
		String img;
		
//		첨부파일
		private List<MultipartFile> uploadfile;
		
		//생성자
		public BoardVO() {
			
		}
		
		//생성자 오버라이드
		public BoardVO(int id, String title, String writer_id, String content, Date regDate, int hit, String files,
				boolean pub ,String img) {
			
			this.id = id;
			this.title = title;
			this.writer_id = writer_id;
			this.content = content;
			this.regDate = regDate;
			this.hit = hit;
			this.files = files;
			this.pub = pub;
			this.img = img;
		}
		
		public BoardVO(int id, String title, String writer_id, String content, Date regDate, int hit, String files,
				boolean pub) {
			
			this.id = id;
			this.title = title;
			this.writer_id = writer_id;
			this.content = content;
			this.regDate = regDate;
			this.hit = hit;
			this.files = files;
			this.pub = pub;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getWriter_id() {
			return writer_id;
		}

		public void setWriter_id(String writer_id) {
			this.writer_id = writer_id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getRegDate() {
			return regDate;
		}

		public void setRegDate(Date regDate) {
			this.regDate = regDate;
		}

		public int getHit() {
			return hit;
		}

		public void setHit(int hit) {
			this.hit = hit;
		}

		public String getFiles() {
			return files;
		}

		public void setFiles(String files) {
			this.files = files;
		}

		public boolean getPub() {
			return pub;
		}

		public void setPub(boolean pub) {
			this.pub = pub;
		}
		
		public List<MultipartFile> getUploadfile(){
			return uploadfile;
		}
		public void setUploadfile(List<MultipartFile> uploadfile) {
			this.uploadfile = uploadfile;
		}
	}
