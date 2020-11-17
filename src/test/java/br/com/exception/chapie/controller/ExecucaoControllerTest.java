package br.com.exception.chapie.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ExecucaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Deve listar todas execuções e retornar status 200")
    public void deveEncontrarTodos() throws Exception {
        mvc.perform(get("/execucoes")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Deve retornar uma execução pelo ID e com status 200")
    public void deveEncontrarPeloId() throws Exception {
        mvc.perform(get("/execucoes/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"id\":2,\"acao\":{\"id\":2,\"nome\":\"Identificar erros\",\"descricao\":\"identifica se possui erros\",\"ativo\":true},\"dataExecucao\":\"2020-11-12T15:37:16.000+0000\"}"));
    }

    @Test
    @DisplayName("Deve salvar uma execução, retornar status 201 e Location no Header")
    public void deveInserir() throws Exception  {
        mvc.perform(post("/execucoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +"\"acao\": {" +
                        "        \"id\": 2" +
                        "    },\"dataExecucao\":\"2020-11-12T02:37:16.134+0000\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    @DisplayName("Deve deletar uma execução pelo id e retornar status 204")
    public void deveDeletar() throws Exception  {
        mvc.perform(delete("/execucoes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve atualizar uma execução pelo id e retornar status 200")
    public void deveAtualizar() throws Exception  {
        mvc.perform(put("/execucoes/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dataExecucao\": \"2020-11-12T01:36:11.947Z\",\"acao\":{\"id\":1}}"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}