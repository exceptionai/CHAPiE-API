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


    public List<Execucao> findAll(){
        return repository.findAll();
    }

    public Execucao findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Execucao.class.getName()));
    }


    public Execucao insert(Execucao execucao) {
        execucao.setId(null);
        execucao.setDataExecucao(new Date());
        Acao acao = execucao.getAcao();
        boolean isAtivo = acaoService.findById(acao.getId()).isAtivo();

        if(isAtivo){
            return repository.save(execucao);
        }
        throw new ExecucaoException("Uma execução só pode ser associada a uma ação ativa");
    }


    public Execucao update(Execucao execucao, Integer id){
        execucao.setId(id);
        return repository.save(execucao);
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }
}
