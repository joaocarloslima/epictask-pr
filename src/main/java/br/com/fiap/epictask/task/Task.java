package br.com.fiap.epictask.task;

import br.com.fiap.epictask.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{task.title.notblank}")
    private String title;

    @Size(min = 10, max = 255, message = "{task.description.size}")
    private String description;

    @Positive(message = "{task.score.positive}" )
    private int score;

    @Min(0) @Max(100)
    private int status;

    @ManyToOne
    private User user;

}
