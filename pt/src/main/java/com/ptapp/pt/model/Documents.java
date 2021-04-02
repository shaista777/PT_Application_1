package com.ptapp.pt.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "documents")
@EntityListeners(AuditingEntityListener.class)
public class Documents {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String fileName;
	private String fileType;
	private String userName;
	@Lob
	private byte[] fileBytes;
	@Column(updatable = false)
	private @CreatedDate Date createdAt;
	private @LastModifiedDate Date last_modified_at;

	public Documents(String userName,String fileName,String fileType,byte[] fileBytes) {
      this.fileName=fileName;
      this.fileType=fileType;
      this.userName=userName;
      this.fileBytes=fileBytes;
	}

	public Documents() {


	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(userName);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;

		if (!(obj instanceof Documents))
			return false;

		Documents doc = (Documents) obj;
		return Objects.equals(userName, doc.userName) && Objects.equals(fileName, doc.fileName) && Objects.equals(fileType, doc.fileType);
		

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User{username='" + userName + '\'' + ", fileName='" + fileName + '\'' + ", fileType=" + fileType+'}';
	}

}
