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

-- Persona: Laura
INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Laura', '45678', '2024-03-28', 'CC', '1002003005', 'Calle 8 #20-30', '3109876543', 'laura@example.com',
    '1982-03-22', 'Casada', 'Colombiana', 'Especialista en Telecomunicaciones', 'Femenino', 'P005',
    (SELECT id FROM rol WHERE nombre = 'Docente')
);

-- Docente: Laura
INSERT INTO docente (
    correo_institucional, tipo, persona_id
) VALUES (
    'laura.prof@ufps.edu.co', 'Tiempo Completo',
    (SELECT id FROM persona WHERE nombre = 'Laura')
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

-- Persona: Andrés
INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Andres', '67890', '2024-03-27', 'CC', '1002003006', 'Diagonal 11 #33', '3115556677', 'andres@example.com',
    '1980-08-10', 'Soltero', 'Colombiano', 'Ingeniero de Software', 'Masculino', 'P006',
    (SELECT id FROM rol WHERE nombre = 'Docente')
);

-- Docente: Andrés
INSERT INTO docente (
    correo_institucional, tipo, persona_id
) VALUES (
    'andres.prof@ufps.edu.co', 'Catedrático',
    (SELECT id FROM persona WHERE nombre = 'Andres')
);


-- Insertar docentes (relacionados con personas previamente creadas o nuevas)

-- Persona 1: Carlos (ya insertado arriba)
INSERT INTO docente (
    correo_institucional, tipo, persona_id
) VALUES (
    'carlos.prof@ufps.edu.co', 'Tiempo Completo',
    (SELECT id FROM persona WHERE id = 2)
);

-- Persona 2: María
INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Maria', '54321', '2024-03-30', 'TI', '1002003003', 'Calle 10 #5-20', '3123456789', 'maria@example.com',
    '1985-06-15', 'Casada', 'Colombiana', 'Magister en Educación', 'Femenino', 'P003',
    (SELECT id FROM rol WHERE nombre = 'Docente')
);

INSERT INTO docente (
    correo_institucional, tipo, persona_id
) VALUES (
    'maria.prof@ufps.edu.co', 'Catedrático',
    (SELECT id FROM persona WHERE nombre = 'Maria')
);

-- Persona 3: Juan
INSERT INTO persona (
    nombre, contrasena, fecha_registro, tipo_documento, numero_documento,
    direccion, telefono, correo, fecha_nacimiento, estado_civil,
    nacionalidad, datos_profesionales, genero, codigo, rol_id
) VALUES (
    'Juan', '98765', '2024-03-29', 'CC', '1002003004', 'Av. Principal #10', '3112233445', 'juan@example.com',
    '1978-11-03', 'Casado', 'Colombiano', 'PhD en Ciencias', 'Masculino', 'P004',
    (SELECT id FROM rol WHERE nombre = 'Docente')
);

INSERT INTO docente (
    correo_institucional, tipo, persona_id
) VALUES (
    'juan.prof@ufps.edu.co', 'Tiempo Completo',
    (SELECT id FROM persona WHERE nombre = 'Juan')
);

-- Carlos (Docente id = 1)
INSERT INTO HORARIOASESORIA (dia_semana, hora_inicio, hora_fin, docente_id)
VALUES 
('MONDAY', '08:00:00', '10:00:00', 1),
('WEDNESDAY', '14:00:00', '16:00:00', 1);

-- María (Docente id = 2)
INSERT INTO HORARIOASESORIA (dia_semana, hora_inicio, hora_fin, docente_id)
VALUES 
('FRIDAY', '09:00:00', '11:00:00', 2),
('TUESDAY', '13:30:00', '15:00:00', 2);

-- Juan (Docente id = 3)
INSERT INTO HORARIOASESORIA (dia_semana, hora_inicio, hora_fin, docente_id)
VALUES 
('THURSDAY', '10:00:00', '12:00:00', 3),
('MONDAY', '15:00:00', '17:00:00', 3);

-- Facultad de Ingeniería (decano: Carlos, docente_id = 1)
INSERT INTO facultad (nombre, decano_id)
VALUES ('Facultad de Ingeniería', 1);

-- Facultad de Educación (decano: María, docente_id = 2)
INSERT INTO facultad (nombre, decano_id)
VALUES ('Facultad de Educación', 2);

-- Facultad de Ciencias (decano: Juan, docente_id = 3)
INSERT INTO facultad (nombre, decano_id)
VALUES ('Facultad de Ciencias', 3);

-- Programa 1
INSERT INTO programa (nombre, codigo, duracion, registro_snies, director_id, facultad_id)
VALUES ('Ingeniería de Sistemas', 'IS123', '10 semestres', 'SNIES45678', 1, 1);

-- Programa 2
INSERT INTO programa (nombre, codigo, duracion, registro_snies, director_id, facultad_id)
VALUES ('Licenciatura en Educación Infantil', 'LEI456', '9 semestres', 'SNIES78901', 2, 2);

-- Programa 3
INSERT INTO programa (nombre, codigo, duracion, registro_snies, director_id, facultad_id)
VALUES ('Ciencias Naturales', 'CN789', '8 semestres', 'SNIES23456', 3, 3);

-- Programa 4
INSERT INTO programa (nombre, codigo, duracion, registro_snies, director_id, facultad_id)
VALUES ('Ingeniería Electrónica', 'IE987', '10 semestres', 'SNIES11223', 4, 1);

-- Programa 5
INSERT INTO programa (nombre, codigo, duracion, registro_snies, director_id, facultad_id)
VALUES ('Educación Física', 'EF321', '8 semestres', 'SNIES44556', 5, 2);
