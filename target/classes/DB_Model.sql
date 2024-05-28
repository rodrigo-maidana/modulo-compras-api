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
CREATE TABLE `categorias` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `bool_eliminado` bit(1) NOT NULL,
  `str_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_categoria`)
)

/*==============================================================*/
/* Table: marcas                                                */
/*==============================================================*/
CREATE TABLE `marcas` (
  `id_marca` int NOT NULL AUTO_INCREMENT,
  `bool_eliminado` bit(1) DEFAULT NULL,
  `str_nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_marca`)
);

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
