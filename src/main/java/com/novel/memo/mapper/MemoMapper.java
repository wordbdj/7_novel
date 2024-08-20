package com.novel.memo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.novel.memo.domain.Memo;

@Mapper
public interface MemoMapper {

	List<Memo> selectMemoByContentId(int contentId);

	Integer insertMemo(			
			@Param("userId")int userId,
			@Param("novelId")int novelId,
			@Param("contentId")int contentId,
			@Param("memo")String memo);
	
	

}
