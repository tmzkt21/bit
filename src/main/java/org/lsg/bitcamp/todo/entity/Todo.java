package org.lsg.bitcamp.todo.entity;

import lombok.*;
import org.lsg.bitcamp.todo.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "todo")
@Builder
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(nullable = false, length = 300)
    private String content;

    private boolean del;

    public void changeContent(String content) {
        this.content = content;
    }
    public void changeDel(boolean del) {
        this.del = del;
    }
}
