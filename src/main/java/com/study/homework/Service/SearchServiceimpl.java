package com.study.homework.Service;

import com.study.homework.Repository.VO.BoardVo;
import com.study.homework.Repository.VO.SearchVo;
import com.study.homework.Repository.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceimpl implements SearchService {
    @Autowired
    private SearchMapper searchmapper;

    public void insertSearch(SearchVo vo){
        searchmapper.insertSearch(vo);
    }
    public List<String> selectSearch(String word){
       return searchmapper.selectSearch(word);
    }
    public List<BoardVo> selectList(String word){
        return searchmapper.selectList(word);
    }
    public int confirmKeyword(String word){
        return searchmapper.confirmKeyword(word);
    }
    public List<String> myKeyword(){
        return searchmapper.myKeyword();
    }

}
