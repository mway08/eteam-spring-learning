package com.eteam.commons;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileUtils {

	/**
	 * Gets the extension of a filename.
	 * <p/>
	 * This method returns the textual part of the filename after the last dot.
	 * There must be no directory separator after the dot.
	 * 
	 * <pre>
	 * foo.txt      --> "txt"
	 * a/b/c.jpg    --> "jpg"
	 * a/b.txt/c    --> ""
	 * a/b/c        --> ""
	 * </pre>
	 * <p/>
	 * The output will be the same irrespective of the machine that the code is
	 * running on.
	 * 
	 * @param filename
	 *            the filename to retrieve the extension of.
	 * @return the extension of the file or an empty string if none exists.
	 */
	public static String getExtension(String filename) {
		return FilenameUtils.getExtension(filename);
	}

	/**
	 * Removes the extension from a filename.
	 * <p/>
	 * This method returns the textual part of the filename before the last dot.
	 * There must be no directory separator after the dot.
	 * 
	 * <pre>
	 * foo.txt    --> foo
	 * a\b\c.jpg  --> a\b\c
	 * a\b\c      --> a\b\c
	 * a.b\c      --> a.b\c
	 * </pre>
	 * <p/>
	 * The output will be the same irrespective of the machine that the code is
	 * running on.
	 * 
	 * @param filename
	 *            the filename to query, null returns null
	 * @return the filename minus the extension
	 */
	public static String removeExtension(String filename) {
		return FilenameUtils.removeExtension(filename);
	}

	/**
	 * �?��目录是否存在,如不存在则新�?
	 * 
	 * @param path
	 * @return 如果不存在 返回true, 存在则返回false
	 * @throws IOException
	 */
	public static boolean createDirectoryIfNotExists(String path)
			throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			org.apache.commons.io.FileUtils.forceMkdir(file);
			return true;
		}
		return false;
	}

	/**
	 * 在文件名和后插入时间
	 * 
	 * @param filename
	 *            文件
	 * @param seperator
	 *            文件名和后缀间的分隔
	 * @return
	 */
	public static String insertTimestampToFilename(String filename,
			String seperator) {
		String ext = FileUtils.getExtension(filename);
		String basename = FileUtils.removeExtension(filename);
		filename = String.format("%s_%s.%s", basename, new Date().getTime(),
				ext);
		return filename;
	}

	public static String calcHumanReadableLength(String path) {
		return calcHumanReadableLength(new File(path).length());
	}

	public static String calcHumanReadableLength(long size) {
		return org.apache.commons.io.FileUtils.byteCountToDisplaySize(size);
	}

	public static String getAbsolutePath(String dir, String filename) {
		return new File(dir, filename).getAbsolutePath();
	}

	public static String contractPath(String... strings) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strings.length; ++i) {
			sb.append(strings[i]);
			if ((i != strings.length - 1)
					&& (File.separatorChar != sb.charAt(sb.length() - 1)))
				sb.append(File.separatorChar);
		}
		return sb.toString();
	}

	public static boolean exists(String path) {
		if (StringUtils.isEmpty(path))
			return false;

		File file = new File(path);

		return file.exists();
	}

	/**
	 * 删除文件/文件 不抛异常
	 * 
	 * @param path
	 */
	public static boolean deleteQuietly(String path) {
		return org.apache.commons.io.FileUtils.deleteQuietly(new File(path));
	}

	/**
	 * Moves a file or directory to the destination directory.
	 * <p/>
	 * When the destination is on another file system, do a "copy and delete".
	 * 
	 * @param src
	 *            the file or directory to be moved
	 * @param destDir
	 *            the destination directory
	 * @param createDestDir
	 *            If <code>true</code> create the destination directory,
	 *            otherwise if <code>false</code> throw an IOException
	 * @throws NullPointerException
	 *             if source or destination is <code>null</code>
	 * @throws IOException
	 *             if source or destination is invalid
	 * @throws IOException
	 *             if an IO error occurs moving the file
	 * @since Commons IO 1.4
	 */
	public static void moveToDirectory(File src, File destDir,
			boolean createDestDir) throws IOException {
		org.apache.commons.io.FileUtils.moveToDirectory(src, destDir,
				createDestDir);
	}

	public static void moveToDirectory(String src, String destDir,
			boolean createDestDir) throws IOException {
		org.apache.commons.io.FileUtils.moveToDirectory(new File(src),
				new File(destDir), createDestDir);
	}
}
