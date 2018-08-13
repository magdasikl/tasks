package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloServiceTest {
    @Mock
    private TrelloService trelloService;

    @Test
    public void fetchTrelloBoards() {

        //Given

        List<TrelloListDto> listTrelloListDto = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "boad",false);
        listTrelloListDto.add(trelloListDto);

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "list1", listTrelloListDto));
        List<TrelloBoardDto> trelloBoardDtos1 = trelloService.fetchTrelloBoards();
//        when().thenReturn(trelloBoardDto);
        //When
        assertEquals(trelloBoardDtos1, new ArrayList<>());
        trelloBoardDto.forEach(trelloBoardDtos -> {
            assertEquals("1", trelloBoardDtos.getId());
            assertEquals("list1", trelloBoardDtos.getName());

            trelloBoardDtos.getLists().forEach(trelloListDtos -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("boad", trelloListDto.getName());
                assertEquals(false, trelloListDto.isClosed());
            });

        });


    }
    @Test
    public void createdTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name","description","pos","list1");
        TrelloAttachmentsTrelloDto trelloAttachmentsTrelloDto = new TrelloAttachmentsTrelloDto(1,12);
        TrelloAttachmentsDto trelloAttachmentsDto = new TrelloAttachmentsDto(trelloAttachmentsTrelloDto);
        TrelloBadgesDto trelloBadgesDto = new TrelloBadgesDto(12,trelloAttachmentsDto);
        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto("1",trelloBadgesDto,"NewCard","url");
        when(trelloService.createdTrelloCard(trelloCardDto)).thenReturn(newCard);
        //When
        CreatedTrelloCardDto card = trelloService.createdTrelloCard(trelloCardDto);
        //then
        assertEquals("NewCard", card.getName());

    }
}