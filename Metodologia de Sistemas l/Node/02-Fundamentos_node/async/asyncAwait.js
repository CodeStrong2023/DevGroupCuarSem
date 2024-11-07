//La palabra async no es necesaria en las funciones, porque ya son asincronas
//Igual proyectan una sincronia visual
async function hola(nombre) {
    return new Promise(function (resolve, reject) {
        setTimeout(function () {
            console.log("hola" + nombre)
            resolve(nombre)
        }, 1000);
    });
}

async function hablar(nombre) {
    return new Promise((resolve, reject) => { //Usamos la sintaxis ES6
        setTimeout(function () {
            console.log("blo blo blo blo blo");
            resolve(nombre);   
        }, 1000);
    });
}

async function adios(nombre) {
    return new Promise((resolve, reject) => {
        setTimeout(function () {
            console.log('Adios ' + nombre)
            //resolve();
            reject('Hay un error');
        }, 1000);
    });
}

//await hola('Ariel');  -> esto es una mala sintaxis
//await solo es valido dentro de una funcion asincrona
async function main() {
    let nombre = await hola('Ariel');
    await hablar();
    await hablar();
    await hablar();
    await adios(nombre);
    console.log('Termina el proceso...')
}

// console.log('Empezamos el proceso...')
// main();
// console.log('Esta va a ser la segunda instruccion')

//Codigo en ingles
function sayHello(name) {
    return new Promise( (resolve, reject) => {
        setTimeout( () => {
            console.log("hello " + name);
            resolve(name);
        }, 1000);
    });
};

function talk(name) {
    return new Promise((resolve, reject) => { // Using ES6 syntax
        setTimeout(() => {
            console.log("bla bla bla bla bla");
            resolve(name);   
        }, 1000);
    });
}

function sayBye(name) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            console.log('Goodbye ' + name);
            resolve(name);
        }, 1000);
    });
}


async function conversation(name) {
    console.log('Code in English')
    console.log('Starting async process...')
    await sayHello(name);
    await talk();
    await talk();
    await talk();
    await sayBye(name);
    console.log('Process completed');
}

conversation('Ariel');
