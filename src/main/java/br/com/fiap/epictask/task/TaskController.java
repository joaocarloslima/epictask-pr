package br.com.fiap.epictask.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    TaskRepository repository;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model){
        model.addAttribute("tasks", repository.findAll());
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

    
}
