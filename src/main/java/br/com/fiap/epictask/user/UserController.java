package br.com.fiap.epictask.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository repository;
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("users", repository.findAllByOrderByScoreDesc());

        return "user/index";
    }

}
