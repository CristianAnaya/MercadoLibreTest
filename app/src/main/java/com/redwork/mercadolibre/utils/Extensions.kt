package com.redwork.mercadolibre.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.redwork.mercadolibre.MercadoLibreApp

val Context.app: MercadoLibreApp get() = applicationContext as MercadoLibreApp

fun Context.showLongToast(message: String?){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}


inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(Intent(this, T::class.java).apply(body))
}
