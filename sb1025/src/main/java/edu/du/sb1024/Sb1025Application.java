package edu.du.sb1024;

import edu.du.sb1024.fileuploadboard.entity.Board;
import edu.du.sb1024.fileuploadboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class Sb1025Application {
    @Autowired
    BoardRepository boardRepository;

    public static void main(String[] args) {
        SpringApplication.run(Sb1025Application.class, args);
    }
    @PostConstruct
    public void init() {

        IntStream.rangeClosed(1, 100).forEach(i->{
            Board board = Board.builder()
                    .title("제목"+i)
                    .createdDatetime(LocalDateTime.now().toString().substring(0, 10))
                    .contents("내용"+i)
                    .deletedYn("N")
                    .hitCnt(0)
                    .build();
            boardRepository.save(board);
        });

    }

}
