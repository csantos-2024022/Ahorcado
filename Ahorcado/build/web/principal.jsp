<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Juego De Ahorcado</title>
        <link rel="stylesheet" href="css/css.css">
    </head>
    <body>
        <div class="game-wrapper">
            <div class="container">
                <h1>¡AHORCADO!</h1>
                
                <div class="timer">
                    Tiempo: <span id="cronometro">00:00</span>
                </div>

                <div class="game-info">
                    <div>Intentos restantes: <span id="intentos">6</span></div>
                    <div>Pista: <span id="pista">Presiona el boton de Iniciar para comenzar</span></div>
                </div>

                <div class="word-display" id="palabra">_ _ _ _ _ _ _ _</div>

                <div class="keyboard">
                    <div class="keyboard-row">
                        <button class="key">Q</button>
                        <button class="key">W</button>
                        <button class="key">E</button>
                        <button class="key">R</button>
                        <button class="key">T</button>
                        <button class="key">Y</button>
                        <button class="key">U</button>
                        <button class="key">I</button>
                        <button class="key">O</button>
                        <button class="key">P</button>
                    </div>
                    <div class="keyboard-row">
                        <button class="key">A</button>
                        <button class="key">S</button>
                        <button class="key">D</button>
                        <button class="key">F</button>
                        <button class="key">G</button>
                        <button class="key">H</button>
                        <button class="key">J</button>
                        <button class="key">K</button>
                        <button class="key">L</button>
                    </div>
                    <div class="keyboard-row">
                        <button class="key">Z</button>
                        <button class="key">X</button>
                        <button class="key">C</button>
                        <button class="key">V</button>
                        <button class="key">B</button>
                        <button class="key">N</button>
                        <button class="key">M</button>
                    </div>
                </div>

                <div>
                    <button class="btn-secondary" id="btnIniciar">iniciar</button>
                    <button class="btn-secondary" id="btnReiniciar">reiniciar</button>
                    <button class="btn-secondary" id="btnPausar">Pausar</button>
                </div>

                <div class="used-letters">
                    <strong>Letras usadas:</strong>
                    <div id="letrasUsadas"></div>
                </div>

                <div class="message" id="mensaje"></div>
            </div>
            <div class="ImagenReferencia">
                <h2>Ahorcado</h2>
                <p>No dejes que muera! HJWKQKJHKSJ</p>
                <img src="img/ahorcado.png" alt="Imagen de referencia">
            </div>
                <div id="modal-resultado" class="modal-overlay">
                   <div class="modal">
                        <span class="close-button">&times;</span>
                        <div class="modal-content">
                            <h2 id="modal-titulo"></h2>
                            <p id="modal-mensaje"></p>
                            <img id="modal-imagen" src="" alt="Resultado del juego"></div>
                    </div>
                </div>
        </div>
        <div> 
            </div>
        
        <script src="js/script.js"></script>
    </body>
</html>