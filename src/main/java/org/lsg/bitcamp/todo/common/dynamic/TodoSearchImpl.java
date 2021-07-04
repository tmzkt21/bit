package org.lsg.bitcamp.todo.common.dynamic;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.lsg.bitcamp.todo.entity.QTodo;
import org.lsg.bitcamp.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public void doa() {
        QTodo todo = QTodo.todo;
        JPQLQuery query = from(todo);

        query.where(todo.tno.gt(170L));
        query.orderBy(todo.tno.desc());
        query.offset(0);
        query.limit(10);

        List<Todo> list = query.fetch();
        long count = query.fetchCount();

        log.warn("결과값" + list);
        log.warn("COUNT" + count);
    }

    @Override
    public Page<Todo> listWithSearch(String keyword, Pageable pageable) {
        QTodo todo = QTodo.todo;
        JPQLQuery query = from(todo);

        if(keyword != null && keyword.trim().length() !=0){
            query.where(todo.content.contains(keyword));
        }

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());


        List<Todo> list = query.fetch();
        long count = query.fetchCount();


        return new PageImpl<>(list,pageable,count);
    }
}
