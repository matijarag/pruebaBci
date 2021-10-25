# Instrucciones
### Instalación
- Descargar el proyecto como zip o Clonar el repositorio.
- Descomprimir el archivo si es que lo descargaron.
- Abrir la ventana de comandos y viajar a la carpeta del proyecto ubicandose en el directorio que posee el archivo pom.xml
	 - Escribir en consola : mvn clean install.
- Abrir Eclipse o IDE de preferencia. 
   - File -> Import -> Existing Maven Project -> Ir al directorio del proyecto.
   - Seleccionar el proyecto correspondiente.
- Seleccionar el archivo Spring Boot Application (buscar como @SpringBootApplication).
- Click derecho sobre el archivo y Run as Java Application.
- Listo, ya puedes probar el proyecto.

### API
- Link [Swagger](https://app.swaggerhub.com/apis/matijarag/Prueba_BCI/1.0)

### Ejemplos de prueba
- La Url Correspondiente es "/api/usersAdd"
- Password se debe componer de 1 letra minúscula , 1 letra mayúscula, 1 carácter especial , entre 8 a 20 caracteres.
- Body:
```
{
	"name":"Juan Rodriguez",
	"email":"juan@rodriguez.org",
	"password":"Abcde.144_",
	"phones":[
			{
				"number":"487545",
				"citycode":"3",
				"contrycode":"56"
			}
	]
}
```
![Image](https://github.com/matijarag/pruebaBci/blob/master/mediaForReadme/ss1.png)
