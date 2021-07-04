package org.lsg.bitcamp.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.lsg.bitcamp.todo.dto.ListRequestDTO;
import org.lsg.bitcamp.todo.dto.ListResponseDTO;
import org.lsg.bitcamp.todo.dto.PageMaker;
import org.lsg.bitcamp.todo.dto.TodoDTO;
import org.lsg.bitcamp.todo.entity.Todo;
import org.lsg.bitcamp.todo.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;


    @Override
    public void register(TodoDTO todoDTO) {
        Todo todo = dtoToEntity(todoDTO);
        //변환완료
        todoRepository.save(dtoToEntity(todoDTO));
        log.info(todo + "저장한 투두값");
    }

    @Override
    public void read(Long tno) {
        todoRepository.findById(tno);
        log.info(todoRepository.findById(tno) + "찾은값");
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    @Override
    public void modify(Long tno, TodoDTO todoDTO) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo entity = dtoToEntity(todoDTO);
        result.ifPresent(todo -> {
            todo.changeContent(entity.getContent());
            log.info(todo.getContent() + "바뀌어서 들어가는 콘텐트");
            todo.changeDel(entity.isDel());
            todoRepository.save(todo);
            log.info(todo + "저장된 투두값");
        });
    }

    @Override
    public ListResponseDTO<TodoDTO> list(ListRequestDTO dto) {

        Page<Todo> result = todoRepository.listWithSearch(dto.getKeyword(),dto.getPageable());

        List<TodoDTO> dtoList = result.getContent().stream()
                .map(todo -> entityToDTO(todo)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(dto.getPage(),dto.getSize(),(int)result.getTotalElements());

        return ListResponseDTO.<TodoDTO>builder()
                .listRequestDTO(dto)
                .dtoList(dtoList)
                .pageMaker(pageMaker)
                .build();

    }



}
