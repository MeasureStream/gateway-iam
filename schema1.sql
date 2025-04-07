--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4 (Debian 17.4-1.pgdg120+2)
-- Dumped by pg_dump version 17.4 (Debian 17.4-1.pgdg120+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: control_unit; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.control_unit (
    remaining_battery double precision NOT NULL,
    rssi double precision NOT NULL,
    id bigint NOT NULL,
    network_id bigint NOT NULL,
    node_id bigint,
    name character varying(255) NOT NULL,
    CONSTRAINT control_unit_remaining_battery_check CHECK ((remaining_battery <= (100)::double precision))
);


ALTER TABLE public.control_unit OWNER TO myuser;

--
-- Name: control_unit_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.control_unit_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.control_unit_seq OWNER TO myuser;

--
-- Name: measurement_unit; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.measurement_unit (
    id bigint NOT NULL,
    id_dcc bigint,
    network_id bigint,
    node_id bigint,
    measures_unit character varying(255) NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE public.measurement_unit OWNER TO myuser;

--
-- Name: measurement_unit_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.measurement_unit_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.measurement_unit_seq OWNER TO myuser;

--
-- Name: node; Type: TABLE; Schema: public; Owner: myuser
--

CREATE TABLE public.node (
    standard boolean NOT NULL,
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    location bytea
);


ALTER TABLE public.node OWNER TO myuser;

--
-- Name: node_seq; Type: SEQUENCE; Schema: public; Owner: myuser
--

CREATE SEQUENCE public.node_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.node_seq OWNER TO myuser;

--
-- Name: control_unit control_unit_network_id_key; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.control_unit
    ADD CONSTRAINT control_unit_network_id_key UNIQUE (network_id);


--
-- Name: control_unit control_unit_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.control_unit
    ADD CONSTRAINT control_unit_pkey PRIMARY KEY (id);


--
-- Name: measurement_unit measurement_unit_id_dcc_key; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.measurement_unit
    ADD CONSTRAINT measurement_unit_id_dcc_key UNIQUE (id_dcc);


--
-- Name: measurement_unit measurement_unit_network_id_key; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.measurement_unit
    ADD CONSTRAINT measurement_unit_network_id_key UNIQUE (network_id);


--
-- Name: measurement_unit measurement_unit_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.measurement_unit
    ADD CONSTRAINT measurement_unit_pkey PRIMARY KEY (id);


--
-- Name: node node_pkey; Type: CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.node
    ADD CONSTRAINT node_pkey PRIMARY KEY (id);


--
-- Name: measurement_unit fk6hg3ouh22gbmnsd76psijvce7; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.measurement_unit
    ADD CONSTRAINT fk6hg3ouh22gbmnsd76psijvce7 FOREIGN KEY (node_id) REFERENCES public.node(id);


--
-- Name: control_unit fke6pjx104vjjg6eogim2sxsi3f; Type: FK CONSTRAINT; Schema: public; Owner: myuser
--

ALTER TABLE ONLY public.control_unit
    ADD CONSTRAINT fke6pjx104vjjg6eogim2sxsi3f FOREIGN KEY (node_id) REFERENCES public.node(id);


--
-- PostgreSQL database dump complete
--

