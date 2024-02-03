package com.gimble.assignment.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gimble.assignment.R
import com.gimble.assignment.TheViewModel
import com.gimble.assignment.data.remote.dashboardapi.DashboardData
import com.gimble.assignment.databinding.FragmentLinksBinding
import com.gimble.assignment.extrafunctions.Greeter
import com.gimble.assignment.extrafunctions.TimeDealer
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class LinksFragment : Fragment() {
 private lateinit var binding: FragmentLinksBinding
    private val viewModel: TheViewModel by lazy {
        ViewModelProvider(this).get(TheViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLinksBinding.inflate(layoutInflater,container,false)
        initialSetup()
        return binding.root
    }

    private fun initialSetup() {
        viewModel.apiData.observe(viewLifecycleOwner, Observer { data ->
            updateUi(data)
        })
        viewModel.getDataforDashboard()
    }

    private fun updateUi(dat : DashboardData) {
        setupLinechart(dat)
        setupStats(dat)

        val buttonA = binding.toplinky
        val buttonB = binding.recentlinky

        buttonA.setOnClickListener {
            replaceFragment(ToplinkFragment())
            swapColors(buttonA, buttonB)

            buttonA.setBackgroundColor(Color.parseColor("#2b80ff"))
            buttonB.setBackgroundColor(Color.parseColor("#f5f5f5"))
        }

        buttonB.setOnClickListener {
            replaceFragment(RecentFragment())
            swapColors(buttonB, buttonA)

            buttonA.setBackgroundColor(Color.parseColor("#f5f5f5"))
            buttonB.setBackgroundColor(Color.parseColor("#2b80ff"))
        }

        //whatsapp
        binding.whatsappbtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=${dat.support_whatsapp_number}")
            intent.data = uri

            startActivity(intent)
        }
        binding.greetings.text=Greeter().getGreeting()


    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView2, fragment)
        transaction.commit()
    }
    private fun swapColors(selectedButton: Button, otherButton: Button) {

        val selectedTextColor = selectedButton.currentTextColor
        selectedButton.setTextColor(otherButton.currentTextColor)
        otherButton.setTextColor(selectedTextColor)


        selectedButton.setBackgroundColor(otherButton.backgroundTintList?.defaultColor ?: Color.TRANSPARENT)
        otherButton.setBackgroundColor(selectedButton.backgroundTintList?.defaultColor ?: Color.TRANSPARENT)
    }



    private fun setupStats(dat: DashboardData) {

        binding.totClicks.text = dat.today_clicks.toString()
        if(dat.top_location.isNotEmpty()) {binding.locationdata.text = dat.top_location}
        if(dat.top_source.isNotEmpty()) {binding.source.text=dat.top_source}
        if(dat.startTime.isNotEmpty()) { binding.sourcedata.text=dat.startTime.toString()}
    }

    private fun setupLinechart(dat : DashboardData) {
        val lc = binding.chart
        lc.description.isEnabled = false
        lc.setTouchEnabled(false)
        lc.isDragEnabled = true
        lc.setScaleEnabled(true)
        lc.setPinchZoom(true)

        val xAxis = lc.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)


        val yAxisLeft = lc.axisLeft
        yAxisLeft.setDrawGridLines(false)

        val yAxisRight = lc.axisRight
        yAxisRight.isEnabled = false  // Disable right Y-axis

        // Example data
        val dataList = dat.data.overall_url_chart
        Log.d("thisis :",TimeDealer().getLast8Days().toString())


        val i = dat.data.overall_url_chart.`2024-02-02`
        val entries=mutableListOf<Entry>()
        entries.add(Entry(0f, dataList.`2024-01-26`.toFloat()))
        entries.add(Entry(1f, dataList.`2024-01-27`.toFloat()))
        entries.add(Entry(2f, dataList.`2024-01-28`.toFloat()))
        entries.add(Entry(3f, dataList.`2024-01-29`.toFloat()))
        entries.add(Entry(4f, dataList.`2024-01-30`.toFloat()))
        entries.add(Entry(5f, dataList.`2024-01-31`.toFloat()))
        entries.add(Entry(6f,dataList.`2024-02-01`.toFloat()))
        entries.add(Entry(7f, dataList.`2024-02-02`.toFloat()))
     



        // Create a LineDataSet
        val dataSet = LineDataSet(entries, null)
        dataSet.valueTextColor = Color.BLACK
        dataSet.setDrawCircles(false)
        dataSet.setDrawValues(false)
        dataSet.valueTextSize = 9f

        // Create LineData and set it to the chart
        val lineDataSets = ArrayList<ILineDataSet>()
        lineDataSets.add(dataSet)

        val lineData = LineData(lineDataSets)
        lc.data = lineData

        // Refresh the chart
        lc.invalidate()
    }

    // The rest of your fragment code


}
