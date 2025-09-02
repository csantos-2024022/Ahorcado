        const palabras = [
            { palabra: "BELINGHAM", pista: "Jugador Numero 5 Del Real Madrid F.C." },
            { palabra: "COMPUTADORA", pista: "Máquina para procesar información" },
            { palabra: "UNIVERSO", pista: "Lugar en donde estan las estrellas y planetas" },
            { palabra: "INTERNET", pista: "Red mundial de computadoras" },
            { palabra: "BERNABEU", pista: "Estadio del Real Madrid" }
        ];

        let palabraActual = "";
        let palabraOculta = "";
        let letrasUsadas = [];
        let intentos = 6;
        let juegoIniciado = false;
        let tiempoInicio = null;
        let cronometro = null;
        let pistaActual = "";

        function iniciarJuego() {
            const palabraObj = palabras[Math.floor(Math.random() * palabras.length)];
            palabraActual = palabraObj.palabra;
            pistaActual = palabraObj.pista;
            
            palabraOculta = "_".repeat(palabraActual.length);
            
            letrasUsadas = [];
            intentos = 6;
            juegoIniciado = true;
            
            // Iniciar cronómetro
            tiempoInicio = Date.now();
            iniciarCronometro();
            
            // Actualizar interfaz
            actualizarPantalla();
            document.getElementById("letraInput").disabled = false;
            document.getElementById("btnAdivinar").disabled = false;
            document.getElementById("pista").textContent = pistaActual;
            
            mostrarMensaje("Juego iniciado! Adivina la palabra letra por letra", "info");
        }

        

        function victoria() {
            juegoIniciado = false;
            pararCronometro();
            document.getElementById("letraInput").disabled = true;
            document.getElementById("btnAdivinar").disabled = true;
            
            const tiempo = obtenerTiempoTranscurrido();
            mostrarMensaje(`ganaste! La palabra era "${palabraActual}". Tiempo: ${tiempo}`, "success");
        }

        function derrota() {
            juegoIniciado = false;
            pararCronometro();
            document.getElementById("letraInput").disabled = true;
            document.getElementById("btnAdivinar").disabled = true;
            
            mostrarMensaje(`perdiste! La palabra era "${palabraActual}"`, "error");
        }

        function reiniciarJuego() {
            juegoIniciado = false;
            pararCronometro();
            
            palabraActual = "";
            palabraOculta = "";
            letrasUsadas = [];
            intentos = 6;
            tiempoInicio = null;
            
            document.getElementById("letraInput").disabled = true;
            document.getElementById("btnAdivinar").disabled = true;
            document.getElementById("letraInput").value = "";
            document.getElementById("pista").textContent = "Presiona INICIAR para comenzar";
            document.getElementById("cronometro").textContent = "00:00";
            
            actualizarPantalla();
            mostrarMensaje("Presiona INICIAR para comenzar un nuevo juego", "info");
        }

        function actualizarPantalla() {
            document.getElementById("palabra").textContent = palabraOculta.split("").join(" ");
            
            document.getElementById("intentos").textContent = intentos;
            
            const letrasDiv = document.getElementById("letrasUsadas");
            letrasDiv.innerHTML = "";
            letrasUsadas.forEach(letra => {
                const span = document.createElement("span");
                span.textContent = letra;
                letrasDiv.appendChild(span);
            });
        }

        function mostrarMensaje(texto, tipo) {
            const mensaje = document.getElementById("mensaje");
            mensaje.textContent = texto;
            mensaje.className = `message ${tipo}`;
        }

        function iniciarCronometro() {
            cronometro = setInterval(() => {
                const tiempoActual = Date.now();
                const tiempoTranscurrido = Math.floor((tiempoActual - tiempoInicio) / 1000);
                const minutos = Math.floor(tiempoTranscurrido / 60);
                const segundos = tiempoTranscurrido % 60;
                
                document.getElementById("cronometro").textContent = 
                    `${minutos.toString().padStart(2, '0')}:${segundos.toString().padStart(2, '0')}`;
            }, 1000);
        }

        function pararCronometro() {
            if (cronometro) {
                clearInterval(cronometro);
                cronometro = null;
            }
        }

        function obtenerTiempoTranscurrido() {
            if (!tiempoInicio) return "00:00";
            
            const tiempoTotal = Date.now() - tiempoInicio;
            const tiempoEnSegundos = Math.floor(tiempoTotal / 1000);
            const minutos = Math.floor(tiempoEnSegundos / 60);
            const segundos = tiempoEnSegundos % 60;
            
            return `${minutos.toString().padStart(2, '0')}:${segundos.toString().padStart(2, '0')}`;
        }

        // Mensaje inicial
        mostrarMensaje("¡Bienvenido al Ahorcado Simple! Presiona INICIAR para comenzar.", "info");
        
        