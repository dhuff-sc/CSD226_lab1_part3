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

    @GetMapping("/staffcontent")
    public ResponseEntity<String> getStaffContent() {
        return ResponseEntity.ok("getStaffContent() : Staff");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok(
                "getAdmin() : Admin" +
                // a button that edit public content
                "<form hx-get=\"/publicedit\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
                "    <button class=\"btn\"><span class=\"glyphicon glyphicon-edit\"></span> Edit Public Content</button>\n" +
                "</form>" +
                // a button that edit staff content
                "<form hx-get=\"/staffedit\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
                "    <button class=\"btn\"><span class=\"glyphicon glyphicon-edit\"></span> Edit Staff Content</button>\n" +
                "</form>");
    }

    @GetMapping("/student")
    public ResponseEntity<String> getStudent(){
        return ResponseEntity.ok("getStudent() : Student");
    }

    @GetMapping("/publicedit")
    public ResponseEntity<String> getPublicEdit(){
        return ResponseEntity.ok("getPublicEdit() : PublicEdit"
                + "<form hx-post=\"/publiccontent\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
                "    <div>\n" +
                "        <label>Content</label>\n" +
                "        <textarea name=\"content\" rows=\"5\" cols=\"30\">Hello World</textarea>\n" +
                "    </div>\n" +
                "    <button class=\"btn\">Submit</button>\n" +
                "    <button class=\"btn\">Cancel</button>\n" +
                "</form>");
    }

    @GetMapping("/staffedit")
    public ResponseEntity<String> getStaffEdit(){
        return ResponseEntity.ok("getStaffEdit() : StaffEdit"
                + "<form hx-post=\"/staffcontent\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
                "    <div>\n" +
                "        <label>Content</label>\n" +
                "        <textarea name=\"content\" rows=\"5\" cols=\"30\">Hello World</textarea>\n" +
                "    </div>\n" +
                "    <button class=\"btn\">Submit</button>\n" +
                "    <button class=\"btn\">Cancel</button>\n" +
                "</form>");
    }



}
