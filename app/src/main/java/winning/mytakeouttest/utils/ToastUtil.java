package winning.mytakeouttest.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	public static void showText(Context context , String string){
		Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
	}
}
