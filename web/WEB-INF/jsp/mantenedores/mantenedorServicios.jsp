<%-- 
    Document   : mantenedorServicios
    Created on : 07-05-2016, 08:31:20 AM
    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>

        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>Mantenedor Servicios
                <small>Opciones avanzadas</small>
            </h1>
        </section>
        <!-- Main content -->
        <section class="content animated fadeInDown">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Servicios</h3>
                            <button type="submit" class="btn btn-primary pull-right" id="newItem">Nuevo</button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="mantenedor" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Id servicio</th>
                                        <th>Nombre</th>
                                        <th>Detalle</th>
                                        <th>Valor Base</th>
                                        <th>Estado</th>
                                        <th>Creado</th>
                                        <th>Modificado</th>
                                        <th>Nombre Condominio</th>
                                        <th>Mantenedor</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${servicios}" var="servicio">
                                        <tr>
                                            <td>${servicio.idServicio}</td>
                                            <td>${servicio.nombre}</td>
                                            <td>${servicio.detalle}</td>
                                            <td>${servicio.valorBase}</td>
                                            <td>${servicio.nomEstado}</td>
                                            <td>${servicio.creado}</td>
                                            <td>${servicio.modificado}</td>
                                            <td>${servicio.nomCondominio}</td>
                                            
                                            <td><span class="label label-${servicio.estado==1?"success":"danger"}">${servicio.nomEstado}</span></td>
                                            <td>
                                                <a class="btn btn-primary btn-xs btnVer" data-id="${perfil.idServicio}" data-url="getServicio.htm" data-original-title="Detalles" data-toggle="tooltip"><i class="fa fa-eye"></i></a>
                                                <a class="btn btn-primary btn-xs btnEditar" data-id="${perfil.idServicio}" data-url="getServicio.htm" data-original-title="Editar" data-toggle="tooltip"><i class="fa fa-pencil-square-o"></i></a>
                                                <a class="btn btn-primary btn-xs btnEliminar" data-id="${perfil.idServicio}" data-url="eliminaServicio.htm" data-original-title="Eliminar" data-toggle="tooltip"><i class="fa fa-times-circle"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot></tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
        <div class="modal" id="new">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">Detalles Servicio</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal form" id="addForm">
                            <input class="form-control" name="idServicio" id="idServicio" type="hidden">
                            <div class="form-group">
                                <label for="nombre" class="col-sm-2 control-label">Nombre</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="nombre" id="nombre" placeholder="Nombre" type="text" required="required">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="detalle" class="col-sm-2 control-label">Detalle</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="detalle" id="detalle" placeholder="Detalle" type="text" required="required">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="valorBase" class="col-sm-2 control-label">ValorBase</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="detalle" id="valorBase" placeholder="valorBase" type="text" required="required">
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
                            <input class="form-control" name="idCondominio" id="nomCondominio" type="hidden">
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="addNew" data-controller="perfil" data-url="nuevoPerfil.htm">Guardar</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
    </jsp:body>
</t:Master>
