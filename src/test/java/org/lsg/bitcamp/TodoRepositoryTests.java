package org.lsg.bitcamp;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.lsg.bitcamp.todo.entity.Todo;
import org.lsg.bitcamp.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class TodoRepositoryTests {
    @Autowired
    TodoRepository todoRepository;

    @Test
    public void testCreate() {
        IntStream.rangeClosed(0,300).forEach(i->{
            todoRepository.save(Todo.builder()
                    .content("내용" +i)
                    .del(false)
                    .build());
        });
    }

    @Test
    public void testRead() {
        Optional<Todo> result = todoRepository.findById(250L);
        result.ifPresent(todo -> {
            log.info(todo);
        });
    }

    @Test
    public void testDelete() {
        Optional<Todo> result = todoRepository.findById(18L);
        result.ifPresent(todo -> {
            todoRepository.delete(todo);
        });
    }

    @Test
    public void testUpdate() {
        Optional<Todo> result = todoRepository.findById(299L);
        result.ifPresent(todo->{
            todo.changeContent("내용수정");
            todoRepository.save(todo);
        });
    }
    @Test
    @Transactional
    @Commit
    public void testUpdate2() {
        todoRepository.repositoryUpdate("255",255L);
    }

    @Test
    public void todoSearch() {
        todoRepository.doa();
    }

    @Test
    public void todoSearch2() {
        String keyword = "29";
        Pageable pageable = PageRequest.of(0,10);
        Page<Todo> result =todoRepository.listWithSearch(keyword,pageable);
        result.getContent().forEach(todo ->{
            log.info(todo);
        });
    }
}
