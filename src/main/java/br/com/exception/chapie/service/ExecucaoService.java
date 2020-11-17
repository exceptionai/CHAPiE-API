package br.com.exception.chapie.service;

import br.com.exception.chapie.exception.ExecucaoException;
import br.com.exception.chapie.exception.ObjetoNaoEncontradoException;
import br.com.exception.chapie.model.Acao;
import br.com.exception.chapie.model.Execucao;
import br.com.exception.chapie.repository.ExecucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExecucaoService {

    @Autowired
    ExecucaoRepository repository;

    @Autowired
    AcaoService acaoService;


    public List<Execucao> listarTodos(){
        return repository.findAll();
    }

    public Execucao encontrarPeloId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Execucao.class.getName()));
    }


    public Execucao inserir(Execucao execucao) {
        execucao.setId(null);
        execucao.setDataExecucao(new Date());
        Acao acao = execucao.getAcao();
        boolean isAtivo = acaoService.encontrarPeloId(acao.getId()).isAtivo();

        if(isAtivo){
            return repository.save(execucao);
        }
        throw new ExecucaoException("Uma execução só pode ser associada a uma ação ativa");
    }


    public Execucao atualizar(Execucao execucao, Integer id){
        execucao.setId(id);
        Execucao execucaoAtualizada = repository.save(execucao);
        Acao acaoCompleta = acaoService.encontrarPeloId(execucao.getAcao().getId());
        execucaoAtualizada.setAcao(acaoCompleta);
        return execucaoAtualizada;
    }

    public void remover(Integer id){
        repository.deleteById(id);
    }
}
