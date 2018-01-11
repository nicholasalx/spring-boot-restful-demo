package com.rjhy.cloud.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.rjhy.cloud.common.utils.IOUtils;
import com.rjhy.cloud.common.utils.URLUtils;
import com.rjhy.cloud.vm.ResultVM;

@RestController
@RequestMapping("/common")
public class CommonController {
	
	@Value("${file-upload-path}")
    private String fileUploadPath;//从配置文件中读取文件存储的配置
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object doUploadFile(MultipartFile file) {
		String name = file.getOriginalFilename();
		Map<String, String> map = null;
		try {
			map = uploadFile(file);
			map.put("orgName", name);
		} catch (IOException e1) {
			 ResultVM.error("文件上传失败！");
		}
		return ResultVM.ok(map);
	}
	
    /**
	 * 上传文件
	 * @param file 文件
	 */
	protected Map<String, String> uploadFile(MultipartFile file) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fileSize", String.valueOf(file.getSize()));	
		Long datetime = System.currentTimeMillis();
		FileUtils.copyInputStreamToFile(file.getInputStream(),
				new File(fileUploadPath + "/temp/", datetime + file.getOriginalFilename()));
		map.put("filePath", "temp/" + datetime + file.getOriginalFilename());
		map.put("docStorePath", fileUploadPath);
		return map;
	}
	
	/**
	 * 下载文件实例
	 * 
	 * @return
	 * @throws IOException
	 */
/*	@RequestMapping("/down/{id}")
	public ResponseEntity<byte[]> down(@PathVariable("id")String id) throws IOException {
		AssessAttachment att = service.getAttachment(id);
		if(att == null)
			return null;
		File file = new File(PropertyUtil.getProperty("docStorePath") + att.getFileUrl());
		String fileName = file.getName().substring(13);
		return download(file,fileName);
	}*/
	
    
    /**
	 * 下载文件
	 * @param file 文件
	 */
	protected ResponseEntity<byte[]> download(File file) throws IOException {
		String fileName = file.getName().substring(13);
		return download(file, fileName);
	}
	
	/**
	 * 下载文件
	 * @param file 文件
	 * @param fileName 写出的文件名
	 */
	protected ResponseEntity<byte[]> download(File file, String fileName) throws IOException {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] body = IOUtils.copyToByteArray(in);
			return download(body, fileName);
		} finally {
			IOUtils.closeQuietly(in);	
		}
	}
	
	/**
	 * 下载文件
	 * @param body 数据
	 * @param fileName 生成的文件名
	 * @return {ResponseEntity}
	 */
	protected ResponseEntity<byte[]> download(byte[] body, String fileName) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String header = request.getHeader("User-Agent").toUpperCase();
		HttpStatus status;
		if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
			fileName = URLUtils.encodeURL(fileName, Charsets.UTF_8.name());
			status = HttpStatus.OK;
		} else {
			fileName = new String(fileName.getBytes(Charsets.UTF_8), Charsets.ISO_8859_1);
			status = HttpStatus.CREATED;
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentLength(body.length);
		return new ResponseEntity<byte[]>(body, headers, status);
	}
}
