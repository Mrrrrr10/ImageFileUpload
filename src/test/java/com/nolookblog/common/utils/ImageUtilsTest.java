package com.nolookblog.common.utils;


import org.junit.Test;

import java.io.File;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public class ImageUtilsTest {

	@Test
	public void testDownloadWithoutCookie() {
		try {
			ImageUtils.downloadWithoutCookie("http://ad.wkanx.com/user/captcha", "captcha.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFilePath() {
		File file = new File(File.separator); //File.separator表示根目录，比如现在就表示在D盘下。
		System.out.println(file.getPath());
	}

}