insert into cargos (pk_cargo, nombre, descripcion, sueldo) values (1, 'Administrador', 'Este es un puesto muy importante', 500);
insert into cargos (pk_cargo, nombre, descripcion, sueldo) values (2, 'Gerente', 'Este es un puesto muy importante', 500);
insert into cargos (pk_cargo, nombre, descripcion, sueldo) values (3, 'Peon', 'Este es un puesto muy importante', 500);
insert into cargos (pk_cargo, nombre, descripcion, sueldo) values (4, 'Lastre', 'Este es un puesto muy importante', 500);
insert into cargos (pk_cargo, nombre, descripcion, sueldo) values (5, 'Recursos Humanos', 'Este es un puesto muy importante', 500);
insert into cargos (pk_cargo, nombre, descripcion, sueldo) values (6, 'Alumno', 'Este es un puesto muy importante', 500);
insert into trabajadores (pk_trabajador, nombres, apellidos, cedula, nacionalidad, fecha_nacimiento, telefono, email, estado_civil, fk_cargo) values (1, 'Luis David ', 'Martinez Udeo', '0504066358', 'Ecuatoriano', '2020-10-05', '09832342266', 'hola@adios.com', 'Casado',1);
insert into trabajadores (pk_trabajador, nombres, apellidos, cedula, nacionalidad, fecha_nacimiento, telefono, email, estado_civil, fk_cargo) values (2, 'Raul Francisco ', 'Perez Lopez', '0504066358', 'Aleman', '2020-10-05', '09832342266', 'hola@adios.com', 'Casado',1);
insert into trabajadores (pk_trabajador, nombres, apellidos, cedula, nacionalidad, fecha_nacimiento, telefono, email, estado_civil, fk_cargo) values (3, 'Horacio Gustabo', 'Palacios Gutierrez', '0504066358', 'Cubano', '2020-10-05', '09832342266', 'hola@adios.com', 'Casado',1);
insert into trabajadores (pk_trabajador, nombres, apellidos, cedula, nacionalidad, fecha_nacimiento, telefono, email, estado_civil, fk_cargo) values (4, 'Ronald Francisco ', 'Mallorga Serrano', '0504066358', 'Argentino', '2020-10-05', '09832342266', 'hola@adios.com', 'Casado',1);
insert into trabajadores (pk_trabajador, nombres, apellidos, cedula, nacionalidad, fecha_nacimiento, telefono, email, estado_civil, fk_cargo) values (5, 'Ana Lucia', 'Bastidas Jaramillo', '0504066358', 'Sueco', '2020-10-05', '09832342266', 'hola@adios.com', 'Soltero',1);
insert into penalidades (pk_penalidad, nombre, descripcion, monto) values(1, 'P001', 'LLegar tarde ', 50);
insert into penalidades (pk_penalidad, nombre, descripcion, monto) values(2, 'P002', 'No trabajar', 20);
insert into penalidades (pk_penalidad, nombre, descripcion, monto) values(3, 'P003', 'Distraccion en horario de trabajo', 10);
insert into penalidades (pk_penalidad, nombre, descripcion, monto) values(4, 'P004', 'Hurto de materiales', 500);
insert into penalidades (pk_penalidad, nombre, descripcion, monto) values(5, 'P005', 'Traicion', 800);
