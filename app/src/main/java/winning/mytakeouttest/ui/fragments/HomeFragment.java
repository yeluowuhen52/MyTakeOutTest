package winning.mytakeouttest.ui.fragments;

import android.animation.ArgbEvaluator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import winning.mytakeouttest.R;
import winning.mytakeouttest.databinding.FragmentHomeBinding;
import winning.mytakeouttest.ui.adapter.HomeRecycleViewAdapter;
import winning.mytakeouttest.ui.base.BaseFragment;
import winning.mytakeouttest.utils.LogUtil;


/**
 * Description: 首页
 * Author: Jiang
 * Date:   2017/1/17
 */
public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;
    //当前滑动距离
    private int sumY = 0;

    private float duration = 150.0f;

    private ArgbEvaluator argbEvaluator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.homeRecycleview.setAdapter(new HomeRecycleViewAdapter());
        binding.homeRecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        //RecycleView的滑动监听(用来处理顶部栏的透明度)
        binding.homeRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sumY += dy;
                LogUtil.d("infotest", sumY / duration + "");
                argbEvaluator = new ArgbEvaluator();
                int bgColor = 0x553190E8;
                if (sumY < 0) {
                    bgColor = 0x553190E8;
                } else if (sumY > 150) {
                    bgColor = 0xFF3190E8;
                } else {
                    bgColor = (int) argbEvaluator.evaluate(sumY / duration, 0x553190E8, 0xFF3190E8);
                }
                binding.llTitleContainer.setBackgroundColor(bgColor);
            }
        });

    }
}
