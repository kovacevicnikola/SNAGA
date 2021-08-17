package rs.magus.snaga.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import rs.magus.snaga.databinding.FragmentSetupBinding

class SetupFragment : Fragment() {

  private lateinit var setupViewModel: SetupViewModel
private var _binding: FragmentSetupBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    setupViewModel =
            ViewModelProvider(this).get(SetupViewModel::class.java)

    _binding = FragmentSetupBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textSlideshow
    setupViewModel.text.observe(viewLifecycleOwner, Observer {
      textView.text = it
    })
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}