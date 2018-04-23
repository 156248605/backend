package com.elex.oa.util;
import com.elex.oa.activiti.image.ProcessDiagramGeneratorExt;
import com.elex.oa.activiti.image.ProcessImageGenerator;
import com.elex.oa.entity.activiti.BpmNodeStatus;
import com.elex.oa.json.JsonResult;
import com.elex.oa.json.JsonResultUtil;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.image.ProcessDiagramGenerator;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *@author hugo.zhao
 *@since 2018/4/11 11:21
*/
public class JsaasUtil {
    static Pattern regex = Pattern.compile("&lt;#if(.*?)&gt;", 98);
    private static final String activityFontName = "黑体";
    private static final String labelFontName = "黑体";
    private static Integer userAmount = Integer.valueOf(Integer.parseInt("0"));
    private static Integer formAmount = Integer.valueOf(Integer.parseInt("0"));
    private static Integer solAmount = Integer.valueOf(Integer.parseInt("0"));
    private static Integer instAmount = Integer.valueOf(Integer.parseInt("0"));
    private static String allowMacAddress = "";
    private static String tipMessage = "世界很大,我想去看看";
    private static String postion = "right";
    private static Integer fontSize = Integer.valueOf(Integer.parseInt("24"));
    private static Set<String> macAddressSet = new HashSet();
    public JsaasUtil() {}

    public static void generateArea(String xml, BpmnModel bpmnModel) throws DocumentException{
        org.dom4j.Document document = DocumentHelper.parseText(xml);
        org.dom4j.Element root = document.getRootElement();
        List childElements = root.element("process").elements("sequenceFlow");
        Iterator var5 = childElements.iterator();
        label40:
        while(var5.hasNext()) {
            org.dom4j.Element element = (org.dom4j.Element)var5.next();
            String sequenceId = element.attributeValue("id");
            List flow = root.element("BPMNDiagram").element("BPMNPlane").elements("BPMNEdge");
            Iterator var9 = flow.iterator();

            while(true) {
                org.dom4j.Element flowEle;
                do {
                    if(!var9.hasNext()) {
                        continue label40;
                    }

                    flowEle = (org.dom4j.Element)var9.next();
                } while(!sequenceId.equals(flowEle.attributeValue("bpmnElement")));

                double x = 0.0D;
                double y = 0.0D;
                double x2 = 0.0D;
                double y2 = 0.0D;
                int i = 0;
                List coord = flowEle.elements("waypoint");
                Iterator graphicInfo = coord.iterator();

                while(graphicInfo.hasNext()) {
                    org.dom4j.Element crd = (org.dom4j.Element)graphicInfo.next();
                    ++i;
                    if(i == 1) {
                        x = Double.parseDouble(crd.attributeValue("x"));
                        y = Double.parseDouble(crd.attributeValue("y"));
                    }

                    if(i == 2) {
                        x2 = Double.parseDouble(crd.attributeValue("x"));
                        y2 = Double.parseDouble(crd.attributeValue("y"));
                    }
                }

                GraphicInfo var23 = new GraphicInfo();
                var23.setX((x + (x + (x + x2) / 2.0D) / 2.0D) / 2.0D);
                var23.setY((y + (y + (y + y2) / 2.0D) / 2.0D) / 2.0D);
                bpmnModel.addLabelGraphicInfo(sequenceId, var23);
            }
        }
    }
    private static void pressText(String pressText, InputStream is, String fontName, int fontSize, String position, String type, OutputStream output) {
        try {
            BufferedImage e = ImageIO.read(is);
            int wideth = e.getWidth((ImageObserver)null);
            int height = e.getHeight((ImageObserver)null);
            BufferedImage image = new BufferedImage(wideth, height, 1);
            Graphics2D g = image.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(e, 0, 0, wideth, height, Color.WHITE, (ImageObserver)null);
            g.setColor(Color.orange);
            g.setFont(new Font(fontName, 1, fontSize));
            int[] point = getPostion(pressText, wideth, height, fontSize, position);
            g.drawString(pressText, point[0], point[1]);
            g.dispose();
            ImageIO.write(image, type, output);
        } catch (Exception var13) {
            var13.printStackTrace();
        }
    }
    private static int[] getPostion(String pressText, int width, int height, int fontSize, String position) {
        if("top".equals(position)) {
            return new int[]{10, fontSize + 5};
        } else {
            int x1;
            if("right".equals(position)) {
                x1 = width - pressText.length() * fontSize - 10;
                return new int[]{x1, fontSize};
            } else {
                int y;
                if("bottom".equals(position)) {
                    x1 = width - pressText.length() * fontSize - 10;
                    y = height - 2;
                    return new int[]{x1, y};
                } else if("left".equals(position)) {
                    byte x = 10;
                    y = height - 2;
                    return new int[]{x, y};
                } else {
                    return new int[]{0, 0};
                }
            }
        }
    }

    public static void handleImage(BpmnModel bpmnModel, Map<String, BpmNodeStatus> maps, List<String> flowIdList, OutputStream output) throws UnsupportedEncodingException, IOException {
        ProcessImageGenerator processImageGenerator = (ProcessImageGenerator)AppBeanUtil.getBean(ProcessImageGenerator.class);
        InputStream is = processImageGenerator.generateDiagram(bpmnModel, "png", maps, flowIdList, "黑体", "黑体", (ClassLoader)null, 1.0D);
        JsonResult result = getResult();
        if(result.isSuccess()) {
            FileUtil.inputToOut(is, output);
        } else {
            pressText(tipMessage, is, "黑体", fontSize.intValue(), postion, "png", output);
        }

    }

    private static JsonResult getResult() {
        int flag;
        if(userAmount.intValue() > 0) {
            flag = getUserAmount();
            if(flag > userAmount.intValue()) {
                return new JsonResult(false, "用户数量超过:" + userAmount);
            }
        }

        if(formAmount.intValue() > 0) {
            flag = getFormAmount();
            if(flag > formAmount.intValue()) {
                return new JsonResult(false, "表单数据超过:" + formAmount);
            }
        }

        if(solAmount.intValue() > 0) {
            flag = getSolAmount();
            if(flag > solAmount.intValue()) {
                return new JsonResult(false, "解决方案超过:" + solAmount);
            }
        }

        if(instAmount.intValue() > 0) {
            flag = getInstAmount();
            if(flag > instAmount.intValue()) {
                return new JsonResult(false, "流程实例数量超过:" + instAmount);
            }
        }

        if(StringUtil.isNotEmpty(allowMacAddress)) {
            allowMacAddress = allowMacAddress.toLowerCase();
            if(macAddressSet.size() == 0) {
                try {
                    macAddressSet = StringUtil.getMacAddress();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }

            boolean flag1 = false;
            Iterator var1 = macAddressSet.iterator();

            while(var1.hasNext()) {
                String mac = (String)var1.next();
                if(allowMacAddress.indexOf(mac) != -1) {
                    flag1 = true;
                }
            }

            if(!flag1) {
                return new JsonResult(false, "MAC地址非法");
            }
        }

        return JsonResultUtil.success();
    }


    public static void handleImage(BpmnModel bpmnModel, OutputStream output) throws UnsupportedEncodingException, IOException {
       // ProcessImageGenerator processImageGenerator = (ProcessImageGenerator)AppBeanUtil.getBean(ProcessImageGenerator.class);
        //InputStream is = processImageGenerator.generateDiagram(bpmnModel, "png", "黑体", "黑体",null, (ClassLoader)null, 1.0D);
        ProcessDiagramGeneratorExt processDiagramGeneratorExt = new ProcessDiagramGeneratorExt();
        InputStream is = processDiagramGeneratorExt.generateDiagram(bpmnModel, "png", "黑体", "黑体",null, (ClassLoader)null, 1.0D);
        JsonResult result = getResult();
        if(result.isSuccess()) {
            FileUtil.inputToOut(is, output);
        } else {
            pressText(tipMessage, is, "黑体", fontSize.intValue(), postion, "png", output);
        }
    }

    private static int getFormAmount() {
        JdbcTemplate template = (JdbcTemplate)AppBeanUtil.getBean(JdbcTemplate.class);
        String sql = "select count(*) from BPM_FORM_VIEW";
        Integer amount = Integer.valueOf(template.queryForObject(sql,Integer.class));
        return amount.intValue();
    }

    private static int getUserAmount() {
        JdbcTemplate template = (JdbcTemplate)AppBeanUtil.getBean(JdbcTemplate.class);
        String sql = "select count(*) from OS_USER";
        Integer amount = Integer.valueOf(template.queryForObject(sql,Integer.class));
        return amount.intValue();
    }
    private static int getSolAmount() {
        JdbcTemplate template = (JdbcTemplate)AppBeanUtil.getBean(JdbcTemplate.class);
        String sql = "select count(*) from BPM_SOLUTION";
        Integer amount = Integer.valueOf(template.queryForObject(sql,Integer.class));
        return amount.intValue();
    }

    private static int getInstAmount() {
        JdbcTemplate template = (JdbcTemplate)AppBeanUtil.getBean(JdbcTemplate.class);
        String sql = "select count(*) from BPM_INST";
        Integer amount = Integer.valueOf(template.queryForObject(sql,Integer.class));
        return amount.intValue();
    }



}
