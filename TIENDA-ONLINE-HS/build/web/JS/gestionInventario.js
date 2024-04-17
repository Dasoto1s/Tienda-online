    document.addEventListener("DOMContentLoaded", function() {
        const btnAgregarProducto = document.getElementById("btnAgregarProducto");
        const formularioAgregarProducto = document.getElementById("formularioAgregarProducto");
    
        btnAgregarProducto.addEventListener("click", function() {
        if (formularioAgregarProducto.style.display === "none") {
            formularioAgregarProducto.style.display = "block"; 
        } else {
            formularioAgregarProducto.style.display = "none"; 
        }
        });
    });
    