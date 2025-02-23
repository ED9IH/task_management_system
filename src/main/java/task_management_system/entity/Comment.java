package task_management_system.entity;
import lombok.*;
import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "text")
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User author;
    @ManyToOne
    @JoinColumn(name = "task_id",referencedColumnName = "id")
    private Task task;
}