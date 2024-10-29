package edu.du.sb1024.fileuploadboard.repository;

import edu.du.sb1024.fileuploadboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    public List<Board> findAllByOrderByBoardIdxDesc();
}
