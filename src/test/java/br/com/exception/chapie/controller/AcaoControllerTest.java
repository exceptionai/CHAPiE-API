package br.com.exception.chapie.controller;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.junit.Assert.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AcaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deve listar todas ações e retornar status 200")
    public void deveEncontrarTodos() throws Exception {
        mvc.perform(get("/acoes")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Deve retornar uma ação pelo ID e com status 200")
    public void deveEncontrarPeloId() throws Exception {
        mvc.perform(get("/acoes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"id\":1,\"nome\":\"atualizacao de sistema\",\"descricao\":\"verifica na internet possiveis atualizacoes de sistema\",\"ativo\":true}"));
    }

    @Test
    @DisplayName("Deve salvar uma ação, retornar status 201 e Location no Header")
    public void deveInserir() throws Exception  {
        mvc.perform(post("/acoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +"    \"nome\": \"atualizacao de sistema\"," + "\"descricao\": \"verifica na internet possiveis atualizacoes de sistema\"," + "    \"ativo\": true" +  "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("Deve deletar uma ação pelo id e retornar status 204")
    public void deveDeletar() throws Exception  {

        mvc.perform(delete("/acoes/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve atualizar uma ação pelo id e retornar status 200")
    public void deveAtualizar() throws Exception  {
        mvc.perform(put("/acoes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Atualizar Procedimentos\",\"descricao\":\"Atualiza os procedimentos\",\"ativo\":true}"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}