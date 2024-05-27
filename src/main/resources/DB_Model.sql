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
   bool_eliminado	BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY (id_categoria)
);

show tables;

/* Inserts para categorías */
INSERT INTO categorias (str_nombre, bool_eliminado) VALUES
('Herramientas Manuales', FALSE),
('Herramientas Eléctricas', FALSE),
('Materiales de Construcción', FALSE),
('Pinturas y Accesorios', FALSE),
('Plomería', FALSE),
('Electricidad', FALSE),
('Jardinería', FALSE),
('Ferretería General', FALSE),
('Tornillería', FALSE),
('Fijaciones y Anclajes', FALSE),
('Cerrajería', FALSE),
('Automatización', FALSE),
('Carpintería', FALSE),
('Seguridad Industrial', FALSE),
('Equipos de Protección Personal', FALSE),
('Iluminación', FALSE),
('Químicos', FALSE),
('Soldadura', FALSE),
('Climatización', FALSE),
('Fontanería', FALSE),
('Adhesivos y Selladores', FALSE),
('Accesorios de Baño', FALSE),
('Accesorios de Cocina', FALSE),
('Muebles y Estanterías', FALSE),
('Puertas y Ventanas', FALSE),
('Ventilación', FALSE),
('Riego y Drenaje', FALSE),
('Maquinaria Pesada', FALSE),
('Automotriz y Transporte', FALSE),
('Artículos de Limpieza', FALSE);



/*==============================================================*/
/* Table: marcas                                                */
/*==============================================================*/
CREATE TABLE marcas
(
   id_marca       	INT AUTO_INCREMENT,
   str_nombre     	VARCHAR(200) NOT NULL,
   bool_eliminado   BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY (id_marca)
);

/* Inserts para marcas */
INSERT INTO marcas (str_nombre, bool_eliminado) VALUES
('Bosch', FALSE),
('Makita', FALSE),
('DeWalt', FALSE),
('Black & Decker', FALSE),
('Stanley', FALSE),
('Hitachi', FALSE),
('Metabo', FALSE),
('Milwaukee', FALSE),
('Hilti', FALSE),
('Ryobi', FALSE),
('Skil', FALSE),
('Einhell', FALSE),
('Craftsman', FALSE),
('Ridgid', FALSE),
('Dremel', FALSE),
('Porter-Cable', FALSE),
('Festool', FALSE),
('Klein Tools', FALSE),
('Snap-on', FALSE),
('Irwin', FALSE),
('Husky', FALSE),
('Senco', FALSE),
('Paslode', FALSE),
('Simpson Strong-Tie', FALSE),
('Estwing', FALSE),
('Wera', FALSE),
('Knipex', FALSE),
('Leatherman', FALSE),
('Bahco', FALSE),
('Wiha', FALSE);

/*==============================================================*/
/* Table: productos                                             */
/*==============================================================*/
CREATE TABLE productos
(
   id_producto          INT AUTO_INCREMENT,
   fk_id_marca          INT,
   fk_id_categoria      INT,
   str_descripcion      VARCHAR(60),
   bool_eliminado   	BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY (id_producto),
   FOREIGN KEY (fk_id_marca) REFERENCES marcas(id_marca),
   FOREIGN KEY (fk_id_categoria) REFERENCES categorias(id_categoria)
);

INSERT INTO productos (fk_id_marca, fk_id_categoria, str_descripcion, bool_eliminado) VALUES
(1, 1, 'Taladro Bosch Profesional', FALSE),
(2, 2, 'Atornillador Inalámbrico Makita', FALSE),
(3, 3, 'Cemento DeWalt 25kg', FALSE),
(4, 4, 'Pintura Acrílica Black & Decker 1L', FALSE),
(5, 5, 'Llave de Paso Stanley', FALSE),
(6, 6, 'Cable Eléctrico Hitachi 100m', FALSE),
(7, 7, 'Tijeras de Jardín Metabo', FALSE),
(8, 8, 'Cinta Métrica Milwaukee 5m', FALSE),
(9, 9, 'Tornillo de Madera Hilti 50mm', FALSE),
(10, 10, 'Anclaje Químico Ryobi', FALSE),
(11, 11, 'Candado de Seguridad Skil', FALSE),
(12, 12, 'Kit de Automatización Einhell', FALSE),
(13, 13, 'Sierra de Carpintero Craftsman', FALSE),
(14, 14, 'Casco de Seguridad Ridgid', FALSE),
(15, 15, 'Guantes de Protección Dremel', FALSE),
(16, 16, 'Lámpara LED Porter-Cable', FALSE),
(17, 17, 'Desengrasante Festool 500ml', FALSE),
(18, 18, 'Máscara de Soldadura Klein Tools', FALSE),
(19, 19, 'Aire Acondicionado Snap-on', FALSE),
(20, 20, 'Tubería de Cobre Irwin 10m', FALSE),
(21, 21, 'Silicona Selladora Husky', FALSE),
(22, 22, 'Espejo de Baño Senco', FALSE),
(23, 23, 'Grifo de Cocina Paslode', FALSE),
(24, 24, 'Estantería de Metal Simpson Strong-Tie', FALSE),
(25, 25, 'Puerta de Madera Estwing', FALSE),
(26, 26, 'Ventilador de Techo Wera', FALSE),
(27, 27, 'Sistema de Riego Knipex', FALSE),
(28, 28, 'Excavadora Leatherman', FALSE),
(29, 29, 'Aceite para Motor Bahco 1L', FALSE),
(30, 30, 'Escoba de Limpieza Wiha', FALSE),
(1, 2, 'Lijadora Bosch', FALSE),
(2, 1, 'Martillo Makita', FALSE),
(3, 4, 'Rodillo de Pintura DeWalt', FALSE),
(4, 3, 'Ladrillo Black & Decker', FALSE),
(5, 6, 'Enchufe Stanley', FALSE),
(6, 5, 'Válvula de Agua Hitachi', FALSE),
(7, 7, 'Corta Césped Metabo', FALSE),
(8, 8, 'Cerradura Milwaukee', FALSE),
(9, 9, 'Tornillo para Concreto Hilti', FALSE),
(10, 10, 'Espárrago Ryobi', FALSE),
(11, 11, 'Llave Allen Skil', FALSE),
(12, 12, 'Kit de Automatización Einhell Pro', FALSE),
(13, 13, 'Sierra Circular Craftsman', FALSE),
(14, 14, 'Protector Auditivo Ridgid', FALSE),
(15, 15, 'Máscara de Protección Dremel', FALSE),
(16, 16, 'Tira LED Porter-Cable', FALSE),
(17, 17, 'Desoxidante Festool 500ml', FALSE),
(18, 18, 'Guantes de Soldador Klein Tools', FALSE),
(19, 19, 'Ventana de PVC Snap-on', FALSE),
(20, 20, 'Manguera de PVC Irwin 20m', FALSE),
(21, 21, 'Cemento Contacto Husky', FALSE),
(22, 22, 'Porta Toallas Senco', FALSE),
(23, 23, 'Fregadero de Acero Inoxidable Paslode', FALSE),
(24, 24, 'Estante de Pared Simpson Strong-Tie', FALSE),
(25, 25, 'Ventana de Madera Estwing', FALSE),
(26, 26, 'Extractor de Aire Wera', FALSE),
(27, 27, 'Bomba de Agua Knipex', FALSE),
(28, 28, 'Bulldozer Leatherman', FALSE),
(29, 29, 'Lubricante Multiusos Bahco 1L', FALSE),
(30, 30, 'Paño de Limpieza Wiha', FALSE),
(1, 3, 'Cinta Aislante Bosch', FALSE),
(2, 4, 'Brocha Makita', FALSE),
(3, 5, 'Llave Inglesa DeWalt', FALSE),
(4, 6, 'Interruptor Black & Decker', FALSE),
(5, 7, 'Podadora Stanley', FALSE),
(6, 8, 'Taladro de Impacto Hitachi', FALSE),
(7, 9, 'Tornillo de Seguridad Metabo', FALSE),
(8, 10, 'Arandela Milwaukee', FALSE),
(9, 11, 'Candado Hilti', FALSE),
(10, 12, 'Control de Automatización Ryobi', FALSE),
(11, 13, 'Cepillo de Carpintero Skil', FALSE),
(12, 14, 'Protector Facial Einhell', FALSE),
(13, 15, 'Guantes de Látex Craftsman', FALSE),
(14, 16, 'Foco LED Ridgid', FALSE),
(15, 17, 'Limpiador de Contactos Dremel', FALSE),
(16, 18, 'Máscara para Soldar Porter-Cable', FALSE),
(17, 19, 'Ventana de Aluminio Festool', FALSE),
(18, 20, 'Tubo de PVC Klein Tools 10m', FALSE),
(19, 21, 'Adhesivo Epóxico Snap-on', FALSE),
(20, 22, 'Espejo con Luz Irwin', FALSE),
(21, 23, 'Grifo Monomando Husky', FALSE),
(22, 24, 'Estante de Metal Senco', FALSE),
(23, 25, 'Puerta de Hierro Paslode', FALSE),
(24, 26, 'Ventilador de Pared Simpson Strong-Tie', FALSE),
(25, 27, 'Aspersor de Jardín Estwing', FALSE),
(26, 28, 'Excavadora Pequeña Wera', FALSE),
(27, 29, 'Aceite para Cadena Knipex 1L', FALSE),
(28, 30, 'Cepillo de Barrer Leatherman', FALSE),
(29, 1, 'Cemento Blanco Bahco 25kg', FALSE),
(30, 2, 'Jabón para Manos Wiha', FALSE);

/*==============================================================*/
/* Table: depositos                                             */
/*==============================================================*/
CREATE TABLE depositos
(
   id_deposito    	INT AUTO_INCREMENT,
   str_nombre     	VARCHAR(60) NOT NULL,
   str_direccion  	VARCHAR(200) NOT NULL,
   str_contacto   	VARCHAR(60) NOT NULL,
   bool_eliminado   BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY (id_deposito)
);

INSERT INTO depositos (str_nombre, str_direccion, str_contacto, bool_eliminado) VALUES
('Depósito Central', 'Calle Principal 123, Ciudad', '0981123456', FALSE),
('Depósito Norte', 'Av. Norte 456, Ciudad', '0982234567', FALSE),
('Depósito Sur', 'Calle Sur 789, Ciudad', '0983345678', FALSE),
('Depósito Este', 'Av. Este 101, Ciudad', '0984456789', FALSE),
('Depósito Oeste', 'Calle Oeste 202, Ciudad', '0985567890', FALSE),
('Depósito A', 'Calle A 303, Ciudad', '0986678901', FALSE),
('Depósito B', 'Av. B 404, Ciudad', '0987789012', FALSE),
('Depósito C', 'Calle C 505, Ciudad', '0988890123', FALSE),
('Depósito D', 'Av. D 606, Ciudad', '0989901234', FALSE),
('Depósito E', 'Calle E 707, Ciudad', '0981012345', FALSE),
('Depósito F', 'Av. F 808, Ciudad', '0982123456', FALSE),
('Depósito G', 'Calle G 909, Ciudad', '0983234567', FALSE),
('Depósito H', 'Av. H 1010, Ciudad', '0984345678', FALSE),
('Depósito I', 'Calle I 1111, Ciudad', '0985456789', FALSE),
('Depósito J', 'Av. J 1212, Ciudad', '0986567890', FALSE),
('Depósito K', 'Calle K 1313, Ciudad', '0987678901', FALSE),
('Depósito L', 'Av. L 1414, Ciudad', '0988789012', FALSE),
('Depósito M', 'Calle M 1515, Ciudad', '0989890123', FALSE),
('Depósito N', 'Av. N 1616, Ciudad', '0980901234', FALSE),
('Depósito O', 'Calle O 1717, Ciudad', '0981012345', FALSE),
('Depósito P', 'Av. P 1818, Ciudad', '0982123456', FALSE),
('Depósito Q', 'Calle Q 1919, Ciudad', '0983234567', FALSE),
('Depósito R', 'Av. R 2020, Ciudad', '0984345678', FALSE),
('Depósito S', 'Calle S 2121, Ciudad', '0985456789', FALSE),
('Depósito T', 'Av. T 2222, Ciudad', '0986567890', FALSE),
('Depósito U', 'Calle U 2323, Ciudad', '0987678901', FALSE),
('Depósito V', 'Av. V 2424, Ciudad', '0988789012', FALSE),
('Depósito W', 'Calle W 2525, Ciudad', '0989890123', FALSE),
('Depósito X', 'Av. X 2626, Ciudad', '0980901234', FALSE),
('Depósito Y', 'Calle Y 2727, Ciudad', '0981012345', FALSE);

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
   bool_eliminado 	BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY (id_proveedor)
);

INSERT INTO proveedores (str_nombre, str_ruc, str_contacto, str_correo, str_direccion, bool_eliminado) VALUES
('Ferretería López', '80000001-1', '0981123456', 'contacto@ferreterialopez.com', 'Calle Principal 123, Ciudad', FALSE),
('Materiales González', '80000002-2', '0982234567', 'ventas@materialesgonzalez.com', 'Av. Norte 456, Ciudad', FALSE),
('Construcciones Martínez', '80000003-3', '0983345678', 'info@construccionesmartinez.com', 'Calle Sur 789, Ciudad', FALSE),
('Suministros Pérez', '80000004-4', '0984456789', 'contacto@suministrosperez.com', 'Av. Este 101, Ciudad', FALSE),
('Herramientas Sánchez', '80000005-5', '0985567890', 'ventas@herramientassanchez.com', 'Calle Oeste 202, Ciudad', FALSE),
('Electricidad Ruiz', '80000006-6', '0986678901', 'info@electricidadruiz.com', 'Calle A 303, Ciudad', FALSE),
('Plomería Fernández', '80000007-7', '0987789012', 'contacto@plomeriafernandez.com', 'Av. B 404, Ciudad', FALSE),
('Pinturas Gómez', '80000008-8', '0988890123', 'ventas@pinturasgomez.com', 'Calle C 505, Ciudad', FALSE),
('Cerrajería Torres', '80000009-9', '0989901234', 'info@cerrajeriatorres.com', 'Av. D 606, Ciudad', FALSE),
('Automatización Ramírez', '80000010-0', '0981012345', 'contacto@automatizacionramirez.com', 'Calle E 707, Ciudad', FALSE),
('Carpintería Díaz', '80000011-1', '0982123456', 'ventas@carpinteriadiaz.com', 'Av. F 808, Ciudad', FALSE),
('Seguridad Industrial Vega', '80000012-2', '0983234567', 'info@seguridadindustrialvega.com', 'Calle G 909, Ciudad', FALSE),
('Iluminación Mendoza', '80000013-3', '0984345678', 'contacto@iluminacionmendoza.com', 'Av. H 1010, Ciudad', FALSE),
('Químicos Ortega', '80000014-4', '0985456789', 'ventas@quimicosortega.com', 'Calle I 1111, Ciudad', FALSE),
('Soldadura Romero', '80000015-5', '0986567890', 'info@soldaduraromero.com', 'Av. J 1212, Ciudad', FALSE),
('Fontanería Herrera', '80000016-6', '0987678901', 'contacto@fontaneriaherrera.com', 'Calle K 1313, Ciudad', FALSE),
('Adhesivos Vázquez', '80000017-7', '0988789012', 'ventas@adhesivosvazquez.com', 'Av. L 1414, Ciudad', FALSE),
('Accesorios de Baño Castro', '80000018-8', '0989890123', 'info@accesoriosdebanocastro.com', 'Calle M 1515, Ciudad', FALSE),
('Accesorios de Cocina Morales', '80000019-9', '0980901234', 'contacto@accesoriosdecocinamorales.com', 'Av. N 1616, Ciudad', FALSE),
('Muebles y Estanterías Peña', '80000020-0', '0981012345', 'ventas@mueblesyestanteriaspena.com', 'Calle O 1717, Ciudad', FALSE),
('Puertas y Ventanas Castillo', '80000021-1', '0982123456', 'info@puertasventanascastillo.com', 'Av. P 1818, Ciudad', FALSE),
('Ventilación Ríos', '80000022-2', '0983234567', 'contacto@ventilacionrios.com', 'Calle Q 1919, Ciudad', FALSE),
('Riego y Drenaje Guzmán', '80000023-3', '0984345678', 'ventas@riegoedrenajeguzman.com', 'Av. R 2020, Ciudad', FALSE),
('Maquinaria Pesada Flores', '80000024-4', '0985456789', 'info@maquinariapesadaflores.com', 'Calle S 2121, Ciudad', FALSE),
('Automotriz y Transporte Medina', '80000025-5', '0986567890', 'contacto@automotriztransporte.com', 'Av. T 2222, Ciudad', FALSE),
('Artículos de Limpieza Silva', '80000026-6', '0987678901', 'ventas@articulosdelimpiezasilva.com', 'Calle U 2323, Ciudad', FALSE),
('Ferretería Navarro', '80000027-7', '0988789012', 'info@ferreterianavarro.com', 'Av. V 2424, Ciudad', FALSE),
('Materiales Ortega', '80000028-8', '0989890123', 'contacto@materialesortega.com', 'Calle W 2525, Ciudad', FALSE),
('Suministros Rodríguez', '80000029-9', '0980901234', 'ventas@suministrosrodriguez.com', 'Av. X 2626, Ciudad', FALSE),
('Herramientas Jiménez', '80000030-0', '0981012345', 'info@herramientasjimenez.com', 'Calle Y 2727, Ciudad', FALSE);

/*==============================================================*/
/* Table: pedidos_compra                                        */
/*==============================================================*/
CREATE TABLE pedidos_compra
(
   id_pedido_compra 	INT AUTO_INCREMENT,
   date_fecha_emision 	DATE,
   str_estado 			VARCHAR(60),
   bool_eliminado   	BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY (id_pedido_compra)
);

-- Insertando pedidos de compra
INSERT INTO pedidos_compra (date_fecha_emision, str_estado, bool_eliminado) VALUES
('2024-01-01', 'Pendiente', FALSE),
('2024-01-02', 'Procesando', FALSE),
('2024-01-03', 'Completado', FALSE),
('2024-01-04', 'Cancelado', FALSE),
('2024-01-05', 'Pendiente', FALSE),
('2024-01-06', 'Procesando', FALSE),
('2024-01-07', 'Completado', FALSE),
('2024-01-08', 'Cancelado', FALSE),
('2024-01-09', 'Pendiente', FALSE),
('2024-01-10', 'Procesando', FALSE);

/*==============================================================*/
/* Table: pedidos_detalles                                      */
/*==============================================================*/
CREATE TABLE pedidos_detalles
(
   id_pedido_detalle 	INT AUTO_INCREMENT,
   fk_id_pedido_compra 	INT,
   fk_id_producto 		INT,
   int_cantidad 		INT,
   bool_eliminado  		BOOLEAN NOT NULL DEFAULT FALSE,
   PRIMARY KEY (id_pedido_detalle),
   FOREIGN KEY (fk_id_pedido_compra) REFERENCES pedidos_compra(id_pedido_compra),
   FOREIGN KEY (fk_id_producto) REFERENCES productos(id_producto)
);

-- Insertando detalles de pedidos
INSERT INTO pedidos_detalles (fk_id_pedido_compra, fk_id_producto, int_cantidad, bool_eliminado) VALUES
(1, 1, 10, FALSE),
(1, 2, 5, FALSE),
(1, 3, 8, FALSE),
(1, 4, 12, FALSE),
(2, 5, 7, FALSE),
(2, 6, 14, FALSE),
(2, 7, 6, FALSE),
(2, 8, 9, FALSE),
(3, 9, 4, FALSE),
(3, 10, 3, FALSE),
(3, 11, 11, FALSE),
(3, 12, 10, FALSE),
(4, 13, 8, FALSE),
(4, 14, 5, FALSE),
(4, 15, 7, FALSE),
(4, 16, 6, FALSE),
(5, 17, 9, FALSE),
(5, 18, 4, FALSE),
(5, 19, 5, FALSE),
(5, 20, 8, FALSE),
(6, 21, 6, FALSE),
(6, 22, 7, FALSE),
(6, 23, 8, FALSE),
(6, 24, 5, FALSE),
(7, 25, 12, FALSE),
(7, 26, 14, FALSE),
(7, 27, 10, FALSE),
(7, 28, 9, FALSE),
(8, 29, 11, FALSE),
(8, 30, 13, FALSE),
(8, 1, 7, FALSE),
(8, 2, 6, FALSE),
(9, 3, 8, FALSE),
(9, 4, 10, FALSE),
(9, 5, 9, FALSE),
(9, 6, 11, FALSE),
(10, 7, 14, FALSE),
(10, 8, 12, FALSE),
(10, 9, 10, FALSE),
(10, 10, 8, FALSE);

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
