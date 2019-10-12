package com.nolookblog.common.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description	数值工具类
 */

public class CustomerNumberUtils extends NumberUtils {

	/**
	 * double类型的数据转换为int类型的数据
	 *
	 * @param source	需要转换的数字
	 * @param multiple	倍数
	 * @return
	 */
	public static int double2Int(double source, double multiple) {
		BigDecimal big1 = new BigDecimal(source).setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal big2 = new BigDecimal(multiple);

		return big1.multiply(big2).intValue();
	}
}
