/*==============================================================*/
/* DBMS name:      modulo_compras                               */
/* Created on:     26/4/2024 22:54:24                           */
/*==============================================================*/

CREATE DATABASE modulo_compras;
DROP DATABASE modulo_compras;

use modulo_compras;

SHOW CREATE TABLE categorias;
SHOW CREATE TABLE marcas;
SHOW CREATE TABLE productos;

SHOW CREATE TABLE depositos;

SHOW CREATE TABLE proveedores;
SHOW CREATE TABLE proveedores_categorias;

SHOW CREATE TABLE pedidos_compra;
SHOW CREATE TABLE pedidos_detalles;

SHOW CREATE TABLE cotizaciones;
SHOW CREATE TABLE cotizacion_detalles;
SHOW CREATE TABLE users;
SHOW CREATE TABLE users_seq;


/*==============================================================*/
/* Table: categorias                                            */
/*==============================================================*/
CREATE TABLE `categorias` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `bool_eliminado` bit(1) NOT NULL,
  `str_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_categoria`)
)

/*Insert categorias*/
INSERT INTO `categorias` (`id_categoria`, `bool_eliminado`, `str_nombre`) VALUES
(1, b'0', 'Herramientas Manuales'),
(2, b'0', 'Herramientas Eléctricas'),
(3, b'0', 'Materiales de Construcción'),
(4, b'0', 'Pinturas y Accesorios'),
(5, b'0', 'Jardinería');


/*==============================================================*/
/* Table: marcas                                                */
/*==============================================================*/
CREATE TABLE `marcas` (
  `id_marca` int NOT NULL AUTO_INCREMENT,
  `bool_eliminado` bit(1) DEFAULT NULL,
  `str_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_marca`)
);

/*Insert marcas*/
INSERT INTO `marcas` (`id_marca`, `bool_eliminado`, `str_nombre`) VALUES
(1, b'0', 'Stanley'),
(2, b'0', 'Bosch'),
(3, b'0', 'Makita'),
(4, b'0', 'DeWalt'),
(5, b'0', 'Truper');

/*==============================================================*/
/* Table: productos                                             */
/*==============================================================*/
CREATE TABLE `productos` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `str_descripcion` varchar(255) NOT NULL,
  `bool_eliminado` bit(1) NOT NULL,
  `fk_id_categoria` int NOT NULL,
  `fk_id_marca` int NOT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `FKh8p9ls86vpm6bugkl95nqqhsw` (`fk_id_categoria`),
  KEY `FKscb203xo285vyjkjncnaq6ojt` (`fk_id_marca`),
  CONSTRAINT `FKh8p9ls86vpm6bugkl95nqqhsw` FOREIGN KEY (`fk_id_categoria`) REFERENCES `categorias` (`id_categoria`),
  CONSTRAINT `FKscb203xo285vyjkjncnaq6ojt` FOREIGN KEY (`fk_id_marca`) REFERENCES `marcas` (`id_marca`)
);

/*Insert productos*/
INSERT INTO `productos` (`id_producto`, `str_descripcion`, `bool_eliminado`, `fk_id_categoria`, `fk_id_marca`) VALUES
(1, 'Martillo de Garra Stanley', b'0', 1, 1),
(2, 'Taladro Inalámbrico Bosch', b'0', 2, 2),
(3, 'Sierra Circular Makita', b'0', 2, 3),
(4, 'Pintura Acrílica DeWalt', b'0', 4, 4),
(5, 'Tijeras de Jardín Truper', b'0', 5, 5),
(6, 'Destornillador Plano Stanley', b'0', 1, 1),
(7, 'Lijadora Orbital Bosch', b'0', 2, 2),
(8, 'Esmeriladora Angular Makita', b'0', 2, 3),
(9, 'Brocha para Pintura DeWalt', b'0', 4, 4),
(10, 'Rastrillo Truper', b'0', 5, 5),
(11, 'Alicates de Corte Stanley', b'0', 1, 1),
(12, 'Atornillador Eléctrico Bosch', b'0', 2, 2),
(13, 'Rotomartillo Makita', b'0', 2, 3),
(14, 'Rodillo para Pintura DeWalt', b'0', 4, 4),
(15, 'Podadora de Césped Truper', b'0', 5, 5);


/*==============================================================*/
/* Table: depositos                                             */
/*==============================================================*/
CREATE TABLE `depositos` (
  `id_deposito` int NOT NULL AUTO_INCREMENT,
  `str_contacto` varchar(255) NOT NULL,
  `str_direccion` varchar(255) NOT NULL,
  `bool_eliminado` bit(1) NOT NULL,
  `str_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_deposito`)
);

/*Insert depositos*/
INSERT INTO `depositos` (`id_deposito`, `str_contacto`, `str_direccion`, `bool_eliminado`, `str_nombre`) VALUES
(1, '0981123456', 'Avenida Principal 123', b'0', 'Depósito Central'),
(2, '0982234567', 'Calle Secundaria 456', b'0', 'Depósito Norte'),
(3, '0983345678', 'Boulevard Industrial 789', b'0', 'Depósito Sur');

/*==============================================================*/
/* Table: proveedores                                           */
/*==============================================================*/
CREATE TABLE `proveedores` (
  `id_proveedor` int NOT NULL AUTO_INCREMENT,
  `str_contacto` varchar(255) DEFAULT NULL,
  `str_correo` varchar(255) DEFAULT NULL,
  `str_direccion` varchar(255) DEFAULT NULL,
  `bool_eliminado` bit(1) NOT NULL,
  `str_nombre` varchar(255) NOT NULL,
  `str_ruc` varchar(255) NOT NULL,
  PRIMARY KEY (`id_proveedor`)
)

/*Insert proveedores*/
INSERT INTO `proveedores` (`id_proveedor`, `str_contacto`, `str_correo`, `str_direccion`, `bool_eliminado`, `str_nombre`, `str_ruc`) VALUES
(1, '0984456789', 'contacto1@empresa.com', 'Calle Comercial 123', b'0', 'Ferretería ABC S.A.', '80012345-6'),
(2, '0985567890', 'contacto2@empresa.com', 'Avenida Industrial 456', b'0', 'Herramientas y Más S.R.L.', '80023456-7'),
(3, '0986678901', 'contacto3@empresa.com', 'Boulevard de los Negocios 789', b'0', 'Construcciones XYZ Ltda.', '80034567-8'),
(4, '0987789012', 'contacto4@empresa.com', 'Parque Empresarial 101', b'0', 'Materiales y Servicios S.A.', '80045678-9'),
(5, '0988890123', 'contacto5@empresa.com', 'Centro Comercial 202', b'0', 'Suministros Integrales S.R.L.', '80056789-0');


/*==============================================================*/
/* Table: proveedores_categorias                                */
/*==============================================================*/
CREATE TABLE `proveedores_categorias` (
  `id_proveedor_categoria` int NOT NULL AUTO_INCREMENT,
  `id_categoria` int NOT NULL,
  `id_proveedor` int NOT NULL,
  PRIMARY KEY (`id_proveedor_categoria`),
  KEY `FKephrpguqqhg4rglabnj2ihvt0` (`id_categoria`),
  KEY `FKhxldlutxikdj3vnnj0ps57da4` (`id_proveedor`),
  CONSTRAINT `FKephrpguqqhg4rglabnj2ihvt0` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`),
  CONSTRAINT `FKhxldlutxikdj3vnnj0ps57da4` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`)
)

/*Insert proveedores_categorias*/
INSERT INTO `proveedores_categorias` (`id_proveedor_categoria`, `id_categoria`, `id_proveedor`) VALUES
(1, 1, 1), -- Ferretería ABC S.A. provee Herramientas Manuales
(2, 2, 1), -- Ferretería ABC S.A. provee Herramientas Eléctricas
(3, 3, 2), -- Herramientas y Más S.R.L. provee Materiales de Construcción
(4, 4, 2), -- Herramientas y Más S.R.L. provee Pinturas y Accesorios
(5, 5, 3), -- Construcciones XYZ Ltda. provee Jardinería
(6, 1, 4), -- Materiales y Servicios S.A. provee Herramientas Manuales
(7, 2, 4), -- Materiales y Servicios S.A. provee Herramientas Eléctricas
(8, 3, 5), -- Suministros Integrales S.R.L. provee Materiales de Construcción
(9, 4, 5), -- Suministros Integrales S.R.L. provee Pinturas y Accesorios
(10, 5, 5); -- Suministros Integrales S.R.L. provee Jardinería

/*==============================================================*/
/* Table: pedidos_compra                                        */
/*==============================================================*/
CREATE TABLE `pedidos_compra` (
  `id_pedido_compra` int NOT NULL AUTO_INCREMENT,
  `bool_eliminado` bit(1) NOT NULL,
  `str_estado` varchar(255) NOT NULL,
  `date_fecha_emision` datetime(6) NOT NULL,
  `str_nro_pedido` varchar(255) NOT NULL,
  PRIMARY KEY (`id_pedido_compra`),
  UNIQUE KEY `UK_mkk34arjsixfk5evdgo7fwyp8` (`str_nro_pedido`)
)

/*Insert pedidos_compra*/
INSERT INTO `pedidos_compra` (`id_pedido_compra`, `bool_eliminado`, `str_estado`, `date_fecha_emision`, `str_nro_pedido`) VALUES
(1, b'0', 'Pendiente', '2024-05-01 10:00:00.000000', 'PC-20240501-001'),
(2, b'0', 'Completado', '2024-05-02 11:30:00.000000', 'PC-20240502-002'),
(3, b'0', 'Pendiente', '2024-05-03 14:15:00.000000', 'PC-20240503-003'),
(4, b'0', 'Completado', '2024-05-04 09:45:00.000000', 'PC-20240504-004'),
(5, b'0', 'Pendiente', '2024-05-05 16:20:00.000000', 'PC-20240505-005');

/*==============================================================*/
/* Table: pedidos_detalles                                      */
/*==============================================================*/
CREATE TABLE `pedidos_detalles` (
  `id_pedido_detalle` int NOT NULL AUTO_INCREMENT,
  `int_cantidad` int NOT NULL,
  `bool_eliminado` bit(1) NOT NULL,
  `fk_id_pedido_compra` int NOT NULL,
  `fk_id_producto` int NOT NULL,
  PRIMARY KEY (`id_pedido_detalle`),
  KEY `FKl97gkdg4pnpt9l6o7wuycihi6` (`fk_id_pedido_compra`),
  KEY `FKmi8foa46g8hvmwp4mmavhpmxl` (`fk_id_producto`),
  CONSTRAINT `FKl97gkdg4pnpt9l6o7wuycihi6` FOREIGN KEY (`fk_id_pedido_compra`) REFERENCES `pedidos_compra` (`id_pedido_compra`),
  CONSTRAINT `FKmi8foa46g8hvmwp4mmavhpmxl` FOREIGN KEY (`fk_id_producto`) REFERENCES `productos` (`id_producto`)
)

/*Insert pedidos_detalles*/
INSERT INTO `pedidos_detalles` (`id_pedido_detalle`, `int_cantidad`, `bool_eliminado`, `fk_id_pedido_compra`, `fk_id_producto`) VALUES
(1, 10, b'0', 1, 1), -- Pedido 1, Producto 1 (Martillo de Garra Stanley)
(2, 5, b'0', 1, 2), -- Pedido 1, Producto 2 (Taladro Inalámbrico Bosch)
(3, 8, b'0', 2, 3), -- Pedido 2, Producto 3 (Sierra Circular Makita)
(4, 15, b'0', 2, 4), -- Pedido 2, Producto 4 (Pintura Acrílica DeWalt)
(5, 12, b'0', 3, 5), -- Pedido 3, Producto 5 (Tijeras de Jardín Truper)
(6, 20, b'0', 3, 6), -- Pedido 3, Producto 6 (Destornillador Plano Stanley)
(7, 7, b'0', 4, 7), -- Pedido 4, Producto 7 (Lijadora Orbital Bosch)
(8, 9, b'0', 4, 8), -- Pedido 4, Producto 8 (Esmeriladora Angular Makita)
(9, 6, b'0', 5, 9), -- Pedido 5, Producto 9 (Brocha para Pintura DeWalt)
(10, 10, b'0', 5, 10); -- Pedido 5, Producto 10 (Rastrillo Truper)

/*==============================================================*/
/* Table: cotizaciones                                          */
/*==============================================================*/
CREATE TABLE `cotizaciones` (
  `id_cotizacion` int NOT NULL AUTO_INCREMENT,
  `bool_eliminado` bit(1) NOT NULL,
  `str_estado` varchar(255) NOT NULL,
  `date_fecha_emision` datetime(6) NOT NULL,
  `str_nro_cotizacion` varchar(255) NOT NULL,
  `fk_id_pedido_compra` int NOT NULL,
  `fk_id_proveedor` int NOT NULL,
  PRIMARY KEY (`id_cotizacion`),
  UNIQUE KEY `UK_6cwqdeyhq2t7cbcucre39o2ad` (`str_nro_cotizacion`),
  KEY `FKf60rgqqrjfljob850gww6qx90` (`fk_id_pedido_compra`),
  KEY `FKllurbxbwdrpi09nd2ff8wwyuh` (`fk_id_proveedor`),
  CONSTRAINT `FKf60rgqqrjfljob850gww6qx90` FOREIGN KEY (`fk_id_pedido_compra`) REFERENCES `pedidos_compra` (`id_pedido_compra`),
  CONSTRAINT `FKllurbxbwdrpi09nd2ff8wwyuh` FOREIGN KEY (`fk_id_proveedor`) REFERENCES `proveedores` (`id_proveedor`)
)

/*==============================================================*/
/* Table: cotizacion_detalles                                   */
/*==============================================================*/
CREATE TABLE `cotizacion_detalles` (
  `id_pedido_detalle` int NOT NULL AUTO_INCREMENT,
  `int_cantidad` int NOT NULL,
  `bool_eliminado` bit(1) NOT NULL,
  `dec_precio_unitario` double NOT NULL,
  `fk_id_cotizacion` int NOT NULL,
  `fk_id_producto` int NOT NULL,
  PRIMARY KEY (`id_pedido_detalle`),
  KEY `FKhpl84uep1s3d92nubvl16kjv6` (`fk_id_cotizacion`),
  KEY `FKjpf5fkehx6n5ju7pn580jrw84` (`fk_id_producto`),
  CONSTRAINT `FKhpl84uep1s3d92nubvl16kjv6` FOREIGN KEY (`fk_id_cotizacion`) REFERENCES `cotizaciones` (`id_cotizacion`),
  CONSTRAINT `FKjpf5fkehx6n5ju7pn580jrw84` FOREIGN KEY (`fk_id_producto`) REFERENCES `productos` (`id_producto`)
)


/*PENDIENTE DE IMPLEMENTAR API*/

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
