package br.com.exception.chapie.service;

import br.com.exception.chapie.exception.ExecucaoException;
import br.com.exception.chapie.model.Acao;
import br.com.exception.chapie.model.Execucao;

import br.com.exception.chapie.repository.ExecucaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ExecucaoServiceTest {

    @InjectMocks
    public ExecucaoService execucaoService;

    @Mock
    public ExecucaoRepository repository;

    @Mock
    public AcaoService acaoService;

    @Before
    public void init() {
		MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testeInsertSemAcaoETeste() {
        Execucao execucao = new Execucao();
        Acao acao = new Acao(1);
        execucao.setAcao(acao);
        Acao acaoRetorno = new Acao(1,true);

        Mockito.when(acaoService.encontrarPeloId(Mockito.anyInt())).thenReturn(acaoRetorno);
        execucaoService.inserir(execucao);

    }


    @Test(expected = ExecucaoException.class)
    public void testeInsertException() {
        Execucao execucao = new Execucao();
        Acao acao = new Acao(1);
        execucao.setAcao(acao);
        Acao acaoRetorno = new Acao(1,false);

        Mockito.when(acaoService.encontrarPeloId(Mockito.anyInt())).thenReturn(acaoRetorno);
        execucaoService.inserir(execucao);

    }

}