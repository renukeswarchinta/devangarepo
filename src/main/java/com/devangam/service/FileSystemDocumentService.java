package com.devangam.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.devangam.archive.Document;
import com.devangam.archive.DocumentMetadata;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileSystemDocumentService {

	/**
	 * Data access object to insert, find and load {@link Document}s.
	 * 
	 * FileSystemDocumentDao saves documents in the file system. No database in
	 * involved. For each document a folder is created. The folder contains the
	 * document and a properties files with the meta data of the document. Each
	 * document in the archive has a Universally Unique Identifier (UUID). The
	 * name of the documents folder is the UUID of the document.
	 * 
	 */

	// public static final String DIRECTORY = "/home/ec2-user/matrimony";
	public static final String META_DATA_FILE_NAME = "metadata.properties";

	/*
	 * @PostConstruct public void init() { createDirectory(DIRECTORY); }
	 */

	/**
	 * Inserts a document to the archive by creating a folder with the UUID of
	 * the document. In the folder the document is saved and a properties file
	 * with the meta data of the document.
	 * 
	 * @see org.murygin.archive.dao.IDocumentDao#insert(org.murygin.archive.service.Document)
	 */
	public void insert(Document document) {
		try {
			createDirectory(document.getDirectory());
			createDirectory(document);
			saveFileData(document);
			// saveMetaData(document);
		} catch (IOException e) {
			String message = "Error while inserting document";
			log.error("Error while inserting document",e);
			// LOG.error(message, e);
			//throw new RuntimeException(message, e);
		}
	}

	public String renameFile(String toFileName, String fromFileName) {
		String[] fileNameSplits = toFileName.split("\\.");
		// extension is assumed to be the last part
		int extensionIndex = fileNameSplits.length - 1;
		String newFileName = fromFileName + "." + fileNameSplits[extensionIndex];
		return newFileName;
	}

	private boolean isMatched(DocumentMetadata metadata, String personName, Date date) {
		if (metadata == null) {
			return false;
		}
		boolean match = true;
		if (personName != null) {
			match = (personName.equals(metadata.getPersonName()));
		}
		if (match && date != null) {
			match = (date.equals(metadata.getDocumentDate()));
		}
		return match;
	}

	private void saveFileData(Document document) throws IOException {
		String path = getDirectoryPath(document);
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(new File(path), document.getFileName())));
		stream.write(document.getFileData());
		stream.close();
	}

	private String createDirectory(Document document) {
		String path = getDirectoryPath(document);
		log.info("createDirectory PATH=" + path);
		createDirectory(path);
		return path;
	}

	public String getDirectoryPath(Document document) {
		return getDirectoryPath(document.getUserId(), document.getDirectory());
	}

	public String getImagePath(Document document) {
		String directoryPath = getDirectoryPath(document.getFileName(), document.getDirectory());
		StringBuilder sb = new StringBuilder();
		sb.append(directoryPath).append(File.separator).append(document.getUserId());
		String path = sb.toString();
		return path;
	}

	private String getDirectoryPath(String uuid, String directory) {
		StringBuilder sb = new StringBuilder();
		if(StringUtils.isNotBlank(uuid)){
			sb.append(directory).append(File.separator).append(uuid);
		}else{
			sb.append(directory);
		}
		String path = sb.toString();	
		return path;
	}

	private void createDirectory(String path) {
		File file = new File(path);
		file.mkdirs();
	}

}
