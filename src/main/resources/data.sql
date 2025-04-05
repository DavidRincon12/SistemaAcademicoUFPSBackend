-- Insertar roles
INSERT INTO rol (nombre, permisos) VALUES ('Administrador', 'ALL');
INSERT INTO rol (nombre, permisos) VALUES ('Docente', 'READ_WRITE');
INSERT INTO rol (nombre, permisos) VALUES ('Estudiante', 'READ');
INSERT INTO rol (nombre, permisos) VALUES ('Invitado', 'LIMITED');

-- Insertar personas
INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Ana', '12345', '2024-03-31', 'CC', '1002003001', 'Cra 45 #22', '3134567890', 'ana@example.com',
    '1999-12-31', 'Soltera', 'Colombiana', 'Ingeniera', 'Femenino', 'P001',
    (SELECT id FROM rol WHERE nombre = 'Estudiante')
);

INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Carlos', '12345', '2024-03-31', 'CC', '1002003002', 'Cra 45 #22', '3134567891', 'carlos@example.com',
    '1990-05-12', 'Soltero', 'Colombiano', 'Profesor', 'Masculino', 'P002',
    (SELECT id FROM rol WHERE nombre = 'Docente')
);
