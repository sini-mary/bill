package megan.wamboi.billsapp.ui

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import megan.wamboi.billsapp.R
import megan.wamboi.billsapp.databinding.FragmentSummaryBinding
import megan.wamboi.billsapp.viewmodel.BillsViewModel


class summaryFragment : Fragment() {
    lateinit var binding: FragmentSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        // Find the FloatingActionButton by its ID
        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAdd)

        // Set an OnClickListener for the FloatingActionButton
        fabAdd.setOnClickListener {
            // Create an Intent to start the new activity
            val intent = Intent(activity, AddBillActivity::class.java)

            // Start the new activity
            startActivity(intent)
        }

        return view
    }


}
