
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

<h1>Configuración del framework</h1>

<table>
  <th>Archivo</th>  <th>Ubicación</th>
  <tr></tr><td>myframework.properties</td>  <td>src/main/java</td></tr>
</table>

<ol>
  <li>Verificar/Modificar los parámetros del correo electrónico</li>
  <li>Verificar/Modificar los parámetros de la base de datos</li>
</ol>

<br>

<table>
  <th>Archivo</th>  <th>Ubicación</th>
  <tr></tr><td>spring.xml</td>  <td>src/main/java</td></tr>
</table>

<ol>
  <li>Agregar los packages necesarios en las siguientes líneas, manteneniendo siempre <b>thejavalistener.fwk</b></i>:
  <ol>
    <li>&lt;context:component-scan base-package="<b>thejavalistener.fwk</b>, app" /&gt;</li>
    <li>packagesToScan="<b>thejavalistener.fwk</b>, app"  </li>
  </ol>
  </li>
  <li>Modificar/Eluminar la base de datos por default:
  <ol>
    <li>Para eliminarla: borrar el bean hsqlServer</li>
    <li>Para mantenerla: modificar el bean: defaultDataBase</li>
  </ol>
  </li>
</ol>

<h1>Pantallsa de demostración</h1>
<p>
El framework provee una consola SQL/HQL llamada HQLScreen. La aplicación demo provee diversas
pantallas que ilustran la principal funcionalidad del framework.
</p>
