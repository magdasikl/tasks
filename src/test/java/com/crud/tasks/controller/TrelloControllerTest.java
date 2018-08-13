package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrelloControllerTest {
    @Mock
    private TrelloController trelloController;

    @Test
    public void getTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "list1", false));

        List<TrelloBoardDto> trelloBoardDto1 = new ArrayList<>();
        trelloBoardDto1.add(new TrelloBoardDto("1", "board1", trelloListDto));

        when(trelloController.getTrelloBoards()).thenReturn(trelloBoardDto1);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloController.getTrelloBoards();
        System.out.println(trelloBoardDtos);
        //Then
        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("board1", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDtos -> {
                assertEquals("1", trelloListDtos.getId());
                assertEquals("list1", trelloListDtos.getName());
                assertEquals(false, trelloListDtos.isClosed());
            });

        });

    }

    @Test
    public void createdTrelloCard() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id",new TrelloBadgesDto(),"name","uu");
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "descr.card", "1", "1");

        when(trelloController.createdTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //when
        CreatedTrelloCardDto createdTrelloCardDtos = trelloController.createdTrelloCard(trelloCardDto);
        //Then
        assertEquals("name",createdTrelloCardDtos.getName());
    }
}