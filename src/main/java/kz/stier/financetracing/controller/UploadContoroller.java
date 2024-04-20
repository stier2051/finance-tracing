package kz.stier.financetracing.controller;

import kz.stier.financetracing.service.ParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class UploadContoroller {

    @Autowired
    private ParsingService parsingService;

    @PostMapping(value = "/api/upload", consumes="multipart/form-data")
    public String uploadPdf(@RequestPart("File") MultipartFile file) throws ParseException, IOException {
        parsingService.parsingPdf(file);
        return "SUCCESS";
    }
}
