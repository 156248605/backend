package com.elex.oa.service.eqptImpl;


import com.elex.oa.dao.eqptDao.RepositoryMapper;
import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Repository;
import com.elex.oa.service.eqptService.RepositoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RepositoryImpl implements RepositoryService {
    @Resource
    private RepositoryMapper repositoryMapper;

    @Override
    public PageInfo<Repository> showRepository(Page page){
        PageHelper.startPage(page.getCurrentPage(),page.getRows());
        List<Repository> listR = repositoryMapper.RepositoryList();
        return new PageInfo<>(listR);
    }

    @Override
    public PageInfo<Repository> searchRepository(Page page,HttpServletRequest request){
        String reptId = request.getParameter("reptId");
        String reptCategory = request.getParameter("reptCategory");
        String position = request.getParameter("position");
        String sn = request.getParameter("sn");
        String bn = request.getParameter("bn");
        if (reptId.equals("") && reptCategory.equals("") && position.equals("") && sn.equals("") && bn.equals("")){
            List<Repository> listR = repositoryMapper.RepositoryList();
            return new PageInfo<>(listR);
        } else {
            Repository repository = new Repository();
            repository.setReptId(reptId);
            repository.setReptCategory(reptCategory);
            repository.setPosition(position);
            repository.setBn(bn);
            repository.setSn(sn);
            List<Repository> listR = repositoryMapper.searchRepository(repository);
            return new PageInfo<>(listR);
        }
    }

    @Override
    public String insertRepository(Repository repository,HttpServletRequest request) {
        repository.setReptCategory(request.getParameter("reptCategory"));
        repository.setReptId(request.getParameter("reptId"));
        repository.setPosition(request.getParameter("position"));
        repository.setNum(request.getParameter("num"));
        repository.setMaterialId(request.getParameter("materialId"));
        repository.setSn(request.getParameter("sn"));
        repository.setBn(request.getParameter("bn"));
        String A;
        if (repositoryMapper.theCategory(repository).isEmpty()){
            A = request.getParameter("reptCategory");
        }else {
            A = repositoryMapper.theCategory(repository).get(0).toString();
        }
        if (repositoryMapper.Position(repository) == null) {
            if (request.getParameter("reptCategory").equals(A)) {
                repositoryMapper.insertRepository(repository);
                return "1";
            }else{
                return "2";
            }
        }else {
            return "0";
        }
    }

    @Override
    public Repository position(HttpServletRequest request){
        Repository repository = new Repository();
        repository.setPosition(request.getParameter("position"));
        Repository repository1 = repositoryMapper.findPosition(repository);
        return repository1;
    }

    @Override
    public void changeRepository(Repository repository,HttpServletRequest request) {
            repository.setReptCategory(request.getParameter("reptCategory"));
            repository.setReptId(request.getParameter("reptId"));
            repository.setPosition(request.getParameter("position"));
            repository.setNum(request.getParameter("num"));
            repository.setMaterialId(request.getParameter("materialId"));
            repository.setSn(request.getParameter("sn"));
            repository.setBn(request.getParameter("bn"));
            repositoryMapper.changeRepository(repository);
    }


    @Override
    public void deleteRepository(Repository repository,HttpServletRequest request) {
            repository.setPosition(request.getParameter("position"));
            repositoryMapper.deleteRepository(repository);
    }
}


