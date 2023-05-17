package com.Xeon.XeonWeb.mvc.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/xoen")
public class AdminHtmlServe {

    @GetMapping("/adminHtml")
    public void adminPage(HttpServletResponse response) throws IOException {
        // Load the HTML file from your desktop
        String filePath = "C:/Users/Gledis/Desktop/fwd/admin.html";

        // Load the HTML file as a resource
        Resource resource = new FileSystemResource(filePath);
        InputStream inputStream = resource.getInputStream();

        // Set the response headers
        response.setContentType("text/html");
        response.setHeader("Content-Disposition", "inline; filename=admin.html");

        // Copy the HTML content to the response
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outputStream.flush();
    }
}
