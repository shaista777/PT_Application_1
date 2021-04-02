package com.ptapp.pt.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.ptapp.pt.model.Documents;
import com.ptapp.pt.repository.DocsRepository;
import com.ptapp.pt.status.Status;

@RestController
public class UploadController {

	@Autowired
	DocsRepository docsRepository;

	@PostMapping("/upload/db")
	public Status uploadToDB(@RequestParam("file") MultipartFile file) throws IOException {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Documents doc = new Documents("", fileName, file.getContentType(), file.getBytes());
		docsRepository.save(doc);

		return Status.SUCCESS;
	}

}
