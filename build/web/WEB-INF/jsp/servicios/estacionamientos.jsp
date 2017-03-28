<%-- 
    Document   : historial
    Created on : 07-05-2016, 08:31:20 AM
    Author     : Joe-Xidu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>Reserva Estacionamientos
            </h1>
        </section>
        <!-- Main content -->
        <section class="content animated fadeInDown">
            <div class="row">
                <div class="col-xs-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#activity" data-toggle="tab">Estacionamientos</a></li>
                            <li><a href="#timeline" data-toggle="tab">Reservas</a></li>
                            <li><a href="#settings" data-toggle="tab">Mantenimiento</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="activity">
                                <div class="box box-default">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Estacionamiento #1</h3>
                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <div class="clearfix">
                                            <span class="pull-left">Ubicación Piso 1</span>
                                            <small class="pull-right">45%</small>
                                        </div>
                                        <div class="progress active">
                                            <div class="progress-bar progress-bar-primary progress-bar-striped" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.box-body -->
                                </div>
                                <div class="box box-default">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Estacionamiento #2</h3>
                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <div class="clearfix">
                                            <span class="pull-left">Ubicación Piso 1</span>
                                            <small class="pull-right">75%</small>
                                        </div>
                                        <div class="progress active">
                                            <div class="progress-bar progress-bar-primary progress-bar-striped" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.box-body -->
                                </div>
                                <div class="box box-default">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Estacionamiento #3</h3>
                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <div class="clearfix">
                                            <span class="pull-left">Ubicación Piso 1</span>
                                            <small class="pull-right">0%</small>
                                        </div>
                                        <div class="progress active">
                                            <div class="progress-bar progress-bar-primary progress-bar-striped" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.box-body -->
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="timeline">
                                <button type="submit" class="btn btn-primary pull-right" id="newItem">Nueva Reserva</button>
                                <table id="mantenedor" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Usuario</th>
                                            <th>Fecha</th>
                                            <th>Inicio</th>
                                            <th>Termino</th>
                                            <th>Valor</th>
                                            <th>Estado</th>
                                            <th>Observaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${perfiles}" var="perfil">
                                            <tr>
                                                <td>${perfil.idPerfil}</td>
                                                <td>${perfil.nombre}</td>
                                                <td>${perfil.creado}</td>
                                                <td>${perfil.modificado}</td>
                                                <td><span class="label label-${perfil.estado==1?"success":"danger"}">${perfil.nomEstado}</span></td>
                                                <td>
                                                    <a class="btn btn-primary btn-xs btnVer" data-id="${perfil.idPerfil}" data-url="getPerfil.htm" data-original-title="Detalles" data-toggle="tooltip"><i class="fa fa-eye"></i></a>
                                                    <a class="btn btn-primary btn-xs btnEditar" data-id="${perfil.idPerfil}" data-url="getPerfil.htm" data-original-title="Editar" data-toggle="tooltip"><i class="fa fa-pencil-square-o"></i></a>
                                                    <a class="btn btn-primary btn-xs btnEliminar" data-id="${perfil.idPerfil}" data-url="eliminaPerfil.htm" data-original-title="Eliminar" data-toggle="tooltip"><i class="fa fa-times-circle"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot></tfoot>
                                </table>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="settings">

                            </div>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <div class="modal" id="new">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">Detalles Condominio</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal form" id="addForm">
                            <input class="form-control" name="idReserva" id="idReserva" type="hidden">
                            <div class="form-group">
                                <label for="fechas" class="col-sm-2 control-label">Fechas</label>
                                <div class="col-sm-10">
                                    <div class="input-group">
                                        <div class="input-group-addon">
                                            <i class="fa fa-clock-o"></i>
                                        </div>
                                        <input name="fechas" id="fechas"  type="text" class="form-control pull-right" required="required">
                                    </div>
                                </div>
                                <!-- /.input group -->
                            </div>
                            <div class="form-group">
                                <label for="usuarioId" class="col-sm-2 control-label">Usuario</label>
                                <div class="col-sm-10">
                                    <select name="usuarioId" id="usuarioId" class="form-control" style="width: 100%;" required="required">
                                        <c:forEach items="${usuarios}" var="usuario">
                                            <option value="${usuario.idUsuario}">${usuario.usuario}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="estado" class="col-sm-2 control-label">Estado</label>
                                <div class="col-sm-10">
                                    <select name="estado" id="estado" class="form-control" style="width: 100%;" required="required">
                                        <c:forEach items="${estados}" var="estado">
                                            <option value="${estado.idEstado}">${estado.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="observaciones" class="col-sm-2 control-label">Observaciones</label>
                                <div class="col-sm-10">
                                    <textarea name="observaciones" id="observaciones" placeholder="Observaciones" class="form-control" rows="3" placeholder="Observaciones..."></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="addNew" data-controller="estacionamientos" data-url="reservaEstacionamiento.htm">Guardar</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
        <!-- /.content -->
    </jsp:body>
</t:Master>