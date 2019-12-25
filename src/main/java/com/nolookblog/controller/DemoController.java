package com.nolookblog.controller;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description 本地测试的接口
 */

@RestController
@RequestMapping("/demo")
public class DemoController {

	private int time = 0;

	/**
	 * 获取数据
	 *
	 * @return
	 */
	@GetMapping("/getData")
	public Map<String, Object> getData() throws Exception {
		Map<String, Object> map = new HashMap<>();
		Boolean success = false;

		time++;
		if (time == 1) {
			throw new ArrayIndexOutOfBoundsException();
		} else if (time == 2) {
			throw new NullPointerException();
		} else if (time == 3) {
			throw new RuntimeException();
		} else if (time == 4) {
			throw new Exception();
		} else {
			if (Math.random() > 0.1) {
				int seconds = RandomUtils.nextInt(1, 5);
				TimeUnit.SECONDS.sleep(seconds);
				System.out.println("耗时: " + seconds);
				success = true;
				map.put("callTime", seconds);
			}
		}

		map.put("success", success);
		map.put("data", "");

		return map;
	}
}
