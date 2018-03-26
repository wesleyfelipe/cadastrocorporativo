insert into UNIDADE_FEDERATIVA(ID, NOME, SIGLA) VALUES (UNIDADE_FEDERATIVA_SEQ.NEXTVAL, 'RIO GRANDE DO SUL', 'RS');
insert into UNIDADE_FEDERATIVA(ID, NOME, SIGLA) VALUES (UNIDADE_FEDERATIVA_SEQ.NEXTVAL, 'SANTA CATARINA', 'SC');

insert into MUNICIPIO(ID, ID_UNIDADE_FEDERATIVA, NOME) VALUES (MUNICIPIO_SEQ.NEXTVAL, (SELECT u.ID from UNIDADE_FEDERATIVA U WHERE u.sigla = 'RS'), 'CANOAS');
insert into MUNICIPIO(ID, ID_UNIDADE_FEDERATIVA, NOME) VALUES (MUNICIPIO_SEQ.NEXTVAL, (SELECT u.ID from UNIDADE_FEDERATIVA U WHERE u.sigla = 'RS'), 'PORTO ALEGRE');
insert into MUNICIPIO(ID, ID_UNIDADE_FEDERATIVA, NOME) VALUES (MUNICIPIO_SEQ.NEXTVAL, (SELECT u.ID from UNIDADE_FEDERATIVA U WHERE u.sigla = 'RS'), 'GRAMADO');
insert into MUNICIPIO(ID, ID_UNIDADE_FEDERATIVA, NOME) VALUES (MUNICIPIO_SEQ.NEXTVAL, (SELECT u.ID from UNIDADE_FEDERATIVA U WHERE u.sigla = 'SC'), 'FLORIANÓPOLIS');
insert into MUNICIPIO(ID, ID_UNIDADE_FEDERATIVA, NOME) VALUES (MUNICIPIO_SEQ.NEXTVAL, (SELECT u.ID from UNIDADE_FEDERATIVA U WHERE u.sigla = 'SC'), 'IMBITUBA');
insert into MUNICIPIO(ID, ID_UNIDADE_FEDERATIVA, NOME) VALUES (MUNICIPIO_SEQ.NEXTVAL, (SELECT u.ID from UNIDADE_FEDERATIVA U WHERE u.sigla = 'SC'), 'URUBICI');

insert into TIPO_DEPENDENCIA(id, Descricao) values (TIPO_DEPENDENCIA_SEQ.NEXTVAL, 'Filho');
insert into TIPO_DEPENDENCIA(id, Descricao) values (TIPO_DEPENDENCIA_SEQ.NEXTVAL, 'Filha');