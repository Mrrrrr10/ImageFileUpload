package com.nolookblog.common.utils;


import org.junit.Test;

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

}