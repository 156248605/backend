package com.elex.oa.service.eqptImpl;

import com.elex.oa.dao.eqptDao.LinkmanMapper;
import com.elex.oa.dao.eqptDao.MaterialMapper;
import com.elex.oa.dao.eqptDao.PartnerMapper;
import com.elex.oa.dao.eqptDao.RepositoryMapper;
import com.elex.oa.entity.eqpt.Linkman;
import com.elex.oa.entity.eqpt.Material;
import com.elex.oa.entity.eqpt.Partner;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.ImportService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {

    @Resource
    private PartnerMapper partnerMapper;

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private LinkmanMapper linkmanMapper;

    @Override
    public String readExcelFileMaterial(MultipartFile file) {
        String result = "";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Material> materialList = readExcel.getExcelInfoMaterial(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        for(Material material:materialList){
            int ret = materialMapper.importMaterial(material);
            if(ret == 0){
                result = "插入数据库失败";
            }
        }
        if(materialList != null && !materialList.isEmpty()){
            result = "上传成功";
        }else{
            result = "上传失败";
        }
        return result;
    }

    @Override
    public String readExcelFileRepository(MultipartFile file) {
        String result = "";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Repository> repositorieList = readExcel.getExcelInfoRepository(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        for(Repository repository:repositorieList){
            int ret = repositoryMapper.importRepository(repository);
            if(ret == 0){
                result = "插入数据库失败";
            }
        }
        if(repositorieList != null && !repositorieList.isEmpty()){
            result = "上传成功";
        }else{
            result = "上传失败";
        }
        return result;
    }

    @Override
    public String readExcelFilePartner(MultipartFile file) {
        String result = "";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Partner> partnerList = readExcel.getExcelInfoPartner(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        for(Partner partner:partnerList){
            int ret = partnerMapper.importPartner(partner);
            if(ret == 0){
                result = "插入数据库失败";
            }
        }
        if(partnerList != null && !partnerList.isEmpty()){
            result = "上传成功";
        }else{
            result = "上传失败";
        }
        return result;
    }

    @Override
    public String readExcelFileLinkman(MultipartFile file) {
        String result = "";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<Linkman> linkmanList = readExcel.getExcelInfoLinkman(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        for(Linkman linkman:linkmanList){
            int ret = linkmanMapper.importLinkman(linkman);
            if(ret == 0){
                result = "插入数据库失败";
            }
        }
        if(linkmanList != null && !linkmanList.isEmpty()){
            result = "上传成功";
        }else{
            result = "上传失败";
        }
        return result;
    }
}
