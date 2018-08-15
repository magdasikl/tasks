package com.crud.tasks.service;

import com.crud.tasks.com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;

    @Test
    public void fetchTrelloBoards() {

        //Given

        List<TrelloListDto> listTrelloListDto = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "boad",false);
        listTrelloListDto.add(trelloListDto);

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "list1", listTrelloListDto));
        List<TrelloBoardDto> trelloBoardDtos1 = trelloService.fetchTrelloBoards();
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
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(newCard);
        //When
        CreatedTrelloCardDto card = trelloService.createdTrelloCard(trelloCardDto);
        //then
        assertEquals("NewCard", card.getName());

    }
}