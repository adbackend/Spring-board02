package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper ;
	
	private Long[] bnoArr = {30807L, 30806L, 30804L, 30799L, 30795L};
	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
//	//댓글 작성
//	@Test
//	public void tesetCreate() {
//		
//		IntStream.rangeClosed(1, 10).forEach(i->{
//			
//			ReplyVO vo = new ReplyVO();
//			
//			vo.setBno(bnoArr[i%5]);
//			vo.setReply("댓글테스트"+i);
//			vo.setReplyer("replyer"+i);
//			
//			mapper.insert(vo);
//			
//		});
//	}
	
//	//특정댓글 조회
//	@Test
//	public void testRead() {
//		
//		Long targetRno = 5L;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		
//		log.info(vo);
//		
//	}
	
//	//댓글삭제
//	@Test
//	public void testDelete() {
//		
//		Long targetBno = 10L;
//		
//		mapper.delete(targetBno);
//	}
	
//	//댓글 수정
//	@Test
//	public void testUpdate() {
//		
//		Long targetRno = 9L;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		vo.setReply("Update");
//		
//		int count = mapper.update(vo);
//		
//		log.info("update count: "+ count);
//		
//	}
	
	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info(reply));
	}
	
	

}
