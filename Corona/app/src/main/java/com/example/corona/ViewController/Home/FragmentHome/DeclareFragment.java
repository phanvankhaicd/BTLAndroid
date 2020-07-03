package com.example.corona.ViewController.Home.FragmentHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.corona.Model.Declare;
import com.example.corona.Model.HealthMonitor.HealthMonitor;
import com.example.corona.Model.HealthMonitor.ItemDeclare;
import com.example.corona.Model.HistoryDeclare;
import com.example.corona.Model.PostHealthMonitor.SendHealthMonitor;
import com.example.corona.Network.Body.CreateDeclare;
import com.example.corona.Network.DataServices;
import com.example.corona.R;
import com.example.corona.Util.LoadingDialog;
import com.example.corona.ViewController.Declare.HistoryDeclareAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.corona.Util.AppConfig.getToken;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeclareFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    public DeclareFragment() {
        // Required empty public constructor
    }

    private ListView lv;
    ArrayList<ItemDeclare> data;
    HistoryDeclareAdapter adapter;
    CheckBox cbHo, cbSot, cbKhoTho, cbDauNguoi, cbTot;
    Button btnSent;
    LoadingDialog loadingDialog;
    int page = 0;
    boolean lastpage = false;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_declare, container, false);
        init(view);

        data = new ArrayList<>();
        adapter = new HistoryDeclareAdapter(data, getContext());
        lv.setAdapter(adapter);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && !lastpage) {

                    showProgressBar(true);
                    page++;
                    getDeclare(page);
                }
            }
        });
        onClickCheckBox();
        onSent();
        return view;
    }

    private void onSent() {
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSubmit();
            }
        });
    }

    void uncheck() {
        cbSot.setChecked(false);
        cbDauNguoi.setChecked(false);
        cbKhoTho.setChecked(false);
        cbHo.setChecked(false);
        cbTot.setChecked(false);
    }

    private void checkSubmit() {
        if ((!cbDauNguoi.isChecked())
                && (!cbSot.isChecked())
                && (!cbKhoTho.isChecked())
                && (!cbHo.isChecked())
                && (!cbTot.isChecked()))
            Toast.makeText(getContext(), "Vui lòng chọn thông tin sức khoẻ hiện tại", Toast.LENGTH_SHORT).show();
        else
            createDeclare();
    }

    private void createDeclare() {
        CreateDeclare body = new CreateDeclare(
                cbSot.isChecked() ? 1 : 0,
                cbHo.isChecked() ? 1 : 0,
                cbKhoTho.isChecked() ? 1 : 0,
                cbDauNguoi.isChecked() ? 1 : 0,
                cbTot.isChecked() ? 1 : 0
        );

        DataServices.getAPIService().createDeclare(body, getToken(getContext()))
                .enqueue(new Callback<SendHealthMonitor>() {
                    @Override
                    public void onResponse(Call<SendHealthMonitor> call, Response<SendHealthMonitor> response) {
                        if (response.body().getErrorCode() == 0) {
                            page = 0;
                            lastpage = false;
                            getDeclare(page);
                            uncheck();
                        }
                    }

                    @Override
                    public void onFailure(Call<SendHealthMonitor> call, Throwable t) {
                        Toast.makeText(getContext(), "Vui long thu lai!", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkbox_suc_khoe_tot:
                configCheckBoxTot(isChecked);
                break;
            case R.id.checkbox_ho:
            case R.id.checkbox_dau_nguoi:
            case R.id.checkbox_sot:
            case R.id.checkbox_kho_tho:
                configCheckBox(isChecked);
                break;
        }
    }

    private void configCheckBox(boolean isChecked) {
        if (isChecked) {
            cbTot.setEnabled(false);
            cbTot.setActivated(false);
        } else if ((!cbDauNguoi.isChecked()) && (!cbSot.isChecked()) && (!cbKhoTho.isChecked()) && (!cbHo.isChecked())) {
            cbTot.setEnabled(true);
        }
    }

    private void onClickCheckBox() {
        cbTot.setOnCheckedChangeListener(this);
        cbHo.setOnCheckedChangeListener(this);
        cbSot.setOnCheckedChangeListener(this);
        cbKhoTho.setOnCheckedChangeListener(this);
        cbDauNguoi.setOnCheckedChangeListener(this);
    }

    private void configCheckBoxTot(boolean isChecked) {
        if (isChecked) {
            cbDauNguoi.setActivated(false);
            cbDauNguoi.setEnabled(false);
            cbKhoTho.setActivated(false);
            cbKhoTho.setEnabled(false);
            cbHo.setActivated(false);
            cbHo.setEnabled(false);
            cbSot.setActivated(false);
            cbSot.setEnabled(false);
        } else {
            cbDauNguoi.setEnabled(true);
            cbKhoTho.setEnabled(true);
            cbHo.setEnabled(true);
            cbSot.setEnabled(true);
        }
    }

    void getDeclare(final int page) {
        DataServices.getAPIService().callHistoryDeclare(page + "", "10", getToken(getContext()))
                .enqueue(new Callback<HealthMonitor>() {
                    @Override
                    public void onResponse(Call<HealthMonitor> call, Response<HealthMonitor> response) {
//                        Toast.makeText(getContext(), "oke", Toast.LENGTH_SHORT).show();
                        if (response.body().getErrorCode() == 0) {
                            if (page == 0)
                                data.clear();
                            if(response.body().getData().getItemDeclare().size() == 0){
                                lastpage = true;
                            }
                            data.addAll((ArrayList<ItemDeclare>) response.body().getData().getItemDeclare());
                            adapter.notifyDataSetChanged();
                            showProgressBar(false);
                        } else {
                            Toast.makeText(getContext(), "Vui lòng kiểm tra lại đường truyền!", Toast.LENGTH_SHORT).show();
                            showProgressBar(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<HealthMonitor> call, Throwable t) {
                        showProgressBar(false);
                    }
                });
//                .enqueue(new Callback<List<Declare>>() {
//                    @Override
//                    public void onResponse(Call<List<Declare>> call, Response<List<Declare>> response) {
//                        Toast.makeText(getContext(), "oke", Toast.LENGTH_SHORT).show();
//                        if (response.isSuccessful()) {
//                            data.clear();
//                            data.addAll((ArrayList<Declare>) response.body());
//                            adapter.notifyDataSetChanged();
//                            loadingDialog.dismissLoadingDialog();
//                        }
//                        else{
//                            loadingDialog.dismissLoadingDialog();
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Declare>> call, Throwable t) {
//                        loadingDialog.dismissLoadingDialog();
//                    }
//                });
    }

    private void showProgressBar(boolean b) {
        progressBar.setActivated(b);
    }

    private void init(View view) {
        lv = view.findViewById(R.id.lv_history);
        cbHo = view.findViewById(R.id.checkbox_ho);
        cbSot = view.findViewById(R.id.checkbox_sot);
        cbKhoTho = view.findViewById(R.id.checkbox_kho_tho);
        cbDauNguoi = view.findViewById(R.id.checkbox_dau_nguoi);
        cbTot = view.findViewById(R.id.checkbox_suc_khoe_tot);
        btnSent = view.findViewById(R.id.btn_sent);
        loadingDialog = new LoadingDialog(getActivity());
        getDeclare(page);
        progressBar = view.findViewById(R.id.progress_bar);
        showProgressBar(true);
    }


}
