package br.com.fiap.epictask.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.user.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("tasks")
@Slf4j
public class TaskController {

    @Autowired
    TaskRepository repository;

    @Autowired
    TaskService service;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal DefaultOAuth2User user){
        User myuser = (User) user;
        log.info("usuario carregado: "+ myuser);
        
        model.addAttribute("tasks", repository.findAll());
        model.addAttribute("user", user.getAttribute("name"));
        model.addAttribute("avatar", user.getAttribute("avatar_url"));
        model.addAttribute("principal", myuser);
        return "task/index";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        var task = repository.findById(id);

        if (task.isEmpty()){
            redirect.addFlashAttribute("message", "Erro ao apagar. Tarefa não encontrada");
            return "redirect:/tasks";
        }
        
        repository.deleteById(id);
        redirect.addFlashAttribute("message", messageSource.getMessage("task.delete", null, LocaleContextHolder.getLocale()));
        return "redirect:/tasks";
    }
    
    @GetMapping("new")
    public String form(Task task){
        return "task/form";
    }
    
    @PostMapping
    public String create(@Valid Task task, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "task/form";

        repository.save(task);
        redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso");
        return "redirect:/tasks";
    }

    @PostMapping("/catch/{id}")
    public String catchTask(@PathVariable Long id, @AuthenticationPrincipal DefaultOAuth2User user){
        User myuser = (User) user;
        service.catchTask(id, myuser);
        return "redirect:/tasks";
    }

    @PostMapping("/drop/{id}")
    public String dropTask(@PathVariable Long id, @AuthenticationPrincipal DefaultOAuth2User user){
        User myuser = (User) user;
        service.dropTask(id, myuser);
        return "redirect:/tasks";
    }

    @PostMapping("/inc/{id}")
    public String inc(@PathVariable Long id, @AuthenticationPrincipal DefaultOAuth2User user){
        User myuser = (User) user;
        service.inc(id, myuser);
        return "redirect:/tasks";
    }

    @PostMapping("/dec/{id}")
    public String dec(@PathVariable Long id, @AuthenticationPrincipal DefaultOAuth2User user){
        User myuser = (User) user;
        service.dec(id, myuser);
        return "redirect:/tasks";
    }

    
}
