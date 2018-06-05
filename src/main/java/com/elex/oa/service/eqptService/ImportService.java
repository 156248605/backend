package com.elex.oa.service.eqptService;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
    String readExcelFileMaterial(MultipartFile file);

    String readExcelFileRepository(MultipartFile file);

    String readExcelFilePartner(MultipartFile file);

    String readExcelFileLinkman(MultipartFile file);
}
