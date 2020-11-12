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
        //DADO
        Execucao execucao = new Execucao();
        Acao acao = new Acao(1);
        execucao.setAcao(acao);
        Acao acaoRetorno = new Acao(1,true);

        //QUANDO
        Mockito.when(acaoService.findById(Mockito.anyInt())).thenReturn(acaoRetorno);
        execucaoService.insert(execucao);

    }


    @Test(expected = ExecucaoException.class)
    public void testeInsertException() {
        //DADO
        Execucao execucao = new Execucao();
        Acao acao = new Acao(1);
        execucao.setAcao(acao);
        Acao acaoRetorno = new Acao(1,false);

        //QUANDO
        Mockito.when(acaoService.findById(Mockito.anyInt())).thenReturn(acaoRetorno);
        execucaoService.insert(execucao);

    }

}