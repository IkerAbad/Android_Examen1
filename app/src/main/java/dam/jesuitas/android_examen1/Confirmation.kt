package dam.jesuitas.android_examen1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.Locale

private const val ARG_CORREO = "correo"
private const val ARG_TOTAL = "total"

/** Pantalla de confirmación: muestra el correo y el coste total del pedido. */
class Confirmation : Fragment() {

    private var correo: String = ""
    private var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            correo = it.getString(ARG_CORREO, "")
            total = it.getDouble(ARG_TOTAL, 0.0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_confirmation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.confirmacion_correo).text =
            getString(R.string.direccion_email) + ": " + correo
        view.findViewById<TextView>(R.id.confirmacion_total).text =
            getString(R.string.coste_total) + ": " + String.format(Locale.getDefault(), "%.2f €", total)
        view.findViewById<Button>(R.id.confirmacion_boton).setOnClickListener {
            Toast.makeText(requireContext(), R.string.pedido_confirmado, Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(correo: String, total: Double) = Confirmation().apply {
            arguments = Bundle().apply {
                putString(ARG_CORREO, correo)
                putDouble(ARG_TOTAL, total)
            }
        }
    }
}
