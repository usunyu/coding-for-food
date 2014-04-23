/*
Explain the data structures and algorithms that you would use to design an in-memory file system. 
Illustrate with an example in code where possible.
*/

import java.util.*;

class File {
	int fid;
	int size;

	public File(int fid, int size) {
		this.fid = fid;
		this.size = size;
	}
}

class Folder {
	int fid;
	LinkedList<File> fileList;
	LinkedList<Folder> folderList;

	public Folder(int fid) {
		this.fid = fid;
	}

	public void addFolder(Folder folder) {
		folderList.add(folder);
	}

	public void addFile(File file) {
		fileList.add(file);
	}
}

class FileSystem {
	int capacity;
	LinkedList<File> topFileList;
	LinkedList<Folder> topFolderList;

	LinkedList<File> currentFileList;
	LinkedList<Folder> currentFolderList;

	// LinkedList<File> parentFileList;
	Folder parentFolder;

	int fileCount;
	int folderCount;

	public FileSystem() {
		topFileList = new LinkedList<File>();
		topFolderList = new LinkedList<Folder>();

		currentFileList = topFileList;
		currentFolderList = topFolderList;

		capacity = 1000;
	}

	public File createFile() {
		File file = new File(fileCount++, 100);
		if(parentFolder != null)
			parentFolder.addFile(file);
		else
			topFileList.add(file);
		return file;
	}

	public Folder createFolder() {
		Folder folder = new Folder(folderCount++);
		if(parentFolder != null)
			parentFolder.addFolder(folder);
		else
			topFolderList.add(folder);
		return folder;
	}

	public void cd(int fid) {
		Folder folder = getFolder(fid);
		parentFolder = folder;
		currentFolderList = folder.folderList;
		currentFileList = folder.fileList;
	}

	public Folder getFolder(int fid) {
		Folder folder = null;
		// ...
		return folder;
	}
}

class Solution {
	public static void main(String[] args) {

	}
}