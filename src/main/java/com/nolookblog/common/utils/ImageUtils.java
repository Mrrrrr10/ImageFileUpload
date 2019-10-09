package com.nolookblog.common.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description 图片下载工具类
 */

public class ImageUtils {

	private static final Integer BUFFER_SIZE = 1024;
	private static File file;
	private static String path = System.getProperty("user.dir");

	static {
		file = new File(path + "\\src\\main\\resources");
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * 下载文件到本地（不带cookie）
	 *
	 * @param urlString 被下载的文件地址
	 * @param fileName  本地文件名
	 * @throws Exception 各种异常
	 */
	public static void downloadWithoutCookie(String urlString, String fileName) {
		InputStream inputStream = null;
		try (
				OutputStream fos = new FileOutputStream(file.getPath() + "\\"
						+ fileName);
				BufferedOutputStream bos = new BufferedOutputStream(fos)
		) {
			// 构造URL
			URL url = new URL(urlString);

			// 打开连接
			URLConnection conn = url.openConnection();

			// 获取cookie
			String cookie = conn.getHeaderField("Set-Cookie");
			System.out.println(cookie);

			// 输入流
			inputStream = conn.getInputStream();

			byte[] buffer = new byte[BUFFER_SIZE];

			// 读取到的数据长度
			int len;

			while ((len = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载文件到本地（带cookie）
	 *
	 * @param urlString 被下载的文件地址
	 * @param fileName  本地文件名
	 * @throws Exception 各种异常
	 */
	public static void downloadWithCookie(String urlString, String fileName) {
		String cookie = null;
		InputStream inputStream = null;

		try (
				FileOutputStream fos = new FileOutputStream(file.getPath() + "\\" + fileName)
		)

		{
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestProperty("Host", "paper.cnstock.com");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0");
			conn.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			// 注意编码，gzip可能会乱码
			conn.setRequestProperty("Accept-Encoding", "utf8, deflate");
			conn.setRequestProperty("Content-Encoding", "utf8");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
			conn.setRequestProperty("Cookie", cookie);
			conn.setRequestProperty("Cache-Control", "max-age=0");
			conn.setRequestProperty("Content-Type", "application/pdf");

			inputStream = conn.getInputStream();

			byte[] buffer = new byte[BUFFER_SIZE];
			int len;
			while ((len = inputStream.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
