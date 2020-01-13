package frox.world.com.permissionManager;
import android.app.Activity;

import androidx.core.app.ActivityCompat;


public class PermissionsManager {

     public static  void checkPermission(Activity a, String[] p) {
        //if (ContextCompat.checkSelfPermission(a.getApplication(), p) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(a, p,1);
        //}
    }
}
