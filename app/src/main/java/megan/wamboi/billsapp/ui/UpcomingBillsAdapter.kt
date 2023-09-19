package megan.wamboi.billsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import megan.wamboi.billsapp.databinding.UpcomingBillsListItemBinding
import megan.wamboi.billsapp.model.UpcomingBill

class UpcomingBillsAdapter(var upcomingBills:List<UpcomingBill>):Adapter<UpcomingBillsAdapter.UpcomingBillsViewHolder> () {
    override fun onBindViewHolder(holder: UpcomingBillsViewHolder, position: Int) {
        val upcomingBill = upcomingBills.get(position)
        holder.binding.apply {
            cbUpcominng.text = upcomingBill.name
            tvAmount.text = upcomingBill.amount.toString()
            tvUpcomingDueDate.text = upcomingBill.dueDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingBillsViewHolder {
        val binding = UpcomingBillsListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingBillsViewHolder(binding)
    }

    override fun getItemCount(): Int {

    }

    class UpcomingBillsViewHolder(var binding: UpcomingBillsListItemBinding) :
        ViewHolder(binding.root)

}