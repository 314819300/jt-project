package com.jt.manage.service;

import com.jt.common.vo.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangning
 * @create 2021-01-26 20:17
 */
public interface FileService {
	PicUploadResult upload(MultipartFile uploadFile);
}
