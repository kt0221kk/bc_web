<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.*" %>
        <html>

        <head>
            <title>Sample Application JSP Page</title>
        </head>

        <body bgcolor=white>

            <table border="0">
                <tr>
                    <td align=center>
                        <img src="images/tomcat.gif">
                    </td>
                    <td>
                        <h1>JSPで動作中</h1>
                    </td>
                </tr>
            </table>

            <p>現在時刻 <%= new Date()%>
            </p>

        </body>

        </html>