package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest2 {
//    @Mock
//    private TrelloController trelloController;
//    @Autowired
//    private TrelloService trelloService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrelloFacade trelloFacade;


    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception {
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
        //When & Then
        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void shouldFetchTrelloBoards() throws Exception {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test List", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards. add(new TrelloBoardDto("1","Test Task", trelloLists));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
        //When & Then
        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //Trello board
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("Test Task")))
                //Trello list
                .andExpect(jsonPath("$[0].lists", hasSize(1)))
                .andExpect(jsonPath("$[0].lists[0].id", is("1")))
                .andExpect(jsonPath("$[0].lists[0].name", is("Test List")))
                .andExpect(jsonPath("$[0].lists[0].closed", is(false)));
    }


















//    @Test
//    public void getTrelloBoards() {
//        //Given
//        List<TrelloListDto> trelloListDto = new ArrayList<>();
//        trelloListDto.add(new TrelloListDto("1", "list1", false));
//
//        List<TrelloBoardDto> trelloBoardDto1 = new ArrayList<>();
//        trelloBoardDto1.add(new TrelloBoardDto("1", "board1", trelloListDto));
//
//        when(trelloController.getTrelloBoards()).thenReturn(trelloBoardDto1);
//        //When
//        List<TrelloBoardDto> trelloBoardDtos = trelloController.getTrelloBoards();
//        System.out.println(trelloBoardDtos);
//        //Then
//        trelloBoardDtos.forEach(trelloBoardDto -> {
//            assertEquals("1", trelloBoardDto.getId());
//            assertEquals("board1", trelloBoardDto.getName());
//
//            trelloBoardDto.getLists().forEach(trelloListDtos -> {
//                assertEquals("1", trelloListDtos.getId());
//                assertEquals("list1", trelloListDtos.getName());
//                assertEquals(false, trelloListDtos.isClosed());
//            });
//
//        });
//
//   }
//
//    @Test
//    public void createdTrelloCard() {
//        //Given
//        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("id",new TrelloBadgesDto(),"name","uu");
//        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "descr.card", "1", "1");
//
//        when(trelloController.createdTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
//
//        //when
//        CreatedTrelloCardDto createdTrelloCardDtos = trelloController.createdTrelloCard(trelloCardDto);
//        //Then
//        assertEquals("name",createdTrelloCardDtos.getName());
//    }
}