package kitrobot.com.wechat_bottom_navigation;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import kitrobot.com.wechat_bottom_navigation.fragment.CategoryFragment;
import kitrobot.com.wechat_bottom_navigation.fragment.FindFragment;
import kitrobot.com.wechat_bottom_navigation.fragment.HomeFragment;
import kitrobot.com.wechat_bottom_navigation.fragment.MineFragment;
import kitrobot.com.wechat_bottom_navigation.view.MyImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private MyImageView mIvHome; // tab 消息的imageview
    private TextView mTvHome;   // tab 消息的imageview

    private MyImageView mIvCategory; // tab 通讯录的imageview
    private TextView mTvCategory;

    private MyImageView mIvFind;  // tab 发现的imageview
    private TextView mTvFind;

    private MyImageView mIvMine; // tab 我的imageview
    private TextView mTvMine;

    private ArrayList<Fragment> mFragments;
    private ArgbEvaluator mColorEvaluator;

    private int mTextNormalColor;// 未选中的字体颜色
    private int mTextSelectedColor;// 选中的字体颜色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initColor();//也就是选中未选中的textview的color
        initView();// 初始化控件
        initData(); // 初始化数据(也就是fragments)
        initSelectImage();// 初始化渐变的图片
        aboutViewpager(); // 关于viewpager
        setListener(); // viewpager设置滑动监听
    }

    private void initSelectImage() {
        mIvHome.setImages(R.drawable.home_normal, R.drawable.home_selected);
        mIvCategory.setImages(R.drawable.category_normal, R.drawable.category_selected);
        mIvFind.setImages(R.drawable.find_normal, R.drawable.find_selected);
        mIvMine.setImages(R.drawable.mine_normal, R.drawable.mine_selected);
    }

    private void initColor() {
        mTextNormalColor = getResources().getColor(R.color.main_bottom_tab_textcolor_normal);
        mTextSelectedColor = getResources().getColor(R.color.main_bottom_tab_textcolor_selected);
    }


    private void setListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPs) {
                    setTabTextColorAndImageView(position,positionOffset);// 更改text的颜色还有图片
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void setTabTextColorAndImageView(int position, float positionOffset) {
        mColorEvaluator = new ArgbEvaluator();  // 根据偏移量 来得到
        int  evaluateCurrent =(int) mColorEvaluator.evaluate(positionOffset,mTextSelectedColor , mTextNormalColor);//当前tab的颜色值
        int  evaluateThe =(int) mColorEvaluator.evaluate(positionOffset, mTextNormalColor, mTextSelectedColor);// 将要到tab的颜色值
        switch (position) {
            case 0:
                mTvHome.setTextColor(evaluateCurrent);  //设置消息的字体颜色
                mTvCategory.setTextColor(evaluateThe);  //设置通讯录的字体颜色

                mIvHome.transformPage(positionOffset);  //设置消息的图片
                mIvCategory.transformPage(1-positionOffset); //设置通讯录的图片
                break;
            case 1:
                mTvCategory.setTextColor(evaluateCurrent);
                mTvFind.setTextColor(evaluateThe);

                mIvCategory.transformPage(positionOffset);
                mIvFind.transformPage(1-positionOffset);
                break;
            case 2:
                mTvFind.setTextColor(evaluateCurrent);
                mTvMine.setTextColor(evaluateThe);

                mIvFind.transformPage(positionOffset);
                mIvMine.transformPage(1-positionOffset);
                break;

        }
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new CategoryFragment());
        mFragments.add(new FindFragment());
        mFragments.add(new MineFragment());
    }

    private void aboutViewpager() {
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(), mFragments);// 初始化adapter
        mViewPager.setAdapter(myAdapter); // 设置adapter
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mIvHome = (MyImageView) findViewById(R.id.iv1);  // tab 微信 imageview
        mTvHome = (TextView) findViewById(R.id.rb1);  //  tab  微信 字

        mIvCategory = (MyImageView) findViewById(R.id.iv2); // tab 通信录 imageview
        mTvCategory = (TextView) findViewById(R.id.rb2);  // tab   通信录 字

        mIvFind = (MyImageView) findViewById(R.id.iv3); // tab 发现 imageview
        mTvFind = (TextView) findViewById(R.id.rb3);   //  tab  发现 字

        mIvMine = (MyImageView) findViewById(R.id.iv4);   // tab 我 imageview
        mTvMine = (TextView) findViewById(R.id.rb4);    // tab   我 字
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.ll_categroy:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.ll_find:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.ll_mine:
                mViewPager.setCurrentItem(3);
                break;
        }
    }

}
