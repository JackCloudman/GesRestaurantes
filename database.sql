--
-- PostgreSQL database dump
--

-- Dumped from database version 12.5 (Ubuntu 12.5-1.pgdg16.04+1)
-- Dumped by pg_dump version 13.1

-- Started on 2021-01-25 21:23:19

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: anobthzyaqzwnc
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO anobthzyaqzwnc;

--
-- TOC entry 3867 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: anobthzyaqzwnc
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 15595030)
-- Name: calificaciones; Type: TABLE; Schema: public; Owner: anobthzyaqzwnc
--

CREATE TABLE public.calificaciones (
    id bigint NOT NULL,
    comentario character varying(255),
    estrellas integer,
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    restaurante_id bigint,
    usuario_id bigint
);


ALTER TABLE public.calificaciones OWNER TO anobthzyaqzwnc;

--
-- TOC entry 203 (class 1259 OID 15595036)
-- Name: categorias; Type: TABLE; Schema: public; Owner: anobthzyaqzwnc
--

CREATE TABLE public.categorias (
    id bigint NOT NULL,
    nombre_categoria character varying(255)
);


ALTER TABLE public.categorias OWNER TO anobthzyaqzwnc;

--
-- TOC entry 206 (class 1259 OID 15595059)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: anobthzyaqzwnc
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO anobthzyaqzwnc;

--
-- TOC entry 204 (class 1259 OID 15595041)
-- Name: restaurantes; Type: TABLE; Schema: public; Owner: anobthzyaqzwnc
--

CREATE TABLE public.restaurantes (
    id bigint NOT NULL,
    descripcion character varying(255),
    direccion character varying(255),
    estrellas real NOT NULL,
    foto character varying(255),
    nombre character varying(255),
    tipo bigint
);


ALTER TABLE public.restaurantes OWNER TO anobthzyaqzwnc;

--
-- TOC entry 205 (class 1259 OID 15595049)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: anobthzyaqzwnc
--

CREATE TABLE public.usuarios (
    id bigint NOT NULL,
    enabled boolean,
    lastname character varying(255),
    name character varying(255),
    password character varying(255),
    role character varying(255),
    username character varying(255)
);


ALTER TABLE public.usuarios OWNER TO anobthzyaqzwnc;

--
-- TOC entry 3857 (class 0 OID 15595030)
-- Dependencies: 202
-- Data for Name: calificaciones; Type: TABLE DATA; Schema: public; Owner: anobthzyaqzwnc
--

INSERT INTO public.calificaciones VALUES (8, 'Buen servicio', 4, '2021-01-25 16:31:14.513', 5, 1);
INSERT INTO public.calificaciones VALUES (16, 'Excelentes servivios', 5, '2021-01-25 21:29:17.791', 5, 15);
INSERT INTO public.calificaciones VALUES (17, 'Las mejores aguas de alfalfa', 5, '2021-01-26 02:55:17.369', 13, 15);


--
-- TOC entry 3858 (class 0 OID 15595036)
-- Dependencies: 203
-- Data for Name: categorias; Type: TABLE DATA; Schema: public; Owner: anobthzyaqzwnc
--

INSERT INTO public.categorias VALUES (2, 'Mexicana');
INSERT INTO public.categorias VALUES (3, 'Japonesa');
INSERT INTO public.categorias VALUES (4, 'Americana');
INSERT INTO public.categorias VALUES (12, 'Jugos y bebidas');


--
-- TOC entry 3859 (class 0 OID 15595041)
-- Dependencies: 204
-- Data for Name: restaurantes; Type: TABLE DATA; Schema: public; Owner: anobthzyaqzwnc
--

INSERT INTO public.restaurantes VALUES (11, 'Subway de la plaza', 'Plaza torres', 0, 'subway.JPG', 'Subway', 4);
INSERT INTO public.restaurantes VALUES (14, 'Cafetería del CIC', 'IPN - Computing Research Center', 0, 'download.jpg', 'Restaurante CIC', 2);
INSERT INTO public.restaurantes VALUES (5, 'Cafeteria de la escom', 'Escuela Superior de Computo', 0, '30429246_iRTCnBUCV7wQY0iZ2AKLiB3gyZNBohDzfq5ltlO9Y04.jpg', 'Cafeteria ESCOM', 2);
INSERT INTO public.restaurantes VALUES (13, 'Aguas del chefcito bien sabrosas', 'Afuera del metro lindavista', 5, 'chefcito.JPG', 'Aguas el chefcito', 12);


--
-- TOC entry 3860 (class 0 OID 15595049)
-- Dependencies: 205
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: anobthzyaqzwnc
--

INSERT INTO public.usuarios VALUES (1, true, 'Reyes', 'Juan Jose', '$2a$10$M6KLQhwmp/aZHn98A2EFt.nGlzeb8svq/0pciSix7CgKAtvpDwSwi', 'ROLE_ADMIN', 'maylo360xd@gmail.com');
INSERT INTO public.usuarios VALUES (10, true, 'Cloudman', 'Jack', '$2a$10$peYSnATrlUPyQY7KZpPx7OtN30HvA5Fk.jMjdf4VMHFzZJNZfGnFO', 'ROLE_USER', 'maylo360.xd@gmail.com');
INSERT INTO public.usuarios VALUES (15, true, 'Pina', 'Cesar', '$2a$10$59mLuUIIEnX4oQuP9q03AeR0XnoNuZV9jpCSIg1c2D6.7wDcj.ADa', 'ROLE_USER', 'cesar.saulo.u2@gmail.com');


--
-- TOC entry 3870 (class 0 OID 0)
-- Dependencies: 206
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: anobthzyaqzwnc
--

SELECT pg_catalog.setval('public.hibernate_sequence', 17, true);


--
-- TOC entry 3719 (class 2606 OID 15595035)
-- Name: calificaciones calificaciones_pkey; Type: CONSTRAINT; Schema: public; Owner: anobthzyaqzwnc
--

ALTER TABLE ONLY public.calificaciones
    ADD CONSTRAINT calificaciones_pkey PRIMARY KEY (id);


--
-- TOC entry 3721 (class 2606 OID 15595040)
-- Name: categorias categorias_pkey; Type: CONSTRAINT; Schema: public; Owner: anobthzyaqzwnc
--

ALTER TABLE ONLY public.categorias
    ADD CONSTRAINT categorias_pkey PRIMARY KEY (id);


--
-- TOC entry 3723 (class 2606 OID 15595048)
-- Name: restaurantes restaurantes_pkey; Type: CONSTRAINT; Schema: public; Owner: anobthzyaqzwnc
--

ALTER TABLE ONLY public.restaurantes
    ADD CONSTRAINT restaurantes_pkey PRIMARY KEY (id);


--
-- TOC entry 3725 (class 2606 OID 15595058)
-- Name: usuarios uk_m2dvbwfge291euvmk6vkkocao; Type: CONSTRAINT; Schema: public; Owner: anobthzyaqzwnc
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT uk_m2dvbwfge291euvmk6vkkocao UNIQUE (username);


--
-- TOC entry 3727 (class 2606 OID 15595056)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: anobthzyaqzwnc
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- TOC entry 3730 (class 2606 OID 15595071)
-- Name: restaurantes fkh75tbfocm8690r74i5sf72ice; Type: FK CONSTRAINT; Schema: public; Owner: anobthzyaqzwnc
--

ALTER TABLE ONLY public.restaurantes
    ADD CONSTRAINT fkh75tbfocm8690r74i5sf72ice FOREIGN KEY (tipo) REFERENCES public.categorias(id) ON DELETE CASCADE;


--
-- TOC entry 3728 (class 2606 OID 15595061)
-- Name: calificaciones fkjba6wa164uifq2xmh3rxtelqv; Type: FK CONSTRAINT; Schema: public; Owner: anobthzyaqzwnc
--

ALTER TABLE ONLY public.calificaciones
    ADD CONSTRAINT fkjba6wa164uifq2xmh3rxtelqv FOREIGN KEY (restaurante_id) REFERENCES public.restaurantes(id) ON DELETE CASCADE;


--
-- TOC entry 3729 (class 2606 OID 15595066)
-- Name: calificaciones fkm1utmk8ppppf1tapx0cvc5564; Type: FK CONSTRAINT; Schema: public; Owner: anobthzyaqzwnc
--

ALTER TABLE ONLY public.calificaciones
    ADD CONSTRAINT fkm1utmk8ppppf1tapx0cvc5564 FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id) ON DELETE CASCADE;


--
-- TOC entry 3868 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: anobthzyaqzwnc
--

REVOKE ALL ON SCHEMA public FROM postgres;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO anobthzyaqzwnc;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 3869 (class 0 OID 0)
-- Dependencies: 640
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON LANGUAGE plpgsql TO anobthzyaqzwnc;


-- Completed on 2021-01-25 21:23:26

--
-- PostgreSQL database dump complete
--

