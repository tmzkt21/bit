package org.lsg.bitcamp.todo.service;

import lombok.Builder;
import org.lsg.bitcamp.todo.dto.ListRequestDTO;
import org.lsg.bitcamp.todo.dto.ListResponseDTO;
import org.lsg.bitcamp.todo.dto.TodoDTO;
import org.lsg.bitcamp.todo.entity.Todo;
import org.lsg.bitcamp.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;


public interface TodoService {
    void register(TodoDTO todoDTO);

    default Todo dtoToEntity(TodoDTO todoDTO){
       return Todo.builder()
                .tno(todoDTO.getTno())
                .content(todoDTO.getContent())
                .del(todoDTO.isDel())
                .build();
    }
    default TodoDTO entityToDTO(Todo todo) {
        return TodoDTO.builder()
                .tno(todo.getTno())
                .content(todo.getContent())
                .del(todo.isDel())
                .modDate(todo.getModDate())
                .regDate(todo.getRegDate())
                .build();
    }

    void read(Long tno);

    void remove(Long tno);

    void modify(Long tno, TodoDTO todoDTO);

    ListResponseDTO<TodoDTO> list(ListRequestDTO listRequestDTO);
}
