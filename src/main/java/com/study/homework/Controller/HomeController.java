package com.study.homework.Controller;

import com.study.homework.Repository.VO.BoardVo;
import com.study.homework.Repository.VO.SearchVo;
import com.study.homework.Service.SearchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
public class HomeController {
    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/")
    public String home(HttpServletRequest req,Model model){



        return "home";
    }



    @ResponseBody
    @GetMapping(value = "selectword.ajax")
    public Map<String,Object> selectWord(String word){

        //내가 검색한 3건 받아오는 구문
        List<String> myKeyword=searchService.myKeyword(word);

        //Mybatis selectSearch로 보내는 HashMap => 전체 검색한 키워드를 내가 검색한 3건을 빼게 하기위한 구문
        HashMap<String,Object> selectSearchMap=new HashMap<>();
        selectSearchMap.put("myKeyword",myKeyword);
        selectSearchMap.put("word",word);

        // 로그 찍어
        logger.info("############################" + word);

        //전체 검색 받아오는 구문
        List<String> selectSearchlist=searchService.selectSearch(selectSearchMap);



        Map<String,Object> linkedhashmap=new LinkedHashMap<>();
        logger.info("*******************"+selectSearchlist+"*******************");

        //내가 검색한 키워드
        for(String mySearch:myKeyword){
            linkedhashmap.put(mySearch,"mysearch");
        }

        //전체검색한 키워드
        for(String totalSearch:selectSearchlist){
            linkedhashmap.put(totalSearch,"totalsearch");
        }


        return linkedhashmap;
    }

    @ResponseBody
    @RequestMapping(value="insertword.ajax")
    public List<BoardVo> insertSearch(HttpServletRequest req,Model model) {
        SearchVo searchVo = new SearchVo();
        String word = req.getParameter("word");
        //내가 입력한 키워드가 키워드히스토리에 몇개가 있는지 받아오는 구문
        int countKeyword=searchService.confirmKeyword(word);

        List<BoardVo> selectList = searchService.selectList(word);
        searchVo.setKeyword(word);
        //
        if(countKeyword == 0) {
            searchService.insertSearch(searchVo);
        }
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+countKeyword+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!"+selectList+"!!!!!!!!!!!!!!!!!!!!!!!!!");



        return selectList;
    }

    @RequestMapping(value="facebook")
    public String facebook(){
        return"facebook";
    }
}
