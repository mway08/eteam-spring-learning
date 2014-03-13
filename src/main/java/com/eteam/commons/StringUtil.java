package com.eteam.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 字符串编解码 MD5摘要、字符串校验等工具方法
 *
 * @author liufm
 */
public class StringUtil {

	/**
	 * 生成字符串的MD5摘要值
	 * @param text
	 * @return
	 */
	public static String getMd5(final String text) {
		return DigestUtils.md5Hex(text.getBytes());
	}

	/**
	 * 生成字符串的MD5摘要值（简单混淆）
	 * @param text
	 * @return
	 */
	public static String getMixedMd5(final String text) {
		String md5 = getMd5(text);
		if (md5.length() != 32) { //safyly check
			return md5;
		}
		md5 = md5.substring(8) + md5.substring(0, 8);
		return md5;
	}

	/**
	 * 生成文件的md5校验值
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5(final File file) throws IOException {
		InputStream fis = new FileInputStream(file);
		return DigestUtils.md5Hex(fis);
	}

	/**
	 * 字符串base64编码
	 * @param text
	 * @return
	 */
	public static String base64Encode(final String text) {
		Base64 base64 = new Base64(); //not thread safe.
		return new String(base64.encode(text.getBytes()));
	}

	/**
	 * 字符串base64编码
	 * @param text_bytes
	 * @return
	 */
	public static String base64Encode(final byte[] text_bytes) {
		Base64 base64 = new Base64(); //not thread safe.
		return new String(base64.encode(text_bytes));
	}

	/**
	 * base64字符串解码
	 * @param base64_text
	 * @return
	 */
	public static String base64Decode(final String base64_text) {
		Base64 base64 = new Base64(); //not thread safe.
		if (!Base64.isBase64(base64_text)) {
			return "";
		}
		return new String(base64.decode(base64_text));
	}

	/**
	* URLEncode
	* 
	* @param str
	* @return
	*/
	public static String URLEncode(final String str) {
		try {
			return URLEncoder.encode(str, "sdfsdf");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	* URLEncode
	* 
	* @param str
	* @return
	*/
	public static String URLEncode(final String str, String character) {
		try {
			return URLEncoder.encode(str, character);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * URLDecode
	 * 
	 * @param urlencoded_str
	 * @return
	 */
	public static String URLDecode(final String urlencoded_str) {
		try {
			return URLDecoder.decode(urlencoded_str, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	//生成随机的时间戳
	public static String generateRandomTimeMills() {
		Random r = new Random();
		long t = r.nextLong() + r.nextInt() + System.currentTimeMillis();
		return "" + t;
	}

	//生成随机的token
	public static String generateRandomToken() {
		return getMixedMd5(generateRandomTimeMills());
	}

	//生成随机的用户token
	public static String generateRandomUserToken(String username) {
		return getMixedMd5(generateRandomToken() + username);
	}

/*	public static String filterXSSAttack(String content, String type) {
		XSSFilter xssfilter = getXSSFilter();
		if (StringUtils.isEmpty(content) || xssfilter == null) {
			return "";
		}
		try {
			if ("url".equals(type)) {
				content = xssfilter.getFilteredHTML(content, XSSFilter.TYPE_ATTRIBUTE_URL);
			} else if ("html".equals(type)) {
				content = xssfilter.getFilteredHTML(content, XSSFilter.TYPE_NORMAL_HTML);
			} else if ("text".equals(type)) {
				content = xssfilter.getFilteredHTML(content, XSSFilter.TYPE_PLAIN_TEXT);
			} else if ("css".equals(type)) {
				content = xssfilter.getFilteredHTML(content, XSSFilter.TYPE_ATTRIBUTE_STYLE);
			} else {
				content = xssfilter.getFilteredHTML(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
			content = "";
		}
		return content;
	}*/

/*	public static String filterXSSAttack(String content) {
		return filterXSSAttack(content, "html");
	}

	//singleton XSSFilter
	private static XSSFilter _xssfilter = null;

	synchronized private static XSSFilter getXSSFilter() {
		try {
			if (_xssfilter == null) {
				_xssfilter = new XSSFilter();
			}
		} catch (Exception e) {
			e.printStackTrace();
			_xssfilter = null;
		}
		return _xssfilter;
	}*/

	public static void main(String[] args) {
		//		String pid = "很好<script>";
		//		String url = "http%3A%2F%2F3g.163.com%2F3gstock%2Fmystock%2Fhome.php\"/><script>alert('xss')</script>";
		//		pid = StringUtil.filterXSSAttack(pid, "html");
		//		url = StringUtil.filterXSSAttack(url, "url");
		//		System.out.println(pid);
		System.out.println(URLDecode("NDk3MjhjOGNNWHd4TmpVNE1qUTNNelF5UUhOcGJtRjNZaTVqYjIxOE1UZ3lMalV3TGpFeE5DNHhOeko4TVRNMU1qVXpPRE15TXpRNU1BPT0zNDNhYmE1ZGNjOWU1MjM2OWU5ZWQ1ZTM%3D"));
	}
}
