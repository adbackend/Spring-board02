package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService boardService;

//	@GetMapping("/list")
//	public void list(Model model) {
//
//		log.info("list ");
//		model.addAttribute("list", boardService.getList());
//	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list: " + cri);
		log.info("리스트 페이지 cri - 한페이지당 출력되는 데이터수.... " + cri.getAmount());
		log.info("리스트 페이지 cri - 현재 페이지번호...." + cri.getPageNum());
		
		model.addAttribute("list",boardService.getList(cri));
		//model.addAttribute("pageMaker", new PageDTO(cri, 123));
		
		int total = boardService.getTotal(cri);
		log.info("전체 '"
				+ "........-> "+total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/register")
	public void register() { 
		
	}
	
	//글상세
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
//		log.info("register...... " + board);
		
		System.out.println(board.getContent());
		
		boardService.register(board);

		//addAttribute는 값을 지속적으로 사용해야할때 addFlashAttribute는 일회성으로 사용해야할때 사용
		rttr.addFlashAttribute("result", board.getBno());

		return "redirect:/board/list";

	}

//	@GetMapping({"/get","/modify"})
//	public void get(@RequestParam("bno") Long bno, Model model) {
//
//		log.info("/get.......OR /modify..........");
//		model.addAttribute("board", boardService.get(bno));
//
//	}
	
	//페이징 처리된
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		
//		log.info("/get or modify..............................");
//		log.info("한페이지당 출력되는 데이터수.........."+ cri.getAmount());
//		log.info("현재페이지............"+ cri.getPageNum());
		
		System.out.println();
		
		model.addAttribute("board",boardService.get(bno));
		
	}

//	@PostMapping("/modify")
//	public String modify(BoardVO board, RedirectAttributes rttr) {
//
//		log.info("modify: "+ board);
//		if(boardService.modify(board)) {
//			rttr.addFlashAttribute("result","success");
//		}
//
//		return "redirect:/board/list";
//
//	}
	
	//페이징 처리된
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("modify..........." + board);
		
		if(boardService.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/list";
	}

//	@PostMapping("/remove")
//	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
//
//		log.info("remove.........."+ bno);
//
//		if(boardService.remove(bno)) {
//			rttr.addFlashAttribute("result","success");
//		}
//
//		return "redirect:/board/list";
//
//	}
	
	//페이징 처리된
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("remove......" +bno);
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/list";
	}

}







