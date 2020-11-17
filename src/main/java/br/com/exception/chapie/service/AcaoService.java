package br.com.exception.chapie.service;

import br.com.exception.chapie.exception.AcaoException;
import br.com.exception.chapie.exception.IntegridadeDadosException;
import br.com.exception.chapie.exception.ObjetoNaoEncontradoException;
import br.com.exception.chapie.model.Acao;
import br.com.exception.chapie.repository.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AcaoService {

    @Autowired
    AcaoRepository repository;

    public List<Acao> listarTodas(){
        return repository.findAll();
    }

    public Acao encontrarPeloId(Integer id){
        return repository.findById(id).orElseThrow(()-> new ObjetoNaoEncontradoException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Acao.class.getName()));
    }

    public Acao inserir(Acao acao) {
        acao.setId(null);
        if(acao.getNome().length() > 40){
            throw new AcaoException("O nome da ação deve conter no máximo 40 caracteres");
        }
        if(acao.getDescricao().length() > 100){
            throw new AcaoException("A descrição da ação deve conter no máximo 100 caracteres");
        }
        return repository.save(acao);
    }


    public Acao atualizar(Acao acao, Integer id){
        acao.setId(id);
        return repository.save(acao);
    }

    public void remover(Integer id){
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new IntegridadeDadosException("Não é possíve excluir uma ação que possua execução");

        }
    }
}
