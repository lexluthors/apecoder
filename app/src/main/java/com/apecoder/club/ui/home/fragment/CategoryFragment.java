package com.apecoder.club.ui.home.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.apecoder.apecoder.R;
import com.apecoder.club.base.BaseBean;
import com.apecoder.club.base.BaseFragment;
import com.apecoder.club.bean.ArticleEntity;
import com.apecoder.club.http.ApiManager;
import com.apecoder.club.http.BaseObserver;
import com.apecoder.club.http.HttpUtils;
import com.apecoder.club.http.ParamsManager;
import com.apecoder.club.ui.adapter.CategoryFragmentAdapter;
import com.apecoder.club.ui.common.WebViewActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description: 每日爆款
 * author: liujie
 * date: 2018/9/25 18:57
 */
public class CategoryFragment extends BaseFragment implements View.OnClickListener {
    private static final int pageSize = 10;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    private int page = 1;
    private int category = -1;
    private CategoryFragmentAdapter adapter;
    private List<ArticleEntity> mAllList = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL,1));
    }

    @Override
    public void initData() {
        category = getArguments().getInt("category", -1);
        adapter = new CategoryFragmentAdapter(getActivity(),mAllList);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                if(category==-1){
                    //获取全部
                    getAllList();
                }else{
                    getList();
                }
            }
        }, recyclerView);
        if(category==-1){
            //获取全部
            getAllList();
        }else{
            getList();
        }
        recyclerView.setAdapter(adapter);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mAllList.clear();
                adapter.notifyDataSetChanged();
                if(category==-1){
                    //获取全部
                    getAllList();
                }else{
                    getList();
                }
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebViewActivity.intentStart(getActivity(),mAllList.get(position).getTitle(),mAllList.get(position).getLink());
            }
        });
    }

    private void getList() {
        //素材分享列表，参数都是默认参数，参考接口文档
        HttpUtils.APIFunction(ApiManager.mProfileApi.getArticleList(ParamsManager.getCateParams(page, category)), new BaseObserver<List<ArticleEntity>>() {
            @Override
            protected void onSuccees(BaseBean<List<ArticleEntity>> t) throws Exception {
                if (t.getCode() == 1) {
                    mAllList.addAll(t.getData());
                    adapter.notifyDataSetChanged();
                    adapter.loadMoreComplete();
                    if (t.getData().size() != pageSize) {
                        adapter.loadMoreEnd();
                    }
                } else {
                    showShortToast(t.getMsg());
                }
                if (swipeLayout.isRefreshing()) {
                    swipeLayout.setRefreshing(false);
                }
            }
        });
    }

    private void getAllList() {
        //素材分享列表，参数都是默认参数，参考接口文档
        HttpUtils.APIFunction(ApiManager.mProfileApi.getAllArticles(ParamsManager.getListParams(page, pageSize)), new BaseObserver<List<ArticleEntity>>() {
            @Override
            protected void onSuccees(BaseBean<List<ArticleEntity>> t) throws Exception {
                if (t.getCode() == 1) {
                    mAllList.addAll(t.getData());
                    adapter.notifyDataSetChanged();
                    adapter.loadMoreComplete();
                    if (t.getData().size() != pageSize) {
                        adapter.loadMoreEnd();
                    }
                } else {
                    showShortToast(t.getMsg());
                }
                if (swipeLayout.isRefreshing()) {
                    swipeLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radial:
                break;
            default:
                break;
        }
    }

}
