package com.wuyong.security.controller;

import com.wuyong.security.dto.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.ipc.netty.http.server.HttpServerRequest;
import reactor.ipc.netty.http.server.HttpServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * created by JianGuo
 * on 2018/1/18
 * description:
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {


    /**
     * 文件上传
     * @param file 变量名称和上传的name标签名称一致，或者加上注解里面定义name
     * @return
     * @throws IOException
     */
    @PostMapping("")
    public FileInfo upload(@RequestParam(name = "file") MultipartFile file) throws IOException {
        log.info("file name:{}", file.getName());
        log.info("file original name:{}", file.getOriginalFilename());
        log.info("file size:{}", file.getSize());

        String folder = "H:\\myproject\\idea";
        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    /**
     * 文件下载处理
     * @param id    文件id
     * @param request 请求
     * @param response  响应
     */
    @GetMapping("/{id}")
    public void download(@PathVariable(name = "id") String id,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        log.info("id:{}", id);
        String folder = "H:\\myproject\\idea";
        // try(...) JDK7新语法，卸载try里面的流不用在final中手动关闭，JDK自动关闭
        try (InputStream inputStream = new FileInputStream(new File(folder, "1516246600371.txt"));
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
