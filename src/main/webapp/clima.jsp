<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
 -->
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>

<p>Hola!</p>

<form action="javascript:void(0);">
      <h2>Clima para el día</h2>
         <div><input id="dia" placeholder="Dia"></input></div>
         <div><input id="obtenerClima" type="submit" value="obtener"></div>
    </form>
    <div id="respuesta"></div>
    <script type="text/javascript">
        function init() {
                //Parameters are APIName,APIVersion,CallBack function,API Root 
                //gapi.client.load('clima', 'v1', null, 'http://localhost:8080/_ah/api');
                document.getElementById('obtenerClima').onclick = function() {
                    obtenerClima();
                  }
        }
        function obtenerClima() {
            var _Dia = document.getElementById('dia').value;
            
            var restRequest = gapi.client.request({
            	  'path': 'http://localhost:8080/_ah/api/clima/v1/clima/' + _Dia
            	});
            	restRequest.then(function(resp) {
            	  console.log(resp.result);
            	  document.getElementById('respuesta').innerHTML = resp.result.clima;
            	}, function(reason) {
            	  console.log('Error: ' + reason.result.error.message);
            	});
    }
        </script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>
<a href="http://localhost:8080/_ah/api/clima/v1/init">Init</a>
<a href="http://localhost:8080/_ah/api/clima/v1/delete">Borrar</a>
-------
</body>
</html>