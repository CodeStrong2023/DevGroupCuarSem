function hola(nombre) {
    return new Promise(function (resolve, reject) {
        setTimeout(function () {
            console.log("hola" + nombre)
            resolve(nombre)
        }, 1000);
    });
}

function hablar(nombre) {
    return new Promise((resolve, reject) => { //Usamos la sintaxis ES6
        setTimeout(function () {
            console.log("blo blo blo blo blo");
            resolve(nombre);   
        }, 1000);
    });
}

function adios(nombre) {
    return new Promise((resolve, reject) => {
        setTimeout(function () {
            console.log('Adios ' + nombre)
            //resolve();
            reject('Hay un error');
        }, 1000);
    });
}

//Llamamos a la función
console.log('Iniciando el proceso...')
hola(' Ariel')
    .then(hablar)
    .then(hablar)
    .then(hablar)
    .then(adios)
    .then((nombre) => {
        console.log('Terminando el proceso');
    })
    .catch(error => {
        console.log('Ha habido un error: ');
        console.log(error);
    })



