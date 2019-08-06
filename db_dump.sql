PGDMP     '    8                w        
   shop_db_ma    11.4    11.4 #    #           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            $           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            %           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            &           1262    16393 
   shop_db_ma    DATABASE     �   CREATE DATABASE shop_db_ma WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Ukrainian_Ukraine.1251' LC_CTYPE = 'Ukrainian_Ukraine.1251';
    DROP DATABASE shop_db_ma;
             postgres    false            �            1259    16418    carts    TABLE     �   CREATE TABLE public.carts (
    id integer NOT NULL,
    creation_time bigint NOT NULL,
    closed boolean NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE public.carts;
       public         postgres    false            �            1259    16416    carts_id_seq    SEQUENCE     �   CREATE SEQUENCE public.carts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.carts_id_seq;
       public       postgres    false    201            '           0    0    carts_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.carts_id_seq OWNED BY public.carts.id;
            public       postgres    false    200            �            1259    16407    items    TABLE     �   CREATE TABLE public.items (
    id integer NOT NULL,
    item_code character varying(50) NOT NULL,
    name character varying(1000) NOT NULL,
    price bigint
);
    DROP TABLE public.items;
       public         postgres    false            �            1259    16405    items_id_seq    SEQUENCE     �   CREATE SEQUENCE public.items_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.items_id_seq;
       public       postgres    false    199            (           0    0    items_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.items_id_seq OWNED BY public.items.id;
            public       postgres    false    198            �            1259    16431    orders    TABLE     �   CREATE TABLE public.orders (
    id integer NOT NULL,
    cart_id integer NOT NULL,
    item_id integer NOT NULL,
    amount integer NOT NULL
);
    DROP TABLE public.orders;
       public         postgres    false            �            1259    16429    orders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public       postgres    false    203            )           0    0    orders_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;
            public       postgres    false    202            �            1259    16396    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    login character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    first_name character varying(200) NOT NULL,
    second_name character varying(200) NOT NULL
);
    DROP TABLE public.users;
       public         postgres    false            �            1259    16394    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public       postgres    false    197            *           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
            public       postgres    false    196            �
           2604    16421    carts id    DEFAULT     d   ALTER TABLE ONLY public.carts ALTER COLUMN id SET DEFAULT nextval('public.carts_id_seq'::regclass);
 7   ALTER TABLE public.carts ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    201    200    201            �
           2604    16410    items id    DEFAULT     d   ALTER TABLE ONLY public.items ALTER COLUMN id SET DEFAULT nextval('public.items_id_seq'::regclass);
 7   ALTER TABLE public.items ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    198    199    199            �
           2604    16434 	   orders id    DEFAULT     f   ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 8   ALTER TABLE public.orders ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    202    203    203            �
           2604    16399    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    197    196    197                      0    16418    carts 
   TABLE DATA                     public       postgres    false    201   �"                 0    16407    items 
   TABLE DATA                     public       postgres    false    199   #                  0    16431    orders 
   TABLE DATA                     public       postgres    false    203   �#                 0    16396    users 
   TABLE DATA                     public       postgres    false    197   �#       +           0    0    carts_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.carts_id_seq', 23, true);
            public       postgres    false    200            ,           0    0    items_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.items_id_seq', 38, true);
            public       postgres    false    198            -           0    0    orders_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.orders_id_seq', 17, true);
            public       postgres    false    202            .           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 38, true);
            public       postgres    false    196            �
           2606    16423    carts carts_pk 
   CONSTRAINT     L   ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_pk PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.carts DROP CONSTRAINT carts_pk;
       public         postgres    false    201            �
           2606    16415    items item_pk 
   CONSTRAINT     K   ALTER TABLE ONLY public.items
    ADD CONSTRAINT item_pk PRIMARY KEY (id);
 7   ALTER TABLE ONLY public.items DROP CONSTRAINT item_pk;
       public         postgres    false    199            �
           2606    16436    orders orders_pk 
   CONSTRAINT     N   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pk;
       public         postgres    false    203            �
           2606    16404    users users_pk 
   CONSTRAINT     L   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pk;
       public         postgres    false    197            �
           2606    16424    carts carts_users_fk    FK CONSTRAINT     s   ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_users_fk FOREIGN KEY (user_id) REFERENCES public.users(id);
 >   ALTER TABLE ONLY public.carts DROP CONSTRAINT carts_users_fk;
       public       postgres    false    197    2710    201            �
           2606    16442    orders orders_carts_fk    FK CONSTRAINT     u   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_carts_fk FOREIGN KEY (cart_id) REFERENCES public.carts(id);
 @   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_carts_fk;
       public       postgres    false    2714    203    201            �
           2606    16437    orders orders_items_fk    FK CONSTRAINT     u   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_items_fk FOREIGN KEY (item_id) REFERENCES public.items(id);
 @   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_items_fk;
       public       postgres    false    2712    203    199               b   x���v
Q���W((M��L�KN,*)V��L�QH.JM,��ϋ/��Mrs�S�¥ũE�)�
a�>���
�:
F�&`d :
%E�@����\\\ <�         x   x���v
Q���W((M��L��,I�-V��L�Q 1��SRu�s�dAQfr��B��O�k����������������:�sa�6_�p���֋Mv((�&�$��+e��4���� �6'�          
   x���             p   x���v
Q���W((M��L�+-N-*V��L�Q��O���Q(H,..�/
�e���%��(�&�祀9�
a�>���
�:
��%@ԁC#cSud����uMk... z�%7     