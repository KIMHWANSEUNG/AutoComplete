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
import java.util.List;

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
    public HashMap<String,Object> selectWord(String word){
        HashMap<String,Object> hashMap=new HashMap<>();
        List<String> myKeyword=searchService.myKeyword(word);
        HashMap<String,Object> selectSearchMap=new HashMap<>();
        selectSearchMap.put("myKeyword",myKeyword);
        selectSearchMap.put("word",word);
        // 로그 찍어
        logger.info("############################" + word);
        List<String> selectSearchlist=searchService.selectSearch(selectSearchMap);

        for(String a : selectSearchlist){
            logger.info(a);
        }
        logger.info("*******************"+selectSearchlist+"*******************");
        hashMap.put("selectSearchlist",selectSearchlist);
        hashMap.put("myKeyword",myKeyword);
        return hashMap;
    }

    @ResponseBody
    @RequestMapping(value="insertword.ajax")
    public List<BoardVo> insertSearch(HttpServletRequest req,Model model) {
        SearchVo searchVo = new SearchVo();
        String word = req.getParameter("word");

        int countKeyword=searchService.confirmKeyword(word);
        List<BoardVo> selectList = searchService.selectList(word);
        searchVo.setKeyword(word);
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
