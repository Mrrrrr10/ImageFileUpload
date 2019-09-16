package com.nolookblog.common.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@RestController
@RequestMapping("/common")
public class UploadController {

	@Value("${system.config.qiNiuUrlPrefix}")
	private String qiNiuUrlPrefix;

	@Value("${system.config.qiNiuAccessKey}")
	private String accessKey;

	@Value("${system.config.qiNiuAccessKey}")
	private String secretKey;

	@Value("${system.config.qiNiuBucket}")
	private String bucket;

	@Value("${system.config.qiNiuMediaPath}")
	private String mediaPath;

	@Value("${system.config.fileServerUrl}")
	private String fileServerUrl;

	/**
	 * 图片上传到七牛云
	 *
	 * @param file
	 * @param method
	 * @return
	 */
	@PostMapping("/upload2QiuNiu")
	public Map<String, Object> upload2QiuNiu(MultipartFile file, String method) {
		Map<String, Object> map = new HashMap<>(0);
		map.put("status", 500);
		map.put("imageUrl", null);

		try {
			// 1.获取文件名
			String filename = file.getOriginalFilename();

			// 2.获取文件字节
			byte[] bytes = file.getBytes();

			// 3.官方配置
			Configuration cfg = new Configuration(Zone.zone0());
			UploadManager uploadManager = new UploadManager(cfg);
			// 上传的文件名
			String key = UUID.randomUUID().toString().replaceAll("-", "")
					+ "_" + filename + "_" + method;

			try {
				Auth auth = Auth.create(accessKey, secretKey);
				String upToken = auth.uploadToken(bucket);

				try {
					// 上传图片
					Response response = uploadManager.put(bytes, key, upToken);

					// 解析上传成功的结果
					DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

					map.put("imageUrl", qiNiuUrlPrefix + key);
				} catch (QiniuException ex) {
					Response r = ex.response;
					System.err.println(r.toString());
					try {
						System.err.println(r.bodyString());
					} catch (QiniuException ex2) {
						//ignore
					}
				}
			} catch (Exception ex) {
				//ignore
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 图片上传到本地
	 *
	 * @param file
	 * @param method
	 * @param req
	 * @return
	 */
	@PostMapping("/upload2Local")
	public Map<String, Object> upload2Local(MultipartFile file, String method, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<>(0);
		map.put("status", 500);
		map.put("imageUrl", null);

		try {
			// 1. 获取上传文件路径
			String path = req.getSession().getServletContext().getRealPath("/upload/");

			// 2. 创建日期
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

			// 3. 创建文件目录
			File f = new File(path + date);
			if (!f.exists()) {
				f.mkdirs();
			}

			// 4. 获取文件名
			String fileName = file.getOriginalFilename();

			// 5. UUID + _ + 文件名
			String uniqueFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;

			// 6. 上传文件
			file.transferTo(new File(f, uniqueFileName));

			map.put("status", 200);
			map.put("imageUrl", uniqueFileName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 图片上传到FastDFS文件服务器
	 *
	 * @param file
	 * @return
	 */
	@PostMapping("/upload2FastDfs")
	public Map<String, Object> upload(MultipartFile file) {
		Map<String, Object> map = new HashMap<>(0);
		map.put("status", 500);
		map.put("url", null);

		try {
			// 1.加载配置文件(TODO: 访问路径不能包含中文)
			String path = this.getClass().getResource("/fastdfs-client.conf").getPath();

			// 2.初始化客户端全局对象
			ClientGlobal.init(path);

			// 3.创建存储客户端对象
			StorageClient storageClient = new StorageClient();

			// 4.获取源文件名
			String originalFilename = file.getOriginalFilename();

			// 5.文件上传
			// byte[] file_buff, String file_ext_name, NameValuePair[] meta_list
			String[] arr = storageClient.upload_file(
					file.getBytes(),
					FilenameUtils.getExtension(originalFilename),
					null);
			StringBuilder url = new StringBuilder(fileServerUrl);

			for (String str : arr) {
				url.append("/" + str);
			}

			map.put("status", 200);
			map.put("url", url.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

}
