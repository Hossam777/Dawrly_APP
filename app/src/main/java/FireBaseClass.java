
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.InetAddress;
import java.util.ArrayList;

public class FireBaseClass {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

}
