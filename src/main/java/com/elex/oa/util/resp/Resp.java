package com.elex.oa.util.resp;

public class Resp<T> {
	
	private Head head;
	
	private T body;
	
	public Head getHead() {
		return head;
	}
	public void setHead(Head head) {
		this.head = head;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	
}
