CREATE DATABASE modulo_compras;
DROP DATABASE modulo_compras;

use modulo_compras;

/*Insert categorias*/
INSERT INTO `categorias` (`id_categoria`, `bool_eliminado`, `str_nombre`) VALUES
(1, b'0', 'Herramientas Manuales'),
(2, b'0', 'Herramientas Eléctricas'),
(3, b'0', 'Materiales de Construcción'),
(4, b'0', 'Pinturas y Accesorios'),
(5, b'0', 'Jardinería');

/*Insert marcas*/
INSERT INTO `marcas` (`id_marca`, `bool_eliminado`, `str_nombre`) VALUES
(1, b'0', 'Stanley'),
(2, b'0', 'Bosch'),
(3, b'0', 'Makita'),
(4, b'0', 'DeWalt'),
(5, b'0', 'Truper');

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

/*Insert depositos*/
INSERT INTO `depositos` (`id_deposito`, `str_contacto`, `str_direccion`, `bool_eliminado`, `str_nombre`) VALUES
(1, '0981123456', 'Avenida Principal 123', b'0', 'Depósito Central'),
(2, '0982234567', 'Calle Secundaria 456', b'0', 'Depósito Norte'),
(3, '0983345678', 'Boulevard Industrial 789', b'0', 'Depósito Sur');

/*Insert proveedores*/
INSERT INTO `proveedores` (`id_proveedor`, `str_contacto`, `str_correo`, `str_direccion`, `bool_eliminado`, `str_nombre`, `str_ruc`) VALUES
(1, '0984456789', 'contacto1@empresa.com', 'Calle Comercial 123', b'0', 'Ferretería ABC S.A.', '80012345-6'),
(2, '0985567890', 'contacto2@empresa.com', 'Avenida Industrial 456', b'0', 'Herramientas y Más S.R.L.', '80023456-7'),
(3, '0986678901', 'contacto3@empresa.com', 'Boulevard de los Negocios 789', b'0', 'Construcciones XYZ Ltda.', '80034567-8'),
(4, '0987789012', 'contacto4@empresa.com', 'Parque Empresarial 101', b'0', 'Materiales y Servicios S.A.', '80045678-9'),
(5, '0988890123', 'contacto5@empresa.com', 'Centro Comercial 202', b'0', 'Suministros Integrales S.R.L.', '80056789-0');

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

/*Insert pedidos_compra*/
INSERT INTO `pedidos_compra` (`id_pedido_compra`, `bool_eliminado`, `str_estado`, `date_fecha_emision`, `str_nro_pedido`) VALUES
(1, b'0', 'Pendiente', '2024-05-01 10:00:00.000000', 'PC-20240501-001'),
(2, b'0', 'Completado', '2024-05-02 11:30:00.000000', 'PC-20240502-002'),
(3, b'0', 'Pendiente', '2024-05-03 14:15:00.000000', 'PC-20240503-003'),
(4, b'0', 'Completado', '2024-05-04 09:45:00.000000', 'PC-20240504-004'),
(5, b'0', 'Pendiente', '2024-05-05 16:20:00.000000', 'PC-20240505-005');

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