package dam.jesuitas.android_examen1

import android.os.Bundle
import android.util.Patterns
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dam.jesuitas.android_examen1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        // El enunciado no indica el precio del café; este valor es una suposición mía.
        const val PRECIO_CAFE = 1.00
        const val RECARGO_LLEVAR = 0.10
        const val RECARGO_TERRAZA = 0.50
        const val PRECIO_HELADO = 0.25
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Los tres botones no están en un RadioGroup: dejo marcado solo el último pulsado.
        val servicios = listOf(binding.buttonBarra, binding.buttonLlevar, binding.buttonConsumo)
        servicios.forEach { boton ->
            boton.setOnClickListener {
                servicios.filter { it !== boton }.forEach { it.isChecked = false }
            }
        }

        binding.buttonContinuar.setOnClickListener { continuar() }

        supportFragmentManager.addOnBackStackChangedListener {
            binding.contenedor.visibility =
                if (supportFragmentManager.backStackEntryCount > 0) android.view.View.VISIBLE
                else android.view.View.GONE
        }
    }

    private fun continuar() {
        val correo = binding.correoInput.text?.toString()?.trim().orEmpty()
        val cafes = binding.cafesInput.text?.toString()?.trim()?.toIntOrNull() ?: 0

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            binding.correoInput.error = getString(R.string.error_correo)
            return
        }
        if (cafes <= 0) {
            binding.cafesInput.error = getString(R.string.error_cafes)
            return
        }
        val recargo = when {
            binding.buttonBarra.isChecked -> 0.0
            binding.buttonLlevar.isChecked -> RECARGO_LLEVAR
            binding.buttonConsumo.isChecked -> RECARGO_TERRAZA
            else -> {
                binding.buttonBarra.error = getString(R.string.error_servicio)
                return
            }
        }
        val helado = if (binding.heladoSwitch.isChecked) PRECIO_HELADO else 0.0

        val total = cafes * (PRECIO_CAFE + recargo) + helado

        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor, Confirmation.newInstance(correo, total))
            .addToBackStack(null)
            .commit()
    }
}
