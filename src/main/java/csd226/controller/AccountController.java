package csd226.controller;

import csd226.data.Account;
import csd226.repositories.AccountRepository;
import csd226.security.JwtTokenUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import csd226.data.AuthRequest;
import csd226.data.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    @PostMapping("/test_form")
    public String test_form(@ModelAttribute Account account, Model model) {
        model.addAttribute("email", account);
        return "result";
    }

    @PostMapping(path = "/auth/login")
    public ResponseEntity<?> login(@ModelAttribute Account acc, Model model) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            acc.getEmail(), acc.getPassword())
            );

            Account account = (Account) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(account);

            AuthResponse response = new AuthResponse(account.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> createAccount(@RequestBody Account signUpFormData) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(signUpFormData.getPassword());

        signUpFormData.setPassword(password);

        Account savedAccount = accountRepository.save(signUpFormData);

        return ResponseEntity.ok("createAccount(): " + signUpFormData.getEmail());
    }

    @GetMapping("/login")
    public ResponseEntity<String> getLogin() {
        String s =
                "<form hx-post=\"/signup\" hx-target=\"this\" hx-swap=\"outerHTML\">" +
                        "    <div>" +
                        "        <label>Username</label>" +
                        "        <input type=\"text\" name=\"username\" value=\"user\">" +
                        "    </div>" +
                        "    <div class=\"form-group\">" +
                        "        <label>Password</label>" +
                        "        <input type=\"password\" name=\"password\" value=\"password\">" +
                        "    </div>" +
                        "    <button class=\"btn\">Submit</button>" +
                        "    <button class=\"btn\" hx-get=\"/signup\">Cancel</button>" +
                        "</form>";
        return ResponseEntity.ok(s);
    }

    @GetMapping("/signin")
    public String getSignin() {
        return "<form hx-post=\"/auth/login\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
                "    <div>\n" +
                "        <label>First Name</label>\n" +
                "        <input type=\"text\" name=\"firstname\" value=\"First Name\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label>Last Name</label>\n" +
                "        <input type=\"text\" name=\"lastname\" value=\"Last Name\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label>Email Address</label>\n" +
                "        <input type=\"email\" name=\"email\" value=\"email@gmail.com\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label>Password</label>\n" +
                "        <input type=\"password\" name=\"password\" value=\"xxxxxxxxxx\">\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label>Confirm Password</label>\n" +
                "        <input type=\"password\" name=\"confirmPassword\" value=\"xxxxxxxxxx\">\n" +
                "    </div>\n" +
                "    <button class=\"btn\">Submit</button>\n" +
                "    <button class=\"btn\" hx-get=\"/signin\">Cancel</button>\n" +
                "</form>";
    }
}

//    @GetMapping(path = "/signinForm")
//    public String signinForm() {
//
//        return "<form hx-post=\"/signin\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
//                "  <div class=\"form-group\">\n" +
//                "    <label>Email Address</label>\n" +
//                "    <input type=\"email\" name=\"email\" value=\"joe@blow.com\">\n" +
//                "  </div>\n" +
//                "  <div class=\"form-group\">\n" +
//                "    <label>Password</label>\n" +
//                "    <input type=\"password\" name=\"password\" value=\"xxxxxxx\">\n" +
//                "  </div>\n" +
//                "  <button class=\"btn\">Submit</button>\n" +
//                "</form>";
//
//    }


//    @GetMapping(path = "/signupForm")
//        public String createAccount() {
//            return "<form hx-post=\"/signup\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
//                    "    <div>\n" +
//                    "        <label>First Name</label>\n" +
//                    "        <input type=\"text\" name=\"firstname\" value=\"Joe\">\n" +
//                    "    </div>\n" +
//                    "    <div class=\"form-group\">\n" +
//                    "        <label>Last Name</label>\n" +
//                    "        <input type=\"text\" name=\"lastname\" value=\"Blow\">\n" +
//                    "    </div>\n" +
//                    "    <div class=\"form-group\">\n" +
//                    "        <label>Email Address</label>\n" +
//                    "        <input type=\"email\" name=\"email\" value=\"joe@blow.com\">\n" +
//                    "    </div>\n" +
//                    "    <div class=\"form-group\">\n" +
//                    // add helper text advise the user their password must be at least 8 characters and
//                    // include one uppercase letter, one lowercase letter, a number and a special character
//                    "        <label>Password</label>\n" +
//                    "        <input type=\"password\" name=\"password\" value=\"xxxxx\">\n" +
//                    "    </div>\n" +
//                    "        <small id=\"passHelp\" class=\"form-text text-muted\">Min. 8 characters, 1 uppercase, 1 lowercase, a number and special character.</small>\n" +
//                    "    <div class=\"form-group\">\n" +
//                    "        <label>Confirm Password</label>\n" +
//                    "        <input type=\"password\" name=\"confirmPassword\" value=\"xxxxx\">\n" +
//                    "    </div>\n" +
//                    "    <button class=\"btn\">Submit</button>\n" +
//                    "    <button class=\"btn\" hx-get=\"/signup\">Cancel</button>\n" +
//                    "</form>";
//        }

//    @GetMapping("/signup")
//    public ResponseEntity<String> createAccount(@RequestBody Account signUpFormData) {
//        String s="<form hx-post=\"/signup\" hx-target=\"this\" hx-swap=\"outerHTML\">\n" +
//                "    <div>\n" +
//                "        <label>First Name</label>\n" +
//                "        <input type=\"text\" name=\"firstname\" value=\"Joe\">\n" +
//                "    </div>\n" +
//                "    <div class=\"form-group\">\n" +
//                "        <label>Last Name</label>\n" +
//                "        <input type=\"text\" name=\"lastname\" value=\"Blow\">\n" +
//                "    </div>\n" +
//                "    <div class=\"form-group\">\n" +
//                "        <label>Email Address</label>\n" +
//                "        <input type=\"email\" name=\"email\" value=\"joe@blow.com\">\n" +
//                "    </div>\n" +
//                "    <div class=\"form-group\">\n" +
//                "        <label>Password</label>\n" +
//                "        <input type=\"password\" name=\"password\" value=\"xxxxx\">\n" +
//                "    </div>\n" +
//                "    <div class=\"form-group\">\n" +
//                "        <label>Confirm Password</label>\n" +
//                "        <input type=\"password\" name=\"confirmPassword\" value=\"xxxxx\">\n" +
//                "    </div>\n" +
//                "    <button class=\"btn\">Submit</button>\n" +
//                "    <button class=\"btn\" hx-get=\"/signin\">Cancel</button>\n" +
//                "</form>";
//        return ResponseEntity.ok(s);
//    }
