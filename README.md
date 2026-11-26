# Android_Examen1

Examen de Android del ciclo de DAM (25 de noviembre de 2022). Es una app en Kotlin para hacer un pedido en una cafetería: se introduce el correo, el número de cafés y el tipo de servicio (consumo en barra, para llevar con un recargo de 0,10 €, o en terraza con 0,50 €), y se puede añadir helado por 0,25 €.

## Estado

El examen original quedó a medias. Después lo terminé: `MainActivity` valida el correo, los cafés y el tipo de servicio, calcula el coste total y abre la pantalla de confirmación (`Confirmation`), que muestra el correo y el total. También arreglé el `build.gradle`, que tenía dos dependencias de navegación mal escritas y un plugin que no se usaba.

El enunciado no da el precio del café, así que uso 1,00 € por café (constante `PRECIO_CAFE`). Los recargos de para llevar y terraza se aplican a cada café, y el helado se suma una sola vez al pedido.

## Cómo abrirlo

Abre la carpeta en Android Studio, deja que Gradle sincronice y ejecútala en un emulador o en un móvil (`minSdk 21`, `compileSdk 33`). No la he podido compilar ni ejecutar desde el entorno en el que hice los últimos cambios (sin Android SDK).

---

# Android_Examen1 (English)

Android exam from my DAM vocational course (25 November 2022). A Kotlin app for placing an order at a coffee shop: you enter your email, the number of coffees and the type of service (at the counter, takeaway with a 0.10 € surcharge, or on the terrace for 0.50 €), and you can add ice cream for 0.25 €.

## Status

The original exam was left unfinished. I finished it afterwards: `MainActivity` validates the email, the number of coffees and the type of service, computes the total cost and opens the confirmation screen (`Confirmation`), which shows the email and the total. I also fixed `build.gradle`, which had two misspelled navigation dependencies and an unused plugin.

The exam statement gives no price for a coffee, so I use 1.00 € per coffee (the `PRECIO_CAFE` constant). The takeaway and terrace surcharges apply to each coffee, and the ice cream is added once per order.

## Opening it

Open the folder in Android Studio, let Gradle sync and run it on an emulator or a phone (`minSdk 21`, `compileSdk 33`). I could not compile or run it in the environment where I made the latest changes (no Android SDK).
