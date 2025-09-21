<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Ahorcado - Iniciar sesión</title>
        <link rel="stylesheet" href="css/inicio.css">
        <script src="js/efectos-login.js" defer></script>
    </head>
    <body>
        <!-- Elementos geométricos flotantes para efectos visuales -->
        <div class="geometric-shape"></div>
        <div class="geometric-shape"></div>
        <div class="geometric-shape"></div>
        <div class="geometric-shape"></div>
        <div class="geometric-shape"></div>
        <div class="geometric-shape"></div>
        <div class="geometric-shape"></div>
        <div class="geometric-shape"></div>
        
        
        <!-- Contenedor principal del login -->
        <div class="login-container">
            <div class="card">
                <div class="card-header">
                    <h1>Bienvenido al Ahorcado</h1>
                    <p>Inicia sesión para comenzar el juego</p>
                </div>
                <div class="card-body">
                    <form action="Validar" method="post">
                        <div class="form-group">
                            <label for="username">Nombre de usuario</label>
                            <input type="text" id="username" name="txtNombre" required>
                        </div>
                        <div class="form-group">
                            <label for="pass">Contraseña</label>
                            <input type="password" id="pass" name="txtPass" required>
                        </div>
                        <button type="submit" name="btnIngresar" value="Ingresar" class="btn-start">Entrar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>