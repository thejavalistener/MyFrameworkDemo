
<h1>ANTES DE COMENZAR</h1>
<p>
  En este proyecto, que es una especie de <i>HelloWorld</i> para comenzar a desarrollar con <b>MyFramework</b>,
  se incluyen pantallas <i>demo</i> que ilustran cómo usar las características principales del <i>framework</i>:
</p>
<ul>
  <li>ConsolaSQL/HQL</li>
  <li>Formularios</li>
  <li>Envío de emails</li>
  <li>Consola en modo texto</li>
</ul>

<h1>Configuración</h1>
<h2>myframework.properties</h2>


*************************************
** Archivo: myframework.properties **
*************************************
Ubucación: src/main/java

a. Verificar/Modificar los parámetros del correo electrónico
b. Verificar/Modificar los parámetros de la base de datos

*************************
** Archivo: spring.xml **
*************************
Ubicación: src/main/java

a. Agregar los packages necesarios en las siguientes líneas (mantener thejavalistener.fwk):
   * <context:component-scan base-package="thejavalistener.fwk,app" />
   * packagesToScan="thejavalistener.fwk,app"   
b. Verificar/Modificar la configuración de la base de datos
     * 	<bean id="DataBaseConfig" ...

*************************************
** Base De Datos (defaultDataBase) **
*************************************

spring.xml define el bean defaultDataBase que levanta una base de datos por defecto. Para trabajar
acon otra base de datos se debe eliminar la configuración de este bean.

*************************
** Pantallas (Screens) **
*************************

El framework provee una consola SQL/HQL llamada HQLScreen. La aplicación demo provee diversas
pantallas que ilustran la principal funcionalidad del framework.
