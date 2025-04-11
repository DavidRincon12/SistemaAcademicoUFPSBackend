
INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES 
('Carlos', 'pwd123', '2024-04-01', 'CC', '1003004001', 'Calle 1 #10', '3111002001', 'carlos@ufps.edu.co', '1985-02-10', 'Soltero', 'Colombiano', 'Administrador de empresas', 'Masculino', 'P010', (SELECT id FROM rol WHERE nombre = 'Empleado')),
('Diana', 'pwd456', '2024-04-02', 'CC', '1003004002', 'Calle 2 #20', '3111002002', 'diana@ufps.edu.co', '1988-03-12', 'Casada', 'Colombiana', 'Contadora publica', 'Femenino', 'P011', (SELECT id FROM rol WHERE nombre = 'Empleado')),
('Luis', 'pwd789', '2024-04-03', 'CC', '1003004003', 'Calle 3 #30', '3111002003', 'luis@ufps.edu.co', '1982-07-01', 'Soltero', 'Colombiano', 'Coordinador academico', 'Masculino', 'P012', (SELECT id FROM rol WHERE nombre = 'Empleado')),
('Marta', 'pwd101', '2024-04-04', 'CC', '1003004004', 'Calle 4 #40', '3111002004', 'marta@ufps.edu.co', '1990-06-21', 'Casada', 'Colombiana', 'Asistente de direccion', 'Femenino', 'P013', (SELECT id FROM rol WHERE nombre = 'Empleado')),
('Jorge', 'pwd202', '2024-04-05', 'CC', '1003004005', 'Calle 5 #50', '3111002005', 'jorge@ufps.edu.co', '1980-11-30', 'Divorciado', 'Colombiano', 'Tecnico Empleado', 'Masculino', 'P014', (SELECT id FROM rol WHERE nombre = 'Empleado'));
