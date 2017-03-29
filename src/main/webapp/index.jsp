<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dvsmedeiros.domain.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Prova LES</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <style>
    body {
        padding-top: 20px;
        /* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
    }
    </style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
    <!-- Page Content -->
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                	<div class="panel-heading text-center" >
                		<h2>CADASTRO</h2>
                	</div>
                	<div class="panel-body">
                		
                		<form>
                			<div class="col-lg-6 row">
	                			<div class="col-lg-12">
		                			<div class="form-group">
		                				<label class="control-label">Nome</label>
		                				<input class="form-control" type="text" name="nome">
		                			</div>
		                			<div class="form-group">
		                				<label class="control-label">Sobrenome</label>
		                				<input class="form-control" type="text" name="nome">
		                			</div>
		                			<a type="submit" class="btn btn-success">
										Salvar
									</a>
								</div>
                			</div>
                		</form>
                		
                	</div>
                </div>
            </div>
        </div>
        <!-- /.row -->
        <%
        	List<Pessoa> pessoas = (List<Pessoa>) request.getAttribute("pessoas");
        	if(pessoas != null && pessoas.size() > 0) {
 		%>
        <div class="row">
        	<div class="col-lg-12">
        		<table class="table">
        			<tr>
        				<th>#</th>
        				<th>Nome</th>
        				<th>Sobrenome</th>
        			</tr>
        			<%for(Pessoa p : pessoas) { %>
        			<tr>
        				<td><%= p.getId() %></td>
        				<td><%= p.getNome() %></td>
        				<td><%= p.getSobrenome() %></td>
        			</tr>
        			<%} %>
        		</table>
        	</div>
        </div>
        <%} %>

    </div>
    <!-- /.container -->

    <!-- jQuery Version 1.11.1 -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
