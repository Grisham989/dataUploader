-- Table: authors
CREATE TABLE authors
(
  first_name character varying(32),
  last_name character varying(32),
  pseudonym character varying(32),
  code character varying(32) NOT NULL,
  CONSTRAINT "AUTHORS_pkey" PRIMARY KEY (code)
)
WITH (
  OIDS=FALSE
);
 -- Table: books
CREATE TABLE books
(
  author_code character varying(32) NOT NULL,
  code character varying(32) NOT NULL,
  title character varying(32) NOT NULL,
  description character varying(255),
  price character varying(32),
  isbn character varying(32) NOT NULL,
  CONSTRAINT books_pkey PRIMARY KEY (code),
  CONSTRAINT book_author_code_fkey FOREIGN KEY (author_code)
      REFERENCES authors (code) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT books_code_isbn_key UNIQUE (code, isbn)
)
WITH (
  OIDS=FALSE
);
-- Table: customers
CREATE TABLE customers
(
  code character varying(32) NOT NULL,
  bar_code character varying(32) NOT NULL,
  first_name character varying(32),
  last_name character varying(32),
  pesel character varying(10),
  phone character varying(10),
  address character varying(32),
  CONSTRAINT customers_pkey PRIMARY KEY (code),
  CONSTRAINT "customers_code_barCode_pesel_phone_key" UNIQUE (code, bar_code, pesel, phone)
)
WITH (
  OIDS=FALSE
);
-- Table: orders
CREATE TABLE orders
(
  customer_code character varying(32) NOT NULL,
  book_code character varying(32) NOT NULL,
  status character varying(32),
  order_date date NOT NULL,
  id integer NOT NULL,
  CONSTRAINT orders_pkey PRIMARY KEY (id),
  CONSTRAINT "ORDERS_book_code_fkey" FOREIGN KEY (book_code)
      REFERENCES books (code) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "ORDERS_customer_code_fkey" FOREIGN KEY (customer_code)
      REFERENCES customers (code) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "ORDERS_book_code_customer_code_order_date_key" UNIQUE (book_code, customer_code, order_date)
)
WITH (
  OIDS=FALSE
);

CREATE SEQUENCE id_seq;
ALTER TABLE orders ALTER COLUMN id SET DEFAULT nextval('id_seq');
ALTER TABLE orders ALTER COLUMN id SET NOT NULL;
ALTER SEQUENCE id_seq OWNED BY orders.id;







  