package com.elex.oa.activiti.util;

import com.elex.oa.core.entity.IExecutionCmd;
import com.elex.oa.core.entity.ProcessMessage;
import com.elex.oa.entity.BpmRuPath;

/**
 *@author hugo.zhao
 *@since 2018/5/5 14:48
*/
public class ProcessHandleHelper {
    private static ThreadLocal<IExecutionCmd> processCmdLocal = new ThreadLocal();
    private static ThreadLocal<ProcessMessage> processMessageLocal = new ThreadLocal();
    private static ThreadLocal<BpmRuPath> backPathLocal = new ThreadLocal();
    private static ThreadLocal<Object> objectLocal = new ThreadLocal();

    public ProcessHandleHelper() {
    }

    public static void addErrorMsg(String message) {
        ProcessMessage msg = getProcessMessage();
        if(msg != null) {
            msg.getErrorMsges().add(message);
        }

    }

    public static void setProcessCmd(IExecutionCmd cmd) {
        processCmdLocal.set(cmd);
    }

    public static IExecutionCmd getProcessCmd() {
        return (IExecutionCmd)processCmdLocal.get();
    }

    public static void clearProcessCmd() {
        processCmdLocal.remove();
    }

    public static ProcessMessage getProcessMessage() {
        return (ProcessMessage)processMessageLocal.get();
    }

    public static void setProcessMessage(ProcessMessage processMessage) {
        processMessageLocal.set(processMessage);
    }

    public static void initProcessMessage() {
        ProcessMessage processMessage = new ProcessMessage();
        processMessageLocal.set(processMessage);
    }

    public static void clearProcessMessage() {
        processMessageLocal.remove();
    }

    public static void setBackPath(BpmRuPath bpmRuPath) {
        backPathLocal.set(bpmRuPath);
    }

    public static BpmRuPath getBackPath() {
        return (BpmRuPath)backPathLocal.get();
    }

    public static void clearBackPath() {
        backPathLocal.remove();
    }

    public static void setObjectLocal(Object obj) {
        objectLocal.set(obj);
    }

    public static Object getObjectLocal() {
        return objectLocal.get();
    }

    public static void clearObjectLocal() {
        objectLocal.remove();
    }
}
