package com.tneciv.zhihudaily.detail.view;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;

import com.tneciv.zhihudaily.R;

/**
 * Created by Tneciv on 3-8-0008.
 */
public class BottomSheetDialogView {
    private Context context;
    private Boolean isNightMode;

    public BottomSheetDialogView(Context context, Boolean isNightMode) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.share_bottomsheet);
        dialog.show();
    }

    public static void show(Context context, Boolean isNightMode) {
        new BottomSheetDialogView(context, isNightMode);
    }


}
