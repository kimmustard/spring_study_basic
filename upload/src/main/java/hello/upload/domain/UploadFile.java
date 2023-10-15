package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
	
	/*
	 * 고객이 업로드한 파일명으로 서버 내부에 파일을 저장하면 안된다.
	 * 서로 다른 고객이 같은 파일이름을 업로드 하는경우 기존 파일 이름과 충돌이 날 수 있다.
	 */

	private String uploadFileName;	//고객이 업로드한 파일명
	private String storeFileName;	//서버 내부에서 관리하는 파일명 (UUID)
	
	
	
	public UploadFile(String uploadFileName, String storeFileName) {
		this.uploadFileName = uploadFileName;
		this.storeFileName = storeFileName;
	}
	

	
}
