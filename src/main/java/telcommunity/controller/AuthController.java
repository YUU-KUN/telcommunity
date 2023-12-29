package telcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import telcommunity.model.User;
import telcommunity.service.CustomUserDetailsService;

// @Controller
public class AuthController {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public String login(@RequestParam(name = "role", defaultValue = "defaultType") String role, Model model) {
        return "redirect:/login?role=mahasiswa";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("auth") User user) {

        // Add the user to the database
        System.out.println("-------------------- USER DATA -------------------");
        System.out.println(user.getUsername() + " " + user.getPassword());
        customUserDetailsService.addUser(user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("auth") User user, HttpServletRequest request) {
        try {
            // Authenticate the user using the provided credentials
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
    
            // You might want to add additional checks for invalid credentials
            if (userDetails == null || !userDetails.getPassword().equals(user.getPassword())) {
                // Invalid credentials, redirect to login with an error message
                System.out.println("Invalid credentials");
                request.setAttribute(
                        "error", "Invalid email or password. Please try again.");
                return "redirect:/login?error";
            }
            System.out.println("Valid credentials");
            System.out.println(userDetails.getUsername());
    
            // Set authentication in the security context
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    
            // Redirect to the dashboard after successful login
            return "redirect:/";
        } catch (Exception e) {
            // Handle any exceptions that occur during authentication
            e.printStackTrace();
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }
}