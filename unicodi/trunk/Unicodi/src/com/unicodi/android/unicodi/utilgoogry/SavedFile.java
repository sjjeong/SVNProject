package com.unicodi.android.unicodi.utilgoogry;

import java.io.File;
import java.util.Vector;

public class SavedFile {
	private String root;
	private Vector<String> fileList = new Vector<String>();

	public SavedFile(String root) {
		this.root = root;
	}

	public void addFile(String name) {
		fileList.add(name);
	}

	public Vector<String> getFileList() {
		return fileList;
	}

	public String getRoot() {
		return root;
	}

	public String getFirstPath() {
		if (fileList.size() > 0) {
			return FileUtil.SAVED_DIR + root + File.separator + fileList.get(0);
		}
		return null;
	}

}
