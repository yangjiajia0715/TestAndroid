package com.testandroid.yang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.AutoLineLayoutManage;
import com.testandroid.yang.adapter.FlowLayoutManager;
import com.testandroid.yang.adapter.RecycleviewAdapter;
import com.testandroid.yang.common.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecycleViewActivity
 * {@link RecycleviewAdapter}
 * {@link AutoLineLayoutManage}
 * Created by yangjiajia on 2017/4/11.
 */

public class RecycleViewActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RecycleViewActivity";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.rv_layoutmanager)
    TextView textView;
    private RecycleviewAdapter recycleviewAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, RecycleViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    @Override
    public void initView() {
        textView.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //custom
        AutoLineLayoutManage autoLineLayoutManage = new AutoLineLayoutManage();

        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();

//        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setLayoutManager(autoLineLayoutManage);

        recyclerView.setLayoutManager(flowLayoutManager);

        initAdapter();

//        recyclerView.setRecycledViewPool(new CusPool());

//        recycleviewAdapter.setHasStableIds(true);

//        recyclerView.setItemViewCacheSize(10);
//        recyclerView.getRecycledViewPool().setMaxRecycledViews();

        RecyclerView.RecycledViewPool recycledViewPool = recyclerView.getRecycledViewPool();

//        recycledViewPool.getRecycledView()
        recyclerView.setAdapter(recycleviewAdapter);

        recyclerView.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
//                holder.getAdapterPosition()
//                Log.d(TAG, "onViewRecycled: getAdapterPosition=" + holder.getAdapterPosition());
            }
        });

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 3000);
    }

    private void initAdapter() {
        Random random = new Random();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int nextInt = random.nextInt();
            switch (nextInt % 4) {
                case 0:
                    students.add(new Student("优" + i, i));
                    break;
                case 1:
                    students.add(new Student("错题会" + i, i));
                    break;
                case 2:
                    students.add(new Student("马虎" + i, i));
                    break;
                case 3:
                    students.add(new Student("恭喜发财" + i, i));
                    break;
                default:
                    students.add(new Student("default" + i, i));
                    break;
            }

        }
        recycleviewAdapter = new RecycleviewAdapter(this, students);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rv_layoutmanager:
                Toast.makeText(this, "rv_layoutmanager", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class CusPool extends RecyclerView.RecycledViewPool {


    }

}
