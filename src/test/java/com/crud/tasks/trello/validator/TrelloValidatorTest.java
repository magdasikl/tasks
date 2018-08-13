package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {
    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("12","Board1",new ArrayList<TrelloList>()));
        trelloBoardList.add(new TrelloBoard("12","test",new ArrayList<TrelloList>()));
    //When
        List<TrelloBoard> trelloBoardListNew = trelloValidator.validateTrelloBoards(trelloBoardList);
    //Then
        assertEquals(1, trelloBoardListNew.size());

    }
}