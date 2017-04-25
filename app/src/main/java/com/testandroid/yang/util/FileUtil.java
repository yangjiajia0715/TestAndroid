package com.testandroid.yang.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 文件操作工具类
 *
 */
public class FileUtil {
	private static final int MB = 1024 * 1024;
	private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 2;
	private static final int LOW_FREE_SPACE = 20;
	
	private FileUtil(){
		
	}
	
	/**
	 * 判断指定路径文件是否存在
	 * @param filePath 文件路径
	 * @return 是否存在
	 */
	public static boolean isFileExist(String filePath) {
		if (TextUtils.isEmpty(filePath))
			return false;

		File file = new File(filePath);
		return file.exists();

	}

	/**
	 * 读取文件数据
	 * @param path 文件路径
	 * @return 文件数据
	 * @throws Exception 读取异常
	 */
	public static byte[] readFile(String path) throws Exception {
		if (!isFileExist(path)) {
			return null;
		}
		FileInputStream inStream = new FileInputStream(new File(path));
		return readInputStream(inStream);
	}


	/**
	 * 根据输入流对象读取文件数据
	 * @param inStream 输入流
	 * @return 文件对象
	 * @throws Exception 读取异常
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}
	
    /**
     * 检测外部存储是否可写
     * @return boolean 外部存储是否可写
     */
	public static boolean externalStorageWriteable() {
        String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);
	}
    
	/**
	 * 检测存储是否可用
	 * 
	 * @return 存储是否可用
	 */
	public static boolean isExternalStorageAvailable() {
		String state = Environment.getExternalStorageState();
		// We can read and write the media
// Something else is wrong. It may be one of many other states, but
// all we need
// to know is we can neither read nor write
		return Environment.MEDIA_MOUNTED.equals(state);
	}

	/**
	 * 计算sdcard上的剩余空间
	 * @return 以 MB 为单位的返回值。
	 */
	private static int freeSpaceOnSd() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
		@SuppressWarnings("deprecation")
		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat.getBlockSize()) / MB;
		return (int) sdFreeMB;
	}
	
	/**
	 * 移动文件
	 * @param from 源文件路径
	 * @param to 目的文件路径
	 * @return 是否搬移成功
	 */
	public static boolean moveFile(String from, String to){
		deleteFile(to);
		File file = new File(from);
		try{	
			return file.renameTo(new File(to));
		}catch(Exception e){
			return false;
		}
	}
	
	/**
     * 删除文件
     * @param filePath 待删除文件
     * @return 删除成功与否
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }

        if (!file.canRead() || !file.canWrite()) {
            return false;
        }

        if (!file.isFile()) {
            return false;
        }

        boolean success = file.delete();
        return success;
    }
    
    /** 
     * 复制单个文件 
     * @param oldPath String 原文件路径 如：c:/fqf.txt 
     * @param newPath String 复制后路径 如：f:/fqf.txt 
     * @return boolean 
     */ 
   public static boolean copyFile(String oldPath, String newPath) { 
       try { 
    	   deleteFile(newPath); 
           int byteread = 0; 
           File oldfile = new File(oldPath); 
           if (oldfile.exists()) { //文件存在时 
               InputStream inStream = new FileInputStream(oldPath); //读入原文件
               FileOutputStream fs = new FileOutputStream(newPath); 
               byte[] buffer = new byte[1444]; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   fs.write(buffer, 0, byteread); 
               } 
               fs.close();
               inStream.close(); 
               return true;
           } 
           return false;
       } 
       catch (Exception e) { 
           System.out.println("复制单个文件操作出错"); 
           e.printStackTrace(); 
           return false;
       } 

   } 
   
   /**
    * 判断SD卡是否有足够可用空间。
    * @return SD卡是否有足够可用空间
    */
   public static boolean isEnoughSDCardFreeSpace(){
	   return LOW_FREE_SPACE < freeSpaceOnSd();
   }
   
   public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
		}else{
			return null;
		}
		return sdDir.toString();
	}
   

	/**
	 * 将Bitmap保存本地JPG图片
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static boolean saveBitmapToFile(String dir,String fileName,Bitmap bitmap) throws IOException {
		boolean result = false;
		FileOutputStream fos = null;
		try {
			
			File path = new File(dir);
			if (!path.exists()) {
				path.mkdirs();
			}
			
			String filePath = dir + File.separator + fileName + ".jpg";
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
			file.createNewFile();
			fos = new FileOutputStream(file);
			result = bitmap.compress(CompressFormat.JPEG, 100, fos);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos != null){
				fos.close();
			}
			return result;
		}
	}
   
}

