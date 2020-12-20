package com.padcx.padcx_healthcareapp_nct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padcx.padcx_healthcareapp_nct.adapters.ConsultHistoryListAdapter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.MessagePresenter
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls.MessagePresenterImpl
import com.padcx.padcx_healthcareapp_nct.mvp.views.MessageView
import com.padcx.shared.data.vos.ConsultVO
import kotlinx.android.synthetic.main.fragment_message.*


class MessageFragment : Fragment(),MessageView {

    private lateinit var mMessagePresenter : MessagePresenter
    private lateinit var mConsultHistoryAdapter : ConsultHistoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()
        setUpRecyclerView()
        mMessagePresenter.onUiReady(this)
    }
    private fun setUpPresenter() {
        mMessagePresenter = ViewModelProviders.of(this).get(MessagePresenterImpl::class.java)
        mMessagePresenter.initPresenter(this)
    }
    private fun setUpRecyclerView(){
        mConsultHistoryAdapter = ConsultHistoryListAdapter(mMessagePresenter)
        val layoutManager = LinearLayoutManager(this.requireContext(),LinearLayoutManager.VERTICAL,false)
        rvConsultHistory.adapter = mConsultHistoryAdapter
        rvConsultHistory.layoutManager = layoutManager
    }

    override fun displayConsultationHistory(consults: List<ConsultVO>) {
        mConsultHistoryAdapter.setNewData(consults.toMutableList())
    }

    override fun navigateToChat(consult: ConsultVO) {
        startActivity(ChatActivity.newIntent(this.requireContext(),consult))
    }

    override fun displayPrescription(consult: ConsultVO) {
        Toast.makeText(this.requireContext(),"No prescription available",Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this.requireContext(),error,Toast.LENGTH_SHORT).show()
    }
}