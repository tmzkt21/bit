package org.lsg.bitcamp.todo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.lsg.bitcamp.todo.dto.ListRequestDTO;
import org.lsg.bitcamp.todo.dto.ListResponseDTO;
import org.lsg.bitcamp.todo.dto.TodoDTO;
import org.lsg.bitcamp.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    //값이수정되지않게
    private final TodoService todoService;

    @PostMapping("/")
    public ResponseEntity<TodoDTO> regiter(@RequestBody TodoDTO todoDTO) {
        todoService.register(todoDTO);
        return null;
   }
   @GetMapping("/{tno}")
   public ResponseEntity<TodoDTO> read(@PathVariable Long tno) {

        todoService.read(tno);
        return null;
   }
    @DeleteMapping("/{tno}")
    public  ResponseEntity<TodoDTO> remove(@PathVariable Long tno) {
       todoService.remove(tno);
        return null;
    }

    @PutMapping("/{tno}")
    public ResponseEntity<TodoDTO> modify(@PathVariable Long tno,@RequestBody TodoDTO todoDTO){

        todoService.modify(tno,todoDTO);

        return null;
    }
    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<TodoDTO>> list(ListRequestDTO listRequestDTO){


        return ResponseEntity.ok(todoService.list(listRequestDTO));
    }

}
