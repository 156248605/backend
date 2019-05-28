package com.elex.oa.service.eqptService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReferencePicService {
    // 获取图片并存入指定位置
    List saveReferencePic(HttpServletRequest request);
    // 删除图片
    String deleteReferencePic(HttpServletRequest request);
    // 获取图片
    List showReferencePic(HttpServletRequest request);
}
