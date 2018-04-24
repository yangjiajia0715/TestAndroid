package com.testandroid.yang.log;


public class LogInfo {

	public static StringBuffer getMessageInfo(Exception exception) {
		StringBuffer messageInfo = new StringBuffer();
		StackTraceElement[] messages = exception.getStackTrace();
		messageInfo.append("descibe:" + exception.getClass());
		messageInfo.append("  location:" + messages[0].toString());

		/*for (int i = 0; i < messages.length; i++) {
			messageInfo.append("\n" + "第" + i + "个错误！");
			messageInfo.append("  descibe:" + exception.getLocalizedMessage());
			messageInfo.append("  descibe:" + exception.getMessage());
			messageInfo.append("  descibe:" + exception.getCause());
			messageInfo.append("  descibe:" + exception.getStackTrace());
			messageInfo.append(messages[i].getClassName());
			messageInfo.append(messages[i].getFileName());
			messageInfo.append(messages[i].getLineNumber());
			messageInfo.append(messages[i].getMethodName());
		}*/

		return messageInfo;
	}
	
	/*public static ReqLogData LogToServer (Exception exception) {
		JSONObject object = new JSONObject();
		try {
			object.put("dataType", ConstantInfos.SAVE_LOG);
			JSONObject item = new JSONObject();

			item.put("uuid", TKContext.getMyUUID());
			item.put("username", ConstantInfos.USERNAME);
			item.put("message", LogInfo.getMessageInfo(exception));
			item.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
		
			object.put("loginfo", item);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return  new ReqLogData(object.toString());
	}*/

}
