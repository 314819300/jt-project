package com.jt.manage.service;

import com.jt.common.vo.PicUploadResult;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


/**
 * @author wangning
 * @create 2021-01-26 20:18
 */
@Service
public class FileServiceImpl implements FileService {

	@Value("${image.localPath}")
	private String localPath;

	@Value("${image.urlPath}")
	private String urlPath;

	/**
	 * 文件上传的实现思路
	 * 1.校验文件类型 jpg|png|gif...
	 * 2.校验是否为恶意程序
	 * 3.为了防止图片检索速度慢，采用分文件存储 yyyy/MM/dd
	 * 4.防止文件重名 使用UUID + 随机数(3)
	 * 5.实现文件上传
	 * @param uploadFile
	 * @return
	 */
	@Override
	public PicUploadResult upload(MultipartFile uploadFile) {
		PicUploadResult result = new PicUploadResult();
		//1.获取文件名称
		String fileName = uploadFile.getOriginalFilename();
		fileName = fileName.toLowerCase();
		if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
			result.setError(1);//
			return result;
		}

		//2.校验文件是否为恶意程序

		try {
			BufferedImage image = ImageIO.read(uploadFile.getInputStream());
			int width = image.getWidth();
			int height = image.getHeight();
			if(width == 0 || height == 0) {
				result.setError(1);//一定不是图片
				return result;
			}
			//3.实现文件分类存储
			String dateDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			String localPathDir = localPath + dateDir;
			//判断文件夹是否存在
			File fileDir = new File(localPathDir);
			if(!fileDir.exists()) {
				fileDir.mkdirs(); //不存在，创建文件夹
			}

			//4.定义文件的名称
			String uuid = UUID.randomUUID().toString().replace("-", "");
			int random = new Random().nextInt(1000);
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			//拼接真正的文件名称
			String realName = uuid + random + fileType;
			String localPathReal = localPathDir + "/" + realName;
			uploadFile.transferTo(new File(localPathReal));
			//定义回显url
//			String url = "http://img10.360buyimg.com/babel/s1180x940_jfs/t1/160141/24/4901/77459/600e8f79E24dd9f04/af7cc2589c5d3d61.jpg.webp";
			String url = urlPath + dateDir + "/" +realName;
			result.setUrl(url);


		} catch (IOException e) {
			e.printStackTrace();
			result.setError(1);//文件上传失败
			return result;
		}


		return result;
	}
}
