package br.com.fiap.epictask.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.epictask.user.User;
import br.com.fiap.epictask.user.UserService;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    @Autowired
    UserService userService;

    public void catchTask(Long id, User myuser) {
        var task = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );

        if(task.getUser() != null)
            throw new IllegalArgumentException("tarefa já atribuída");

        task.setUser(myuser);
        repository.save(task);
    }

    public void dropTask(Long id, User myuser) {
        var task = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );

        if(task.getUser() != myuser)
            throw new IllegalArgumentException("tarefa atribuída a outro usuário");


        task.setUser(null);
        repository.save(task);
    }

    public void inc(Long id, User myuser) {
        var task = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );
        if (task.getStatus() +10 > 100) return;
        task.setStatus(task.getStatus() + 10);
        
        if (task.getStatus() == 100){
            userService.addScore(myuser, task.getScore());
        }
        
        repository.save(task);
    }

    public void dec(Long id, User myuser) {
        var task = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("tarefa não encontrada")
        );

        if (task.getStatus() - 10 < 0) return;

        task.setStatus(task.getStatus() - 10);
        repository.save(task);
    }

}
