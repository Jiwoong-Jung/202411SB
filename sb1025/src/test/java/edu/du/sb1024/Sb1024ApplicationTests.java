package edu.du.sb1024;

import edu.du.sb1024.fileuploadboard.entity.Board;
import edu.du.sb1024.fileuploadboard.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
class Sb1024ApplicationTests {
    @Autowired
    BoardRepository boardRepository;

    @Test
    void testPage1() {
        Pageable pageable = PageRequest.of(1, 10);
        List<Board> page = boardRepository.findAllByOrderByBoardIdxDesc(pageable);
        page.forEach(board -> System.out.println(board));
    }

}
