package task_management_system.entity;

import lombok.*;
import task_management_system.util.*;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Enumerated(value = EnumType.STRING)
    @Column(name ="priority")
    private Priority priority;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User author;
    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comment;

}