package edu.du.sb1024.fileuploadboard.board.controller;

import edu.du.sb1024.fileuploadboard.board.dto.BoardDto;
import edu.du.sb1024.fileuploadboard.board.dto.BoardFileDto;
import edu.du.sb1024.fileuploadboard.board.service.BoardService;
import edu.du.sb1024.fileuploadboard.entity.Board;
import edu.du.sb1024.fileuploadboard.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
public class BoardController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardRepository boardRepository;
	
	@RequestMapping("/board/openBoardList.do")
	public String openBoardList(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) throws Exception{
		log.info("====> openBoardList {}", "테스트");
//		List<BoardDto> list = boardService.selectBoardList();

//		List<Board> list = boardRepository.findAllByOrderByBoardIdxDesc();
		List<BoardDto> list = boardService.selectBoardList();
		// 페이지 정보에 따라 현재 페이지의 시작 인덱스를 계산
		final int start = (int) pageable.getOffset();
		// 현재 페이지의 끝 인덱스를 계산하되, 목록 크기를 초과하지 않도록 함
		final int end = Math.min((start + pageable.getPageSize()), list.size());
		// 현재 페이지의 아이템 서브리스트를 포함하는 Page 객체 생성
		final Page<BoardDto> page = new PageImpl<>(list.subList(start, end), pageable, list.size());
		// 페이지 객체를 모델에 추가하여 뷰에서 접근 가능하도록 함
		model.addAttribute("list", page);
//		model.addAttribute("list", list);
		// 게시물 목록을 표시할 뷰 이름 반환
		return "board/boardList";
	}
	
	@RequestMapping("board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{
		return "board/boardWrite";
	}
	
	@RequestMapping("board/insertBoard.do")
	public String insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		boardService.insertBoard(board, multipartHttpServletRequest);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception{
		ModelAndView mv = new ModelAndView("board/boardDetail");
		
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		
		return mv;
	}
	
	@RequestMapping("board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("board/downloadBoardFile.do")
	public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception{
		String currentPath = Paths.get("").toAbsolutePath().toString();
		System.out.println("---------------------"+currentPath);
		BoardFileDto boardFile = boardService.selectBoardFileInformation(idx, boardIdx);
		if(ObjectUtils.isEmpty(boardFile) == false) {
			String fileName = boardFile.getOriginalFileName();
			
			byte[] files = FileUtils.readFileToByteArray(new File("./src/main/resources/static"+boardFile.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}
