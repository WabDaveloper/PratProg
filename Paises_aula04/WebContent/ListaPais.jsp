<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="Modelo.Pais, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar Paises</title>
</head>
<body>
<h2>LISTA DE PAISES</h2>
<%
	
	ArrayList<Pais> papais = (ArrayList<Pais>)request.getAttribute("paises");
	for(Pais pais:papais){
%>

<p>
<b>ID:</b><%= pais.getId() %><br>	
<b>NOME:</b><%= pais.getNome() %>	<br>
<b>POPULAÇÃO:</b><%=pais.getPopulacao() %>	<br>
<b>AREA:</b><%=pais.getArea() %><br>
</p>
<% } %>
</body>
</html>