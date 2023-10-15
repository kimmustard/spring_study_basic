package hello.upload.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import hello.upload.domain.UploadFile;

@Component
public class FileStore {

	@Value("${file.dir}")
	private String fileDir;

	public String getFullPath(String fileName) {
		return fileDir + fileName;
	}
	
	
	//여러개 파일업로드시
	public List<UploadFile> storeFiles (List<MultipartFile> multipartFiles) throws IOException{
		List<UploadFile> storeFileResult = new ArrayList<>();
		
		for (MultipartFile multipartFile : multipartFiles) {
			if(!multipartFile.isEmpty()) {
				UploadFile uploadFile = storeFile(multipartFile);
				storeFileResult.add(uploadFile);
			}
		}
		
		return storeFileResult;
	
	}
	
	
	
	public UploadFile storeFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
		 if(multipartFile.isEmpty()) {
			 return null;
		 }
		
		 String originalFilename = multipartFile.getOriginalFilename();
		 String storeFileName = createStoreFileName(originalFilename);
		 
		 multipartFile.transferTo(new File(getFullPath(storeFileName)));
		 
		 return new UploadFile(originalFilename, storeFileName);
	}

	
	// UUID와 추출한 확장자를 합쳐주는 메서드
	private String createStoreFileName(String originalFilename) {
		String uuid = UUID.randomUUID().toString();
		 String ext = extractExt(originalFilename);	//
		return uuid+ "." + ext;
	}

	
	//확장자를 추출하는 녀석 text.png  -> png 
	private String extractExt(String originalFilename) {
		int pos = originalFilename.lastIndexOf(".");
		 return originalFilename.substring(pos+1);
	}
	
	
}
