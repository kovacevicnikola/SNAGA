package rs.magus.snaga.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rs.magus.snaga.databinding.FragmentSetupBinding

class SetupFragment : Fragment() {

  private lateinit var setupViewModel: SetupViewModel
private var _binding: FragmentSetupBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    private lateinit var pagerAdapter: SetupFragmentStateAdapter
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

      setupViewModel =
          ViewModelProvider(this).get(SetupViewModel::class.java)
      pagerAdapter = SetupFragmentStateAdapter(this)
      _binding = FragmentSetupBinding.inflate(inflater, container, false)
    val root: View = binding.root
      binding.vpSetup.adapter = pagerAdapter
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}