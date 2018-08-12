package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
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
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoard() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "list1", false));
        trelloListsDto.add(new TrelloListDto("2", "list2", false));

        TrelloBoardDto board = new TrelloBoardDto("1", "boad", trelloListsDto);
        //When
        TrelloBoard trelloBoard = trelloMapper.mapToBoard(board);
        //Then
        assertEquals(board.getLists().size(), trelloBoard.getLists().size());
    }

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> TrelloListDto = new ArrayList<>();
        TrelloListDto.add(new TrelloListDto("1", "list1", false));
        TrelloListDto.add(new TrelloListDto("2", "list2", false));
        TrelloListDto.add(new TrelloListDto("3", "list3", true));

        List<TrelloBoardDto> TrelloBoardDto = new ArrayList<>();
        TrelloBoardDto.add(new TrelloBoardDto("1", "board1", TrelloListDto));
        TrelloBoardDto.add(new TrelloBoardDto("2", "board2", TrelloListDto));
        TrelloBoardDto.add(new TrelloBoardDto("3", "board3", TrelloListDto));
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(TrelloBoardDto);
//        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals(TrelloBoardDto.size(), trelloBoardList.size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "list1", false));
        trelloLists.add(new TrelloList("2", "list2", false));
        trelloLists.add(new TrelloList("3", "list3", true));

        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard("1", "board1", trelloLists));
        trelloBoard.add(new TrelloBoard("2", "board2", trelloLists));
        trelloBoard.add(new TrelloBoard("3", "board3", trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardListDto = trelloMapper.mapToBoardsDto(trelloBoard);
        //Then
        assertEquals(trelloBoardListDto.get(0).getName(), trelloBoard.get(0).getName());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "list1", false));
        trelloListsDto.add(new TrelloListDto("2", "list2", false));
        trelloListsDto.add(new TrelloListDto("3", "list3", true));
        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals(trelloListsDto.get(2).getId(), trelloList.get(2).getId());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "list1", false));
        trelloLists.add(new TrelloList("2", "list2", false));
        trelloLists.add(new TrelloList("3", "list3", true));
        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(trelloListsDto.size(), trelloLists.size());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "descr.card", "1", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "descr.card", "1", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCard.getPos(),trelloCardDto.getPos());
    }
}