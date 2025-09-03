    // --- Datos y estado ---
    const palabras = [
        { palabra: "BELLINGHAM", pista: "Jugador Numero 5 Del Real Madrid F.C." },
        { palabra: "INGLATERRA", pista: "Pais que su bandera es blanco y rojo, con una cruz al medio" },
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

    const palabraDisplay = document.getElementById("palabra");
    const intentosDisplay = document.getElementById("intentos");
    const pistaDisplay = document.getElementById("pista");
    const letrasUsadasDisplay = document.getElementById("letrasUsadas");
    const mensajeDisplay = document.getElementById("mensaje");
    const botonesTeclado = document.querySelectorAll(".keyboard .key");
    const btnIniciar = document.getElementById("btnIniciar");
    const btnReiniciar = document.getElementById("btnReiniciar");

    const imagenAhorcadoElem = document.getElementById("imagenAhorcado") || document.querySelector(".ImagenReferencia img");

    const imagenesAhorcado = [
        "img/ahorcado.png", 
        "img/cabeza.png", 
        "img/torzo.png",   
        "img/brazo1.png",   
        "img/brazo2.png",  
        "img/pierna1.png",
        "img/pierna2.png"   
    ];



    if (btnIniciar) btnIniciar.addEventListener("click", () => iniciarJuego());
    if (btnReiniciar) btnReiniciar.addEventListener("click", () => reiniciarJuego());

    // Teclado en pantalla
    botonesTeclado.forEach(boton => {
        boton.addEventListener("click", () => {
            const letra = boton.textContent ? boton.textContent.trim().toUpperCase() : "";
            if (!letra || !juegoIniciado) return;
            manejarClickLetra(letra);
        });
    });

    window.addEventListener("keydown", (e) => {
        if (!juegoIniciado) return;
        const key = e.key.toUpperCase();
        if (/^[A-ZÑ]$/.test(key)) {
            manejarClickLetra(key);
        }
    });

    function iniciarJuego() {
        if (juegoIniciado) return;

        const palabraObj = palabras[Math.floor(Math.random() * palabras.length)];
        palabraActual = (palabraObj.palabra || "").toUpperCase();
        pistaActual = palabraObj.pista || "";

        palabraOculta = "_".repeat(palabraActual.length);

        letrasUsadas = [];
        intentos = 6;
        juegoIniciado = true;

        tiempoInicio = Date.now();
        iniciarCronometro();

        actualizarPantalla();
        pistaDisplay.textContent = pistaActual;
        if (imagenAhorcadoElem) imagenAhorcadoElem.src = imagenesAhorcado[0];

        habilitarTeclado();
        mostrarMensaje("Juego iniciado! Adivina la palabra letra por letra", "info");
    }

    function manejarClickLetra(letra) {
        letra = letra.toUpperCase();

        if (!juegoIniciado) return;
        if (letrasUsadas.includes(letra)) {
            mostrarMensaje(`Ya usaste la letra "${letra}".`, "info");
            return;
        }

        letrasUsadas.push(letra);
        const boton = Array.from(botonesTeclado).find(b => b.textContent.trim().toUpperCase() === letra);
        if (boton) {
            boton.disabled = true;
            boton.classList.add("used"); 
        }
        actualizarLetrasUsadas();

        let letraEncontrada = false;
        let nuevaPalabraOculta = "";

        for (let i = 0; i < palabraActual.length; i++) {
            if (palabraActual[i] === letra) {
                nuevaPalabraOculta += letra;
                letraEncontrada = true;
            } else {
                // si palabraOculta está vacío (caso inicial), poner _
                nuevaPalabraOculta += palabraOculta[i] || "_";
            }
        }

        palabraOculta = nuevaPalabraOculta;

        if (!letraEncontrada) {
            intentos--;
            actualizarImagenAhorcado();
            mostrarMensaje(`La letra "${letra}" no está en la palabra. Intentos restantes: ${intentos}`, "error");
        } else {
            mostrarMensaje(`Correcto! La letra "${letra}" se encontró.`, "success");
        }

        actualizarPantalla();

        if (palabraOculta === palabraActual) {
            victoria();
        } else if (intentos <= 0) {
            derrota();
        }
    }

    function victoria() {
        juegoIniciado = false;
        pararCronometro();
        deshabilitarTeclado();
        mostrarMensaje(`¡Ganaste! La palabra era "${palabraActual}"`, "success");
        if (imagenAhorcadoElem) imagenAhorcadoElem.src = imagenesAhorcado[0];
    }

    function derrota() {
        juegoIniciado = false;
        pararCronometro();
        deshabilitarTeclado();
        if (imagenAhorcadoElem) imagenAhorcadoElem.src = imagenesAhorcado[imagenesAhorcado.length - 1];
        mostrarMensaje(`Perdiste! La palabra era "${palabraActual}"`, "error");
    }

    function reiniciarJuego() {
        juegoIniciado = false;
        pararCronometro();

        palabraActual = "";
        palabraOculta = "";
        letrasUsadas = [];
        intentos = 6;
        tiempoInicio = null;

        actualizarPantalla();
        pistaDisplay.textContent = "Presiona el boton de Iniciar para comenzar";
        const cron = document.getElementById("cronometro");
        if (cron) cron.textContent = "00:00";
        mostrarMensaje("Presiona INICIAR para comenzar un nuevo juego", "info");

        // Reset visual del teclado
        habilitarTeclado();

        if (imagenAhorcadoElem) imagenAhorcadoElem.src = imagenesAhorcado[0];
    }

    function deshabilitarTeclado() {
        botonesTeclado.forEach(boton => boton.disabled = true);
    }

    function habilitarTeclado() {
        botonesTeclado.forEach(boton => {
            boton.disabled = false;
            boton.classList.remove("used");
        });
    }

    function actualizarPantalla() {
        palabraDisplay.textContent = (palabraOculta || "").split("").join(" ");
        intentosDisplay.textContent = intentos;
        actualizarLetrasUsadas();
    }

    function actualizarLetrasUsadas() {
        letrasUsadasDisplay.innerHTML = "";
        letrasUsadas.forEach(letra => {
            const span = document.createElement("span");
            span.textContent = letra;
            letrasUsadasDisplay.appendChild(span);
        });
    }

    function mostrarMensaje(texto, tipo) {
        mensajeDisplay.textContent = texto;
        mensajeDisplay.className = `message ${tipo || ""}`;
    }

    function actualizarImagenAhorcado() {
        if (!imagenAhorcadoElem) return;
        const fallos = 6 - intentos; // 0..6
        let index = fallos;
        if (index < 0) index = 0;
        if (index >= imagenesAhorcado.length) index = imagenesAhorcado.length - 1;
        imagenAhorcadoElem.src = imagenesAhorcado[index];
    }

    function iniciarCronometro() {
        const cron = document.getElementById("cronometro");
        if (!cron) return;
        if (cronometro) clearInterval(cronometro);
        cronometro = setInterval(() => {
            const ahora = Date.now();
            const diff = Math.floor((ahora - tiempoInicio) / 1000);
            const min = Math.floor(diff / 60);
            const seg = diff % 60;
            cron.textContent = `${min.toString().padStart(2, "0")}:${seg.toString().padStart(2, "0")}`;
        }, 1000);
    }

    function pararCronometro() {
        if (cronometro) {
            clearInterval(cronometro);
            cronometro = null;
        }
    }

    // Exponer las funciones que tu HTML llama via onclick para compatibilidad con tu JSP
    window.iniciarJuego = iniciarJuego;
    window.reiniciarJuego = reiniciarJuego;
    window.pararCronometro = pararCronometro;

    // Mensaje inicial y estado
    mostrarMensaje("Bienvenido al Ahorcado! Presiona INICIAR para comenzar.", "info");
    deshabilitarTeclado();
    if (imagenAhorcadoElem) imagenAhorcadoElem.src = imagenesAhorcado[0];


