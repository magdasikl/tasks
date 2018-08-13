package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloServiceTest {

    @Test
    public void fetchTrelloBoards() {

        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "list1", false));
        trelloListsDto.add(new TrelloListDto("2", "list2", false));

        TrelloBoardDto board = new TrelloBoardDto("1", "boad", trelloListsDto);
        //When
    }
    @Test
    public void createdTrelloCard() {
    }
}