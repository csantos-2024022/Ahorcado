let palabras = []; 
let palabraActual = "";
let palabraOculta = "";
let letrasUsadas = [];
let intentos = 6;
let juegoIniciado = false;
let juegoPausado = false;
let tiempoInicio = null;
let tiempoPausado = 0;
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
const btnReanudar = document.getElementById("btnReanudar"); 
const btnDetener = document.getElementById("btnReiniciar"); 

const imagenAhorcadoElem = document.getElementById("imagenAhorcado") || document.querySelector(".ImagenReferencia img");
const modalOverlay = document.getElementById("modal-resultado");
const modalTitulo = document.getElementById("modal-titulo");
const modalMensaje = document.getElementById("modal-mensaje");
const closeButton = document.querySelector("#modal-resultado .close-button");

const imagenesAhorcado = [
    "img/ahorcado.png",
    "img/cabeza.png",
    "img/torzo.png",
    "img/brazo1.png",
    "img/brazo2.png",
    "img/pierna1.png",
    "img/pierna2.png"
];


async function cargarPalabrasDesdeDB() {
    try {
        const response = await fetch('ObtenerPalabras');
        if (!response.ok) {
            throw new Error('Error al cargar las palabras desde el servidor');
        }
        const data = await response.json();
        palabras = data;
        mostrarMensaje("Listo para jugar!", "success");
        if(btnIniciar) btnIniciar.disabled = false;
    } catch (error) {
        console.error('Error:', error);
        mostrarMensaje("Error al cargar palabras. Intenta recargar la página.", "error");
        if(btnIniciar) btnIniciar.disabled = true;
    }
}

function iniciarJuego() {
    if (juegoIniciado || palabras.length === 0) return;

    const palabraObj = palabras[Math.floor(Math.random() * palabras.length)];
    palabraActual = palabraObj.palabra.toUpperCase();
    pistaActual = palabraObj.pista;

    palabraOculta = "_".repeat(palabraActual.length);
    letrasUsadas = [];
    intentos = 6;
    juegoIniciado = true;
    juegoPausado = false;
    tiempoPausado = 0;

    tiempoInicio = Date.now();
    iniciarCronometro();

    actualizarPantalla();
    actualizarBotones();
    pistaDisplay.textContent = pistaActual;
    if (imagenAhorcadoElem) imagenAhorcadoElem.src = imagenesAhorcado[0];

    habilitarTeclado();
    mostrarMensaje("Juego iniciado!!! Adivina la palabra letra por letra", "info");
}

function reiniciarJuego() {
    juegoIniciado = false;
    juegoPausado = false;
    tiempoPausado = 0;
    pararCronometro();

    palabraActual = "";
    palabraOculta = "";
    letrasUsadas = [];
    intentos = 6;
    tiempoInicio = null;

    actualizarPantalla();
    actualizarBotones();
    pistaDisplay.textContent = "Presiona el boton de Iniciar para comenzar";
    const cron = document.getElementById("cronometro");
    if (cron) cron.textContent = "00:00";
    mostrarMensaje("Presiona INICIAR para comenzar un nuevo juego", "info");

    habilitarTeclado();
    if (imagenAhorcadoElem) imagenAhorcadoElem.src = imagenesAhorcado[0];
}

function togglePausa() {
    if (!juegoIniciado) return;
    
    juegoPausado = !juegoPausado;
    
    if (juegoPausado) {
        tiempoPausado = Date.now() - tiempoInicio; 
        pararCronometro();
        deshabilitarTeclado(true); 
        mostrarMensaje("Juego pausado Presiona REANUDAR para continuar", "info");
    } else {
        tiempoInicio = Date.now() - tiempoPausado;
        iniciarCronometro();
        habilitarTeclado();
        mostrarMensaje("Juego reanudado!!! continua adivinando", "info");
    }
    actualizarBotones();
}

function manejarClickLetra(letra, boton) {
    if (!juegoIniciado || juegoPausado || letrasUsadas.includes(letra)) {
        return;
    }

    letrasUsadas.push(letra);
    
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

    if (boton) {
        boton.classList.add("used");
        if (letraEncontrada) {
            boton.classList.add("correct");
        } else {
            boton.classList.add("incorrect");
        }
        boton.disabled = true;
    }

    if (!letraEncontrada) {
        intentos--;
        actualizarImagenAhorcado();
        mostrarMensaje(`La letra "${letra}" no está en la palabra Intentos restantes: ${intentos}`, "error");
    } else {
        mostrarMensaje(`Correcto! La letra "${letra}" se encontro`, "success");
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
    juegoPausado = false;
    pararCronometro();
    deshabilitarTeclado();
    actualizarBotones();
    mostrarMensaje(`Ganaste! La palabra era "${palabraActual}"`, "success");
    mostrarModal("GANASTE!", "adivinaste!!!!", "win");
}

function derrota() {
    juegoIniciado = false;
    juegoPausado = false;
    pararCronometro();
    deshabilitarTeclado();
    actualizarBotones();
    if (imagenAhorcadoElem) imagenAhorcadoElem.src = imagenesAhorcado[imagenesAhorcado.length - 1];
    mostrarMensaje(`Perdiste La palabra era "${palabraActual}".`, "error");
    mostrarModal("PERDISTE =(", `La palabra era "${palabraActual}". Intentalo de nuevo!!!`, "lose");
}


function deshabilitarTeclado(soloSiEstaPausado = false) {
    botonesTeclado.forEach(boton => {
        if (soloSiEstaPausado) {
            boton.disabled = true;
        } else if (!boton.classList.contains('used')) {
            boton.disabled = true;
        }
    });
}

function habilitarTeclado() {
    botonesTeclado.forEach(boton => {
        boton.disabled = false;
        boton.classList.remove("used", "correct", "incorrect");
    });
}

function actualizarPantalla() {
    palabraDisplay.textContent = palabraOculta.split("").join(" ");
    intentosDisplay.textContent = intentos;
}

function actualizarBotones() {
    if (btnIniciar) {
        btnIniciar.style.display = juegoIniciado ? "none" : "inline-block";
    }
    if (btnReiniciar) {
        btnReiniciar.style.display = juegoIniciado ? "inline-block" : "none";
    }
    if (btnReanudar) {
        btnReanudar.style.display = juegoIniciado ? "inline-block" : "none";
        btnReanudar.textContent = juegoPausado ? "Reanudar" : "Pausar";
    }
}

function mostrarMensaje(texto, tipo) {
    mensajeDisplay.textContent = texto;
    mensajeDisplay.className = `message ${tipo || ""}`;
}

function actualizarImagenAhorcado() {
    if (!imagenAhorcadoElem) return;
    const fallos = 6 - intentos;
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
        if (juegoPausado) return;
        
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

function mostrarModal(titulo, mensaje, tipo) {
    if (modalTitulo) {
        modalTitulo.textContent = titulo;
        modalTitulo.className = (tipo === "win") ? "win" : "lose";
    }
    if (modalMensaje) modalMensaje.textContent = mensaje;

    if (modalOverlay) modalOverlay.style.display = "flex";
}

function ocultarModal() {
    if (modalOverlay) modalOverlay.style.display = "none";
}


document.addEventListener("DOMContentLoaded", () => {
    cargarPalabrasDesdeDB();
    reiniciarJuego();
});

if (btnIniciar) btnIniciar.addEventListener("click", iniciarJuego);
if (btnReiniciar) btnReiniciar.addEventListener("click", reiniciarJuego);
if (btnReanudar) btnReanudar.addEventListener("click", togglePausa);

botonesTeclado.forEach(boton => {
    boton.addEventListener("click", () => {
        const letra = boton.textContent.trim().toUpperCase();
        if (juegoIniciado && !juegoPausado && letra) {
            manejarClickLetra(letra, boton);
        }
    });
});

if (closeButton) {
    closeButton.addEventListener("click", ocultarModal);
}