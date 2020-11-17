INSERT INTO ACAO (ID_ACAO, NOME, DESCRICAO, ATIVO) VALUES (BOT_ACAO_SEQ.NEXTVAL,'atualizacao de sistema','verifica na internet possiveis atualizacoes de sistema', 1)
INSERT INTO ACAO (ID_ACAO, NOME, DESCRICAO, ATIVO) VALUES (BOT_ACAO_SEQ.NEXTVAL,'Identificar erros','identifica se possui erros', 1)
INSERT INTO ACAO (ID_ACAO, NOME, DESCRICAO, ATIVO) VALUES (BOT_ACAO_SEQ.NEXTVAL,'Transferência de consciencia','transfere a consciência para outro robô', 1)

INSERT INTO EXECUCAO (ID_EXECUCAO, ID_ACAO, DATA_EXECUCAO) VALUES (BOT_EXECUCAO_SEQ.NEXTVAL,1,SYSDATE);
INSERT INTO EXECUCAO (ID_EXECUCAO, ID_ACAO, DATA_EXECUCAO) VALUES (BOT_EXECUCAO_SEQ.NEXTVAL,2,TO_DATE('2020/11/12 12:37:16', 'yyyy/mm/dd hh24:mi:ss'));
INSERT INTO EXECUCAO (ID_EXECUCAO, ID_ACAO, DATA_EXECUCAO) VALUES (BOT_EXECUCAO_SEQ.NEXTVAL,1,SYSDATE);