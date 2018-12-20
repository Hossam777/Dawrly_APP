package com.example.khaled.dawarly.Controller;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.khaled.dawarly.Entities.Item;
import com.example.khaled.dawarly.Entities.Report;
import com.example.khaled.dawarly.Entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;


public class FireBaseClass {

    //callback template to use
    public interface FirebaseCallback
    {
        void upload_done(boolean bool);
        void geturi(String uri);
        void getitems(ArrayList<Item> items);
        void getreports(ArrayList<Report> reports);
        void getuser(User user);
    }

    private StorageReference mstorageRef ;
    private FirebaseFirestore db ;
    Activity activity;

    public FireBaseClass(Activity a)
    {
        activity = a;
        db = FirebaseFirestore.getInstance();
        mstorageRef = FirebaseStorage.getInstance().getReference();
    }


    public void LoadUser(String mail,String Password,final FirebaseCallback firebaseCallback){
        DocumentReference docRef = db.collection("USERS").document(mail);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        final User user = new User();
                        String userdata = "";
                        if(document.getData().get("Name") != null)
                            user.setName(document.getData().get("Name").toString());
                        if(document.getData().get("Mobile") != null)
                            user.setMobile(document.getData().get("Mobile").toString());
                        if(document.getData().get("Password") != null)
                            user.setPassword(document.getData().get("Password").toString());
                        if(document.getData().get("UserData") != null)
                            userdata = document.getData().get("UserData").toString();
                        String arr[] = userdata.split(";");
                        user.setGender(arr[0]);
                        user.setAge(Integer.parseInt(arr[1]));
                        user.setRate(Float.parseFloat(arr[2]));
                        user.setBan_Status(Boolean.parseBoolean(arr[3]));
                        firebaseCallback.getuser(user);
                    } else {
                        firebaseCallback.getuser(null);
                    }
                } else {
                    //task not successfull
                    Toast.makeText(activity,"Firebase is busy, try again later",Toast.LENGTH_SHORT).show();
                    firebaseCallback.getuser(null);
                }
            }
        });
    }
    public void UpdateUser(final User user,final FirebaseCallback firebaseCallback){
        DocumentReference docRef = db.collection("USERS").document(user.getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //cannt signup with the same mail
                        HashMap<String,String> userdata = new HashMap<>();
                        userdata.put("Mobile",user.getMobile());
                        userdata.put("Name",user.getName());
                        userdata.put("Password",user.getPassword());
                        userdata.put("UserData",user.getGender()+";"+user.getAge()+";"+user.getRate()+";"+user.getBan_Status());
                        db.collection("USERS").document(user.getEmail()).set(userdata);
                        firebaseCallback.upload_done(true);

                    } else {
                        Toast.makeText(activity,"could not update",Toast.LENGTH_SHORT).show();
                        firebaseCallback.upload_done(false);
                    }
                } else {
                    Toast.makeText(activity,"Server busy, try again later",Toast.LENGTH_SHORT).show();
                    firebaseCallback.upload_done(false);
                }
            }
        });
    }
    public void UploadUser(final User user,final FirebaseCallback firebaseCallback){
        DocumentReference docRef = db.collection("USERS").document(user.getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //cannt signup with the same mail
                        Toast.makeText(activity,"this mail already signed up",Toast.LENGTH_SHORT).show();
                        firebaseCallback.upload_done(false);
                    } else {
                        HashMap<String,String> userdata = new HashMap<>();
                        userdata.put("Mobile",user.getMobile());
                        userdata.put("Name",user.getName());
                        userdata.put("Password",user.getPassword());
                        userdata.put("UserData",user.getGender()+";"+user.getAge()+";"+user.getRate()+";"+user.getBan_Status());
                        db.collection("USERS").document(user.getEmail()).set(userdata);
                        firebaseCallback.upload_done(true);
                    }
                } else {
                    Toast.makeText(activity,"Server busy, try again later",Toast.LENGTH_SHORT).show();
                    firebaseCallback.upload_done(false);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(activity,"failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void LoadItems(final FirebaseCallback firebaseCallback){
        final ArrayList<Item>items = new ArrayList<>();
        db.collection("ITEMS").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot document : task.getResult()){
                            final Item item = new Item();
                            item.setItemid(document.getId());
                            if(document.getData().get("Name") != null)
                                item.setName(document.getData().get("Name").toString());
                            if(document.getData().get("Desc") != null)
                                item.setDescription(document.getData().get("Desc").toString());
                            if(document.getData().get("Cat") != null)
                                item.setCategory(document.getData().get("Cat").toString());
                            if(document.getData().get("Pic") != null)
                                item.setPictureurl(document.getData().get("Pic").toString());
                            if(document.getData().get("Quiz") != null)
                                item.setQuiz(document.getData().get("Quiz").toString());
                            items.add(item);
                        }
                        firebaseCallback.getitems(items);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity,"failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void UploadItem(final Item item,final FirebaseCallback firebaseCallback){
        UploadPhoto(item.getPicture(),new FirebaseCallback() {
            @Override
            public void geturi(String uri) {
                final HashMap<String,String> itemdata = new HashMap<>();
                itemdata.put("Cat",item.getCategory());
                itemdata.put("Desc",item.getDescription());
                itemdata.put("Name",item.getName());
                itemdata.put("Quiz",item.getQuiz().getQuestions_toString());
                itemdata.put("Pic",uri);
                db.collection("ITEMS").add(itemdata);
                firebaseCallback.upload_done(true);
            }
            @Override
            public void upload_done(boolean bool) {
                if(!bool)
                    firebaseCallback.upload_done(false);
            }

            @Override
            public void getitems(ArrayList<Item> items) {

            }

            @Override
            public void getreports(ArrayList<Report> reports) {

            }

            @Override
            public void getuser(User user) {

            }

        });
    }

    public void DeleteItem(String id,final FirebaseCallback firebaseCallback){
        db.collection("ITEMS").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        firebaseCallback.upload_done(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        firebaseCallback.upload_done(false);
                    }
                });

    }

    public void LoadReports(final FirebaseCallback firebaseCallback){
        final ArrayList<Report> reports = new ArrayList<>();
        db.collection("REPORTS").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot document : task.getResult()){
                            final Report report = new Report();
                                report.setID(document.getId());
                                if(document.getData().get("Email") != null)
                                    report.setEmail(document.getData().get("Email").toString());
                                if(document.getData().get("Description") != null)
                                    report.setDescription(document.getData().get("Description").toString());
                                reports.add(report);
                }
                        firebaseCallback.getreports(reports);
            }
        });
    }
    public void UploadReport(Report report,final FirebaseCallback firebaseCallback){
        HashMap<String,String> reportdata = new HashMap<>();
        reportdata.put("Email",report.getEmail());
        reportdata.put("Description",report.getDescription());
        db.collection("REPORTS").add(reportdata).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(activity,"Report uploaded",Toast.LENGTH_SHORT).show();
                firebaseCallback.upload_done(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity,"Failed, Try again later",Toast.LENGTH_SHORT).show();
                firebaseCallback.upload_done(false);
            }
        });
    }

    public void UploadPhoto(Uri file,final FirebaseCallback firebaseCallback){
        StorageReference reference = mstorageRef.child("profile_images");
        reference.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String download_Uri = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                firebaseCallback.geturi(download_Uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity,"Could not upload the photo!",Toast.LENGTH_SHORT).show();
                firebaseCallback.upload_done(false);
            }
        });
    }

    public boolean CheckInternetConnection() {
        if(AppStatus.getInstance(activity).isOnline())
            return true;
        return false;
    }

}
