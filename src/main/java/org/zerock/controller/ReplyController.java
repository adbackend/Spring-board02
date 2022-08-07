package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies")
@Log4j
@AllArgsConstructor
@RestController
public class ReplyController {
	
	
	private ReplyService replyService;
	
	//댓글등록
	@PostMapping(value = "/new",
				 consumes = "application/json", //수신하고자 하는 데이터 포맷정의
				 produces = {MediaType.TEXT_PLAIN_VALUE}) //출력하고자 하는 데이터 포맷정의
	public ResponseEntity<String> create(@RequestBody ReplyVO ReplyVO){
		
		log.info("ReplyVO: " + ReplyVO);
		
		int insertCount = replyService.register(ReplyVO);
		log.info("Reply InsetCount: " + insertCount);
		
		return insertCount==1? 
				new ResponseEntity<>("success",HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	//댓글목록
	@GetMapping(value = "/pages/{bno}/{page}",
				produces = {MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, 
												 @PathVariable("bno") Long bno){
		
		log.info("리스트............");
		
		Criteria cri = new Criteria(page, 10);
		log.info(cri);
		
		return new ResponseEntity<>(replyService.getListPage(cri, bno), HttpStatus.OK);
	}
	
	//댓글조회
	@GetMapping(value = "/{rno}",
				produces = {MediaType.APPLICATION_XML_VALUE, 
							MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		
		log.info("댓글조회......." + rno);
		
		return new ResponseEntity<>(replyService.get(rno), HttpStatus.OK);
	}
	
	//댓글삭제
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		
		log.info("댓글삭제....."+ rno);
		
		return replyService.remove(rno) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글수정
	@RequestMapping(value = "/{rno}",
					method = {RequestMethod.PUT, RequestMethod.PATCH},
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@RequestBody ReplyVO replyVO,
										 @PathVariable("rno") Long rno){
		
		replyVO.setRno(rno);
		log.info("rno......."+ rno);
		log.info("modify");
		
		return replyService.modify(replyVO) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
