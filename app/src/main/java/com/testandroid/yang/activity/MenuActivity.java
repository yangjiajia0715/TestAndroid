package com.testandroid.yang.activity;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.testandroid.yang.R;
import com.testandroid.yang.adapter.MultiChoiceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 三种菜单
 * Created by yangjiajia on 2017/2/19 0019.
 */

public class MenuActivity extends BaseActivity {
    private static final String TAG = "MenuActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_option_menu)
    TextView tvOptionMenu;
    @BindView(R.id.tv_context_menu)
    TextView tvContextMenu;
    @BindView(R.id.tv_action_mode)
    TextView tvActionMode;
    @BindView(R.id.tv_pop_menu)
    TextView tvPopMenu;

    TextView tvOther;

    ListView listview;
    ListView listviewRight;

    ActionMode mActionMode;

    ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.d(TAG, "onCreateActionMode: ");
            mode.getMenuInflater().inflate(R.menu.action_mode_menu, menu);
            mode.setTitle("Mode");
            mode.setSubtitle("Subtitle");
//            mode.setType(ActionMode.TYPE_FLOATING);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.d(TAG, "onPrepareActionMode: ");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.drop_down_answer_action_mode:
                    Log.d(TAG, "onActionItemClicked:1 item=" + item);
                    break;
                case R.id.drop_down_setting_action_mode:
                    Log.d(TAG, "onActionItemClicked: 2item=" + item);
                    mode.finish();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.d(TAG, "onDestroyActionMode: ");
            mActionMode = null;
        }
    };
    private PopupMenu popupMenu;

    public static void start(Context context) {
        Intent starter = new Intent(context, MenuActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Log.d(TAG, "onCreate: " + metrics);
        initView();
        initData();
    }

    @Override
    public void initView() {
        Log.d(TAG, "initView: toolBar=" + toolbar);

        if (toolbar == null)
            toolbar = (Toolbar) findViewById(R.id.toolbar);

        Log.d(TAG, "initView: toolBar=" + findViewById(R.id.toolbar));

        toolbar.setTitle(R.string.text_three_menu);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            Log.d(TAG, "initView: actionBar=" + actionBar);
            actionBar.setDisplayHomeAsUpEnabled(false);//隐藏返回按钮
            actionBar.setDisplayHomeAsUpEnabled(true);//需配合manifest.xml,
        }

        if (tvContextMenu == null) {
            tvContextMenu = (TextView) findViewById(R.id.tv_context_menu);
        }

        tvPopMenu = (TextView) findViewById(R.id.tv_pop_menu);

        tvActionMode = (TextView) findViewById(R.id.tv_action_mode);
        tvOther = (TextView) findViewById(R.id.tv_pop_menu_other);

        listview = (ListView) findViewById(R.id.listview);
        listviewRight = (ListView) findViewById(R.id.listviewright);
    }


    //optionmenu 每次show之前都会调用该方法，或者，invalidate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu: menu=" + menu);
        MenuItem menuItem = menu.findItem(R.id.drop_down_answer);
        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setIconifiedByDefault(false);
        Log.d(TAG, "onCreateOptionsMenu: searchView=" + searchView);

        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        actionProvider.setShareIntent(getDefaultIntent());

        //注意配：app:showAsAction="ifRoom|collapseActionView"
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Log.d(TAG, "onMenuItemActionExpand: item=" + item);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Log.d(TAG, "onMenuItemActionCollapse: item=" + item);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.add("动态添加");
        Log.d(TAG, "onPrepareOptionsMenu: menu=" + menu);
        return super.onPrepareOptionsMenu(menu);
//        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d(TAG, "onCreateContextMenu: menu=" + menu + ",v=" + v + ",menuInfo=" + menuInfo);
        getMenuInflater().inflate(R.menu.context_ment, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d(TAG, "onContextItemSelected: item=" + item);
        switch (item.getItemId()) {
            case R.id.drop_down_answer:
                Toast.makeText(this, "drop_down_answer上下文菜单", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.drop_down_setting:
                Toast.makeText(this, "drop_down_setting上下文菜单", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.drop_down_answer:
//                Toast.makeText(this, "drop_down_answer", Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.drop_down_setting:
                Toast.makeText(this, "drop_down_setting", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        return super.getSupportParentActivityIntent();
    }

    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
    }

    @Override
    public void initData() {
        registerForContextMenu(tvContextMenu);

        tvOther.setSelected(true);

        tvActionMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActionMode != null)
                    return;

                v.startActionMode(mActionModeCallback);
//                v.setSelected(true);
            }
        });

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("点我点我" + i);
        }

//        listview.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Log.d(TAG, "onItemCheckedStateChanged: position=" + position + ",checked=" + checked);
//                listview.getCheckedItemIds();
//                listview.setItemChecked(position,true);
                Log.d(TAG, "onItemCheckedStateChanged: getCheckedItemCount=" + listview.getCheckedItemCount());
//                Log.d(TAG, "onItemCheckedStateChanged: getCheckedItemPosition=" +  listview.getCheckedItemPosition());
                Log.d(TAG, "onItemCheckedStateChanged: getCheckedItemPositions=" + listview.getCheckedItemPositions());
                long[] checkedItemIds = listview.getCheckedItemIds();

                for (long checkedItemId : checkedItemIds) {
                    Log.d(TAG, "onItemCheckedStateChanged: checkedItemId=" + checkedItemId);
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                Log.d(TAG, "onCreateActionMode: ");
                mode.getMenuInflater().inflate(R.menu.action_mode_listview_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                Log.d(TAG, "onPrepareActionMode: ");
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                Log.d(TAG, "onActionItemClicked: item=" + item.getTitle());
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                Log.d(TAG, "onDestroyActionMode: ");

            }
        });

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        listview.setAdapter(adapter);

//        listview.setLongClickable(true);

        List<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringArrayList.add("复选框--点我点我" + i);
        }

        MultiChoiceAdapter choiceAdapter = new MultiChoiceAdapter(this, stringArrayList, listviewRight);
//        choiceAdapter.setOnCheckedChangeListenertt(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
        listviewRight.setAdapter(choiceAdapter);
        listviewRight.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listviewRight.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Log.d(TAG, "onItemCheckedStateChanged--listviewRight: checked=" + checked);
                Log.d(TAG, "onItemCheckedStateChanged--listviewRight: getCheckedItemCount=" + listviewRight.getCheckedItemCount());
                Log.d(TAG, "onItemCheckedStateChanged--listviewRight: getCheckedItemPositions=" + listviewRight.getCheckedItemPositions());

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                Log.d(TAG, "onCreateActionMode--listviewRight: ");
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                Log.d(TAG, "onPrepareActionMode--listviewRight: ");
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });


        tvPopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMenu(v);
            }
        });
    }

    private void showPopMenu(View v) {
        if (popupMenu == null) {
//            popupMenu = new PopupMenu(this, v, Gravity.END);
            popupMenu = new PopupMenu(this, v, Gravity.END, R.attr.popupMenuStyle, R.style.PopmenuStyle);
            popupMenu = new PopupMenu(this, v, Gravity.END, R.attr.popupMenuStyle, R.style.PopmenuStyle);
//        popupMenu.inflate();
            popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.drop_down_pop_menu_1:
                            item.setChecked(true);
                            break;
                        case R.id.drop_down_pop_menu_2:
                            item.setChecked(true);
//                            Log.d(TAG, "onMenuItemClick: isChecked=" + item.isChecked());
//                            if (item.isChecked()) {
//                                item.setChecked(false);
//                            } else {
////                                item.setCheckable(true);
//                                item.setChecked(true);
//                            }
                            return true;
                        default:
                            return false;

                    }
                    return false;
                }
            });
        }
        popupMenu.show();

    }


}
