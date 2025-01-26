package com.notsauce.parkd.controllers;


import com.notsauce.parkd.models.Blog;
import com.notsauce.parkd.models.Park;
import com.notsauce.parkd.models.User;
import com.notsauce.parkd.models.data.BlogRepository;
import com.notsauce.parkd.models.data.ParkRepository;
import com.notsauce.parkd.models.data.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("blog/view/{parkCode}")
    public String parkBlogPage(Model model, @PathVariable String parkCode) {
        Optional<Park> optionalPark = parkRepository.findById(parkCode);
        if (optionalPark.isPresent()) {
            Park park = optionalPark.get();

            model.addAttribute("park", park);

        }
        return "blog/view";
    }

    @GetMapping("blog/create/{parkCode}")
    public String displaySubmitBlogPostForm(Model model, @PathVariable String parkCode) {

        Optional<Park> optionalPark = parkRepository.findById(parkCode);
        if (optionalPark.isPresent()) {
            Park park = optionalPark.get();

            model.addAttribute("park", park);
            model.addAttribute("blog", new Blog());
        }
        return "blog/create";
    }

    @PostMapping("blog/create/{parkCode}")
    public String submitBlogPost(Model model, @PathVariable String parkCode, Blog blog, Errors errors, HttpSession session) {

        Park aPark = new Park();
        if (errors.hasErrors()) {
            return "redirect:/blog/create/" + parkCode;
        }

        Optional<Park> optionalPark = parkRepository.findById(parkCode);
        if (optionalPark.isPresent()) {
            aPark = optionalPark.get();

            Optional<User> optionalUser = userRepository.findById((Integer) session.getAttribute("user"));
            if (optionalUser.isPresent()) {
                User aUser = optionalUser.get();
                blog.setAuthor(aUser);
            }

            blog.setSubject(aPark);
            blogRepository.save(blog);
        }
        return "redirect:/blog/view/" + parkCode;
    }
}
