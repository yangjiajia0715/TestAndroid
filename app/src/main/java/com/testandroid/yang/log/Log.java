package com.testandroid.yang.log;


import com.testandroid.yang.BuildConfig;

/**
 * 日志能力类
 *
 */
public class Log {
	/**
	 * 设定SD卡上的日志文件保存目录
	 */
//	private final static String path = Environment.getExternalStorageDirectory() + LocalFileManager.BASE_DIR + "/cache/log";
	/**
	 * 缺省日志保存的天数
	 */
	private final static int defaultSaveDays = 7;
	/**
	 * 控制台显示的日志标签
	 */
	protected static final String TAG = "info";
	
	/**
	 * 是否启动日志能力 
	 */
	private static boolean mIsEnabled = BuildConfig.DEBUG;//在build.gradle中配置, 或者自定义字段，2016-10-27
	
	/**
	 * 私有化构造方法，防止new实例
	 */
	private Log() {
	}
	
	/**
	 * 获取堆栈中完整的日志信息（类名，方法名）
	 * @param message 日志信息
	 * @return 组合了类名.方法名():message形式的日志信息。
	 */
	private static String buildMessage(String message) {
		StackTraceElement ste = new Throwable().fillInStackTrace().getStackTrace()[2];
		
		StringBuffer sb = new StringBuffer();
		sb.append(ste.getClassName());
		sb.append(".");
		sb.append(ste.getMethodName());
		sb.append("(): ");
		sb.append(message);
//		toFile(sb.toString());
		return sb.toString();
	}
	
	/**
	 * 查询日志输出开启状态
	 * @return 日志输出开启状态
	 */
	public static boolean isEnabled() {
		return mIsEnabled;
	}
	
	/**
	 * 在控制台输出  VERBOSE 级别日志信息。
	 * @param msg
	 */
	public static void v(String msg) {
		if(isEnabled()) {
			android.util.Log.v(TAG, buildMessage(msg));
		}
	}
	
	/**
	 * 在控制台输出  VERBOSE 级别日志信息。
	 * @param msg 日志信息
	 * @param throwable Throwable对象
	 */
	public static void v(String msg, Throwable throwable) {
		if(isEnabled()) {
			android.util.Log.v(TAG, buildMessage(msg), throwable);
		}
	}
	
	/**
	 * 在控制台输出  DEBUG 级别日志信息。
	 * @param msg 日志信息
	 */
	public static void d(String msg) {
		if(isEnabled()) {
			android.util.Log.d(TAG, buildMessage(msg));
		}
	}
	
	/**
	 * 在控制台输出  DEBUG 级别日志信息。 
	 * @param msg 日志信息
	 * @param throwable Throwable 对象
	 */
	public static void d(String msg, Throwable throwable) {
		if(isEnabled()) {
			android.util.Log.d(TAG, buildMessage(msg), throwable);
		}
	}
	
	/**
	 * 在控制台输出  INFO 级别日志信息。 
	 * @param msg 日志信息
	 */
	public static void i(String msg) {
		if(isEnabled()) {
			android.util.Log.i(TAG, buildMessage(msg));
		}
	}
	
	/**
	 * 在控制台输出  INFO 级别日志信息。 
	 * @param msg 日志信息
	 * @param throwable Throwable对象
	 */
	public static void i(String msg, Throwable throwable) {
		if(isEnabled()) {
			android.util.Log.i(TAG, buildMessage(msg), throwable);
		}
	}
	
	/**
	 * 在控制台输出  ERROR 级别日志信息。 
	 * @param msg 日志信息
	 */
	public static void e(String msg) {
		if(isEnabled()) {
			android.util.Log.e(TAG, buildMessage(msg));
		}
	}
	
	/**
	 * 在控制台输出  ERROR 级别日志信息。 
	 * @param msg 日志信息
	 * @param throwable Throwable对象
	 */
	public static void e(String msg, Throwable throwable) {
		if(isEnabled()) {
			android.util.Log.e(TAG, buildMessage(msg), throwable);
		}
	}
	
	/**
	 * send a WARN log message
	 * @param msg
	 */
	public static void w(String msg) {
		if(isEnabled()) {
			android.util.Log.w(TAG, buildMessage(msg));
		}
	}
	
	/**
	 * 在控制台输出  WARN 级别日志信息。 
	 * @param msg 日志信息
	 * @param throwable Throwable对象
	 */
	public static void w(String msg, Throwable throwable) {
		if(isEnabled()) {
			android.util.Log.w(TAG, buildMessage(msg), throwable);
		}
	}
	
	/**
	 * 在控制台输出  DEBUG 级别日志信息。 
	 * @param msg 日志信息
	 */
	public static void debug(String msg) {
		android.util.Log.d(TAG, buildMessage(msg));
	}

	/**
	 * 关闭日志能力。
	 */
	/*public static void disable(){
		mIsEnabled = false;
	}*/
	
	/**
	 * 开始日志能力，
	 */
	/*public static void enable(){
		mIsEnabled = true;
	}*/
}