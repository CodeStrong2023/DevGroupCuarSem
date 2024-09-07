// Variables globales

let ataqueJugador;
let ataqueEnemigo;
let vidasJugador = 3;
let vidasEnemigo = 3;
let personajeSeleccionado = "";
//
let botonPunio = document.getElementById("boton-punio");
let botonPatada = document.getElementById("boton-patada");
let botonBarrida = document.getElementById("boton-barrida");
//

function iniciarJuego() {
  let sectionSeleccionarAtaque = document.getElementById("seleccionar-ataque");
  sectionSeleccionarAtaque.style.display = "none";

  // Asocia los botones de personajes a un evento click
  document.querySelectorAll("[data-personaje]").forEach((button) => {
    button.addEventListener("click", function () {
      // Remueve la clase seleccionada de todos los botones
      document
        .querySelectorAll("[data-personaje]")
        .forEach((btn) => btn.classList.remove("seleccionado"));

      // Agrega la clase seleccionada al botón actual
      this.classList.add("seleccionado");

      // Almacena el personaje seleccionado
      personajeSeleccionado = this.getAttribute("data-personaje");
    });
  });

  let botonPersonajeJugador = document.getElementById("boton-personaje");
  botonPersonajeJugador.addEventListener("click", seleccionarPersonajeJugador);

  let sectionReiniciar = document.getElementById("reiniciar");
  sectionReiniciar.style.display = "none";

  botonPunio.addEventListener("click", ataquePunio);
  botonPatada.addEventListener("click", ataquePatada);
  botonBarrida.addEventListener("click", ataqueBarrida);

  let botonReiniciar = document.getElementById("boton-reiniciar");
  botonReiniciar.addEventListener("click", reiniciarJuego);
}

function seleccionarPersonajeJugador() {
  let spanPersonajeJugador = document.getElementById("personaje-jugador");
  let sectionSeleccionarAtaque = document.getElementById("seleccionar-ataque");
  sectionSeleccionarAtaque.style.display = "block";
  let sectionSeleccionarPersonaje = document.getElementById(
    "seleccionar-personaje"
  );
  sectionSeleccionarPersonaje.style.display = "none";

  if (personajeSeleccionado) {
    spanPersonajeJugador.innerHTML = personajeSeleccionado;
    seleccionarPersonajeEnemigo();
  } else {
    let mensajeError = document.createElement("p");
    mensajeError.innerHTML = "Selecciona un personaje";
    mensajeError.style.color = "red";

    sectionSeleccionarPersonaje.appendChild(mensajeError);

    // Eliminar el mensaje luego de 2 segundos
    setTimeout(() => {
      sectionSeleccionarPersonaje.removeChild(mensajeError);
    }, 2000);
  }
}

function seleccionarPersonajeEnemigo() {
  let personajeAleatorio = aleatorio(1, 4);
  let spanPersonajeEnemigo = document.getElementById("personaje-enemigo");

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
  let ataqueAleatorio = aleatorio(1, 3);

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
  let spanVidasJugador = document.getElementById("vidas-jugador");
  let spanVidasEnemigo = document.getElementById("vidas-enemigo");

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
  let sectionReiniciar = document.getElementById("reiniciar");
  sectionReiniciar.style.display = "block";
  let sectionMensaje = document.getElementById("mensajes");
  let parrafo = document.createElement("p");

  parrafo.innerHTML = resultado;

  sectionMensaje.appendChild(parrafo);

  botonPunio.disabled = true;
  botonPatada.disabled = true;
  botonBarrida.disabled = true;
}

function crearMensaje(nuevoMensaje) {
  let mensajes = document.getElementById("mensajes");

  mensajes.innerHTML =
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
