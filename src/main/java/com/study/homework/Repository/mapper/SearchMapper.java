package com.study.homework.Repository.mapper;

import com.study.homework.Repository.VO.BoardVo;
import com.study.homework.Repository.VO.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SearchMapper {
    void insertSearch(SearchVo vo);
    List<String> selectSearch(String word);
    List<BoardVo> selectList(String word);
    int confirmKeyword(String word);
    List<String> myKeyword();
}
