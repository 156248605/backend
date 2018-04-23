package com.elex.oa.activiti.image;

import com.elex.oa.entity.activiti.BpmNodeStatus;
import com.elex.oa.util.StringUtil;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;

import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.activiti.image.impl.DefaultProcessDiagramCanvas;
import org.springframework.stereotype.Service;

import java.awt.geom.RoundRectangle2D.Double;
/**
 *@author hugo.zhao
 *@since 2018/4/9 10:22
*/
public class ProcessDiagramGeneratorExt extends DefaultProcessDiagramGenerator implements ProcessImageGenerator{
    private Map<String, String> processColors = new HashMap();
    private Map<String, String> timeoutColors = new HashMap();

    public ProcessDiagramGeneratorExt() {
    }
    @Override
    public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, Map<String, BpmNodeStatus> highLightedActivities, List<String> highLightedFlows, String activityFontName, String labelFontName, ClassLoader customClassLoader, double scaleFactor) {
        return this.generateProcessDiagram(bpmnModel, imageType, highLightedActivities, highLightedFlows, activityFontName, labelFontName, customClassLoader, scaleFactor).generateImage(imageType);
    }

    protected DefaultProcessDiagramCanvas generateProcessDiagram(BpmnModel bpmnModel, String imageType, Map<String, BpmNodeStatus> highLightedActivities, List<String> highLightedFlows, String activityFontName, String labelFontName, ClassLoader customClassLoader, double scaleFactor) {
        this.prepareBpmnModel(bpmnModel);
        ProcessDiagramCanvasExt processDiagramCanvas = initProcessDiagramCanvas(bpmnModel, imageType, activityFontName == null?"黑体":activityFontName, labelFontName == null?"黑体":labelFontName, customClassLoader);
        Iterator var11 = bpmnModel.getPools().iterator();

        while(var11.hasNext()) {
            Pool process = (Pool)var11.next();
            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(process.getId());
            processDiagramCanvas.drawPoolOrLane(process.getName(), graphicInfo);
        }

        var11 = bpmnModel.getProcesses().iterator();

        Process process1;
        Iterator graphicInfo2;
        while(var11.hasNext()) {
            process1 = (Process)var11.next();
            graphicInfo2 = process1.getLanes().iterator();

            while(graphicInfo2.hasNext()) {
                Lane artifact = (Lane)graphicInfo2.next();
                GraphicInfo graphicInfo1 = bpmnModel.getGraphicInfo(artifact.getId());
                processDiagramCanvas.drawPoolOrLane(artifact.getName(), graphicInfo1);
            }
        }

        var11 = ((Process)bpmnModel.getProcesses().get(0)).findFlowElementsOfType(FlowNode.class).iterator();

        while(var11.hasNext()) {
            FlowNode process2 = (FlowNode)var11.next();
            this.drawActivity(processDiagramCanvas, bpmnModel, process2, highLightedActivities, highLightedFlows, scaleFactor);
        }

        var11 = bpmnModel.getProcesses().iterator();

        while(var11.hasNext()) {
            process1 = (Process)var11.next();
            graphicInfo2 = process1.getArtifacts().iterator();

            while(graphicInfo2.hasNext()) {
                Artifact artifact1 = (Artifact)graphicInfo2.next();
                this.drawArtifact(processDiagramCanvas, bpmnModel, artifact1);
            }
        }

        return processDiagramCanvas;
    }

    protected static ProcessDiagramCanvasExt initProcessDiagramCanvas(BpmnModel bpmnModel, String imageType, String activityFontName, String labelFontName, ClassLoader customClassLoader) {
        double minX = 1.7976931348623157E308D;
        double maxX = 0.0D;
        double minY = 1.7976931348623157E308D;
        double maxY = 0.0D;

        GraphicInfo nrOfLanes;
        for(Iterator flowNodes = bpmnModel.getPools().iterator(); flowNodes.hasNext(); maxY = nrOfLanes.getY() + nrOfLanes.getHeight()) {
            Pool artifacts = (Pool)flowNodes.next();
            nrOfLanes = bpmnModel.getGraphicInfo(artifacts.getId());
            minX = nrOfLanes.getX();
            maxX = nrOfLanes.getX() + nrOfLanes.getWidth();
            minY = nrOfLanes.getY();
        }

        List var22 = gatherAllFlowNodes(bpmnModel);
        Iterator var23 = var22.iterator();

        label155:
        while(var23.hasNext()) {
            FlowNode var25 = (FlowNode)var23.next();
            GraphicInfo artifact = bpmnModel.getGraphicInfo(var25.getId());
            if(artifact.getX() + artifact.getWidth() > maxX) {
                maxX = artifact.getX() + artifact.getWidth();
            }

            if(artifact.getX() < minX) {
                minX = artifact.getX();
            }

            if(artifact.getY() + artifact.getHeight() > maxY) {
                maxY = artifact.getY() + artifact.getHeight();
            }

            if(artifact.getY() < minY) {
                minY = artifact.getY();
            }

            Iterator process = var25.getOutgoingFlows().iterator();

            while(true) {
                List l;
                do {
                    if(!process.hasNext()) {
                        continue label155;
                    }

                    SequenceFlow graphicInfoList = (SequenceFlow)process.next();
                    l = bpmnModel.getFlowLocationGraphicInfo(graphicInfoList.getId());
                } while(l == null);

                Iterator graphicInfo = l.iterator();

                while(graphicInfo.hasNext()) {
                    GraphicInfo graphicInfo1 = (GraphicInfo)graphicInfo.next();
                    if(graphicInfo1.getX() > maxX) {
                        maxX = graphicInfo1.getX();
                    }

                    if(graphicInfo1.getX() < minX) {
                        minX = graphicInfo1.getX();
                    }

                    if(graphicInfo1.getY() > maxY) {
                        maxY = graphicInfo1.getY();
                    }

                    if(graphicInfo1.getY() < minY) {
                        minY = graphicInfo1.getY();
                    }
                }
            }
        }

        List var24 = gatherAllArtifacts(bpmnModel);
        Iterator var26 = var24.iterator();

        GraphicInfo var36;
        while(var26.hasNext()) {
            Artifact var28 = (Artifact)var26.next();
            GraphicInfo var30 = bpmnModel.getGraphicInfo(var28.getId());
            if(var30 != null) {
                if(var30.getX() + var30.getWidth() > maxX) {
                    maxX = var30.getX() + var30.getWidth();
                }

                if(var30.getX() < minX) {
                    minX = var30.getX();
                }

                if(var30.getY() + var30.getHeight() > maxY) {
                    maxY = var30.getY() + var30.getHeight();
                }

                if(var30.getY() < minY) {
                    minY = var30.getY();
                }
            }

            List var32 = bpmnModel.getFlowLocationGraphicInfo(var28.getId());
            if(var32 != null) {
                Iterator var34 = var32.iterator();

                while(var34.hasNext()) {
                    var36 = (GraphicInfo)var34.next();
                    if(var36.getX() > maxX) {
                        maxX = var36.getX();
                    }

                    if(var36.getX() < minX) {
                        minX = var36.getX();
                    }

                    if(var36.getY() > maxY) {
                        maxY = var36.getY();
                    }

                    if(var36.getY() < minY) {
                        minY = var36.getY();
                    }
                }
            }
        }

        int var27 = 0;
        Iterator var29 = bpmnModel.getProcesses().iterator();

        while(var29.hasNext()) {
            Process var31 = (Process)var29.next();
            Iterator var33 = var31.getLanes().iterator();

            while(var33.hasNext()) {
                Lane var35 = (Lane)var33.next();
                ++var27;
                var36 = bpmnModel.getGraphicInfo(var35.getId());
                if(var36.getX() + var36.getWidth() > maxX) {
                    maxX = var36.getX() + var36.getWidth();
                }

                if(var36.getX() < minX) {
                    minX = var36.getX();
                }

                if(var36.getY() + var36.getHeight() > maxY) {
                    maxY = var36.getY() + var36.getHeight();
                }

                if(var36.getY() < minY) {
                    minY = var36.getY();
                }
            }
        }

        if(var22.isEmpty() && bpmnModel.getPools().isEmpty() && var27 == 0) {
            minX = 0.0D;
            minY = 0.0D;
        }

        return new ProcessDiagramCanvasExt((int)maxX + 10, (int)maxY + 10, (int)minX, (int)minY, imageType, activityFontName, labelFontName, customClassLoader);
    }
    protected Color getTimeoutStatusColor(String status) {
        Color color = null;
        String colorString = (String)this.timeoutColors.get(status);
        if(StringUtil.isNotEmpty(colorString)) {
            String[] rgb = colorString.split(",");
            color = new Color(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()));
        }

        return color;
    }



    protected void drawActivity(ProcessDiagramCanvasExt processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode, Map<String, BpmNodeStatus> highLightedActivities, List<String> highLightedFlows, double scaleFactor) {
        BpmNodeStatus bpmNodeStatus = (BpmNodeStatus)highLightedActivities.get(flowNode.getId());
        ActivityDrawInstruction drawInstruction = (ActivityDrawInstruction)this.activityDrawInstructions.get(flowNode.getClass());
        GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
        boolean highLighted;
        String var31;
        if(drawInstruction != null) {
            drawInstruction.draw(processDiagramCanvas, bpmnModel, flowNode);
            if(flowNode instanceof UserTask) {
                UserTask multiInstanceSequential = (UserTask)flowNode;
                if(bpmNodeStatus != null) {
                    Color nestedFlowElement = this.getTimeoutStatusColor(bpmNodeStatus.getTimeoutStatus());
                    if(nestedFlowElement != null) {
                        this.drawUserTaskSpec(processDiagramCanvas, multiInstanceSequential.getName(), graphicInfo, nestedFlowElement);
                    }
                }
            }

            boolean var26 = false;
            boolean var28 = false;
            highLighted = false;
            if(flowNode instanceof Activity) {
                Activity defaultFlow = (Activity)flowNode;
                MultiInstanceLoopCharacteristics isDefault = defaultFlow.getLoopCharacteristics();
                if(isDefault != null) {
                    var26 = isDefault.isSequential();
                    var28 = !var26;
                }
            }

            if(!(flowNode instanceof SubProcess)) {
                if(flowNode instanceof CallActivity) {
                    highLighted = true;
                }
            } else {
                highLighted = graphicInfo.getExpanded() != null && !graphicInfo.getExpanded().booleanValue();
            }

            if(scaleFactor == 1.0D) {
                processDiagramCanvas.drawActivityMarkers((int)graphicInfo.getX(), (int)graphicInfo.getY(), (int)graphicInfo.getWidth(), (int)graphicInfo.getHeight(), var26, var28, highLighted);
            }

            if(highLightedActivities.containsKey(flowNode.getId())) {
                var31 = (String)this.processColors.get(bpmNodeStatus.getJumpType());
                Color var32 = null;
                if(StringUtil.isNotEmpty(var31)) {
                    String[] drawConditionalIndicator = var31.split(",");
                    var32 = new Color(Integer.parseInt(drawConditionalIndicator[0].trim()), Integer.parseInt(drawConditionalIndicator[1].trim()), Integer.parseInt(drawConditionalIndicator[2].trim()));
                }

                drawHighLight(processDiagramCanvas, bpmnModel.getGraphicInfo(flowNode.getId()), var32);
            }
        }

        Iterator var27 = flowNode.getOutgoingFlows().iterator();

        while(var27.hasNext()) {
            SequenceFlow var29 = (SequenceFlow)var27.next();
            highLighted = highLightedFlows.contains(var29.getId());
            var31 = null;
            if(flowNode instanceof Activity) {
                var31 = ((Activity)flowNode).getDefaultFlow();
            } else if(flowNode instanceof Gateway) {
                var31 = ((Gateway)flowNode).getDefaultFlow();
            }

            boolean var33 = false;
            if(var31 != null && var31.equalsIgnoreCase(var29.getId())) {
                var33 = true;
            }

            boolean var34 = var29.getConditionExpression() != null && !(flowNode instanceof Gateway);
            String sourceRef = var29.getSourceRef();
            String targetRef = var29.getTargetRef();
            FlowElement sourceElement = bpmnModel.getFlowElement(sourceRef);
            FlowElement targetElement = bpmnModel.getFlowElement(targetRef);
            List graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(var29.getId());
            if(graphicInfoList != null && graphicInfoList.size() > 0) {
                graphicInfoList = connectionPerfectionizer(processDiagramCanvas, bpmnModel, sourceElement, targetElement, graphicInfoList);
                int[] xPoints = new int[graphicInfoList.size()];
                int[] yPoints = new int[graphicInfoList.size()];

                for(int labelGraphicInfo = 1; labelGraphicInfo < graphicInfoList.size(); ++labelGraphicInfo) {
                    graphicInfo = (GraphicInfo)graphicInfoList.get(labelGraphicInfo);
                    GraphicInfo previousGraphicInfo = (GraphicInfo)graphicInfoList.get(labelGraphicInfo - 1);
                    if(labelGraphicInfo == 1) {
                        xPoints[0] = (int)previousGraphicInfo.getX();
                        yPoints[0] = (int)previousGraphicInfo.getY();
                    }

                    xPoints[labelGraphicInfo] = (int)graphicInfo.getX();
                    yPoints[labelGraphicInfo] = (int)graphicInfo.getY();
                }

                processDiagramCanvas.drawSequenceflow(xPoints, yPoints, var34, var33, highLighted, scaleFactor);
                GraphicInfo var35 = bpmnModel.getLabelGraphicInfo(var29.getId());
                if(var35 != null) {
                    processDiagramCanvas.drawLabel(var29.getName(), var35, false);
                }
            }
        }

        if(flowNode instanceof FlowElementsContainer) {
            var27 = ((FlowElementsContainer)flowNode).getFlowElements().iterator();

            while(var27.hasNext()) {
                FlowElement var30 = (FlowElement)var27.next();
                if(var30 instanceof FlowNode) {
                    this.drawActivity(processDiagramCanvas, bpmnModel, (FlowNode)var30, highLightedActivities, highLightedFlows, scaleFactor);
                }
            }
        }

    }
    protected void drawUserTaskSpec(ProcessDiagramCanvasExt canvas, String textName, GraphicInfo graphicInfo, Color backgroundColor) {
        Graphics2D g = canvas.getGraphics2d();
        Paint originalPaint = g.getPaint();
        int x = (int)graphicInfo.getX();
        int y = (int)graphicInfo.getY();
        int width = (int)graphicInfo.getWidth();
        int height = (int)graphicInfo.getHeight();
        byte arcR = 6;
        Double rect = new Double((double)x, (double)y, (double)width, (double)height, (double)arcR, (double)arcR);
        g.fill(rect);
        Paint orgPaint = g.getPaint();
        g.setColor(backgroundColor);
        g.fillRoundRect(x, y, width, height, 6, 6);
        g.setPaint(orgPaint);
        g.draw(rect);
        g.setPaint(originalPaint);
        canvas.drawTaskText(textName, graphicInfo);
        canvas.drawUserTaskImage(graphicInfo, 1.0D);
    }

    private static void drawHighLight(ProcessDiagramCanvasExt processDiagramCanvas, GraphicInfo graphicInfo, Color color) {
        processDiagramCanvas.drawHighLight((int)graphicInfo.getX(), (int)graphicInfo.getY(), (int)graphicInfo.getWidth(), (int)graphicInfo.getHeight(), color);
    }



}
