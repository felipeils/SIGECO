<%-- 
    Document   : mantenedorTipoViviendas
    Created on : 07-05-2016, 08:31:20 AM
    Author     : Joe-Xidu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>

        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>Mantenedor Tipo Viviendas
                <small>Opciones avanzadas</small>
            </h1>
        </section>
        <!-- Main content -->
        <section class="content animated fadeInDown">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Tipo Viviendas</h3>
                            <button type="submit" class="btn btn-primary pull-right" id="newItem">Nuevo</button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="mantenedor" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Participación</th>
                                        <th>Creado</th>
                                        <th>Modificado</th>
                                        <th>Estado</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${tiposVivienda}" var="tipoVivienda">
                                        <tr>
                                            <td>${tipoVivienda.nombre}</td>
                                            <td>${tipoVivienda.participacion}</td>
                                            <td>${tipoVivienda.creado}</td>
                                            <td>${tipoVivienda.modificado}</td>
                                            <td><span class="label label-${tipoVivienda.estado==1?"success":"danger"}">${tipoVivienda.nomEstado}</span></td>
                                            <td>
                                                <a class="btn btn-primary btn-xs btnVer" data-id="${tipoVivienda.idTipoVivienda}" data-url="getTipoVivienda.htm" data-original-title="Detalles" data-toggle="tooltip"><i class="fa fa-eye"></i></a>
                                                <a class="btn btn-primary btn-xs btnEditar" data-id="${tipoVivienda.idTipoVivienda}" data-url="getTipoVivienda.htm" data-original-title="Editar" data-toggle="tooltip"><i class="fa fa-pencil-square-o"></i></a>
                                                <a class="btn btn-primary btn-xs btnEliminar" data-id="${tipoVivienda.idTipoVivienda}" data-url="eliminaTipoVivienda.htm" data-original-title="Eliminar" data-toggle="tooltip"><i class="fa fa-times-circle"></i></a>
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
                        <h4 class="modal-title">Detalles Tipo Vivienda</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal form" id="addForm">
                            <input class="form-control" name="idTipoVivienda" id="idTipoVivienda" type="hidden">
                            <div class="form-group">
                                <label for="nombre" class="col-sm-2 control-label">Nombre</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="nombre" id="nombre" placeholder="Nombre" type="text" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="participacion" class="col-sm-2 control-label">Participación</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="participacion" id="participacion" placeholder="Participación" type="number" required="required">
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
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="addNew" data-controller="tipoVivienda" data-url="nuevoTipoVivienda.htm">Guardar</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>

    </jsp:body>
</t:Master>