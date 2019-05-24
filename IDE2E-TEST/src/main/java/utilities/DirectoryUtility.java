package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.apache.tika.Tika;


public class DirectoryUtility {
	
	public ArrayList<String> getFilesList(String folderPath) {

			ArrayList<String> fileNameArray = new ArrayList<String>();
			
			File dir = new File(folderPath);
			File [] list = dir.listFiles();
		
			
			for(File file : list) {
				if(file.isFile()) {
					fileNameArray.add(file.getName());
				}
			}
			
			return fileNameArray;
	}
	
	
	public int getFilesCount(String folderPath) {

		int fileCount = 0;
	
		File dir = new File(folderPath);
		File [] list = dir.listFiles();
	
		
		for(File file : list) {
			if(file.isFile()) {
				fileCount++;
			}
		}
		
		return fileCount;
	}


	public ArrayList<String> getFolderList(String folderPath) {

		ArrayList<String> folderNameArray = new ArrayList<String>();
		
		File dir = new File(folderPath);
		File [] list = dir.listFiles();
	
		
		for(File file : list) {
			if(file.isDirectory()) {
				folderNameArray.add(file.getName());
			}
		}
		
		return folderNameArray;
	}

	
	public int getFolderCount(String folderPath) {

		int folderCount = 0;
	
		File dir = new File(folderPath);
		File [] list = dir.listFiles();
	
		
		for(File file : list) {
			if(file.isDirectory()) {
				folderCount++;
			}
		}
		
		return folderCount;
	}

	
	public String getFileExtension(File filePath) {

		String fileExtension;
		
		if(filePath.getName().indexOf(".")==-1) {
			fileExtension = "";
		}else {
			fileExtension = filePath.getName().substring(filePath.getName().lastIndexOf("."));
			
		}
		return fileExtension;
	}

	
	public Long getFileSize(File filePath){

		long fileSize=0;
		try {
			fileSize = Files.size(filePath.toPath());
		} catch (IOException e) {
			e.printStackTrace();

		}
		return fileSize;
			
	}


	public String getFileMIMEType(File filePath){

			String fileMIMEType = "";
			try {
				fileMIMEType = new Tika().detect(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return fileMIMEType;
	}

}


