package csd226.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookstoreController {

    @GetMapping("/publiccontent")
    public ResponseEntity<String> getPublicContent() {
        return ResponseEntity.ok("getPublicContent() : Home");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin() { return ResponseEntity.ok("getAdmin() : Admin");
    }

    @GetMapping("/student")
    public ResponseEntity<String> getStudent(){ // map a URL to a method
        return ResponseEntity.ok("getStudent() : Student");
    }

    @GetMapping("/publicedit")
    public ResponseEntity<String> getPublicEdit(){ // map a URL to a method
        return ResponseEntity.ok("getPublicEdit() : PublicEdit");
    }

    @GetMapping("/staffedit")
    public ResponseEntity<String> getStaffEdit(){ // map a URL to a method
        return ResponseEntity.ok("getStaffEdit() : StaffEdit");
    }



}
