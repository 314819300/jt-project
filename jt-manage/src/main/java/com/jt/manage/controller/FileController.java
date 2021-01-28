package com.jt.manage.controller;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author wangning
 * @create 2021-01-26 19:33
 * 文件上传案例
 */
@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	/**
	 * 编辑Controller方法的步骤
	 * 1.明确url地址
	 * 2.明确返回值类型 页面String json（对象）
	 * 3.注意请求参数！！！！！！
	 * 4.注意return的写法
	 * @return 返回值
	 */
	@RequestMapping("/file")
	public String file(MultipartFile pic) throws Exception{
		//1.获取文件上传的名字
		String name = pic.getOriginalFilename();
		//2.定义文件上传的路径
		File fileDir = new File("F:\\jt-upload");
		//3.判断文件夹是否存在
		if(!fileDir.exists()) {
			//创建文件夹
			fileDir.mkdirs();
		}
		pic.transferTo(new File("F:\\jt-upload\\" + name));
		return "redirect:/file.jsp";
	}

	//实现文件上传
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult fileUpload(MultipartFile uploadFile) {

		return fileService.upload(uploadFile);
	}
}
