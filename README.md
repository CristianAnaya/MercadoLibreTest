# Cristian Candidate Android

El propósito del siguiente Proyecto es desarrollar una App, nativa en Android, que utilice las APIs de Mercadolibre para permitirle a un usuario ver los detalles de un producto.

## Experiencia de usuario

La App debe permitir la posibilidad de:
1. Realizar busquedas por medio de un campo de búsqueda.
2. Visualización de resultados de la búsqueda.
3. Visualizar el detalle de un producto seleccionado.

Para el desarrolo se usará el patrón arquitectónico MVVM, estandar propuesto por Google.


## [Patrón Modelo – Vista – ModelView (MVVM)](https://developer.android.com/jetpack/docs/guide)


De forma genérica:
- El Modelo, al igual que en MVC, representa la capa de datos y/o lógica de negocio.
- La Vista presenta la información y es activa, reaccionando a cambios en el modelo, de forma similar a un patrón MVC activo.
- El Modelo de Vista es un actor intermediario entre el modelo y la vista y contiene toda la lógica de presentación.


### Arquitectura

Este proyecto sigue buenas prácticas de la arquitectura limpia (Clean Architecture) planteada por Robert C. Martin para hacer un código más independiente, mantenible y sencillo para generar pruebas unitarias.

#### Capas

* App: UI.
* Presentación: View Models.
* Casos de uso: GetAllCategoryUseCase, GetProductDetailUseCase.
* Dominio: Product, Shipping, Seller, etc.
* Framework: ImageManager, RequestManager.

## [Android Jetpack](https://developer.android.com/jetpack)
Jetpack es un conjunto de bibliotecas, herramientas y guías para ayudar a los desarrolladores a escribir apps de alta calidad de forma más sencilla. Estos componentes te ayudan a seguir recomendaciones, te liberan de escribir código estándar y simplifican tareas complejas para que puedas enfocarte en el código
que realmente te interesa.

Jetpack incluye bibliotecas de paquetes de androidx.* desglosadas de las API de la plataforma. Esto significa que ofrece retrocompatibilidad y se actualiza con mayor frecuencia que la plataforma de Android, de manera que siempre tendrás acceso a las mejores y más recientes versiones de los componentes de Jetpack.


### Bibliotecas de vinculación de datos parte de Android Jetpack


#### ViewModel

[ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html)
es una clase que se encarga de preparar y administrar los datos para una `Activity` o una `Fragment`. También maneja la comunicación de la Actividad / Fragmento con el resto de la aplicación (por ejemplo, llamando a las clases de lógica de negocios).

Un ViewModel siempre se crea en asociación con un ámbito (un fragmento o una actividad) y se conservará mientras el ámbito esté activo. Por ejemplo, si es una Actividad, hasta que se termine.

En otras palabras, esto significa que un ViewModel no se destruirá si su propietario se destruye por un cambio de configuración (por ejemplo, rotación). La nueva instancia del propietario se volverá a conectar al ViewModel existente.

El propósito de ViewModel es adquirir y mantener la información necesaria para una Actividad o un Fragmento. La Actividad o el Fragmento debería poder observar los cambios en el Modelo de vista. ViewModels generalmente expone esta información a través de un `LiveData` enlace de datos de Android. También puede usar cualquier construcción de observabilidad de su marco 
favorito.

La única responsabilidad de ViewModel es administrar los datos para la IU. **Nunca debe acceder a su jerarquía de vistas ni retener una referencia a la Actividad o al Fragmento**.

#### LiveData

[LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)
es una clase de retención de datos observable. A diferencia de una clase observable regular, LiveData está optimizada para ciclos de vida, lo que significa que respeta el ciclo de vida de otros componentes de las apps, como actividades, fragmentos o servicios. Esta optimización garantiza que LiveData solo actualice observadores de componentes de apps que tienen un 
estado de ciclo de vida activo.

LiveData considera que un observador, representado por la clase `Observer`, está en un estado activo si su ciclo de vida tiene el estado `STARTED` o `RESUMED`. LiveData solo notifica a los observadores activos sobre las actualizaciones. A los observadores inactivos registrados para observar objetos `LiveData` no se les notifica sobre los cambios.

Puedes registrar un observador sincronizado con un objeto que implementa la interfaz de `LifecycleOwner`. Esta relación permite quitar el observador cuando el estado del objeto Lifecycle correspondiente cambia a `DESTROYED`, lo que es particularmente útil para las actividades y los fragmentos porque pueden observar objetos `LiveData` de manera segura sin 
preocuparse por las pérdidas (se anula de inmediato la suscripción a las actividades y los fragmentos cuando se destruyen sus ciclos de vida).

LiveData evita Memory Leaks: Una pérdida de memoria ocurre cuando su código asigna memoria para un objeto, pero nunca lo desasigna. Esto puede suceder por muchas razones, no importa la causa, cuando se produce una pérdida de memoria, el recolector de basura cree que todavía se necesita un objeto porque otros objetos aún lo hacen referencia. Pero esas referencias deberían haberse borrado. La memoria asignada para los objetos filtrados actúa como un bloque inamovible, lo que obliga al resto de la aplicación a ejecutarse en lo que queda del montón. Esto puede causar frecuentes recolecciones de basura. Si la aplicación continúa perdiendo memoria, finalmente se quedará sin memoria y se bloqueará.


#### DataBinding

[DataBinding](https://developer.android.com/topic/libraries/data-binding?hl=es)
es una biblioteca de vinculación de datos que permite vincular los componentes de la IU de tus diseños a las fuentes de datos de tu app usando un formato declarativo en lugar de la programación.

Los diseños a menudo se definen en actividades con código que llama a los métodos de marco de trabajo de la IU. Por ejemplo, el siguiente código llama a `findViewById()` para encontrar un widget `TextView` y vincularlo con la propiedad userName de la variable viewModel.

La vinculación de componentes en el archivo de diseño permite quitar varias llamadas al marco de trabajo de la IU en las actividades, que resultan más sencillas y fáciles de mantener. Esto también puede mejorar el rendimiento de la app y ayudar a evitar pérdidas de memoria y excepciones de puntero nulo.


---

#### Librerías Principales usadas en el Proyecto
   
- [ViewModel and LiveData](https://developer.android.com/jetpack/androidx/releases/lifecycle/) 
Los componentes optimizados para Lifecycle realizan acciones como respuesta a un cambio en el estado del ciclo de vida de otro componente, como actividades o fragmentos.

### Administrador de solicitudes: Retrofit

Este proyecto utiliza Retrofit para proporcionar una lista de productos y su información desde una API.

### Manejo de flujos de datos y eventos: RxJava - RxAndroid

Este proyecto utiliza RxJava - RxAndroid para proporcionar el trabajo de flujos asíncronos de orígenes de datos.

### Biblioteca de imágenes - Glide

Este proyecto utiliza Glide para cargar las imágenes de los productos.

### Inyección de dependencia - Dagger Hilt

Este proyecto utiliza Dagger Hilt para gestionar la inyección de dependencia.

