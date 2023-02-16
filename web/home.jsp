<%-- 
    Document   : home
    Created on : 16-feb-2023, 8:33:41
    Author     : hugop
--%>

<%@page import="entities.Usuario"%>
<%@page import="entities.Tipo"%>
<%@page import="java.util.List"%>
<%@page import="entities.Modelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Bootstrap Example</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%List<Modelo> modelos = (List<Modelo>) session.getAttribute("modelos");
      List<Tipo> tipos = (List<Tipo>) session.getAttribute("tipos");
      Usuario usuario = (Usuario) session.getAttribute("usuario");%>
  <!-- Header -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#"><img src="img/logo.png" alt="Logo" height="50"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link text-dark" href="Controller?op=inicio">TODOS</a>
        </li>
        <%for(Tipo tipo : tipos) {%>
        <li class="nav-item">
          <a class="nav-link text-dark" href="Controller?op=filtered&filter=<%=tipo.getId()%>"><%=tipo.getNombre()%></a>
        </li>
        <%}%>
      </ul>
      <div id="right" class="ml-auto text-center">
          <%if(usuario!=null){%>
        <div class="flex-column">
            <span class="navbar-text text-success">
              Bienvenido <%=usuario.getNick()%>
            </span>
      </div>
            <div class="d-flex">
      <button class="btn btn-primary ml-2" data-toggle="modal" data-target="#modalCita">Pedir cita</button>
      <a href="Controller?op=logout"><button class="btn btn-success ml-2">Logout</button></a>
      </div>
      <%} else {%>
                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modelLogin">Login</button>
      <%}%>
    </div>
    </div>
  </nav>

  <!-- Container -->
  <div class="container-fluid">
    <div class="row">
        <%for (Modelo modelo : modelos){%>
      <div class="col-lg-4 col-md-6 col-sm-12 mt-3">
          <a href="" data-toggle="modal" data-target="#modelAcabado" data-nombre="<%=modelo.getNombre()%>" data-id="<%=modelo.getId()%>">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><%=modelo.getNombre()%></h5>
              </div>
          <img class="card-img-top" src="img/<%=modelo.getImagen()%>" alt="Foto">
        </div>
        </a>
      </div>
      <%}%>
    </div>
      </div>
      <!-- Footer -->
      <footer class="bg-dark py-3">
        <div class="text-center text-white font-weight-bold">
          <p>Hugo Polo - Azarquiel</p>
        </div>
      </footer>      
      
        <!-- Modal de login-->
        <div class="modal fade" id="modelLogin" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Login/Register</h5>
            </div>
            <form action="Controller?op=login" method="POST">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="exampleInputNick">Nick</label>
                        <input type="text" class="form-control" name="nick" placeholder="Enter Nick" required="">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPass">Password</label>
                        <input type="password" class="form-control" name="pass" placeholder="Enter Password"  required="">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                    <button class="btn btn-primary" type="submit">Login/Register</button>
                </div>
            </form>
        </div>
    </div>
</div>
        
        <!-- Modal  del acabado-->
    <div class="modal fade" id="modelAcabado">
        <div class="modal-dialog">
          <div class="modal-content">
          
            <!-- Encabezado de la ventana modal -->
            <div class="modal-header bg-dark text-white text-center justify-content-center d-flex">
              <h4 class="modal-title"></h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
            <!-- Cuerpo de la ventana modal -->
            <div class="modal-body justify-content-center text-center" id="acc">         
            <!--Insercion JS-->    
            </div>
            
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Aceptar</button>
            </div>
          </div>
        </div>
      </div>
        
        <div class="modal fade" id="modalCita" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center bg-dark text-white d-flex justify-content-center">
                    <h5 class="modal-title" id="exampleModalLabel">Reserva de cita</h5>
                </div>
                <form action="Controller?op=reserva" method="POST">
                    <div class="modal-body">
                        <div class="form-group justify-content-center text-center">
                            <%if(usuario!=null){%>
                            <p>Se va a proceder a registrar cita a <%=usuario.getNick()%> a fecha de hoy del siguiente modelo:</p> <%}%>
                            <select class="form-control mx-auto text-center" name="reserva" id="reserva">
                                <option selected disabled value="x">Elija modelo</option>
                                <%for (Modelo modelo : modelos){%>
                                <option value="<%=modelo.getId()%>"><%=modelo.getNombre()%></option>
                                <%}%>
                            </select>
                            <p class="mt-3">Le avisaremos informando del dia que puede realizar la prueba</p>
                        </div>
                       
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button class="btn btn-primary" type="submit">Aceptar</button>
                    </div>
                </form>
            </div>
        </div>
    </div> 
        
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
      <script src="./js/myjs.js"></script>
    </body>
    </html>
    
    
    
