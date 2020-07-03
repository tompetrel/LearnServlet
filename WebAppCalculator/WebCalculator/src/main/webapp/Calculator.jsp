<%-- 
    Document   : Calculator
    Created on : Jul 4, 2020, 12:57:20 AM
    Author     : TAI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculator</title>
    </head>
    <body>
        <form action="CalculatorController">
            <h1 style="color: blue">Calculator</h1>
            Number 1: <input type="text" name="num1"><br><br>
            Number 2: <input type="text" name="num2"><br><br>
            <input type="submit" name="action" value="+" >
            <input type="submit" name="action" value="-" >
            <input type="submit" name="action" value="*" >
            <input type="submit" name="action" value="/" >
        </form>
    </body>
</html>
