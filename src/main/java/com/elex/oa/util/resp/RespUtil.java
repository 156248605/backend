package com.elex.oa.util.resp;


public class RespUtil {
	
	public static <T> Resp<T> successResp(String code,String msg,T t)
	{
		Resp<T> resp = new Resp<T>();
		Head head = new Head();
		head.setRspCode(code);
		head.setRspMsg(msg);
		resp.setHead(head);
		resp.setBody(t);
		return resp;
	}

}
