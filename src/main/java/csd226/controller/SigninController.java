package csd226.controller;

import csd226.data.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SigninController {
    @PostMapping("/signin")
    public String signin(@ModelAttribute Account values) {
        // here we just return email and password but you would want to start the authentication process here.
        // we'll do this later
        return "Hello " + values.getFirstname() + ":" + " you are signed in!";
    }

    @GetMapping(path = "/signinForm")
    public String signinForm() {

        return "<form hx-post=\"/signin\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
                "  <div class=\"form-group\">\n" +
                "    <label>Email Address</label>\n" +
                "    <input type=\"email\" name=\"email\" value=\"joe@blow.com\">\n" +
                "  </div>\n" +
                "  <div class=\"form-group\">\n" +
                "    <label>Password</label>\n" +
                "    <input type=\"password\" name=\"password\" value=\"xxxxxxx\">\n" +
                "  </div>\n" +
                "  <button class=\"btn\">Submit</button>\n" +
                "</form>";

    }
}
