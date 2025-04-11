INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES 
-- Administrador
('Carlos', 'pwd123', '2024-04-01', 'CC', '1003004001', 'Calle 1 #10', '3111002001', 'carlos@gmail.com', '1985-02-10', 'Soltero', 'Colombiano', 'Administrador de empresas', 'Masculino', 'P010', (SELECT id FROM rol WHERE nombre = 'Administrador')),
('Diana', 'pwd456', '2024-04-02', 'CC', '1003004002', 'Calle 2 #20', '3111002002', 'diana@gmail.com', '1988-03-12', 'Casada', 'Colombiana', 'Contadora publica', 'Femenino', 'P011', (SELECT id FROM rol WHERE nombre = 'Administrador')),
('Luis', 'pwd789', '2024-04-03', 'CC', '1003004003', 'Calle 3 #30', '3111002003', 'luis@gmail.com', '1982-07-01', 'Soltero', 'Colombiano', 'Coordinador academico', 'Masculino', 'P012', (SELECT id FROM rol WHERE nombre = 'Administrador')),
('Marta', 'pwd101', '2024-04-04', 'CC', '1003004004', 'Calle 4 #40', '3111002004', 'marta@gmail.com', '1990-06-21', 'Casada', 'Colombiana', 'Asistente de direccion', 'Femenino', 'P013', (SELECT id FROM rol WHERE nombre = 'Administrador')),
('Jorge', 'pwd202', '2024-04-05', 'CC', '1003004005', 'Calle 5 #50', '3111002005', 'jorge@gmail.com', '1980-11-30', 'Divorciado', 'Colombiano', 'Tecnico Administrador', 'Masculino', 'P014', (SELECT id FROM rol WHERE nombre = 'Administrador')),

-- DOCENTE
('Andres', 'pwd301', '2024-04-06', 'CC', '1003004006', 'Calle 6 #60', '3111002006', 'andres@gmail.com', '1979-01-15', 'Casado', 'Colombiano', 'Profesor titular', 'Masculino', 'P015', (SELECT id FROM rol WHERE nombre = 'Docente')),
('Natalia', 'pwd302', '2024-04-07', 'CC', '1003004007', 'Calle 7 #70', '3111002007', 'natalia@gmail.com', '1984-08-20', 'Soltera', 'Colombiana', 'Investigadora', 'Femenino', 'P016', (SELECT id FROM rol WHERE nombre = 'Docente')),
('Pedro', 'pwd303', '2024-04-08', 'CC', '1003004008', 'Calle 8 #80', '3111002008', 'pedro@gmail.com', '1975-05-22', 'Viudo', 'Colombiano', 'Jefe de departamento', 'Masculino', 'P017', (SELECT id FROM rol WHERE nombre = 'Docente')),
('Laura', 'pwd304', '2024-04-09', 'CC', '1003004009', 'Calle 9 #90', '3111002009', 'laura@gmail.com', '1992-12-01', 'Casada', 'Colombiana', 'Tutora academica', 'Femenino', 'P018', (SELECT id FROM rol WHERE nombre = 'Docente')),
('Sergio', 'pwd305', '2024-04-10', 'CC', '1003004010', 'Calle 10 #100', '3111002010', 'sergio@gmail.com', '1987-03-05', 'Soltero', 'Colombiano', 'Catedratico', 'Masculino', 'P019', (SELECT id FROM rol WHERE nombre = 'Docente')),

-- ESTUDIANTE
('Camila', 'pwd401', '2024-04-11', 'TI', '1003004011', 'Calle 11 #110', '3111002011', 'camila@gmail.com', '2001-07-15', 'Soltera', 'Colombiana', 'Estudiante de sistemas', 'Femenino', 'P020', (SELECT id FROM rol WHERE nombre = 'Estudiante')),
('David', 'pwd402', '2024-04-12', 'TI', '1003004012', 'Calle 12 #120', '3111002012', 'david@gmail.com', '2000-09-22', 'Soltero', 'Colombiano', 'Estudiante de electronica', 'Masculino', 'P021', (SELECT id FROM rol WHERE nombre = 'Estudiante')),
('Juliana', 'pwd403', '2024-04-13', 'TI', '1003004013', 'Calle 13 #130', '3111002013', 'juliana@gmail.com', '2002-11-03', 'Soltera', 'Colombiana', 'Estudiante de derecho', 'Femenino', 'P022', (SELECT id FROM rol WHERE nombre = 'Estudiante')),
('Esteban', 'pwd404', '2024-04-14', 'TI', '1003004014', 'Calle 14 #140', '3111002014', 'esteban@gmail.com', '1999-04-30', 'Soltero', 'Colombiano', 'Estudiante de contaduria', 'Masculino', 'P023', (SELECT id FROM rol WHERE nombre = 'Estudiante')),
('Valeria', 'pwd405', '2024-04-15', 'TI', '1003004015', 'Calle 15 #150', '3111002015', 'valeria@gmail.com', '2001-01-09', 'Soltera', 'Colombiana', 'Estudiante de arquitectura', 'Femenino', 'P024', (SELECT id FROM rol WHERE nombre = 'Estudiante')),

-- Empleado
('Hector', 'pwd501', '2024-04-16', 'CC', '1003004016', 'Calle 16 #160', '3111002016', 'hector@gmail.com', '1995-10-10', 'Casado', 'Colombiano', 'Ingeniero industrial', 'Masculino', 'P025', (SELECT id FROM rol WHERE nombre = 'Empleado')),
('Luisa', 'pwd502', '2024-04-17', 'CC', '1003004017', 'Calle 17 #170', '3111002017', 'luisa@gmail.com', '1994-03-03', 'Soltera', 'Colombiana', 'Abogada', 'Femenino', 'P026', (SELECT id FROM rol WHERE nombre = 'Empleado')),
('Felipe', 'pwd503', '2024-04-18', 'CC', '1003004018', 'Calle 18 #180', '3111002018', 'felipe@gmail.com', '1993-06-25', 'Casado', 'Colombiano', 'Administrador financiero', 'Masculino', 'P027', (SELECT id FROM rol WHERE nombre = 'Empleado')),
('Isabel', 'pwd504', '2024-04-19', 'CC', '1003004019', 'Calle 19 #190', '3111002019', 'isabel@gmail.com', '1991-09-18', 'Casada', 'Colombiana', 'Psicologa', 'Femenino', 'P028', (SELECT id FROM rol WHERE nombre = 'Empleado')),
('Oscar', 'pwd505', '2024-04-20', 'CC', '1003004020', 'Calle 20 #200', '3111002020', 'oscar@gmail.com', '1990-02-22', 'Divorciado', 'Colombiano', 'Diseñador grafico', 'Masculino', 'P029', (SELECT id FROM rol WHERE nombre = 'Empleado'));
