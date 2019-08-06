--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4
-- Dumped by pg_dump version 11.4

-- Started on 2019-08-06 12:02:25

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

DROP DATABASE shop_db_ma;
--
-- TOC entry 2854 (class 1262 OID 16393)
-- Name: shop_db_ma; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE shop_db_ma WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Ukrainian_Ukraine.1251' LC_CTYPE = 'Ukrainian_Ukraine.1251';


ALTER DATABASE shop_db_ma OWNER TO postgres;

\connect shop_db_ma

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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 201 (class 1259 OID 16418)
-- Name: carts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carts (
    id integer NOT NULL,
    creation_time bigint NOT NULL,
    closed boolean NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.carts OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16416)
-- Name: carts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.carts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.carts_id_seq OWNER TO postgres;

--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 200
-- Name: carts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.carts_id_seq OWNED BY public.carts.id;


--
-- TOC entry 199 (class 1259 OID 16407)
-- Name: items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.items (
    id integer NOT NULL,
    item_code character varying(50) NOT NULL,
    name character varying(1000) NOT NULL,
    price bigint
);


ALTER TABLE public.items OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16405)
-- Name: items_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.items_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.items_id_seq OWNER TO postgres;

--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 198
-- Name: items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.items_id_seq OWNED BY public.items.id;


--
-- TOC entry 203 (class 1259 OID 16431)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    cart_id integer NOT NULL,
    item_id integer NOT NULL,
    amount integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16429)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;

--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 202
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- TOC entry 197 (class 1259 OID 16396)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    login character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    first_name character varying(200) NOT NULL,
    second_name character varying(200) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16394)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 2858 (class 0 OID 0)
-- Dependencies: 196
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 2707 (class 2604 OID 16421)
-- Name: carts id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts ALTER COLUMN id SET DEFAULT nextval('public.carts_id_seq'::regclass);


--
-- TOC entry 2706 (class 2604 OID 16410)
-- Name: items id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items ALTER COLUMN id SET DEFAULT nextval('public.items_id_seq'::regclass);


--
-- TOC entry 2708 (class 2604 OID 16434)
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- TOC entry 2705 (class 2604 OID 16399)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2846 (class 0 OID 16418)
-- Dependencies: 201
-- Data for Name: carts; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (3, 234234230000, true, 1);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (5, 1565024867119, false, 21);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (6, 1565024869119, true, 22);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (7, 1565024869119, false, 23);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (8, 1565024869119, true, 24);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (9, 1565024867119, false, 25);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (10, 1565024869119, true, 26);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (11, 1565024869119, false, 27);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (12, 1565024869119, true, 28);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (13, 1565024869119, true, 29);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (14, 1565024869119, true, 20);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (4, 1565024867119, true, 20);
INSERT INTO public.carts (id, creation_time, closed, user_id) VALUES (15, 1565024869119, true, 20);


--
-- TOC entry 2844 (class 0 OID 16407)
-- Dependencies: 199
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.items (id, item_code, name, price) VALUES (1, '455534345', 'Сигарети "Malboro"', 2300);


--
-- TOC entry 2848 (class 0 OID 16431)
-- Dependencies: 203
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2842 (class 0 OID 16396)
-- Dependencies: 197
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (1, 'sters', '12345', 'sters', 'rych');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (16, 'test_user', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (20, 'user_login', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (21, 'user_login', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (22, 'user_login', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (23, 'user_login', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (24, 'user_login', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (25, 'user_login', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (26, 'user_login', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (27, 'user_login', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (28, 'user_login', 'test_pass', 'test_name', 'test_surname');
INSERT INTO public.users (id, login, password, first_name, second_name) VALUES (29, 'user_login', 'test_pass', 'test_name', 'test_surname');


--
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 200
-- Name: carts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.carts_id_seq', 15, true);


--
-- TOC entry 2860 (class 0 OID 0)
-- Dependencies: 198
-- Name: items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.items_id_seq', 13, true);


--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 202
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 10, true);


--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 196
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 29, true);


--
-- TOC entry 2714 (class 2606 OID 16423)
-- Name: carts carts_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_pk PRIMARY KEY (id);


--
-- TOC entry 2712 (class 2606 OID 16415)
-- Name: items item_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT item_pk PRIMARY KEY (id);


--
-- TOC entry 2716 (class 2606 OID 16436)
-- Name: orders orders_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);


--
-- TOC entry 2710 (class 2606 OID 16404)
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- TOC entry 2717 (class 2606 OID 16424)
-- Name: carts carts_users_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_users_fk FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2719 (class 2606 OID 16442)
-- Name: orders orders_carts_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_carts_fk FOREIGN KEY (cart_id) REFERENCES public.carts(id);


--
-- TOC entry 2718 (class 2606 OID 16437)
-- Name: orders orders_items_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_items_fk FOREIGN KEY (item_id) REFERENCES public.items(id);


-- Completed on 2019-08-06 12:02:26

--
-- PostgreSQL database dump complete
--

