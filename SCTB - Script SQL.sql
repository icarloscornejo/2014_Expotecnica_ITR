create database SCTBlocal
go

alter database SCTBlocal set compatibility_level = 120
go

use SCTBlocal
go

if not exists(select * from sys.sql_logins where name = N'SCTB')
begin
    create login SCTB with password = 'prototypeC1',
	default_database = SCTB,
	default_language = Spanish
end
go

if not exists(select * from sys.database_principals where name = N'SCTB')
begin
    create user SCTB for login SCTB
    exec sp_addrolemember  N'db_owner', N'SCTB'
end
go

set dateformat dmy;
go

--Creacion de esquemas para guardar orden

create schema tbl --Esquema que contendra todas las tablas
go

create schema viw --Esquema que contendra todas las vistas de consultas
go

create schema pcd --Esquema que contendra todos los procedimientos
go

create schema hlp --Esquema que contendra funciones
go

create schema tgr --Esquema que contendra triggers
go

--Creacion de tablas por jerarquia

--Primera parte
create table tbl.Quioskos(
id_quiosko int not null identity primary key,
nom_quiosko varchar(50),
dir_quiosko varchar(100)
)

create table tbl.TiposdeServicios(
id_tipservicio int not null identity primary key,
nom_tipservicio varchar(50),
des_tipservicio varchar(100)
)

create table tbl.Departamentos(
id_departamento int not null identity primary key,
nom_departamento varchar(50)
)

create table tbl.Sucursales(
id_sucursal int not null identity primary key,
nom_sucursal varchar(50),
dir_sucursal varchar(100),
id_departamento int,
foreign key (id_departamento) references tbl.Departamentos(id_departamento) on delete cascade on update cascade
)

--Segunda parte
create table tbl.DatosdeEmpresas(
id_datempresa int not null identity primary key,
nom_datempresa varchar(50),
tel_datempresa varchar(9),
dir_datempresa varchar(100)
)

create table tbl.CargosdeTrabajos(
id_cargotrabajo int not null identity primary key,
nom_cargotrabajo varchar(50)
)

create table tbl.EstadosCiviles(
id_estadocivil int not null identity primary key,
nom_estadocivil varchar(50)
)

create table tbl.Generos(
id_genero int not null identity primary key,
nom_genero varchar(30)
)

--Tercera parte
create table tbl.TiposdePersonas(
id_tippersona int not null identity primary key,
nom_tippersona varchar(50),
des_tippersona varchar(100)
)

create table tbl.Personas(
id_persona int not null identity primary key,
nom_persona varchar(50),
ape_persona varchar(50),
correo_persona varchar(50),
feccreacion_persona date,
id_tippersona int,
foreign key (id_tippersona) references tbl.TiposdePersonas(id_tippersona) on delete cascade on update cascade
)

create table tbl.bitSucursales(
id_bitsucursal int not null identity primary key,
id_persona int,
id_sucursal int,
foreign key (id_persona) references tbl.Personas(id_persona) on delete cascade on update cascade,
constraint bitpersona unique(id_persona),
foreign key (id_sucursal) references tbl.Sucursales(id_sucursal) on delete cascade on update cascade
)

create table tbl.DatosdeTrabajos(
id_dattrabajo int not null identity primary key,
salario_dattrabajo money,
id_datempresa int,
id_cargotrabajo int,
id_persona int,
foreign key (id_datempresa) references tbl.DatosdeEmpresas(id_datempresa) on delete cascade on update cascade,
foreign key (id_cargotrabajo) references tbl.CargosdeTrabajos(id_cargotrabajo) on delete cascade on update cascade,
foreign key (id_persona) references tbl.Personas(id_persona) on delete cascade on update cascade,
constraint persona unique(id_persona)
)

create table tbl.DatosdePersonas(
id_datpersona int not null identity primary key,
dui_datpersona varbinary(500),
nit_datpersona varbinary(500),
dir_datpersona varchar(100),
telcel_datpersona varchar(9),
telcasa_datpersona varchar(9),
id_genero int,
id_estadocivil int,
id_persona int,
foreign key (id_genero) references tbl.Generos(id_genero) on delete cascade on update cascade,
foreign key (id_estadocivil) references tbl.EstadosCiviles(id_estadocivil) on delete cascade on update cascade,
foreign key (id_persona) references tbl.Personas(id_persona),
constraint persona2 unique(id_persona)
)

create table tbl.Usuarios(
id_usuario int not null identity primary key,
usu_usuario varbinary(500),
contra_usuario varbinary(500),
id_persona int,
foreign key (id_persona) references tbl.Personas(id_persona) on delete cascade on update cascade,
constraint persona3 unique(id_persona)
)

--Cuarta parte
create table tbl.TiposdeTarjetas(
id_tiptarjeta int not null identity primary key,
nom_tiptarjeta varchar(50)
)

create table tbl.ClasificaciondeTarjetas(
id_clatarjeta int not null identity primary key,
nom_clatarjeta varchar(50),
des_clatarjeta varchar(100),
id_tiptarjeta int,
foreign key (id_tiptarjeta) references tbl.TiposdeTarjetas(id_tiptarjeta) on delete cascade on update cascade
)

create table tbl.EstadosdeCuentas(
id_estcuenta int not null identity primary key,
nom_estcuenta varchar(50),
des_estcuenta varchar(100)
)

create table tbl.TiposdeCuentas(
id_tipcuenta int not null identity primary key,
nom_tipcuenta varchar(50),
des_tipcuenta varchar(100)
)

--Quinta parte
create table tbl.Primas(
id_prima int not null identity primary key,
montomensual_prima money,
montoasegurado_prima money
)

create table tbl.TiposdeSeguros(
id_tipseguro int not null identity primary key,
nom_tipseguro varchar(50),
des_tipseguro varchar(100)
)

create table tbl.PorcentajesdeSeguros(
id_porcentaje int not null identity primary key,
porcentaje_porcentaje varchar(4)
)

create table tbl.Parentescos(
id_parentesco int not null identity primary key,
nom_parentesco varchar(50)
)

--Sexta parte
create table tbl.Cuentas(
id_cuenta int not null identity primary key,
num_cuenta varbinary(500),
pin_cuenta varbinary(500),
id_persona int,
id_tipcuenta int,
foreign key (id_persona) references tbl.Personas(id_persona) on delete cascade on update cascade,
constraint persona4 unique(id_persona),
foreign key (id_tipcuenta) references tbl.TiposdeCuentas(id_tipcuenta) on delete cascade on update cascade
)

create table tbl.ServiciosRealizados(
id_serviciorealizado int not null identity primary key,
fecha_serviciorealizado date,
montoefectuado_serviciorealizado money,
montoactualizado_serviciorealizado money,
id_sucursal int,
id_quiosko int,
id_tipservicio int not null,
id_cuenta int,
foreign key (id_sucursal) references tbl.Sucursales(id_sucursal) on delete cascade on update cascade,
foreign key (id_quiosko) references tbl.Quioskos(id_quiosko) on delete cascade on update cascade,
foreign key (id_tipservicio) references tbl.TiposdeServicios(id_tipservicio) on delete cascade on update cascade,
foreign key (id_cuenta) references tbl.Cuentas(id_cuenta) on delete cascade on update cascade
)

create table tbl.Seguros(
id_seguro int not null identity primary key,
monto_seguro money,
id_prima int,
id_tipseguro int,
id_cuenta int,
foreign key (id_prima) references tbl.Primas(id_prima) on delete cascade on update cascade,
foreign key (id_tipseguro) references tbl.TiposdeSeguros(id_tipseguro) on delete cascade on update cascade,
foreign key (id_cuenta) references tbl.Cuentas(id_cuenta) on delete cascade on update cascade,
constraint cuenta unique(id_cuenta)
)

create table tbl.Beneficiarios(
id_beneficiario int not null identity primary key,
nom_beneficiario varchar(50),
ape_beneficiario varchar(50),
id_parentesco int,
id_porcentaje int,
id_seguro int,
foreign key (id_parentesco) references tbl.Parentescos(id_parentesco) on delete cascade on update cascade,
foreign key (id_porcentaje) references tbl.PorcentajesdeSeguros(id_porcentaje) on delete cascade on update cascade,
foreign key (id_seguro) references tbl.Seguros(id_seguro) on delete cascade on update cascade
)

create table tbl.Ahorros(
id_ahorro int not null identity primary key,
saldo_ahorro money,
id_cuenta int,
foreign key (id_cuenta) references tbl.Cuentas(id_cuenta) on delete cascade on update cascade,
constraint cuenta2 unique(id_cuenta)
)

create table tbl.bitEstadosdeCuentas(
id_bitestcuenta int not null identity primary key,
id_cuenta int,
id_estcuenta int,
foreign key (id_cuenta) references tbl.Cuentas(id_cuenta) on delete cascade on update cascade,
constraint cuenta3 unique(id_cuenta),
foreign key (id_estcuenta) references tbl.EstadosdeCuentas(id_estcuenta) on delete cascade on update cascade
)

create table tbl.EstadosdeRemesas(
id_estremesa int not null identity primary key,
nom_estremesa varchar(50),
des_estremesa varchar(100)
)

create table tbl.Remesas(
id_remesa int not null identity primary key,
monto_remesa money,
nomdestinatario_remesa varchar(100),
pin_remesa varbinary(500),
id_cuenta int,
foreign key (id_cuenta) references tbl.Cuentas(id_cuenta) on delete cascade on update cascade
)

create table tbl.bitEstadosdeRemesas(
id_bitestremesa int not null identity primary key,
id_remesa int,
id_estremesa int,
foreign key (id_remesa) references tbl.Remesas(id_remesa) on delete cascade on update cascade,
constraint remesa unique(id_remesa),
foreign key (id_estremesa) references tbl.EstadosdeRemesas(id_estremesa) on delete cascade on update cascade
)

create table tbl.Tarjetas(
id_tarjeta int not null identity primary key,
num_tarjeta varbinary(500),
id_cuenta int,
id_clatarjeta int,
foreign key (id_cuenta) references tbl.Cuentas(id_cuenta) on delete cascade on update cascade,
foreign key (id_clatarjeta) references tbl.ClasificaciondeTarjetas(id_clatarjeta) on delete cascade on update cascade
)

create table tbl.EstadosdeTarjetas(
id_esttarjeta int not null identity primary key,
nom_esttarjeta varchar(50)
)


create table tbl.bitEstadosdeTarjetas(
id_bitesttarjeta int not null identity primary key,
id_esttarjeta int,
id_tarjeta int,
foreign key (id_esttarjeta) references tbl.EstadosdeTarjetas(id_esttarjeta) on delete cascade on update cascade,
foreign key (id_tarjeta) references tbl.Tarjetas(id_tarjeta) on delete cascade on update cascade,
constraint tarjeta unique(id_tarjeta)
)

create table tbl.Saldos(
id_saldo int not null identity primary key,
saldo_saldo money,
id_tarjeta int,
foreign key (id_tarjeta) references tbl.Tarjetas(id_tarjeta) on delete cascade on update cascade,
constraint tarjeta2 unique(id_tarjeta)
)

--Septima parte
create table tbl.TiposdePrestamos(
id_tipprestamo int not null identity primary key,
nom_tipprestamo varchar(50),
des_tipprestamo varchar(100)
)

create table tbl.Plazos(
id_plazo int not null identity primary key,
nom_plazo varchar(50),
cuotasmensuales_plazo int
)

create table tbl.EstadosdePrestamos(
id_estprestamo int not null identity primary key,
nom_estprestamo varchar(50),
des_estprestamo varchar(100)
)

create table tbl.Prestamos(
id_prestamo int not null identity primary key,
monto_prestamo money,
cuota_prestamo money,
id_tipprestamo int,
id_cuenta int,
id_plazo int,
foreign key (id_tipprestamo) references tbl.TiposdePrestamos(id_tipprestamo) on delete cascade on update cascade,
foreign key (id_cuenta) references tbl.Cuentas(id_cuenta) on delete cascade on update cascade,
foreign key (id_plazo) references tbl.Plazos(id_plazo) on delete cascade on update cascade
)

create table tbl.bitEstadosdePrestamos(
id_bitestprestamo int,
id_prestamo int,
id_estprestamo int,
foreign key (id_prestamo) references tbl.Prestamos(id_prestamo) on delete cascade on update cascade,
constraint prestamo unique(id_prestamo),
foreign key (id_estprestamo) references tbl.EstadosdePrestamos(id_estprestamo) on delete cascade on update cascade
)

create table tbl.Cuotas(
id_cuota int not null identity primary key,
monto_cuota money,
fecha_cuota date,
id_prestamo int,
foreign key (id_prestamo) references tbl.Prestamos(id_prestamo) on delete cascade on update cascade
)

--Insercion de datos
insert into tbl.TiposdeServicios values('Deposito','Deposito de dinero en cuentas, remesas o en ahorros.')
insert into tbl.TiposdeServicios values('Pago','Pagos de cuentas o prestamos.')
insert into tbl.TiposdeServicios values('Retiro','Retiros de dinero de remesas, cuenta de ahorros o de tarjetas de credito.')
insert into tbl.TiposdeServicios values('Creacion de Cuenta','Creacion de cuentas bancarias.')
insert into tbl.TiposdeServicios values('Modificacion de Estado de Cuenta','Modificacion de cuentas, congeladas, canceladas o desactivadas temporalmente.')
insert into tbl.TiposdeServicios values('Retiro','Retiros de dinero de cuenta de ahorros o de tarjetas de credito.')

insert into tbl.Departamentos values('Ahuachapán')
insert into tbl.Departamentos values('Santa Ana')
insert into tbl.Departamentos values('Sonsonate')
insert into tbl.Departamentos values('Usulután')
insert into tbl.Departamentos values('San Miguel')
insert into tbl.Departamentos values('Morazán')
insert into tbl.Departamentos values('La Unión')
insert into tbl.Departamentos values('La Libertad')
insert into tbl.Departamentos values('Chalatenango')
insert into tbl.Departamentos values('Cuscatlán')
insert into tbl.Departamentos values('San Salvador')
insert into tbl.Departamentos values('La Paz')
insert into tbl.Departamentos values('Cabañas')
insert into tbl.Departamentos values('San Vicente')

insert into tbl.Sucursales values('Bancaria Central','Metrocentro 2a Etapa.',11)
insert into tbl.Sucursales values('Bancaria San Luis','Centro comercial San Luis.',11)
insert into tbl.Sucursales values('Bancaria Multiplaza','Centro comercial MultiPlaza.',11)

insert into tbl.Quioskos values('Quiosko Metrocentro','Metrocentro 2a Etapa.')
insert into tbl.Quioskos values('Quiosko Mejicanos','Colonia mejicanos, frente a alcaldia.')
insert into tbl.Quioskos values('Quiosko Galerias','Centro comercial Galerias.')

insert into tbl.CargosdeTrabajos values('Jefe')
insert into tbl.CargosdeTrabajos values('Gerente')
insert into tbl.CargosdeTrabajos values('Empleado')
insert into tbl.CargosdeTrabajos values('Personal de Limpieza')

insert into tbl.EstadosCiviles values('Soltero/a')
insert into tbl.EstadosCiviles values('Casado/a')
insert into tbl.EstadosCiviles values('Divorciado/a')
insert into tbl.EstadosCiviles values('Viudo/a')

insert into tbl.Generos values('Hombre')
insert into tbl.Generos values('Mujer')

insert into tbl.TiposdePersonas values('Superadministrador','Control sobre administradores y revisiones de sistema.')
insert into tbl.TiposdePersonas values('Administrador','Control sobre gerentes y empleados y revisiones de sistema')
insert into tbl.TiposdePersonas values('Gerente','Control de operaciones de sistema y de empleados')
insert into tbl.TiposdePersonas values('Empleado','Control sobre operaciones basicas de sistema e insercion.')
insert into tbl.TiposdePersonas values('Cliente','Usuario de bancaria.')

insert into tbl.TiposdePrestamos values('Personal','Prestamo para uso personal.')
insert into tbl.TiposdePrestamos values('Estudios','Prestamos especificos para estudios.')
insert into tbl.TiposdePrestamos values('Hipotecarios','Prestamos especificos para compra de casas, en dado caso no se llegara a pagar se tomaria la casa.')

insert into tbl.Primas values(5,7000)
insert into tbl.Primas values(10,14000)
insert into tbl.Primas values(15,21000)
insert into tbl.Primas values(20,28000)

insert into tbl.TiposdeSeguros values('De vida','Cubre un seguro por muerte de cualquier tipo.')
insert into tbl.TiposdeSeguros values('De vida y ahorro','Cubre un seguro por muerte o por ahorro.')
insert into tbl.TiposdeSeguros values('De accidentes','Cubre un seguro por accidentes personales.')

insert into tbl.Parentescos values('Esposo/a')
insert into tbl.Parentescos values('Hijo/a')
insert into tbl.Parentescos values('Madre')
insert into tbl.Parentescos values('Padre')
insert into tbl.Parentescos values('Pariente')
insert into tbl.Parentescos values('Amigo/a')

insert into tbl.TiposdeCuentas values('Ahorro','Cuenta para guardar dinero y poder ganar intereses.')
insert into tbl.TiposdeCuentas values('Corriente','Cuenta para ahorros y usos de tarjetas de credito y debito.')

insert into tbl.EstadosdeCuentas values('Activa','Cuenta administrada activamente por el cliente.')
insert into tbl.EstadosdeCuentas values('Desactivada','Cuenta desactivada por el banco por motivos.')
insert into tbl.EstadosdeCuentas values('Congelada','Cuenta congelada por motivos.')

insert into tbl.TiposdeTarjetas values('Debito')
insert into tbl.TiposdeTarjetas values('Credito')

insert into tbl.ClasificaciondeTarjetas values('Clasica','Tarjeta de debito clasica de Bancaria.',1)
insert into tbl.ClasificaciondeTarjetas values('Oro','Tarjeta de debito oro de Bancaria.',1)
insert into tbl.ClasificaciondeTarjetas values('Standard','Tarjeta de credito standard de Bancaria.',2)
insert into tbl.ClasificaciondeTarjetas values('Pro','Tarjeta de credito pro de Bancaria.',2)
insert into tbl.ClasificaciondeTarjetas values('Elite','Tarjeta de credito elite de Bancaria.',2)

insert into tbl.EstadosdePrestamos values('En proceso','En proceso de pago de prestamo.')
insert into tbl.EstadosdePrestamos values('Finalizado','Prestamo totalmente pagado.')

insert into tbl.EstadosdeRemesas values('Enviada','Remesa enviada, esperando cobro.')
insert into tbl.EstadosdeRemesas values('Cobrada','Remesa cobrada.')

insert into tbl.Plazos values('1 año',12)
insert into tbl.Plazos values('2 años',24)
insert into tbl.Plazos values('3 años',36)
insert into tbl.Plazos values('4 años',48)
insert into tbl.Plazos values('5 años',60)
insert into tbl.Plazos values('6 años',72)
insert into tbl.Plazos values('7 años',84)

insert into tbl.PorcentajesdeSeguros values('100%')
insert into tbl.PorcentajesdeSeguros values('75%')
insert into tbl.PorcentajesdeSeguros values('50%')
insert into tbl.PorcentajesdeSeguros values('25%')

insert into tbl.EstadosdeTarjetas values('Activa')
insert into tbl.EstadosdeTarjetas values('Desactivada')
insert into tbl.EstadosdeTarjetas values('No iniciada')

GO
--Creacion de vistas

create view viw.LoginV as
select u.id_usuario, p.nom_persona, p.ape_persona, p.correo_persona, u.usu_usuario, u.contra_usuario, tp.nom_tippersona
from tbl.Usuarios as u, tbl.Personas as p, tbl.TiposdePersonas as tp
where tp.id_tippersona = p.id_tippersona and p.id_persona = u.id_persona
GO

create view viw.SegurosV as
select b.nom_beneficiario, b.ape_beneficiario, pa.nom_parentesco, ps.porcentaje_porcentaje, s.monto_seguro, pr.montomensual_prima, pr.montoasegurado_prima, ts.nom_tipseguro, c.num_cuenta
from tbl.Seguros as s, tbl.TiposdeSeguros as ts, tbl.Beneficiarios as b, tbl.PorcentajesdeSeguros as ps, tbl.Parentescos as pa, tbl.Primas as pr, tbl.Cuentas as c
where pa.id_parentesco = b.id_parentesco and ps.id_porcentaje = b.id_porcentaje and s.id_seguro = b.id_seguro and pr.id_prima = s.id_prima and ts.id_tipseguro = s.id_tipseguro and c.id_cuenta = s.id_cuenta
GO

create view viw.PrestamosV as
select pr.monto_prestamo, pr.cuota_prestamo, tp.nom_tipprestamo, pl.nom_plazo, pl.cuotasmensuales_plazo, ep.nom_estprestamo, c.num_cuenta
from tbl.Prestamos as pr, tbl.bitEstadosdePrestamos as bep, tbl.EstadosdePrestamos ep, tbl.TiposdePrestamos as tp, tbl.Plazos as pl, tbl.Cuentas as c
where tp.id_tipprestamo = pr.id_tipprestamo and pl.id_plazo = pr.id_plazo and pr.id_prestamo = bep.id_prestamo and ep.id_estprestamo = bep.id_estprestamo and c.id_cuenta = pr.id_cuenta
GO

create view viw.CuotasdePrestamosV as
select cu.monto_cuota, cu.fecha_cuota, c.num_cuenta, p.monto_prestamo, p.cuota_prestamo
from tbl.Cuotas as cu, tbl.Cuentas as c, tbl.Prestamos as p
where p.id_prestamo = cu.id_prestamo and c.id_cuenta = p.id_cuenta
GO


--Creacion de funciones

CREATE FUNCTION hlp.enc(@var varchar(300))
RETURNS varbinary(500)
AS
BEGIN
	DECLARE @res AS varbinary(500)
	SET @res = ENCRYPTBYPASSPHRASE('Prototype',@var)
	RETURN @res
END
GO


CREATE FUNCTION hlp.desenc(@var varbinary(500))
RETURNS varchar(300)
AS
BEGIN
	DECLARE @res AS varchar(300)
	SET @res = DECRYPTBYPASSPHRASE('Prototype',@var)
	RETURN @res
END
GO

CREATE FUNCTION hlp.LoginF(@usu_usuario varchar(300), @contra_usuario varchar(300))
RETURNS varchar(50)
AS
BEGIN
	DECLARE @nom_tippersona varchar(50)
	IF EXISTS(SELECT nom_tippersona FROM viw.LoginV WHERE hlp.desenc(usu_usuario) = @usu_usuario AND hlp.desenc(contra_usuario) = @contra_usuario)
		SET @nom_tippersona = (SELECT nom_tippersona FROM viw.LoginV WHERE hlp.desenc(usu_usuario) = @usu_usuario AND hlp.desenc(contra_usuario) = @contra_usuario)
	ELSE
		SET @nom_tippersona = 'Null'
	RETURN @nom_tippersona
END
GO

--Creacion de procedimientos

	--Procedimientos de mantenimiento
CREATE PROC pcd.AgregarPersona(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @nom_sucursal varchar(50), @nom_tippersona varchar(50), @id_persona int OUTPUT)
AS
BEGIN
	BEGIN TRANSACTION AgregarPersona 
	BEGIN TRY
		DECLARE @id_sucursal int
		DECLARE @id_tippersona int
		SET @id_sucursal = (SELECT id_sucursal FROM tbl.Sucursales WHERE nom_sucursal = @nom_sucursal)
		SET @id_tippersona = (SELECT id_tippersona FROM tbl.TiposdePersonas WHERE nom_tippersona = @nom_tippersona)
		INSERT INTO tbl.Personas VALUES(@nom_persona, @ape_persona, @correo_persona,GETDATE(), @id_tippersona)
		SELECT @id_persona = id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona
		INSERT INTO tbl.bitSucursales VALUES(@id_persona, @id_sucursal)
		COMMIT TRAN AgregarPersona
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AgregarPersona
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar agregar a una persona.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.ModificarPersona(@id_persona int)
AS
BEGIN
	BEGIN TRANSACTION ModificarPersona
	BEGIN TRY
		UPDATE tbl.Personas SET nom_persona = @nom_persona, ape_persona = @ape_persona, correo_persona = @correo_persona WHERE id_persona = @id_persona
		COMMIT TRAN ModificarPersona
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ModificarPersona
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar modificar a una persona.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.AgregarPersonaADM(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @nom_tippersona varchar(50), @id_persona int OUTPUT)
AS
BEGIN
	BEGIN TRANSACTION AgregarPersonaADM 
	BEGIN TRY
		DECLARE @id_tippersona int
		SET @id_tippersona = (SELECT id_tippersona FROM tbl.TiposdePersonas WHERE nom_tippersona = @nom_tippersona)
		INSERT INTO tbl.Personas VALUES(@nom_persona, @ape_persona, @correo_persona,GETDATE(), @id_tippersona)
		SELECT @id_persona = id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona
		COMMIT TRAN AgregarPersonaADM
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AgregarPersonaADM
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar agregar a una persona SADM o ADM.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.AgregarPersonaconDatos(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @nom_sucursal varchar(50), @nom_tippersona varchar(50), @dui_datpersona varchar(300), @nit_datpersona varchar(300), @dir_datpersona varchar(100), @telcel_datpersona varchar(9), @telcasa_datpersona varchar(9), @nom_genero varchar(50), @nom_estcivil varchar(50))
AS
BEGIN
	BEGIN TRANSACTION AgregarPersonaconDatos 
	BEGIN TRY
		DECLARE @id_genero int
		DECLARE @id_estcivil int
		DECLARE @id int
		SET @id_genero = (SELECT id_genero FROM tbl.Generos WHERE nom_genero = @nom_genero)
		SET @id_estcivil = (SELECT id_estadocivil FROM tbl.EstadosCiviles WHERE nom_estadocivil = @nom_estcivil)
		EXEC pcd.AgregarPersona @nom_persona, @ape_persona, @correo_persona, @nom_sucursal, @nom_tippersona, @id_persona = @id OUTPUT
		INSERT INTO tbl.DatosdePersonas VALUES(hlp.enc(@dui_datpersona), hlp.enc(@nit_datpersona), @dir_datpersona, @telcel_datpersona, @telcasa_datpersona, @id_genero, @id_estcivil, @id)
		COMMIT TRAN AgregarPersonaconDatos
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AgregarPersonaconDatos
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar agregar a una persona con datos.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.ModificarPersonaconDatos(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @dui_datpersona varchar(300), @nit_datpersona varchar(300), @dir_datpersona varchar(100), @telcel_datpersona varchar(9), @telcasa_datpersona varchar(9), @nom_genero varchar(50), @nom_estcivil varchar(50))
AS
BEGIN
	BEGIN TRANSACTION ModificarPersonaconDatos 
	BEGIN TRY
		DECLARE @id_genero int
		DECLARE @id_estcivil int
		DECLARE @id_persona int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		SET @id_genero = (SELECT id_genero FROM tbl.Generos WHERE nom_genero = @nom_genero)
		SET @id_estcivil = (SELECT id_estadocivil FROM tbl.EstadosCiviles WHERE nom_estadocivil = @nom_estcivil)
		EXEC pcd.ModificarPersona @nom_persona, @ape_persona, @correo_persona
		UPDATE tbl.DatosdePersonas SET dui_datpersona = hlp.enc(@dui_datpersona),nit_datpersona = hlp.enc(@nit_datpersona),dir_datpersona = @dir_datpersona,telcel_datpersona = @telcel_datpersona,telcasa_datpersona = @telcasa_datpersona,id_genero = @id_genero,id_estadocivil = @id_estcivil WHERE id_persona = @id_persona
		COMMIT TRAN ModificarPersonaconDatos
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ModificarPersonaconDatos
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar modificar a una persona con datos.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.AgregarCliente(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @dui_datpersona varchar(100), @nit_datpersona varchar(100), @dir_datpersona varchar(100), @telcel_datpersona varchar(9), @telcasa_datpersona varchar(9), @nom_genero varchar(50), @nom_estcivil varchar(50), @salario_dattrabajo money, @nom_datempresa varchar(100), @nom_cargotrabajo varchar(50))
AS
BEGIN
	BEGIN TRANSACTION AgregarCliente
	BEGIN TRY
		DECLARE @id_datempresa int
		DECLARE @id_cargotrabajo int
		DECLARE @id int
		SET @id_datempresa = (SELECT id_datempresa FROM tbl.DatosdeEmpresas WHERE nom_datempresa = @nom_datempresa)
		SET @id_cargotrabajo = (SELECT id_cargotrabajo FROM tbl.CargosdeTrabajos WHERE nom_cargotrabajo = @nom_cargotrabajo) 
		EXEC pcd.AgregarPersonaconDatos @nom_persona, @ape_persona, @correo_persona, 'Bancaria Central', 'Cliente', @dui_datpersona, @nit_datpersona, @dir_datpersona, @telcel_datpersona, @telcasa_datpersona, @nom_genero, @nom_estcivil
		SET @id = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		INSERT INTO tbl.DatosdeTrabajos VALUES(@salario_dattrabajo, @id_datempresa, @id_cargotrabajo, @id)
		COMMIT TRAN AgregarCliente
		RETURN 
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AgregarCliente
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar agregar a un cliente.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO	

CREATE PROC pcd.ModificarCliente(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @dui_datpersona varchar(100), @nit_datpersona varchar(100), @dir_datpersona varchar(100), @telcel_datpersona varchar(9), @telcasa_datpersona varchar(9), @nom_genero varchar(50), @nom_estcivil varchar(50), @salario_dattrabajo money, @nom_datempresa varchar(100), @nom_cargotrabajo varchar(50))
AS
BEGIN
	BEGIN TRANSACTION ModificarCliente
	BEGIN TRY
		DECLARE @id_datempresa int
		DECLARE @id_cargotrabajo int
		DECLARE @id_persona int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		SET @id_datempresa = (SELECT id_datempresa FROM tbl.DatosdeEmpresas WHERE nom_datempresa = @nom_datempresa)
		SET @id_cargotrabajo = (SELECT id_cargotrabajo FROM tbl.CargosdeTrabajos WHERE nom_cargotrabajo = @nom_cargotrabajo)
		EXEC pcd.ModificarPersonaconDatos  @nom_persona, @ape_persona, @correo_persona, @dui_datpersona, @nit_datpersona, @dir_datpersona, @telcel_datpersona, @telcasa_datpersona, @nom_genero, @nom_estcivil
		UPDATE tbl.DatosdeTrabajos SET salario_dattrabajo = @salario_dattrabajo,id_datempresa = @id_datempresa,id_cargotrabajo = @id_cargotrabajo WHERE id_persona = @id_persona
		COMMIT TRAN ModificarCliente
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ModificarCliente
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar modificar a un cliente.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.EliminarCliente(@correo_persona varchar(50))
AS
BEGIN
	BEGIN TRANSACTION EliminarCliente
	BEGIN TRY
		DECLARE @id_persona int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE correo_persona = @correo_persona)
		DELETE FROM tbl.DatosdePersonas WHERE id_persona = @id_persona
		DELETE FROM tbl.Personas WHERE id_persona = @id_persona
		COMMIT TRAN EliminarCliente
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN EliminarCliente
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar eliminar a un cliente.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.AgregarEmpleado(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @nom_sucursal varchar(50), @dui_datpersona varchar(300), @nit_datpersona varchar(300), @dir_datpersona varchar(100), @telcel_datpersona varchar(9), @telcasa_datpersona varchar(9), @nom_genero varchar(50), @nom_estcivil varchar(50), @usu_usuario varchar(300), @contra_usuario varchar(300))
AS
BEGIN
	BEGIN TRANSACTION AgregarEmpleado
	BEGIN TRY
		DECLARE @id int
		EXEC pcd.AgregarPersonaconDatos @nom_persona, @ape_persona, @correo_persona, @nom_sucursal, 'Empleado', @dui_datpersona, @nit_datpersona, @dir_datpersona, @telcel_datpersona, @telcasa_datpersona, @nom_genero, @nom_estcivil
		SET @id = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		INSERT INTO tbl.Usuarios VALUES(hlp.enc(@usu_usuario), hlp.enc(@contra_usuario), @id)
		COMMIT TRAN AgregarEmpleado
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AgregarEmpleado
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar agregar a un empleado.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.ModificarEmpleado(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @nom_sucursal varchar(50), @dui_datpersona varchar(300), @nit_datpersona varchar(300), @dir_datpersona varchar(100), @telcel_datpersona varchar(9), @telcasa_datpersona varchar(9), @nom_genero varchar(50), @nom_estcivil varchar(50), @usu_usuario varchar(300))
AS
BEGIN
	BEGIN TRANSACTION ModificarEmpleado
	BEGIN TRY
		DECLARE @id_persona int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		EXEC pcd.ModificarPersonaconDatos @nom_persona, @ape_persona, @correo_persona, @dui_datpersona, @nit_datpersona, @dir_datpersona, @telcel_datpersona, @telcasa_datpersona, @nom_genero, @nom_estcivil
		UPDATE tbl.Usuarios SET usu_usuario = hlp.enc(@usu_usuario) WHERE id_persona = @id_persona
		COMMIT TRAN ModificarEmpleado
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ModificarEmpleado
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar modificar a un empleado.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.EliminarEmpleado(@correo_persona varchar(50))
AS
BEGIN
	BEGIN TRANSACTION EliminarEmpleado
	BEGIN TRY
		DECLARE @id_persona int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE correo_persona = @correo_persona)
		DELETE FROM tbl.DatosdePersonas WHERE id_persona = @id_persona
		DELETE FROM tbl.Personas WHERE correo_persona = @correo_persona
		COMMIT TRAN EliminarEmpleado
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN EliminarEmpleado
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar eliminar a un empleado.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.AgregarGerente(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @nom_sucursal varchar(50), @dui_datpersona varchar(300), @nit_datpersona varchar(300), @dir_datpersona varchar(100), @telcel_datpersona varchar(9), @telcasa_datpersona varchar(9), @nom_genero varchar(50), @nom_estcivil varchar(50), @usu_usuario varchar(300), @contra_usuario varchar(300))
AS
BEGIN
	BEGIN TRANSACTION AgregarGerente
	BEGIN TRY
		DECLARE @id int
		EXEC pcd.AgregarPersonaconDatos @nom_persona, @ape_persona, @correo_persona, @nom_sucursal, 'Gerente', @dui_datpersona, @nit_datpersona, @dir_datpersona, @telcel_datpersona, @telcasa_datpersona, @nom_genero, @nom_estcivil
		SET @id = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		INSERT INTO tbl.Usuarios VALUES(hlp.enc(@usu_usuario), hlp.enc(@contra_usuario), @id)
		COMMIT TRAN AgregarGerente
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AgregarGerente
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar agregar a un gerente.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.ModificarGerente(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50), @nom_sucursal varchar(50), @dui_datpersona varchar(300), @nit_datpersona varchar(300), @dir_datpersona varchar(100), @telcel_datpersona varchar(9), @telcasa_datpersona varchar(9), @nom_genero varchar(50), @nom_estcivil varchar(50), @usu_usuario varchar(300))
AS
BEGIN
	BEGIN TRANSACTION ModificarGerente
	BEGIN TRY
		DECLARE @id_persona int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		EXEC pcd.ModificarPersonaconDatos @nom_persona, @ape_persona, @correo_persona, @dui_datpersona, @nit_datpersona, @dir_datpersona, @telcel_datpersona, @telcasa_datpersona, @nom_genero, @nom_estcivil
		UPDATE tbl.Usuarios SET usu_usuario = hlp.enc(@usu_usuario) WHERE id_persona = @id_persona
		COMMIT TRAN ModificarGerente
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ModificarGerente
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar modificar a un gerente.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.EliminarGerente(@correo_persona varchar(50))
AS
BEGIN
	BEGIN TRANSACTION EliminarGerente
	BEGIN TRY
		DECLARE @id_persona int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE correo_persona = @correo_persona)
		DELETE FROM tbl.DatosdePersonas WHERE id_persona = @id_persona
		DELETE FROM tbl.Personas WHERE correo_persona = @correo_persona
		COMMIT TRAN EliminarGerente
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN EliminarGerente
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar eliminar a un gerente.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.AgregarAdministrador(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50),  @usu_usuario varchar(300), @contra_usuario varchar(300))
AS
BEGIN
	DECLARE @verificador int
	BEGIN TRAN AgregarAdministrador
	BEGIN TRY
		DECLARE @id int
		IF NOT EXISTS(SELECT id_persona FROM tbl.Personas WHERE correo_persona = @correo_persona)
			BEGIN
			EXEC pcd.AgregarPersonaADM @nom_persona, @ape_persona, @correo_persona, 'Administrador', @id_persona = @id OUTPUT
			SET @id = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
			SET @verificador = 0
			END
		ELSE
			BEGIN
			SET @verificador = 1
			END
		IF NOT EXISTS(SELECT id_usuario FROM tbl.Usuarios WHERE hlp.desenc(usu_usuario) = @usu_usuario)
			BEGIN			
			INSERT INTO tbl.Usuarios VALUES(hlp.enc(@usu_usuario), hlp.enc(@contra_usuario), @id)
			END
		ELSE
			BEGIN
			SET @verificador = 1
			END
		IF(@verificador = 1)
			BEGIN
			RAISERROR ('Persona o usuario ya existente.', 19, 503) WITH LOG;
			END
		COMMIT TRAN AgregarAdministrador
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AgregarAdministrador
		DECLARE @Mensaje NVARCHAR(4000)
		IF(@verificador = 1)
			BEGIN
			SET @Mensaje = 'Persona o usuario ya existente.'
			END
		ELSE
			BEGIN
			SET @Mensaje = 'Error al intentar agregar a un administrador.';
			END
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.ModificarAdministrador(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50),  @usu_usuario varchar(300))
AS
BEGIN
	BEGIN TRANSACTION ModificarAdministrador
	BEGIN TRY
		DECLARE @id_persona int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		EXEC pcd.ModificarPersona @nom_persona, @ape_persona, @correo_persona
		UPDATE tbl.Usuarios SET usu_usuario = hlp.enc(@usu_usuario) WHERE id_persona = @id_persona
		COMMIT TRAN ModificarAdministrador
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ModificarAdministrador
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar modificar a un administrador.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.EliminarAdministrador(@correo_persona varchar(50))
AS
BEGIN
	BEGIN TRANSACTION EliminarAdministrador
	BEGIN TRY
		DELETE FROM tbl.Personas WHERE correo_persona = @correo_persona
		COMMIT TRAN EliminarAdministrador
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN EliminarAdministrador
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar eliminar a un administrador.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.AgregarSuperAdministrador(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50),  @usu_usuario varchar(300), @contra_usuario varchar(300))
AS
BEGIN
	BEGIN TRANSACTION AgregarSuperAdministrador
	BEGIN TRY
		DECLARE @id int
		EXEC pcd.AgregarPersonaADM @nom_persona, @ape_persona, @correo_persona, 'Superadministrador', @id_persona = @id OUTPUT
		SET @id = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		INSERT INTO tbl.Usuarios VALUES(hlp.enc(@usu_usuario), hlp.enc(@contra_usuario), @id)
		COMMIT TRAN AgregarSuperAdministrador
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AgregarSuperAdministrador
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar agregar a un superadministrador.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.ModificarSuperAdministrador(@nom_persona varchar(50), @ape_persona varchar(50), @correo_persona varchar(50),  @usu_usuario varchar(300), @contra_usuario varchar(300))
AS
BEGIN
	BEGIN TRANSACTION ModificarSuperAdministrador 
	BEGIN TRY
		DECLARE @id_persona int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE nom_persona = @nom_persona AND ape_persona = @ape_persona AND correo_persona = @correo_persona)
		EXEC pcd.ModificarPersona @nom_persona, @ape_persona, @correo_persona
		UPDATE tbl.Usuarios SET usu_usuario = hlp.enc(@usu_usuario),contra_usuario = hlp.enc(@contra_usuario) WHERE id_persona = @id_persona
		COMMIT TRAN ModificarSuperAdministrador
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ModificarSuperAdministrador
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar modificar a un superadministrador.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.AgregarEmpresa(@nom_datempresa varchar(50), @tel_datempresa varchar(9), @dir_datempresa varchar(100))
AS
BEGIN
	BEGIN TRANSACTION AgregarEmpresa 
	BEGIN TRY
		INSERT INTO tbl.DatosdeEmpresas VALUES(@nom_datempresa, @tel_datempresa, @dir_datempresa)
		COMMIT TRAN AgregarEmpresa
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AgregarEmpresa
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al intentar agregar una empresa.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

EXEC pcd.AgregarSuperAdministrador 'Carlos', 'Cornejo', 'icarloscornejo@outlook.com', '12BAAAFA6533B8D4DD19751EE7CA604CE235116ED506E228444A94A6F279165A273641A3DB945632897D190B90E836E3B16285ABDDA112D33C0005722AB86BA37706D9DB5AB4C548A93922524D1D3B1961BED785C93E6B7EA365DE6D0AA40C75CFD7B4750FCE24DB75C7146D0622885E27EDED4BA13F2C6845C3455603FEF0EA', '25D78B7766D45A1AC22F2FD7E53C6EE028D7BB7F5F14A6C3537757D9285AE8F8BA089EAFD84D025D6F2E9AAEEF1C92629E55C875257EE6F89C10C314CF4DE53C9E50179E505C87968E1A3CDF112F25B2D3059621C40D87129608580B0AF6A6475C74AD2B08A4C3486EAF5622467730F6956CA06B9DAE9F0F09BBF432059322ED' 
GO
EXEC pcd.AgregarSuperAdministrador 'Dennys', 'Campos', 'dennys@campos.com', '0B07FA01D9536E54C690C8BBDC8D1D414A6A24FEF26FAE4818552DCB35CE35E4E8FAAAA26ECF2E1210C889386DFBBE3173608B868AE927A72B818123CD12278F4B150713DDFE85E79DCE85A1A45F51EE4563550B06E2A76A6EE8323B10AD06BAF4456E77C213DAC205A14096DA3E4A54985060EC68A926C1713140C7C419CAE3', '2EC3DAB13AE82E6359B5480050363F9EA01A83A8764CD45466D48C6494D8F526932F0E12656BEDDD7F0E6C25ADC307418CE2E4680D022B63888DF2224862A12C3BD939262CB7C223E62C61DE573A95304A9F4171FD5E28DB168D2FF1F775C925C6FD8BA0B60A9AE8039C2BC3C5FEA01CF8BA9A024D0329788360A0EBBBC53CA9' 
GO
EXEC pcd.AgregarSuperAdministrador 'Carlos', 'Cazun', 'carlos@cazun.com', '0CC6187ADD41BE9FC55853E7DD788AD49E1AE8AB7ECB29925D82EA03B9B4A7CF92197CE6C25C36843C30622ED5C9A0674790D817598B903CA7A25F64E717A8B94FDF2CADA2AE97772220471FF441F1FD0D8C68BDE063E0CCA121751097F9723DA86D5CE14C90AF9804CFBA27D0F1073D2EF6149A00C2F2D7C675F7F7B52BA004', '9100B316D3305E66E2F857D118D0571D2650AA4D009B65A4742D3F839B7B3E0B8C85098C0C9CBEB18A8A6DB87978D2C5C560743C436620C25CF33A1D14698D6F263C76B3E4A882CD1D3C840DBFB856066A6E620AD3CA461493E4D8B5B06E7AEF525EFE42D451E73A9D864BF52CC7D9C2777DA7D9E3010E145C576AB2E80C2B6E' 
GO
EXEC pcd.AgregarSuperAdministrador 'Romeo', 'Cardona', 'romeo@cardona.com', '85F3EDFE170630D58DBF35DCB7A81DB6332E42860E731CE4681128310536890EF27E4213676C304148D307019553B504FAD9B1B79C64FE5713502CF7B8060D4F626399821D8043913C52AD5BBFB0870AC4E03F83C754396375969307EC79DED0C06C6960B1344A6A6A0615DF9891621CFAB408F9F03FD19B97AC20653741E8F0', '80DA70DF7FA80F5806F67258AEE6D2E7ABCBC1946B48956F5207F2E3E0C6802C8B68E0689DC5D17541E1B158B13E3C7740E4A37A3C57CA61C1EF5653B837D09E0BE2DC10C7B71761805BAFFEA543A4C9524503A460A64401BFD85C718B4DB6ADDA82D3A8C99C7E4307157E34A2A60EDC752F1F4FB48296DD890EF2B13BE0066F' 
GO
EXEC pcd.AgregarSuperAdministrador 'Gerardo', 'Garcia', 'famoso@timador.com', '9124C24B779C0E1E1AB453B9051FE95441CD9BD6242715C7BE555F072E49BE9F7ECCEF3140C3B75B76784DA7E822897CFF737F36361760A7C850198CD9B098AF23AC2A56D627AFB22DE0EAAEF52A60D062FB9691AC91B96051596099E4E56207BE49CC207FE0E43C5AE14802C3A1E0806A43743FE3F8F9C743215D4EEA2AC0D7', '3B697AE4BF2ACA6A8E971DC657F0C0F5E873C3FAE6F030077DADA13B9C7376977C2803DAF6B12FB7EA11DC3091DDFB2C49BFD550BC116437E4235D35262EB289281713CC299335FEC7B8B15E89BF95E205C58B76110A1CC160C906CEB379B6577CA3237ED3EAAA4971649CAC8D4BE8FD8686DE0642E6491F3B21394FD0C56E50' 
GO

	--Procedimientos de Logica de Negocio

CREATE PROC pcd.NuevoPrestamo(@monto_prestamo money, @nom_tipprestamo varchar(50), @nom_plazo varchar(50), @nom_estprestamo varchar(50), @num_cuenta varchar(300))
AS
BEGIN
	BEGIN TRANSACTION NuevoPrestamo
	BEGIN TRY
		DECLARE @id_tipprestamo int
		DECLARE @id_plazo int
		DECLARE @id_estprestamo int
		DECLARE @id_cuenta int
		DECLARE @cuota_prestamo money
		DECLARE @cuotasmensuales_plazo int
		DECLARE @i int
		DECLARE @tempcuota money
		DECLARE @tempinteres money
		DECLARE @acumuladointeres money
			SET @cuotasmensuales_plazo = (SELECT cuotasmensuales_plazo FROM tbl.Plazos WHERE nom_plazo = @nom_plazo)
			SET @i = (0)
			SET @tempcuota = (@monto_prestamo / @cuotasmensuales_plazo)
			SET @tempinteres = (0)
			SET @acumuladointeres = (0)
			WHILE(@i < @cuotasmensuales_plazo)
			BEGIN
				SET @tempinteres = (@monto_prestamo - (@tempcuota * @i))
				SET @acumuladointeres = (@acumuladointeres + ((@tempinteres / @cuotasmensuales_plazo) * 0.10))
				SET @i = (@i + 1)
			END
			SET @cuota_prestamo = ((@acumuladointeres / @cuotasmensuales_plazo) + @tempcuota)
		SET @id_tipprestamo = (SELECT id_tipprestamo FROM tbl.TiposdePrestamos WHERE nom_tipprestamo = @nom_tipprestamo)
		SET @id_plazo = (SELECT id_plazo FROM tbl.Plazos WHERE nom_plazo = @nom_plazo)
		SET @id_estprestamo = (SELECT id_estprestamo FROM tbl.EstadosdePrestamos WHERE nom_estprestamo = @nom_estprestamo)
		SET @id_cuenta = (SELECT id_cuenta FROM tbl.Cuentas WHERE hlp.desenc(num_cuenta) = @num_cuenta)
		INSERT INTO tbl.Prestamos VALUES(@monto_prestamo, @cuota_prestamo, @id_tipprestamo, @id_cuenta, @id_plazo)
		COMMIT TRAN NuevoPrestamo
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevoPrestamo
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al agregar un nuevo prestamo.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.NuevoSeguro(@id_prima int, @nom_tipseguro varchar(50), @id_cuenta int, @id_seguro int OUTPUT)
AS
BEGIN
	BEGIN TRANSACTION NuevoSeguro
	BEGIN TRY
		DECLARE @id_tipseguro int
		DECLARE @id int
		SET @id_tipseguro = (SELECT id_tipseguro FROM tbl.TiposdeSeguros WHERE nom_tipseguro = @nom_tipseguro)
		INSERT INTO tbl.Seguros VALUES(0, @id_prima, @id_tipseguro, @id_cuenta)
		SELECT @id = id_seguro FROM tbl.Seguros WHERE id_cuenta = @id_cuenta
		COMMIT TRAN NuevoSeguro
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevoSeguro
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al agregar un nuevo seguro.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.NuevoBeneficiario(@nom_beneficiario varchar(50), @ape_beneficiario varchar(50), @nom_parentesco varchar(50), @porcentaje_porcentaje varchar(4), @id_seguro int)
AS
BEGIN
	BEGIN TRANSACTION NuevoBeneficiario
	BEGIN TRY
		DECLARE @id_parentesco int
		DECLARE @id_porcentaje int
		SET @id_parentesco = (SELECT id_parentesco FROM tbl.Parentescos WHERE nom_parentesco = @nom_parentesco)
		SET @id_porcentaje = (SELECT id_porcentaje FROM tbl.PorcentajesdeSeguros WHERE porcentaje_porcentaje = @porcentaje_porcentaje)
		INSERT INTO tbl.Beneficiarios VALUES(@nom_beneficiario, @ape_beneficiario, @id_parentesco, @id_porcentaje, @id_seguro)
		COMMIT TRAN NuevoBeneficiario
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevoBeneficiario
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al agregar un nuevo beneficiario.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.NuevoSeguro1Beneficiario(@id_prima int, @nom_tipseguro varchar(50), @id_cuenta int, @nom_beneficiario1 varchar(50), @ape_beneficiario1 varchar(50), @nom_parentesco1 varchar(50), @porcentaje_porcentaje1 varchar(4))
AS
BEGIN
	BEGIN TRANSACTION NuevoSeguro1Beneficiario
	BEGIN TRY
		DECLARE @id int
		EXEC pcd.NuevoSeguro @id_prima, @nom_tipseguro, @id_cuenta, @id_seguro = @id OUTPUT
		EXEC pcd.NuevoBeneficiario @nom_beneficiario1, @ape_beneficiario1, @nom_parentesco1, @porcentaje_porcentaje1, @id
		COMMIT TRAN NuevoSeguro1Beneficiario
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevoSeguro1Beneficiario
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al agregar un nuevo seguro con 1 beneficiario.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.NuevoSeguro2Beneficiarios(@id_prima int, @nom_tipseguro varchar(50), @id_cuenta int, @nom_beneficiario1 varchar(50), @ape_beneficiario1 varchar(50), @nom_parentesco1 varchar(50), @porcentaje_porcentaje1 varchar(4), @nom_beneficiario2 varchar(50), @ape_beneficiario2 varchar(50), @nom_parentesco2 varchar(50), @porcentaje_porcentaje2 varchar(4))
AS
BEGIN
	BEGIN TRANSACTION NuevoSeguro2Beneficiarios
	BEGIN TRY
		DECLARE @id int
		EXEC pcd.NuevoSeguro @id_prima, @nom_tipseguro, @id_cuenta, @id_seguro = @id OUTPUT
		EXEC pcd.NuevoBeneficiario @nom_beneficiario1, @ape_beneficiario1, @nom_parentesco1, @porcentaje_porcentaje1, @id
		EXEC pcd.NuevoBeneficiario @nom_beneficiario2, @ape_beneficiario2, @nom_parentesco2, @porcentaje_porcentaje2, @id
		COMMIT TRAN NuevoSeguro2Beneficiarios
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevoSeguro2Beneficiarios
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al agregar un nuevo seguro con 2 beneficiarios.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.NuevoSeguro3Beneficiarios(@id_prima int, @nom_tipseguro varchar(50), @id_cuenta int, @nom_beneficiario1 varchar(50), @ape_beneficiario1 varchar(50), @nom_parentesco1 varchar(50), @porcentaje_porcentaje1 varchar(4), @nom_beneficiario2 varchar(50), @ape_beneficiario2 varchar(50), @nom_parentesco2 varchar(50), @porcentaje_porcentaje2 varchar(4), @nom_beneficiario3 varchar(50), @ape_beneficiario3 varchar(50), @nom_parentesco3 varchar(50), @porcentaje_porcentaje3 varchar(4))
AS
BEGIN
	BEGIN TRANSACTION NuevoSeguro3Beneficiarios 
	BEGIN TRY
		DECLARE @id int
		EXEC pcd.NuevoSeguro @id_prima, @nom_tipseguro, @id_cuenta, @id_seguro = @id OUTPUT
		EXEC pcd.NuevoBeneficiario @nom_beneficiario1, @ape_beneficiario1, @nom_parentesco1, @porcentaje_porcentaje1, @id
		EXEC pcd.NuevoBeneficiario @nom_beneficiario2, @ape_beneficiario2, @nom_parentesco2, @porcentaje_porcentaje2, @id
		EXEC pcd.NuevoBeneficiario @nom_beneficiario3, @ape_beneficiario3, @nom_parentesco3, @porcentaje_porcentaje3, @id
		COMMIT TRAN NuevoSeguro3Beneficiarios
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevoSeguro3Beneficiarios
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al agregar un nuevo seguro con 3 beneficiarios.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.NuevoSeguro4Beneficiarios(@id_prima int, @nom_tipseguro varchar(50), @id_cuenta int, @nom_beneficiario1 varchar(50), @ape_beneficiario1 varchar(50), @nom_parentesco1 varchar(50), @porcentaje_porcentaje1 varchar(4), @nom_beneficiario2 varchar(50), @ape_beneficiario2 varchar(50), @nom_parentesco2 varchar(50), @porcentaje_porcentaje2 varchar(4), @nom_beneficiario3 varchar(50), @ape_beneficiario3 varchar(50), @nom_parentesco3 varchar(50), @porcentaje_porcentaje3 varchar(4), @nom_beneficiario4 varchar(50), @ape_beneficiario4 varchar(50), @nom_parentesco4 varchar(50), @porcentaje_porcentaje4 varchar(4))
AS
BEGIN
	BEGIN TRANSACTION NuevoSeguro4Beneficiarios 
	BEGIN TRY
		DECLARE @id int
		EXEC pcd.NuevoSeguro @id_prima, @nom_tipseguro, @id_cuenta, @id_seguro = @id OUTPUT
		EXEC pcd.NuevoBeneficiario @nom_beneficiario1, @ape_beneficiario1, @nom_parentesco1, @porcentaje_porcentaje1, @id
		EXEC pcd.NuevoBeneficiario @nom_beneficiario2, @ape_beneficiario2, @nom_parentesco2, @porcentaje_porcentaje2, @id
		EXEC pcd.NuevoBeneficiario @nom_beneficiario3, @ape_beneficiario3, @nom_parentesco3, @porcentaje_porcentaje3, @id
		EXEC pcd.NuevoBeneficiario @nom_beneficiario4, @ape_beneficiario4, @nom_parentesco4, @porcentaje_porcentaje4, @id
		COMMIT TRAN NuevoSeguro4Beneficiarios
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevoSeguro4Beneficiarios
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al agregar un nuevo seguro con 4 beneficiarios.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.Remesa(@monto_remesa money, @nomdestinatario_remesa varchar(100), @pin_remesa varchar(300), @num_cuenta varchar(300))
AS
BEGIN
	BEGIN TRANSACTION NuevaRemesa
	BEGIN TRY
		DECLARE @id_cuenta int
		DECLARE @id_estremesa int
		DECLARE @id_remesa int
		SET @id_cuenta = (SELECT id_cuenta FROM tbl.Cuentas WHERE hlp.desenc(num_cuenta) = @num_cuenta)
		SET @id_estremesa = (SELECT id_estremesa FROM tbl.EstadosdeRemesas WHERE nom_estremesa = 'Enviada')
		INSERT INTO tbl.Remesas VALUES(@monto_remesa, @nomdestinatario_remesa, hlp.enc(@pin_remesa), @id_cuenta)
		SET @id_remesa = (SELECT id_remesa FROM tbl.Remesas WHERE id_cuenta = @id_cuenta)
		INSERT INTO tbl.bitEstadosdeRemesas VALUES(@id_remesa, @id_estremesa)		
		COMMIT TRAN NuevaRemesa
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevaRemesa
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al agregar una nueva remesa.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.EfectuarRemesa(@nomdestinatario_remesa varchar(100), @pin_remesa varchar(300))
AS
BEGIN
	BEGIN TRANSACTION EfectuarRemesa 
	BEGIN TRY
		DECLARE @id_estremesa int
		DECLARE @id_remesa int
		SET @id_estremesa = (SELECT id_estremesa FROM tbl.EstadosdeRemesas WHERE nom_estremesa = 'Cobrada')
		SET @id_remesa = (SELECT id_remesa FROM tbl.Remesas WHERE nomdestinatario_remesa = @nomdestinatario_remesa AND hlp.desenc(pin_remesa) = @pin_remesa)
		UPDATE tbl.bitEstadosdeRemesas SET id_estremesa = @id_estremesa WHERE id_remesa = @id_remesa		
		COMMIT TRAN EfectuarRemesa
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN EfectuarRemesa
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al efectuar una remesa.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.NuevaCuenta(@correo_persona varchar(50), @num_cuenta varchar(300), @pin_cuenta varchar(300), @nom_tipcuenta varchar(50), @nom_estcuenta varchar(50))
AS
BEGIN
	BEGIN TRANSACTION NuevaCuenta
	BEGIN TRY
		DECLARE @id_persona int
		DECLARE @id_tipcuenta int
		DECLARE @id_estcuenta int
		DECLARE @id_cuenta int
		SET @id_persona = (SELECT id_persona FROM tbl.Personas WHERE correo_persona = @correo_persona)
		SET @id_tipcuenta = (SELECT id_tipcuenta FROM tbl.TiposdeCuentas WHERE nom_tipcuenta = @nom_tipcuenta)
		SET @id_estcuenta = (SELECT id_estcuenta FROM tbl.EstadosdeCuentas WHERE nom_estcuenta = @nom_estcuenta)
		INSERT INTO tbl.Cuentas VALUES(hlp.enc(@num_cuenta), hlp.enc(@pin_cuenta), @id_persona, @id_tipcuenta)
		SET @id_cuenta = (SELECT id_cuenta FROM tbl.Cuentas WHERE id_persona = @id_persona)
		INSERT INTO tbl.bitEstadosdeCuentas VALUES(@id_cuenta, @id_estcuenta)
		COMMIT TRAN NuevaCuenta
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevaCuenta
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al crear una cuenta.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.ModificarCuenta(@num_cuenta varchar(300), @pin_cuenta varchar(300), @nom_estcuenta varchar(50))
AS
BEGIN
	BEGIN TRANSACTION ModificarCuenta
	BEGIN TRY
		DECLARE @id_cuenta int
		DECLARE @id_estcuenta int
		SET @id_cuenta = (SELECT id_cuenta FROM tbl.Cuentas WHERE hlp.desenc(num_cuenta) = @num_cuenta)
		SET @id_estcuenta = (SELECT id_estcuenta FROM tbl.EstadosdeCuentas WHERE nom_estcuenta = @nom_estcuenta)
		UPDATE tbl.Cuentas SET pin_cuenta = hlp.enc(@pin_cuenta) WHERE id_cuenta = @id_cuenta
		UPDATE tbl.bitEstadosdeCuentas SET id_estcuenta = @id_estcuenta WHERE id_cuenta = @id_cuenta
		COMMIT TRAN ModificarCuenta
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ModificarCuenta
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al modificar una cuenta.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.NuevaTarjetadeCreditoyDebito(@num_tarjetadebito varchar(300), @num_tarjetacredito varchar(300), @id_cuenta int)
AS
BEGIN
	BEGIN TRANSACTION NuevaTarjetadeCreditoyDebito
	BEGIN TRY
		DECLARE @id_clatarjetadebito int
		DECLARE @id_clatarjetacredito int
		DECLARE @id_tarjetadebito int
		DECLARE @id_tarjetacredito int
		DECLARE @id_esttarjeta int
		SET @id_clatarjetadebito = (SELECT id_clatarjeta FROM tbl.ClasificaciondeTarjetas WHERE nom_clatarjeta = 'Clasica')
		SET @id_clatarjetacredito = (SELECT id_clatarjeta FROM tbl.ClasificaciondeTarjetas WHERE nom_clatarjeta = 'Standard')
		INSERT INTO tbl.Tarjetas VALUES(hlp.enc(@num_tarjetadebito), @id_cuenta, @id_clatarjetadebito)
		INSERT INTO tbl.Tarjetas VALUES(hlp.enc(@num_tarjetacredito), @id_cuenta, @id_clatarjetacredito)
		SET @id_tarjetadebito = (SELECT id_tarjeta FROM tbl.Tarjetas WHERE hlp.desenc(num_tarjeta) = @num_tarjetadebito)
		SET @id_tarjetacredito = (SELECT id_tarjeta FROM tbl.Tarjetas WHERE hlp.desenc(num_tarjeta) = @num_tarjetacredito)
		SET @id_esttarjeta = (SELECT id_esttarjeta FROM tbl.EstadosdeTarjetas WHERE nom_esttarjeta = 'No iniciada')
		INSERT INTO tbl.bitEstadosdeTarjetas VALUES(@id_esttarjeta, @id_tarjetadebito)
		INSERT INTO tbl.bitEstadosdeTarjetas VALUES(@id_esttarjeta, @id_tarjetacredito)
		INSERT INTO tbl.Saldos VALUES(0, @id_tarjetacredito)
		COMMIT TRAN NuevaTarjetadeCreditoyDebito
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevaTarjetadeCreditoyDebito
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al crear una tarjeta de credito y debito.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.NuevaTarjetadeDebito(@num_tarjetadebito varchar(300), @id_cuenta int)
AS
BEGIN
	BEGIN TRANSACTION NuevaTarjetadeDebito 
	BEGIN TRY
		DECLARE @id_clatarjetadebito int
		DECLARE @id_tarjetadebito int
		DECLARE @id_esttarjeta int
		SET @id_clatarjetadebito = (SELECT id_clatarjeta FROM tbl.ClasificaciondeTarjetas WHERE nom_clatarjeta = 'Clasica')
		INSERT INTO tbl.Tarjetas VALUES(hlp.enc(@num_tarjetadebito), @id_cuenta, @id_clatarjetadebito)
		SET @id_tarjetadebito = (SELECT id_tarjeta FROM tbl.Tarjetas WHERE hlp.desenc(num_tarjeta) = @num_tarjetadebito)
		SET @id_esttarjeta = (SELECT id_esttarjeta FROM tbl.EstadosdeTarjetas WHERE nom_esttarjeta = 'No iniciada')
		INSERT INTO tbl.bitEstadosdeTarjetas VALUES(@id_esttarjeta, @id_tarjetadebito)
		COMMIT TRAN NuevaTarjetadeDebito
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN NuevaTarjetadeDebito
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al crear una tarjeta de debito.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.ModificarEstadodeTarjeta(@num_tarjeta varchar(300), @nom_esttarjeta varchar(300))
AS
BEGIN
	BEGIN TRANSACTION ModificarEstadodeTarjeta 
	BEGIN TRY
		DECLARE @id_tarjeta int
		DECLARE @id_esttarjeta int
		SET @id_tarjeta = (SELECT id_tarjeta FROM tbl.Tarjetas WHERE hlp.desenc(num_tarjeta) = @num_tarjeta)
		SET @id_esttarjeta = (SELECT id_esttarjeta FROM tbl.EstadosdeTarjetas WHERE nom_esttarjeta = @nom_esttarjeta)
		UPDATE tbl.bitEstadosdeTarjetas SET id_esttarjeta = @id_esttarjeta WHERE id_tarjeta = @id_tarjeta
		COMMIT TRAN ModificarEstadodeTarjeta
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ModificarEstadodeTarjeta
		DECLARE @Mensaje NVARCHAR(4000) = 'Error al modificar el estado de una tarjeta.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.ActivarTarjeta(@num_tarjeta varchar(300), @nom_clatarjeta varchar(50))
AS
BEGIN
	BEGIN TRANSACTION ActivarTarjeta
	BEGIN TRY
		DECLARE @id_tarjeta int
		DECLARE @id_clatarjeta int
		DECLARE @id_esttarjeta int
		SET @id_tarjeta = (SELECT id_tarjeta FROM tbl.Tarjetas WHERE hlp.desenc(num_tarjeta) = @num_tarjeta)
		SET @id_clatarjeta = (SELECT id_clatarjeta FROM tbl.ClasificaciondeTarjetas WHERE nom_clatarjeta = @nom_clatarjeta)
		SET @id_esttarjeta = (SELECT id_esttarjeta FROM tbl.EstadosdeTarjetas WHERE nom_esttarjeta = 'Activa')
		UPDATE tbl.Tarjetas SET id_clatarjeta = @id_clatarjeta WHERE id_tarjeta = @id_tarjeta
		UPDATE tbl.bitEstadosdeTarjetas SET id_esttarjeta = @id_esttarjeta WHERE id_tarjeta = @id_tarjeta
		COMMIT TRAN ActivarTarjeta
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN ActivarTarjeta
		DECLARE @Mensaje NVARCHAR(4000) = 'Activando una tarjeta.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.AbonaraCuenta(@id_cuenta int, @monto money)
AS
BEGIN
	BEGIN TRANSACTION AbonaraCuenta
	BEGIN TRY
		DECLARE @ahorroactual money
		DECLARE @ahorronuevo money
		SET @ahorroactual = (SELECT saldo_ahorro FROM tbl.Ahorros WHERE id_cuenta = @id_cuenta)
		SET @ahorronuevo = (@ahorroactual + @monto)
		UPDATE tbl.Ahorros SET saldo_ahorro = @ahorronuevo WHERE id_cuenta = @id_cuenta
		COMMIT TRAN AbonaraCuenta
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN AbonaraCuenta
		DECLARE @Mensaje NVARCHAR(4000) = 'Abonando a una cuenta.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

CREATE PROC pcd.RetirardeCuenta(@id_cuenta int, @monto money)
AS
BEGIN
	BEGIN TRANSACTION RetirardeCuenta
	BEGIN TRY
		DECLARE @ahorroactual money
		DECLARE @ahorronuevo money
		SET @ahorroactual = (SELECT saldo_ahorro FROM tbl.Ahorros WHERE id_cuenta = @id_cuenta)
		SET @ahorronuevo = (@ahorroactual - @monto)
		UPDATE tbl.Ahorros SET saldo_ahorro = @ahorronuevo WHERE id_cuenta = @id_cuenta
		COMMIT TRAN RetirardeCuenta
		RETURN
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN RetirardeCuenta
		DECLARE @Mensaje NVARCHAR(4000) = 'Retirando de una cuenta.';
		DECLARE @Severidad INT = ERROR_SEVERITY();
		DECLARE @Estado INT = ERROR_STATE();
		RAISERROR (@Mensaje, @Severidad, @Estado) WITH LOG;
	END CATCH
END
GO

--Creacion de triggers

	--antesdedepositooretirodeahorro
	--despuesdedepositooretirodeahorro

	--FROM DELETED ON TRIGGER

	