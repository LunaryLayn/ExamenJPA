<%-- 
    Document   : acabado
    Created on : 16-feb-2023, 10:05:07
    Author     : hugop
--%>

<%@page import="entities.Itemacabado"%>
<%@page import="entities.Modelo"%>
<%@page import="java.util.List"%>
<%@page import="entities.Modeloacabado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%List<Modeloacabado> acabados = (List<Modeloacabado>) session.getAttribute("acabados");

      Modelo modelo = (Modelo) session.getAttribute("modeloselected");
      int i = 0;  %>

<img src="img/<%=modelo.getImagen()%>" alt="Foto" class="img-fluid">

<%if(acabados!=null){%>
<div class="bg-dark text-white text-center font-weight-bold py-3">Acabados</div>

    <div id="accordion" class="accordion text-center">
        <%for(Modeloacabado acabado : acabados) {
            i++;
            List<Itemacabado> itemsacabados = acabado.getItemacabadoList();
        %>
        <div class="card">
          <div class="card-header" id="heading<%=i%>">
            <h5 class="mb-0">
              <button class="btn btn-link" data-toggle="collapse" data-target="#collapse<%=i%>" aria-expanded="true" aria-controls="collapse<%=i%>">
                <%=acabado.getAcabado().getNombre()%>
              </button>
            </h5>
          </div>
      
          <div id="collapse<%=i%>" class="collapse" aria-labelledby="heading<%=i%>" data-parent="#accordion">
            <div class="card-body">
                <%for(Itemacabado itemacabado : itemsacabados){%>
                <p><%=itemacabado.getNombre()%></p>
                <%}%>
            </div>
          </div>
        </div>
</div>
<%}}%>
