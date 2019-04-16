package com.adminapp.adminapp.controllers;

import com.adminapp.adminapp.services.PostsService;
import com.adminapp.adminapp.entity.Posts;
import com.adminapp.adminapp.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FileUploadController {

    private final StorageService storageService;


    @Autowired
    PostsService postsService;

    @Value("${app.post.storageLocation}")
    private String storageLocation;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(value = "admin/post")
    public ResponseEntity<Posts> create(@RequestParam("file") MultipartFile file, @RequestParam("content") String content ,@RequestParam("description") String description, @RequestParam("title") String title) {

        String fileName = this.createFileName(file);
        Posts post = new Posts();
        post.setFilename(fileName);
        post.setContent(content);
        post.setTitle(title);
        post.setDescription(description);
        storageService.store(file);
        postsService.insertPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/admin/post/{id}")
    public ResponseEntity<Posts> deleteCustomer(@PathVariable("id") long id) {
        Posts post = postsService.findById(id);
        if (post != null) {
            postsService.deleteById(id);
            storageService.deleteByName(post.getFilename());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "admin/post/{id}")
    public ResponseEntity<Posts> updatePost(@PathVariable("id") long id, @RequestParam(required = false, name = "file") MultipartFile file, @RequestParam(required = false, name = "title") String title, @RequestParam(required = false, name = "content") String content, @RequestParam(required = false, name = "description") String description) {
        Posts post = postsService.findById(id);

        if (title != null && post.getTitle() != title) {
            post.setTitle(title);
        if (content != null && post.getContent() != content) {
            post.setContent(content);
        }
        if (description != null && post.getDescription() != description) {
            post.setDescription(description);
        }

        if (file != null) {
            storageService.deleteByName(post.getFilename());
            String fileName = this.createFileName(file);
            post.setFilename(fileName);
            storageService.store(file);
        }
            postsService.insertPost(post);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/upload-dir/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, HttpServletRequest request) {

        Resource file = storageService.loadAsResource(filename);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("cannot determine type");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }


    private String createFileName(MultipartFile file) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH-mm-ss");
        String strDate = dateFormat.format(date);

        String fileName = strDate.concat(file.getOriginalFilename());

        return fileName;
    }

}
