function hola(nombre,miCallback){
    setTimeout(function(){
    console.log("hola"+nombre)
    miCallback(nombre)
    }, 2000);
    
};

function adios(nombre,otroCallback){
    setTimeout(function(){
        console.log('adios...'+nombre)
        otroCallback();

    },2500);

}

console.log('iniciando el proceso')

hola(' Carlos ',function(nombre){
    adios(nombre,function(){
       console.log('terminando el proceso') 
    })
    
});

//hola('carlos',function(){});
//adios('carlos',function(){});



