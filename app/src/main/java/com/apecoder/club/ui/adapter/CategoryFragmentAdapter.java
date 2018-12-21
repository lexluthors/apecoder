package com.apecoder.club.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.apecoder.apecoder.R;
import com.apecoder.club.bean.ArticleEntity;
import com.apecoder.club.util.GlideUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * description:
 * author: liujie
 * date: 2018/9/26 14:27
 */
public class CategoryFragmentAdapter extends BaseQuickAdapter<ArticleEntity, BaseViewHolder> {
    private Context mContext;

    public CategoryFragmentAdapter(Context context,List<ArticleEntity> mData) {
        super(R.layout.item_category_layourt, mData);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleEntity mListBean) {
        ((TextView) helper.getView(R.id.title_des)).setText(mListBean.getTitle());
        ((TextView) helper.getView(R.id.detail)).setText(mListBean.getDes());
        ((TextView) helper.getView(R.id.nickname)).setText(mListBean.getUser().getNickName());
        ((TextView) helper.getView(R.id.comments)).setText(mListBean.getPraise()+"赞");
        if(TextUtils.isEmpty(mListBean.getCoverImage())){
            helper.getView(R.id.cover_image).setVisibility(View.GONE);
        }else{
            helper.getView(R.id.cover_image).setVisibility(View.VISIBLE);
            GlideUtil.display(mContext, helper.getView(R.id.cover_image), mListBean.getCoverImage());
        }
        GlideUtil.display(mContext, helper.getView(R.id.avatar), mListBean.getUser().getAvatar());
    }

}
