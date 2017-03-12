package com.devangam.archive;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * A document from an archive managed by {@link IArchiveService}.
 * 
 */
public class Document extends DocumentMetadata implements Serializable {

    private static final long serialVersionUID = 2004955454853853315L;
    
    private byte[] fileData;
    private String userId;
    private String directory;
    
    public Document( byte[] fileData, String fileName, Date documentDate, String personName) {
        super(fileName, documentDate, personName);
        this.fileData = fileData;
    }

    public Document(Properties properties) {
        super(properties);
    }
    
    public Document(DocumentMetadata metadata) {
        super(metadata.getUuid(), metadata.getFileName(), metadata.getDocumentDate(), metadata.getPersonName());
    }

    public Document(byte[] bytes, String originalFilename, String userId,String directory) {
    	this.fileData = bytes;
    	this.fileName = getImagePathWithUUID(originalFilename);
    	this.userId = userId;
    	this.directory = directory;
	}
    
    public String getImagePathWithUUID(String originalFileName) {
		String imagePath = null;
		if (StringUtils.isNotBlank(originalFileName)) {
			String uuid = String.valueOf(Instant.now().getEpochSecond());
			imagePath = uuid + "_" + originalFileName;
		}
		return imagePath;
	}
    

	public byte[] getFileData() {
        return fileData;
    }
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
    
    public DocumentMetadata getMetadata() {
        return new DocumentMetadata(getUuid(), getFileName(), getDocumentDate(), getPersonName());
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
    
}
