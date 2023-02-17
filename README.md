# appDaniela

Este proyecto consiste en un ejercicio donde se integran tres pantallas para mostrar una lista de comidas, sus detalles y la ubicación de su origen. 
Se utilizó un patron de arquitectura VMMV con un diseño repository como metodo de abstracción de las fuentes de datos que nos faciliten el intercambio con una metodologia (similar a la que se destribe en esta imagen) ![image](https://user-images.githubusercontent.com/59578465/219741300-e418da5c-8828-422e-bc3e-5e16ea47d21a.png)
 
de una fuente de datos local con una externa mediante APIs. Se utilizo un PagingData como metodo para mostrar la lista y un filtro para facilite la 
busqueda de coincidencias. Se utilizo koin como metodo de inyeccion de dependencias y Retrofit como la libreria para el intercambio con la data WEB y el Jetpack navigaton para el flujo de la navegación. 
