package br.com.fiap.epictask.task;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tasks")
public class TaskController {

    @GetMapping
    public String index(Model model){
        model.addAttribute("tasks", List.of(
            "Tarefa 1",
            "Tarefa 2",
            "Tarefa 3",
            "Test Unitário"
        ));
        return "task/index";
    }
    
}
