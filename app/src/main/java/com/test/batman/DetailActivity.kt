package com.test.batman

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.batman.Utilities.CheckNetwork
import com.test.batman.ViewModel.DetailViewModel
import com.test.batman.ViewModel.DetailViewModelFactory
import com.test.batman.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.video_item.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: DetailViewModelFactory
    private lateinit var viewModel: DetailViewModel
    private lateinit var id: String
    private lateinit var databinding: ActivityDetailBinding
    private lateinit var checkNetwork: CheckNetwork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        component.inject(this)
        setSupportActionBar(detail_toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        checkNetwork = CheckNetwork()

        val b = intent.extras
        id = b!!.getString("VIDEO_ID")!!
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        if(checkNetwork.isOnline(this)) {

            viewModel.getDetailPage(this, id).observe(this, Observer {
                databinding.videoDetailModel = it
            })

        }
        else{
            AlertDialog.Builder(this)
                .setMessage("please turn on wifi!")
                .setPositiveButton("OK"){dialog, which ->
                    finish()
                }
                .show()
        }
//        detail_pb.visibility = View.VISIBLE
        detail_pb.visibility = View.GONE

    }
}