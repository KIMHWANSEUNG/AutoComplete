package com.study.homework.Service;

import com.study.homework.Repository.VO.BoardVo;
import com.study.homework.Repository.VO.SearchVo;

import java.util.HashMap;
import java.util.List;


public interface SearchService
{
    public void insertSearch(SearchVo vo);
    public List<String> selectSearch(HashMap paramMap);
    public List<BoardVo> selectList(String word);
    public int confirmKeyword(String word);
    public List<String> myKeyword(String word);

}
