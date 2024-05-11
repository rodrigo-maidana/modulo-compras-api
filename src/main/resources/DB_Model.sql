/*==============================================================*/
/* DBMS name:      modulo_compras                               */
/* Created on:     26/4/2024 22:54:24                           */
/*==============================================================*/

CREATE DATABASE modulo_compras;
DROP DATABASE modulo_compras;

use modulo_compras;

/*==============================================================*/
/* Table: categorias                                            */
/*==============================================================*/
CREATE TABLE categorias
(
   id_categoria   	INT AUTO_INCREMENT,
   str_nombre     	VARCHAR(60) NOT NULL,
   bool_eliminado	BOOLEAN NULL DEFAULT FALSE,
   PRIMARY KEY (id_categoria)
);

/* Inserts para categorías */
INSERT INTO categorias (str_nombre) VALUES ('Herramientas');
INSERT INTO categorias (str_nombre) VALUES ('Pinturas');
INSERT INTO categorias (str_nombre) VALUES ('Electricidad');
INSERT INTO categorias (str_nombre) VALUES ('Carpintería');
INSERT INTO categorias (str_nombre) VALUES ('Jardinería');


/*==============================================================*/
/* Table: marcas                                                */
/*==============================================================*/
CREATE TABLE marcas
(
   id_marca       	INT AUTO_INCREMENT,
   str_nombre     	VARCHAR(200) NOT NULL,
   bool_eliminado   BOOLEAN NULL DEFAULT FALSE,
   PRIMARY KEY (id_marca)
);

/* Inserts para marcas */
INSERT INTO marcas (str_nombre) VALUES ('Black & Decker');
INSERT INTO marcas (str_nombre) VALUES ('Bosch');
INSERT INTO marcas (str_nombre) VALUES ('DeWalt');
INSERT INTO marcas (str_nombre) VALUES ('Stanley');
INSERT INTO marcas (str_nombre) VALUES ('Makita');
INSERT INTO marcas (str_nombre) VALUES ('Milwaukee');
INSERT INTO marcas (str_nombre) VALUES ('Hitachi');
INSERT INTO marcas (str_nombre) VALUES ('Festool');


/*==============================================================*/
/* Table: depositos                                             */
/*==============================================================*/
CREATE TABLE depositos
(
   id_deposito    	INT AUTO_INCREMENT,
   str_nombre     	VARCHAR(60) NOT NULL,
   str_direccion  	VARCHAR(200) NOT NULL,
   str_contacto   	VARCHAR(60) NOT NULL,
   bool_eliminado   BOOLEAN NULL DEFAULT FALSE,
   PRIMARY KEY (id_deposito)
);

INSERT INTO depositos (str_nombre, str_direccion, str_contacto) VALUES ('Depósito Central', 'Calle Principal 123', '0981123456');
INSERT INTO depositos (str_nombre, str_direccion, str_contacto) VALUES ('Depósito Secundario', 'Calle Secundaria 456', '0971345678');

/*==============================================================*/
/* Table: productos                                             */
/*==============================================================*/
CREATE TABLE productos
(
   id_producto          INT AUTO_INCREMENT,
   fk_id_marca          INT,
   fk_id_categoria      INT,
   str_descripcion      VARCHAR(60),
   bool_eliminado   	BOOLEAN NULL DEFAULT FALSE,
   PRIMARY KEY (id_producto),
   FOREIGN KEY (fk_id_marca) REFERENCES marcas(id_marca),
   FOREIGN KEY (fk_id_categoria) REFERENCES categorias(id_categoria)
);

/* Inserts para productos */
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (1, 1, 'Taladro percutor 20V');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (2, 2, 'Sierra circular 1500W');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (3, 3, 'Juego de destornilladores 6 piezas');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (4, 4, 'Broca para madera 8mm');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (5, 5, 'Manguera de jardín 15m');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (6, 1, 'Lijadora orbital 200W');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (7, 2, 'Martillo antirebote');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (8, 3, 'Tijeras de podar ergonómicas');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (1, 4, 'Pintura latex blanco 20L');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (2, 5, 'Guantes de trabajo de cuero');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (3, 1, 'Gafas de seguridad anti-impacto');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (4, 2, 'Caja de herramientas 200 piezas');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (5, 3, 'Regadera de metal 5L');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (6, 4, 'Rodillo para pintura profesional');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (7, 5, 'Espátula de acero inoxidable 15cm');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (8, 1, 'Enchufe eléctrico universal');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (1, 2, 'Sierra de mano para madera');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (2, 3, 'Fertilizante universal 5kg');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (3, 4, 'Sellador acrílico 300ml');
INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion) VALUES (4, 5, 'Bisagra de puerta de acero 10cm');


/*==============================================================*/
/* Table: proveedores                                           */
/*==============================================================*/
CREATE TABLE proveedores
(
   id_proveedor   	INT AUTO_INCREMENT,
   str_nombre     	VARCHAR(60) NOT NULL,
   str_ruc        	VARCHAR(60) NOT NULL,
   str_contacto   	VARCHAR(60) NOT NULL,
   str_correo     	VARCHAR(60) NOT NULL,
   str_direccion  	VARCHAR(200) NOT NULL,
   bool_eliminado 	BOOLEAN NULL DEFAULT FALSE,
   PRIMARY KEY (id_proveedor)
);

INSERT INTO proveedores (str_nombre, str_ruc, str_contacto, str_correo, str_direccion) VALUES ('Ferretería Industrial S.A.', '80020000-5', '0981123456', 'contacto@ferreindustrial.com.py', 'Avenida Industrial 789');
INSERT INTO proveedores (str_nombre, str_ruc, str_contacto, str_correo, str_direccion) VALUES ('Pinturas y Solventes S.R.L.', '80025555-7', '0971345678', 'ventas@pintysol.com.py', 'Calle Pintor 321');

/*==============================================================*/
/* Table: pedidos_compra                                        */
/*==============================================================*/
CREATE TABLE pedidos_compra
(
   id_pedido_compra 	INT AUTO_INCREMENT,
   date_fecha_emision 	DATE,
   str_estado 			VARCHAR(60),
   bool_eliminado   	BOOLEAN NULL DEFAULT FALSE,
   PRIMARY KEY (id_pedido_compra)
);

INSERT INTO pedidos_compra (date_fecha_emision, str_estado) VALUES ('2024-01-15', 'En proceso');
INSERT INTO pedidos_compra (date_fecha_emision, str_estado) VALUES ('2024-01-20', 'Completado');

/*==============================================================*/
/* Table: pedidos_detalles                                      */
/*==============================================================*/
CREATE TABLE pedidos_detalles
(
   id_pedido_detalle 	INT AUTO_INCREMENT,
   fk_id_pedido_compra 	INT,
   fk_id_producto 		INT,
   int_cantidad 		INT,
   bool_eliminado  		BOOLEAN NULL DEFAULT FALSE,
   PRIMARY KEY (id_pedido_detalle),
   FOREIGN KEY (fk_id_pedido_compra) REFERENCES pedidos_compra(id_pedido_compra),
   FOREIGN KEY (fk_id_producto) REFERENCES productos(id_producto)
);

INSERT INTO pedidos_detalles (fk_id_pedido_compra, fk_id_producto, int_cantidad) VALUES (1, 1, 10);
INSERT INTO pedidos_detalles (fk_id_pedido_compra, fk_id_producto, int_cantidad) VALUES (1, 2, 5);
INSERT INTO pedidos_detalles (fk_id_pedido_compra, fk_id_producto, int_cantidad) VALUES (2, 3, 20);

/*PENDIENTE DE IMPLEMENTAR API*/

/*==============================================================*/
/* Table: detalles_categorias_proveedor                         */
/*==============================================================*/
create table detalles_categorias_proveedor
(
   id_detalle_categoria_proveedor int not null,
   fk_id_proveedor      int,
   fk_id_categoria      int,
   primary key (id_detalle_categoria_proveedor)
);

/*==============================================================*/
/* Table: detalles_orden_pago                                   */
/*==============================================================*/
create table detalles_orden_pago
(
   id_detalle_orden_pago int not null,
   fk_id_nota_credito   int,
   fk_id_orden_pago     int,
   fk_id_metodo_pago    int,
   dec_monto            decimal,
   primary key (id_detalle_orden_pago)
);

/*==============================================================*/
/* Table: detalles_pedido_devolucion                            */
/*==============================================================*/
create table detalles_pedido_devolucion
(
   id_detalle_pedido_devolucion int not null,
   fk_id_pedido_devolucion int,
   fk_id_producto       int,
   fk_id_estado_producto_devuelto int,
   int_cantidad         int,
   primary key (id_detalle_pedido_devolucion)
);

/*==============================================================*/
/* Table: detalle_orden_compra                                  */
/*==============================================================*/
create table detalle_orden_compra
(
   id_detalle_orden_compra int not null,
   fk_id_orden_compra   int,
   id_producto          int,
   int_cantidad         int,
   dec_precio           decimal,
   primary key (id_detalle_orden_compra)
);

/*==============================================================*/
/* Table: estados_productos_devueltos                           */
/*==============================================================*/
create table estados_productos_devueltos
(
   id_estado_producto_devuelto int not null,
   str_descripcion      varchar(60),
   primary key (id_estado_producto_devuelto)
);

/*==============================================================*/
/* Table: facturas                                              */
/*==============================================================*/
create table facturas
(
   fk_id_factura        int not null,
   fk_id_proveedor      int,
   fk_id_orden_pago     int,
   date_fecha_emision   date,
   date_fecha_vencimiento date,
   str_timbrado         varchar(60),
   str_ruc              varchar(60),
   str_condicion        varchar(20),
   str_estado           varchar(20),
   dec_monto_total      decimal,
   dec_saldo_pendiente  decimal,
   primary key (fk_id_factura)
);

/*==============================================================*/
/* Table: facturas_detalles                                     */
/*==============================================================*/
create table facturas_detalles
(
   id_compra_detalle    int not null,
   fk_id_producto       int,
   fk_id_factura        int,
   int_cantidad         int,
   dec_precio_unitario  decimal,
   dec_porcentaje_iva   decimal,
   primary key (id_compra_detalle)
);



/*==============================================================*/
/* Table: metodos_pago                                          */
/*==============================================================*/
create table metodos_pago
(
   id_metodo_pago       int auto_increment primary key,
   str_nombre           varchar(60)
);

/*==============================================================*/
/* Table: notas_creditos                                        */
/*==============================================================*/
create table notas_creditos
(
   id_nota_credito      int not null,
   fk_id_proveedor      int,
   fk_id_factura        int,
   date_fecha_emision   date,
   str_ruc              varchar(60),
   str_estado           varchar(20),
   dec_monto_total      decimal,
   dec_saldo_pendiente  decimal,
   primary key (id_nota_credito)
);

/*==============================================================*/
/* Table: nota_creditos_detalles                                */
/*==============================================================*/
create table nota_creditos_detalles
(
   id_nota_credito_detalles int not null,
   fk_id_nota_credito   int,
   str_descripcion      varchar(60),
   int_cantidad         int,
   dec_precio_unitario  decimal,
   dec_porcentaje_iva   decimal,
   primary key (id_nota_credito_detalles)
);

/*==============================================================*/
/* Table: ordenes_pago                                          */
/*==============================================================*/
create table ordenes_pago
(
   id_orden_pago        int not null,
   date_fecha_pago      date,
   str_estado           varchar(20),
   dec_total            decimal,
   primary key (id_orden_pago)
);

/*==============================================================*/
/* Table: orden_compra                                          */
/*==============================================================*/
create table orden_compra
(
   id_orden_compra      int not null,
   fk_id_proveedor      int,
   fk_id_deposito       int,
   date_fecha_emision   date,
   str_estado           varchar(20),
   primary key (id_orden_compra)
);

/*==============================================================*/
/* Table: pedidos_cotizacion                                    */
/*==============================================================*/
create table pedidos_cotizacion
(
   id_pedido_cotizacion int not null,
   fk_id_proveedor      int,
   fk_id_pedido_de_compra int,
   date_fecha_emision   date,
   str_estado           varchar(20),
   primary key (id_pedido_cotizacion)
);

/*==============================================================*/
/* Table: pedidos_cotizacion_detalles                           */
/*==============================================================*/
create table pedidos_cotizacion_detalles
(
   id_pedido_cotizacion_detalle int not null,
   fk_id_pedido_cotizacion int,
   fk_id_producto       int,
   int_cantidad         int,
   dec_costo_unitario   decimal,
   primary key (id_pedido_cotizacion_detalle)
);

/*==============================================================*/
/* Table: pedidos_devoluciones                                  */
/*==============================================================*/
create table pedidos_devoluciones
(
   id_pedido_devolucion int not null,
   fk_id_factura        int,
   date_fecha_emision   date,
   str_motivo_devolucion varchar(200),
   str_estado           varchar(20),
   primary key (id_pedido_devolucion)
);
