CREATE TABLE public.mst_stock
(
    stock_id            int4 NOT NULL,
    additional_info     text NULL,
    created_at          timestamp(6) NULL,
    created_by          varchar(255) NULL,
    jumlah_stock_barang int4 NULL,
    gambar_barang       text NULL,
    nama_barang         varchar(255) NULL,
    no_seri_barang      varchar(255) NULL,
    updated_at          timestamp(6) NULL,
    updated_by          varchar(255) NULL,
    CONSTRAINT mst_stock_pkey PRIMARY KEY (stock_id)
);