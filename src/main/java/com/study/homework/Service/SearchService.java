package com.study.homework.Service;

import com.study.homework.Repository.VO.BoardVo;
import com.study.homework.Repository.VO.SearchVo;
import com.study.homework.Repository.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SearchService
{
    public void insertSearch(SearchVo vo);
    public List<String> selectSearch(String word);
    public List<BoardVo> selectList(String word);
    public int confirmKeyword(String word);
    public List<String> myKeyword();
}
