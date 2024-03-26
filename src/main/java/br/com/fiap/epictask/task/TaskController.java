package br.com.fiap.epictask.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    TaskRepository repository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("tasks", repository.findAll());
        return "task/index";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        var task = repository.findById(id);

        if (task.isEmpty()){
            redirect.addFlashAttribute("message", "Erro ao apagar. Tarefa não encontrada");
            return "redirect:/tasks";
        }
        
        repository.deleteById(id);
        redirect.addFlashAttribute("message", "Tarefa apagada com sucesso");
        return "redirect:/tasks";
    }
    
    @GetMapping("new")
    public String form(){
        return "task/form";
    }
    
    @PostMapping
    public String create(Task task, RedirectAttributes redirect){
        repository.save(task);
        redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso");
        return "redirect:/tasks";
    }

    
}
