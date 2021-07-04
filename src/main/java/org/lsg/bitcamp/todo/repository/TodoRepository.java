package org.lsg.bitcamp.todo.repository;

import org.lsg.bitcamp.todo.common.dynamic.TodoSearch;
import org.lsg.bitcamp.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long>, TodoSearch {

    @Modifying
    @Query("update Todo set content=:content where tno=:tno")
    //UPDATE 테이블명 SET 컬럼명 = 변경할 값 WHERE 컬럼명=값
    void repositoryUpdate(String content,Long tno);
}
