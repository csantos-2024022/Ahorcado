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

// Event listeners para los botones de control
btnIniciar.addEventListener("click", iniciarJuego);
btnReiniciar.addEventListener("click", reiniciarJuego);

// Añadir event listener a cada botón del teclado
botonesTeclado.forEach(boton => {
    boton.addEventListener("click", () => {
        if (juegoIniciado) {
            manejarClickLetra(boton.textContent);
        }
    });
});

function iniciarJuego() {
    if (juegoIniciado) return; 

    const palabraObj = palabras[Math.floor(Math.random() * palabras.length)];
    palabraActual = palabraObj.palabra;
    pistaActual = palabraObj.pista;

    palabraOculta = "_".repeat(palabraActual.length);

    letrasUsadas = [];
    intentos = 6;
    juegoIniciado = true;

    tiempoInicio = Date.now();
    iniciarCronometro();

    // Actualizar interfaz
    actualizarPantalla();
    pistaDisplay.textContent = pistaActual;
    
    // Habilitar botones del teclado
    botonesTeclado.forEach(boton => boton.disabled = false);

    mostrarMensaje("Juego iniciado! Adivina la palabra letra por letra", "info");
}

function manejarClickLetra(letra) {
    letra = letra.toUpperCase();

    if (letrasUsadas.includes(letra) || !juegoIniciado) {
        return;
    }

    letrasUsadas.push(letra);
    actualizarLetrasUsadas();

    // Comprobar si la letra está en la palabra
    let letraEncontrada = false;
    let nuevaPalabraOculta = "";

    for (let i = 0; i < palabraActual.length; i++) {
        if (palabraActual[i] === letra) {
            nuevaPalabraOculta += letra;
            letraEncontrada = true;
        } else {
            nuevaPalabraOculta += palabraOculta[i];
        }
    }

    palabraOculta = nuevaPalabraOculta;

    if (!letraEncontrada) {
        intentos--;
        mostrarMensaje(`La letra "${letra}" no está en la palabra. Intentos restantes: ${intentos}`, "error");
    } else {
        mostrarMensaje(`¡Correcto! La letra "${letra}" se encontró.`, "success");
    }

    actualizarPantalla();

    // Comprobar estado del juego
    if (palabraOculta === palabraActual) {
        victoria();
    } else if (intentos === 0) {
        derrota();
    }
}

function victoria() {
    juegoIniciado = false;
    pararCronometro();
    deshabilitarTeclado();
    mostrarMensaje(`¡Ganaste! La palabra era "${palabraActual}"`, "success");
}

function derrota() {
    juegoIniciado = false;
    pararCronometro();
    deshabilitarTeclado();
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

    // Restablecer la interfaz de usuario
    actualizarPantalla();
    pistaDisplay.textContent = "Presiona el boton de Iniciar para comenzar";
    document.getElementById("cronometro").textContent = "00:00";
    mostrarMensaje("Presiona INICIAR para comenzar un nuevo juego", "info");
    habilitarTeclado();
}

function deshabilitarTeclado() {
    botonesTeclado.forEach(boton => boton.disabled = true);
}

function habilitarTeclado() {
    botonesTeclado.forEach(boton => boton.disabled = false);
}

function actualizarPantalla() {
    palabraDisplay.textContent = palabraOculta.split("").join(" ");
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
    mensajeDisplay.className = `message ${tipo}`;
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



mostrarMensaje("Bienvenido al Ahorcado! Presiona INICIAR para comenzar.", "info");
deshabilitarTeclado();