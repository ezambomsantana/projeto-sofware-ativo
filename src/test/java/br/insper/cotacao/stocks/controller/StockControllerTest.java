package br.insper.cotacao.stocks.controller;

import br.insper.cotacao.stocks.dto.StockDTO;
import br.insper.cotacao.stocks.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

    @InjectMocks
    public StockController stockController;

    @Mock
    public StockService stockService;

    public MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(stockController)
                .build();
    }

    @Test
    public void test_listAllShouldReturnOneStock() throws Exception {

        StockDTO stockDTO = new StockDTO(
                1,
                "PETR",
                "Petrobras",
                (float) 100.0,
                LocalDate.now(),
                LocalDate.now(),
                null);

        Mockito.when(stockService.listAll())
                .thenReturn(List.of(stockDTO));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/stocks")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ticker")
                        .value("PETR"));

    }


}
