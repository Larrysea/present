package com.present.student.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyFileUploadController {

    // Upload One File.
    @RequestMapping(value = "/uploadOneFile")
    public String uploadOneFileHandler() {
        // Forward to "/WEB-INF/pages/uploadOneFile.jsp".
        return "uploadOneFile";
    }

    // Upload Multi File.
    @RequestMapping(value = "/uploadMultiFile")
    public String uploadMultiFileHandler() {
        // Forward to "/WEB-INF/pages/uploadMultiFile.jsp".
        return "uploadMultiFile";
    }

    // uploadOneFile.jsp, uploadMultiFile.jsp submit to.
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String uploadFileHandler(HttpServletRequest request, Model model,
                                    @RequestParam("file") MultipartFile[] files) {

        // Root Directory.
        String uploadRootPath = request.getServletContext().getRealPath(
                "upload");
        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);
        //
        // Create directory if it not exists.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        //
        List<File> uploadedFiles = new ArrayList<File>();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];

            // Client File Name
            String name = file.getOriginalFilename();
            System.out.println("Client File Name = " + name);

            if (name != null && name.length() > 0) {
                try {
                    byte[] bytes = file.getBytes();

                    // Create the file on server
                    File serverFile = new File(uploadRootDir.getAbsolutePath()
                            + File.separator + name);

                    // Stream to write data to file in server.
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();
                    //
                    uploadedFiles.add(serverFile);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        model.addAttribute("uploadedFiles", uploadedFiles);
        return "uploadResult";
    }

}