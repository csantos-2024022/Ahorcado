<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ahorcado - Iniciar sesión</title>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <div class="login-container">
            <div class="card">
                <div class="card-header">
                    <h1>Bienvenido al Ahorcado</h1>
                    <p>Inicia sesión para comenzar el juego.</p>
                </div>
                <div class="card-body">
                    <form action="principal.jsp" method="post">
                        <div class="form-group">
                            <label for="username">Nombre de usuario</label>
                            <input type="text" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <input type="password" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn-start">Entrar</button>
                    </form>
                    <p class="signup-text">¿No tienes cuenta? <a href="#">Regístrate</a></p>
                </div>
            </div>
        </div>
    </body>
</html>