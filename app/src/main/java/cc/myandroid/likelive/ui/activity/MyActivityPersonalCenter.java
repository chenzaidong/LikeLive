package cc.myandroid.likelive.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.db.DBManager;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.utils.ImageUtil;
import cc.myandroid.likelive.utils.SPUtils;

public class MyActivityPersonalCenter extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EditText et_nickname;
    private TextView tv_nickName;
    private TextView tv_sex;
    private LayoutInflater inflater;
    private PopupWindow mPopupWindow;
    private LinearLayout click_picture, click_camera, click_cancel;
    private RelativeLayout head, nickname, sex;
    private ImageView img_head_portrait;
    CheckBox chb_man, chb_woman;
    private String change_sex;//定义一个全局的变量,用户选着什么就是什么
    private Uri muri;//图片保存的地址
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_personal_center);
        muri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp.jpg"));
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        user = SPUtils.getUser();
        findView();
        setOnListener();
    }

    /**
     * 设置监听器
     */
    private void setOnListener() {
        head.setOnClickListener(this);
        nickname.setOnClickListener(this);
        sex.setOnClickListener(this);
    }

    /**
     * 查找控件
     */
    private void findView() {
        head = (RelativeLayout) findViewById(R.id.head);
        nickname = (RelativeLayout) findViewById(R.id.nickname);
        sex = (RelativeLayout) findViewById(R.id.sex);
        img_head_portrait = (ImageView) findViewById(R.id.img_head_portrait);
        tv_nickName = (TextView) findViewById(R.id.tv_nickName);
        tv_sex = (TextView) findViewById(R.id.tv_sex);

        if (user != null) {
            if (user.getIcon() == null) {
                img_head_portrait.setImageResource(R.mipmap.login_icon);
            } else {
                File file = new File(user.getIcon());
                ImageUtil.laodImage(this, file, img_head_portrait,ImageUtil.CIRCLE,0);
            }
            tv_nickName.setText(user.getNickname());
            tv_sex.setText(user.getGender());
        }
    }

    /**
     * 设置里面里面的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head:
                showPopupWindow();
                break;
            case R.id.nickname:
                showNicknameDiaLog();
                break;
            case R.id.sex:
                showSexChange();
                break;

        }
    }

    /**
     * 弹出性别对话框
     */
    private void showSexChange() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.change_sex, null);
        chb_man = (CheckBox) view.findViewById(R.id.chb_man);
        chb_woman = (CheckBox) view.findViewById(R.id.chb_woman);
        chb_man.setOnCheckedChangeListener(this);
        chb_woman.setOnCheckedChangeListener(this);
        builder.setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_sex.setText(change_sex);
                        Toast.makeText(MyActivityPersonalCenter.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * 选着性别的监听
     *
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (chb_man.getId() == buttonView.getId()) {
            if (isChecked) {
                chb_woman.setChecked(false);
                change_sex = "男";
            }
        } else if (chb_woman.getId() == buttonView.getId()) {
            if (isChecked) {
                chb_man.setChecked(false);
                change_sex = "女";
            }
        }
        user.setGender(change_sex);
        SPUtils.saveUser(user);
        DBManager.getInstance().updateUser(user);
    }

    /**
     * 弹出一个自定义对话框,修改昵称
     */
    private void showNicknameDiaLog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view3 = getLayoutInflater().inflate(R.layout.dialog_edittext, null);
        et_nickname = (EditText) view3.findViewById(R.id.et_nickname);
        builder.setView(view3)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String new_nickname = et_nickname.getText().toString().trim();
                        tv_nickName.setText(new_nickname);
                        user.setNickname(new_nickname);
                        SPUtils.saveUser(user);
                        DBManager.getInstance().updateUser(user);
                    }
                })
                .setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 展示向下到上的一个动画
     */
    private void showPopupWindow() {
        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.options_head_portrait, null);
        mPopupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        click_picture = (LinearLayout) view.findViewById(R.id.click_picture);
        click_camera = (LinearLayout) view.findViewById(R.id.click_camera);
        click_cancel = (LinearLayout) view.findViewById(R.id.click_cancel);

        click_picture.setOnClickListener(new Click_OptionsView());
        click_camera.setOnClickListener(new Click_OptionsView());
        click_cancel.setOnClickListener(new Click_OptionsView());

        View view1 = inflater.inflate(R.layout.activity_my_personal_center, null);
        mPopupWindow.showAtLocation(view1, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 写个内部类 实现 监听(图片选着,拍照选着,和取消)
     */
    public class Click_OptionsView implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.click_picture:
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, 1);
                    mPopupWindow.dismiss();
                    break;
                case R.id.click_camera:
                    Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent1.putExtra(MediaStore.EXTRA_OUTPUT, muri);
                    startActivityForResult(intent1, 2);
                    mPopupWindow.dismiss();
                    break;
                case R.id.click_cancel:
                    mPopupWindow.dismiss();
                    break;
            }
        }
    }

    /**
     * 照片选着好了或者拍照好了 返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {//图库
                Uri uri = data.getData();
                Bitmap bitmap = decodesampledBitmapFromResource(uri, img_head_portrait.getWidth(), img_head_portrait.getHeight());
                img_head_portrait.setImageBitmap(bitmap);
                user.setIcon(findPath(uri));
                SPUtils.saveUser(user);
                DBManager.getInstance().updateUser(user);
            } else if (requestCode == 2) {//拍照
                Bitmap bitmap = decodesampledBitmapFromResource(muri.getPath(), img_head_portrait.getWidth(), img_head_portrait.getHeight());
                img_head_portrait.setImageBitmap(bitmap);
                user.setIcon(muri.getPath());
                SPUtils.saveUser(user);
                DBManager.getInstance().updateUser(user);
            }
        }
    }

    public Bitmap decodesampledBitmapFromResource(Uri uri, int reqWidth, int reqHeigth) {
        return decodesampledBitmapFromResource(findPath(uri), reqWidth, reqHeigth);
    }

    public Bitmap decodesampledBitmapFromResource(String path, int reqWidth, int reqHeigth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeigth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeigth) {
        //获取位图的宽高
        int width = options.outWidth;
        int heigth = options.outHeight;
        Log.e("------------", heigth + "");
        int inSampleSize = 1;
        if (heigth > reqHeigth || width > reqWidth) {
            if (width > heigth) {
                inSampleSize = Math.round((float) heigth / (float) reqHeigth);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    private String findPath(Uri uri) {
        //在哪里去找
        String[] proj = {MediaStore.Images.Media.DATA};
        //下面就要去找了
        Cursor cursor = managedQuery(uri, proj, null, null, null);   //返回了一个游标
        //通过字段首选查找那个字段额索引
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(index);
        Log.e("路径是:", path);
        return path;
    }
}
