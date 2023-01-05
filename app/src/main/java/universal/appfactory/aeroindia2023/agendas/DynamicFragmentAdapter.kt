package universal.appfactory.aeroindia2023.agendas

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class DynamicFragmentAdapter (fragmentManager: FragmentManager,
                              lifecycle: Lifecycle,
                              private val mContext: Context,
                              private val count: Int,
                              private val classification: String,
                              private val name: List<String>) :

FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return DynamicFragment.newInstance(classification, name[position], mContext)
    }

    override fun getItemCount(): Int {
        return count
    }
}
