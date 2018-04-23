package com.elex.oa.activiti.image;

import org.activiti.image.impl.DefaultProcessDiagramCanvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.RoundRectangle2D.Double;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import org.activiti.bpmn.model.GraphicInfo;
/**
 *@author hugo.zhao
 *@since 2018/4/9 10:55
*/
public class ProcessDiagramCanvasExt extends DefaultProcessDiagramCanvas {
    public ProcessDiagramCanvasExt(int width, int height, int minX, int minY, String imageType, String activityFontName, String labelFontName, ClassLoader customClassLoader) {
        super(width, height, minX, minY, imageType, activityFontName, labelFontName,null, customClassLoader);
    }

    public ProcessDiagramCanvasExt(int width, int height, int minX, int minY, String imageType) {
        super(width, height, minX, minY, imageType);
    }

    public void initialize(String imageType) {
        super.initialize(imageType);
        Font font = new Font(this.activityFontName, 0, 12);
        ANNOTATION_FONT = new Font(this.activityFontName, 0, 12);
        this.g.setFont(font);
        this.fontMetrics = this.g.getFontMetrics();
        LABEL_FONT = new Font(this.labelFontName, 2, 11);
    }

    public Graphics2D getGraphics2d() {
        return this.g;
    }

    public void drawHighLight(int x, int y, int width, int height, Color color) {
        Paint originalPaint = this.g.getPaint();
        Stroke originalStroke = this.g.getStroke();
        if(color == null) {
            color = new Color(233, 195, 65);
        }

        this.g.setPaint(color);
        this.g.setStroke(THICK_TASK_BORDER_STROKE);
        Double rect = new Double((double)x, (double)y, (double)width, (double)height, 6.0D, 6.0D);
        this.g.draw(rect);
        this.g.setPaint(originalPaint);
        this.g.setStroke(originalStroke);
    }

    protected void drawTask(String name, GraphicInfo graphicInfo, boolean thickBorder) {
        Paint originalPaint = this.g.getPaint();
        int x = (int)graphicInfo.getX();
        int y = (int)graphicInfo.getY();
        int width = (int)graphicInfo.getWidth();
        int height = (int)graphicInfo.getHeight();
        this.g.setPaint(TASK_BOX_COLOR);
        byte arcR = 6;
        if(thickBorder) {
            arcR = 3;
        }

        Double rect = new Double((double)x, (double)y, (double)width, (double)height, (double)arcR, (double)arcR);
        this.g.fill(rect);
        this.g.setPaint(TASK_BORDER_COLOR);
        if(thickBorder) {
            Stroke boxWidth = this.g.getStroke();
            this.g.setStroke(THICK_TASK_BORDER_STROKE);
            this.g.draw(rect);
            this.g.setStroke(boxWidth);
        } else {
            this.g.draw(rect);
        }

        this.g.setPaint(originalPaint);
        if(name != null && name.length() > 0) {
            int boxWidth1 = width - 6;
            int boxHeight = height - ICON_PADDING - ICON_PADDING - 12 - 2 - 2;
            int boxX = x + width / 2 - boxWidth1 / 2;
            int boxY = y + height / 2 - boxHeight / 2 + ICON_PADDING + ICON_PADDING - 2 - 2;
            this.drawMultilineCentredText(name, boxX, boxY, boxWidth1, boxHeight);
        }

    }

    public void drawUserTaskImage(GraphicInfo graphicInfo, double scaleFactor) {
        this.g.drawImage(USERTASK_IMAGE, (int)(graphicInfo.getX() + (double)ICON_PADDING / scaleFactor), (int)(graphicInfo.getY() + (double)ICON_PADDING / scaleFactor), (int)((double)USERTASK_IMAGE.getWidth() / scaleFactor), (int)((double)USERTASK_IMAGE.getHeight() / scaleFactor), (ImageObserver)null);
    }

    public void drawTaskText(String text, GraphicInfo graphicInfo) {
        int x = (int)graphicInfo.getX();
        int y = (int)graphicInfo.getY();
        int width = (int)graphicInfo.getWidth();
        int height = (int)graphicInfo.getHeight();
        if(text != null && text.length() > 0) {
            int boxWidth = width - 6;
            int boxHeight = height - ICON_PADDING - ICON_PADDING - 12 - 2 - 2;
            int boxX = x + width / 2 - boxWidth / 2;
            int boxY = y + height / 2 - boxHeight / 2 + ICON_PADDING + ICON_PADDING - 2 - 2;
            this.drawMultilineText2(text, boxX, boxY, boxWidth, boxHeight, true);
        }

    }

    protected void drawMultilineText2(String text, int x, int y, int boxWidth, int boxHeight, boolean centered) {
        AttributedString attributedString = new AttributedString(text);
        attributedString.addAttribute(TextAttribute.FONT, this.g.getFont());
        attributedString.addAttribute(TextAttribute.FOREGROUND, Color.WHITE);
        AttributedCharacterIterator characterIterator = attributedString.getIterator();
        int currentHeight = 0;
        ArrayList layouts = new ArrayList();
        String lastLine = null;
        LineBreakMeasurer measurer = new LineBreakMeasurer(characterIterator, this.g.getFontRenderContext());

        int currentY;
        int currentX;
        for(TextLayout layout = null; measurer.getPosition() < characterIterator.getEndIndex() && currentHeight <= boxHeight; currentHeight += currentX) {
            currentY = measurer.getPosition();
            layout = measurer.nextLayout((float)boxWidth);
            currentX = Float.valueOf(layout.getDescent() + layout.getAscent() + layout.getLeading()).intValue();
            if(currentHeight + currentX > boxHeight) {
                if(!layouts.isEmpty()) {
                    layouts.remove(layouts.size() - 1);
                    if(lastLine.length() >= 4) {
                        lastLine = lastLine.substring(0, lastLine.length() - 4) + "...";
                    }

                    layouts.add(new TextLayout(lastLine, this.g.getFont(), this.g.getFontRenderContext()));
                }
                break;
            }

            layouts.add(layout);
            lastLine = text.substring(currentY, measurer.getPosition());
        }

        currentY = y + (centered?(boxHeight - currentHeight) / 2:0);
        boolean currentX1 = false;

        TextLayout textLayout;
        for(Iterator var16 = layouts.iterator(); var16.hasNext(); currentY = (int)((float)currentY + textLayout.getDescent() + textLayout.getLeading())) {
            textLayout = (TextLayout)var16.next();
            currentY = (int)((float)currentY + textLayout.getAscent());
            currentX = x + (centered?(boxWidth - java.lang.Double.valueOf(textLayout.getBounds().getWidth()).intValue()) / 2:0);
            textLayout.draw(this.g, (float)currentX, (float)currentY);
        }

    }


}
