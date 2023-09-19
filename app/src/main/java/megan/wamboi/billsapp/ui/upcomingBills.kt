package megan.wamboi.billsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import megan.wamboi.billsapp.R
import megan.wamboi.billsapp.databinding.FragmentUpcomingBillsBinding
import megan.wamboi.billsapp.viewmodel.BillsViewModel


class upcomingBills : Fragment() {
    var binding:FragmentUpcomingBillsBinding?=null
    val billsViewModel:BillsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate t
        // he layout for this fragment
        binding =FragmentUpcomingBillsBinding.inflate(layoutInflater,container,false)
        return inflater.inflate(R.layout.fragment_upcoming_bills, container, false)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        billsViewModel.getWeeklyUpcoming().observe(this) { upComingweekly ->
            val adapter = UpcomingBillsAdapter(upComingweekly)
            binding?.rvWeekly?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rvWeekly?.adapter = adapter
        }
        billsViewModel.getMonthlyUpcoming().observe(this) { upComingMontly ->
            val adapter = UpcomingBillsAdapter(upComingMontly)
            binding?.rvMonthly?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rvMonthly?.adapter = adapter
        }
        billsViewModel.getYearlyUpcoming().observe(this) { upComingAnnually ->
            val adapter = UpcomingBillsAdapter(upComingAnnually)
            binding?.rvYearly?.layoutManager = LinearLayoutManager(requireContext())
            binding?.rvYearly?.adapter = adapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


}