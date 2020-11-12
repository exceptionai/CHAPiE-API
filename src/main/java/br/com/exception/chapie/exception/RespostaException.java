package br.com.exception.chapie.exception;

import javax.servlet.http.HttpServletRequest;

public class RespostaException {
    private String metodo;
    private String caminho;
    private String mensagemErro;
    private Object detalhesErro;

    public RespostaException(HttpServletRequest request, String errorMessage, Object errorDetail){
        this.metodo = request.getMethod();
        this.caminho = request.getRequestURI();
        this.mensagemErro = errorMessage;
        this.detalhesErro = errorDetail;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public Object getDetalhesErro() {
        return detalhesErro;
    }

    public void setDetalhesErro(Object detalhesErro) {
        this.detalhesErro = detalhesErro;
    }
}
