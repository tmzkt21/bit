package org.lsg.bitcamp.todo.common.dynamic;

import org.lsg.bitcamp.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoSearch {
    void doa();

    Page<Todo> listWithSearch(String keyword, Pageable pageable);
}
