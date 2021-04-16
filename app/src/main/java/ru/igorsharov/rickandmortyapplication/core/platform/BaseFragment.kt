package ru.igorsharov.rickandmortyapplication.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import ru.igorsharov.rickandmortyapplication.AndroidApplication
import ru.igorsharov.rickandmortyapplication.core.di.ApplicationComponent
import javax.inject.Inject

abstract class BaseFragment : Fragment {
    constructor()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected var _binding: ViewBinding? = null

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (requireActivity().application as AndroidApplication).appComponent
    }

    protected open val bottomNavigationViewVisibility = View.GONE

    override fun onStart() {
        super.onStart()
        (requireActivity() as BottomNavigationActivity).setBottomNavigationVisibility(
            bottomNavigationViewVisibility
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initViewModelObservers()
        _binding = getViewBinding(inflater, container)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun initViewModelObservers()

    protected abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ViewBinding
}