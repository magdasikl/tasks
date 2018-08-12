package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloFacadeTest {

    @Autowired
    private TrelloFacade trelloFacade;

    @Autowired
    private TrelloMapper trelloMapper;
    @Autowired
    private TrelloService trelloService;
    @Autowired
    private TrelloValidator trelloValidator;


    @Test
    public void testMaperBoard () {
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto("1", "list1", false));
        listDto.add(new TrelloListDto("2", "list2", false));
        listDto.add(new TrelloListDto("3", "list3", true));

        List<TrelloBoardDto> boardDto = new ArrayList<>();
        boardDto.add(new TrelloBoardDto("1", "board1", listDto));
        boardDto.add(new TrelloBoardDto("2", "board2", listDto));
        boardDto.add(new TrelloBoardDto("3", "board3", listDto));


        //When
        List<TrelloBoard> trelloBoardList =  trelloMapper.mapToBoards(boardDto);
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then System.out.println(trelloBoardDtoList.get(0).getName());
        ////
//        System.out.println(boardDto.get(0).getName());
        assertEquals(boardDto.size(),trelloBoardDtoList.size());
        assertEquals(boardDto.get(0).getName(), trelloBoardDtoList.get(0).getName());

    }
}