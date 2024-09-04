let ataqueJugador;
let ataqueEnemigo;
let vidasJugador = 3;
let vidasEnemigo = 3;
let personajeSeleccionado = "";
let sectionSeleccionarAtaque;
let botonPersonajeJugador;
let sectionReiniciar;
let botonPunio;
let botonPatada;
let botonBarrida;
let botonReiniciar;
let spanPersonajeJugador;
let sectionSeleccionarPersonaje;
let personajeAleatorio;
let spanPersonajeEnemigo;
let ataqueAleatorio;
let spanVidasJugador;
let spanVidasEnemigo;
let sectionMensaje;
let parrafo;

function iniciarJuego() {
  sectionSeleccionarAtaque = document.getElementById("seleccionar-ataque");
  sectionSeleccionarAtaque.style.display = "none";

  document.querySelectorAll("[data-personaje]").forEach((button) => {
    button.addEventListener("click", function () {
      document
        .querySelectorAll("[data-personaje]")
        .forEach((btn) => btn.classList.remove("seleccionado"));
      this.classList.add("seleccionado");
      personajeSeleccionado = this.getAttribute("data-personaje");
    });
  });

  botonPersonajeJugador = document.getElementById("boton-personaje");
  botonPersonajeJugador.addEventListener("click", seleccionarPersonajeJugador);

  sectionReiniciar = document.getElementById("reiniciar");
  sectionReiniciar.style.display = "none";

  botonPunio = document.getElementById("boton-punio");
  botonPunio.addEventListener("click", ataquePunio);

  botonPatada = document.getElementById("boton-patada");
  botonPatada.addEventListener("click", ataquePatada);

  botonBarrida = document.getElementById("boton-barrida");
  botonBarrida.addEventListener("click", ataqueBarrida);

  botonReiniciar = document.getElementById("boton-reiniciar");
  botonReiniciar.addEventListener("click", reiniciarJuego);
}

function seleccionarPersonajeJugador() {
  spanPersonajeJugador = document.getElementById("personaje-jugador");
  sectionSeleccionarAtaque = document.getElementById("seleccionar-ataque");
  sectionSeleccionarAtaque.style.display = "block";
  sectionSeleccionarPersonaje = document.getElementById("seleccionar-personaje");
  sectionSeleccionarPersonaje.style.display = "none";

  if (personajeSeleccionado) {
    spanPersonajeJugador.innerHTML = personajeSeleccionado;
    seleccionarPersonajeEnemigo();
  } else {
    let mensajeError = document.createElement("p");
    mensajeError.innerHTML = "Selecciona un personaje";
    mensajeError.style.color = "red";

    sectionSeleccionarPersonaje.appendChild(mensajeError);

    setTimeout(() => {
      sectionSeleccionarPersonaje.removeChild(mensajeError);
    }, 2000);
  }
}

function seleccionarPersonajeEnemigo() {
  personajeAleatorio = aleatorio(1, 4);
  spanPersonajeEnemigo = document.getElementById("personaje-enemigo");

  if (personajeAleatorio == 1) {
    spanPersonajeEnemigo.innerHTML = "Zuko";
  } else if (personajeAleatorio == 2) {
    spanPersonajeEnemigo.innerHTML = "Katara";
  } else if (personajeAleatorio == 3) {
    spanPersonajeEnemigo.innerHTML = "Aang";
  } else {
    spanPersonajeEnemigo.innerHTML = "Toph";
  }
}

function ataquePunio() {
  ataqueJugador = "Punio";
  ataqueAleatorioEnemigo();
}

function ataquePatada() {
  ataqueJugador = "Patada";
  ataqueAleatorioEnemigo();
}

function ataqueBarrida() {
  ataqueJugador = "Barrida";
  ataqueAleatorioEnemigo();
}

function ataqueAleatorioEnemigo() {
  ataqueAleatorio = aleatorio(1, 3);

  if (ataqueAleatorio == 1) {
    ataqueEnemigo = "Punio";
  } else if (ataqueAleatorio == 2) {
    ataqueEnemigo = "Patada";
  } else {
    ataqueEnemigo = "Barrida";
  }

  combate();
}

function combate() {
  spanVidasJugador = document.getElementById("vidas-jugador");
  spanVidasEnemigo = document.getElementById("vidas-enemigo");

  if (ataqueEnemigo == ataqueJugador) {
    crearMensaje("EMPATE");
  } else if (
    (ataqueJugador == "Punio" && ataqueEnemigo == "Barrida") ||
    (ataqueJugador == "Patada" && ataqueEnemigo == "Punio") ||
    (ataqueJugador == "Barrida" && ataqueEnemigo == "Patada")
  ) {
    crearMensaje("GANASTE");
    vidasEnemigo--;
    spanVidasEnemigo.innerHTML = vidasEnemigo;
  } else {
    crearMensaje("PERDISTE");
    vidasJugador--;
    spanVidasJugador.innerHTML = vidasJugador;
  }

  revisarVidas();
}

function revisarVidas() {
  if (vidasEnemigo == 0) {
    crearMensajeFinal("FELICITACIONES!!! HAS GANADO");
  } else if (vidasJugador == 0) {
    crearMensajeFinal("QUE PENA :c HAS PERDIDO");
  }
}

function crearMensajeFinal(resultado) {
  sectionReiniciar.style.display = "block";
  sectionMensaje = document.getElementById("mensajes");
  parrafo = document.createElement("p");

  parrafo.innerHTML = resultado;
  sectionMensaje.appendChild(parrafo);

  botonPunio.disabled = true;
  botonPatada.disabled = true;
  botonBarrida.disabled = true;
}

function crearMensaje(nuevoMensaje) {
  sectionMensaje = document.getElementById("mensajes");

  sectionMensaje.innerHTML =
    "Tu personaje atacó con " +
    ataqueJugador +
    ", el personaje del enemigo atacó con " +
    ataqueEnemigo +
    " - " +
    nuevoMensaje;
}

function reiniciarJuego() {
  location.reload();
}

function aleatorio(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

window.addEventListener("load", iniciarJuego);
