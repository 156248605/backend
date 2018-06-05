package com.elex.oa.core.entity;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 *@author hugo.zhao
 *@since 2018/5/5 15:31
*/
public class ProcessMessage {
    private Set<String> messages = new HashSet();
    private Set<String> errorMsges = new HashSet();

    public ProcessMessage() {
    }

    public Set<String> getMessages() {
        return this.messages;
    }

    public void setMessages(Set<String> messages) {
        this.messages = messages;
    }

    public Set<String> getErrorMsges() {
        return this.errorMsges;
    }

    public void addMsg(String str) {
        this.messages.add(str);
    }

    public void addErrorMsg(String str) {
        this.errorMsges.add(str);
    }

    public void setErrorMsges(Set<String> errorMsges) {
        this.errorMsges = errorMsges;
    }

    public String getErrors() {
        StringBuffer sb = new StringBuffer();
        Iterator var2 = this.errorMsges.iterator();

        while(var2.hasNext()) {
            String err = (String)var2.next();
            sb.append(err).append("<br/>");
        }

        return sb.toString();
    }
}
