package csd226.controller;


import csd226.data.Content;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookstoreController {

    @GetMapping("/publiccontent")
    public ResponseEntity<String> getPublicContent() {
        return ResponseEntity.ok("getPublicContent() :");
    }

    @GetMapping("/publiccontent2")
    public Content getPublicContent2() {
        return new Content("some content");
    }




}
