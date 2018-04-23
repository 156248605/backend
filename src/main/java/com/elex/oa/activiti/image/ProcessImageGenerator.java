package com.elex.oa.activiti.image;

import com.elex.oa.entity.activiti.BpmNodeStatus;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.image.ProcessDiagramGenerator;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/4/9 10:17
*/
public interface ProcessImageGenerator extends ProcessDiagramGenerator{
    InputStream generateDiagram(BpmnModel var1, String var2, Map<String,BpmNodeStatus> var3, List<String> var4,String var5, String var6,ClassLoader var7,double var8);
}
