      package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo); //댓글작성
	
	public ReplyVO read(Long rno); //특정 댓글 읽기
	
	public int delete(Long targetBno); //댓글 삭제
	
	public int update(ReplyVO reply); //댓글수정
	
	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri,
										   @Param("bno") Long bno);

}
