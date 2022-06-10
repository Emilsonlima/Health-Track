-- Gerado por Oracle SQL Developer Data Modeler 21.2.0.183.1957
--   em:        2022-06-10 16:36:31 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE t_alimento (
    id_ref             NUMBER(15) NOT NULL,
    tp_ref             VARCHAR2(20) NOT NULL,
    dt_ref             DATE NOT NULL,
    nr_cal             NUMBER(5, 2) NOT NULL,
    t_perfil_id_perfil NUMBER(15) NOT NULL
);

ALTER TABLE t_alimento ADD CONSTRAINT t_alimento_pk PRIMARY KEY ( id_ref,
                                                                  t_perfil_id_perfil );

CREATE TABLE t_atividade (
    id_atividade       NUMBER(15) NOT NULL,
    nm_atividade       VARCHAR2(20) NOT NULL,
    nr_cal             NUMBER(5, 2) NOT NULL,
    dt_atividade       DATE NOT NULL,
    t_perfil_id_perfil NUMBER(15) NOT NULL
);

ALTER TABLE t_atividade ADD CONSTRAINT t_atividade_pk PRIMARY KEY ( id_atividade,
                                                                    t_perfil_id_perfil );

CREATE TABLE t_perfil (
    id_perfil NUMBER(15) NOT NULL,
    nm_perfil VARCHAR2(60) NOT NULL,
    nr_idade  NUMBER(2) NOT NULL,
    nm_sexo   VARCHAR2(20) NOT NULL,
    vl_altura NUMBER(5, 2) NOT NULL
);

ALTER TABLE t_perfil ADD CONSTRAINT t_perfil_pk PRIMARY KEY ( id_perfil );

CREATE TABLE t_peso (
    id_peso            NUMBER(15) NOT NULL,
    vl_peso            NUMBER(5, 2) NOT NULL,
    dt_peso            DATE NOT NULL,
    t_perfil_id_perfil NUMBER(15) NOT NULL
);

ALTER TABLE t_peso ADD CONSTRAINT t_peso_pk PRIMARY KEY ( id_peso,
                                                          t_perfil_id_perfil );

CREATE TABLE t_press_art (
    id_press           NUMBER(15) NOT NULL,
    vl_press_sis       NUMBER(3) NOT NULL,
    vl_press_dis       NUMBER(2),
    dt_press           DATE NOT NULL,
    t_perfil_id_perfil NUMBER(15) NOT NULL
);

ALTER TABLE t_press_art ADD CONSTRAINT t_press_art_pk PRIMARY KEY ( id_press,
                                                                    t_perfil_id_perfil );

CREATE TABLE t_usuario (
    usuario_id         NUMBER NOT NULL,
    nm_email           VARCHAR2(60) NOT NULL,
    nr_senha           VARCHAR2(6) NOT NULL,
    t_perfil_id_perfil NUMBER(15) NOT NULL
);

ALTER TABLE t_usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( usuario_id );

ALTER TABLE t_alimento
    ADD CONSTRAINT t_alimento_t_perfil_fk FOREIGN KEY ( t_perfil_id_perfil )
        REFERENCES t_perfil ( id_perfil );

ALTER TABLE t_atividade
    ADD CONSTRAINT t_atividade_t_perfil_fk FOREIGN KEY ( t_perfil_id_perfil )
        REFERENCES t_perfil ( id_perfil );

ALTER TABLE t_peso
    ADD CONSTRAINT t_peso_t_perfil_fk FOREIGN KEY ( t_perfil_id_perfil )
        REFERENCES t_perfil ( id_perfil );

ALTER TABLE t_press_art
    ADD CONSTRAINT t_press_art_t_perfil_fk FOREIGN KEY ( t_perfil_id_perfil )
        REFERENCES t_perfil ( id_perfil );

ALTER TABLE t_usuario
    ADD CONSTRAINT t_usuario_t_perfil_fk FOREIGN KEY ( t_perfil_id_perfil )
        REFERENCES t_perfil ( id_perfil );

CREATE SEQUENCE usuario_usuario_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER usuario_usuario_id_trg BEFORE
    INSERT ON t_usuario
    FOR EACH ROW
    WHEN ( new.usuario_id IS NULL )
BEGIN
    :new.usuario_id := usuario_usuario_id_seq.nextval;
END;
/



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                             6
-- CREATE INDEX                             0
-- ALTER TABLE                             11
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           1
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          1
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
